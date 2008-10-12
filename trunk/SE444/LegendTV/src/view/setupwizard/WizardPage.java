package view.setupwizard;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public abstract class WizardPage extends JComponent
{
	private WizardPage previousPage;
	
	public WizardPage getPreviousPage()
	{
		return this.previousPage;
	}
	
	public void setPreviousPage(WizardPage previousPage)
	{
		this.previousPage	= previousPage;
	}
	
	public abstract WizardPage getNextPage();
	public abstract String getTitle();
}
