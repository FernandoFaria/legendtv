/*****************************************************************************
 * vlm.c: JNI native VLM functions for VLC Java Bindings
 *****************************************************************************
 * Copyright (C) 1998-2006 The VideoLAN team
 * Copyright (C) 2007 The LegendTV Project
 *
 * Authors: Philippe Morin <phmorin@free.fr>
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
#include <vlc/libvlc.h>

#include "../faults/jFaults.h"	// JNI fault tolerance

#include "generated/VLM.h"		// JVLC internal imports, generated by gcjh
#include "exceptions.h"

JNIEXPORT void JNICALL Java_com_googlecode_legendtv_core_VLM_addBroadcast(JNIEnv *env, jobject _this, jstring name,
															   jstring inputmrl, jstring outputmrl,
															   jobjectArray options, jboolean enable, jboolean loop)
{
	JFAULT_TOLERANT_SCOPE(env,
	{
		int					i_options			= 0;
	    const char**		ppsz_options		= NULL;
		const char*			psz_name			= (*env)->GetStringUTFChars(env, name, 0);
		const char*			psz_inputmrl		= (*env)->GetStringUTFChars(env, inputmrl, 0);
		const char*			psz_outputmrl		= (*env)->GetStringUTFChars(env, outputmrl, 0);
		libvlc_exception_t*	exception			= JVLC_initException();
		libvlc_instance_t*	instance			= JVLC_getIntfInstance(env, _this);
	
	    if (options != NULL)
	    {
	    	i_options		= (int)(*env)->GetArrayLength(env, (jarray)options);
	        ppsz_options	= (const char**)malloc(i_options * sizeof(char*));
	        
	        for (int i = 0; i < i_options - 1; ++i)
	        {
	        	jstring	curElem;
	        	
	        	curElem			= (jstring)(*env)->GetObjectArrayElement(env, options, i);
	            ppsz_options[i] = (*env)->GetStringUTFChars(env, curElem, 0);
	        }
	    }
	
	    libvlc_vlm_add_broadcast(instance, (char*)psz_name, (char*)psz_inputmrl,
	    						 (char*)psz_outputmrl, i_options,
	    						 (char**)ppsz_options, enable, loop, exception);
	
	    CHECK_EXCEPTION_FREE;
	    
	    if (psz_name != NULL)
	        (*env)->ReleaseStringUTFChars(env, name, psz_name);
	        
	    if (psz_inputmrl != NULL)
	    	(*env)->ReleaseStringUTFChars(env, inputmrl, psz_inputmrl);
	    	
	    if (psz_outputmrl != NULL)
	        (*env)->ReleaseStringUTFChars(env, outputmrl, psz_outputmrl);
	});
}

JNIEXPORT void JNICALL Java_com_googlecode_legendtv_core_VLM_deleteMedia(JNIEnv *env, jobject _this, jstring name) 
{
	JFAULT_TOLERANT_SCOPE(env,
	{
	    libvlc_exception_t*	exception	= JVLC_initException();
		libvlc_instance_t*	instance	= JVLC_getIntfInstance(env, _this);
		const char*			psz_name	= (*env)->GetStringUTFChars(env, name, 0);
	
	    libvlc_vlm_del_media(instance, (char*)psz_name, exception);
	    CHECK_EXCEPTION_FREE;
	
	    if (psz_name != NULL)
	    	(*env)->ReleaseStringUTFChars(env, name, psz_name);
	});
}

JNIEXPORT void JNICALL Java_com_googlecode_legendtv_core_VLM_setEnabled(JNIEnv *env, jobject _this, jstring name,
															 jboolean newStatus) 
{
	JFAULT_TOLERANT_SCOPE(env,
	{
	    libvlc_exception_t*	exception	= JVLC_initException();
		libvlc_instance_t*	instance	= JVLC_getIntfInstance(env, _this);
	    const char*			psz_name	= (*env)->GetStringUTFChars(env, name, 0);
	
	    libvlc_vlm_set_enabled(instance, (char*)psz_name, newStatus, exception);
	    
		CHECK_EXCEPTION_FREE;
	
		if (psz_name != NULL)
	        (*env)->ReleaseStringUTFChars(env, name, psz_name);
	});
}

JNIEXPORT void JNICALL Java_com_googlecode_legendtv_core_VLM_setOutput(JNIEnv *env, jobject _this, jstring name, jstring mrl) 
{
	JFAULT_TOLERANT_SCOPE(env,
	{
		libvlc_exception_t*	exception	= JVLC_initException();
		libvlc_instance_t*	instance	= JVLC_getIntfInstance(env, _this);
	    const char*			psz_name	= (*env)->GetStringUTFChars(env, name, 0);
		const char*			psz_mrl		= (*env)->GetStringUTFChars(env, mrl, 0);
	
	    libvlc_vlm_set_output(instance, (char*)psz_name, (char*)psz_mrl, exception);
	    
	    CHECK_EXCEPTION_FREE;
	
		if (psz_name != NULL)
			(*env)->ReleaseStringUTFChars(env, name, psz_name);    
	
		if (psz_mrl != NULL)
			(*env)->ReleaseStringUTFChars(env, mrl, psz_mrl);
	});
}

JNIEXPORT void JNICALL Java_com_googlecode_legendtv_core_VLM_setInput(JNIEnv *env, jobject _this, jstring name, jstring mrl) 
{
	JFAULT_TOLERANT_SCOPE(env,
	{
		libvlc_exception_t*	exception	= JVLC_initException();
		libvlc_instance_t*	instance	= JVLC_getIntfInstance(env, _this);
	    const char*			psz_name	= (*env)->GetStringUTFChars(env, name, 0);
		const char*			psz_mrl		= (*env)->GetStringUTFChars(env, mrl, 0);    
	
	    libvlc_vlm_set_input(instance, (char*)psz_name, (char*)psz_mrl, exception);
	    
	    CHECK_EXCEPTION_FREE;
	
	    if (psz_name != NULL)
	        (*env)->ReleaseStringUTFChars(env, name, psz_name);
	    
	    if (psz_mrl != NULL)
			(*env)->ReleaseStringUTFChars(env, mrl, psz_mrl);
	});
}

JNIEXPORT void JNICALL Java_com_googlecode_legendtv_core_VLM_setLooping(JNIEnv *env, jobject _this, jstring name,
															 jboolean newStatus) 
{
	JFAULT_TOLERANT_SCOPE(env,
	{
		libvlc_exception_t*	exception	= JVLC_initException();
		libvlc_instance_t*	instance	= JVLC_getIntfInstance(env, _this);
	    const char*			psz_name	= (*env)->GetStringUTFChars(env, name, 0);
	
	    libvlc_vlm_set_loop(instance, (char*)psz_name, newStatus, exception);
	    
	    CHECK_EXCEPTION_FREE;
	
		if (psz_name != NULL)
			(*env)->ReleaseStringUTFChars(env, name, psz_name);
	});
}

JNIEXPORT void JNICALL Java_com_googlecode_legendtv_core_VLM_changeMedia(JNIEnv *env, jobject _this, jstring name,
															  jstring inputmrl, jstring outputmrl,
															  jobjectArray options, jboolean enablenewbroadcast,
															  jboolean broadcast) 
{
	JFAULT_TOLERANT_SCOPE(env,
	{
		int					i_options			= 0;
	    const char**		ppsz_options		= NULL;
	    const char*			psz_name			= (*env)->GetStringUTFChars(env, name, 0);
	    const char*			psz_inputmrl		= (*env)->GetStringUTFChars(env, inputmrl, 0);    
	    const char*			psz_outputmrl		= (*env)->GetStringUTFChars(env, outputmrl, 0);
		libvlc_exception_t*	exception			= JVLC_initException();
		libvlc_instance_t*	instance			= JVLC_getIntfInstance(env, _this);
	    
	    if (options != NULL)
	    {
	    	i_options		= (int)(*env)->GetArrayLength(env, (jarray)options);
	        ppsz_options	= (const char**)malloc(i_options * sizeof(char*));
	        
	        for (int i = 0; i < i_options - 1; ++i)
	        {
	        	jstring	curElem;
	        	
	        	curElem			= (jstring)(*env)->GetObjectArrayElement(env, options, i);
	            ppsz_options[i] = (*env)->GetStringUTFChars(env, curElem, 0);
	        }
	    }
	
	    libvlc_vlm_change_media(instance, (char*)psz_name, (char*)psz_inputmrl, (char*)psz_outputmrl,
	    						i_options, (char**)ppsz_options, enablenewbroadcast, broadcast, exception);
	
	    CHECK_EXCEPTION_FREE;
	    
	    if (psz_name != NULL)
	    	(*env)->ReleaseStringUTFChars(env, name, psz_name);
	
	    if (psz_inputmrl != NULL)
	    	(*env)->ReleaseStringUTFChars(env, name, psz_inputmrl);
	    	
	    if (psz_outputmrl != NULL)
	    	(*env)->ReleaseStringUTFChars(env, name, psz_outputmrl);
	});
}

JNIEXPORT void JNICALL Java_com_googlecode_legendtv_core_VLM_playMedia(JNIEnv *env, jobject _this, jstring name) 
{
	JFAULT_TOLERANT_SCOPE(env,
	{
		libvlc_exception_t*	exception	= JVLC_initException();
		libvlc_instance_t*	instance	= JVLC_getIntfInstance(env, _this);
	    const char*			psz_name	= (*env)->GetStringUTFChars(env, name, 0);
	    
	    libvlc_vlm_play_media(instance, (char*)psz_name, exception);
	    
	    CHECK_EXCEPTION_FREE;
	
		if (psz_name != NULL)
			(*env)->ReleaseStringUTFChars(env, name, psz_name);
	});
}

JNIEXPORT void JNICALL Java_com_googlecode_legendtv_core_VLM_stopMedia(JNIEnv *env, jobject _this, jstring name) 
{
	JFAULT_TOLERANT_SCOPE(env,
	{
		libvlc_exception_t*	exception	= JVLC_initException();
		libvlc_instance_t*	instance	= JVLC_getIntfInstance(env, _this);
	    const char*			psz_name	= (*env)->GetStringUTFChars(env, name, 0);
	
	
	    libvlc_vlm_stop_media(instance, (char*)psz_name, exception);
	    
	    CHECK_EXCEPTION_FREE;
	
	    if (psz_name != NULL)
	    	(*env)->ReleaseStringUTFChars(env, name, psz_name);
	});
}

JNIEXPORT void JNICALL Java_com_googlecode_legendtv_core_VLM_pauseMedia(JNIEnv *env, jobject _this, jstring name) 
{
	JFAULT_TOLERANT_SCOPE(env,
	{
		libvlc_exception_t*	exception	= JVLC_initException();
		libvlc_instance_t*	instance	= JVLC_getIntfInstance(env, _this);
	    const char*			psz_name	= (*env)->GetStringUTFChars(env, name, 0);
	   
	    libvlc_vlm_pause_media(instance, (char*)psz_name, exception);
	    
	    CHECK_EXCEPTION_FREE;
	
	    if (psz_name != NULL)
	    	(*env)->ReleaseStringUTFChars(env, name, psz_name);
	});
}

JNIEXPORT void JNICALL Java_com_googlecode_legendtv_core_VLM_seekMedia(JNIEnv *env, jobject _this, jstring name, jfloat percentage) 
{
	JFAULT_TOLERANT_SCOPE(env,
	{
		libvlc_exception_t*	exception	= JVLC_initException();
		libvlc_instance_t*	instance	= JVLC_getIntfInstance(env, _this);
	    const char*			psz_name	= (*env)->GetStringUTFChars(env, name, 0);
	
	    libvlc_vlm_seek_media(instance, (char*)psz_name, (float)percentage, exception);
	    
	    CHECK_EXCEPTION_FREE;
	
	    if (psz_name != NULL)
	    	(*env)->ReleaseStringUTFChars(env, name, psz_name);
	});
}

JNIEXPORT jstring JNICALL Java_com_googlecode_legendtv_core_VLM_showMedia(JNIEnv *env, jobject _this, jstring name) 
{
	JFAULT_TOLERANT_SCOPE_WITH_RETURN(env,
	{
		libvlc_exception_t*	exception		= JVLC_initException();
		libvlc_instance_t*	instance		= JVLC_getIntfInstance(env, _this);
	    const char*			psz_name		= (*env)->GetStringUTFChars(env, name, 0);
	    char*				psz_response;
	    jstring				js_response;
	   
	    psz_response	= libvlc_vlm_show_media(instance, (char*)psz_name, exception);
	    
	    CHECK_EXCEPTION_FREE;
	
	    if (psz_name != NULL)
	    	(*env)->ReleaseStringUTFChars(env, name, psz_name);
	    	
	    js_response		= (*env)->NewStringUTF(env, psz_response);
	    
	    if (psz_response != NULL)
	    	free(psz_response);
	
	    return js_response;
	}, NULL);
}

typedef float (*mediaAttrFunc)(libvlc_instance_t*, char*, int, libvlc_exception_t*);

static inline float __getMediaAttr(mediaAttrFunc func, JNIEnv *env, jobject _this, jstring name, jint index)
{
	JFAULT_TOLERANT_SCOPE_WITH_RETURN(env,
	{
		libvlc_exception_t*	exception	= JVLC_initException();
		libvlc_instance_t*	instance	= JVLC_getIntfInstance(env, _this);
		const char*			psz_name	= (*env)->GetStringUTFChars(env, name, 0);
		jfloat				response;
		
		response	= func(instance, (char*)psz_name, (int)index, exception);
		
		CHECK_EXCEPTION_FREE;
		
		if (psz_name != NULL)
			(*env)->ReleaseStringUTFChars(env, name, psz_name);
		
		return response;
	}, 0);
}

JNIEXPORT jfloat JNICALL Java_com_googlecode_legendtv_core_VLM_getMediaPosition(JNIEnv *env, jobject _this,
																	 jstring name, jint index)
{
	return ((jfloat)__getMediaAttr((mediaAttrFunc)libvlc_vlm_get_media_position, env, _this, name, index));
}

JNIEXPORT jint JNICALL Java_com_googlecode_legendtv_core_VLM_getMediaTime(JNIEnv *env, jobject _this, jstring name, jint index)
{
	return ((jint)__getMediaAttr((mediaAttrFunc)libvlc_vlm_get_media_time, env, _this, name, index));
}

JNIEXPORT jint JNICALL Java_com_googlecode_legendtv_core_VLM_getMediaLength(JNIEnv *env, jobject _this, jstring name, jint index)
{
	return ((jint)__getMediaAttr((mediaAttrFunc)libvlc_vlm_get_media_length, env, _this, name, index));
}

JNIEXPORT jint JNICALL Java_com_googlecode_legendtv_core_VLM_getMediaRate(JNIEnv *env, jobject _this, jstring name, jint index)
{
	return ((jint)__getMediaAttr((mediaAttrFunc)libvlc_vlm_get_media_rate, env, _this, name, index));
}

JNIEXPORT jint JNICALL Java_com_googlecode_legendtv_core_VLM_getMediaTitle(JNIEnv *env, jobject _this, jstring name, jint index)
{
	return ((jint)__getMediaAttr((mediaAttrFunc)libvlc_vlm_get_media_title, env, _this, name, index));
}

JNIEXPORT jint JNICALL Java_com_googlecode_legendtv_core_VLM_getMediaChapter(JNIEnv *env, jobject _this, jstring name, jint index)
{
	return ((jint)__getMediaAttr((mediaAttrFunc)libvlc_vlm_get_media_chapter, env, _this, name, index));
}

JNIEXPORT jboolean JNICALL Java_com_googlecode_legendtv_core_VLM_isSeekable(JNIEnv *env, jobject _this, jstring name, jint index)
{
	return ((jboolean)__getMediaAttr((mediaAttrFunc)libvlc_vlm_get_media_seekable, env, _this, name, index));
}