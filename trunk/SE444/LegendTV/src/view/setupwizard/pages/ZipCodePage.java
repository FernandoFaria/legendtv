package view.setupwizard.pages;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.InputMap;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import view.setupwizard.SetupWizard;
import view.setupwizard.WizardPage;
import view.utils.EditableFocusMappingDisabler;
import view.utils.LimitedLengthDocument;
import view.utils.UIHelper;

@SuppressWarnings("serial")
public class ZipCodePage extends WizardPage
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
	public ZipCodePage(SetupWizard wizard)
	{
		super(wizard);
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.layoutComponents();
		this.setModal(false);
	}
	
	@Override
	public WizardPage getNextPage()
	{
		return new TVReceptionMethodPage(this.getWizard());
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
		
		this.setupZipCodeField();
		
		this.add(this.zipCodePanel);
	}

	private void setupZipCodeField()
	{
		InputMap zipInputMap;
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
		
		zipInputMap	= this.zipCodeField.getInputMap();
		
		// Map ESC to BACKSPACE (Remote "CLEAR" button)
		zipInputMap.put(
				KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
				zipInputMap.get(
						KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0)));
		
		this.zipCodeField.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent e)
			{
				// Allow ENTER to go to the next component
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					ZipCodePage.this.zipCodeField.transferFocus();
			}
			
			@Override
			public void keyReleased(KeyEvent e)
			{
				// Don't allow the user to proceed with an incorrect zip code
				ZipCodePage.this.setModal(
						!ZipCodePage.this.isValidZipCode());
				
				ZipCodePage.this.getWizard().refreshButtonState();
			}
		});
		
		// Remove the mappings on the arrow keys when editing text
		//this.zipCodeField.addFocusListener(new EditableFocusMappingDisabler());
		
		// Add verification of the zip code
		this.zipCodeField.setInputVerifier(new InputVerifier()
		{
			@Override
			public boolean verify(JComponent input)
			{
				return ZipCodePage.this.isValidZipCode();
			}
		});
		
		this.zipCodePanel.add(this.zipCodeField);
		this.zipCodePanel.add(Box.createHorizontalGlue());
	}
	
	private boolean isValidZipCode()
	{
		String	zipCode = ZipCodePage.this.zipCodeField.getText();
		boolean matches	= zipCode.matches("^\\d{5}$");
		
		return matches;
	}
	
	
}
