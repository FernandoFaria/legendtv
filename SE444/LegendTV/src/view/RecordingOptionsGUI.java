package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import view.LButton;

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
public class RecordingOptionsGUI {

	private JFrame frame = new JFrame();
	private JPanel mainPanel, optionsPanel, buttonPanel, screenLabelPanel;
	private JLabel screenLabel, showLabel, repeatLabel, qualityLabel, expirationLabel;
	private JLabel repeatOptions, qualityOptions, expirationOptions;
	
	//JButtons
	private JButton confirmButton, cancelButton;
	
	//private Insets in = new Insets(0,0,0,0);
	
	private Icon buttonLeftArrow = new ImageIcon("images/left_arrow.png");
	private Icon buttonRightArrow = new ImageIcon("images/right_arrow.png");
	
	/**
	 * This code will be cleaned up at a later date.  The main functionality of this code has been split up
	 * by carriage returns to show differences in functionality.  Other methods will be created to reduce
	 * the "God" Constructor.
	 * 
	 * 
	 * @param show  This parameter is not a String, but will be of type "Recorded Program" object
	 */
	public RecordingOptionsGUI(String show) {
		
		//Main Panel
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBackground(Color.BLACK);
		mainPanel.setBorder( new EmptyBorder(10, 20, 15, 15) );
		
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
		
		//Show label setup
		showLabel = new JLabel( "Family Guy - 10/4/08 - 8:00 - Channel 6 (Fox)" );
		showLabel.setForeground( Color.WHITE );
		showLabel.setFont( UIHelper.getBodyFont() );
		
		repeatLabel = new JLabel("Reoccurring Recording:");
		repeatLabel.setForeground(Color.WHITE);
		
		qualityLabel = new JLabel("Recording Quality:");
		qualityLabel.setForeground(Color.WHITE);
		
		expirationLabel = new JLabel("Expiration:");
		expirationLabel.setForeground(Color.WHITE);		
		
		repeatOptions = new JLabel("Just this episode");
		repeatOptions.setForeground(Color.WHITE);
		qualityOptions = new JLabel("Medium");
		qualityOptions.setForeground(Color.WHITE);
		expirationOptions = new JLabel("Never delete");
		expirationOptions.setForeground(Color.WHITE);
		
		confirmButton = new LButton("OK");
	
		cancelButton = new LButton("Cancel");
		
		String[] repeatOptions = {"Just this once", "All episodes", "All new episodes"};
		HorizontalSpinner repeatSpinner = new HorizontalSpinner(buttonLeftArrow, 
				repeatOptions, buttonRightArrow);
		
		String[] qualityOptions = {"Low", "Medium", "High"};
		HorizontalSpinner qualitySpinner = new HorizontalSpinner(buttonLeftArrow, 
				qualityOptions, buttonRightArrow);
		
		String[] expirationOptions = {"Delete after 14 Days", "Delete if space runs low", "Never Delete"};
		HorizontalSpinner expirationSpinner = new HorizontalSpinner(buttonLeftArrow, 
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
		
		mainPanel.add(screenLabelPanel);
		mainPanel.add(Box.createVerticalStrut(50));
		mainPanel.add(showLabel);
		mainPanel.add(Box.createVerticalStrut(25));
		mainPanel.add(optionsPanel);
		mainPanel.add(Box.createVerticalStrut(15));
		mainPanel.add(buttonPanel);
	
		frame.add(mainPanel);
		frame.setTitle("Legend TV - Recording Options");
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RecordingOptionsGUI gui = new RecordingOptionsGUI("Family Guy");
	}

}
