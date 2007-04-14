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
 * jFaults.h
 * ==========
 * 
 * Header for fatal signal interception.
 * 
 * This is the core of JNI fault tolerance, providing try..catch-like
 * encapsulation for native code at the Java -> C boundary. If a
 * fatal signal occurs while encapsulated code is executing, this component
 * will automatically intercept the signal and throw a corresponding Java
 * exception, effectively redirecting handling of the signal to Java code.
 * 
 * It is assumed that the Java code will take the necessary steps of
 * unloading the faulting native code module and freeing the dynamic memory
 * allocated by the module, as tracked by MemTrack.
 */

#ifndef JFAULT_H
#define JFAULT_H

#include <jni.h>
#include <setjmp.h>

/**
 * Type definition for signal handlers.
 */
typedef void (*sighandler_t)(int);

/**
 * Struct for representing a fault tolerant scope.
 */
typedef struct JFault_HandlerScope_s
{
	/**
	 * Saved environment for recovery after a fault.
	 */
	jmp_buf							savedEnv;
	
	/**
	 * Previous fault handler scope, if any.
	 */
	struct JFault_HandlerScope_s*	prev;
} JFault_HandlerScope_t;

/**
 * Pointer to information about the current fault scope.
 */
extern __thread JFault_HandlerScope_t* __curScope;

/**
 * This macro is used to mark a code section that needs fault tolerance.
 * 
 * This will automatically register necessary signal handlers and setup jump
 * points for handling faults.
 * 
 * @param	JNIENV			The local JNI interface pointer.
 * @param	SCOPE_BODY		The body of the scope -- the block of code that this
 * 							macro will wrap with fault tolerance.
 * @param	DEFAULT_RETURN	The default value to return if a fault is handled
 * 							during the block.
 */
#define JFAULT_TOLERANT_SCOPE_WITH_RETURN(JNIENV, SCOPE_BODY, DEFAULT_RETURN)	\
	int	__jmpSignal;															\
																				\
	JFault_setupScope();														\
																				\
	__jmpSignal	= setjmp(__curScope->savedEnv);									\
																				\
	if (!__jmpSignal)															\
	{																			\
		JFault_handleQueuedFault();												\
		SCOPE_BODY																\
		JFault_handleQueuedFault();												\
		JFault_destroyScope();													\
	}																			\
																				\
	else																		\
	{																			\
		JFault_throwOrReRaise(JNIENV, __jmpSignal,								\
							  __FUNCTION__, __FILE__);							\
		return DEFAULT_RETURN;													\
	}

#define JFAULT_TOLERANT_SCOPE(JNIENV, SCOPE_BODY)	\
		JFAULT_TOLERANT_SCOPE_WITH_RETURN(JNIENV, SCOPE_BODY,);

/**
 * Initializer for JFault fault tolerance.
 * This function sets up the hooks necessary to intercept faults.
 */
void JFault_init();

/**
 * Destructor for JFault fault tolerance.
 * This function removes the hooks for fault tolerance.
 * 
 * Once this function has been called, fault tolerance is no longer available.
 * Thus, if any of the fatal signals normally handled by JFault are received,
 * the default signal handler will be invoked, normally resulting in a
 * program crash.
 */
void JFault_finalize();

/**
 * Function called during JFAULT_TOLERANT_SCOPE to setup a fault tolerant
 * scope / section.
 */
void JFault_setupScope();

/**
 * Function called during JFAULT_TOLERANT_SCOPE to handle any waiting faults.
 * 
 * A waiting fault is a fault that arises outside of a fault scope. The first of
 * such faults is queued up until this function is called, usually upon entry to
 * the first fault scope following the fault.
 */
void JFault_handleQueuedFault();

/**
 * Function called during JFAULT_TOLERANT_SCOPE to destroy a fault tolerant
 * scope / section.
 */
void JFault_destroyScope();

/**
 * Function called if a fatal signal has been handled.
 * 
 * Once this function has been invoked, it should be assumed that normal flow
 * control has been subverted and that the current module will soon be
 * unloaded.
 * 
 * @param	env			The JNI environment for the active thread.
 * @param	sigNum		The number of the signal being handled.
 * @param	funcName	The function in which the fault occurred.
 * @param	fileName	The file in which the function lives.
 */
void JFault_throwOrReRaise(JNIEnv* env, int sigNum, const char* funcName,
						   const char* fileName);

#endif
