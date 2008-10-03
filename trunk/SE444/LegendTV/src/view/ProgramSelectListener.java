/**
 * File: ProgramSelectListener.java
 * 
 * Created by Eric Lutley on Oct 3, 2008
 *
 * Version: $Id$
 *
 * Revisions:
 *		$Log$
 */
package view;

import java.util.Date;
import java.util.EventListener;

/**
 * @author Eric
 *
 */
public interface ProgramSelectListener extends EventListener {

	void programSelected( Channel channel, Date time, Program program );
}
