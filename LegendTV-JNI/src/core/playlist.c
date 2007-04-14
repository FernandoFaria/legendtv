/*****************************************************************************
 * playlist.c: JNI native playlist functions for VLC Java Bindings
 *****************************************************************************
 * Copyright (C) 1998-2006 The VideoLAN team
 * Copyright (C) 2007 The LegendTV Project
 *
 * Authors: Filippo Carone <filippo@carone.org>
 * 			Guy Paddock <gap7472@rit.edu>
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
#include <vlc/libvlc.h>
#include <stdio.h>

#ifdef WIN32
	#include <windows.h>
	#undef usleep
	#define usleep(var) Sleep(var/1000)
#else
	#include <unistd.h>
#endif

#include "../faults/jFaults.h"	// JNI fault tolerance

#include "generated/Playlist.h"	// JVLC internal imports, generated by gcjh
#include "instance.h"
#include "exceptions.h"

JNIEXPORT jint JNICALL Java_com_googlecode_legendtv_core_Playlist_add(JNIEnv *env, jobject _this,
														   jstring uri, jstring name,
														   jobjectArray options)
{
	//JFAULT_TOLERANT_SCOPE_WITH_RETURN(env,
	//{
		libvlc_exception_t*	exception		= JVLC_initException();
		libvlc_instance_t*	instance		= JVLC_getIntfInstance(env, _this);
		int					i_options		= 0;
	    const char**		ppsz_options	= NULL;
	    const char*			psz_uri			= (*env)->GetStringUTFChars(env, uri, 0);
	    const char*			psz_name		= (*env)->GetStringUTFChars(env, name, 0);
	    int					res;
	    
	    if (options != NULL)
	    {
	    	i_options		= (int)(*env)->GetArrayLength(env, (jarray)options) + 1;
	    	ppsz_options	= (const char**)malloc(i_options * sizeof(char*));
	    	
	    	sprintf((char*)ppsz_options[0], "%s", "jvlc");
	
	        for (int i = 0; i < i_options - 1; ++i)
	        {
	        	jstring	curElem;

	        	curElem				= (jstring)(*env)->GetObjectArrayElement(env, options, i);
	            ppsz_options[i + 1]	= (*env)->GetStringUTFChars(env, curElem, 0);
	        }
	        
	        res	= libvlc_playlist_add_extended(instance, psz_uri, psz_name,
	        								   i_options, ppsz_options, exception);
	
	        CHECK_EXCEPTION_FREE;
	    }
	    
	    else
	    {
	        res	= libvlc_playlist_add(instance, psz_uri, psz_name, exception);
	        
	        CHECK_EXCEPTION_FREE;
	    }
	    
	    if (psz_uri != NULL)
	    	(*env)->ReleaseStringUTFChars(env, uri, psz_uri);
	    	
	    if (psz_name != NULL)
	    	(*env)->ReleaseStringUTFChars(env, name, psz_name);
	    	
	    return res;
	//}, 0);
}

JNIEXPORT void JNICALL Java_com_googlecode_legendtv_core_Playlist_remove(JNIEnv *env, jobject _this, jint itemID)
{
    JFAULT_TOLERANT_SCOPE(env,
	{
	    libvlc_exception_t*	exception	= JVLC_initException();
		libvlc_instance_t*	instance	= JVLC_getIntfInstance(env, _this);
	
	    libvlc_playlist_delete_item(instance, itemID, exception);
	
	    CHECK_EXCEPTION_FREE;
	});
}

JNIEXPORT void JNICALL Java_com_googlecode_legendtv_core_Playlist_removeAll(JNIEnv *env, jobject _this)
{
	JFAULT_TOLERANT_SCOPE(env,
	{
	    libvlc_exception_t*	exception	= JVLC_initException();
		libvlc_instance_t*	instance	= JVLC_getIntfInstance(env, _this);
	
	    libvlc_playlist_clear(instance, exception);
	
	    CHECK_EXCEPTION_FREE;
	});
}

JNIEXPORT jint JNICALL Java_com_googlecode_legendtv_core_Playlist_size(JNIEnv *env, jobject _this)
{
	JFAULT_TOLERANT_SCOPE_WITH_RETURN(env,
	{
		libvlc_exception_t*	exception	= JVLC_initException();
		libvlc_instance_t*	instance	= JVLC_getIntfInstance(env, _this);
		int					res;
		
		res	= libvlc_playlist_items_count(instance, exception);
		
		CHECK_EXCEPTION_FREE;
		
		return res;
	}, 0);
}

JNIEXPORT void JNICALL Java_com_googlecode_legendtv_core_Playlist_play(JNIEnv *env, jobject _this, jint id, jobjectArray options)
{
	JFAULT_TOLERANT_SCOPE(env,
	{
		libvlc_exception_t*	exception		= JVLC_initException();
		libvlc_instance_t*	instance		= JVLC_getIntfInstance(env, _this);
	    int					i_options		= 0;
	    const char**		ppsz_options	= NULL;
	
	    if (options != NULL)
	    {
	    	i_options		= (int)(*env)->GetArrayLength(env, (jarray)options);
	    	ppsz_options	= (const char**)malloc(i_options * sizeof(char*));
	    	
	    	for (int i = 0; i < i_options - 1; ++i)
	    	{
	    		jstring	curElem;
	    		
	    		curElem			= (jstring)(*env)->GetObjectArrayElement(env, options, i);
	    		ppsz_options[i]	= (*env)->GetStringUTFChars(env, curElem, 0);
	    	}
	    }
	
	    libvlc_playlist_play(instance, id, i_options, (char**)ppsz_options,
	    					 exception);
	    
	    CHECK_EXCEPTION;
	    
	    while (!libvlc_playlist_isplaying(instance, exception))
	    {
	        usleep(100);
	    }
	
	    CHECK_EXCEPTION_FREE;
	});
}


JNIEXPORT void JNICALL Java_com_googlecode_legendtv_core_Playlist_togglePause(JNIEnv *env, jobject _this)
{
	JFAULT_TOLERANT_SCOPE(env,
	{
		libvlc_exception_t*	exception	= JVLC_initException();
		libvlc_instance_t*	instance	= JVLC_getIntfInstance(env, _this);
		
		libvlc_playlist_pause(instance, exception);
		
		CHECK_EXCEPTION_FREE;
	});
}

JNIEXPORT void JNICALL Java_com_googlecode_legendtv_core_Playlist_nextItem(JNIEnv *env, jobject _this)
{
    JFAULT_TOLERANT_SCOPE(env,
	{
		libvlc_exception_t*	exception	= JVLC_initException();
		libvlc_instance_t*	instance	= JVLC_getIntfInstance(env, _this);
		
	    libvlc_playlist_next(instance, exception);
	
	    CHECK_EXCEPTION_FREE;
	});
}

JNIEXPORT void JNICALL Java_com_googlecode_legendtv_core_Playlist_previousItem(JNIEnv *env, jobject _this)
{
	JFAULT_TOLERANT_SCOPE(env,
	{
		libvlc_exception_t*	exception	= JVLC_initException();
		libvlc_instance_t*	instance	= JVLC_getIntfInstance(env, _this);
	    
	    libvlc_playlist_prev(instance, exception);
	
	    CHECK_EXCEPTION_FREE;
	});
}

JNIEXPORT void JNICALL Java_com_googlecode_legendtv_core_Playlist_stop(JNIEnv *env, jobject _this)
{
    JFAULT_TOLERANT_SCOPE(env,
	{
		libvlc_exception_t*	exception	= JVLC_initException();
		libvlc_instance_t*	instance	= JVLC_getIntfInstance(env, _this);
		
	    libvlc_playlist_stop(instance, exception);
	    
	    while (libvlc_playlist_isplaying(instance, exception))
	    {
	        usleep(100);
	    }
	
	    CHECK_EXCEPTION_FREE;
	});
}

JNIEXPORT jboolean JNICALL Java_com_googlecode_legendtv_core_Playlist_isPlaying(JNIEnv *env, jobject _this)
{
	JFAULT_TOLERANT_SCOPE_WITH_RETURN(env,
	{
		libvlc_exception_t*	exception	= JVLC_initException();
		libvlc_instance_t*	instance	= JVLC_getIntfInstance(env, _this);
		jboolean			res;
		
		res	= libvlc_playlist_isplaying(instance, exception);
		
		CHECK_EXCEPTION_FREE;
		
		return res;
	}, 0);
}

JNIEXPORT jboolean JNICALL Java_com_googlecode_legendtv_core_Playlist_isLooping(JNIEnv *env, jobject _this)
{
	// FIXME: No way to find this out?
	return 0;
}

JNIEXPORT void JNICALL Java_com_googlecode_legendtv_core_Playlist_setLooping(JNIEnv *env, jobject _this, jboolean loop)
{
	JFAULT_TOLERANT_SCOPE(env,
	{
		libvlc_exception_t*	exception	= JVLC_initException();
		libvlc_instance_t*	instance	= JVLC_getIntfInstance(env, _this);
		
		libvlc_playlist_loop(instance, loop, exception);
		
		CHECK_EXCEPTION_FREE;
	});
}   
