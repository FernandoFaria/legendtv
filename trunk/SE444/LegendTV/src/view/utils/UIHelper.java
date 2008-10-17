package view.utils;

import java.awt.Color;
import java.awt.Font;

/**
 * A utility class for providing commonly-used fonts & colors, in addition
 * to converting layout information to JComponent-renderable HTML.
 * 
 * @author Guy Paddock (gap7472@rit.edu)
 */
public class UIHelper
{
	/**
	 * Returns the font to be used for screen headings.
	 * 
	 * @return	A font object with the appropriate properties set for
	 * 			use on headings.
	 */
	public static Font getHeadingFont()
	{
		return new Font("Segoe UI", Font.PLAIN, 48);
	}

	/**
	 * Returns the font to be used for screen body text.
	 * 
	 * @return	A font object with the appropriate properties set for
	 * 			use on body text.
	 */
	public static Font getBodyFont()
	{
		return new Font("Segoe UI", Font.PLAIN, 24);
	}

	/**
	 * Returns the background color to be used on most screens.
	 * 
	 * @return	The screen background color.
	 */
	public static Color getBackgroundColor()
	{
		return Color.BLACK;
	}
	
	/**
	 * Returns the foreground color to be used on most screens.
	 * 
	 * @return	The screen foreground color.
	 */
	public static Color getForegroundColor()
	{
		return Color.WHITE;
	}

	/**
	 * Converts an array of Strings into an HTML-formatted string,
	 * in which each string has become a distinct paragraph of text in the
	 * resulting mark-up.
	 * 
	 * @param lines	The lines of text to convert.
	 * @return	An HTML string that will appropriately render the
	 * 			provided lines of text as paragraphs.
	 */
	public static String linesToHtmlText(String[] lines)
	{
		StringBuilder	text	= new StringBuilder();
		
		text.append("<html><head><style type='text/css'>p { margin-bottom: 20px; }</style><body>");
		
		for (String curLine : lines)
		{
			text.append("<p>" + curLine + "</p>");
		}
		
		text.append("</body></html>");
		
		return text.toString();
	}

	/**
	 * Converts an array of Strings into an HTML-formatted string,
	 * in which each string has become a distinct list item in the resulting
	 * mark-up.
	 * 
	 * @param lines	The lines of text to convert.
	 * @return	An HTML string that will appropriately render the
	 * 			provided lines of text as an ordered list.
	 */
	public static String linesToHtmlList(String[] lines)
	{
		StringBuilder	text	= new StringBuilder();

		text.append("<html><body><ol>");
		
		for (String curLine : lines)
		{
			text.append("<li>" + curLine + "</li>");
		}
		
		text.append("</ol></body></html>");
		
		return text.toString();
	}
}
