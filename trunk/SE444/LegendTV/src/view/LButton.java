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
	
	public static final String largeButton = "Large Button";
	public static final String smallButton = "Small Button";
	
	private static Icon smallButtonOffIcon = new ImageIcon("images/button_off_short.png");
	private static Icon smallButtonOnIcon = new ImageIcon("images/button_on_short.png");
	private static Icon largeButtonOnIcon = new ImageIcon();
	private static Icon largeButtonOffIcon = new ImageIcon();
	
	private Insets in = new Insets(0,0,0,0);
	
	public LButton(String text ){
		this(text, smallButton);
	}
	
	public LButton( String text, String size){
		super(text);
		
		if( size.equals(smallButton) ){
			this.setIcon( smallButtonOffIcon );
			this.setRolloverIcon(smallButtonOnIcon);
		}else if( size.equals(largeButton) ){
			this.setIcon( largeButtonOffIcon );
			this.setRolloverIcon(largeButtonOnIcon);
		}
		
		this.setBackground(Color.BLACK);
		this.setIconTextGap(0);
		this.setForeground(Color.WHITE);
		this.setMargin(in);
		this.setVerticalTextPosition(SwingConstants.CENTER);
		this.setHorizontalTextPosition(SwingConstants.CENTER);
		this.setFocusPainted(false);
		this.setBorderPainted(false);
		
	}

	
	
}
