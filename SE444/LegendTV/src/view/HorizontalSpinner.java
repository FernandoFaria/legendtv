package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.*;

/*
 * HorizontalSpinner.java
 *
 * Version:
 *	 $Id$
 *
 * Revisions:
 *   $Log$
 */
public class HorizontalSpinner extends JPanel {
	
	private JButton leftButton, rightButton;
	private Icon leftImage, rightImage;
	private String[] options;
	private JLabel displayLabel;
	
	private int index;
	
	
	public HorizontalSpinner(Icon left, String[] optionChoices, Icon right){
		
		leftImage = left;
		rightImage = right;
		/**
		options = new JLabel[optionChoices.length];	

		for(int i = 0; i < optionChoices.length; i++){
			options[i] = new JLabel(optionChoices[i]);
			options[i].setForeground(Color.WHITE);
		}*/
		
		leftButton = new JButton(leftImage);
		leftButton.setBackground(Color.DARK_GRAY);
		leftButton.setBorderPainted(false);
		
		rightButton = new JButton(rightImage);
		rightButton.setBackground(Color.DARK_GRAY);
		rightButton.setBorderPainted(false);
		
		index = 0;
		displayLabel = new JLabel(options[index]);
		

		this.setLayout( new BoxLayout(this, BoxLayout.X_AXIS) );		
		this.add(leftButton);
		this.add(Box.createHorizontalGlue());
		this.add(displayLabel);
		this.add(Box.createHorizontalGlue());
		this.add(rightButton);
		
		this.setBackground(Color.DARK_GRAY);
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
}
