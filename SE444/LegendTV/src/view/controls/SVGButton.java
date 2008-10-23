package view.controls;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.IOException;
import java.util.EnumSet;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.batik.bridge.UserAgent;
import org.apache.batik.bridge.UserAgentAdapter;
import org.apache.batik.dom.svg.SAXSVGDocumentFactory;
import org.apache.batik.swing.JSVGCanvas;
import org.apache.batik.swing.gvt.GVTTreeRendererAdapter;
import org.apache.batik.swing.gvt.GVTTreeRendererEvent;
import org.apache.batik.swing.svg.SVGDocumentLoaderAdapter;
import org.apache.batik.swing.svg.SVGDocumentLoaderEvent;
import org.apache.batik.swing.svg.SVGUserAgentAdapter;
import org.apache.batik.util.XMLResourceDescriptor;
import org.w3c.dom.Element;
import org.w3c.dom.svg.SVGDocument;

import view.utils.SvgImage;
import view.utils.UIHelper;

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
	
	private JSVGCanvas[]		imageLoaders;
	
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
		int	numStates	= ButtonState.values().length;
		
    	if (normalImagePath == null)
    		throw new IllegalArgumentException("normalImagePath must be set.");
		
		this.text				= text;
		this.images				= new Image[numStates];
		this.imagePaths			= new String[numStates];
		this.imageLoaders		= new JSVGCanvas[numStates];
		
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
		// Move off the event dispatch thread to prevent a deadlock condition
		// with Batik needed a lock on the AWT event thread during rendering.
		(new Thread()
		{
			@Override
			public void run()
			{
				if ((SVGButton.this.getWidth() > 0) &&
					(SVGButton.this.getHeight() > 0))
				{
					try
					{
						SVGButton.this.renderImages();
					}
					
					catch (IOException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				SVGButton.this.repaint();
			}
		}).start();
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
	    		if (this.imageLoaders[stateNum] == null)
	    			initializeLoader(state);
	    		
    			this.renderImage(state);
	    	}
	    }
	}
	
	/**
	 * Initializes a JSVGCanvas which provides SVG image loading functionality.
	 * 
	 * @param state	The button state for which a loader is being initialized.
	 */
	private void initializeLoader(final ButtonState state)
	{
		JSVGCanvas	loader		= new JSVGCanvas(
										new SVGUserAgentAdapter(),
										false,
										false);
		final int	stateNum	= state.ordinal();
		
		loader.addSVGDocumentLoaderListener(
				new SVGDocumentLoaderAdapter()
				{
					@Override
					public void documentLoadingCompleted(
									SVGDocumentLoaderEvent e)
					{
						SVGDocument	document;
						Element		textNode;

					    document	= e.getSVGDocument();
						textNode	= document.getElementById(TEXT_NODE);
						
						if (textNode != null)
							textNode.setTextContent(SVGButton.this.text);

					}
				});
		
		loader.addGVTTreeRendererListener(
			new GVTTreeRendererAdapter()
			{
				@Override
				public void gvtRenderingCompleted(GVTTreeRendererEvent e)
				{
					SVGButton.this.images[stateNum]	= e.getImage();
					
					SVGButton.this.repaint();
				}
			});
		
		this.imageLoaders[stateNum] = loader;
	}
	
	/**
	 * Method called to render a button state image.
	 * 
	 * @param	state	The button state for which an image is being rendered.
	 */
	private void renderImage(ButtonState state)
	throws IOException
	{
		int			stateNum	= state.ordinal();
		JSVGCanvas	loader		= this.imageLoaders[stateNum];
		
		this.images[stateNum] = null;
		
		// Stop any previous processing since we reuse existing loaders.
		loader.stopProcessing();
		
		// Adjust size appropriately.
		loader.setSize(this.getSize());
		
		try
		{
			// Have the appropriate loader re-render the image
			loader.loadSVGDocument(
					UIHelper.resourcePathToUrl(
							this.imagePaths[stateNum]).toString());
		}
		
		catch (NullPointerException ex)
		{
			// TODO: Actually prevent this!
		}
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
		
		testFrame.setLayout(new BorderLayout());
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
		testFrame.setSize(250, 100);
		
		testFrame.add(testBtn, BorderLayout.CENTER);
		
		testFrame.setVisible(true);
	}
}