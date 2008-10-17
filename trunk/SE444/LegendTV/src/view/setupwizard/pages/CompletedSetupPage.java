package view.setupwizard.pages;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import view.setupwizard.SetupWizard;
import view.setupwizard.WizardPage;
import view.utils.UIHelper;

/**
 * The page that singals the completion of the setup wizard.
 * 
 * @author Guy Paddock (gap7472@rit.edu);
 */
@SuppressWarnings("serial")
public class CompletedSetupPage extends WizardPage
{	
	/**
	 * The title of the page.
	 */
	private static final String TITLE = "Setup Completed";
	
	/**
	 * The explanatory text on the page.
	 */
	private static final String	TEXT_EXPLANATORY_LINES[] =
	{
		"Congradulations! Your system has now been set-up.",
		
		"To begin using LegendTV, select 'Start LegendTV' below.",
		
		"Alternatively, if you need to make any changes to the information " +
		"you previously provided, you can select '< Previous Step' below to" +
		"go back to the previous screen."
	};
	
	/**
	 * The label that displays the explanatory text.
	 */
	private JLabel	explanatoryLabel;
	
	/**
	 * Constructor for CompletedSetupPage.
	 * 
	 * @param wizard	The parent wizard instance.
	 */
	public CompletedSetupPage(SetupWizard wizard)
	{
		super(wizard);
		
		layoutControls();		
	}

	/**
	 * Lays out the controls on this page.
	 */
	private void layoutControls()
	{
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		setupExplanatoryText();
	}

	/**
	 * Sets-up the explanatory label & its text.
	 */
	private void setupExplanatoryText()
	{
		this.explanatoryLabel	= new JLabel(
										UIHelper.linesToHtmlText(
											TEXT_EXPLANATORY_LINES));
		
		this.explanatoryLabel.setBorder(new EmptyBorder(20, 0, 0, 0));
		this.explanatoryLabel.setForeground(UIHelper.getForegroundColor());
		this.explanatoryLabel.setFont(UIHelper.getBodyFont());
		this.explanatoryLabel.setPreferredSize(new Dimension(100, 400));
		this.add(this.explanatoryLabel);
	}
	
	/**
	 * Gets the title of this page.
	 * 
	 * @return	The title of this page.
	 */
	@Override
	public String getTitle()
	{
		return TITLE;
	}
	
	/**
	 * Gets the page that follows this one.
	 * 
	 * @return	The next page.
	 */
	@Override
	public WizardPage getNextPage()
	{
		return null; 
	}
}
