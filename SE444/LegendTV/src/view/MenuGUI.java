package view;
import javax.swing.*;
import java.awt.*;
import javax.swing.plaf.basic.*;

import view.utils.UIHelper;
import view.controls.SVGButton;

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
	
	public MenuGUI(SVGButton northButton, SVGButton westButton,
			SVGButton eastButton, SVGButton southButton,
			SVGButton bottomButton) {
		
		GridBagConstraints gbc = new GridBagConstraints();
		
    	JLabel titleLabel = new JLabel("LegendTV");
    	JLabel centerLabel = new JLabel("Main Menu", SwingConstants.CENTER);
    	

    	
    	titleLabel.setFont(UIHelper.getHeadingFont());
    	titleLabel.setForeground(UIHelper.getForegroundColor());
    	titleLabel.setMinimumSize(new Dimension(0, 0));
    	titleLabel.setPreferredSize(new Dimension(Short.MAX_VALUE,
    			Short.MAX_VALUE));
    	centerLabel.setFont(UIHelper.getHeadingFont());
    	centerLabel.setForeground(UIHelper.getForegroundColor());
    	centerLabel.setMinimumSize(new Dimension(0, 0));
    	centerLabel.setPreferredSize(new Dimension(Short.MAX_VALUE,
    			Short.MAX_VALUE));
    	
    	this.setBackground(Color.BLACK);
    	
    	this.setLayout(new GridBagLayout());
    	
    	gbc.fill = GridBagConstraints.BOTH;
    	
    	gbc.gridx = 0;
    	gbc.gridy = 0;
    	gbc.gridwidth = 16;
    	gbc.gridheight = 1;
    	gbc.weightx = 0.16;
    	gbc.weighty = 0.01;
    	this.add(Box.createGlue(), gbc);
    	
    	gbc.gridx = 0;
    	gbc.gridy = 1;
    	gbc.gridwidth = 1;
    	gbc.gridheight = 1;
    	gbc.weightx = 0.01;
    	gbc.weighty = 0.01;
    	this.add(Box.createGlue(), gbc);
    	
    	gbc.gridx = 1;
    	gbc.gridy = 1;
    	gbc.gridwidth = 4;
    	gbc.gridheight = 1;
    	gbc.weightx = .04;
    	gbc.weighty = .01;
    	this.add(titleLabel, gbc);
    	
    	gbc.gridx = 5;
    	gbc.gridy = 5;
    	gbc.gridwidth = 11;
    	gbc.gridheight = 1;
    	gbc.weightx = 0.11;
    	gbc.weighty = 0.01;
    	this.add(Box.createGlue(), gbc);
    	
    	gbc.gridx = 0;
    	gbc.gridy = 2;
    	gbc.gridwidth = 6;
    	gbc.gridheight = 3;
    	gbc.weightx = 0.06;
    	gbc.weighty = 0.03;
    	this.add(Box.createGlue(), gbc);
    	
    	gbc.gridx = 6;
    	gbc.gridy = 2;
    	gbc.gridwidth = 4;
    	gbc.gridheight = 3;
    	gbc.weightx = 0.04;
    	gbc.weighty = 0.03;
    	this.add(northButton, gbc);
    	
    	gbc.gridx = 10;
    	gbc.gridy = 2;
    	gbc.gridwidth = 6;
    	gbc.gridheight = 3;
    	gbc.weightx = 0.06;
    	gbc.weighty = 0.03;
    	this.add(Box.createGlue(), gbc);
    	
    	gbc.gridx = 0;
    	gbc.gridy = 5;
    	gbc.gridwidth = 16;
    	gbc.gridheight = 1;
    	gbc.weightx = 0.16;
    	gbc.weighty = 0.01;
    	this.add(Box.createGlue(), gbc);
    	
    	gbc.gridx = 0;
    	gbc.gridy = 6;
    	gbc.gridwidth = 1;
    	gbc.gridheight = 3;
    	gbc.weightx = 0.01;
    	gbc.weighty = 0.03;
    	this.add(Box.createGlue(), gbc);
    	
    	gbc.gridx = 1;
    	gbc.gridy = 6;
    	gbc.gridwidth = 4;
    	gbc.gridheight = 3;
    	gbc.weightx = 0.04;
    	gbc.weighty = 0.03;
    	this.add(westButton, gbc);
    	
    	gbc.gridx = 5;
    	gbc.gridy = 6;
    	gbc.gridwidth = 1;
    	gbc.gridheight = 3;
    	gbc.weightx = 0.01;
    	gbc.weighty = 0.03;
    	this.add(Box.createGlue(), gbc);
    	
    	gbc.gridx = 6;
    	gbc.gridy = 6;
    	gbc.gridwidth = 4;
    	gbc.gridheight = 3;
    	gbc.weightx = 0.04;
    	gbc.weighty = 0.03;
    	this.add(centerLabel, gbc);
    	
    	gbc.gridx = 10;
    	gbc.gridy = 6;
    	gbc.gridwidth = 1;
    	gbc.gridheight = 3;
    	gbc.weightx = 0.01;
    	gbc.weighty = 0.03;
    	this.add(Box.createGlue(), gbc);
    	
    	gbc.gridx = 11;
    	gbc.gridy = 6;
    	gbc.gridwidth = 4;
    	gbc.gridheight = 3;
    	gbc.weightx = 0.04;
    	gbc.weighty = 0.03;
    	this.add(eastButton, gbc);
    	
    	gbc.gridx = 15;
    	gbc.gridy = 6;
    	gbc.gridwidth = 1;
    	gbc.gridheight = 3;
    	gbc.weightx = 0.01;
    	gbc.weighty = 0.03;
    	this.add(Box.createGlue(), gbc);
    	
    	gbc.gridx = 0;
    	gbc.gridy = 9;
    	gbc.gridwidth = 16;
    	gbc.gridheight = 1;
    	gbc.weightx = 0.16;
    	gbc.weighty = 0.01;
    	this.add(Box.createGlue(), gbc);
    	
    	gbc.gridx = 0;
    	gbc.gridy = 10;
    	gbc.gridwidth = 6;
    	gbc.gridheight = 3;
    	gbc.weightx = 0.06;
    	gbc.weighty = 0.03;
    	this.add(Box.createGlue(), gbc);
    	
    	gbc.gridx = 6;
    	gbc.gridy = 10;
    	gbc.gridwidth = 4;
    	gbc.gridheight = 3;
    	gbc.weightx = 0.04;
    	gbc.weighty = 0.03;
    	this.add(southButton, gbc);
    	
    	gbc.gridx = 10;
    	gbc.gridy = 10;
    	gbc.gridwidth = 6;
    	gbc.gridheight = 3;
    	gbc.weightx = 0.06;
    	gbc.weighty = 0.03;
    	this.add(Box.createGlue(), gbc);
    	
    	gbc.gridx = 0;
    	gbc.gridy = 13;
    	gbc.gridwidth = 16;
    	gbc.gridheight = 1;
    	gbc.weightx = 0.16;
    	gbc.weighty = 0.01;
    	this.add(Box.createGlue(), gbc);
    	
    	gbc.gridx = 0;
    	gbc.gridy = 14;
    	gbc.gridwidth = 7;
    	gbc.gridheight = 1;
    	gbc.weightx = 0.07;
    	gbc.weighty = 0.01;
    	this.add(Box.createGlue(), gbc);
    	
    	gbc.gridx = 7;
    	gbc.gridy = 14;
    	gbc.gridwidth = 2;
    	gbc.gridheight = 1;
    	gbc.weightx = 0.02;
    	gbc.weighty = 0.01;
    	this.add(bottomButton, gbc);
    	
    	gbc.gridx = 9;
    	gbc.gridy = 14;
    	gbc.gridwidth = 7;
    	gbc.gridheight = 1;
    	gbc.weightx = 0.07;
    	gbc.weighty = 0.01;
    	this.add(Box.createGlue(), gbc);
    	
    	gbc.gridx = 0;
    	gbc.gridy = 15;
    	gbc.gridwidth = 16;
    	gbc.gridheight = 1;
    	gbc.weightx = 0.16;
    	gbc.weighty = 0.01;
    	this.add(Box.createGlue(), gbc);
    	
	}
	
	/**
	 * For testing.
	 */
	public static void main(String[] args) {
		MainFrame mainFrame = new MainFrame();
		
		SVGButton northButton = UIHelper.createButton("North", null);
		SVGButton westButton = UIHelper.createButton("West", null);
		SVGButton eastButton = UIHelper.createButton("East", null);
		SVGButton southButton = UIHelper.createButton("South", null);
		SVGButton bottomButton = UIHelper.createButton("Exit", null);
			
		mainFrame.add(new MenuGUI(northButton, westButton, eastButton,
				southButton, bottomButton));
    	
		/*
    	northButton.setBorder(new BasicBorders.ButtonBorder(Color.WHITE,
    			Color.WHITE, Color.WHITE, Color.WHITE));
    	westButton.setBorder(new BasicBorders.ButtonBorder(Color.WHITE,
				Color.WHITE, Color.WHITE, Color.WHITE));
    	eastButton.setBorder(new BasicBorders.ButtonBorder(Color.WHITE,
				Color.WHITE, Color.WHITE, Color.WHITE));
    	southButton.setBorder(new BasicBorders.ButtonBorder(Color.WHITE,
				Color.WHITE, Color.WHITE, Color.WHITE));
    	bottomButton.setBorder(new BasicBorders.ButtonBorder(Color.WHITE,
				Color.WHITE, Color.WHITE, Color.WHITE));
    	*/
		
		mainFrame.setUndecorated(true);
		mainFrame.setVisible(true);
	}

}
