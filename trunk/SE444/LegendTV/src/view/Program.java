/**
 * File: Program.java
 * 
 * Created by Eric Lutley on Oct 3, 2008
 *
 * Version: $Id$
 *
 * Revisions:
 *		$Log$
 */
package view;

import javax.swing.ImageIcon;

/**
 * @author Eric
 *
 */
public class Program {

	public static final String TV_Y = "TV-Y";
	public static final ImageIcon TV_Y_ICON = new ImageIcon( "images/tv_y.jpg" ); 
	
	public static final String TV_Y7 = "TV-Y7";
	public static final String TV_G = "TV-G";
	public static final String TV_PG = "TV-PG";
	public static final String TV_14 = "TV-14";
	public static final String TV_MA = "TV-MA";
	
	private final String title;
	private final String description;
	private final String rating;
	private final int length;

	public Program( String title, String description, String rating, int length ) {
		this.title = title;
		this.description = description;
		this.rating = rating;
		this.length = length;
	}
	
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append( "[\"" );
		string.append( title );
		string.append( "\", " );
		string.append( rating );
		string.append( ", " );
		string.append( length );
		string.append( " minutes]" );
		
		return string.toString();
	}

	public String getDescription() {
		return description;
	}

	public String getTitle() {
		return title;
	}
	
	public int getLength(){
		return length;
	}
	
	public String getRating(){
		return rating;
	}
	
}
