package view.setupwizard;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import view.controls.SVGButton;
import view.setupwizard.pages.WelcomePage;
import view.utils.UIHelper;

/**
 * The setup wizard that the user sees the first time they start LegendTV.
 * 
 * This walks the user through the process of:
 * <ol>
 * 	<li>Calibrating his or her display device.</li>
 *  <li>Detecting & setting-up tuner cards</li>
 *  <li>Setting-up TV listings</li>
 *  <li>Setting-up recorder storage</li>
 * </ol>
 *  
 * @author Guy Paddock (gap7472@rit.edu)
 */
@SuppressWarnings("serial")
public class SetupWizard extends JPanel
{
	/**
	 * The title that prefixes all headings
	 */
	private static final String	TITLE_PREFIX	= "LegendTV Setup: ";
	
	/**
	 * The text displayed on the exit button.
	 */
	private static final String BUTTON_EXIT		= "Exit Setup";
	
	/**
	 * The text displayed on the previous button.
	 */
	private static final String BUTTON_PREVIOUS	= "< Previous Step";
	
	/**
	 * The text displayed on the next button when another page is next.
	 */
	private static final String BUTTON_NEXT		= "Next Step >";
	
	/**
	 * The text displayed on the next button when the user is viewing the
	 * last wizard page.
	 */
	private static final String BUTTON_FINISH	= "Start LegendTV";
	
	/**
	 * The label for the text heading at the top of screens.
	 */
	JLabel		headingLbl;
	
	/**
	 * The panel that contains the contents of the wizard (i.e. placeholder
	 * for each wizard page).
	 */
	JPanel		contentPnl;
	
	/**
	 * The panel at the bottom of the wizard screen that contains the exit,
	 * next, and previous buttons.
	 */
	JPanel		buttonPnl;
	
	/**
	 * The exit button.
	 */
	SVGButton	exitBtn;
	
	/**
	 * The previous button.
	 */
	SVGButton	prevBtn;
	
	/**
	 * The next button.
	 */
	SVGButton	nextBtn;
	
	/**
	 * The current wizard page.
	 */
	WizardPage	currentPage;

	/**
	 * Constructor for SetupWizard.
	 */
	public SetupWizard()
	{
		super();		
		
		this.layoutControls();
		
		this.setCurrentPage(new WelcomePage(this));
	}

	/**
	 * Registers a wizard event listener, which will be informed when
	 * events occur in this wizard instance.
	 * 
	 * @param listener	The listener to register.
	 */
	public void addEventListener(WizardEventListener listener)
	{
		this.listenerList.add(WizardEventListener.class, listener);
	}

	/**
	 * Unregisters a wizard event listener, which will no longer be informed of
	 * events that occur in this wizard instance.
	 * 
	 * @param listener	The listener to unregister.
	 */
	public void removeEventListener(WizardEventListener listener)
	{
		this.listenerList.remove(WizardEventListener.class, listener);
	}
	
	/**
	 * Gets whether or not the wizard is currently at the first page.
	 * 
	 * @return	True if the first page of the wizard is currently being
	 * 			displayed, false otherwise.
	 */	
	public boolean isAtFirstPage()
	{
		return (this.currentPage.getPreviousPage() == null);
	}
	
	/**
	 * Gets whether or not the wizard is currently at the last page.
	 * 
	 * @return	True if the last page of the wizard is currently being
	 * 			displayed, false otherwise.
	 */	
	public boolean isAtLastPage()
	{
		return (this.currentPage.getNextPage() == null);
	}
	
	/**
	 * Advances the wizard to the next page. This method should not be
	 * called if the wizard is at the last page.
	 * 
	 * @throws	IllegalArgumentException	If the wizard is at the last page
	 * 										when this method is called.
	 * 										(isAtLastPage() is true)
	 * @see     SetupWizard#isAtLastPage() 
	 */
	public void goToNextPage()
	throws IllegalArgumentException
	{
		WizardPage	nextPage	= this.currentPage.getNextPage();
		
		if (this.isAtLastPage())
		{
			throw new IllegalStateException(
					"The wizard is at the last page -- there is no next page " +
					"defined!");
		}
		
		nextPage.setPreviousPage(this.currentPage);
		this.setCurrentPage(nextPage);
	}

	/**
	 * Returns the wizard to the previous page. This method should not be
	 * called if the wizard is at the first page.
	 * 
	 * @throws	IllegalArgumentException	If the wizard is at the first page
	 * 										when this method is called.
	 * 										(isAtFirstPage() is true)
	 * @see     SetupWizard#isAtFirstPage() 
	 */
	public void goToPreviousPage()
	{
		WizardPage	previousPage = this.currentPage.getPreviousPage();
		
		if (this.isAtLastPage())
		{
			throw new IllegalStateException(
					"The wizard is at the first page -- there is no previous " +
					"page defined!");
		}
		
		this.setCurrentPage(previousPage);
	}
	
