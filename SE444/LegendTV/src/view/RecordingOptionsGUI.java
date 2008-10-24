package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import view.controls.HorizontalSpinner;
import view.controls.LButton;
import view.utils.ScreenManager;
import view.utils.UIHelper;

/*
 * Version: $Id$
 * 
 * Revisions $Log$
 */

/**
 * WARNING!!! THIS CLASS IS FAR FROM COMPLETE.  Code needs to be re-factored for 
 * better flexibility and understandability 
 * 
 * This class displays the options for a recorded TV Show.
 *
 * @author  Andrew Bona
 */
public class RecordingOptionsGUI extends JPanel implements KeyListener, ActionListener{

	private JPanel optionsPanel, buttonPanel, screenLabelPanel;
	private JLabel screenLabel, showLabel, repeatLabel, qualityLabel, expirationLabel;
	private JLabel repeatOptions, qualityOptions, expirationOptions;
	private HorizontalSpinner repeatSpinner, qualitySpinner, expirationSpinner;
	
	// The Screen Manager allows us to move back to the previous screen.
	private final ScreenManager screenManager;
	
	//JButtons
	private JButton confirmButton, cancelButton;
	
	//private Insets in = new Insets(0,0,0,0);
	
	private Icon buttonLeftArrow, buttonRightArrow;
	private final Program program;
	
