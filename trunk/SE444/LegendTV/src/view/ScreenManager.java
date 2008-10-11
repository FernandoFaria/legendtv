/**
 * File: ScreenManager.java
 * 
 * Created by Eric Lutley on Oct 11, 2008
 *
 * Version: $Id$
 *
 * Revisions:
 *		$Log$
 */
package view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 * The <code>ScreenManager</code> is responsible for displaying LegendTV's 
 * different screens in the main JFrame object.  It registers a KeyListener 
 * with whatever screen is in the JFrame, which listens for key events that 
 * have been bound as shortcuts to specific screens.  When it encounters a 
 * bound key event, it changes the displayed screen to the one indicated by 
 * the key shortcut.  This class also maintains a history stack of screens so 
 * that the user can backtrack to previous screens.
 * 
 * @author Eric Lutley
 */
public class ScreenManager {

	private final Stack<JComponent> screenStack;
	private final JFrame mainFrame;
	private final Map<Integer,JComponent> buttonScreenMap;
	private final RemoteListener remoteListener;
	
	public ScreenManager( JFrame mainFrame ) {
		this.mainFrame = mainFrame;
		screenStack = new Stack<JComponent>();
		buttonScreenMap = new HashMap<Integer,JComponent>();
		remoteListener = new RemoteListener();
	}
	
	public void back() {
		if ( screenStack.size() > 1 ) {
			JComponent topScreen = screenStack.pop();
			if ( topScreen instanceof Screen ) {
				Screen screen = (Screen) topScreen;
				screen.onStop();
			}
			mainFrame.remove( topScreen );
			topScreen.removeKeyListener( remoteListener );
			// Now get the next screen on the list
			topScreen = screenStack.peek();
			mainFrame.add( topScreen );
			topScreen.addKeyListener( remoteListener );
			if ( topScreen instanceof Screen ) {
				Screen screen = (Screen) topScreen;
				screen.onResume();
			}
			mainFrame.repaint();
		}
	}
	
	public void show( JComponent view ) {
		if ( !screenStack.isEmpty() ) {
			JComponent currentView = screenStack.peek();
			if ( currentView instanceof Screen ) {
				Screen currentScreen = (Screen) currentView;
				currentScreen.onPause();
			}
			mainFrame.remove( currentView );
			currentView.removeKeyListener( remoteListener );
		}
		// Now initialize the new "top view"
		mainFrame.add( view );
		view.addKeyListener( remoteListener );
		if ( view instanceof Screen ) {
			Screen screen = (Screen) view;
			screen.onStart();
		}
		mainFrame.repaint();
		screenStack.add( view );
	}
	
	public JComponent bindKeyToScreen( int keyCode, JComponent screen ) {
		return buttonScreenMap.put( keyCode, screen );		
	}
	
	public JComponent unbindKey( int keyCode ) {
		return buttonScreenMap.remove( keyCode );
	}
	
	private class RemoteListener implements KeyListener {

		@Override
		public void keyPressed( KeyEvent e ) {
			JComponent newScreen = buttonScreenMap.get( e.getKeyCode() );
			if ( newScreen != null ) {
				ScreenManager.this.show( newScreen );
			}
		}

		@Override
		public void keyReleased( KeyEvent e ) {
			// Do nothing for this event			
		}

		@Override
		public void keyTyped( KeyEvent e ) {
			// Do nothing for this event
		}
		
	}
}
