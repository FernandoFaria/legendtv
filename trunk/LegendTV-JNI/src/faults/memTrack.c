/* Copyright (c) 2007, Guy Paddock and The Legend TV Project
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the Legend TV Project nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE LEGEND TV PROJECT ``AS IS'' AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE LEGEND TV PROJECT BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/**
 * memTrack.c
 * ==========
 * 
 * Implementation of dynamic memory allocation tracker.
 * 
 * This is the second component of JNI fault tolerance and is needed so that
 * dynamic memory of a faulting module or modules can be unloaded when a fault
 * occurs.
 */

#define _GNU_SOURCE

#include <stdio.h>
#include <string.h>
#include <malloc.h>
#include <dlfcn.h>
#include "memTrack.h"
#include "critical.h"

/**
 * Structure representing a single element in the list that tracks the names of
 * the modules for which dynamic allocations will be tracked.
 */
typedef struct MemTrack_ModListElem_s
{
	/**
	 * The name of the module.
	 */
	const char*						name;
	
	/**
	 * The next item in the list, or NULL if this is the last item in the list.
	 */
	struct MemTrack_ModListElem_s*	next;
} MemTrack_ModListElem_t;

/**
 * Structure representing a single element in the list that tracks dynamic
 * memory allocations.
 */
typedef struct MemTrack_MemListElem_s
{
	/**
	 * Name of the module that allocated the memory.
	 */
	const char*						moduleName;
	
	/**
	 * Pointer to the start of the dynamic memory allocation
	 */
	void*							pointer;
	
	/**
	 * Size of the dynamic memory allocation.
	 */
	size_t							size;
	
	/**
	 * Pointer to the next element in the list after this element, or NULL if
	 * this is the last item in the list.
	 */
	struct MemTrack_MemListElem_s*	next;
} MemTrack_MemListElem_t;

/**
 * Type definition for malloc() hook functions.
 */
typedef void*	(*MemTrack_MallocHookFunc)	(size_t, const void*);

/**
 * Type definition for free() hook functions.
 */
typedef void	(*MemTrack_FreeHookFunc) 	(void*, const void*);

/**
 * Private pointer to the head of the modules list.
 */
MemTrack_ModListElem_t*	__modNameList	= NULL;

/**
 * Private pointer to the head of the memory allocation tracking list.
 */
MemTrack_MemListElem_t*	__allocList		= NULL;

/**
 * Mutex used to lock the malloc hooks.
 */
pthread_mutex_t	__hookMutex			= PTHREAD_MUTEX_INITIALIZER,
				__modListMutex		= PTHREAD_MUTEX_INITIALIZER,
				__allocListMutex	= PTHREAD_MUTEX_INITIALIZER;

/**
 * The following two private function pointers are used to preserve the
 * original values of __malloc_hook and __free_hook.
 */
MemTrack_MallocHookFunc	__origMalloc;
MemTrack_FreeHookFunc	__origFree;

/*
 * Prototype declarations.
 */
void __addTrackedModule(const char* moduleName);
void __removeTrackedModule(const char* moduleName);

void *__trackedMalloc(size_t size, const void *caller);
void __trackedFree(void *pointer, const void *caller);

void __addTracking(const char* moduleName, void* pointer, size_t size);

void __freeAll(const char* moduleName);
void __removeTracking(void* pointer);

/**
 * Function that initializes dynamic memory tracking for a particular module.
 * This function will install the appropriate hooks for malloc() and free().
 * 
 * @param	moduleName	The name of the module for which dynamic memory
 * 						tracking should start.
 */
void MemTrack_start(const char* moduleName)
{
	// Install hooks if this is the first module.
	if (__modNameList == NULL)
	{
		CRITICAL_SECTION(__hookMutex,
		{
			__origMalloc	= __malloc_hook;
			__malloc_hook	= __trackedMalloc;
		
			__origFree		= __free_hook;
			__free_hook		= __trackedFree;
		});
	}
	
	__addTrackedModule(moduleName);
}

/**
 * Function called when dynamic allocation tracking for a particular module
 * should cease.
 * 
 * If this is the last module being tracked, this function will also unhook
 * malloc() and free().
 * 
 * If shouldFree is true (non-zero), this will free all dynamic memory that
 * is still being tracked for the module.
 * 
 * @param	moduleName	The name of the module for which dynamic memory
 * 						tracking should stop.
 * @param	shouldFree	True (non-zero) if the dynamic memory still being
 * 						tracked for the module should be freed; false (zero)
 * 						if it should be left alone.
 */
