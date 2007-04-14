package org.legendtv.core;

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
