package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import data.RecordedProgram;

import view.LButton;
import view.Program;


/*
 * RecordedProgramsGUI.java
 *
 * Version:
 *	 $Id$
 *
 * Revisions:
 *   $Log$
 */
public class RecordedProgramsGUI extends JPanel implements ActionListener, ListSelectionListener, KeyListener{
	
	//GUI Components for the Frame and Panels
	JFrame frame = new JFrame();
	JPanel mainPanel;
	
	//GUI Components for the Button Bar
	JButton backButton, watchButton, deleteButton, optionsButton;
	
	//GUI Component for the description
	JTextArea programDescription;
	
	//GUI Component for the Label
	JLabel screenDescription = new JLabel("Recorded Programs");
	
	//GUI Components for the Table
	private LinkedList<RecordedProgram> programs;
	private JScrollPane tableScroller;	
	private JTable recordedProgramsTable;
	private Object[][] tableData;
	private DefaultTableModel tableModel;
	private JTableHeader tableHead;
	
	public RecordedProgramsGUI(LinkedList<RecordedProgram> recordedPrograms){
		programs = recordedPrograms;
		//programListModel = new DefaultListModel();
		
		tableData = new Object[ programs.size() ][2];
				
		//Iterate through the LinkedList and add the elements to the ListModel
		Iterator<RecordedProgram> it = programs.iterator();
		int i = 0;
		while(it.hasNext()){
			RecordedProgram temp = (RecordedProgram) it.next();
			tableData[i][0] = temp;
			tableData[i][1] = temp.getRecordedDate();
			i++;
		}
		
		//Set up the specifications for the Table
		tableModel = new DefaultTableModel(tableData, new Object[]{"Title", "Recorded Date"}) {
		    public boolean isCellEditable(int row, int col) { return false; }};
		recordedProgramsTable = new JTable(tableModel);
		recordedProgramsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		recordedProgramsTable.setShowVerticalLines(false);
		recordedProgramsTable.setShowHorizontalLines(false);
		recordedProgramsTable.setBackground(Color.WHITE);
		recordedProgramsTable.setAutoCreateRowSorter(true);
		recordedProgramsTable.changeSelection(0, 0, false, false);
		recordedProgramsTable.getSelectionModel().addListSelectionListener(this);
		recordedProgramsTable.addKeyListener(this);
		tableHead = recordedProgramsTable.getTableHeader();  //The specifications for the column headers
		tableHead.setReorderingAllowed(false);
		tableScroller = new JScrollPane(recordedProgramsTable);  //The scroll bar for the table
		tableScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				
		//Set up the Label
		screenDescription = new JLabel("Recorded Programs");
		screenDescription.setForeground(Color.WHITE);
		Font labelFont = new Font(null, Font.BOLD, 32);
		screenDescription.setFont(labelFont);
		
		//Set up the program description 
		String displayString = programs.get(0).toFullDescription();		
		programDescription = new JTextArea();
		programDescription.setBackground(Color.DARK_GRAY);
		programDescription.setForeground(Color.WHITE);
		programDescription.setText(displayString);
		programDescription.setEditable(false);
		
		//Set up the Button(s)
		backButton = new LButton("Back to TV Menu");
		backButton.setActionCommand("Back");
		backButton.addActionListener(this);
		watchButton = new LButton("Watch");
		watchButton.setActionCommand("Watch");
		watchButton.addActionListener(this);
		deleteButton = new LButton("Delete");
		deleteButton.setActionCommand("Delete");
		deleteButton.addActionListener(this);
		optionsButton = new LButton("Program Options");
		optionsButton.setActionCommand("Options");
		optionsButton.addActionListener(this);
		
		
		//Set up the panels and Boxes
		JPanel programBox = new JPanel(new GridLayout(2,1));
		Box componentBox = Box.createHorizontalBox();
		Box buttonsBox = Box.createVerticalBox();
						
		mainPanel = new JPanel();
		BoxLayout b = new BoxLayout(mainPanel, BoxLayout.Y_AXIS );
		mainPanel.setLayout(b);
		mainPanel.setBorder( new EmptyBorder(10, 45, 20, 10) );
		mainPanel.add(screenDescription);
		mainPanel.add(Box.createVerticalStrut(25));
		mainPanel.add(componentBox);
		mainPanel.setBackground(Color.BLACK);	
				
		componentBox.add(programBox);
		Component horComp = Box.createHorizontalStrut(25);
		horComp.setMaximumSize( new Dimension(0, 25) );
		componentBox.add(horComp);
		componentBox.add(buttonsBox);
		componentBox.setAlignmentX((float)0.0);
		
		programBox.add(programDescription);
		programBox.add(tableScroller);
		tableScroller.setBorder( new EmptyBorder(15, 0, 10, 0) );
		tableScroller.setBackground(Color.BLACK);
		programBox.setBackground(Color.BLACK);
		
		buttonsBox.add(watchButton);
		buttonsBox.add(deleteButton);
		buttonsBox.add(optionsButton);
		Component c = Box.createVerticalStrut(35);
		c.setMaximumSize(new Dimension(0,35));
		buttonsBox.add(c);
		buttonsBox.add(backButton);
		
		//Set the focus changes for the button box
		watchButton.addKeyListener(this);
		deleteButton.addKeyListener(this);
		optionsButton.addKeyListener(this);
		backButton.addKeyListener(this);
			
		//Frame Settings 
		frame.add(mainPanel);
		frame.setTitle("LegendTV - Recorded Programs");
		frame.pack();
		frame.setVisible(true);
		
		//Grant Focus to the first thing in the table
		recordedProgramsTable.requestFocusInWindow();
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String command = e.getActionCommand();
		
		if(command.equals("Delete")){
			Object[] options = {"Confirm Delete", "Cancel Delete"};
			int n = JOptionPane.showOptionDialog(frame, "Are you sure you want to delete?", "Confirmation Screen",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					null,     //do not use a custom Icon
					options,  //the titles of buttons
					options[1]); //default button title
			if( n == 0){
				System.out.println("Delete: " + recordedProgramsTable.getSelectedRow());
				int selectedRow = recordedProgramsTable.getSelectedRow();
				Object o = recordedProgramsTable.getValueAt(selectedRow, 0);
				recordedProgramsTable.changeSelection(selectedRow + 1, 0, false, false);
				programs.remove(o);
				tableModel.removeRow(selectedRow);
			}
			
		}else if(command.equals("Watch")){
			
		}else if(command.equals("Options")){
			
		}else if(command.equals("Back")){
			
		}
		
	}
	
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		if(recordedProgramsTable.getSelectedRow() == -1){
			return;
		}
		System.out.println("Change: " + recordedProgramsTable.getSelectedRow());
		int selectedRow = recordedProgramsTable.getSelectedRow();
		RecordedProgram o = (RecordedProgram) recordedProgramsTable.getValueAt(selectedRow, 0);
		programDescription.setText(o.toFullDescription());
	}
	
	
	public static void main(String[] args){
		LinkedList<RecordedProgram> list = new LinkedList<RecordedProgram>();
		list.add( new RecordedProgram("Family Guy", "Peter does something stupid!", Program.TV_14, 30, "10/10/2008", "10/30/2008") );
		list.add( new RecordedProgram("Family Guy", "Peter does something stupid Again!", Program.TV_MA, 30, "10/10/2008", "10/30/2008") );
		list.add( new RecordedProgram("Price is Right!", "We miss Bob Barker...", Program.TV_14, 30, "10/10/2008", "10/30/2008") );
		list.add( new RecordedProgram("CSI: NY", "Every cast member is gone...", Program.TV_14, 30, "10/11/2008", "10/30/2008") );
		list.add( new RecordedProgram("CSI: RIT", "Someone has stolen the tiger!", Program.TV_14, 30, "10/12/2008", "10/30/2008") );
		list.add( new RecordedProgram("Altos", "There is drama in Altoville", Program.TV_14, 30, "10/08/2008", "10/30/2008") );
		list.add( new RecordedProgram("CSI: Miami", "It looks like bird.... is the word...", Program.TV_14, 30, "10/15/2008", "10/30/2008") );
		list.add( new RecordedProgram("CSI: Miami", "Why did you tape this show?", Program.TV_14, 30, "10/22/2008", "10/30/2008") );
		list.add( new RecordedProgram("Altos", "Peter does something stupid! And he's not even in this show!", Program.TV_G, 30, "10/10/2008", "10/30/2008") );
		list.add( new RecordedProgram("Altos", "The Sopranos are getting jealous...", Program.TV_14, 30, "10/12/2008", "10/30/2008") );
		list.add( new RecordedProgram("Altos", "Altos make perfect harmony with the Tenors", Program.TV_14, 30, "10/14/2008", "10/30/2008") );
		list.add( new RecordedProgram("Altos", "I'm running out of ideas...", Program.TV_14, 30, "10/16/2008", "10/30/2008") );
		list.add( new RecordedProgram("Altos", "And I'm done!", Program.TV_14, 30, "10/18/2008", "10/30/2008") );

		
		RecordedProgramsGUI a = new RecordedProgramsGUI(list);
	}

	public void keyPressed(KeyEvent key) {
		// TODO Auto-generated method stub
		if(key.getComponent().equals(recordedProgramsTable)){
			if(key.getKeyCode() == (KeyEvent.VK_RIGHT)){
				watchButton.requestFocusInWindow();
			}
		}else if(key.getComponent().equals(watchButton)){
			if(key.getKeyCode() == KeyEvent.VK_DOWN){
				deleteButton.requestFocusInWindow();
			}else if(key.getKeyCode() == KeyEvent.VK_UP ){
				backButton.requestFocusInWindow();
			}else if(key.getKeyCode() == KeyEvent.VK_LEFT){
				recordedProgramsTable.requestFocusInWindow();
			}else if(key.getKeyCode() == KeyEvent.VK_ENTER){
				watchButton.doClick();
			}
		}else if(key.getComponent().equals(deleteButton)){
			if(key.getKeyCode() == KeyEvent.VK_DOWN){
				optionsButton.requestFocusInWindow();
			}else if(key.getKeyCode() == KeyEvent.VK_UP ){
				watchButton.requestFocusInWindow();
			}else if(key.getKeyCode() == KeyEvent.VK_LEFT){
				recordedProgramsTable.requestFocusInWindow();
			}else if(key.getKeyCode() == KeyEvent.VK_ENTER){
				deleteButton.doClick();
			}
		}else if(key.getComponent().equals(optionsButton)){
			if(key.getKeyCode() == KeyEvent.VK_DOWN){
				backButton.requestFocusInWindow();
			}else if(key.getKeyCode() == KeyEvent.VK_UP ){
				deleteButton.requestFocusInWindow();
			}else if(key.getKeyCode() == KeyEvent.VK_LEFT){
				recordedProgramsTable.requestFocusInWindow();
			}else if(key.getKeyCode() == KeyEvent.VK_ENTER){
				optionsButton.doClick();
			}
		}else if(key.getComponent().equals(backButton)){
			if(key.getKeyCode() == KeyEvent.VK_DOWN){
				watchButton.requestFocusInWindow();
			}else if(key.getKeyCode() == KeyEvent.VK_UP ){
				optionsButton.requestFocusInWindow();
			}else if(key.getKeyCode() == KeyEvent.VK_LEFT){
				recordedProgramsTable.requestFocusInWindow();
			}else if(key.getKeyCode() == KeyEvent.VK_ENTER){
				backButton.doClick();
			}
		}
		
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
