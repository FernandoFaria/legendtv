/**
 * File: ProgramView.java
 * 
 * Created by Eric Lutley on Oct 3, 2008
 *
 * Version: $Id$
 *
 * Revisions:
 *		$Log$
 */
package view;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * @author Eric
 *
 */
public class ProgramView extends JComponent {

	private final JLabel showTitle;
	private final  JLabel channel;
	private final JLabel ratingIcon;
	private final JLabel channelIcon;
	private final JButton simpleRecord;
	private final JButton advRecord;
	private final JButton watch;
	private final JButton otherShowings;
	private final JButton blockShow;
	private final JButton goBack;
	private final JTextArea description;
	
	public ProgramView() {
		// Create the top of the view, which has the title, channel, and icons
		JPanel leftLabels = new JPanel();
		leftLabels.setLayout( new BoxLayout( leftLabels, BoxLayout.Y_AXIS ) );
		showTitle = new JLabel();
		leftLabels.add( showTitle );
		channel = new JLabel();
		leftLabels.add( channel );
		leftLabels.add( Box.createVerticalGlue() );
		
		Container rightLabels = new JPanel();
		rightLabels.add( Box.createHorizontalGlue() );
		ratingIcon = new JLabel( new ImageIcon( "images/tv_ma.gif" ) );
		ratingIcon.setAlignmentY( JComponent.TOP_ALIGNMENT );
		rightLabels.add(  ratingIcon );
		channelIcon = new JLabel( new ImageIcon( "images/tv_14.jpg" ) );
		channelIcon.setAlignmentY( JComponent.TOP_ALIGNMENT );
		rightLabels.add( channelIcon );
		
		Container topPanel = new JPanel();
		topPanel.setLayout( new BoxLayout( topPanel, BoxLayout.X_AXIS ) );
		topPanel.add( leftLabels );
		topPanel.add( Box.createHorizontalGlue() );
		topPanel.add( rightLabels );
		
		// Create container of action buttons at bottom
		Container buttonPanel = new JPanel( new GridLayout( 1, 0 ) );
		buttonPanel.add( simpleRecord = new JButton("<html>One-Touch<br/>Record</html>") );
		buttonPanel.add( advRecord = new JButton("<html>Advanced<br/>Record</html>" ) );
		buttonPanel.add( watch = new JButton("Watch") );
		buttonPanel.add( otherShowings = new JButton("<html>Other<br/>Showings</html>") );
		buttonPanel.add( blockShow = new JButton("<html>Block<br/>Show</html>") );
		buttonPanel.add( goBack = new JButton( "Go Back" ) );
		
		// Now add these panels to the ProgramView screen
		this.setLayout( new BoxLayout( this, BoxLayout.PAGE_AXIS ) );
		this.add( topPanel );
		
		description = new JTextArea();
		description.setEditable( false );
		this.add( new JScrollPane( description ) );
		
		this.add( buttonPanel );
	}
	
	public void setProgram( Program p, Channel c ) {
		showTitle.setText( p.getTitle() );
		description.setText( p.getDescription() );
		channel.setText( c.toString() );
	}
	
	public static void main( String[] args ) {
		JFrame frame = new JFrame( "Program View Test" );
		ProgramView view = new ProgramView();
		
		view.setProgram( new Program("Family Guy", "Peter is dumb", Program.TV_14, 30 ), 
							new Channel( (short) 6, "Fox" ) );
		
		frame.add( view );
		frame.pack();
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.setVisible( true );
		
		view.setProgram( new Program("CSI:RIT", "An SE major is killed by his team members", Program.TV_MA, 60 ), 
				new Channel( (short) 2, "CBS" ) );
	}
}
