package com.googlecode.legendtv.data.menu;

import java.awt.event.ActionListener;

/**
 * Interface for classes that perform actions that ActionListeners can
 * subscribe to notifications about.
 * 
 * @author Guy Paddock
 */
public interface ActionEmitter
{
	/**
	 * Method to add an action listener that will be informed when an action is performed.
	 * 
	 * @param listener	The listener to add.
	 * @see ActionEmitter#removeActionListener(ActionListener)
	 */
	public void addActionListener(ActionListener listener);
	
	/**
	 * Method to remove an action listener. The listener will no longer receive notifications
	 * when an action is performed.
	 * 
	 * If the provided listener was not previously subscribed via addActionListener(), this method
	 * should do nothing and return immediately.
	 * 
	 * @param listener	The listener to remove.
	 * @see ActionEmitter#addActionListener(ActionListener)
	 */
	public void removeActionListener(ActionListener listener);
}
