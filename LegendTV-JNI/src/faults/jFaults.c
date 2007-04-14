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
 * jFaults.c
 * =========
 * 
 * Implementation of fatal signal interception.
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
#define _GNU_SOURCE

#include <signal.h>
#include <string.h>
#include <pthread.h>

#include "memTrack.h"
#include "jFaults.h"
#include "critical.h"

/**
 * Array of signals that JFault signal interception will handle.
 */
static const int HANDLED_SIGNALS[]	=
{
	SIGBUS,
	SIGSEGV,
	SIGILL,
	SIGFPE
};

/**
 * Number of signals in HANDLED_SIGNALS above.
 */
static const int NUM_HANDLED_SIGNALS	= sizeof(HANDLED_SIGNALS) / sizeof(int);

/**
 * Constant for the class name of the error to throw when a fatal signal is caught.
 */
static const char* EXCEPTION_CLASS_NAME	= "com/googlecode/legendtv/faults/FatalSignalError";

/**
 * Constant for the signature of the constructor for the unhandled signal exception.
 */
static const char* EXCEPTION_CONSTR_SIG	= "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V";

/**
 * Pointer to information about the current fault scope.
 */
__thread JFault_HandlerScope_t*	__curScope			= NULL;

/**
 * Signal handlers that were active before fault tolerance.
 */
sighandler_t*					oldHandlers			= NULL;

/**
 * Mutex for __waitingFault.
 */
pthread_mutex_t					__waitingFaultMutex	= PTHREAD_MUTEX_INITIALIZER;
	
/**
 * Information about the current waiting fault, if any.
 */
struct
{
	int	present;
	int	sigNum;
}								__waitingFault		= {0, 0};

// Prototype declarations
void __JFault_sigHandler(int signum);
void __JFault_queueFault(int sigNum);
int __JFault_throwException(JNIEnv* env, const char* sigName, const char* funcName, const char* fileName);

/**
 * Initializer for JFault fault tolerance.
 * This function sets up the hooks necessary to intercept faults.
 */
void JFault_init()
{
	oldHandlers	= (sighandler_t*)MemTrack_untrackedMalloc(sizeof(sighandler_t) * NUM_HANDLED_SIGNALS);
	
	for (int i = 0; i < NUM_HANDLED_SIGNALS; ++i)
	{
		oldHandlers[i]	= signal(HANDLED_SIGNALS[i], __JFault_sigHandler);
	}
}

/**
 * Destructor for JFault fault tolerance.
 * This function removes the hooks for fault tolerance.
 * 
 * Once this function has been called, fault tolerance is no longer available.
 * Thus, if any of the fatal signals normally handled by JFault are received,
 * the default signal handler will be invoked, normally resulting in a
 * program crash.
 */
void JFault_finalize()
{
	for (int i = 0; i < NUM_HANDLED_SIGNALS; ++i)
	{
		signal(HANDLED_SIGNALS[i], oldHandlers[i]);
	}
	
	MemTrack_untrackedFree(oldHandlers);
}

/**
 * Function called during JFAULT_TOLERANT_SCOPE to setup a fault tolerant
 * scope / section.
 */
void JFault_setupScope()
{
	JFault_HandlerScope_t*	newScope;
	
	newScope		= (JFault_HandlerScope_t*)MemTrack_untrackedMalloc(sizeof(JFault_HandlerScope_t));
	newScope->prev	= __curScope;
	__curScope		= newScope;
}

/**
 * This is the actual function that is called to handle a fault.
 * Flow control will be routed back to the position in the faulting function
 * that was saved by the JFAULT_TOLERANT_SCOPE macro, then trigger an exception.
 * 
 * @param signum	The signal number for the signal being handled.
 */
void __JFault_sigHandler(int sigNum)
{
	// If current thread has a fault scope, use it
	if (__curScope != NULL)
		longjmp(__curScope->savedEnv, sigNum);

	// Otherwise, queue-up fault and bail out of thread		
	else
	{
		__JFault_queueFault(sigNum);
		
		// There's really nowhere for control to go...
		pthread_exit(NULL);
	}
}

/**
 * This function is called to queue up a fault for handling in the next
 * activated fault scope.
 * 
 * @param signum	The signal number for the signal being queued.
 */
void __JFault_queueFault(int sigNum)
{
	CRITICAL_SECTION(__waitingFaultMutex,
	{
		// We only care about the first fault, so see if one exists
		if (!__waitingFault.present)
		{
			__waitingFault.present	= 1;
			__waitingFault.sigNum	= sigNum;
		}
	});
}

