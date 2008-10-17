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
		shows.add( new ProgramListing("CSI" , "The original", Program.TV_14, 30, cbs , "10/16/08", "9:00PM") );
		shows.add( new ProgramListing("CSI" , "The original times two", Program.TV_14, 30, cbs , "10/23/08", "9:00PM"));
		shows.add( new ProgramListing("CSI: Miami", "It looks like that bird... is the word..", Program.TV_14, 29, cbs, "10/18/08", "10:00PM" ) );
		//showTitlesList.add("CSI: RIT");
		//showTitlesList.add("CSI: Wasilla");
		
		/*
		showTimesListModel.add(0, "Mon 09/29/08 9:00PM CBS");
		showTimesListModel.add(1, "Mon 09/29/08 10:00PM CBS");
		showTimesListModel.add(2, "Tue 09/30/08 9:00PM CBS");
		showTimesListModel.add(3, "Fri 10/3/08 10:00PM USA");
		showTimesListModel.add(4, "Mon 09/29/08 9:00PM CBS");
		*/
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
		if( !searchKey.equals("No shows found") ){
			Iterator<ProgramListing> it = showTimesList.iterator();
			int i = 0;
			while( it.hasNext() ){
				ProgramListing p = it.next();
				System.out.println( p.getTitle() );
				if(p.getTitle().equals(searchKey)){
					times.add(i, p);
					i++;
				}
			}
			
		}
	}
	

}
