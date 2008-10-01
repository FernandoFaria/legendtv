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

import java.lang.Exception;

/**
 * VLC general exception class.
 * 
 * TODO: Make, declare, and throw more specific exceptions.
 * 
 * @author Philippe Morin <phmorin@free.fr>
 * @author Guy Paddock <guy.paddock@gmail.com>
 */
public class VLCException extends Exception
{
	public VLCException()
	{
	   super();
	}
	
	public VLCException(String message)
	{
	   super(message);
	}
	
	public VLCException(String message, Throwable cause)
	{
	   super(message,cause);
	}
	
	public VLCException(Throwable cause)
	{
	   super(cause);
	}
}
