package view.setupwizard.pages;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import view.controls.HorizontalSpinner;
import view.setupwizard.SetupWizard;
import view.setupwizard.WizardPage;
import view.utils.EditableFocusMappingDisabler;
import view.utils.UIHelper;

/**
 * The page that discovers how the user receives TV.
 * 
 * @author Guy Paddock (gap7472@rit.edu);
 */
@SuppressWarnings("serial")
public class TVReceptionMethodPage extends WizardPage
{	
	private static String TITLE = "How do you receive TV?";	

	/**
	 * The explanatory text on the page.
	 */
	private static final String	TEXT_EXPLANATORY_LINES[] =
	{
		"In order to proceed, LegendTV needs to know how you receive your " +
		"television signal.",
		
		"Please indicate the method(s) by which you receive television:"
	};
	
	/**
	 * The label that displays the explanatory text.
	 */
	private JLabel	explanatoryLabel;
	
	private JPanel	methodsPanel;
	
	private JLabel	methodLabels[];
	
	private HorizontalSpinner	methodSpinners[];
	
	/**
	 * @param wizard	The parent wizard instance.
	 */
	public TVReceptionMethodPage(SetupWizard wizard)
	{
		super(wizard);
		
		this.layoutControls();
	}

	private void layoutControls()
	{
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		this.setupExplanatoryText();		
		this.setupSpinners();
	}

	private void setupSpinners()
	{
		JPanel	centeringPanel;
		
		int	curIndex	= 0;
		
		this.methodsPanel		= new JPanel();
		
		this.methodsPanel.setAlignmentX(CENTER_ALIGNMENT);
		this.methodsPanel.setOpaque(false);
		this.methodsPanel.setBorder(new EmptyBorder(10, 20, 0, 0));
		this.methodsPanel.setMaximumSize(new Dimension(600, 400));
		this.methodsPanel.setLayout(new GridLayout(4, 2, 20, 2));
		
		centeringPanel	= new JPanel();

		centeringPanel.setAlignmentX(LEFT_ALIGNMENT);
		centeringPanel.setOpaque(false);
		centeringPanel.setLayout(
				new BoxLayout(centeringPanel, BoxLayout.X_AXIS));
		
		centeringPanel.add(Box.createHorizontalGlue());
		centeringPanel.add(this.methodsPanel);
		centeringPanel.add(Box.createHorizontalGlue());
		
		this.add(centeringPanel);
		this.add(Box.createVerticalGlue());
		
		this.methodLabels		= new JLabel[4];
		this.methodSpinners		= new HorizontalSpinner[4];
		
		this.createMethodSpinner(curIndex++, "Over-the-air (Antenna)");
		this.createMethodSpinner(curIndex++, "Analog Cable");
		this.createMethodSpinner(curIndex++, "Digital Cable");
		this.createMethodSpinner(curIndex++, "Satellite Dish");
	}
	
	private void createMethodSpinner(int index, String method)
	{
		String[]	yesNoOptions	= new String[] { "No", "Yes" };
		JPanel		spinnerPanel	= new JPanel();
		
		spinnerPanel.setOpaque(false);
		spinnerPanel.setLayout(new BoxLayout(spinnerPanel, BoxLayout.X_AXIS));
		
		this.methodLabels[index]	= new JLabel(method);
		this.methodLabels[index].setFont(UIHelper.getBodyFont());
		this.methodLabels[index].setForeground(UIHelper.getForegroundColor());
		
		this.methodSpinners[index]	= UIHelper.createHorizontalSpinner(
											yesNoOptions);
		
		this.methodSpinners[index].addFocusListener(
				new EditableFocusMappingDisabler());
		
		this.methodsPanel.add(this.methodLabels[index]);
		this.methodsPanel.add(this.methodSpinners[index]);		
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
