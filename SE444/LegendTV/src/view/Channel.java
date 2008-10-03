/**
 * File: Channel.java
 * 
 * Created by Eric Lutley on Sep 30, 2008
 *
 * Version: $Id$
 *
 * Revisions:
 *		$Log$
 */
package view;

/**
 * @author Eric
 *
 */
public class Channel {

	private final short number;
	private final String name;

	public Channel( short number, String name ) {
		this.number = number;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public short getNumber() {
		return number;
	}
	
	public String toString() {
		return "[" + number + ": " + name + "]";
	}
}
