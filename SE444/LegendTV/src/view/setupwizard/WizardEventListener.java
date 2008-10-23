package view.setupwizard;

import java.util.EventListener;

/**
 * An interface for components that need to be informed when events occur
 * in the setup wizard.
 * 
 * @author Guy Paddock (gap7472@rit.edu)
 */
public interface WizardEventListener extends EventListener
{
	/**
	 * Method called when the setup wizard has completed.
	 */
	public void wizardCompleted();
}
