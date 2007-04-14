/*****************************************************************************
 * exceptions.c: Exception convenience functions for VLC Java Bindings
 *****************************************************************************
 * Copyright (C) 1998-2006 The VideoLAN team
 * Copyright (C) 2007 The LegendTV Project
 *
 * Authors: Filippo Carone <filippo@carone.org>
 * 			Guy Paddock <gap7472@rit.edu>
 *
 *
 * $Id $
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111, USA.
 *****************************************************************************/

#include <jni.h>

#include "exceptions.h"

/**
 * Constant for the fully-qualified class name of the VLCException on
 * the Java side.
 */
static const char* VLC_EXCEPTION_CLASS_NAME	= "org/legendtv/core/VLCException";

/**
 * Initializes a new exception structure instance.
 * 
 * @return	A pointer to a new libvlc_exception_t instance, for use with many
 * 			of the functions provided throughout the VLC interface.
 */
libvlc_exception_t*	JVLC_initException()
{
	libvlc_exception_t	*retVal = (libvlc_exception_t *)malloc(sizeof(libvlc_exception_t));
	
	libvlc_exception_init(retVal);
	
	return retVal;
}

/**
 * Handles an exception that has been raised by something in the VLC codebase.
 * This will convert the VLC exception into a correspondng Java VLCException
 * instance and throw it.
 * 
 * @param	env			Pointer to the current JNI environment.
 * @param	exception	Pointer to the libvlc_exception_t instance that is to
 * 						be thrown as a Java VLCException.
 */
void JVLC_handleException(JNIEnv* env, libvlc_exception_t* exception)
{
	jclass newExcCls;

	// Obtain Java exception class
	newExcCls	= (*env)->FindClass(env, VLC_EXCEPTION_CLASS_NAME);
	
	// If unable to find the new exception class, give up.
	if (newExcCls == 0)
		return;
	
	// Extract message from exception and insert it into the new
	// Java VLCException incarnation
	(*env)->ThrowNew(env, newExcCls, libvlc_exception_get_message(exception));
}
