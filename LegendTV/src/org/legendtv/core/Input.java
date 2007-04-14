package org.legendtv.core;

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
     * @return The total length, in milliseconds, of the current file playing.
     * @throws VLCException
     */
    public native long getLength() throws VLCException;    

    /**
     * @return The current position in millis within the playing item.
     * @throws VLCException
     */
    public native long getTime() throws VLCException;
    
    /**
     * Moves current input to time specified in value
     * @param value The time in milliseconds to move the input to.
     * @throws VLCException
     */
    public native void setTime(long value) throws VLCException;
    
    /**
     * @return The position in %.
     * @throws VLCException
     */
    public native float getPosition() throws VLCException;

    /**
     * Moves current input to position specified in a float [0-1].
     * @param value The position, from 0 to 1, to move the input to.
     * @throws VLCException
     */
    public native void setPosition(float value) throws VLCException;

    /**
     * @return True if the current input is really playing
     */
    public native boolean isPlaying() throws VLCException;
    
    /**
     * @return If the playing item is a video file, returns the FPS, otherwise 0.
     * @throws VLCException
     */
    public native float getFPS() throws VLCException;
    
    /**
     * @return True if the current input has spawned a video output window
     */
    public native boolean hasVout() throws VLCException;
}
