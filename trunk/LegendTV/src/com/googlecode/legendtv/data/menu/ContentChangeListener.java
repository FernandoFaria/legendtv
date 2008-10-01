package com.googlecode.legendtv.data.menu;

/**
 * Interface for classes that subscribe to notifications about content changes.
 *  
 * @author Guy Paddock
 */
public interface ContentChangeListener
{
	/**
	 * Method called to inform the listener that content has changed.
	 * 
	 * @param source	The ContentGenerator whose contents have changed.
	 */
	public void contentChanged(ContentGenerator<?> source);
}
