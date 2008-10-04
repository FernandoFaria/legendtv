package view;
import java.awt.Color;
import java.awt.Insets;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

/*
 * LButton.java
 *
 * Version:
 *	 $Id$
 *
 * Revisions:
 *   $Log$
 */
public class LButton extends JButton{
	
	private static Icon buttonOffIcon = new ImageIcon("images/button_off_short.png");
	private static Icon buttonOnIcon = new ImageIcon("images/button_on_short.png");
	private Insets in = new Insets(0,0,0,0);
	
	public LButton(String text ){
		super(text, buttonOffIcon );
	
		this.setBackground(Color.BLACK);
		this.setIconTextGap(0);
		this.setForeground(Color.WHITE);
		this.setMargin(in);
		this.setVerticalTextPosition(SwingConstants.CENTER);
		this.setHorizontalTextPosition(SwingConstants.CENTER);
		this.setBorderPainted(false);
		this.setRolloverIcon(buttonOnIcon);
	}

	
	
}
