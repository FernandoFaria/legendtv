import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class WelcomePage extends WizardPage
{	
	private static final String TITLE = "Welcome";
	
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
	
	private JLabel	explanatoryLabel;
	
	public WelcomePage()
	{
		super();
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.explanatoryLabel	= new JLabel(
										UIHelper.linesToHtmlText(
											TEXT_EXPLANATORY_LINES));
		
		setupExplanatoryText();
		
		
	}

	private void setupExplanatoryText()
	{
		this.explanatoryLabel.setBorder(new EmptyBorder(20, 0, 0, 0));
		this.explanatoryLabel.setForeground(UIHelper.getForegroundColor());
		this.explanatoryLabel.setFont(UIHelper.getBodyFont());
		this.explanatoryLabel.setPreferredSize(new Dimension(100, 400));
		this.add(this.explanatoryLabel);
	}
	
	@Override
	public String getTitle()
	{
		return TITLE;
	}
	
	@Override
	public WizardPage getNextPage()
	{
		return new BlackLevelCalibrationPage(); 
	}
}
