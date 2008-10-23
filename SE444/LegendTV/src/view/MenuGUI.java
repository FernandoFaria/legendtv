package view;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

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
	
	private SVGButton northButton;
	private SVGButton westButton;
	private SVGButton eastButton;
	private SVGButton southButton;
	private SVGButton bottomButton;
	
	public MenuGUI(String title, SVGButton northButton, SVGButton westButton,
			SVGButton eastButton, SVGButton southButton,
			SVGButton bottomButton) {
		
		this.northButton = northButton;
		this.westButton = westButton;
		this.eastButton = eastButton;
		this.southButton = southButton;
		this.bottomButton = bottomButton;
		
		GridBagConstraints gbc = new GridBagConstraints();
		
    	JLabel titleLabel = new JLabel("LegendTV");
    	JLabel centerLabel = new JLabel(title, SwingConstants.CENTER);
    	
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
    	this.add(this.northButton, gbc);
    	
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
    	this.add(this.westButton, gbc);
    	
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
    	this.add(this.eastButton, gbc);
    	
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
    	this.add(this.southButton, gbc);
    	
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
    	this.add(this.bottomButton, gbc);
    	
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
    	
    	this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"),
    			"UPkeypressed");
    	this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"),
				"LEFTkeypressed");
    	this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"),
				"RIGHTkeypressed");
    	this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"),
				"DOWNkeypressed");
    	this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ENTER"),
				"ENTERkeypressed");

    	this.getActionMap().put("UPkeypressed", new MenuGUIAction("UP"));
    	this.getActionMap().put("LEFTkeypressed", new MenuGUIAction("LEFT"));
    	this.getActionMap().put("RIGHTkeypressed", new MenuGUIAction("RIGHT"));
    	this.getActionMap().put("DOWNkeypressed", new MenuGUIAction("DOWN"));
    	this.getActionMap().put("ENTERkeypressed", new MenuGUIAction("ENTER"));
	}
	
	public SVGButton getNorthButton() {
		return northButton;
	}

	public void setNorthButton(SVGButton northButton) {
		this.northButton = northButton;
	}

	public SVGButton getWestButton() {
		return westButton;
	}

	public void setWestButton(SVGButton westButton) {
		this.westButton = westButton;
	}

	public SVGButton getEastButton() {
		return eastButton;
	}

	public void setEastButton(SVGButton eastButton) {
		this.eastButton = eastButton;
	}

	public SVGButton getSouthButton() {
		return southButton;
	}

	public void setSouthButton(SVGButton southButton) {
		this.southButton = southButton;
	}

	public SVGButton getBottomButton() {
		return bottomButton;
	}

	public void setBottomButton(SVGButton bottomButton) {
		this.bottomButton = bottomButton;
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
			
		mainFrame.add(new MenuGUI("Test", northButton, westButton, eastButton,
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
	
	private class MenuGUIAction extends AbstractAction {

		private String key;
		
		public MenuGUIAction(String key) {
			this.key = key;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (this.key.equals("UP")) {
				getNorthButton().requestFocus();
				unfocusAllButtons();
				getNorthButton().setLooksFocused(true);
			}
			if (this.key.equals("LEFT")) {
				getWestButton().requestFocus();
				unfocusAllButtons();
				getWestButton().setLooksFocused(true);
			}
			if (this.key.equals("RIGHT")) {
				getEastButton().requestFocus();
				unfocusAllButtons();
				getEastButton().setLooksFocused(true);
			}
			if (this.key.equals("DOWN")) {
				if (getSouthButton().isFocusOwner()) {
					getBottomButton().requestFocus();
					unfocusAllButtons();
					getBottomButton().setLooksFocused(true);
				}
				else {
					getSouthButton().requestFocus();
					unfocusAllButtons();
					getSouthButton().setLooksFocused(true);
				}
			}
			if (this.key.equals("ENTER")) {
				if (getNorthButton().isFocusOwner()) {
					getNorthButton().fireActionEvent();
					unfocusAllButtons();
				}
				if (getWestButton().isFocusOwner()) {
					getWestButton().fireActionEvent();
					unfocusAllButtons();
				}
				if (getEastButton().isFocusOwner()) {
					getEastButton().fireActionEvent();
					unfocusAllButtons();
				}
				if (getSouthButton().isFocusOwner()) {
					getSouthButton().fireActionEvent();
					unfocusAllButtons();
				}
				if (getBottomButton().isFocusOwner()) {
					getBottomButton().fireActionEvent();
					unfocusAllButtons();
				}
			}
		}
		
		private void unfocusAllButtons() {
			getNorthButton().setLooksFocused(false);
			getWestButton().setLooksFocused(false);
			getEastButton().setLooksFocused(false);
			getSouthButton().setLooksFocused(false);
			getBottomButton().setLooksFocused(false);
		}
	}
}



