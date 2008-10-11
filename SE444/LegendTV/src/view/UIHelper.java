package view;

import java.awt.Color;
import java.awt.Font;


public class UIHelper
{
	public static Font getHeadingFont()
	{
		return new Font("Segoe UI", Font.PLAIN, 56);
	}
	
	public static Font getBodyFont()
	{
		return new Font("Segoe UI", Font.PLAIN, 24);
	}
	
	public static Color getBackgroundColor()
	{
		return Color.BLACK;
	}
	
	public static Color getForegroundColor()
	{
		return Color.WHITE;
	}

	public static String linesToHtmlText(String[] lines)
	{
		StringBuilder	text	= new StringBuilder();
		
		// FIXME
		text.append("<html><head><style type='text/css'>p { margin-bottom: 20px; }</style><body>");
		
		for (String curLine : lines)
		{
			text.append("<p>" + curLine + "</p>");
		}
		
		text.append("</body></html>");
		
		return text.toString();
	}

	public static String linesToHtmlList(String[] lines)
	{
		StringBuilder	text	= new StringBuilder();
		
		// FIXME
		text.append("<html><body><ol>");
		
		for (String curLine : lines)
		{
			text.append("<li>" + curLine + "</li>");
		}
		
		text.append("</ol></body></html>");
		
		return text.toString();
	}
}
