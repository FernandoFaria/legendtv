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
 * Wrapper object to the VLC VLM media server interface.  
 * 
 * @author Guy Paddock <guy.paddock@gmail.com>
 */
public class Media
{
	private String 			name;
	private String 			input;
	private String 			output;
	private String[]		options;
    private boolean			enabled;
    private boolean			looping;
    private NativeMethods	nativeMethods;
	
    /**
	 * Initializes a new media broadcast with one input.
	 * 
	 * @param	instance		The VLC instance that the new media pertains to.
	 * @param	name			The name of the new broadcast.
	 * @param	input			The input MRL.
	 * @param	output			The output MRL (the parameter to the "sout" variable).
	 * @param	options			Additional options.
	 * @param	enabled			Whether or not the broadcast should be enabled.
	 * @param	looping			Whether or not the broadcast should be played in a loop.
	 * @throws	VLCException	If an error occurs. 
	 */
    public Media(VLCInstance instance, String name, String input, String output, String[] options,
	             boolean enabled, boolean looping)
    throws VLCException
	{
    	this.name			= name;
    	this.input			= input;
    	this.output			= output;
    	this.options		= options;
    	this.enabled		= enabled;
    	this.looping		= looping;
    	
    	this.nativeMethods	= new NativeMethods(instance); 
    	
    	this.nativeMethods.initialize(name, input, output, options, enabled, looping);
	}
	
    /**
     * Accessor for the name of this medium.
     * 
     * @return	The name of this medium.
     */
    public String getName()
    {
    	return name;
    }

    /**
	 * Accessor for the options of this medium.
	 * 
	 * @return	The array of options for this medium.
	 */
	public String[] getOptions()
    {
    	return options;
    }	
    
    /**
     * Accessor for whether or not this instance is enabled.
     * 
     * @return	True if this instance is enabled, false otherwise.
     */
    public boolean isEnabled()
    {
    	return (this.enabled);
    }
    
    /**
     * Mutator for whether or not this instance is enabled.
     * 
     * @param	enabled			True if this instance should be enabled, false if is should be disabled.
	 * @throws	VLCException	If an error occurs.
     */
    public void setEnabled(boolean enabled)
    throws VLCException
    {
    	this.enabled	= enabled;
    	
    	this.nativeMethods.setEnabled(this.name, enabled);
    }
    
    /**
     * Accessor for the output MRL of this medium.
     * 
     * @return	The output MRL of this medium.
     */
	public String getOutput()
    {
    	return output;
    }

	/**
     * Set the output for this instance.
     * 
     * @param	output			The output MRL (the parameter to the "sout" variable).
	 * @throws	VLCException	If an error occurs.
     */
    public void setOutput(String output)
    throws VLCException
    {
    	this.output	= output;
    	
    	this.nativeMethods.setOutput(this.name, output);
    }

    /**
     * Accessor for the input MRL of this medium.
     * 
     * @return	The input MRL of this medium.
     */
	public String getInput()
    {
    	return input;
    }

	/**
     * Set this media's input MRL.
     * 
     * This will delete all existing inputs and add the specified one.
     * 
     * @param	input			The input MRL.
	 * @throws	VLCException	If an error occurs.
     */
    public void setInput(String input)
    throws VLCException
    {
    	this.input	= input;
    	
    	this.nativeMethods.setInput(this.name, input);
    }

    /**
     * Accessor for the looping mode of this medium.
     * 
     * @return	True if this medium will loop, false if it will play through only once.
     */
	public boolean isLooping()
    {
    	return (this.looping);
    }

    /**
     * Mutator for the looping mode of this medium.
     * 
     * @param 	looping			True if this medium should loop, false if it should only play through once.
	 * @throws	VLCException	If an error occurs.
     */
    public void setLooping(boolean looping)
    throws VLCException
    {
    	this.looping	= looping;
    	
    	this.nativeMethods.setLooping(this.name, looping);
    }    
    
    /**
     * Plays this medium.
     * 
	 * @throws	VLCException	If an error occurs.
     */
    public void play()
    throws VLCException
    {
    	this.nativeMethods.play(this.name);
    }
	
    /**
     * Stops playing this medium.
     * 
	 * @throws	VLCException	If an error occurs.
     */
    public void stop()
    throws VLCException
    {
    	this.nativeMethods.stop(this.name);
    }

    /**
     * Pauses this medium.
     * 
	 * @throws	VLCException	If an error occurs.
     */    
    public void pause()
    throws VLCException
    {
    	this.nativeMethods.pause(this.name);
    }
    
