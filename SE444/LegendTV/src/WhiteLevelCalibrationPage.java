import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class WhiteLevelCalibrationPage extends WizardPage
{
	private static final String TITLE = "Adjust Contrast";
	
	private static final String	TEXT_EXPLANATORY_LINES[] =
	{
		"Now, we'll calibrate the contrast or \"white level\", which controls "+
		"how light the lightest part of an image appears on your screen.",
		
		"",
		
		"Follow these steps to calibrate it:"
	};
	
	private static final String TEXT_BULLET_STEPS[]	=
	{
		"Ensure the contrast of your screen is at its lowest setting.",
		
		"Slowly increase the contrast setting until you can just see the "+
		"star in the image to the right."
	};
	
	private JLabel	explanatoryLabel;
	private JPanel	calibrationPanel;
	private JLabel	explanatoryBullets;
	private JLabel	calibrationImage;
	
	public WhiteLevelCalibrationPage()
	{
		super();
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
				
		setupExplanatoryText();
		setupCalibrationPanel();
	}

	private void setupExplanatoryText()
	{
		this.explanatoryLabel	= createLabel(
									UIHelper.linesToHtmlText(
											TEXT_EXPLANATORY_LINES));
		
		this.explanatoryLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.explanatoryLabel.setBorder(new EmptyBorder(60, 0, 0, 0));
		this.explanatoryLabel.setSize(0, 400);
		
		this.add(this.explanatoryLabel);		
	}

	private void setupCalibrationPanel()
	{
		this.calibrationPanel	= new JPanel();
		
		this.calibrationPanel.setLayout(new GridLayout(1, 2));
		this.calibrationPanel.setOpaque(false);
		this.calibrationPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		this.add(this.calibrationPanel);
		
		this.explanatoryBullets	= createLabel(
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
	
	private JLabel createLabel(String content)
	{
		JLabel	label	= new JLabel(content);
		
		label.setForeground(UIHelper.getForegroundColor());
		label.setFont(UIHelper.getBodyFont());
		
		return label;
	}
	
	@Override
	public String getTitle()
	{
		return TITLE;
	}
	
	@Override
	public WizardPage getNextPage()
	{
		return new WhiteLevelCalibrationPage();
	}
}
