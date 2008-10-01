 /*****************************************************************************
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
 * Abstract super-class for all VLC interfaces.
 * 
 * @author Guy Paddock
 */
public abstract class VLCInterface
{
	/**
	 * The VLC instance that this interface pertains to.
	 */
	private VLCInstance instance;
    
	/**
	 * Constructor for a VLCInterface interface instance.
	 * 
	 * @param	instance	VLC instance that this interface instance
	 * 						pertains to.
	 */
	protected VLCInterface(VLCInstance instance)
    {
    	this.instance = instance;
    }
    
    /**
     * Accessor for the VLC instance identifier that this interface instance
     * pertains to.
     * 
     * @return	VLC instance identifier that this instance pertains to.
     */
	public VLCInstance getInstance()
	{
		return instance;
	}
}
