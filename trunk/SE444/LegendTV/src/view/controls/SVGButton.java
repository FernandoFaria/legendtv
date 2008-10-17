package view.controls;

import java.awt.Color;
import java.awt.Dimension;
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

@SuppressWarnings("serial")
public class SVGButton
extends JComponent
implements MouseListener
{
	enum ButtonState
	{
		Normal,
		Highlighted,
		Hover,
		Depressed
	}

	private static final String		TEXT_NODE	= "text";
	
	private String					text;

	private ButtonState				state;
	private String[]				imagePaths;
	private Image[]					images;
	
	public SVGButton(String normalImagePath, String highlightImagePath,
	 		 		 String hoverImagePath, String depressedImagePath)
	throws IOException
	{
		this(null, normalImagePath, highlightImagePath, hoverImagePath,
			 depressedImagePath);
	}

	public SVGButton(String text, String normalImagePath,
					 String highlightImagePath, String hoverImagePath,
					 String depressedImagePath)
	throws IOException
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
		
		this.setOpaque(false);
		this.setBackground(Color.BLACK);
				
		this.setupListeners();
		this.switchToNormalState();
	}

	public String getText()
	{
		return text;
	}
	
	public void setText(String text)
	{
		this.text = text;
		
		this.ReRender();
	}
	
	public void addActionListener(ActionListener listener)
	{
		this.listenerList.add(ActionListener.class, listener);
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		Image	activeImage	= this.images[this.state.ordinal()];
		
		super.paintComponent(g); 
		
		g.drawImage(
				activeImage,
				0,
				0,
				this.getWidth(),
				this.getHeight(),
				Color.BLACK,
				null);
	}
	
	@Override
	public void mouseEntered(MouseEvent e)
	{
		this.setState(ButtonState.Hover);
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		this.switchToNormalState();
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		this.setState(ButtonState.Depressed);
	}

	@Override
	public void mouseClicked(MouseEvent arg0)
	{
		this.fireActionEvent();
	}
	
	@Override
	public void mouseReleased(MouseEvent e)
	{
		this.switchToNormalState();
		this.fireActionEvent();
	}

	@Override
	public void setBounds(int x, int y, int width, int height)
	{
		super.setBounds(x, y, width, height);
		
		this.ReRender();
	}
	
	@Override
	public Dimension getPreferredSize()
	{
		return new Dimension(250, 50);
	}

	private void setupListeners()
	{
		this.addMouseListener(this);
	}
	
	private void setState(ButtonState newState)
	{
		if (this.images[newState.ordinal()] == null)
			newState	= ButtonState.Normal;
		
		this.state	= newState;
		
		this.repaint();
	}
	
	private void switchToNormalState()
	{
		if (this.hasFocus())
			this.setState(ButtonState.Highlighted);
		
		else if (this.getMousePosition() != null)
			this.setState(ButtonState.Hover);	
			
		else
			this.setState(ButtonState.Normal);
	}
	
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
	
	private void renderImages()
	throws IOException
	{
		String					parserClassName;
	    SAXSVGDocumentFactory	docFactory;
	    
	    parserClassName	= XMLResourceDescriptor.getXMLParserClassName();
	    docFactory		= new SAXSVGDocumentFactory(parserClassName);

	    for (ButtonState state : EnumSet.allOf(ButtonState.class))
	    {
	    	int	stateNum	= state.ordinal();
	    	
	    	if (this.imagePaths[stateNum] != null)
	    	{	
	    		this.images[stateNum] = this.renderImage(
	    									this.imagePaths[stateNum],
	    									docFactory);
	    	}
	    }
	}
	
	private Image renderImage(String path,
							 SAXSVGDocumentFactory docFactory)
	throws IOException
	{
		String		docUri			= this.toUri(path);
		SVGDocument	document		= docFactory.createSVGDocument(docUri);
		Element		textNode		= document.getElementById(TEXT_NODE);
		UserAgent	userAgent;
		SvgImage	imageRenderer;
		
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
	
	private void fireActionEvent()
	{
		ActionEvent	event	= new ActionEvent(this, 0, this.getText());
		
		for (ActionListener listener :
				this.listenerList.getListeners(ActionListener.class))
		{
			listener.actionPerformed(event);
		}
	}
	
	private String toUri(String path)
	{
		return new File(path).toURI().toString();
	}
	
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