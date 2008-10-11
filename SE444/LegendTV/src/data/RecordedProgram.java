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
	
	public String toString(){
		return getTitle() + '\n'+ '\n' + "Date Recorded: " + getRecordedDate() +'\n'+
				"Set Expiration Date: " + getExpirationDate() + '\n'+'\n' + getDescription();
	}

}
