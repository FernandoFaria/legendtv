import java.awt.BorderLayout;
import java.net.URISyntaxException;

import javax.swing.JFrame;

import org.apache.batik.swing.JSVGCanvas;

public class SvgTest extends JFrame
{
	private JSVGCanvas	svgCanvas;
	
	public SvgTest() throws URISyntaxException
	{
		this.setTitle("SVG Test Application");
		this.setBounds(10, 10, 672, 70);
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.svgCanvas	= new JSVGCanvas();
		this.svgCanvas.setURI("file:///C:/Users/Guy/Documents/Eclipse%20Workspace/LegendTV%20(New)/images/vistamenu.svg");
				
		this.add(this.svgCanvas, BorderLayout.CENTER);
	}
	
	/**
	 * @param args
	 * @throws URISyntaxException 
	 */
	public static void main(String[] args) throws URISyntaxException
	{
		new SvgTest().setVisible(true);
	}
}
