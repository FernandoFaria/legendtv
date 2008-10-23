package view.setupwizard.pages;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import view.setupwizard.SetupWizard;
import view.setupwizard.WizardPage;
import view.utils.LimitedLengthDocument;
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
		"knows what shows are being broadcast in your area. The zip code " +
		"detected for your location is shown below.",
		
		"If this information is correct, choose &quot;Next Step >&quot; " +
		"below. If this information is incorrect, please correct it and " +
		"then choose &quot;Next Step >&quot;."
	};
	
	/**
	 * The label that displays the explanatory text.
	 */
	private JLabel		explanatoryLabel;
	
	/**
	 * The panel that contains the zip code label and field.
	 */
	private JPanel		zipCodePanel;
	
	/**
	 * The label next to the zip code field.
	 */
	private JLabel		zipCodeLabel;
	
	/**
	 * The field that the user enters their zip code in.
	 */
	private JTextField	zipCodeField;
	
	/**
	 * Constructor for ListingsSetupPage.
	 * 
	 * @param wizard	The parent wizard instance.
	 */
	public ListingsSetupPage(SetupWizard wizard)
	{
		super(wizard);
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.layoutComponents();
	}

	/**
	 * Method for performing layout of all of the components on this
	 * page.
	 */
	private void layoutComponents()
	{
		this.setupExplanatoryText();
		this.setupZipCodePanel();
		
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
		
		this.explanatoryLabel.setForeground(UIHelper.getForegroundColor());
		this.explanatoryLabel.setFont(UIHelper.getBodyFont());
		
		this.add(Box.createVerticalStrut(UIHelper.STANDARD_MARGIN));
		this.add(this.explanatoryLabel);
	}

	/**
	 * Sets-up the zip code field.
	 */
	private void setupZipCodePanel()
	{
		// Zip code panel
		this.zipCodePanel	= new JPanel();

		this.zipCodePanel.setAlignmentX(LEFT_ALIGNMENT);
		this.zipCodePanel.setOpaque(false);
		this.zipCodePanel.setLayout(
				new BoxLayout(this.zipCodePanel, BoxLayout.X_AXIS));

		this.zipCodePanel.add(Box.createHorizontalGlue());
		
		// Zip code label
		this.zipCodeLabel	= new JLabel("Zip code: ");
		
		this.zipCodeLabel.setFont(UIHelper.getBodyFont());
		this.zipCodeLabel.setForeground(UIHelper.getForegroundColor());
		
		this.zipCodePanel.add(this.zipCodeLabel);
		this.zipCodePanel.add(Box.createHorizontalStrut(10));
		
		// Zip code field
		this.zipCodeField	= new JTextField(
									new LimitedLengthDocument(5),
									"14623", 0);

		this.zipCodeField.setHorizontalAlignment(JTextField.CENTER);
		this.zipCodeField.setPreferredSize(new Dimension(80, 40));
		this.zipCodeField.setMaximumSize(new Dimension(80, 40));		
		this.zipCodeField.setFont(UIHelper.getBodyFont().deriveFont(Font.BOLD));
		
		// Invert colors to make field stand out
		this.zipCodeField.setBackground(UIHelper.getForegroundColor());
		this.zipCodeField.setForeground(UIHelper.getBackgroundColor());
		
		this.zipCodePanel.add(this.zipCodeField);
		this.zipCodePanel.add(Box.createHorizontalGlue());
		
		this.add(this.zipCodePanel);
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
	
	@Override
	public void activate()
	{
		this.zipCodeField.requestFocusInWindow();
	}
}
