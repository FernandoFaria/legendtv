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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * @author Eric
 *
 */
public class ListingGrid extends JComponent implements KeyListener {
	
	private static final Color PROGRAM_BORDER_COLOR = Color.BLACK;
	private static final int GRID_PADDING = 10;
	private static final int TIME_SHIFTS = 2;
	
	public static void main( String[] args ) {
		JFrame window = new JFrame( "GuideView Test" );
		ListingGrid lg = new ListingGrid( 4, 6 );
		ProgramSelectListener psl = new ProgramSelectListener() {
			@Override
			public void programSelected( Channel c, Date d, Program p ) {
				System.out.printf( "Selected %s on %s at %s\n", p, c, d );
			}
		};
		lg.addProgramSelectionListener( psl );
		window.add( lg );
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
	private final List<ProgramSelectListener> selectListenerList = new ArrayList<ProgramSelectListener>();
	
	/* The "View" component of the guide should not worry too much about navigating 
	 * the data model.  Instead, it should deal in simple operations (next channel, next 
	 * program time for channel) and let the model worry about when the next show starts.
	 * The view can merely adjust itself to ensure that the right channels and timeslots are shown.
	 */
	
	public ListingGrid( int channels, int timeSlots ) {
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
		// Set the number of seconds to zero
		now.set( Calendar.SECOND, 0 );
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
	
	public void addProgramSelectionListener( ProgramSelectListener psl ) {
		System.out.println( selectListenerList.add( psl ) );
		Thread.yield();
	}
	
	public void removeProgramSelectionListener( ProgramSelectListener psl ) {
		selectListenerList.remove( psl );
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
			
			// Notify any listeners that we have changed the selected program
			fireSelectionEvent();
		}
	}

	private void channelLeft() {
		if ( selectedTime > 0 ) {
			JComponent curSelection = programs[selectedChannel][selectedTime];
			curSelection.setBackground( null );
			selectedTime -= 1;
			curSelection = programs[selectedChannel][selectedTime];
			curSelection.setBackground( Color.YELLOW );
			
			// Notify any listeners that we have changed the selected program
			fireSelectionEvent();
		}
	}

	private void channelDown() {
		if ( selectedChannel < channels-1 ) {
			JComponent curSelection = programs[selectedChannel][selectedTime];
			curSelection.setBackground( null );
			selectedChannel += 1;
			curSelection = programs[selectedChannel][selectedTime];
			curSelection.setBackground( Color.YELLOW );
			
			// Notify any listeners that we have changed the selected program
			fireSelectionEvent();
		}
	}

	private void channelUp() {
		if ( selectedChannel > 0 ) {
			JComponent curSelection = programs[selectedChannel][selectedTime];
			curSelection.setBackground( null );
			selectedChannel -= 1;
			curSelection = programs[selectedChannel][selectedTime];
			curSelection.setBackground( Color.YELLOW );
			
			// Notify any listeners that we have changed the selected program
			fireSelectionEvent();
		}
	}

	protected void fireSelectionEvent() {
		Channel channel = null;
		Program program = null;
		Date time = null;
		ProgramSelectListener[] ll = selectListenerList.toArray( new ProgramSelectListener[ selectListenerList.size() ] );
		for ( ProgramSelectListener psl : selectListenerList ) {
			if ( program == null ) {
				// There is at least one listener, so initialize the event
				channel = new Channel( (short) (selectedChannel + 1), "Channel "
						+ ( selectedChannel + 1) );
				program = new Program( "Family Guy", "Peter is dumb",
						Program.TV_14, 30 );
				Calendar c = new GregorianCalendar();
				c.setTime( earliestTime );
				c.add( Calendar.MINUTE, 30 * selectedTime );
				time = c.getTime();
			}
			psl.programSelected( channel, time, program );
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
