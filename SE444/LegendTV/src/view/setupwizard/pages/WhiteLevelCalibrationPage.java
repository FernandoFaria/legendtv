package view.setupwizard.pages;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import view.setupwizard.SetupWizard;
import view.setupwizard.WizardPage;
import view.utils.UIHelper;

/**
 * The wizard page that walks the user through calibrating the contrast or 
 * "white level" of their display device.
 * 
 * @author Guy Paddock (gap7472@rit.edu)
 */
@SuppressWarnings("serial")
public class WhiteLevelCalibrationPage extends WizardPage
{
	/**
	 * The title of the page.
	 */
	private static final String TITLE = "Adjust Contrast";
	
	/**
	 * The explanatory text on the page.
	 */
	private static final String	TEXT_EXPLANATORY_LINES[] =
	{
		"Now, we'll calibrate the contrast or \"white level\", which controls " +
		"how light the lightest part of an image appears on your screen.",
		
		"",
		
		"Follow these steps to calibrate it:"
	};
	
	/**
	 * The bullet text of the step-by-step calibration process.
	 */
	private static final String TEXT_BULLET_STEPS[]	=
	{
		"Ensure the contrast of your screen is at its lowest setting.",
		
		"Slowly increase the contrast setting until you can just see the " +
		"star in the image to the right."
	};
	
	/**
	 * The label that displays the explanatory text.
	 */
	private JLabel	explanatoryLabel;
	
	/**
	 * The panel that contains the calibration steps and calibration image.
	 */
	private JPanel	calibrationPanel;
	
	/**
	 * The label that displays the bullet text of the step-by-step
	 * calibration process.
	 */
	private JLabel	explanatoryBullets;
	
	/**
	 * The image that the user uses to do the calibration.
	 */
	private JLabel	calibrationImage;
	
	/**
	 * Constructor for WhiteLevelCalibrationPage.
	 * 
	 * @param wizard	The parent wizard instance.
	 */
	public WhiteLevelCalibrationPage(SetupWizard wizard)
	{
		super(wizard);
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
				
		setupExplanatoryText();
		setupCalibrationPanel();
	}

	/**
	 * Sets-up the explanatory label & its text.
	 */
	private void setupExplanatoryText()
	{
		this.explanatoryLabel	= UIHelper.createLabel(
									UIHelper.linesToHtmlText(
											TEXT_EXPLANATORY_LINES));
		
		this.explanatoryLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.explanatoryLabel.setBorder(new EmptyBorder(20, 0, 0, 0));
		this.explanatoryLabel.setSize(0, 400);
		
		this.add(this.explanatoryLabel);		
	}

	/**
	 * Sets up the panel that contains the calibration steps and calibration
	 * image.
	 */
	private void setupCalibrationPanel()
	{
		this.calibrationPanel	= new JPanel();
		
		this.calibrationPanel.setLayout(new GridLayout(1, 2));
		this.calibrationPanel.setOpaque(false);
		this.calibrationPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		this.add(this.calibrationPanel);
		
		this.explanatoryBullets	= UIHelper.createLabel(
									UIHelper.linesToHtmlList(
										TEXT_BULLET_STEPS));		
		
		this.calibrationPanel.add(this.explanatoryBullets);
		
		this.calibrationImage	= new JLabel();
				
		this.calibrationImage.setPreferredSize(new Dimension(115, 115));
		this.calibrationImage.setHorizontalAlignment(SwingConstants.CENTER);
		this.calibrationImage.setIcon(
				new ImageIcon("images/CalibrateContrast.png"));
		
		this.calibrationPanel.add(this.calibrationImage);
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
		return new TunerDetectionPage(this.getWizard());
	}
}
