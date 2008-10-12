import javax.swing.*;
import java.awt.*;
import javax.swing.plaf.basic.*;

import view.MainFrame;

/**
 * MenuGUI.java
 * 
 * The "cross" form used for the major menus throughout the application.
 * 
 * $Id$
 * 
 * @author Bryan Eldridge
 *
 */
public class MenuGUI extends JPanel {

	public void addConstrainedPanel(GridBagConstraints gbc) {
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		this.add(panel, gbc);
	}
	
	public MenuGUI() {
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		JButton northButton = new JButton("North");
    	JButton westButton = new JButton("West");
    	JButton eastButton = new JButton("East");
    	JButton southButton = new JButton("South");
    	JButton bottomButton = new JButton("Exit");
    	JLabel titleLabel = new JLabel("LegendTV");
    	JLabel centerLabel = new JLabel("Main Menu", SwingConstants.CENTER);
    	
    	northButton.setFont(new Font("Verdana", Font.PLAIN, 28));
    	northButton.setForeground(Color.WHITE);
    	northButton.setBackground(Color.BLACK);
    	northButton.setBorder(new BasicBorders.ButtonBorder(Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE));
    	westButton.setFont(new Font("Verdana", Font.PLAIN, 28));
    	westButton.setForeground(Color.WHITE);
    	westButton.setBackground(Color.BLACK);
    	westButton.setBorder(new BasicBorders.ButtonBorder(Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE));
    	eastButton.setFont(new Font("Verdana", Font.PLAIN, 28));
    	eastButton.setForeground(Color.WHITE);
    	eastButton.setBackground(Color.BLACK);
    	eastButton.setBorder(new BasicBorders.ButtonBorder(Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE));
    	southButton.setFont(new Font("Verdana", Font.PLAIN, 28));
    	southButton.setForeground(Color.WHITE);
    	southButton.setBackground(Color.BLACK);
    	southButton.setBorder(new BasicBorders.ButtonBorder(Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE));
    	bottomButton.setFont(new Font("Verdana", Font.PLAIN, 28));
    	bottomButton.setForeground(Color.WHITE);
    	bottomButton.setBackground(Color.BLACK);
    	bottomButton.setBorder(new BasicBorders.ButtonBorder(Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE));
    	titleLabel.setFont(new Font("Verdana", Font.PLAIN, 28));
    	titleLabel.setForeground(Color.WHITE);
    	centerLabel.setFont(new Font("Verdana", Font.PLAIN, 28));
    	centerLabel.setForeground(Color.WHITE);
    	
    	this.setBackground(Color.BLACK);
    	
    	this.setLayout(new GridBagLayout());
    	
    	gbc.fill = GridBagConstraints.BOTH;
    	gbc.gridx = 0;
    	gbc.gridy = 0;
    	gbc.gridwidth = 16;
    	gbc.gridheight = 1;
    	gbc.weightx = 1.0;
    	gbc.weighty = 0.0625;
    	addConstrainedPanel(gbc); // End first row
    	
    	gbc.fill = GridBagConstraints.BOTH;
    	gbc.gridx = 0;
    	gbc.gridy = 1;
    	gbc.gridwidth = 1;
    	gbc.gridheight = 1;
    	gbc.weightx = 0.0625;
    	gbc.weighty = 0.0625;
    	addConstrainedPanel(gbc); 
    	
    	gbc.fill = GridBagConstraints.BOTH;
    	gbc.gridx = 1;
    	gbc.gridy = 1;
    	gbc.gridwidth = 4;
    	gbc.gridheight = 1;
    	gbc.weightx = 0.25;
    	gbc.weighty = 0.0625;
    	this.add(titleLabel, gbc);
    	
    	gbc.fill = GridBagConstraints.BOTH;
    	gbc.gridx = 5;
    	gbc.gridy = 1;
    	gbc.gridwidth = 11;
    	gbc.gridheight = 1;
    	gbc.weightx = 0.6875;
    	gbc.weighty = 0.0625;
    	addConstrainedPanel(gbc); // End second row
    	
    	gbc.fill = GridBagConstraints.BOTH;
    	gbc.gridx = 0;
    	gbc.gridy = 2;
    	gbc.gridwidth = 6;
    	gbc.gridheight = 3;
    	gbc.weightx = 0.375;
    	gbc.weighty = 0.1875;
    	addConstrainedPanel(gbc);
    	
    	gbc.fill = GridBagConstraints.BOTH;
    	gbc.gridx = 6;
    	gbc.gridy = 2;
    	gbc.gridwidth = 4;
    	gbc.gridheight = 3;
    	gbc.weightx = .25;
    	gbc.weighty = 0.1875;
    	this.add(northButton, gbc);
    	
    	gbc.fill = GridBagConstraints.BOTH;
    	gbc.gridx = 10;
    	gbc.gridy = 2;
    	gbc.gridwidth = 6;
    	gbc.gridheight = 3;
    	gbc.weightx = 0.375;
    	gbc.weighty = 0.1875;
    	addConstrainedPanel(gbc); // End third row
    	
    	gbc.fill = GridBagConstraints.BOTH;
    	gbc.gridx = 0;
    	gbc.gridy = 5;
    	gbc.gridwidth = 16;
    	gbc.gridheight = 1;
    	gbc.weightx = 1.0;
    	gbc.weighty = 0.0625;
    	addConstrainedPanel(gbc); // End fourth row
    	
    	gbc.fill = GridBagConstraints.BOTH;
    	gbc.gridx = 0;
    	gbc.gridy = 6;
    	gbc.gridwidth = 1;
    	gbc.gridheight = 3;
    	gbc.weightx = 0.0625;
    	gbc.weighty = 0.1875;
    	addConstrainedPanel(gbc);
    	
    	gbc.fill = GridBagConstraints.BOTH;
    	gbc.gridx = 1;
    	gbc.gridy = 6;
    	gbc.gridwidth = 4;
    	gbc.gridheight = 3;
    	gbc.weightx = 0.25;
    	gbc.weighty = 0.1875;
    	this.add(westButton, gbc);
    	
    	gbc.fill = GridBagConstraints.BOTH;
    	gbc.gridx = 5;
    	gbc.gridy = 6;
    	gbc.gridwidth = 6;
    	gbc.gridheight = 3;
    	gbc.weightx = 0.375;
    	gbc.weighty = 0.1875;
    	this.add(centerLabel, gbc);
    	
    	gbc.fill = GridBagConstraints.BOTH;
    	gbc.gridx = 11;
    	gbc.gridy = 6;
    	gbc.gridwidth = 4;
    	gbc.gridheight = 3;
    	gbc.weightx = 0.25;
    	gbc.weighty = 0.1875;
    	this.add(eastButton, gbc);
    	
    	gbc.fill = GridBagConstraints.BOTH;
    	gbc.gridx = 15;
    	gbc.gridy = 6;
    	gbc.gridwidth = 1;
    	gbc.gridheight = 3;
    	gbc.weightx = 0.0625;
    	gbc.weighty = 0.1875;
    	addConstrainedPanel(gbc); // End fifth row
    	
    	gbc.fill = GridBagConstraints.BOTH;
    	gbc.gridx = 0;
    	gbc.gridy = 9;
    	gbc.gridwidth = 16;
    	gbc.gridheight = 1;
    	gbc.weightx = 1.0;
    	gbc.weighty = 0.0625;
    	addConstrainedPanel(gbc); // End sixth row
    	
    	gbc.fill = GridBagConstraints.BOTH;
    	gbc.gridx = 0;
    	gbc.gridy = 10;
    	gbc.gridwidth = 6;
    	gbc.gridheight = 3;
    	gbc.weightx = 0.375;
    	gbc.weighty = 0.1875;
    	addConstrainedPanel(gbc);
    	
    	gbc.fill = GridBagConstraints.BOTH;
    	gbc.gridx = 6;
    	gbc.gridy = 10;
    	gbc.gridwidth = 4;
    	gbc.gridheight = 3;
    	gbc.weightx = 0.25;
    	gbc.weighty = 0.1875;
    	this.add(southButton, gbc);
    	
    	gbc.fill = GridBagConstraints.BOTH;
    	gbc.gridx = 10;
    	gbc.gridy = 10;
    	gbc.gridwidth = 6;
    	gbc.gridheight = 3;
    	gbc.weightx = 0.375;
    	gbc.weighty = 0.1875;
    	addConstrainedPanel(gbc); // End seventh row
    	
    	gbc.fill = GridBagConstraints.BOTH;
    	gbc.gridx = 0;
    	gbc.gridy = 13;
    	gbc.gridwidth = 16;
    	gbc.gridheight = 1;
    	gbc.weightx = 1.0;
    	gbc.weighty = 0.0625;
    	addConstrainedPanel(gbc); // End eighth row
    	
    	gbc.fill = GridBagConstraints.BOTH;
    	gbc.gridx = 0;
    	gbc.gridy = 14;
    	gbc.gridwidth = 7;
    	gbc.gridheight = 1;
    	gbc.weightx = 0.4375;
    	gbc.weighty = 0.0625;
    	addConstrainedPanel(gbc);
    	
    	gbc.fill = GridBagConstraints.BOTH;
    	gbc.gridx = 7;
    	gbc.gridy = 14;
    	gbc.gridwidth = 2;
    	gbc.gridheight = 1;
    	gbc.weightx = 0.125;
    	gbc.weighty = 0.0625;
    	this.add(bottomButton);
    	
    	gbc.fill = GridBagConstraints.BOTH;
    	gbc.gridx = 9;
    	gbc.gridy = 14;
    	gbc.gridwidth = 7;
    	gbc.gridheight = 1;
    	gbc.weightx = 0.4375;
    	gbc.weighty = 0.0625;
    	addConstrainedPanel(gbc); // End ninth row
    	
    	gbc.fill = GridBagConstraints.BOTH;
    	gbc.gridx = 0;
    	gbc.gridy = 15;
    	gbc.gridwidth = 16;
    	gbc.gridheight = 1;
    	gbc.weightx = 1.0;
    	gbc.weighty = 0.0625;
    	addConstrainedPanel(gbc); // End tenth row
    }
	
	/**
	 * For testing.
	 */
	public static void main(String[] args) {
		MainFrame mainFrame = new MainFrame();
		mainFrame.add(new MenuGUI());
		mainFrame.setExtendedState(JFrame.NORMAL);
		mainFrame.setUndecorated(false);
		mainFrame.setVisible(true);
	}

}
