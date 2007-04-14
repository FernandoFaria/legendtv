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

package org.legendtv.faults;

/**
 * Error thrown from native code by JNI fault tolerance when a fatal signal
 * (SIGBUS, SIGSEGV, SIGFPE, or SIGILL) has been intercepted from the
 * operating system.
 * 
 * This error should be allowed to propagate up the stack until the responsible
 * thread dies -- it is too severe to attempt to handle. This is effectively
 * the equivalent of stopping a crash and returning control to sane code,
 * so the only acceptable way to handle it is to remove the faulting module
 * from memory.
 * 
 * This has the advantage that other threads will continue to execute normally.
 * 
 * @author Guy Paddock
 */
public class FatalSignalError extends Error
{
	/**
	 * Constructor for FatalSignalError.
	 * 
	 * @param sigName	The name of the signal that was intercepted.
	 * @param funcName	The name of the function that was executing at the time
	 * 					the signal occurred.
	 * @param fileName	The name of the source file that contains the function
	 * 					named by funcName.
	 */
	public FatalSignalError(String sigName, String funcName, String fileName)
	{
		super(String.format("'%s' while in '%s()' of '%s'", sigName, funcName, fileName));
	}
}
