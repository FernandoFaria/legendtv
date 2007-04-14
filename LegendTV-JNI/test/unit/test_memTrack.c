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
#define _GNU_SOURCE

#include <CUnit/CUnit.h>
#include <CUnit/Console.h>
#include <stdio.h>
#include <malloc.h>
#include "memTrack.h"

static const char*	MODULE_NAME			= "test_memTrack";
static const char*	OTHER_MODULE_NAME	= "other_module";

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

/*
 * External variable declarations.
 */
extern MemTrack_ModListElem_t*	__modNameList;
extern MemTrack_MemListElem_t*	__allocList;

/*
 * Function prototype declarations.
 */
void __addTrackedModule(const char* moduleName);
int __isTrackedModule(const char* moduleName);
void __removeTrackedModule(const char* moduleName);
void *__trackedMalloc(size_t size, const void *caller);
void __trackedFree(void *pointer, const void *caller);
void __addTracking(const char* moduleName, void* pointer, size_t size);
void __removeTracking(void* pointer);
void __freeAll(const char* moduleName);

void test_addRemModule()
{
	/* There should be no modules and nothing being tracked. */
	CU_ASSERT_EQUAL(__isTrackedModule(MODULE_NAME), 0);
	
	__addTrackedModule(MODULE_NAME);
	
	/* This module should now be tracked. */
	CU_ASSERT_PTR_NOT_NULL_FATAL(__modNameList);
	CU_ASSERT_PTR_NOT_NULL(strstr(__modNameList->name, MODULE_NAME));
	CU_ASSERT_PTR_NULL(__modNameList->next);
	
	/* isTracked should reflect this. */
	CU_ASSERT_EQUAL(__isTrackedModule(MODULE_NAME), 1);
	
	__removeTrackedModule(MODULE_NAME);
	
	/* We should have gone back to nothing being tracked. */
	CU_ASSERT_PTR_NULL(__modNameList);
	CU_ASSERT_EQUAL(__isTrackedModule(MODULE_NAME), 0);
}

void test_hookingUnhooking()
{
	/* There should be no hooks right now. */
	CU_ASSERT_PTR_NOT_EQUAL(__malloc_hook, __trackedMalloc);
	CU_ASSERT_PTR_NOT_EQUAL(__free_hook, __trackedFree);
	
	MemTrack_start(MODULE_NAME);
	
	/* We should be hooked right now. */
	CU_ASSERT_PTR_NOT_NULL(__modNameList);
	CU_ASSERT_PTR_EQUAL(__malloc_hook, __trackedMalloc);
	CU_ASSERT_PTR_EQUAL(__free_hook, __trackedFree);
	
	MemTrack_start(OTHER_MODULE_NAME);
	
	/* We should be still be hooked. */
	CU_ASSERT_PTR_NOT_NULL(__modNameList);
	CU_ASSERT_PTR_EQUAL(__malloc_hook, __trackedMalloc);
	CU_ASSERT_PTR_EQUAL(__free_hook, __trackedFree);
	
	MemTrack_stop(OTHER_MODULE_NAME, 0);
	
	/* Still... hooked. */
	CU_ASSERT_PTR_NOT_NULL(__modNameList);
	CU_ASSERT_PTR_EQUAL(__malloc_hook, __trackedMalloc);
	CU_ASSERT_PTR_EQUAL(__free_hook, __trackedFree);
	
	MemTrack_stop(MODULE_NAME, 0);
	
	/* No modules should be tracked. */
	CU_ASSERT_PTR_NULL(__modNameList);
	
	/* We should no longer have any hooks. */
	CU_ASSERT_PTR_NOT_EQUAL(__malloc_hook, __trackedMalloc);
	CU_ASSERT_PTR_NOT_EQUAL(__free_hook, __trackedFree);
}

void test_addRemTracking()
{
	int		size		= 1024;
	int*	testAlloc	= malloc(size);
	
	__addTracking(MODULE_NAME, testAlloc, size);
	
	/* There should be something in the allocation list. */
	CU_ASSERT_PTR_NOT_NULL_FATAL(__allocList);
	CU_ASSERT_PTR_NOT_NULL(strstr(__allocList->moduleName, MODULE_NAME));
	CU_ASSERT_PTR_EQUAL(__allocList->pointer, testAlloc);
	CU_ASSERT_EQUAL(__allocList->size, size);
	CU_ASSERT_PTR_NULL(__allocList->next);
	
	__removeTracking(testAlloc);
	
	/* There should be nothing in the allocation list. */
	CU_ASSERT_PTR_NULL(__allocList);
	
	free(testAlloc);
}

