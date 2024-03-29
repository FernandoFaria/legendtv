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
import java.io.FileNotFoundException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.LayoutFocusTraversalPolicy;

import view.utils.ScreenManager;
import view.utils.UIHelper;

/**
 * @author Eric
 *
 */
public class GuideView extends JComponent implements Screen {

	public static final Color BACKGROUND = Color.BLACK;
	private ListingGrid listingGrid;
	
	public static void main( String[] args ) {
		JFrame frame = new JFrame( "GuideView Test" );
		frame.add( new GuideView( null ) );
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.pack();
		frame.setVisible( true );
	}
	
	public GuideView( ScreenManager screenManager ) {
		this.setLayout( new GridBagLayout() );
		this.setBackground( BACKGROUND );
		
		GridBagConstraints c = new GridBagConstraints();
		// Add the program view to the top left
		c.gridx = 1;
		c.gridwidth = 1;
		c.weightx = 0.6;
		c.gridy = 1;
		c.gridheight = 1;
		c.weighty = 0.3;
		c.fill = GridBagConstraints.BOTH;
		ProgramView programView = new ProgramView( screenManager );
		this.add( programView, c );
		
		// Add the TV preview to the top right
		try {
			URL url = UIHelper.resourcePathToUrl( "images/family-guy.jpg" );
			c.gridx = 2;
			c.gridwidth = 1;
			c.weightx = 0.4;
			c.gridy = 1;
			c.gridheight = 1;
			c.weighty = 0.3;
			c.fill = GridBagConstraints.BOTH;
			JComponent tvView = new JLabel( new ImageIcon( url ) );
			tvView.setBackground( BACKGROUND );
			tvView.setOpaque( true );
			this.add( tvView, c );
		} catch ( FileNotFoundException e ) {
			// Do nothing
		}
		
		// Add the Listing Grid to the bottom half
		c.gridx = 1;
		c.gridwidth = 2;
		c.weightx = 0;
		c.gridy = 2;
		c.gridheight = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		listingGrid = new ListingGrid( 4, 6 );
		listingGrid.addProgramSelectionListener( programView );
		this.add( listingGrid, c );
		
		this.setFocusTraversalPolicyProvider( true );
		this.setFocusTraversalPolicy( new LayoutFocusTraversalPolicy() );
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onResume() {
		listingGrid.requestFocusInWindow();
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		
	}
}
