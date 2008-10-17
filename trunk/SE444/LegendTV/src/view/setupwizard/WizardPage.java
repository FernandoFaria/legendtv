package view.setupwizard;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public abstract class WizardPage extends JComponent
{
	/**
	 * The parent setup wizard instance of this page.
	 */
	private SetupWizard	wizard;
	
	/**
	 * The page that was displayed right before this one.
	 */
	private WizardPage 	previousPage;
	
	/**
	 * Whether or not this page is modal.
	 */
	private boolean		isModal;
	
	/**
	 * Initializes this wizard page with the specified parent wizard instance.
	 * 
	 * @param wizard	The parent wizard instance.
	 */
	public WizardPage(SetupWizard wizard)
	{
		this.wizard	= wizard;
	}
	
	/**
	 * Gets the page that was displayed right before this one.
	 * 
	 * @return	The previous page.
	 */
	public WizardPage getPreviousPage()
	{
		return this.previousPage;
	}
	
	/**
	 * Sets which page was displayed right before this one.
	 * 
	 * @param previousPage	The new previous page.
	 */
	public void setPreviousPage(WizardPage previousPage)
	{
		this.previousPage	= previousPage;
	}
	
	/**
	 * Gets the page that follows this one.
	 * 
	 * @return	The next page.
	 */
	public abstract WizardPage getNextPage();
	
	/**
	 * Indicates whether or not this page is modal. If true, the user must
	 * interact with this page before beign allowed to navigate to the next
	 * page.
	 * 
	 * @return	True if this page is modal, false otherwise.
	 */
	public boolean isModal()
	{
		return this.isModal;
	}

	/**
	 * Gets the title of this page.
	 * 
	 * @return	The title of this page.
	 */
	public abstract String getTitle();
	
	/**
	 * Called when this page becomes active. This enables pages to perform
	 * actions as soon as they are visible.
	 * 
	 * The default implementation does nothing.
	 */
	public void activate()
	{
	}
	
	/**
	 * Gets the parent setup wizard instance.
	 * 
	 * @return	The parent setup wizard instance.
	 */
	protected SetupWizard getWizard()
	{
		return this.wizard;
	}
		
	/**
	 * Sets whether this page is modal or not.
	 * 
	 * @param isModal	True if this page should be modal; false otherwise.
	 */
	protected void setModal(boolean isModal)
	{
		this.isModal	= isModal;
	}
}