void test_untrackedAllocFree()
{
	int		size		= 1024;
	int*	testAlloc	= NULL;
	
	MemTrack_start(MODULE_NAME);
	
	testAlloc	= MemTrack_untrackedMalloc(size);
	
	/* The allocation should have succeeded and should not be tracked. */
	CU_ASSERT_PTR_NOT_NULL(testAlloc);
	CU_ASSERT_PTR_NULL(__allocList);
	
	MemTrack_untrackedFree(testAlloc);
	
	/* Still, nothing... */
	CU_ASSERT_PTR_NULL(__allocList);
	
	MemTrack_stop(MODULE_NAME, 0);
}

void test_trackedAllocFreeWithNoMod()
{
	int		size		= 1024;
	int*	testAlloc;
	
	testAlloc	= malloc(size);
	
	/* The allocation should have succeeded and should not be tracked. */
	CU_ASSERT_PTR_NOT_NULL(testAlloc);
	CU_ASSERT_PTR_NULL(__allocList);
	
	free(testAlloc);
	
	/* Still, nothing... */
	CU_ASSERT_PTR_NULL(__allocList);
	
	/* Ensure that we are not suddenly hooked. */
	CU_ASSERT_PTR_NOT_EQUAL(__malloc_hook, __trackedMalloc);
	CU_ASSERT_PTR_NOT_EQUAL(__free_hook, __trackedFree);
}

void test_trackedAllocFreeWithOtherMod()
{
	int		size		= 1024;
	int*	testAlloc;
	
	MemTrack_start(OTHER_MODULE_NAME);
	
	testAlloc	= malloc(size);
	
	/* The allocation should have succeeded and should not be tracked. */
	CU_ASSERT_PTR_NOT_NULL(testAlloc);
	CU_ASSERT_PTR_NULL(__allocList);
	
	free(testAlloc);
	
	/* Still, nothing... */
	CU_ASSERT_PTR_NULL(__allocList);
	
	MemTrack_stop(OTHER_MODULE_NAME, 0);
}

void test_trackedAllocFreeWithThisMod()
{
	int		size		= 1024;
	int*	testAlloc;
	
	MemTrack_start(MODULE_NAME);
	
	/* We should be hooked right now. */
	CU_ASSERT_PTR_NOT_NULL(__modNameList);
	CU_ASSERT_PTR_EQUAL(__malloc_hook, __trackedMalloc);
	CU_ASSERT_PTR_EQUAL(__free_hook, __trackedFree);
	
	testAlloc	= malloc(size);
	
	/* Ensure allocation succeeded. */
	CU_ASSERT_PTR_NOT_NULL(testAlloc);
	
	/* THIS allocation should have been tracked. */
	CU_ASSERT_PTR_NOT_NULL_FATAL(__allocList);
	CU_ASSERT_PTR_NOT_NULL(strstr(__allocList->moduleName, MODULE_NAME));
	CU_ASSERT_PTR_EQUAL(__allocList->pointer, testAlloc);
	CU_ASSERT_EQUAL(__allocList->size, size);
	CU_ASSERT_PTR_NULL(__allocList->next);
	
	free(testAlloc);
	
	/* The allocation should no longer be tracked. */
	CU_ASSERT_PTR_NULL(__allocList);
	
	MemTrack_stop(MODULE_NAME, 0);
}

void test_trackedAllocFreeWithThisModAndOther()
{
	int		size		= 1024;
	int		*testAlloc1,
			*testAlloc2;
	
	/* Start tracking two modules. */
	MemTrack_start(OTHER_MODULE_NAME);
	MemTrack_start(MODULE_NAME);
	
	/* We should be hooked right now. */
	CU_ASSERT_PTR_NOT_NULL(__modNameList);
	CU_ASSERT_PTR_EQUAL(__malloc_hook, __trackedMalloc);
	CU_ASSERT_PTR_EQUAL(__free_hook, __trackedFree);
	
	testAlloc1	= malloc(size);
	
	/* Allocation should have succeeded. */
	CU_ASSERT_PTR_NOT_NULL(testAlloc1);
	
	/* Allocation should have been tracked. */
	CU_ASSERT_PTR_NOT_NULL_FATAL(__allocList);
	CU_ASSERT_PTR_NOT_NULL(strstr(__allocList->moduleName, MODULE_NAME));
	CU_ASSERT_PTR_EQUAL(__allocList->pointer, testAlloc1);
	CU_ASSERT_EQUAL(__allocList->size, size);
	CU_ASSERT_PTR_NULL(__allocList->next);
	
	testAlloc2	= MemTrack_untrackedMalloc(size * 2);
	
	// Artificially track the second allocation as if malloc() had been caught
	__addTracking(OTHER_MODULE_NAME, testAlloc2, size * 2);
	
	/* This second allocation should have been tracked. */
	CU_ASSERT_PTR_NOT_NULL_FATAL(__allocList);
	CU_ASSERT_PTR_NOT_NULL(strstr(__allocList->moduleName, OTHER_MODULE_NAME));
	CU_ASSERT_PTR_EQUAL(__allocList->pointer, testAlloc2);
	CU_ASSERT_EQUAL(__allocList->size, size * 2);
	
	/* Our first allocation should still be tracked. */
	CU_ASSERT_PTR_NOT_NULL_FATAL(__allocList->next);
	CU_ASSERT_PTR_NOT_NULL(strstr(__allocList->next->moduleName, MODULE_NAME));
	CU_ASSERT_PTR_EQUAL(__allocList->next->pointer, testAlloc1);
	CU_ASSERT_EQUAL(__allocList->next->size, size);
	CU_ASSERT_PTR_NULL(__allocList->next->next);
	
	// Artificially un-track the second allocation as if free() had been caught
	__removeTracking(testAlloc2);
	
	/* Our first allocation should still be tracked. */
	CU_ASSERT_PTR_NOT_NULL_FATAL(__allocList);
	CU_ASSERT_PTR_NOT_NULL(strstr(__allocList->moduleName, MODULE_NAME));
	CU_ASSERT_PTR_EQUAL(__allocList->pointer, testAlloc1);
	CU_ASSERT_EQUAL(__allocList->size, size);
	CU_ASSERT_PTR_NULL(__allocList->next);
	
	MemTrack_untrackedFree(testAlloc2);
	free(testAlloc1);
	
	/* No allocations should be tracked any longer. */
	CU_ASSERT_PTR_NULL(__allocList);
	
	MemTrack_stop(MODULE_NAME, 0);
	MemTrack_stop(OTHER_MODULE_NAME, 0);
}

