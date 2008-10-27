package data;

import view.Program;


/*
 * RecordedProgram.java
 *
 * Version:
 *	 $Id$
 *
 * Revisions:
 *   $Log$
 */
public class RecordedProgram extends Program{
	
	private String expirationDate;
	private final String recordedDate;

	public RecordedProgram(String title, String description, String rating,
			int length, String recorded, String expiration) {
		super(title, description, rating, length);
		expirationDate = expiration;
		recordedDate = recorded;
	}
	
	public String getExpirationDate(){
		if(expirationDate != null){
			return expirationDate;
		}else{
			return "None";
		}
	}
	
	public String getRecordedDate(){
		return recordedDate;
	}
	
	public void setExpirationDate(String newExpiration){
		expirationDate = newExpiration;
	}
	
	public String[] toFullDescription(){
		String[] returnString = {"<h1><u>" + getTitle() + "</u></h1><br><br><h2>Date Recorded: " + getRecordedDate() + 
				"<br>Set Expiration Date: " + getExpirationDate() + "</h2><br><br>" + getDescription()};
		return returnString;
	}
	
	public String toString(){
		return getTitle();
	}

}
