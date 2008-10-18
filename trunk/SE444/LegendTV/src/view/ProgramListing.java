/*
 * ProgramListing.java
 *
 * Version:
 *	 $Id$
 *
 * Revisions:
 *   $Log$
 */

package view;

public class ProgramListing extends Program{
	
	private Channel programChannel;
	private String date, time;

	public ProgramListing(String title, String description, String rating, int length, 
			Channel channel, String date, String time) {
		super(title, description, rating, length);
		
		this.programChannel = channel;
		this.date = date;
		this.time = time;
	}
	
	public Channel getProgramChannel(){
		return programChannel;
	}
	
	public String date(){
		return date;
	}
	
	public String time(){
		return time;
	}
	
	public String toString(){
		return date + " " + time + " " + programChannel.getName(); 
	}
	
}
