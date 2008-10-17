package view.setupwizard.pages;

import java.awt.Component;
import java.awt.Dimension;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;

import view.setupwizard.SetupWizard;
import view.setupwizard.WizardPage;
import view.utils.UIHelper;

/**
 * The page that is displayed to the user while the system is detecting
 * tuner cards.
 * 
 * @author Guy Paddock	(gap7472@rit.edu)
 */
@SuppressWarnings("serial")
public class TunerDetectionPage extends WizardPage
{
	/**
	 * The title of the page.
	 */
	private static final String TITLE = "Capture Device Detection";
	
	/**
	 * The explanatory text on the page.
	 */
	private static final String	TEXT_EXPLANATORY_LINES[] =
	{
		"Now that basic setup is out of the way, it's time to setup your " +
		"capture devices, which will allow you to watch and record live " +
		"television.",
		
		"LegendTV is currently auto-detecting the capture devices that are " +
		"installed in your system.<br />This means that no additional" +
		"information is needed from you at this time.",
		
		"Feel free to kick back and relax &mdash; this process should take " +
		"no longer than 3 minutes."
	};
	
	/**
	 * Some fake status text for the prototype. In the final system, this will
	 * be replaced with real text indicating the detection progress.
	 */
	private static final String FAKE_STATUS_TEXT[] =
	{
		"Detecting uncompressed analog capture devices...",
		"Detected no uncompressed analog capture devices.",
		
		"Detecting MPEG-compressed analog capture devices...",
		"Detected 1 MPEG-compressed analog capture device. Detecting...",
		"Detected 2 MPEG-compressed analog capture devices. Detecting...",
		"Detected 3 MPEG-compressed analog capture devices. Detecting...",
		"Detected 4 MPEG-compressed analog capture devices. Detecting...",
		
		"Detecting High Definition (HD) capture devices...",
		"Detected 1 High Definition (HD) capture device. Detecting...",
		"Detected 2 High Definition (HD) capture device. Detecting...",
		
		"Detection complete!"
	};
	
	/**
	 * How long each step of the detection process should take to complete in
	 * the prototype.
	 */
	private static final int	FAKE_SCAN_STEP_PERIOD =
									(500 * 60) / FAKE_STATUS_TEXT.length;	
	
	/**
	 * The label that displays the explanatory text.
	 */
	private JLabel			explanatoryLabel;
	
	/**
	 * The text that displays the detection progress.
	 */
	private JLabel 			statusLabel;
	
	/**
	 * The graphical depiction of the detection progress.
	 */
	private JProgressBar	progressBar;
	
	/**
	 * Constructor for TunerDetectionPage.
	 */
	public TunerDetectionPage()
	{
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		setupExplanatoryText();
		setupProgressControls();
	}
	
	/**
	 * Sets-up the explanatory label & its text.
	 */
	private void setupExplanatoryText()
	{
		this.explanatoryLabel	= new JLabel(
									UIHelper.linesToHtmlText(
											TEXT_EXPLANATORY_LINES));

		this.explanatoryLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.explanatoryLabel.setBorder(new EmptyBorder(20, 0, 0, 0));
		this.explanatoryLabel.setForeground(UIHelper.getForegroundColor());
		this.explanatoryLabel.setFont(UIHelper.getBodyFont());

		this.add(this.explanatoryLabel);
	}

	/**
	 * Sets-up the controls to provide feedback to the user on the
	 * status of the detection process.
	 */
	private void setupProgressControls()
	{
		this.statusLabel	= new JLabel();
		this.statusLabel.setBorder(new EmptyBorder(10, 0, 0, 0));
		this.statusLabel.setForeground(UIHelper.getForegroundColor());
		this.statusLabel.setFont(UIHelper.getBodyFont());
		this.statusLabel.setSize(1, 200);
		
		this.add(this.statusLabel);
		
		this.progressBar	= new JProgressBar(
								0,
								TunerDetectionPage.FAKE_STATUS_TEXT.length - 1);
		
		this.progressBar.setOpaque(false);
		this.progressBar.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.progressBar.setBorder(new EmptyBorder(10, 0, 0, 0));
		this.progressBar.setMinimumSize(new Dimension(1, 100));
		this.progressBar.setSize(1, 100);
		this.progressBar.setPreferredSize(new Dimension(1, 100));
		
		this.add(this.progressBar);
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
		return new TunerSpacesPage();
	}
	
	
	/**
	 * Indicates whether or not this page is modal (user cannot navigate away).
	 * 
	 * @return	True (this page is modal).
	 */
	@Override
	public boolean isModal()
	{
		return true;
	}
	
	/**
	 * Called when this page becomes active. This enables pages to perform
	 * actions as soon as they are visible.
	 * 
	 * @param	wizard	The containing setup wizard instance.
	 */
	@Override
	public void activate(final SetupWizard wizard)
	{
		Timer	progressTimer = new Timer();
	
		progressTimer.schedule(new TimerTask()
		{
			private int step	= 0;
			
			@Override
			public void run()
			{
				if (this.step < TunerDetectionPage.FAKE_STATUS_TEXT.length)
				{
					TunerDetectionPage.this.progressBar.setValue(this.step);
					
					TunerDetectionPage.this.statusLabel.setText(
							TunerDetectionPage.FAKE_STATUS_TEXT[this.step]);
					
					++this.step;
				}
				
				else
				{
					this.cancel();
					
					wizard.goToNextPage();
				}
			}
		},
		0,
		FAKE_SCAN_STEP_PERIOD);
	}
}
