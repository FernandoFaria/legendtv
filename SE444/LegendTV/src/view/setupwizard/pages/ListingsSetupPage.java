package view.setupwizard.pages;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import view.setupwizard.SetupWizard;
import view.setupwizard.WizardPage;
import view.utils.UIHelper;

public class ListingsSetupPage extends WizardPage
{
	/**
	 * The title of the page.
	 */
	private static final String TITLE = "Setup TV Listings";
	
	/**
	 * The explanatory text on the page.
	 */
	private static final String	TEXT_EXPLANATORY_LINES[] =
	{
		"It is now time to setup TV listings downloads, so that LegendTV " +
		"knows what shows are being broadcast in your area.",
		
		"Please enter your zip code: (TO BE COMPLETED)"
	};
	
	/**
	 * The label that displays the explanatory text.
	 */
	private JLabel		explanatoryLabel;
	
	private JTextArea	zipCodeField;
	
	/**
	 * @param wizard	The parent wizard instance.
	 */
	public ListingsSetupPage(SetupWizard wizard)
	{
		super(wizard);
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		//this.setModal(true);
		
		this.setupExplanatoryText();
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
	
	@Override
	public WizardPage getNextPage()
	{
		return new CompletedSetupPage(this.getWizard());
	}

	@Override
	public String getTitle()
	{
		return TITLE;
	}
}
