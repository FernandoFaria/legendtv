
@SuppressWarnings("serial")
public class DisplayDevicePage extends WizardPage
{	
	private static final String TITLE = "Select Display Device";
	
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