/**
 * Function called during JFAULT_TOLERANT_SCOPE to handle a queued faults.
 * 
 * A queued fault is a fault that arises while execution is outside of a fault
 * scope (usually in a separate thread). The first of such faults is queued up
 * until this function is called, usually upon entry to the first activated
 * fault scope following the fault.
 */
void JFault_handleQueuedFault()
{
	// Lock access to the waiting fault info
	pthread_mutex_lock(&__waitingFaultMutex);
	
	if ((__waitingFault.present) && (__curScope != NULL))
	{
		// Extract signal now, so we can release the mutex
		int	sigNum	= __waitingFault.sigNum;
		
		__waitingFault.present	= 0;
		__waitingFault.sigNum	= 0;
		
		// We do this now because the signal handler is likely
		// not to return normally.
		pthread_mutex_unlock(&__waitingFaultMutex);
		
		// Handle the fault as if it just occurred
		__JFault_sigHandler(sigNum);
	}
	
	pthread_mutex_unlock(&__waitingFaultMutex);
}

/**
 * Function called during JFAULT_TOLERANT_SCOPE to destroy a fault tolerant
 * scope / section.
 */
void JFault_destroyScope()
{
	if (__curScope != NULL)
	{
		JFault_HandlerScope_t*	oldScope;
		
		oldScope	= __curScope;
		__curScope	= __curScope->prev;
		
		MemTrack_untrackedFree(oldScope);
	}
}

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
						   const char* fileName)
{
	char*	sigName	= strsignal(sigNum);
	
	// Free the resources used by our current fault scope
	JFault_destroyScope();
	
	/*
	 * Try to throw an exception for the unhandled signal, but fall back
	 * to default signal handling if this fails.
	 * 
	 * SIGUSR1 is a signal sent from an inner call to indicate that an
	 * exception was thrown for an unhandled signal and we should abort
	 * our call; we don't throw an exception for this signal.
	 */
	if ((sigNum != SIGUSR1) &&
		(!__JFault_throwException(env, sigName, funcName, fileName)))
	{
		// Stop signal handling; there's no point if we can't throw exceptions.
		JFault_finalize();
		
		// Re-raise the signal for default handler to intercept.
		raise(sigNum);
	}
	
	else
	{
		if (__curScope != NULL)
		{
			// Abort all other fault tolerant code above us on the call stack.
			// This frees dynamic resources used to maintain fault scopes,
			// as well as sends control in the current thread back to a safe
			// point.
			longjmp(__curScope->savedEnv, SIGUSR1);
		}
	}
}

/**
 * Function called by throwOrReRaise() to actually attempt to throw an
 * exception for the fatal signal.
 * 
 * This function will attempt to create a populated FatalSignalError instance
 * and throw it.
 * 
 * @param	env			The JNI environment for the active thread.
 * @param	sigName		The string name of the signal that was handled.
 * @param	funcName	The function in which the fault occurred.
 * @param	fileName	The file in which the function lives.
 * @return				True (non-zero) if an exception instance was
 * 						successfully created and thrown, false (0) otherwise.
 */
int __JFault_throwException(JNIEnv* env, const char* sigName, const char* funcName, const char* fileName)
{
	int		retVal		= 0;
	jclass	excepClass;
	
	// Check for a pending exception before we attempt to throw another one.
	if ((*env)->ExceptionOccurred(env))
	{
		// Warn the user of the originally-pending exception
		printf("WARNING: Pending exception superceded by fault tolerance; backtrace was:\n");
		(*env)->ExceptionDescribe(env);
		
		// Clear the original exception, to make way for the one we are about
		// to throw.
		(*env)->ExceptionClear(env);
	}
	
	excepClass	= (*env)->FindClass(env, EXCEPTION_CLASS_NAME);
	
	if (excepClass != NULL)
	{
		jstring		jSigName	= (*env)->NewStringUTF(env, sigName),
					jFuncName	= (*env)->NewStringUTF(env, funcName),
					jFileName	= (*env)->NewStringUTF(env, fileName);
		jmethodID	excepConstr	= (*env)->GetMethodID(env, excepClass,
													  "<init>", EXCEPTION_CONSTR_SIG);
		
		if (excepConstr != NULL)
		{
			jobject	exception	= (*env)->NewObject(env, excepClass,
													excepConstr, jSigName,
													jFuncName, jFileName);
		
			(*env)->Throw(env, (jthrowable)exception);
		}
		
		retVal	= 1;
	}
	
	return (retVal);
}