	/**
	 * Method called to force the wizard to re-evaluate what buttons
	 * are enabled and visible based on information from the current wizard
	 * pages.
	 * 
	 * Wizard pages must call this method after changing internal state that
	 * might affect the use of the next and previous buttons.
	 */
	public void refreshButtonState()
	{
		this.prevBtn.setVisible(
				!this.isAtFirstPage());
		
		this.nextBtn.setVisible(!this.currentPage.isModal());
		
		if (this.isAtLastPage())
			this.nextBtn.setText(BUTTON_FINISH);
		
		else
			this.nextBtn.setText(BUTTON_NEXT);
	}
	
	/**
	 * Sets the text displayed in the heading of the wizard.
	 * 
	 * @param heading	The new heading text.
	 */
	private void setHeadingText(String heading)
	{
		this.headingLbl.setText(TITLE_PREFIX + heading);
	}

	/**
	 * Sets the page currently being displayed in the wizard.
	 * 
	 * @param page	The new wizard page to display.
	 */
	private void setCurrentPage(WizardPage page)
	{	
		if (this.currentPage != null)
			this.contentPnl.remove(this.currentPage);
		
		this.contentPnl.setOpaque(false);
		this.contentPnl.add(page, BorderLayout.CENTER);
		this.contentPnl.repaint();
		
		this.currentPage	= page;
		
		this.setHeadingText(page.getTitle());		

		refreshButtonState();
		
		this.currentPage.activate();
	}
	
	/**
	 * Lays-out the controls on the wizard screen.
	 */
	private void layoutControls()
	{
		this.setBackground(UIHelper.getBackgroundColor());
		this.setForeground(UIHelper.getForegroundColor());
		
		this.setOpaque(true);
		this.setBorder(new EmptyBorder(10, 10, 10, 10));
		this.setLayout(new BorderLayout());
						
		setupHeadingText();
		setupContentPanel();
		setupButtonPanel();
	}
	
	/**
	 * Sets-up the heading text displayed at the top of the screen.
	 */
	private void setupHeadingText()
	{
		this.headingLbl	= new JLabel();
		
		this.headingLbl.setForeground(UIHelper.getForegroundColor());
		this.headingLbl.setFont(UIHelper.getHeadingFont());
		this.headingLbl.setText(TITLE_PREFIX);
		
		this.add(this.headingLbl, BorderLayout.NORTH);
	}

	/**
	 * Sets-up the content panel, which contains the page that the wizard
	 * is currently displaying.
	 */
	private void setupContentPanel()
	{
		this.contentPnl	= new JPanel();
		
		this.contentPnl.setLayout(new BorderLayout());
		this.contentPnl.setOpaque(false);
		
		this.add(this.contentPnl, BorderLayout.CENTER);
	}

	/**
	 * Sets-up the button panel along the bottom of the wizard screen.
	 */
	private void setupButtonPanel()
	{
		this.buttonPnl	= new JPanel();
		
		this.buttonPnl.setOpaque(false);
		this.buttonPnl.setPreferredSize(new Dimension(0, 80));
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
	
	/**
	 * Utility method for creating a button that can be put into the button
	 * panel at the bottom of the wizard screen.
	 * 
	 * @param caption	The text to put on the button.
	 * @param action	The action to perform when the button is clicked.
	 * @return			A new SVGButton instance with the specified properties.
	 */
	private SVGButton createButton(String caption, ActionListener action)
	{
		SVGButton	button	= UIHelper.createButton(caption, action);
		
		// FIXME: This should be done automatically!
		button.setPreferredSize(new Dimension(230, 76));
		button.setMinimumSize(new Dimension(230, 76));
		button.setMaximumSize(new Dimension(230, 76));
		
		return button;
	}
	
	/**
	 * Method called when the user clicks the "Previous" button.
	 */
	private void prevBtnClicked()
	{
		goToPreviousPage();
	}
	
	/**
	 * Method called when the user clicks the "Next" button.
	 */
	private void nextBtnClicked()
	{
		if (!this.isAtLastPage())
			goToNextPage();
		
		else
			finishAndClose();
	}

	/**
	 * Method called when the user clicks the "Exit" button.
	 */
	private void exitBtnClicked()
	{
		// FIXME: This should be a shield pattern! We don't want the user losing
		//		  a lot of the information they provided during set-up if
		//		  they accidentally closed it.
		System.exit(0);
	}
	
	/**
	 * Method called when the user has completed the setup wizard.
	 */
	private void finishAndClose()
	{
		for (WizardEventListener listener :
			this.listenerList.getListeners(WizardEventListener.class))
		{
			listener.wizardCompleted();
		}
	}
}