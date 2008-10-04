/**
 * File: GuideView.java
 * 
 * Created by Eric Lutley on Oct 3, 2008
 *
 * Version: $Id$
 *
 * Revisions:
 *		$Log$
 */
package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Eric
 *
 */
public class GuideView extends JComponent {

	public static void main( String[] args ) {
		JFrame frame = new JFrame( "GuideView Test" );
		frame.add( new GuideView() );
		
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.pack();
		frame.setVisible( true );
	}
	
	public GuideView() {
		this.setLayout( new GridBagLayout() );
		
		GridBagConstraints c = new GridBagConstraints();
		// Add the program view to the top left
		c.gridx = 1;
		c.gridwidth = 1;
		c.weightx = 0.6;
		c.gridy = 1;
		c.gridheight = 1;
		c.weighty = 0.3;
		c.fill = GridBagConstraints.BOTH;
		ProgramView programView = new ProgramView();
		this.add( programView, c );
		
		// Add the TV preview to the top right
		c.gridx = 2;
		c.gridwidth = 1;
		c.weightx = 0.4;
		c.gridy = 1;
		c.gridheight = 1;
		c.weighty = 0.3;
		c.fill = GridBagConstraints.BOTH;
		JComponent tvView = new JLabel( new ImageIcon( "images/family-guy.jpg" ) );
		this.add( tvView, c );
		
		// Add the Listing Grid to the bottom half
		c.gridx = 1;
		c.gridwidth = 2;
		c.weightx = 0;
		c.gridy = 2;
		c.gridheight = 1;
		c.weighty = 0.7;
		c.fill = GridBagConstraints.BOTH;
		ListingGrid listingGrid = new ListingGrid( 4, 6 );
		this.add( listingGrid, c );
	}
}
