package view;


import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import view.LButton;

import java.awt.*;


/*
 * SearchGuideGUI.java
 *
 * Version:
 *	 $Id$
 *
 * Revisions:
 *   $Log$
 */
public class SearchGuideGUI {

	//GUI Components for the Frame and Panels
	private JFrame frame = new JFrame();
	private JPanel mainPanel, searchPanel, searchResults, buttonsPanel;
	
	private JLabel pageLabel, searchLabel;
	
	private JList showNames, showTimes;
	private DefaultListModel showNameListModel, showTimesListModel;
	private JScrollPane nameScroll, timeScroll;
	
	private JTextField text;
	private JTextArea showDescription;
	
	private JButton searchButton, backButton, confirmButton;
	private Font headerFont = new Font(null, Font.BOLD, 32);
	
	private Insets in = new Insets(0,0,0,0);
	private Icon buttonOffIcon= new ImageIcon("images/button_off_short.png");
	private Icon buttonOnIcon= new ImageIcon("images/button_on_short.png");
	
	
	public SearchGuideGUI(){
		mainPanel = new JPanel( );
		BoxLayout b1 = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
		mainPanel.setLayout( b1 );
		mainPanel.setBackground(Color.BLACK);
		mainPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		searchPanel = new JPanel();
		searchResults = new JPanel();
		BoxLayout b2 = new BoxLayout(searchResults, BoxLayout.X_AXIS);
		searchResults.setLayout(b2);
		buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS) );
		searchPanel.setBackground(Color.BLACK);
		searchResults.setBackground(Color.BLACK);
		buttonsPanel.setBackground(Color.BLACK);
		
		//Setting the page label
		pageLabel = new JLabel("Search TV Guide");
		pageLabel.setFont(headerFont);
		pageLabel.setForeground(Color.WHITE);
		BorderLayout border = new BorderLayout();
		JPanel labelPanel = new JPanel(border);
		labelPanel.add(pageLabel, BorderLayout.WEST);
		border.getLayoutComponent(BorderLayout.WEST).setMaximumSize(pageLabel.getSize() );
//		labelPanel.setMinimumSize(pageLabel.getSize());
		labelPanel.setMaximumSize(new Dimension(10000, (int)pageLabel.getSize().getHeight()));
		labelPanel.setBackground(Color.BLUE);	
		
		
		searchLabel = new JLabel("Enter search terms(s): ");
		searchLabel.setForeground(Color.WHITE);
		text = new JTextField(40);
		searchButton = new LButton("Search");
		searchPanel.add(searchLabel);
		searchPanel.add(text);
		searchPanel.add(searchButton);
		
		//Set up the Search Results panel and its contents
		showNameListModel = new DefaultListModel();
		showNameListModel.add(0, "CSI: Crime Scene Investigation");
		showNameListModel.add(1, "CSI: Miami");
		showNameListModel.add(2, "CSI: RIT");
		showNameListModel.add(0, "CSI: Wasilla");
		
		showTimesListModel = new DefaultListModel();
		showTimesListModel.add(0, "Mon 09/29/08 9:00PM CBS");
		showTimesListModel.add(1, "Mon 09/29/08 10:00PM CBS");
		showTimesListModel.add(2, "Tue 09/30/08 9:00PM CBS");
		showTimesListModel.add(3, "Fri 10/3/08 10:00PM USA");
		showTimesListModel.add(4, "Mon 09/29/08 9:00PM CBS");
		
		showNames = new JList(showNameListModel);
		showTimes = new JList(showTimesListModel);
		nameScroll = new JScrollPane(showNames);
		nameScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		timeScroll = new JScrollPane(showTimes);
		timeScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		showDescription = new JTextArea("Las Vegas criminologists use scientific methods to solve grisly murders in this unusually graphic drama", 10, 20);
		
		searchResults.setBorder(new EmptyBorder(15, 25, 25, 25));
		searchResults.add(nameScroll);
		searchResults.add(Box.createHorizontalStrut(10));
		searchResults.add(timeScroll);
		searchResults.add(Box.createHorizontalStrut(10));
		searchResults.add(showDescription);
		searchResults.add(Box.createHorizontalStrut(10));
		searchResults.add(buttonsPanel);
		
		confirmButton = new LButton("Select");
		backButton = new LButton("Back to Menu");
		buttonsPanel.add(confirmButton);
		buttonsPanel.add(backButton);		
		
		mainPanel.add(Box.createVerticalStrut(15));
		mainPanel.add(labelPanel);
		Component c = Box.createVerticalStrut(10);
		c.setMaximumSize(new Dimension(0,10));
		mainPanel.add(c);
		mainPanel.add(searchPanel);
		mainPanel.add(Box.createGlue());
		mainPanel.add(searchResults);
		
		frame.setTitle("LegendTV - Search TV Guide");
		frame.add(mainPanel);
		frame.pack();
		frame.setVisible(true);
		
	}
	
	
	/**
	 * [Place method description here]
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SearchGuideGUI search = new SearchGuideGUI();
	}

}
