/**
 * File: GuideView.java
 * 
 * Created by Eric Lutley on Sep 29, 2008
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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * @author Eric
 *
 */
public class GuideView extends JComponent implements KeyListener {
	
	private static final Color PROGRAM_BORDER_COLOR = Color.BLACK;
	private static final int GRID_PADDING = 10;
	private static final int TIME_SHIFTS = 2;
	
	public static void main( String[] args ) {
		JFrame window = new JFrame( "GuideView Test" );
		window.add(  new GuideView( 4, 6 ) );
		window.pack();
		window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		window.setVisible( true );
	}
	
	private final int channels;
	private final int timeSlots;
	private int selectedChannel = 1;
	private int selectedTime = 1;
	private JComponent[][] programs;
	private Date earliestTime;
	private int lowestChannel;
	private JLabel[] channelLabels;
	private JLabel[] timeSlotLabels;
	
	/* The "View" component of the guide should not worry too much about navigating 
	 * the data model.  Instead, it should deal in simple operations (next channel, next 
	 * program time for channel) and let the model worry about when the next show starts.
	 * The view can merely adjust itself to ensure that the right channels and timeslots are shown.
	 */
	
	public GuideView( int channels, int timeSlots ) {
		this.channels = channels;
		this.timeSlots = timeSlots;
		
		this.setLayout( new GridBagLayout() );
		
		GridBagConstraints c = new GridBagConstraints();
		channelLabels = new JLabel[ channels ];
		c.gridx = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.ipadx = 0;
		c.ipady = GRID_PADDING;
		c.weightx = 0;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		for ( int cy = 0; cy < channels; ++cy ) {
			c.gridy = 2 + cy;	// Grid (1,1) is empty-top-left corner
			channelLabels[ cy ] = new JLabel( "Channel " + (cy+1) );
			channelLabels[ cy ].setBorder( BorderFactory.createRaisedBevelBorder() );
			this.add( channelLabels[ cy ], c );
		}
		
		timeSlotLabels = new JLabel[ timeSlots ];
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.ipadx = GRID_PADDING;
		c.ipady = 0;
		c.weightx = 1;
		c.weighty = 0;
		c.fill = GridBagConstraints.BOTH;
		Calendar now = new GregorianCalendar();
		int timePastHalf = now.get( Calendar.MINUTE ) % 30;
		now.add( Calendar.MINUTE, -timePastHalf );
		DateFormat timeFormat = DateFormat.getTimeInstance( DateFormat.SHORT );
		this.earliestTime = now.getTime();
		for ( int tx = 0; tx < timeSlots; ++tx ) {
			c.gridx = 2 + tx;	// Grid (1,1) is empty-top-left corner
			String timeStr = timeFormat.format( now.getTime() );
			timeSlotLabels[ tx ] = new JLabel( timeStr );
			timeSlotLabels[ tx ].setBorder( BorderFactory.createRaisedBevelBorder() );
			timeSlotLabels[ tx ].setHorizontalAlignment( JLabel.CENTER );
			this.add( timeSlotLabels[ tx ], c );
			// Update the time
			now.add( Calendar.MINUTE, 30 );
		}
		
		// Populate guide with dummy programs
		c.gridwidth = 1;
		c.gridheight = 1;
		c.ipadx = 0;
		c.ipady = 0;
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		programs = new JComponent[channels][timeSlots];
		for ( int chan = 0; chan < channels; ++chan ) {
			c.gridy = 2 + chan;
			for ( int time = 0; time < timeSlots; ++time ) {
				c.gridx = 2 + time;
				// Create the program
				JLabel program = new JLabel( "No information available" );
				program.setHorizontalAlignment( JLabel.CENTER );
				program.setOpaque( true );
				program.setBorder( BorderFactory.createLineBorder( PROGRAM_BORDER_COLOR ) );
				this.add( program, c );
				programs[chan][time] = program;
			}
		}
		
		// Add keylistener to base component
		this.setFocusable( true );
		this.addKeyListener( this );
		this.requestFocusInWindow();
	}
	
	public void setSelectedChannel( int channel ) {
		this.selectedChannel = channel;
	}
	
	public void setSelectedTime( int time ) {
		this.selectedTime = time;
	}
	