void MemTrack_stop(const char* moduleName, int shouldFree)
{
	__removeTrackedModule(moduleName);
	
	// Remove hooks if this was the last module
	if (__modNameList == NULL)
	{
		CRITICAL_SECTION(__hookMutex,
		{
			__free_hook		= __origFree;
			__malloc_hook	= __origMalloc;
		});
	}
	
	if (shouldFree)
		__freeAll(moduleName);
}

/**
 * Private function that adds a module name to the list of modules whose
 * dynamic memory allocations are being tracked.
 * 
 * @param	moduleName	The name of the module to add to the list.
 */
void __addTrackedModule(const char* moduleName)
{
	MemTrack_ModListElem_t*	newElem;
	
	newElem			= (MemTrack_ModListElem_t*)MemTrack_untrackedMalloc(sizeof(MemTrack_ModListElem_t));
	newElem->name	= moduleName;
	
	CRITICAL_SECTION(__modListMutex,
	{
		newElem->next	= __modNameList;
		__modNameList	= newElem;
	});
}

/**
 * Private function that determines if the dynamic memory allocations of a
 * particular module are being tracked or not.
 * 
 * @param	moduleName	The name of the module in question.
 * @return				True (non-zero) if the allocations of the module are
 * 						being tracked; false (0) otherwise.
 */
int __isTrackedModule(const char* moduleName)
{
	int	retVal	= 0;
	
	if (moduleName != NULL)
	{
		CRITICAL_SECTION(__modListMutex,
		{
			for (MemTrack_ModListElem_t* cur = __modNameList; cur != NULL; cur = cur->next)
			{
				if (strstr(moduleName, cur->name) != NULL)
				{
					retVal	= 1;
					
					break;
				}
			}
		});
	}
		
	return (retVal);
}

/**
 * Private function that removes a module name from the list of modules whose
 * dynamic memory allocations are being tracked.
 * 
 * @param	moduleName	The name of the module to remove from the list.
 */
void __removeTrackedModule(const char* moduleName)
{
	MemTrack_ModListElem_t	*prev	= NULL,
							*next;
	
	CRITICAL_SECTION(__modListMutex,
	{
		for (MemTrack_ModListElem_t* cur = __modNameList; cur != NULL; cur = next)
		{
			next	= cur->next;
			
			if (strstr(cur->name, moduleName) != NULL)
			{
				if (prev != NULL)
				{
					// Removing item in the middle of the list;
					// point "next" of previous element to element after this one.
					prev->next		= cur->next;
				}
				
				else
				{
					// Removing the first item of the list;
					// point the head of list to the element after this one.
					__modNameList	= cur->next;
				}
					
				MemTrack_untrackedFree(cur);
				
				break;
			}
			
			else
			{
				// Keep track of last item examined, in case the next item is the one
				// being removed.
				prev	= cur;
			}
		}
	});
}

/**
 * Private function that allocates dynamic memory and tracks it.
 * 
 * This function should not be called directly -- it will automatically be
 * invoked by calls to malloc() using the GNU __malloc_hook extension.
 * 
 * @param	size	The number of bytes to dynamically allocate.
 * @param	caller	Pointer to the calling instruction.
 * @return			A void pointer to the beginning of the allocated memory, or
 * 					NULL if no memory is available.
 */
void *__trackedMalloc(size_t size, const void *caller)
{
	void*		retVal;
	Dl_info		callerInfo;
	
	retVal	= MemTrack_untrackedMalloc(size);
  	
  	if (dladdr(caller, &callerInfo) != 0)
  	{
//		printf("__trackedMalloc called by %s to allocate %d bytes\n",
//	   		   callerInfo.dli_fname, size);

  		if (__isTrackedModule(callerInfo.dli_fname))
			__addTracking(callerInfo.dli_fname, retVal, size);
  	}
  	
	return (retVal);
}

/**
 * Private function that frees dynamic memory and stops tracking it.
 * 
 * This function should not be called directly -- it will automatically be
 * invoked by calls to free() using the GNU __free_hook extension.
 * 
 * @param	pointer	A pointer to the beginning of the dynamic memory allocation
 * 					that should be freed.
 * @param	caller	Pointer to the calling instruction.
 */
void __trackedFree(void *pointer, const void *caller)
{
	Dl_info		callerInfo;
	
	if (dladdr(caller, &callerInfo) != 0)
	{
//		printf("__trackedFree called by %s to free %x\n",
//			   callerInfo.dli_fname, pointer);
		
		if (__isTrackedModule(callerInfo.dli_fname))
			__removeTracking(pointer);
	}
	
	MemTrack_untrackedFree(pointer);
}

