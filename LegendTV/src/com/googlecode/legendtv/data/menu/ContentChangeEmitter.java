package com.googlecode.legendtv.data.menu;

/**
 * Interface for classes whose content may change that ContentChangeListeners can
 * subscribe to notifications about.
 * 
 * @author Guy Paddock
 */
public interface ContentChangeEmitter
{
	/**
	 * Method to add a content change listener that will be informed when content changes.
	 * 
	 * @param listener	The listener to add.
	 * @see ContentChangeEmitter#removeChangeListener(ContentChangeListener)
	 */
	public void addChangeListener(ContentChangeListener listener);
	
	/**
	 * Method to remove a content change listener. The listener will no longer receive
	 * notifications when content changes.
	 * 
	 * If the provided listener was not previously subscribed via addChangeListener(), this method
	 * should do nothing and return immediately.
	 * 
	 * @param listener	The listener to remove.
	 * @see ContentChangeEmitter#removeChangeListener(ContentChangeListener)
	 */
	public void removeChangeListener(ContentChangeListener listener);
}
