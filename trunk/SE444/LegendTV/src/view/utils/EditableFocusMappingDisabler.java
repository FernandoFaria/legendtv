/**
 * 
 */
package view.utils;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class EditableFocusMappingDisabler extends FocusAdapter
{
	@Override
	public void focusGained(FocusEvent e)
	{
		UIHelper.removeArrowKeyFocusMappings();
	}

	@Override
	public void focusLost(FocusEvent e)
	{
		UIHelper.addArrowKeyFocusMappings();
	}
}