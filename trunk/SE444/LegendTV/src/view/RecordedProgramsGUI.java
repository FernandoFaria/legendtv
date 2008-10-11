package view;

import java.awt.*;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
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
public class RecordedProgramsGUI {
	
	//GUI Components for the Frame and Panels
	JFrame frame = new JFrame();
	JPanel mainPanel;
	
	//GUI Components for the Button Bar
	JButton backButton, selectButton, deleteButton;
	
	//GUI Component for the description
	JTextArea programDescription;
	
	//GUI Component for the Label
	JLabel screenDescription = new JLabel("Recorded Programs");
	
	//GUI Components for the Table
	private LinkedList<RecordedProgram> programs;
	private JScrollPane tableScroller;	
	private JTable recordedProgramsTable;
	private JTableHeader tableHead;
	
	public RecordedProgramsGUI(LinkedList<RecordedProgram> recordedPrograms){
		programs = recordedPrograms;
		//programListModel = new DefaultListModel();
		
		Object[][] tableData = new Object[ programs.size() ][2];
				
		//Iterate through the LinkedList and add the elements to the ListModel
		Iterator<RecordedProgram> it = programs.iterator();
		int i = 0;
		while(it.hasNext()){
			RecordedProgram temp = (RecordedProgram) it.next();
			tableData[i][0] = temp.getTitle();
			tableData[i][1] = temp.getRecordedDate();
			i++;
		}
		
		//Set up the specifications for the Table
		recordedProgramsTable = new JTable(new DefaultTableModel(tableData, new Object[]{"Title", "Recorded Date"})) {
		    public boolean isCellEditable(int row, int col) { return false; }
		};
		recordedProgramsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		recordedProgramsTable.setShowVerticalLines(false);
		recordedProgramsTable.setShowHorizontalLines(false);
		recordedProgramsTable.setBackground(Color.WHITE);
		recordedProgramsTable.setAutoCreateRowSorter(true);
		tableHead = recordedProgramsTable.getTableHeader();  //The specifications for the column headers
		tableHead.setReorderingAllowed(false);
		tableScroller = new JScrollPane(recordedProgramsTable);  //The scroll bar for the table
		//tableScroller.setPreferredSize(new Dimension(1000, 500) );
		tableScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);	
				
		//Set up the Label
		screenDescription = new JLabel("Recorded Programs");
		screenDescription.setForeground(Color.WHITE);
		Font labelFont = new Font(null, Font.BOLD, 32);
		screenDescription.setFont(labelFont);
		
		//Set up the program description 
		String displayString = programs.get(0).toString();		
		programDescription = new JTextArea();
		programDescription.setBackground(Color.DARK_GRAY);
		programDescription.setForeground(Color.WHITE);
		programDescription.setText(displayString);
		programDescription.setEditable(false);
		
		//Set up the Button(s)
		backButton = new LButton("Go Back");
		selectButton = new LButton("Select");
		deleteButton = new LButton("Delete");
		
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
		//mainPanel.add(Box.createGlue());
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
		
		buttonsBox.add(deleteButton);
		buttonsBox.add(selectButton);
		Component vertComp = Box.createVerticalStrut(50);
		vertComp.setMaximumSize( new Dimension(50, 0) );
		//buttonsBox.add();
		buttonsBox.add(backButton);		
			
		//Frame Settings 
		frame.add(mainPanel);
		frame.setTitle("LegendTV - Recorded Programs");
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void main(String[] args){
		LinkedList<RecordedProgram> list = new LinkedList<RecordedProgram>();
		list.add( new RecordedProgram("Family Guy", "Peter does something stupid!", Program.TV_14, 30, "10/10/2008", "10/30/2008") );
		list.add( new RecordedProgram("Family Guy", "Peter does something stupid!", Program.TV_14, 30, "10/10/2008", "10/30/2008") );
		list.add( new RecordedProgram("Price is Right!", "Peter does something stupid!", Program.TV_14, 30, "10/10/2008", "10/30/2008") );
		list.add( new RecordedProgram("CSI: NY", "Peter does something stupid!", Program.TV_14, 30, "10/11/2008", "10/30/2008") );
		list.add( new RecordedProgram("CSI: RIT", "Peter does something stupid!", Program.TV_14, 30, "10/12/2008", "10/30/2008") );
		list.add( new RecordedProgram("Altos", "Peter does something stupid!", Program.TV_14, 30, "10/08/2008", "10/30/2008") );
		list.add( new RecordedProgram("Altos", "Peter does something stupid!", Program.TV_14, 30, "10/15/2008", "10/30/2008") );
		list.add( new RecordedProgram("Altos", "Peter does something stupid!", Program.TV_14, 30, "10/22/2008", "10/30/2008") );
		list.add( new RecordedProgram("Altos", "Peter does something stupid!", Program.TV_14, 30, "10/10/2008", "10/30/2008") );
		list.add( new RecordedProgram("Altos", "Peter does something stupid!", Program.TV_14, 30, "10/10/2008", "10/30/2008") );
		list.add( new RecordedProgram("Altos", "Peter does something stupid!", Program.TV_14, 30, "10/10/2008", "10/30/2008") );
		list.add( new RecordedProgram("Altos", "Peter does something stupid!", Program.TV_14, 30, "10/10/2008", "10/30/2008") );
		list.add( new RecordedProgram("Altos", "Peter does something stupid!", Program.TV_14, 30, "10/10/2008", "10/30/2008") );

		
		RecordedProgramsGUI a = new RecordedProgramsGUI(list);
	}

}
