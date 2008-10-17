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
	
	public SearchDatabase(){
		showTitlesList = new LinkedList<ProgramListing>();
		Channel cbs = new Channel((short)4, "CBS");
		showTimesList.add( new ProgramListing("CSI" , "The original", Program.TV_14, 30, cbs , "10/16/08", "9:00PM") );
		showTimesList.add( new ProgramListing("CSI: Miami", "It looks like that bird... is the word..", Program.TV_14, 29, cbs, "10/18/08", "10:00PM" ) );
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

		Iterator<ProgramListing> it = showTitlesList.iterator();
		int i = 0;
		while( it.hasNext() ){
			titles.add(i, it.next());
			i++;
		}
	}
	
	public void getTimesList( DefaultListModel times, String searchKey ){
		
		Iterator<ProgramListing> it = showTimesList.iterator();
		int i = 0;
		while( it.hasNext() ){
			times.add(i, it.next());
			i++;
		}		
		
	}

}
