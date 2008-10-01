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
 * Playlist interface implementation class.
 * 
 * @author Filippo Carone <filippo@carone.org>
 * @author Guy Paddock <guy.paddock@gmail.com>
 */
public class Playlist extends VLCInterface
{
	/**
	 * Constructor for an Playlist interface instance.
	 * 
	 * @param	instance	VLC instance that this Playlist instance pertains to.
	 */
	Playlist(VLCInstance instance)
    {
    	super(instance);
    }

    /**
     * Adds a new item to the playlist.
     * 
     * TODO: document the kind of items that can be added.
     * 
     * @param	uri				Location of the item.
     * @param	name			Name of the item.
     * @return					The item ID.
     * @throws	VLCException	If an error occurs.
     */
    public synchronized int addItem(String uri, String name) throws VLCException
    {
        return addItem(uri, name, null);
    }

    /**
     * Adds a new item to the playlist with the specified playback options.
     * 
     * TODO: document the kind of items that can be added.
     * 
     * @param	uri				Location of the item.
     * @param	name			Name of the item.
     * @param	options			The playback options to use for the item.
     * @return					The item ID.
     * @throws	VLCException	If an error occurs.
     */
    public native synchronized int addItem(String uri, String name, String[] options) throws VLCException;
    
    /**
     * Removes the item at the specified index from the playlist.
     * 
     * @param	index			The index at which the item to be removed exists in the playlist.
     * @throws	VLCException	If an error occurs.
     */
    public native synchronized void removeItem(int index) throws VLCException;
    
    /**
     * Clears the playlist, making it empty.
     * 
     * @throws	VLCException	If an error occurs. 
     */
    public native synchronized void removeAllItems() throws VLCException;
    
    /**
     * Returns the number of items in the playlist. Beware that this number could be bigger than the number of times
     * add() has been called.
     * 
     * TODO: this should return the number of items added with add, with no respect to videolan internal playlist.
     * 
     * @return					The current number of items in the playlist
     * @throws	VLCException	If an error occurs.
     */
    public native synchronized int size() throws VLCException;

    /**
     * Plays the current item in the playlist.
     * 
     * @throws	VLCException	If an error occurs.
     */
    public synchronized void play() throws VLCException
    {
    	play(-1);
    }

    /**
	 * Plays the item at the specified index in the playlist.
	 * 
     * @param	index			The index of the item in the playlist to play.
     * @throws	VLCException	If an error occurs.
     */
    public native synchronized void play(int index) throws VLCException;

    /**
     * This function returns true if the current item has not been stopped.
     * 
     * @return					True if the current item has not been stopped
     * @throws	VLCException	If an error occurs.
     */
    public native boolean isPlaying() throws VLCException;
    
    /**
     * Toggles pause for the current item.
     * 
     * @throws	VLCException	If an error occurs.
     */
    public native synchronized void togglePause() throws VLCException;

    /**
     * Move to next item in the playlist and play it.
     * 
     * @throws	VLCException	If an error occurs.
     */
    public native synchronized void nextItem() throws VLCException;

    /**
     * Move to previous item in the playlist and play it.
     * 
     * @throws	VLCException	If an error occurs.
     */
    public native synchronized void previousItem() throws VLCException;

    /**
     * Stops the currently playing item. Compared to pause, stopping an item destroys any information related to the
     * item.
     */
    public native synchronized void stop() throws VLCException;

    /**
     * Accessor for the looping state of the playlist.
     * 
     * @return	True if the playlist is set to loop; false otherwise.
     */
    public native synchronized boolean isLooping();
    
    /**
     * Mutator for the looping state of the playlist.
     * 
     * @param loop	True if the playlist should loop; false otherwise.
     */
    public native synchronized void setLooping(boolean looping);
}
