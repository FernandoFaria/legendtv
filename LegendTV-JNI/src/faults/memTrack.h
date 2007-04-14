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
 * memTrack.h
 * ==========
 * 
 * Header for dynamic memory allocation tracker.
 * 
 * This is the second component of JNI fault tolerance and is needed so that
 * dynamic memory of a faulting module or modules can be unloaded when a fault
 * occurs.
 */
 
#ifndef MEMTRACK_H
#define MEMTRACK_H

#include <stddef.h>

/**
 * Function that initializes dynamic memory tracking for a particular module.
 * This function will install the appropriate hooks for malloc() and free().
 * 
 * @param	moduleName	The name of the module for which dynamic memory
 * 						tracking should start.
 */
void MemTrack_start(const char* moduleName);

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
void MemTrack_stop(const char* moduleName, int shouldFree);

/**
 * Function that allocates dynamic memory without tracking it.
 * 
 * @param	size	The number of bytes to dynamically allocate.
 * @return			A void pointer to the beginning of the allocated memory, or
 * 					NULL if no memory is available.
 */
void *MemTrack_untrackedMalloc(size_t size);

/**
 * Function that frees dynamic memory without tracking it.
 * 
 * @param	pointer	A pointer to the beginning of the dynamic memory allocation
 * 					that should be freed.
 */
void MemTrack_untrackedFree(void *pointer);

#endif
