
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class SetupWizard extends JComponent
{
	private static final String	TITLE_PREFIX	= "LegendTV Setup - ";	
	private static final String BUTTON_EXIT		= "Exit System Setup";
	private static final String BUTTON_PREVIOUS	= "< Previous Step";
	private static final String BUTTON_NEXT		= "Next Step >";
	private static final String BUTTON_FINISH	= "Start Using LegendTV >";
	
	JFrame		parentWindow;
	JLabel		headingLbl;
	JPanel		contentPnl,
				buttonPnl;
	JButton		exitBtn,
				prevBtn,
				nextBtn;
	WizardPage	currentPage;
	
	protected void setHeadingText(String heading)
	{
		this.headingLbl.setText(TITLE_PREFIX + heading);
	}

	protected void setCurrentPage(WizardPage page)
	{
		this.currentPage	= page;
		
		this.setHeadingText(page.getTitle());		

		this.prevBtn.setVisible(!this.isFirstPage());
				
		if (this.isLastPage())
			this.nextBtn.setText(BUTTON_FINISH);
		
		else
			this.nextBtn.setText(BUTTON_NEXT);
	}
	
	protected boolean isFirstPage()
	{
		return (this.currentPage.getPreviousPage() == null);
	}
	
	protected boolean isLastPage()
	{
		return (this.currentPage.getNextPage() == null);
	}
	
	public SetupWizard()
	{
		this.headingLbl	= new JLabel();
		this.contentPnl	= new JPanel();
		this.buttonPnl	= new JPanel();
		this.exitBtn	= new JButton();
		this.prevBtn	= new JButton();
		this.nextBtn	= new JButton();
		
		this.setupControls();
	}
	
	private void setupControls()
	{
		// Inherit background
		this.setOpaque(false);

		this.setBorder(new EmptyBorder(10, 10, 10, 10));
		this.setLayout(new BorderLayout());
						
		setupHeadingText();
		setupContentPanel();
		setupButtonPanel();
	}
	
	private void setupHeadingText()
	{
		this.headingLbl.setForeground(Color.WHITE);
		this.headingLbl.setFont(new Font("Segoe UI", Font.PLAIN, 48));
		this.headingLbl.setText(TITLE_PREFIX);
		
		this.add(this.headingLbl, BorderLayout.NORTH);
	}

	private void setupContentPanel()
	{
		this.contentPnl.setOpaque(false);
		this.contentPnl.setOpaque(false);		
		this.add(this.contentPnl, BorderLayout.CENTER);
	}

	private void setupButtonPanel()
	{
		this.buttonPnl.setOpaque(false);
		this.buttonPnl.setPreferredSize(new Dimension(0, 60));
		this.buttonPnl.setLayout(
				new BoxLayout(this.buttonPnl, BoxLayout.X_AXIS));
		
		// Exit Button
		this.setupButton(this.exitBtn, BUTTON_EXIT,
				new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						exitBtnClicked();
					}
				});
		
		this.buttonPnl.add(this.exitBtn);
		this.buttonPnl.add(Box.createHorizontalGlue());
		
		// Previous button		
		this.setupButton(this.prevBtn, BUTTON_PREVIOUS,
				new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						prevBtnClicked();
					}
				});
		
		this.buttonPnl.add(this.prevBtn);
		
		// Next button
		this.setupButton(this.nextBtn, BUTTON_NEXT,
				new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						nextBtnClicked();
					}
				});
		
		this.buttonPnl.add(Box.createHorizontalStrut(5));
		this.buttonPnl.add(this.nextBtn);
		
		this.add(this.buttonPnl, BorderLayout.SOUTH);
	}

	private void setupButton(JButton button, String caption,
							 ActionListener action)
	{
		Dimension	size	= new Dimension(250, 50);
		
		button.setPreferredSize(size);
		button.setMinimumSize(size);
		button.setMaximumSize(size);
		button.setText(caption);
		button.addActionListener(action);
	}
	
	private void goToNextPage()
	{
		WizardPage	nextPage	= this.currentPage.getNextPage();
		
		nextPage.setPreviousPage(this.currentPage);
		this.setCurrentPage(nextPage);
	}

	private void goToPreviousPage()
	{
		this.setCurrentPage(this.currentPage.getPreviousPage());
	}

	protected void exitBtnClicked()
	{
		System.exit(0);
	}

	protected void prevBtnClicked()
	{
		goToPreviousPage();
	}
	
	private void nextBtnClicked()
	{
		if (!this.isLastPage())
			goToNextPage();
		
		else
			finishAndClose();
	}

	private void finishAndClose()
	{
		// TODO Advance to main menu
		System.exit(0);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		JFrame		mainFrame	= new MainFrame();
		SetupWizard	wizard		= new SetupWizard();
		
		wizard.setCurrentPage(new DisplayDevicePage());
		
		mainFrame.add(wizard, BorderLayout.CENTER);
		mainFrame.setVisible(true);
	}
}