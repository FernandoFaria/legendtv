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
 * Input interface implementation class.
 * 
 * @author Filippo Carone <filippo@carone.org>
 * @author Guy Paddock <guy.paddock@gmail.com>
 */
public class Input extends VLCInterface
{	
	/**
	 * Constructor for an Input interface instance.
	 * 
	 * @param	instance	VLC instance that this Input instance pertains to.
	 */
    Input(VLCInstance instance)
    {
    	super(instance);
    }
    
	/**
     * @return					The total length, in milliseconds, of the current file playing.
     * @throws	VLCException	If an error occurs.
     */
    public native long getLength() throws VLCException;    

    /**
     * @return					The current position in milliseconds within the playing item.
     * @throws	VLCException	If an error occurs.
     */
    public native long getTime() throws VLCException;
    
    /**
     * Moves current input to time specified for the time parameter.
     * 
     * @param	time			The time in milliseconds to move the input to.
     * @throws	VLCException	If an error occurs.
     */
    public native void setTime(long time) throws VLCException;
    
    /**
     * @return					The current input stream position as a % of the total stream length.
     * @throws	VLCException	If an error occurs.
     */
    public native float getPosition() throws VLCException;

    /**
     * Moves current input to the position specified in a float [0-1], representing a percentage of the total input
     * stream length.
     * 
     * @param	position		The position, from 0 to 1, to move the input to.
     * @throws	VLCException	If an error occurs.
     */
    public native void setPosition(float position) throws VLCException;

    /**
     * @return 					True if the current input is really playing
     * @throws	VLCException	If an error occurs.
     */
    public native boolean isPlaying() throws VLCException;
    
    /**
     * @return 					If the playing item is a video file, returns the FPS, otherwise 0.
     * @throws	VLCException	If an error occurs.
     */
    public native float getFPS() throws VLCException;
    
    /**
     * @return 					True if the current input has spawned a video output window
     * @throws	VLCException	If an error occurs.
     */
    public native boolean hasVout() throws VLCException;
}
