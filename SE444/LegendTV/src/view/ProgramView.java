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

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Date;

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

import view.controls.LButton;
import view.controls.SVGButton;
import view.utils.ScreenManager;
import view.utils.UIHelper;

/**
 * @author Eric
 *
 */
public class ProgramView extends JComponent implements ProgramSelectListener {

	private static final Color BACKGROUND = Color.BLACK;
	private static final Color FONT_COLOR = Color.WHITE;
	
	private final JLabel showTitle;
	private final  JLabel channel;
	private final JLabel ratingIcon;
	private final JLabel channelIcon;
	private final JButton simpleRecord;
	private final JButton advRecord;
	private final JButton watch;
	private final JButton otherShowings;
	private final JButton blockShow;
	private final SVGButton goBack;
	private final JTextArea description;
	private Program program;
	private final ScreenManager screenManager;
	
	public ProgramView( ScreenManager screenManager ) {
		this.screenManager = screenManager;
		this.setBackground( BACKGROUND );
		
		
		// Create the top of the view, which has the title, channel, and icons
		JPanel leftLabels = new JPanel();
		leftLabels.setBackground( BACKGROUND );
		leftLabels.setLayout( new BoxLayout( leftLabels, BoxLayout.Y_AXIS ) );
		showTitle = new JLabel();
		showTitle.setFont( new Font( "Arial", Font.BOLD, 24 ) );
		showTitle.setForeground( FONT_COLOR );
		leftLabels.add( showTitle );
		channel = new JLabel();
		channel.setFont( new Font( "Arial", Font.BOLD, 16 ) );
		channel.setForeground( FONT_COLOR );
		leftLabels.add( channel );
		leftLabels.add( Box.createVerticalGlue() );
		
		Container rightLabels = new JPanel();
		rightLabels.setBackground( BACKGROUND );
		rightLabels.add( Box.createHorizontalGlue() );
		ratingIcon = new JLabel();
		try {
			ratingIcon.setIcon( new ImageIcon( UIHelper.resourcePathToUrl( "images/tv_14.jpg" ) ) );
		} catch ( FileNotFoundException e ) {
			// Don't set an image for the rating
		}
		ratingIcon.setAlignmentY( JComponent.TOP_ALIGNMENT );
		rightLabels.add(  ratingIcon );
		channelIcon = new JLabel();
		try {
			channelIcon.setIcon( new ImageIcon( UIHelper.resourcePathToUrl( "images/fox_logo.gif" ) ) );
		} catch ( FileNotFoundException e ) {
			// Don't set any image for the channel
		}
		channelIcon.setAlignmentY( JComponent.TOP_ALIGNMENT );
		rightLabels.add( channelIcon );
		
		Container topPanel = new JPanel();
		topPanel.setBackground( BACKGROUND );
		topPanel.setLayout( new BoxLayout( topPanel, BoxLayout.X_AXIS ) );
		topPanel.add( leftLabels );
		topPanel.add( Box.createHorizontalGlue() );
		topPanel.add( rightLabels );
		
		// Create container of action buttons at bottom
		Container buttonPanel = new JPanel( new GridLayout( 1, 0 ) );
		buttonPanel.add( simpleRecord = new LButton("<html>One-Touch<br/>Record</html>") );
		advRecord = new LButton("<html>Advanced<br/>Record</html>" );
		advRecord.addActionListener( new AdvancedRecordingListener() );
		buttonPanel.add( advRecord );
		buttonPanel.add( watch = new LButton("Watch") );
		buttonPanel.add( otherShowings = new LButton("<html>Other<br/>Showings</html>") );
		buttonPanel.add( blockShow = new LButton("<html>Block<br/>Show</html>") );
		buttonPanel.add( goBack = UIHelper.createButton( "Go Back", 
				new ActionListener() {
					@Override
					public void actionPerformed( ActionEvent e ) {
						ProgramView.this.screenManager.back();
					}
		}) );
		
		// Now add these panels to the ProgramView screen
		this.setLayout( new GridBagLayout() );
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		this.add( topPanel, c );
		
		description = new JTextArea();
		description.setEditable( false );
		description.setForeground( Color.WHITE );
		description.setBackground( Color.DARK_GRAY );
		description.setLineWrap( true );
		c.gridy = 2;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		this.add( new JScrollPane( description ), c );
		
		c.gridy = 3;
		c.weighty = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		this.add( buttonPanel, c );
		
		//FIXME: Remove this once the program actually works
		setProgram( new Program( "Family Guy", 
								"Family Guy is an animated American television sitcom created by " +
								"Seth MacFarlane that airs on Fox and regularly on other television " +
								"networks in syndication. The show centers on a semi-dysfunctional family " +
								"that lives in the fictional town of Quahog, Rhode Island. The show uses " +
								"frequent \"cutaway gags\", jokes in the form of tangential vignettes", 
								Program.TV_14, 30 ), 
					new Channel( (short) 6, "Fox" ) );
	}
	
	public void setProgram( Program p, Channel c ) {
		program = p;
		showTitle.setText( p.getTitle() );
		description.setText( p.getDescription() );
		channel.setText( c.toString() );
		ratingIcon.setIcon( p.getRatingIcon() );
		
	}
	
	public static void main( String[] args ) {
		JFrame frame = new JFrame( "Program View Test" );
		ProgramView view = new ProgramView( null );
		
		view.setProgram( new Program("Family Guy", "Peter is dumb", Program.TV_14, 30 ), 
							new Channel( (short) 6, "Fox" ) );
		
		frame.add( view );
		frame.pack();
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.setVisible( true );
		
		view.setProgram( new Program("CSI:RIT", "An SE major is killed by his team members", Program.TV_MA, 60 ), 
				new Channel( (short) 2, "CBS" ) );
	}

	@Override
	public void programSelected( Channel channel, Date time, Program program ) {
		this.setProgram( program, channel );
	}
	
	private class AdvancedRecordingListener implements ActionListener {
		@Override
		public void actionPerformed( ActionEvent e ) {
			if ( program != null ) {
				screenManager.show( 
						new RecordingOptionsGUI( screenManager, program ) );
				ProgramView.this.getParent().requestFocusInWindow();
			}
		}
	}
}
