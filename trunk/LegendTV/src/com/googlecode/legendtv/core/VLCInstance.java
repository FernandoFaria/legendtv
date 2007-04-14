package com.googlecode.legendtv.core;

public class VLCInstance
{
	private long 		id;
	private Audio		audioIntf;
	private Input		inputIntf;
	private Playlist	playlistIntf;
	private VLM			vlmIntf;
	
	static
	{
		// FIXME
		System.load("/home/pvr/LTV/LegendTV-JNI/src/libLTV-JVLC.so");
	}
	
	public VLCInstance()
	{
		// Initialize VLC core and obtain instance ID
		this.id				= createInstance(new String[0]);
		
		// Initialize interfaces
		this.audioIntf		= new Audio(this);
		this.inputIntf		= new Input(this);
		this.playlistIntf	= new Playlist(this);
		this.vlmIntf		= new VLM(this);
	}
	
	long getID()
	{
		return id;
	}
	
	public Audio getAudio()
	{
		return audioIntf;
	}
	
	public Input getInput()
	{
		return inputIntf;
	}
	
	public Playlist getPlaylist()
	{
		return playlistIntf;
	}
	
	public VLM getVLM()
	{
		return vlmIntf;
	}
	
	@Override
	public void finalize() throws Throwable
	{
		destroy();
		
		super.finalize();
	}
	
	private native long createInstance(String[] args);
    private native void destroy();
}
