package org.legendtv.core;

public class VLM extends VLCInterface
{
	/**
	 * Constructor for a VLM interface instance.
	 * 
	 * @param	instance	VLC instance that this VLM instance pertains to.
	 */
	VLM(VLCInstance instance)
    {
    	super(instance);
    }
    
    /**
	 * Add a broadcast, with one input
	 * @param name		The name of the new broadcast
	 * @param input		The input MRL
	 * @param output	The output MRL (the parameter to the "sout" variable)
	 * @param options	Additional options
	 * @param enabled	Boolean for enabling the new broadcast
	 * @param loop		Should this broadcast be played in loop ?
	 */
    public native void addBroadcast(String name, String input, String output,
									String[] options,
									boolean enabled, boolean loop) throws VLCException;

    /**
     * Delete a media (vod or broadcast)
     * @param name the media to delete
     */    
    public native void deleteMedia(String name) throws VLCException;

    /**
     * Enable or disable a media (vod or broadcast)
     * @param name the media to work on
     * @param enabled the new status
     */    
    public native void setEnabled(String name, boolean enabled) throws VLCException;

    /**
     * Set the output for a media
     * @param name the media to work on
     * @param output the output MRL (the parameter to the "sout" variable)
     */
    public native void setOutput(String name, String output) throws VLCException;

    /**
     * Set a media's input MRL. This will delete all existing inputs and
     * add the specified one.
     * @param name the media to work on
     * @param input the input MRL
     */
    public native void setInput(String name, String input) throws VLCException;

    /**
     * Set loop mode for a media
     * @param name the media to work on
     * @param loop the new status
     */
    public native void setLooping(String name, boolean loop) throws VLCException;

    /**
     * Edit the parameters of a media. This will delete all existing inputs and
     * add the specified one.
     * @param name the name of the new broadcast
     * @param input the input MRL
     * @param output the output MRL (the parameter to the "sout" variable)
     * @param options additional options
     * @param enabled boolean for enabling the new broadcast
     * @param loop Should this broadcast be played in loop ?
     */    
    public native void changeMedia(String name, String input, String output,
								   String[] options,
								   boolean enabled, boolean loop) throws VLCException;

    /**
     * Plays a media
     * @param name of the broadcast to play
     */
    public native void playMedia(String name) throws VLCException;
	
    /**
     * Stops a media
     * @param name of the broadcast to stop
     */
    public native void stopMedia(String name) throws VLCException;

    /**
     * Pauses a media
     * @param name name of the broadcast to pause
     */    
    public native void pauseMedia(String name) throws VLCException;
	
	public native void seekMedia(String name, float percentage) throws VLCException;

	public native String showMedia(String name) throws VLCException;
	
	public native float getMediaPosition(String name, int mediaInstance) throws VLCException;

	public native int getMediaTime(String name, int mediaInstance) throws VLCException;

	public native int getMediaLength(String name, int mediaInstance) throws VLCException;

	public native int getMediaRate(String name, int mediaInstance) throws VLCException;

	public native int getMediaTitle(String name, int mediaInstance) throws VLCException;

	public native int getMediaChapter(String name, int mediaInstance) throws VLCException;

	public native boolean isSeekable(String name, int mediaInstance) throws VLCException;
}
