

@SuppressWarnings("serial")
public class BlackLevelCalibrationPage extends WizardPage
{
	private static final String TITLE = "Calibrate Brightness (Black Level)";
	
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
