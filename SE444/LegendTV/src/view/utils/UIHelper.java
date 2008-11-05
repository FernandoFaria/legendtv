package view.utils;

import java.awt.AWTKeyStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import view.controls.HorizontalSpinner;
import view.controls.SVGButton;

/**
 * A utility class for providing functionality that is commonly used by the
 * UI for laying out controls and content.
 * 
 * @author Guy Paddock (gap7472@rit.edu)
 */
public class UIHelper
{
	/**
	 * The standard amount of space to put between components on screens.
	 */
	public static final int STANDARD_MARGIN = 50;
	
	/**
	 * Returns the font to be used for screen headings.
	 * 
	 * @return	A font object with the appropriate properties set for
	 * 			use on headings.
	 */
	public static Font getHeadingFont()
	{
		return new Font("Segoe UI", Font.PLAIN, 36);
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
	 * Creates a standard label. 
	 * 
	 * @param	text	The text to put into the label.
	 * @return			A new JLabel initialized with the provided text.
	 */
	public static JLabel createLabel(String text)
	{
		JLabel	label	= new JLabel(text);
		
		label.setForeground(UIHelper.getForegroundColor());
		label.setFont(UIHelper.getBodyFont());
		
		return label;
	}
	
	/**
	 * Creates a standard, scalable button with the specified caption and
	 * action.
	 * 
	 * @param caption	The caption to display on the specified button.
	 * @param action	The action to perform, if any, when the button is
	 * 					activated.
	 * @return			A new SVGButton instance setup with the provided
	 * 					caption and action.
	 */
	public static SVGButton createButton(String caption, ActionListener action)
	{
		SVGButton	button	= null;
		
		button = new SVGButton(
						caption,
						"images/button_normal.svg",
						"images/button_highlight.svg",
						"images/button_hover.svg",
						"images/button_down.svg");
		
		if (action != null)
			button.addActionListener(action);
		
		return button;
	}
	
	/**
	 * Creates a common horizontal spin control.
	 * 
	 * @param options	The options to support in the control.
	 * 
	 * @return	The new HorizontalSpinner control instance.
	 */
	public static HorizontalSpinner createHorizontalSpinner(String[] options)
	{
		HorizontalSpinner	retVal	= null;
		
		try
		{
			ImageIcon	leftArrow,
						rightArrow;
			
			leftArrow	= new ImageIcon(
								UIHelper.resourcePathToUrl(
										"images/left_arrow.png"));
	
			rightArrow	= new ImageIcon(
								UIHelper.resourcePathToUrl(
										"images/right_arrow.png"));
			
			retVal	= new HorizontalSpinner(leftArrow, options, rightArrow);
		}
		
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}		
		
		return retVal;
	}
	
	/**
	 * Converts a relative resource path to an absolute URI that is safe for use
	 * both inside and outside JAR files.
	 * 
	 * @param path	The relative resource file path.
	 * @return		The URI corresponding to the provided file path.
	 * @throws	FileNotFoundException	If the specified resource file does
	 * 									not exist.
	 */
	public static URL resourcePathToUrl(String path)
	throws FileNotFoundException
	{
		// Prefix a slash so that paths are relative to project root, not class
		URL	resource	= UIHelper.class.getResource("/" + path);
		
		if (resource == null)
			throw new FileNotFoundException("Resource file not found: /" + path);
		
		return resource;
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
	
//	public static void removeArrowKeyFocusMappings()
//	{
//		KeyboardFocusManager	focusMgr;
//		Set<AWTKeyStroke>		backwardKeys,
//								forwardKeys,
//								upKeys,
//								downKeys;
//		
//		focusMgr = KeyboardFocusManager.getCurrentKeyboardFocusManager();
//		
//		backwardKeys	= new HashSet<AWTKeyStroke>(
//								focusMgr.getDefaultFocusTraversalKeys(
//										KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS));
//		
//		forwardKeys		= new HashSet<AWTKeyStroke>(
//								focusMgr.getDefaultFocusTraversalKeys(
//										KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
//		
//		upKeys			= new HashSet<AWTKeyStroke>(
//								focusMgr.getDefaultFocusTraversalKeys(
//										KeyboardFocusManager.UP_CYCLE_TRAVERSAL_KEYS));
//		
//		downKeys		= new HashSet<AWTKeyStroke>(
//								focusMgr.getDefaultFocusTraversalKeys(
//										KeyboardFocusManager.DOWN_CYCLE_TRAVERSAL_KEYS));
//
//		backwardKeys.remove(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_LEFT, 0));
//		forwardKeys.remove(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_RIGHT, 0));
//		upKeys.remove(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_UP, 0));
//		downKeys.remove(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_DOWN, 0));
//		
//		focusMgr.setDefaultFocusTraversalKeys(
//				KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, backwardKeys);
//		
//		focusMgr.setDefaultFocusTraversalKeys(
//				KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, forwardKeys);
//		
//		focusMgr.setDefaultFocusTraversalKeys(
//				KeyboardFocusManager.UP_CYCLE_TRAVERSAL_KEYS, upKeys);
//		
//		focusMgr.setDefaultFocusTraversalKeys(
//				KeyboardFocusManager.DOWN_CYCLE_TRAVERSAL_KEYS, downKeys);
//	}
//	
//	public static void addArrowKeyFocusMappings()
//	{
//		KeyboardFocusManager	focusMgr;
//		Set<AWTKeyStroke>		backwardKeys,
//								forwardKeys,
//								upKeys,
//								downKeys;
//		
//		focusMgr = KeyboardFocusManager.getCurrentKeyboardFocusManager();
//		
//		backwardKeys	= new HashSet<AWTKeyStroke>(
//								focusMgr.getDefaultFocusTraversalKeys(
//										KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS));
//		
//		forwardKeys		= new HashSet<AWTKeyStroke>(
//								focusMgr.getDefaultFocusTraversalKeys(
//										KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
//		
//		upKeys			= new HashSet<AWTKeyStroke>(
//								focusMgr.getDefaultFocusTraversalKeys(
//										KeyboardFocusManager.UP_CYCLE_TRAVERSAL_KEYS));
//		
//		downKeys		= new HashSet<AWTKeyStroke>(
//								focusMgr.getDefaultFocusTraversalKeys(
//										KeyboardFocusManager.DOWN_CYCLE_TRAVERSAL_KEYS));
//
//		backwardKeys.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_LEFT, 0));
//		forwardKeys.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_RIGHT, 0));
//		upKeys.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_UP, 0));
//		downKeys.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_DOWN, 0));
//		
//		focusMgr.setDefaultFocusTraversalKeys(
//				KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, backwardKeys);
//		
//		focusMgr.setDefaultFocusTraversalKeys(
//				KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, forwardKeys);
//		
//		focusMgr.setDefaultFocusTraversalKeys(
//				KeyboardFocusManager.UP_CYCLE_TRAVERSAL_KEYS, upKeys);
//		
//		focusMgr.setDefaultFocusTraversalKeys(
//				KeyboardFocusManager.DOWN_CYCLE_TRAVERSAL_KEYS, downKeys);
//	}
}
