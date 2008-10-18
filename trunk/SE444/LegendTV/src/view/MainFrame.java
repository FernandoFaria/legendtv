package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import view.controls.SVGButton;
import view.utils.ScreenManager;
import view.utils.UIHelper;

/**
 * Container window for screens in the LegendTV Swing-based user interface.
 * 
 * @author Guy Paddock (gap7472@rit.edu)
 */
@SuppressWarnings("serial")
public class MainFrame extends JFrame
{
	private final ScreenManager screenManager;

	/**
	 * Constructor for MainFrame.
	 * 
	 * @throws HeadlessException When run in an environment without a display.
	 */
	public MainFrame() throws HeadlessException
	{
		super("LegendTV");
		
		this.setupControls();
		
		screenManager = new ScreenManager( this );
		MenuGUI tvMenu = createTVMenu();
		MenuGUI mainMenu = createMainMenu( tvMenu );
		screenManager.show( mainMenu );
	}
	
	private MenuGUI createMainMenu( MenuGUI tvMenu ) {
		final MenuGUI finalTvMenu = tvMenu;
		SVGButton tv = UIHelper.createButton( "TV", 
				new ActionListener() {
				@Override
				public void actionPerformed( ActionEvent e ) {
					screenManager.show( finalTvMenu );
				}
			} );
		
		SVGButton settings = UIHelper.createButton( "Settings", 
				new ActionListener() {
				@Override
				public void actionPerformed( ActionEvent e ) {
					JOptionPane.showMessageDialog( MainFrame.this, 
					"This feature is not yet implemented" );
				}
			} );
			
			SVGButton music = UIHelper.createButton( "Music", 
					new ActionListener() {
					@Override
					public void actionPerformed( ActionEvent e ) {
						JOptionPane.showMessageDialog( MainFrame.this, 
						"This feature is not yet implemented" );
					}
				} );
			
			SVGButton video = UIHelper.createButton( "Video", 
					new ActionListener() {
					@Override
					public void actionPerformed( ActionEvent e ) {
						JOptionPane.showMessageDialog( MainFrame.this, 
								"This feature is not yet implemented" );
					}
				} );
		return new MenuGUI(tv, music, video, settings, createExitButton() );
	}

	private MenuGUI createTVMenu() {
		SVGButton search = UIHelper.createButton( "Search TV Listings", 
			new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent e ) {
				JComponent screen = new SearchGuideGUI();
				screenManager.show( screen );
			}
		} );
		
		SVGButton watchTv = UIHelper.createButton( "Watch Live TV", 
				new ActionListener() {
				@Override
				public void actionPerformed( ActionEvent e ) {
					JComponent screen = new PlaybackScreen();
					screenManager.show( screen );
				}
			} );
		
		SVGButton browseListings = UIHelper.createButton( "View TV Guide", 
				new ActionListener() {
				@Override
				public void actionPerformed( ActionEvent e ) {
					JComponent screen = new GuideView( new ActionListener() {
						@Override
						public void actionPerformed( ActionEvent e ) {
							screenManager.back();
						}
					} );
					screenManager.show( screen );
				}
			} );
		
		SVGButton browseRecordings = UIHelper.createButton( "Browse Recorded Programs", 
				new ActionListener() {
				@Override
				public void actionPerformed( ActionEvent e ) {
					JComponent screen = new RecordedProgramsGUI( null );
					screenManager.show( screen );
				}
			} );
		return new MenuGUI(watchTv, browseListings, browseRecordings, search, createBackButton() );
	}
	
	private SVGButton createExitButton() {
		return UIHelper.createButton( "Exit", new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent e ) {
				MainFrame.this.dispose();
			}
		} );
	}
	
	private SVGButton createBackButton() {
		return UIHelper.createButton( "Back", new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent e ) {
				screenManager.back();
			}
		} );
	}
	
	/**
	 * Sets-up the controls and layout of this frame.
	 */
	private void setupControls()
	{
		Container	contentPane	= this.getContentPane();

		// Kill the app when closed
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// Maximize with no title bar
		this.setUndecorated(true);
		this.setExtendedState(MAXIMIZED_BOTH);

		// Black & white themeing
		// TODO: Move into theme
		contentPane.setForeground(Color.WHITE);
		contentPane.setBackground(Color.BLACK);
		
		this.setLayout(new BorderLayout());
	}

	/**
	 * Main method for testing.
	 * 
	 * @param args	Command-line arguments (unused).
	 */
	public static void main(String[] args)
	{
		new MainFrame().setVisible(true);
	}
}