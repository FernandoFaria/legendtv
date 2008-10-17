package view.setupwizard.pages;

import javax.swing.JLabel;

import view.setupwizard.SetupWizard;
import view.setupwizard.WizardPage;

/**
 * The page that discovers how the user receives TV.
 * 
 * @author Guy Paddock (gap7472@rit.edu);
 */
@SuppressWarnings("serial")
public class TunerSpacesPage extends WizardPage
{
	
	private static String TITLE = "TV Reception Method";	

	/**
	 * The explanatory text on the page.
	 */
	private static final String	TEXT_EXPLANATORY_LINES[] =
	{
		"Welcome to LegendTV! This step-by-step wizard will help you setup "+
		"your system. <br /> This process should take approximately 25 minutes.",
		
		"If you are short on time, you may exit this process at any point "+
		"by selecting 'Exit Setup' below. <br /> Your place will be "+
		"saved and you can restart where you left off at a later time, but you "+
		"will not be able to use LegendTV until the setup process is complete.",
		
		"If you are ready to proceed, select 'Next Step >' below."
	};
	
	/**
	 * The label that displays the explanatory text.
	 */
	private JLabel	explanatoryLabel;
	
	/**
	 * @param wizard	The parent wizard instance.
	 */
	public TunerSpacesPage(SetupWizard wizard)
	{
		super(wizard);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public WizardPage getNextPage()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTitle()
	{
		// TODO Auto-generated method stub
		return TITLE;
	}
}
