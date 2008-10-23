package view.setupwizard.pages;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import view.setupwizard.SetupWizard;
import view.setupwizard.WizardPage;
import view.utils.UIHelper;

@SuppressWarnings("serial")
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
		
		"Please enter your zip code:"
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
		
		this.layoutComponents();
	}

	private void layoutComponents()
	{
		this.setupExplanatoryText();
		this.setupZipCodeField();
		
		this.add(Box.createVerticalGlue());
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

	private void setupZipCodeField()
	{
		this.zipCodeField	= new JTextArea();
		
		this.zipCodeField.setAlignmentX(LEFT_ALIGNMENT);
		this.zipCodeField.setMaximumSize(new Dimension(100, 60));		
		this.zipCodeField.setFont(UIHelper.getBodyFont());
		
		// Invert colors to make field stand out
		this.zipCodeField.setBackground(UIHelper.getForegroundColor());
		this.zipCodeField.setForeground(UIHelper.getBackgroundColor());
		
		this.add(this.zipCodeField);
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
