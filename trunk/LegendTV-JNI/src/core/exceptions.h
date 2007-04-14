/*****************************************************************************
 * exceptions.h: Exception convenience functions for VLC Java Bindings
 *****************************************************************************
 * Copyright (C) 1998-2006 The VideoLAN team
 * Copyright (C) 2007 The LegendTV Project
 *
 * Authors: Guy Paddock <gap7472@rit.edu>
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
 
#ifndef JVLC_EXCEPTIONS_H
#define JVLC_EXCEPTIONS_H

#include <stdlib.h>		// needed for free()

#include <jni.h>
#include <vlc/libvlc.h>

#include "instance.h"

libvlc_exception_t*	JVLC_initException();
void JVLC_handleException(JNIEnv*, libvlc_exception_t*);

#define CHECK_EXCEPTION								\
		if (libvlc_exception_raised(exception))		\
			JVLC_handleException(env, exception);

#define CHECK_EXCEPTION_FREE	\
		CHECK_EXCEPTION;		\
		free(exception);

#endif
