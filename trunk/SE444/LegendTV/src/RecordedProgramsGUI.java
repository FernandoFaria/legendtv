
import java.util.*;
import java.awt.*;
import javax.swing.*;


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
	JButton backButton;
	
	//GUI Component for the description
	JTextArea programDescription;
	
	//GUI Component for the Label
	JLabel screenDescription = new JLabel("Recorded Programs");
	
	//GUI Components for the List
	private LinkedList programs;
	private JList programList;
	private DefaultListModel programListModel;
	private JScrollPane listScroller;
	
	private Insets in = new Insets(0,0,0,0);
	
	//Icons
	private Icon testIcon= new ImageIcon("images/button_on_short.png");
	
	public RecordedProgramsGUI(LinkedList recordedPrograms){
		programs = recordedPrograms;
		programListModel = new DefaultListModel();
		
		//Iterate through the LinkedList and add the elements to the ListModel
		Iterator it = programs.iterator();
		int i = 0;
		while(it.hasNext()){
			programListModel.add(i, it.next());
			i++;
		}
		programList = new JList(programListModel);
		
		programList.setFixedCellHeight(20);
		programList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		listScroller = new JScrollPane(programList);
		listScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
				
		//Set up the Label
		screenDescription = new JLabel("Recorded Programs");
		screenDescription.setForeground(Color.WHITE);
		Font labelFont = new Font(null, Font.BOLD, 32);
		screenDescription.setFont(labelFont);
		
		//Set up the program description 
		//Hard Coded in right now... Make dynamic in the future
		programDescription = new JTextArea();
		programDescription.setBackground(Color.DARK_GRAY);
		programDescription.setForeground(Color.WHITE);
		programDescription.setText("Peter does something stupid!!! ");
		programDescription.setEditable(false);
		
		//Set up the Button(s)

		backButton = new JButton("Go Back", testIcon);  //Include the Image in this line
		backButton.setForeground(Color.WHITE);
		backButton.setIconTextGap(0);
		
		//button.setIcon(testIcon2);
		backButton.setBackground(Color.BLACK);
		//button.setForeground(Color.WHITE);
		backButton.setMargin(in);
		backButton.setVerticalTextPosition(SwingConstants.CENTER);
		backButton.setHorizontalTextPosition(SwingConstants.CENTER);
		backButton.setBorderPainted(false);
		backButton.setRolloverIcon(testIcon);
		
		//Set up the panels and Boxes
		JPanel programBox = new JPanel(new GridLayout(2,1));
		Box componentBox = Box.createHorizontalBox();
		//componentBox.
		Box buttonsBox = Box.createVerticalBox();
				
		mainPanel = new JPanel();
		BoxLayout b = new BoxLayout(mainPanel, BoxLayout.Y_AXIS );
		mainPanel.setLayout(b);
		mainPanel.add(screenDescription);
		mainPanel.add(componentBox);
		mainPanel.setBackground(Color.BLACK);	
				
		componentBox.add(buttonsBox);
		componentBox.add(programBox);
		componentBox.setAlignmentX((float)0.0);
		
		programBox.add(programDescription);
		programBox.add(listScroller);
		
		buttonsBox.add(backButton);
			
		//Frame Settings 
		frame.add(mainPanel);
		frame.setTitle("LegendTV - Recorded Programs");
		frame.setSize(600, 800);
		frame.setVisible(true);
	}
	
	public static void main(String[] args){
		LinkedList list = new LinkedList();
		list.add("Program 1");
		list.add("Program 2");
		list.add("Program 3");
		list.add("Program 4");

		
		RecordedProgramsGUI a = new RecordedProgramsGUI(list);
	}

}