	/**
	 * This code will be cleaned up at a later date.  The main functionality of this code has been split up
	 * by carriage returns to show differences in functionality.  Other methods will be created to reduce
	 * the "God" Constructor.
	 * 
	 * 
	 * @param show  This parameter is not a String, but will be of type "Recorded Program" object
	 */
	public RecordingOptionsGUI( ScreenManager screenManager, Program p ) {
		
		this.screenManager = screenManager;
		this.program = p;
		//Main Panel
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBackground(Color.BLACK);
		this.setBorder( new EmptyBorder(10, 20, 15, 15) );
		
		buttonPanel = new JPanel( new FlowLayout(FlowLayout.CENTER) );
		buttonPanel.setBackground(Color.BLACK);
		
		//Screen label setup
		screenLabelPanel = new JPanel( new BorderLayout() );
		screenLabel = new JLabel( "Recording Options " );
		screenLabel.setForeground( Color.WHITE );
		screenLabel.setFont( UIHelper.getHeadingFont() );
		screenLabelPanel.add(screenLabel, BorderLayout.WEST);
		screenLabelPanel.setBackground(Color.BLACK);
		screenLabelPanel.setMaximumSize(new Dimension(10000, screenLabel.getHeight()));
		screenLabel.setFocusable(false);
		
		//Show label setup 
		showLabel = new JLabel( p.getTitle() );
		showLabel.setForeground( Color.WHITE );
		showLabel.setFont( UIHelper.getBodyFont() );
		showLabel.setFocusable(false);
		
		repeatLabel = new JLabel("Reoccurring Recording:");
		repeatLabel.setForeground(Color.WHITE);
		repeatLabel.setFocusable(false);
		
		qualityLabel = new JLabel("Recording Quality:");
		qualityLabel.setForeground(Color.WHITE);
		qualityLabel.setFocusable(false);
		
		expirationLabel = new JLabel("Expiration:");
		expirationLabel.setForeground(Color.WHITE);
		expirationLabel.setFocusable(false);
		
		repeatOptions = new JLabel("Just this episode");
		repeatOptions.setForeground(Color.WHITE);
		qualityOptions = new JLabel("Medium");
		qualityOptions.setForeground(Color.WHITE);
		expirationOptions = new JLabel("Never delete");
		expirationOptions.setForeground(Color.WHITE);
		
		confirmButton = new LButton("OK");
		confirmButton.addActionListener( this );
	
		cancelButton = new LButton("Cancel");
		cancelButton.addActionListener( this );
		
		try{
			buttonLeftArrow = new ImageIcon( UIHelper.resourcePathToUrl(
				"images/left_arrow.png"));
		
			buttonRightArrow = new ImageIcon(UIHelper.resourcePathToUrl(
				"images/right_arrow.png"));
		}catch(Exception e){
			
		}
		
		String[] repeatOptions = {"Just this once", "All episodes", "All new episodes"};
		repeatSpinner = new HorizontalSpinner(buttonLeftArrow, 
				repeatOptions, buttonRightArrow);
		
		String[] qualityOptions = {"Low", "Medium", "High"};
		qualitySpinner = new HorizontalSpinner(buttonLeftArrow, 
				qualityOptions, buttonRightArrow);
		
		String[] expirationOptions = {"Delete after 14 Days", "Delete if space runs low", "Never Delete"};
		expirationSpinner = new HorizontalSpinner(buttonLeftArrow, 
				expirationOptions, buttonRightArrow);
		
		optionsPanel = new JPanel(new GridBagLayout() );
		GridBagConstraints repeatConstraints = new GridBagConstraints();
		repeatConstraints.gridx = 1;
		repeatConstraints.gridy = 1;
		repeatConstraints.insets = new Insets(2,2,2,2);	
		repeatConstraints.anchor = GridBagConstraints.LINE_START;
		optionsPanel.add(repeatLabel, repeatConstraints );
		repeatConstraints.gridy = 2;
		optionsPanel.add(qualityLabel, repeatConstraints );
		repeatConstraints.gridy = 3;
		optionsPanel.add(expirationLabel, repeatConstraints );
		optionsPanel.setBackground(Color.BLACK);
		repeatConstraints.gridx = 2;
		repeatConstraints.gridy = 1;
		repeatConstraints.fill = GridBagConstraints.HORIZONTAL;
		optionsPanel.add(repeatSpinner, repeatConstraints);
		repeatConstraints.gridy = 2;
		optionsPanel.add(qualitySpinner, repeatConstraints);
		repeatConstraints.gridy = 3;
		optionsPanel.add(expirationSpinner, repeatConstraints);
		
		buttonPanel.add(confirmButton);
		buttonPanel.add(cancelButton);
		
		repeatSpinner.addKeyListener(this);
		qualitySpinner.addKeyListener(this);
		expirationSpinner.addKeyListener(this);
		confirmButton.addKeyListener(this);
		cancelButton.addKeyListener(this);
		
		this.add(screenLabelPanel);
		this.add(Box.createVerticalStrut(50));
		this.add(showLabel);
		this.add(Box.createVerticalStrut(25));
		this.add(optionsPanel);
		this.add(Box.createVerticalStrut(15));
		this.add(buttonPanel);
			
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame();
		Program p = new Program("Family Guy", "Peter does something stupid", Program.TV_14, 20);
		RecordingOptionsGUI gui = new RecordingOptionsGUI(null, p);
		frame.add(gui);
		frame.setTitle("Legend TV - Recording Options");
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int keyCode = e.getKeyCode();
		if( repeatSpinner.isFocusOwner() ){
			if(keyCode == KeyEvent.VK_DOWN){
				qualitySpinner.requestFocusInWindow();
			}
		}else if( qualitySpinner.isFocusOwner() ){
			if(keyCode == KeyEvent.VK_DOWN){
				expirationSpinner.requestFocusInWindow();
			}else if(keyCode == KeyEvent.VK_UP){
				repeatSpinner.requestFocusInWindow();
			}			
		}else if( expirationSpinner.isFocusOwner() ){
			if(keyCode == KeyEvent.VK_UP){
				qualitySpinner.requestFocusInWindow();
			}else if(keyCode == KeyEvent.VK_DOWN){
				this.confirmButton.requestFocusInWindow();
			}
		}else if( confirmButton.isFocusOwner() ){
			if( keyCode == KeyEvent.VK_UP){
				expirationSpinner.requestFocusInWindow();
			}else if( keyCode == KeyEvent.VK_RIGHT){
				cancelButton.requestFocusInWindow();
			}
		}else if( cancelButton.isFocusOwner() ){
			if( keyCode == KeyEvent.VK_UP){
				expirationSpinner.requestFocusInWindow();
			}else if( keyCode == KeyEvent.VK_LEFT){
				confirmButton.requestFocusInWindow();
			}			
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed( ActionEvent e ) {
		if ( e.getSource() == confirmButton ) {
			program.setRecording( true );
		}
		screenManager.back();
	}

}