    /**
     * Deletes this medium.
     * NOTE: This has no effect on garbage collection.
     * 
     * @throws VLCException
     */
    public void delete()
    throws VLCException
    {
    	this.nativeMethods.delete(this.name);
    }
    
    /**
     * Method called when this instance is about to be garbage collected.
     * This guarantees that the internal VLM code deletes this medium.
     */
    @Override
    protected void finalize()
    throws Throwable
    {	    
	    this.delete();	    
	    super.finalize();
    }

	/**
     * Private class that hides all the internal native methods that lie behind the instance magic of this class.
     *  
     * @author Filippo Carone <filippo@carone.org>
     * @author Guy Paddock <guy.paddock@gmail.com>
     */
    private static class NativeMethods extends VLCInterface
    {
    	/**
    	 * Constructor for NativeMethods.
    	 * 
    	 * @param instance	The VLC instance that the method calls apply to.
    	 */
    	public NativeMethods(VLCInstance instance)
        {
	        super(instance);
        }

		/**
    	 * Initializes a new broadcast with one input.
    	 * 
    	 * @param	name			The name of the new broadcast.
    	 * @param	input			The input MRL.
    	 * @param	output			The output MRL (the parameter to the "sout" variable).
    	 * @param	options			Additional options.
    	 * @param	enabled			Whether or not the broadcast should be enabled.
    	 * @param	looping			Whether or not the broadcast should be played in a loop.
    	 * @throws	VLCException	If an error occurs. 
    	 */
        public native void initialize(String name, String input, String output,
                                      String[] options,
                                      boolean enabled, boolean looping)
        throws VLCException;
        
        /**
         * Delete a media (vod or broadcast)
         * 
         * @param 	name			The media to delete.
         * @throws	VLCException	If an error occurs.
         */    
        public native void delete(String name)
        throws VLCException;
        
    	/**
	     * Enable or disable a media (vod or broadcast).
	     * 
	     * @param 	enabled			The new status.
         * @throws	VLCException	If an error occurs.
	     */    
	    public native void setEnabled(String name, boolean enabled)
	    throws VLCException;
	
	    /**
	     * Set the output for a media.
	     * 
	     * @param	name			The media to work on.
	     * @param	output			The output MRL (the parameter to the "sout" variable).
         * @throws	VLCException	If an error occurs.
	     */
	    public native void setOutput(String name, String output)
	    throws VLCException;
	
	    /**
	     * Set a media's input MRL.
	     * 
	     * This will delete all existing inputs and add the specified one.
	     * 
	     * @param 	name			The media to work on.
	     * @param 	input			The input MRL.
         * @throws	VLCException	If an error occurs.
	     */
	    public native void setInput(String name, String input)
	    throws VLCException;
	
	    /**
	     * Set loop mode for a media.
	     * 
	     * @param 	name			The media to work on.
	     * @param 	looping			The new status.
         * @throws	VLCException	If an error occurs.
	     */
	    public native void setLooping(String name, boolean looping)
	    throws VLCException;
	
	    /**
	     * Edit the parameters of a media.
	     * 
	     * This will delete all existing inputs and add the specified one.
	     * 
	     * @param 	name			The name of the new broadcast
	     * @param 	input			The input MRL
	     * @param 	output			The output MRL (the parameter to the "sout" variable)
	     * @param 	options			Additional options
	     * @param 	enabled			Boolean for enabling the new broadcast
	     * @param 	looping			Should this broadcast be played in loop ?
         * @throws	VLCException	If an error occurs.
	     */    
	    public native void change(String name, String input, String output,
	                              String[] options,
	                              boolean enabled, boolean looping)
	    throws VLCException;
	
	    /**
	     * Plays a media.
	     * 
	     * @param 	name			The name of the broadcast to play.
         * @throws	VLCException	If an error occurs.
	     */
	    public native void play(String name)
	    throws VLCException;
		
	    /**
	     * Stops a media.
	     * 
	     * @param 	name			The name of the broadcast to stop.
         * @throws	VLCException	If an error occurs.
	     */
	    public native void stop(String name)
	    throws VLCException;
	
	    /**
	     * Pauses a media.
	     * 
	     * @param 	name			The name of the broadcast to pause
         * @throws	VLCException	If an error occurs.
	     */    
	    public native void pause(String name)
	    throws VLCException;
    }
}
