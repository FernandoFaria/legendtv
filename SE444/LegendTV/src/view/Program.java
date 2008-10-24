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

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

import view.utils.UIHelper;

/**
 * @author Eric
 *
 */
public class Program {

	public static final String TV_Y = "TV-Y";
	private static final String TV_Y_PATH = "images/tv_y.jpg";
	
	public static final String TV_Y7 = "TV-Y7";
	private static final String TV_Y7_PATH = "images/tv_y7.jpg";
	
	public static final String TV_G = "TV-G";
	private static final String TV_G_PATH = "images/tv_g.jpg";
	
	public static final String TV_PG = "TV-PG";
	private static final String TV_PG_PATH = "images/tv_pg.jpg";
	
	public static final String TV_14 = "TV-14";
	private static final String TV_14_PATH = "images/tv_14.jpg";
	
	public static final String TV_MA = "TV-MA";
	private static final String TV_MA_PATH = "images/tv_ma.gif";
	
	private static final Map<String,ImageIcon >ratingIconMap;
	static {
		ratingIconMap = new HashMap<String,ImageIcon>();
		try {
			ratingIconMap.put( TV_Y, 
					new ImageIcon( UIHelper.resourcePathToUrl( TV_Y_PATH ) ) );
		} catch ( FileNotFoundException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ratingIconMap.put( TV_Y7, 
					new ImageIcon( UIHelper.resourcePathToUrl( TV_Y7_PATH ) ) );
		} catch ( FileNotFoundException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ratingIconMap.put( TV_G, 
					new ImageIcon( UIHelper.resourcePathToUrl( TV_G_PATH ) ) );
		} catch ( FileNotFoundException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ratingIconMap.put( TV_PG, 
					new ImageIcon( UIHelper.resourcePathToUrl( TV_PG_PATH ) ) );
		} catch ( FileNotFoundException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ratingIconMap.put( TV_14, 
					new ImageIcon( UIHelper.resourcePathToUrl( TV_14_PATH ) ) );
		} catch ( FileNotFoundException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ratingIconMap.put( TV_MA, 
					new ImageIcon( UIHelper.resourcePathToUrl( TV_MA_PATH ) ) );
		} catch ( FileNotFoundException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Immutable fields
	private final String title;
	private final String description;
	private final String rating;
	private final ImageIcon ratingIcon;
	private final int length;
	
	// Mutable fields
	private boolean isRecording;

	public Program( String title, String description, String rating, int length ) {
		this.title = title;
		this.description = description;
		this.rating = rating;
		this.length = length;
		this.ratingIcon = Program.ratingIconMap.get( rating );
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
	
	public ImageIcon getRatingIcon() {
		return ratingIcon;
	}
	
	public void setRecording( boolean isRecording ) {
		this.isRecording = isRecording;
	}
	
	public boolean isRecording() {
		return isRecording;
	}
}