void test_freeAll()
{
	int		size		= 1024;
	int		*testAlloc1,
			*testAlloc2;
	
	/* Start tracking two modules. */
	MemTrack_start(OTHER_MODULE_NAME);
	MemTrack_start(MODULE_NAME);
	
	testAlloc1	= malloc(size);
	testAlloc2	= MemTrack_untrackedMalloc(size * 2);
	
	// Ensure first allocation was tracked; second wasn't
	CU_ASSERT_PTR_NOT_NULL_FATAL(__allocList);
	CU_ASSERT_PTR_NULL(__allocList->next);
	
	// Artificially track the second allocation as if malloc() had been caught
	__addTracking(OTHER_MODULE_NAME, testAlloc2, size * 2);
	
	// Ensure both allocations tracked
	CU_ASSERT_PTR_NOT_NULL_FATAL(__allocList);
	CU_ASSERT_PTR_NOT_NULL(__allocList->next);
	
	MemTrack_stop(MODULE_NAME, 1);
	
	// Ensure first allocation is no longer tracked
	CU_ASSERT_PTR_NOT_NULL_FATAL(__allocList);
	CU_ASSERT_PTR_NOT_NULL(strstr(__allocList->moduleName, OTHER_MODULE_NAME));
	CU_ASSERT_PTR_EQUAL(__allocList->pointer, testAlloc2);
	CU_ASSERT_EQUAL(__allocList->size, size * 2);
	CU_ASSERT_PTR_NULL(__allocList->next);
	
	MemTrack_stop(OTHER_MODULE_NAME, 1);
	
	// Ensure no allocations are being tracked
	CU_ASSERT_PTR_NULL(__allocList);
}

int setup()
{
	__modNameList	= NULL;
	__allocList		= NULL;
	__malloc_hook	= NULL;
	__free_hook		= NULL;
	
	return 0;
}

CU_TestInfo	tests[]	=
{
	{ "Adding / Removing Tracked Modules",			test_addRemModule							},
	{ "Adding / Removing Hooks",					test_hookingUnhooking						},
	{ "Adding / Removing Tracked Memory",			test_addRemTracking							},
	{ "Untracked Allocation / Free",				test_untrackedAllocFree						},
	{ "Tracked Allocation / Free (No Module)",		test_trackedAllocFreeWithNoMod				},
	{ "Tracked Allocation / Free (Other Module)",	test_trackedAllocFreeWithOtherMod			},
	{ "Tracked Allocation / Free (This Module)",	test_trackedAllocFreeWithThisMod			},
	{ "Tracked Allocation / Free (Two Modules)",	test_trackedAllocFreeWithThisModAndOther	},
	{ "Freeing Tracked Memory", 					test_freeAll								},
	CU_TEST_INFO_NULL
};

CU_SuiteInfo suites[] =
{
	{
		"MemTrack",
		setup,
		NULL,
		tests
	},
	CU_SUITE_INFO_NULL
};

int main()
{
	CU_pSuite	suite;
	
	if (CU_initialize_registry() == CUE_SUCCESS)
	{
		CU_ErrorCode	error	= CU_register_suites(suites);
		
		if (error == CUE_SUCCESS)
			CU_console_run_tests();
		
		CU_cleanup_registry();
	}
}
