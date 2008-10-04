import java.awt.Color;
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
public class BlackLevelCalibrationPage extends WizardPage
{
	private static final String TITLE = "Adjust Brightness";
	
	private static final String	TEXT_EXPLANATORY_LINES[] =
	{
		"Now it's time to calibrate the way that images appear on your screen "+
		"to ensure a high-quality picture.",
		
		"We'll begin with brightness. The brightness setting, or \"black level\" "+
		"controls how light or dark the darkest part of an image appears on your "+
		"screen.",
		
		"",
		
		"Follow these steps to calibrate it:"
	};
	
	private static final String TEXT_BULLET_STEPS[]	=
	{
		"Turn the contrast of your screen to its lowest setting.",
		
		"Turn the brightness of your screen to its lowest setting.",
		
		"Slowly increase the brightness setting until you can just see the "+
		"star in the image to the right."
	};
	
	private JLabel	explanatoryLabel;
	private JPanel	calibrationPanel;
	private JLabel	explanatoryBullets;
	private JLabel	calibrationImage;
	
	public BlackLevelCalibrationPage()
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
				new ImageIcon("images/CalibrateBrightness.png"));
		
		this.calibrationPanel.add(this.calibrationImage);
	}
	
	private JLabel createLabel(String content)
	{
		JLabel	label	= new JLabel(content);
		
		label.setBorder(new EmptyBorder(20, 0, 0, 0));
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
