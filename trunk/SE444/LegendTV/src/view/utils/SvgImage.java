package view.utils;

import org.apache.batik.bridge.BridgeContext;
import org.apache.batik.bridge.GVTBuilder;
import org.apache.batik.bridge.UserAgent;
import org.apache.batik.dom.svg.SAXSVGDocumentFactory;
import org.apache.batik.ext.awt.RenderingHintsKeyExt;
import org.apache.batik.gvt.GraphicsNode;
import org.apache.batik.util.XMLResourceDescriptor;
import org.w3c.dom.svg.SVGDocument;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Dimension2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.URI;

/**
 * Convenient utility class to get the Image representation of an SVG resource.
 * 
 * @author Guy Paddock (gap7472@rit.edu)
 * @author Jon Johnson (jjohnson@454.com)
 * @license Public Domain
 * @source http://www.nabble.com/RE:-How-to-load-an-SVG-resource-into-a-java.awt.Image--p2536598.html
 */
public class SvgImage
{
	/**
	 * Root node of the svg document
	 */
	private GraphicsNode 	rootSvgNode;

	/**
	 * Loaded SVG document
	 */
	private SVGDocument 	svgDocument;

	/**
	 * The rendering context.
	 */
	private BridgeContext	context;

	/**
	 * The user agent that will handle displaying rendering feedback to the
	 * user.
	 */
	private UserAgent		userAgent;
	
	/**
	 * Initializes a new instance of SvgImage with the SVG resource located
	 * at the specified local file path.
	 * 
	 * @param path	The file path of the SVG resource.
	 * @throws FileNotFoundException	If no file is found at the specified
	 * 									path.
	 * @throws IOException				If the SVG resource cannot be read.
	 */
	public SvgImage(String path, UserAgent userAgent)
	throws FileNotFoundException, IOException
	{
		File	fileForPath	= new File(path);
		
		if (!fileForPath.exists())
			throw new FileNotFoundException("File not found: " + path);
		
		this.userAgent	= userAgent;
		
		this.loadFromUri(fileForPath.toURI());
	}
	
	/**
	 * Initializes a new instance of SvgImage with the SVG resource
	 * located at the specified URI.
	 * 
	 * @param	uri	location of the SVG resource.
	 * @throws	IOException	If the SVG resource cannot be read.
	 */
	public SvgImage(URI uri, UserAgent userAgent)
	throws IOException
	{
		this.userAgent	= userAgent;
		
		this.loadFromUri(uri);
	}
	
	/**
	 * Loads an SVG resource from a pre-loaded document.
	 * 
	 * @param document	The pre-loaded SVG document resource.
	 */
	public SvgImage(SVGDocument document, UserAgent userAgent)
	{
		this.userAgent	= userAgent;
		
		this.setDocument(document);
	}
	
	/**
	 * Get the svg root node of the document.
	 * 
	 * @return svg root node.
	 */
	public GraphicsNode getRootSvgNode()
	{
		return rootSvgNode;
	}

	/**
	 * Get the svg document.
	 * 
	 * @return the svg document.
	 */
	public SVGDocument getSvgDocument()
	{
		return svgDocument;
	}
	
	/**
	 * Loads an SVG resource from a URI into a document.
	 * 
	 * @param	uri	location of the svg resource.
	 * @throws	IOException	If the svg resource cannot be read.
	 */
	private void loadFromUri(URI uri)
	throws IOException
	{
		String					parser;
		SAXSVGDocumentFactory	factory;
		
		parser				= XMLResourceDescriptor.getXMLParserClassName();
		factory 			= new SAXSVGDocumentFactory(parser);

		this.setDocument((SVGDocument)factory.createDocument(uri.toString()));
	}

	/**
	 * Sets the current SVG document to the provided instance.
	 * 
	 * @param document	The SVG document resource that will be used as this
	 * 					object's current document.
	 */
	private void setDocument(SVGDocument document)
	{
		// Build the tree and get the document dimensions
		GVTBuilder	builder	= new GVTBuilder();
		
		this.svgDocument	= document;
		this.context		= new BridgeContext(this.userAgent);
		this.rootSvgNode	= builder.build(this.context, document);
	}

	/**
	 * Renders and returns the SVG-based image.
	 * 
	 * @param width		Desired width for the rendered image.
	 * @param height	Desired height for the rendered image.
	 * @return The rendered SVG image of the specified dimensions.
	 */
	public Image getImage(int width, int height)
	{
		// Paint svg into image buffer
		BufferedImage	image;
		Graphics2D		g2d;
		
		image	= new BufferedImage(
								width,
								height,
								BufferedImage.TYPE_INT_ARGB);
		
		g2d	= (Graphics2D)image.getGraphics();

		// Fix "Graphics2D from BufferedImage lacks BUFFERED_IMAGE hint" error
		g2d.setRenderingHint(
				RenderingHintsKeyExt.KEY_BUFFERED_IMAGE, new
				WeakReference<BufferedImage>(image)); 
		
		// For a smooth graphic with no jagged edges or rastorized look.
		g2d.setRenderingHint(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		
		g2d.setRenderingHint(
				RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);

		// Scale image to desired size
		g2d.transform(createScaleTransform(width, height));

		// Paint the SVG document
		rootSvgNode.paint(g2d);

		// Cleanup and return image
		g2d.dispose();

		return image;
	}

	/**
	 * Creates an affine transform that will appropriately scale the SVG image
	 * to the specified width and height.
	 * 
	 * @param desiredWidth	The desired width for the final image.
	 * @param desiredHeight	The desired height for the final image.
	 * @return	The appropriate affine transform for scaling the image the
	 * 			specified dimensions.
	 */
	private AffineTransform createScaleTransform(double desiredWidth,
												 double desiredHeight)
	{
		Dimension2D		documentSize;
		double 			imageWidth,
						imageHeight;
		AffineTransform	transform;
		
		documentSize	= this.context.getDocumentSize();
		imageWidth		= documentSize.getWidth();
		imageHeight		= documentSize.getHeight();
		transform		= AffineTransform.getScaleInstance(
							desiredWidth / imageWidth,
							desiredHeight / imageHeight);
		
		return transform;
	}
}