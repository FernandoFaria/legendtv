import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.HeadlessException;

import javax.swing.JFrame;

/**
 * Container window for screens in the LegendTV Swing-based user interface.
 * 
 * @author Guy Paddock (gap7472@rit.edu)
 */
@SuppressWarnings("serial")
public class MainFrame extends JFrame
{
	/**
	 * Constructor for MainFrame.
	 * 
	 * @throws HeadlessException When run in an environment without a display.
	 */
	public MainFrame() throws HeadlessException
	{
		super("LegendTV");
		
		this.setupControls();
	}
	
	/**
	 * Sets-up the controls and layout of this frame.
	 */
	private void setupControls()
	{
		Container	contentPane	= this.getContentPane();

		// Kill the app when closed
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// Maximize with no title bar
		this.setUndecorated(true);
		this.setExtendedState(MAXIMIZED_BOTH);

		// Black & white themeing
		// TODO: Move into theme
		contentPane.setForeground(Color.WHITE);
		contentPane.setBackground(Color.BLACK);
		
		this.setLayout(new BorderLayout());
	}

	/**
	 * Main method for testing.
	 * 
	 * @param args	Command-line arguments (unused).
	 */
	public static void main(String[] args)
	{
		new MainFrame().setVisible(true);
	}
}