/**
 * Private function to add a pointer to the list of dynamic memory allocations
 * being tracked.
 *
 * Elements added by this function that have not been removed by
 * removeTracking() will be freed when freeAll() is called.
 *
 * @param	pointer	The pointer to the dynamic memory allocation that should
 * 					be tracked.
 * @param	size	The size of the dynamic memory allocation.
 */
void __addTracking(const char* moduleName, void* pointer, size_t size)
{
	MemTrack_MemListElem_t*	newElem;
	
	newElem				= (MemTrack_MemListElem_t*)MemTrack_untrackedMalloc(sizeof(MemTrack_MemListElem_t));
	newElem->moduleName	= moduleName;
	newElem->pointer	= pointer;
	newElem->size		= size;
	
	CRITICAL_SECTION(__allocListMutex,
	{
		newElem->next		= __allocList;
		__allocList			= newElem;
	});
}

/**
 * Private function to remove a pointer from the list of dynamic memory
 * allocations being tracked.
 * 
 * Elements removed by this function are no longer tracked, and will not be
 * freed when freeAll() is called.
 * 
 * @param	pointer	The pointer to the dynamic memory allocation that should
 * 					no longer be tracked.
 */
void __removeTracking(void* pointer)
{
	MemTrack_MemListElem_t	*prev	= NULL,
							*next;
	
	CRITICAL_SECTION(__allocListMutex,
	{
		for (MemTrack_MemListElem_t* cur = __allocList; cur != NULL; cur = next)
		{
			next	= cur->next;
			
			if (cur->pointer == pointer)
			{
				if (prev != NULL)
				{
					// Removing item in the middle of the list;
					// point "next" of previous element to element after this one.
					prev->next	= cur->next;	
				}
				
				else
				{
					// Removing the first item of the list;
					// point the head of list to the element after this one.
					__allocList	= cur->next;
				}
				
				MemTrack_untrackedFree(cur);
				
				break;
			}
			
			else
			{
				// Keep track of last item examined, in case the next item is the one
				// being removed.
				prev	= cur;
			}
		}
	});
}

/**
 * Private function that runs through the list of dynamic memory allocations
 * that are still being tracked for a particular module and frees each one.
 * 
 * This function does nothing if the list is empty.
 * All entries in the list for the spcecified module will removed after this
 * function is complete.
 * 
 * Once this function has completed, it is safe to assume that all memory
 * allocations for the module have been freed.
 * 
 * @param	moduleName	The name of the module whose dynamic allocations should
 * 						be freed.
 */
void __freeAll(const char* moduleName)
{
	MemTrack_MemListElem_t	*prev	= NULL,
							*next;
	
	CRITICAL_SECTION(__allocListMutex,
	{
		for (MemTrack_MemListElem_t* cur = __allocList; cur != NULL; cur = next)
		{
			next	= cur->next;
			
			if (strstr(cur->moduleName, moduleName) != NULL)
			{
//				printf("---Post-freeing %d bytes at %x for %s...\n",
//					   cur->size, cur->pointer, cur->moduleName);
			
				if (prev != NULL)
					prev->next	= next;
					
				else
					__allocList	= next;
				
				MemTrack_untrackedFree(cur->pointer);
				MemTrack_untrackedFree(cur);
			}
			
			else
			{
				// Keep track of last item examined, in case the next item is the one
				// being removed.
				prev	= cur;
			}
		}
	});
}

/**
 * Function that allocates dynamic memory without tracking it.
 * 
 * NOTE:	Use this function sparingly. Memory allocated by this function will
 * 			not be automatically freed when a module is unloaded!
 * 
 * @param	size	The number of bytes to dynamically allocate.
 * @return			A void pointer to the beginning of the allocated memory, or
 * 					NULL if no memory is available.
 */
void *MemTrack_untrackedMalloc(size_t size)
{
	void*				retVal;
	
	CRITICAL_SECTION(__hookMutex,
	{
		MemTrack_MallocHookFunc	origHook	= __malloc_hook;
		
		__malloc_hook	= __origMalloc;
	  	retVal			= malloc(size);
	  	__malloc_hook	= origHook;
	});
	
	return retVal;
}

/**
 * Function that frees dynamic memory without tracking it.
 * 
 * NOTE:	Do NOT use this function on memory that is already being tracked.
 *			Doing so will result in a double-free when tracking ends.
 * 
 * @param	pointer	A pointer to the beginning of the dynamic memory allocation
 * 					that should be freed.
 */
void MemTrack_untrackedFree(void *pointer)
{
	CRITICAL_SECTION(__hookMutex,
	{
		MemTrack_FreeHookFunc	origHook	= __free_hook;
		
		__free_hook	= __origFree;
	
		free(pointer);
	
		__free_hook	= origHook;
	});
}
