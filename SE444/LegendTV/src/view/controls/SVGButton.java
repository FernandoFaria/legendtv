package view.controls;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.EnumSet;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.batik.bridge.UserAgent;
import org.apache.batik.bridge.UserAgentAdapter;
import org.apache.batik.dom.svg.SAXSVGDocumentFactory;
import org.apache.batik.util.XMLResourceDescriptor;
import org.w3c.dom.Element;
import org.w3c.dom.svg.SVGDocument;

import view.utils.SvgImage;

/**
 * A button that is rendered using Scalable Vector Graphics (SVG).
 * 
 * @author Guy Paddock (gap7472@rit.edu)
 */
@SuppressWarnings("serial")
public class SVGButton
extends JComponent
implements MouseListener
{
	/**
	 * A private enumeration of the possible states of the button.
	 * 
	 * @author Guy Paddock (gap7472@rit.edu)
	 */
	enum ButtonState
	{
		/**
		 * The normal state of the button, in which the mouse is not
		 * over the button and the button does not have focus.
		 */
		Normal,
		
		/**
		 * The state of the button when it has focus.
		 */
		Highlighted,
		
		/**
		 * The state of the button when the mouse button is hovering
		 * over it.
		 */
		Hover,
		
		/**
		 * The state of the button when the mouse button is being
		 * depressed over it.
		 */
		Depressed
	}

	/**
	 * The id of the SVG node that contains button text.
	 */
	private static final String	TEXT_NODE	= "text";
	
	/**
	 * The text displayed on the fact of this button.
	 */
	private String				text;

	/**
	 * The current state of this button.
	 */
	private ButtonState			state;
	
	/**
	 * The paths to each of the images used for the states of this button.
	 */
	private String[]			imagePaths;
	
	/**
	 * The pre-rendered images for each of the states of this button.
	 * These are automatically updated as the size and text of this button
	 * changes.
	 */
	private Image[]				images;
	
	/**
	 * Initializes a new instance of SVG button with the specified
	 * images for the various button states.
	 * 
	 * The normal image path must be specified -- all other paths are optional.
	 * States that are missing images will use the image from the normal
	 * button state.
	 * 
	 * @param normalImagePath		The path to the SVG resource to use for the
	 * 								normal button state.
	 * @param highlightImagePath	The path to the SVG resource to use for the
	 * 								highlighted button state.
	 * @param hoverImagePath		The path to the SVG resource to use for the
	 * 								hover button state.
	 * @param depressedImagePath	The path to the SVG resource to use for the
	 * 								depressed button state.
	 * 
	 * @throws IllegalArgumentException	If normalImagePath is null.
	 */
	public SVGButton(String normalImagePath, String highlightImagePath,
	 		 		 String hoverImagePath, String depressedImagePath)
	throws IOException, IllegalArgumentException
	{
		this(null, normalImagePath, highlightImagePath, hoverImagePath,
			 depressedImagePath);
	}

	/**
	 * Initializes a new instance of SVG button with the specified
	 * images for the various button states.
	 * 
	 * The normal image path must be specified -- all other paths are optional.
	 * States that are missing images will use the image from the normal
	 * button state.
	 * 
	 * @param text					The text to display on the face of this
	 * 								button.
	 * @param normalImagePath		The path to the SVG resource to use for the
	 * 								normal button state.
	 * @param highlightImagePath	The path to the SVG resource to use for the
	 * 								highlighted button state.
	 * @param hoverImagePath		The path to the SVG resource to use for the
	 * 								hover button state.
	 * @param depressedImagePath	The path to the SVG resource to use for the
	 * 								depressed button state.
	 * 
	 * @throws IllegalArgumentException	If normalImagePath is null.
	 */
	public SVGButton(String text, String normalImagePath,
					 String highlightImagePath, String hoverImagePath,
					 String depressedImagePath)
	throws IllegalArgumentException
	{
    	if (normalImagePath == null)
    		throw new IllegalArgumentException("normalImagePath must be set.");
		
		this.text				= text;
		this.images				= new Image[ButtonState.values().length];
		this.imagePaths			= new String[ButtonState.values().length];
		
		this.imagePaths[ButtonState.Normal.ordinal()]		= normalImagePath;
		this.imagePaths[ButtonState.Highlighted.ordinal()]	= highlightImagePath;
		this.imagePaths[ButtonState.Hover.ordinal()]		= hoverImagePath;
		this.imagePaths[ButtonState.Depressed.ordinal()]	= depressedImagePath;
		
		this.setOpaque(true);
		this.setBackground(Color.BLACK);
				
		this.setupListeners();
		this.switchToNormalState();
	}

	/**
	 * Gets the text currently being displayed on this button.
	 * @return
	 */
	public String getText()
	{
		return text;
	}
	
	/**
	 * Sets the text to display on the face of this button.
	 * 
	 * @param text	The new text for this button.
	 */
	public void setText(String text)
	{
		this.text = text;
		
		this.ReRender();
	}
	
	/**
	 * Adds an action listener, which will be informed when this button
	 * is activated.
	 * 
	 * @param listener	The new listener to add.
	 */
	public void addActionListener(ActionListener listener)
	{
		this.listenerList.add(ActionListener.class, listener);
	}
	
	/**
	 * Overrides the default JComponent paint code to paint this
	 * component.
	 */
	@Override
	protected void paintComponent(Graphics g)
	{
		Image	activeImage	= this.images[this.state.ordinal()];
		
		super.paintComponent(g);
		
		g.drawImage(activeImage, 0, 0, this.getBackground(), null);
	}
	
	/**
	 * Handles the event when the mouse enters the bounds of this button.
	 */
	@Override
	public void mouseEntered(MouseEvent e)
	{
		this.setState(ButtonState.Hover);
	}

	/**
	 * Handles the event when the mouse leaves the bounds of this button.
	 */
	@Override
	public void mouseExited(MouseEvent e)
	{
		this.switchToNormalState();
	}

	/**
	 * Handles the event when the user presses the mouse button down on this
	 * button.
	 */
	@Override
	public void mousePressed(MouseEvent e)
	{
		if (e.getButton() == MouseEvent.BUTTON1)
			this.setState(ButtonState.Depressed);
	}

	/**
	 * Handles the event when the user quickly clicks this button.
	 * This event handler does nothing, but is required by the interface.
	 */
	@Override
	public void mouseClicked(MouseEvent arg0)
	{
	}
	
	/**
	 * Handles the event when the user releases the mouse button over this
	 * button.
	 */
	@Override
	public void mouseReleased(MouseEvent e)
	{
		this.switchToNormalState();

		if (e.getButton() == MouseEvent.BUTTON1)
			this.fireActionEvent();
	}

	/**
	 * Overrides the setBounds method to ensure that the images for
	 * this button's states are re-rendered on resize.
	 */
	@Override
	public void setBounds(int x, int y, int width, int height)
	{
		super.setBounds(x, y, width, height);
		
		this.ReRender();
	}
	
	/**
	 * Sets-up the appropriate listeners for this button.
	 */
	private void setupListeners()
	{
		this.addMouseListener(this);
	}
	
	/**
	 * Sets the state of this button.
	 * 
	 * @param newState	The new button state.
	 * @see	ButtonState
	 */
	private void setState(ButtonState newState)
	{
		if (this.images[newState.ordinal()] == null)
			newState	= ButtonState.Normal;
		
		this.state	= newState;
		
		this.repaint();
	}
	
	/**
	 * Method called to switch the button to its normal state,
	 * depending on its focus and the position of the mouse.
	 */
	private void switchToNormalState()
	{
		if (this.hasFocus())
			this.setState(ButtonState.Highlighted);
		
		else if (this.getMousePosition() != null)
			this.setState(ButtonState.Hover);	
			
		else
			this.setState(ButtonState.Normal);
	}
	
	/**
	 * Method called to re-render the button's information in response to size
	 * or text changes.
	 */
	private void ReRender()
	{
		try
		{
			if ((this.getWidth() > 0) && (this.getHeight() > 0))
				this.renderImages();
		
			this.repaint();
		}
		
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Method called to re-render all of the state images in response to
	 * size or text changes.
	 * 
	 * @throws IOException	If an error occurs while reading an SVG resource.
	 */
	private void renderImages()
	throws IOException
	{
	    for (ButtonState state : EnumSet.allOf(ButtonState.class))
	    {
	    	int	stateNum	= state.ordinal();
	    	
	    	if (this.imagePaths[stateNum] != null)
	    	{	
	    		this.images[stateNum] = this.renderImage(
	    									this.imagePaths[stateNum]);
    		}
	    }
	}
	
	/**
	 * Method called to render a button state image from the SVG resource
	 * at the specified file path.
	 * 
	 * @param	path	The file path of the SVG resource to render into
	 * 					an image.
	 * @return			The rendered button state image.
	 */
	private Image renderImage(String path)
	throws IOException
	{
		String					docUri			= this.toUri(path),
								parserClassName;
		SAXSVGDocumentFactory	docFactory;
		SVGDocument				document;
		Element					textNode;
		UserAgent				userAgent;
		SvgImage				imageRenderer;

	    parserClassName	= XMLResourceDescriptor.getXMLParserClassName();
	    docFactory		= new SAXSVGDocumentFactory(parserClassName);
	    document		= docFactory.createSVGDocument(docUri);
		textNode		= document.getElementById(TEXT_NODE);
		
		if (textNode != null)
			textNode.setTextContent(this.text);
		
		userAgent	= new UserAgentAdapter()
		{
			@Override
			public void displayError(Exception e)
			{
				e.printStackTrace();
			}
		};
		
		imageRenderer	= new SvgImage(document, userAgent);
		
		return (imageRenderer.getImage(this.getWidth(), this.getHeight()));
	}

	/**
	 * Method called to inform all action listeners that this button
	 * has been activated.
	 */
	private void fireActionEvent()
	{
		ActionEvent	event	= new ActionEvent(this, 0, this.getText());
		
		for (ActionListener listener :
				this.listenerList.getListeners(ActionListener.class))
		{
			listener.actionPerformed(event);
		}
	}
	
	/**
	 * Private utility method for converting paths to URIs for Batik.
	 * 
	 * @param path	The file path.
	 * @return		The URI string corresponding to the provided file path.
	 */
	private String toUri(String path)
	{
		// Prefix a slash so that paths are relative to project root, not class
		return this.getClass().getResource("/" + path).toString();
	}
	
	/**
	 * Main method used for testing.
	 * 
	 * @param args	Command-line arguments (unused).
	 */
	public static void main(String[] args) throws IOException
	{
		JFrame		testFrame	= new JFrame();
		SVGButton	testBtn		= new SVGButton(
										"Test",
										"images/button_normal.svg",
										"images/button_highlight.svg",
										"images/button_hover.svg",
										"images/button_down.svg");
		
		testBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				JOptionPane.showMessageDialog(null, "You clicked the button!");
			}
		});
		
		testFrame.setBackground(Color.BLACK);
		testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		testFrame.setTitle("Test");
		testFrame.setSize(300, 300);
		
		testFrame.add(testBtn);
		
		testFrame.setVisible(true);
	}
}