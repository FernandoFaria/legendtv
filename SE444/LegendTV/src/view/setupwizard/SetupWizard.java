package view.setupwizard;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import view.MainFrame;
import view.controls.SVGButton;
import view.setupwizard.pages.WelcomePage;
import view.utils.UIHelper;

@SuppressWarnings("serial")
public class SetupWizard extends JComponent
{
	private static final String	TITLE_PREFIX	= "LegendTV Initial Setup: ";	
	private static final String BUTTON_EXIT		= "Exit Setup";
	private static final String BUTTON_PREVIOUS	= "< Previous Step";
	private static final String BUTTON_NEXT		= "Next Step >";
	private static final String BUTTON_FINISH	= "Start Using LegendTV >";
	
	JFrame		parentWindow;
	JLabel		headingLbl;
	JPanel		contentPnl,
				buttonPnl;
	SVGButton	exitBtn,
				prevBtn,
				nextBtn;
	WizardPage	currentPage;
	
	protected void setHeadingText(String heading)
	{
		this.headingLbl.setText(TITLE_PREFIX + heading);
	}

	protected void setCurrentPage(WizardPage page)
	{	
		if (this.currentPage != null)
			this.contentPnl.remove(this.currentPage);
		
		this.contentPnl.add(page, BorderLayout.CENTER);
		this.contentPnl.repaint();
		
		this.currentPage	= page;
		
		this.setHeadingText(page.getTitle());		

		this.prevBtn.setVisible(
				!this.isFirstPage() &&
				!this.currentPage.isModal());
		
		this.nextBtn.setVisible(!this.currentPage.isModal());
		
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
		super();		
		
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
		this.headingLbl	= new JLabel();
		
		this.headingLbl.setForeground(UIHelper.getForegroundColor());
		this.headingLbl.setFont(UIHelper.getHeadingFont());
		this.headingLbl.setText(TITLE_PREFIX);
		
		this.add(this.headingLbl, BorderLayout.NORTH);
	}

	private void setupContentPanel()
	{
		this.contentPnl	= new JPanel();
		
		this.contentPnl.setLayout(new BorderLayout());
		this.contentPnl.setOpaque(false);		
		this.add(this.contentPnl, BorderLayout.CENTER);
	}

	private void setupButtonPanel()
	{
		this.buttonPnl	= new JPanel();
		
		this.buttonPnl.setOpaque(false);
		this.buttonPnl.setPreferredSize(new Dimension(0, 60));
		this.buttonPnl.setLayout(
				new BoxLayout(this.buttonPnl, BoxLayout.X_AXIS));
		
		// Exit Button
		this.exitBtn	= this.createButton(BUTTON_EXIT,
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
		this.prevBtn	= this.createButton(BUTTON_PREVIOUS,
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
		this.nextBtn	= this.createButton(BUTTON_NEXT,
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

	private SVGButton createButton(String caption, ActionListener action)
	{
		SVGButton	button	= null;
		
		try
		{
			button	= new SVGButton(
							caption,
							"images/button_normal.svg",
							"images/button_highlight.svg",
							"images/button_hover.svg",
							"images/button_down.svg");
			
			button.addActionListener(action);
		}
		
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return button;
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

	protected void exitBtnClicked()
	{
		System.exit(0);
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
		
		wizard.setCurrentPage(new WelcomePage());
		
		mainFrame.add(wizard, BorderLayout.CENTER);
		mainFrame.setVisible(true);
	}
}