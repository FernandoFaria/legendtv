import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;

/*
 * Version: $Id$
 * 
 * Revisions $Log$
 */

/**
 * WARNING!!! THIS CLASS IS FAR FROM COMPLETE.  COLORS ON PANELS ARE FOR DEBUGGING PURPOSES!!!
 * 
 * This class displays the options for a recorded TV Show.
 *
 * @author  Andrew Bona
 */
public class RecordingOptionsGUI{

	JFrame frame = new JFrame();
	JPanel mainPanel, repeatPanel, qualityPanel, expirationPanel, buttonPanel;
	JLabel screenLabel, showLabel, repeatLabel, qualityLabel, expirationLabel;
	JLabel repeatOptions, qualityOptions, expirationOptions;
	JButton confirmButton, cancelButton;
	JButton leftArrow, rightArrow;
	JButton leftArrow1, rightArrow1;
	JButton leftArrow2, rightArrow2;
	
	/**
	 * This code will be cleaned up at a later date.  The main functionality of this code has been split up
	 * by carriage returns to show differences in functionality.  Other methods will be created to reduce
	 * the "God" Constructor.
	 * 
	 * 
	 * @param show  This parameter is not a String, but will be of type "Recorded Program" object
	 */
	public RecordingOptionsGUI(String show) {
		
		mainPanel = new JPanel( new GridLayout(6, 1) );
		mainPanel.setBackground(Color.BLACK);
		GridLayout grid = new GridLayout(1,2);
		FlowLayout flow = new FlowLayout(FlowLayout.LEFT);
		
		JPanel repeatPanel2 = new JPanel( grid );
		JPanel qualityPanel2 = new JPanel( grid );
		JPanel expirationPanel2 = new JPanel( grid );
		
		JPanel repeatPanel3 = new JPanel( flow );
		JPanel qualityPanel3 = new JPanel( flow );
		JPanel expirationPanel3 = new JPanel( flow );
		
		repeatPanel3.setBackground(Color.BLACK);
		qualityPanel3.setBackground(Color.BLACK);
		expirationPanel3.setBackground(Color.BLACK);
		
		Font headerFont = new Font(null, Font.BOLD, 32);
		Font subHeaderFont = new Font(null,Font.ITALIC, 24);
		
		repeatPanel = new JPanel( flow );
		repeatPanel.setBackground(Color.BLACK);

		qualityPanel = new JPanel( flow );
		qualityPanel.setBackground(Color.BLACK);

		expirationPanel = new JPanel( flow );
		expirationPanel.setBackground(Color.BLACK);

		buttonPanel = new JPanel( new FlowLayout(FlowLayout.CENTER) );
		buttonPanel.setBackground(Color.BLACK);
		
		screenLabel = new JLabel("   Recording Options ");
		screenLabel.setForeground(Color.WHITE);
		screenLabel.setFont(headerFont);
		showLabel = new JLabel("    Family Guy - 10/4/08 - 8:00 - Channel 6 (Fox)");
		showLabel.setForeground(Color.WHITE);
		showLabel.setFont(subHeaderFont);
		repeatLabel = new JLabel("                    Reoccurring Recording: ");
		repeatLabel.setForeground(Color.WHITE);
		qualityLabel = new JLabel("                    Recording Quality: ");
		qualityLabel.setForeground(Color.WHITE);
		expirationLabel = new JLabel("                    Expiration: ");
		expirationLabel.setForeground(Color.WHITE);
		repeatOptions = new JLabel("Just this episode");
		repeatOptions.setForeground(Color.WHITE);
		qualityOptions = new JLabel("Medium");
		qualityOptions.setForeground(Color.WHITE);
		expirationOptions = new JLabel("Never delete");
		expirationOptions.setForeground(Color.WHITE);
		
		leftArrow = new JButton("<");
		rightArrow = new JButton(">");
		
		leftArrow1 = new JButton("<");
		rightArrow1 = new JButton(">");
		
		leftArrow2 = new JButton("<");
		rightArrow2 = new JButton(">");
		
		confirmButton = new JButton("OK");
		cancelButton = new JButton("Cancel");
		
		
		repeatPanel.add(leftArrow);
		repeatPanel.add(repeatOptions);
		repeatPanel.add(rightArrow);
		repeatPanel3.add(repeatLabel);
		repeatPanel2.add(repeatPanel3);
		repeatPanel2.add(repeatPanel);
		
		qualityPanel.add(qualityLabel);
		qualityPanel.add(leftArrow1);
		qualityPanel.add(qualityOptions);
		qualityPanel.add(rightArrow1);
		qualityPanel3.add(qualityLabel);
		qualityPanel2.add(qualityPanel3);
		qualityPanel2.add(qualityPanel);
		
		expirationPanel.add(expirationLabel);
		expirationPanel.add(leftArrow2);
		expirationPanel.add(expirationOptions);
		expirationPanel.add(rightArrow2);
		expirationPanel3.add(expirationLabel);
		expirationPanel2.add(expirationPanel3);
		expirationPanel2.add(expirationPanel);
		
		buttonPanel.add(confirmButton);
		buttonPanel.add(cancelButton);
				
		mainPanel.add(screenLabel);
		mainPanel.add(showLabel);
		mainPanel.add(repeatPanel2);
		mainPanel.add(qualityPanel2);
		mainPanel.add(expirationPanel2);
		mainPanel.add(buttonPanel);
	
		frame.add(mainPanel);
		frame.setTitle("Legend TV - Recording Options");
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
