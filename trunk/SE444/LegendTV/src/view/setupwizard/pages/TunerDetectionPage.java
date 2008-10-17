package view.setupwizard.pages;

import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;

import view.setupwizard.WizardPage;
import view.utils.UIHelper;

public class TunerDetectionPage extends WizardPage
{
	private static final String TITLE = "Capture Device Detection";
	
	private static final String	TEXT_EXPLANATORY_LINES[] =
	{
		"Now that basic setup is out of the way, it's time to setup your " +
		"capture devices, which will allow you to watch and record live " +
		"television.",
		
		"LegendTV is currently auto-detecting the capture devices that are " +
		"installed in your system.<br />This means that no additional" +
		"information is needed from you at this time.",
		
		"Feel free to kick back and relax for a few minutes &mdash; this " +
		"process should take about 3 minutes."
	};
	
	private JLabel			explanatoryLabel;
	private JLabel			progressLabel;
	private JProgressBar	progressBar;
	
	public TunerDetectionPage()
	{
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		setupExplanatoryText();
	}
	
	private void setupExplanatoryText()
	{
		this.explanatoryLabel	= new JLabel(
									UIHelper.linesToHtmlText(
											TEXT_EXPLANATORY_LINES));

		this.explanatoryLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.explanatoryLabel.setBorder(new EmptyBorder(60, 0, 0, 0));
		this.explanatoryLabel.setForeground(UIHelper.getForegroundColor());
		this.explanatoryLabel.setFont(UIHelper.getBodyFont());
		
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
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean isModal()
	{
		return true;
	}
}
