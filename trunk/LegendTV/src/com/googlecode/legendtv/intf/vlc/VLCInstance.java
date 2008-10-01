 /*****************************************************************************
  * Copyright (C) 1998-2006 the VideoLAN team
  * Copyright (C) 2007 The LegendTV team 
  * 
  * $Id$
  *
  * This program is free software; you can redistribute it
  * and/or modify it under the terms of the GNU General Public License
  * as published by the Free Software Foundation; either version 2 of the
  * License, or (at your option) any later version.
  * 
  * This program is distributed in the hope that it will be useful, but
  * WITHOUT ANY WARRANTY; without even the implied warranty of
  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
  * General Public License for more details.
  * 
  * You should have received a copy of the GNU General Public
  * License along with this program; if not, write to the Free Software
  * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston MA 02110-1301, USA.
  * 
  */
package com.googlecode.legendtv.intf.vlc;

/**
 * VLC playback engine interface.
 * 
 * @author Guy Paddock <guy.paddock@gmail.com>
 */
public class VLCInstance
{
	private long 		id;
	private Audio		audioIntf;
	private Input		inputIntf;
	private Playlist	playlistIntf;
	
	static
	{
		// FIXME: This should be a relative path!
		System.load("/home/pvr/LTV/LegendTV-JNI/src/libLTV-JVLC.so");
	}
	
	/**
	 * Constructor for VLCInstance.
	 * 
	 * Initializes a new VLC instance with all supported interfaces and default arguments.
	 */
	public VLCInstance()
	{
		// Initialize VLC core and obtain instance ID
		this.id				= createInstance(new String[0]);
		
		// Initialize interfaces
		this.audioIntf		= new Audio(this);
		this.inputIntf		= new Input(this);
		this.playlistIntf	= new Playlist(this);
	}
	
	/**
	 * Internal method that handles the initialization and creation of this instance.
	 * 
	 * @param args	The command-line arguments to use for the new instance.
	 * @return		A handle to refer to this instance when calling VLC engine internals.
	 */
	private native long createInstance(String[] args);
	
	/**
	 * Accessor for the handle of this instance, used internally by the VLC engine.
	 * 
	 * @return	The handle of this instance.
	 */
	long getID()
	{
		return id;
	}
	
	/**
	 * Accessor for the audio interface to this instance.
	 * 
	 * @return	The audio interface to this instance.
	 */
	public Audio getAudio()
	{
		return audioIntf;
	}
	
	/**
	 * Accessor for the input interface to this instance.
	 * 
	 * @return	The input interface to this instance.
	 */
	public Input getInput()
	{
		return inputIntf;
	}
	
	/**
	 * Accessor for the playlist interface to this instance.
	 * 
	 * @return	The playlist interface to this instance.
	 */
	public Playlist getPlaylist()
	{
		return playlistIntf;
	}
	
	@Override
	public void finalize() throws Throwable
	{
		destroy();
		
		super.finalize();
	}
	
	/**
	 * Internal destructor for this instance.
	 */
    private native void destroy();
}
