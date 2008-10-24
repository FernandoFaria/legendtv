/*
 * SearchDatabase.java
 *
 * Version:
 *	 $Id$
 *
 * Revisions:
 *   $Log$
 */
package view;

import java.util.*;
import javax.swing.*;

public class SearchDatabase {
	
	private LinkedList <ProgramListing>showTitlesList;
	private LinkedList <ProgramListing>showTimesList;
	private LinkedList <ProgramListing>shows;
	
	public SearchDatabase(){
		showTitlesList = new LinkedList<ProgramListing>();
		showTimesList = new LinkedList<ProgramListing>();
		shows = new LinkedList<ProgramListing>();
		
		Channel cbs = new Channel((short)4, "CBS");
		Channel fox =  new Channel((short)6, "Fox");
		Channel nbc = new Channel((short)2, "NBC");
		
		shows.add( new ProgramListing("CSI" , "The original", Program.TV_14, 30, cbs , "10/16/08", "9:00PM") );
		shows.add( new ProgramListing("CSI" , "The original times two", Program.TV_14, 30, cbs , "10/23/08", "9:00PM"));
		shows.add( new ProgramListing("CSI: Miami", "It looks like that bird... is the word..", Program.TV_14, 29, cbs, "10/18/08", "10:00PM" ) );
		shows.add( new ProgramListing("Family Guy", "Peter does something stupid", Program.TV_14, 30, nbc, "10/24/08", "10:00PM" ));
		shows.add( new ProgramListing("Family Guy", "Peanut Butter Jelly Time!!!", Program.TV_14, 30, fox, "10/24/08", "10:00PM" ));
		shows.add( new ProgramListing("Family Guy", "Chicken Fight.... Part 1", Program.TV_14, 30, cbs, "10/24/08", "10:00PM" ));
		shows.add( new ProgramListing("Family Guy", "Chicken FIght Part 2", Program.TV_14, 30, cbs, "10/25/08", "10:00PM" ));
		shows.add( new ProgramListing("Family Guy", "ooooooo!!!  Piece of Candy!!!", Program.TV_14, 30, cbs, "10/26/08", "9:00PM" ));
		shows.add( new ProgramListing("Family Guy", "Itsgonnarain!!!", Program.TV_14, 30, cbs, "10/28/08", "8:00PM" ));
		shows.add( new ProgramListing("Law and Order", "The original", Program.TV_14, 60, nbc, "10/30/2008", "9:00PM"));
		shows.add( new ProgramListing("Law and Order", "Laws versus order", Program.TV_14, 60, nbc, "10/30/2008", "9:00PM"));
		shows.add( new ProgramListing("Law and Order: Criminal Intent", "TCriminals have some intent", Program.TV_14, 60, nbc, "10/30/2008", "9:00PM"));
		shows.add( new ProgramListing("Law and Order: Criminal Intent", "What is going on with those darn criminals?!?", Program.TV_14, 60, nbc, "10/30/2008", "9:00PM"));
		shows.add( new ProgramListing("Law and Order: SVU", "Special Victims Unit", Program.TV_14, 60, nbc, "10/30/2008", "9:00PM"));
		shows.add( new ProgramListing("Law and Order: SVU", "DRAMA!!!!", Program.TV_14, 60, nbc, "10/30/2008", "9:00PM"));
	}
	
	public void getShowTitles(DefaultListModel titles, String searchKey){
		titles.clear();
		Iterator<ProgramListing> it = shows.iterator();
		int i = 0;
		TreeSet<String> programTitles = new TreeSet<String>();
		while( it.hasNext() ){
			ProgramListing p = it.next();
			if( p.getTitle().contains(searchKey) ){
				programTitles.add( p.getTitle() );
			}
			showTimesList.add( p );
		}
			
		Iterator<String> itString = programTitles.iterator();

		while( itString.hasNext() ){
			titles.add( i, itString.next() );
			i++;
		}
		if( titles.isEmpty() ){	
			titles.add(0, "No shows found");
		}
	}
	
	public void getTimesList( DefaultListModel times, String searchKey ){
		times.clear();
		Iterator<ProgramListing> it = showTimesList.iterator();
		int i = 0;
		if( searchKey != null && !searchKey.equals("No shows found") ){
			while( it.hasNext() ){
				ProgramListing p = it.next();
				if(p.getTitle().equals(searchKey)){
					times.add(i, p);
					i++;
				}
			}			
		}else{
			while( it.hasNext() ){
				ProgramListing p = it.next();
				times.add(i, p);
				i++;

			}	
		}
	}
	

}
