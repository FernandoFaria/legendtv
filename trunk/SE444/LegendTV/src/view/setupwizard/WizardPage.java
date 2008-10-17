package view.setupwizard;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public abstract class WizardPage extends JComponent
{
	/**
	 * The page that was displayed right before this one.
	 */
	private WizardPage previousPage;
	
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
	 * Indicates whether or not this page is modal (user cannot navigate away).
	 * By default, all page are non-modal. Pages that need to be modal should
	 * override this default implementation.
	 * 
	 * @return	True if this page is modal, false otherwise.
	 */
	public boolean isModal()
	{
		return false;
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
	 * 
	 * @param	wizard	The containing setup wizard instance.
	 */
	public void activate(SetupWizard wizard)
	{
	}
}
