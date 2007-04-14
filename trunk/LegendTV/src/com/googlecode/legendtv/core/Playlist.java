 /*****************************************************************************
 * Playlist.java: PlaylistIntf implementation class
 *****************************************************************************
 *
 * Copyright (C) 1998-2006 the VideoLAN team
 * 
 * Author: Filippo Carone <filippo@carone.org>
 *
 * Created on 28-feb-2006
 *
 * $Id: Playlist.java 17089 2006-10-15 10:54:15Z littlejohn $
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


package com.googlecode.legendtv.core;

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
     * TODO: document the kind of items that can be added.
     * Add a new item in the playlist.
     * 
     * @param uri Location of the item
     * @param name Name of the item
     * @return The item ID
     */
    public synchronized int add(String uri, String name) throws VLCException
    {
        return add(uri, name, null);
    }

    public native synchronized int add(String uri, String name, String[] options) throws VLCException;
    
    public native synchronized void remove(int index) throws VLCException;
    
    /**
     * Clear the playlist which becomes empty after this call.
     */
    public native synchronized void removeAll() throws VLCException;
    
    /**
     * TODO: this should return the number of items added with add, with no
     * respect to videolan internal playlist.
     * 
     * Returns the number of items in the playlist. Beware that this number
     * could be bigger than the number of times add() has been called.
     * 
     * @return Current number of items in the playlist
     */
    public native synchronized int size() throws VLCException;

    /**
     * Plays the current item in the playlist.
     */
    public synchronized void play() throws VLCException
    {
    	play(-1, null);
    }

    /**
	 * Plays the item specified in id, with options. At the moment options
	 * has no effect and can be safely set to null.
     * @param id The ID to play
     * @param options Options to play the item with
     */
    public native synchronized void play(int id, String[] options) throws VLCException;

    /**
     * This function returns true if the current item has not been stopped.
     * @return True if the current item has not been stopped
     */
    public native boolean isPlaying() throws VLCException;
    
    /**
     * Toggles pause for the current item.
     */
    public native synchronized void togglePause() throws VLCException;

    /**
     * Move to next item in the playlist and play it.
     */
    public native synchronized void nextItem() throws VLCException;

    /**
     * Move to previous item in the playlist and play it.
     */
    public native synchronized void previousItem() throws VLCException;

    /**
     * Stops the currently playing item. Differently from pause, stopping
     * an item destroys any information related to the item.
     */
    public native synchronized void stop() throws VLCException;


    public native synchronized boolean isLooping();
    
    /**
     * @param loop
     */
    public native synchronized void setLooping(boolean loop);
}
