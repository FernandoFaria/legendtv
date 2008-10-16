package view;
import java.awt.Color;
import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

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
public class LButton extends JButton implements FocusListener{
	
	public static final String largeButton = "Large Button";
	public static final String smallButton = "Small Button";
	
	private static Icon smallButtonOffIcon = new ImageIcon("images/button_off_short.png");
	private static Icon smallButtonOnIcon = new ImageIcon("images/button_on_short.png");
	private static Icon largeButtonOnIcon = new ImageIcon();
	private static Icon largeButtonOffIcon = new ImageIcon();
	
	private Insets in = new Insets(0,0,0,0);
	private String buttonSize;
	
	public LButton(String text ){
		this(text, smallButton);
	}
	
	public LButton( String text, String size){
		super(text);
		
		this.buttonSize = size;
		
		if( buttonSize.equals(smallButton) ){
			this.setIcon( smallButtonOffIcon );
			this.setRolloverIcon(smallButtonOnIcon);
		}else if( buttonSize.equals(largeButton) ){
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
		this.addFocusListener(this);
		this.setBorderPainted(false);
		
	}

	@Override
	public void focusGained(FocusEvent e) {
		if(buttonSize.equals(smallButton)){
			setIcon(smallButtonOnIcon);
		}else if(buttonSize.equals(largeButton)){
			setIcon(largeButtonOnIcon);
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		if(buttonSize.equals(smallButton)){
			setIcon(smallButtonOffIcon);
		}else if(buttonSize.equals(largeButton)){
			setIcon(largeButtonOffIcon);
		}
	}

	
	
}
