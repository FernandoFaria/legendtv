package view.controls;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

/*
 * HorizontalSpinner.java
 *
 * Version:
 *	 $Id$
 *
 * Revisions:
 *   $Log$
 */
public class HorizontalSpinner extends JPanel implements ActionListener, KeyListener, FocusListener{
	
	private JButton leftButton, rightButton;
	private Icon leftImage, rightImage;
	private String[] options;
	private JLabel displayLabel;
	
	private final BevelBorder focusBorder = new BevelBorder(BevelBorder.RAISED, Color.WHITE, Color.WHITE );
	private final EmptyBorder unfocusedBorder = new EmptyBorder(focusBorder.getBorderInsets(this));
	
	private int index;
	
	
	public HorizontalSpinner(Icon left, String[] optionChoices, Icon right){
		
		leftImage = left;
		rightImage = right;
		options = optionChoices;	

		leftButton = new JButton(leftImage);
		leftButton.setBackground(Color.DARK_GRAY);
		leftButton.setBorderPainted(false);
		leftButton.setActionCommand("Left");
		leftButton.setFocusPainted(false);
		leftButton.addActionListener(this);
		leftButton.setFocusable(false);
		
		rightButton = new JButton(rightImage);
		rightButton.setBackground(Color.DARK_GRAY);
		rightButton.setBorderPainted(false);
		rightButton.setActionCommand("Right");
		rightButton.addActionListener(this);
		rightButton.setFocusPainted(false);
		rightButton.setFocusable(false);
		
		index = 0;
		displayLabel = new JLabel(options[index]);
		displayLabel.setForeground(Color.WHITE);
		int maxSize = 0;
		for(int i = 1; i<optionChoices.length; i++){
			if( optionChoices[maxSize].length() < optionChoices[i].length() ){
				maxSize = i;
			}
		}
		displayLabel.setMinimumSize(new Dimension(displayLabel.HEIGHT, optionChoices[maxSize].length() *100) );

		this.setLayout( new BoxLayout(this, BoxLayout.X_AXIS) );		
		this.add(leftButton);
		this.add(Box.createHorizontalGlue());
		this.add(displayLabel);
		this.add(Box.createHorizontalGlue());
		this.add(rightButton);
		this.setBackground(Color.DARK_GRAY);
		this.setFocusable(true);
		this.setBorder(unfocusedBorder);
		this.addFocusListener(this);
		this.addKeyListener(this);
		this.setVisible(true);
	}
	
	public void displayRightOption(){
		index++;
		if( index <= (options.length-1) ){
			displayLabel.setText( options[index] );
		}else{
			index = 0;
			displayLabel.setText( options[index] );
		}
	}
	
	public void displayLeftOption(){
		index--;
		if( index >= 0 ){
			displayLabel.setText( options[index] );
		}else{
			index = (options.length-1);
			displayLabel.setText( options[index] );
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("Left")){
			displayLeftOption();
		}else if(e.getActionCommand().equals("Right")){
			displayRightOption();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if( this.hasFocus() ){
			if( e.getKeyCode() == KeyEvent.VK_LEFT){
				displayLeftOption();
			}else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
				displayRightOption();
			}
		}		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		setBorder(focusBorder);		
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		setBorder(unfocusedBorder);
	}
	
	
}