	@Override
	public void keyPressed( KeyEvent arg0 ) {
		// TODO Auto-generated method stub
		switch ( arg0.getKeyCode() ) {
		case KeyEvent.VK_UP:
			channelUp();
			break;
		case KeyEvent.VK_DOWN:
			channelDown();
			break;
		case KeyEvent.VK_LEFT:
			channelLeft();
			break;
		case KeyEvent.VK_RIGHT:
			channelRight();
			break;
		}
	}

	private void channelRight() {
		if ( selectedTime < timeSlots-1 ) {
			JComponent curSelection = programs[selectedChannel][selectedTime];
			curSelection.setBackground( null );
			selectedTime += 1;
			curSelection = programs[selectedChannel][selectedTime];
			curSelection.setBackground( Color.YELLOW );
		}
	}

	private void channelLeft() {
		JComponent curSelection = programs[selectedChannel][selectedTime];
		curSelection.setBackground( null );
		if ( selectedTime > 0 ) {
			selectedTime -= 1;
			curSelection = programs[selectedChannel][selectedTime];
			curSelection.setBackground( Color.YELLOW );
		}
	}

	private void redrawGuide() {
		// Create timeslot labels
		Calendar now = new GregorianCalendar();
		now.setTime( earliestTime );
		int timePastHalf = now.get( Calendar.MINUTE ) % 30;
		now.add( Calendar.MINUTE, -timePastHalf );
		DateFormat timeFormat = DateFormat.getTimeInstance( DateFormat.SHORT );
		for ( int tx = 0; tx < timeSlots; ++tx ) {
			String timeStr = timeFormat.format( now.getTime() );
			timeSlotLabels[ tx ].setText( timeStr );
			// Update the time
			now.add( Calendar.MINUTE, 30 );
		}
		
		// Populate guide with dummy programs
		GridBagConstraints c = new GridBagConstraints();
		c.gridwidth = 1;
		c.gridheight = 1;
		c.ipadx = 0;
		c.ipady = 0;
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		programs = new JComponent[channels][timeSlots];
		for ( int chan = 0; chan < channels; ++chan ) {
			c.gridy = 2 + chan;
			for ( int time = 0; time < timeSlots; ++time ) {
				c.gridx = 2 + time;
				// Create the program
				JLabel program = new JLabel( "No information available" );
				program.setHorizontalAlignment( JLabel.CENTER );
				program.setOpaque( true );
				program.setBorder( BorderFactory.createLineBorder( PROGRAM_BORDER_COLOR ) );
				this.add( program, c );
				programs[chan][time] = program;
			}
		}
		
		// Populate guide with dummy programs
		c.gridwidth = 1;
		c.gridheight = 1;
		c.ipadx = 0;
		c.ipady = 0;
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		programs = new JComponent[channels][timeSlots];
		for ( int chan = 0; chan < channels; ++chan ) {
			c.gridy = 2 + chan;
			for ( int time = 0; time < timeSlots; ++time ) {
				c.gridx = 2 + time;
				// Create the program
				JLabel program = new JLabel( "No information available" );
				program.setHorizontalAlignment( JLabel.CENTER );
				program.setOpaque( true );
				program.setBorder( BorderFactory.createLineBorder( PROGRAM_BORDER_COLOR ) );
				this.add( program, c );
				programs[chan][time] = program;
			}
		}
	}

	private void channelDown() {
		if ( selectedChannel < channels-1 ) {
			JComponent curSelection = programs[selectedChannel][selectedTime];
			curSelection.setBackground( null );
			selectedChannel += 1;
			curSelection = programs[selectedChannel][selectedTime];
			curSelection.setBackground( Color.YELLOW );
		}
	}

	private void channelUp() {
		if ( selectedChannel > 0 ) {
			JComponent curSelection = programs[selectedChannel][selectedTime];
			curSelection.setBackground( null );
			selectedChannel -= 1;
			curSelection = programs[selectedChannel][selectedTime];
			curSelection.setBackground( Color.YELLOW );
		}
	}

	@Override
	public void keyReleased( KeyEvent arg0 ) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyTyped( KeyEvent arg0 ) {
		// TODO Auto-generated method stub
	}
}
