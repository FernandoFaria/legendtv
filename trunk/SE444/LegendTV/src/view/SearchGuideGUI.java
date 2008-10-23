package view;


import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import view.controls.LButton;
import view.utils.ScreenManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/*
 * SearchGuideGUI.java
 *
 * Version:
 *	 $Id$
 *
 * Revisions:
 *   $Log$
 */
public class SearchGuideGUI extends JPanel implements ActionListener, KeyListener, ListSelectionListener{

	//GUI Components for the Panels	
	private JPanel searchPanel, searchResults, buttonsPanel;
	
	//GUI Components for the Labels
	private JLabel pageLabel, searchLabel;
	
	private JList showNames, showTimes;
	private DefaultListModel showNameListModel, showTimesListModel;
	private JScrollPane nameScroll, timeScroll;
	
	private JTextField text;
	private JTextArea showDescription;
	
	private JButton searchButton, backButton, confirmButton;
	private Font headerFont = new Font(null, Font.BOLD, 32);
	
	private SearchDatabase database = new SearchDatabase();
	
	private ScreenManager manager;
	
	public SearchGuideGUI(ScreenManager sm){

		this.manager = sm;
		
		BoxLayout b1 = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout( b1 );
		this.setBackground( Color.BLACK );
		this.setAlignmentX( Component.LEFT_ALIGNMENT );
		
		searchPanel = new JPanel();
		searchResults = new JPanel(new GridBagLayout() );
		//BoxLayout b2 = new BoxLayout( searchResults, BoxLayout.X_AXIS );
		//searchResults.setLayout(b2);
		buttonsPanel = new JPanel();
		buttonsPanel.setLayout( new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS) );
		searchPanel.setBackground(Color.BLACK);
		searchResults.setBackground(Color.BLACK);
		buttonsPanel.setBackground(Color.BLACK);
		
		//Setting the page label
		pageLabel = new JLabel("   Search TV Guide");
		pageLabel.setFont(headerFont);
		pageLabel.setForeground(Color.WHITE);
		BorderLayout border = new BorderLayout();
		JPanel labelPanel = new JPanel(border);
		labelPanel.add(pageLabel, BorderLayout.WEST);
		border.getLayoutComponent( BorderLayout.WEST).setMaximumSize(pageLabel.getSize() );
		labelPanel.setMaximumSize( new Dimension(10000, (int) pageLabel.getSize().getHeight()));
		labelPanel.setBackground(Color.BLACK);	
		
		searchLabel = new JLabel("Enter search terms(s): ");
		searchLabel.setForeground(Color.WHITE);
		text = new JTextField(40);
		searchButton = new LButton( "Search" );
		searchButton.setActionCommand( "Search" );
		searchButton.addActionListener( this );
		searchPanel.add(searchLabel);
		searchPanel.add(text);
		searchPanel.add(searchButton);
		
		//Set up the Search Results panel and its contents
		showNameListModel = new DefaultListModel();
		showTimesListModel = new DefaultListModel();
				
		showNames = new JList(showNameListModel);
		showTimes = new JList(showTimesListModel);
		nameScroll = new JScrollPane(showNames);
		nameScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		timeScroll = new JScrollPane(showTimes);
		timeScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		showDescription = new JTextArea("", 10, 20);
		showDescription.setEnabled(false);
		showDescription.setBackground(Color.DARK_GRAY);
		showDescription.setForeground(Color.WHITE);
		showDescription.setWrapStyleWord(true);
		showDescription.setMaximumSize(new Dimension(10,20));
		showNames.addListSelectionListener(this);
		showTimes.addListSelectionListener(this);
		
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.insets = new Insets(0,0,10,10);
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1.0;
		c.weighty = 1.0;
		searchResults.add(nameScroll, c);
		//searchResults.add(Box.createHorizontalStrut(10));
		c.gridx = 2;
		searchResults.add(timeScroll, c);
		//searchResults.add(Box.createHorizontalStrut(10));
		c.gridx = 3;
		searchResults.add(showDescription, c);
		//searchResults.add(Box.createHorizontalStrut(10));
		c.gridx = 4;
		c.weightx = 0.0;
		c.weighty = 0.0;
		searchResults.add(buttonsPanel, c);
		
		confirmButton = new LButton("Select");
		confirmButton.setActionCommand("OK");
		confirmButton.addActionListener(this);
		backButton = new LButton("Back to Menu");
		backButton.setActionCommand("Back");
		backButton.addActionListener(this);
		buttonsPanel.add(confirmButton);
		buttonsPanel.add(backButton);		
		
		this.add(Box.createVerticalStrut(15));
		this.add(labelPanel);
		Component box = Box.createVerticalStrut(10);
		box.setMaximumSize(new Dimension(0,10));
		this.add(box);
		this.add(searchPanel);
		this.add(Box.createGlue());
		this.add(searchResults);
		
	}
	
	
	/**
	 * [Place method description here]
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		
		SearchGuideGUI search = new SearchGuideGUI(null);
		
		frame.setTitle("LegendTV - Search TV Guide");
		frame.add(search);
		frame.pack();
		frame.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if( e.getActionCommand().equals("Search") ){
			database.getShowTitles( showNameListModel, text.getText() );
		}else if( e.getActionCommand().equals("OK") ){
			
		}else if( e.getActionCommand().equals("Back") ){
			manager.back();
		}
	}


	@Override
	public void keyPressed(KeyEvent e) {
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyTyped(KeyEvent e) {
		
	}


	@Override
	public void valueChanged(ListSelectionEvent e) {
		if(e.getSource().equals(showNames)){
			database.getTimesList(showTimesListModel, (String) showNames.getSelectedValue() );
		}else if(e.getSource().equals(showTimes)){
			System.out.println("Display description");
			if(showTimes.getSelectedIndex() != -1 ){
				showDescription.setText( ((ProgramListing) showTimes.getSelectedValue()).getDescription() );
			}else{
				showDescription.setText("");
			}
		}
	}

}
