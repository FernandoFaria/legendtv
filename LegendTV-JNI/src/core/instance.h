/*****************************************************************************
 * instance.h: Main instance functions for VLC Java Bindings
 *****************************************************************************
 * Copyright (C) 2007 The LegendTV Project
 *
 * Authors: Guy Paddock <gap7472@rit.edu>
 *
 * $Id$
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

#ifndef JVLC_INSTANCE_H
#define JVLC_INSTANCE_H

#include <jni.h>

libvlc_instance_t* JVLC_getIntfInstance(JNIEnv *env, jobject intfInstance);
#endif
