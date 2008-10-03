import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;


public class RecordingOptionsGUI implements WindowListener{

	JFrame frame = new JFrame();
	JPanel mainPanel, repeatPanel, qualityPanel, expirationPanel, buttonPanel;
	JLabel screenLabel, showLabel, repeatLabel, qualityLabel, expirationLabel;
	JLabel repeatOptions, qualityOptions, expirationOptions;
	JButton confirmButton, cancelButton;
	JButton leftArrow, rightArrow;
	JButton leftArrow1, rightArrow1;
	JButton leftArrow2, rightArrow2;
	
	public RecordingOptionsGUI(String show) {
		
		mainPanel = new JPanel( new GridLayout(6, 1) );
		mainPanel.setBackground(Color.BLACK);
		GridLayout grid = new GridLayout(1,2);
		FlowLayout flow = new FlowLayout(FlowLayout.LEFT);
		JPanel repeatPanel2 = new JPanel( grid );
		JPanel qualityPanel2 = new JPanel( new GridLayout(1,2) );
		JPanel expirationPanel2 = new JPanel( new GridLayout(1,2) );
		repeatPanel2.setBackground(Color.CYAN);
		qualityPanel2.setBackground(Color.PINK);
		expirationPanel2.setBackground(Color.YELLOW);
		
		repeatPanel = new JPanel( flow );
		repeatPanel.setBackground(Color.RED);
		qualityPanel = new JPanel( flow );
		qualityPanel.setBackground(Color.GREEN);
		expirationPanel = new JPanel( flow );
		expirationPanel.setBackground(Color.BLUE);
		buttonPanel = new JPanel( new FlowLayout(FlowLayout.CENTER) );
		buttonPanel.setBackground(Color.ORANGE);
		
		screenLabel = new JLabel("    Recording Options ");
		screenLabel.setForeground(Color.WHITE);
		showLabel = new JLabel("    Family Guy - 10/4/08 - 8:00 - Channel 6 (Fox)");
		showLabel.setForeground(Color.WHITE);
		repeatLabel = new JLabel("  Reoccurring Recording: ");
		repeatLabel.setForeground(Color.WHITE);
		qualityLabel = new JLabel("  Recording Quality: ");
		qualityLabel.setForeground(Color.WHITE);
		expirationLabel = new JLabel("  Expiration: ");
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
		repeatPanel2.add(repeatLabel);
		repeatPanel2.add(repeatPanel);
		
		qualityPanel.add(qualityLabel);
		qualityPanel.add(leftArrow1);
		qualityPanel.add(qualityOptions);
		qualityPanel.add(rightArrow1);
		qualityPanel2.add(qualityLabel);
		qualityPanel2.add(qualityPanel);
		
		expirationPanel.add(expirationLabel);
		expirationPanel.add(leftArrow2);
		expirationPanel.add(expirationOptions);
		expirationPanel.add(rightArrow2);
		expirationPanel2.add(expirationLabel);
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
		frame.setVisible(true);
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RecordingOptionsGUI gui = new RecordingOptionsGUI("Family Guy");
	}

	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		System.exit(1);
		
	}

	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
