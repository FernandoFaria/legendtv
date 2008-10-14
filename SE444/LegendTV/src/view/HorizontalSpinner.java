package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
public class HorizontalSpinner extends JPanel implements ActionListener{
	
	private JButton leftButton, rightButton;
	private Icon leftImage, rightImage;
	private String[] options;
	private JLabel displayLabel;
	
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
		
		rightButton = new JButton(rightImage);
		rightButton.setBackground(Color.DARK_GRAY);
		rightButton.setBorderPainted(false);
		rightButton.setActionCommand("Right");
		rightButton.addActionListener(this);
		rightButton.setFocusPainted(false);
		
		index = 0;
		displayLabel = new JLabel(options[index]);
		displayLabel.setForeground(Color.WHITE);
		int maxSize = 0;
		for(int i = 1; i<optionChoices.length; i++){
			if( optionChoices[maxSize].length() < optionChoices[i].length() ){
				maxSize = i;
			}
		}		

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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("Left")){
			displayLeftOption();
		}else if(e.getActionCommand().equals("Right")){
			displayRightOption();
		}
	}
}
