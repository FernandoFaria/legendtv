package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.event.MouseInputAdapter;

import view.controls.SVGButton;
import view.utils.ScreenManager;
import view.utils.UIHelper;

public class PlaybackScreen extends JComponent implements ActionListener, KeyListener
{
	private static int HIDE_DELAY	= 5000;
	
	
	private JPanel 		playbackPanel;
	//Buttons - play, Fast forward, skip forward, skip backward, rewind, stop
	private JButton		playPauseBtn, skipForwardBtn, fastForwardBtn, skipBackwardBtn, rewindBtn, stopBtn, backBtn;
	private Icon		playIcon, pauseIcon, skipToEndIcon, forwardIcon, skipToStartIcon, rewindIcon, stopIcon;
	private	Timer		hideTimer;
	private TimerTask	hideTimerTask;
	private JLabel		currentStatus;
	private ScreenManager manager;
	
	public PlaybackScreen( ScreenManager sm)
	{
		this.manager = sm;
		try{
			this.playIcon = new ImageIcon(UIHelper.resourcePathToUrl("images/play.png"));
			this.pauseIcon = new ImageIcon(UIHelper.resourcePathToUrl("images/pause.png"));
			this.skipToEndIcon = new ImageIcon(UIHelper.resourcePathToUrl("images/SkipToEnd.png"));
			this.forwardIcon = new ImageIcon(UIHelper.resourcePathToUrl("images/FFWD.png"));
			this.skipToStartIcon = new ImageIcon(UIHelper.resourcePathToUrl("images/SkipToStart.png"));
			this.rewindIcon = new ImageIcon(UIHelper.resourcePathToUrl("images/Rewind.png"));
			this.stopIcon = new ImageIcon(UIHelper.resourcePathToUrl("images/Stop.png"));
			
		}catch(Exception e){
			
		}
		
		layoutControls();
		
		this.addKeyListener(this);
		
		this.addMouseMotionListener(new MouseInputAdapter()
		{
			@Override
			public void mouseMoved(MouseEvent e)
			{
				PlaybackScreen.this.showPanel();
			}	
		});
		
		this.hideTimer	= new Timer();
	}

	private void layoutControls()
	{
		this.setLayout(new BorderLayout());
	
		this.playbackPanel	= new JPanel();

		this.playbackPanel.setBackground(Color.BLACK);
		this.playbackPanel.setVisible(false);
		this.playbackPanel.setPreferredSize(new Dimension(1, 80));
		
		this.add(this.playbackPanel, BorderLayout.SOUTH);
		
		
		//Set the Buttons 
		this.playPauseBtn = new JButton(pauseIcon);
		this.playPauseBtn.addActionListener(this);
		this.playPauseBtn.setActionCommand("Play/Pause");
		this.playPauseBtn.setBackground(Color.BLACK);
		this.playPauseBtn.setForeground(Color.WHITE);
		this.playPauseBtn.setFocusPainted(false);
		this.playPauseBtn.setBorderPainted(false);
		
		this.skipForwardBtn = new JButton(skipToEndIcon);
		this.skipForwardBtn.addActionListener(this);
		this.skipForwardBtn.setActionCommand("Skip Forward");
		this.skipForwardBtn.setBackground(Color.BLACK);
		this.skipForwardBtn.setForeground(Color.WHITE);
		this.skipForwardBtn.setFocusPainted(false);
		this.skipForwardBtn.setBorderPainted(false);
		
		this.fastForwardBtn = new JButton(forwardIcon);
		this.fastForwardBtn.addActionListener(this);
		this.fastForwardBtn.setActionCommand("Fast Forward");
		this.fastForwardBtn.setBackground(Color.BLACK);
		this.fastForwardBtn.setForeground(Color.WHITE);
		this.fastForwardBtn.setFocusPainted(false);
		this.fastForwardBtn.setBorderPainted(false);
		
		this.skipBackwardBtn = new JButton(skipToStartIcon);
		this.skipBackwardBtn.addActionListener(this);
		this.skipBackwardBtn.setActionCommand("Skip to the start of the show");
		this.skipBackwardBtn.setBackground(Color.BLACK);
		this.skipBackwardBtn.setForeground(Color.WHITE);
		this.skipBackwardBtn.setFocusPainted(false);
		this.skipBackwardBtn.setBorderPainted(false);
		
		this.rewindBtn = new JButton(rewindIcon);
		this.rewindBtn.addActionListener(this);
		this.rewindBtn.setActionCommand("Rewind");
		this.rewindBtn.setBackground(Color.BLACK);
		this.rewindBtn.setForeground(Color.WHITE);
		this.rewindBtn.setFocusPainted(false);
		this.rewindBtn.setBorderPainted(false);
		
		this.stopBtn = new JButton(stopIcon);
		this.stopBtn.addActionListener(this);
		this.stopBtn.setActionCommand("Stop");
		this.stopBtn.setBackground(Color.BLACK);
		this.stopBtn.setForeground(Color.WHITE);
		this.stopBtn.setFocusPainted(false);
		this.stopBtn.setBorderPainted(false);
		
		this.backBtn = new JButton("Back to Menu");
		try{
			this.backBtn.setIcon(new ImageIcon(UIHelper.resourcePathToUrl("images/button_off_short.png")));
			this.backBtn.setRolloverIcon(new ImageIcon(UIHelper.resourcePathToUrl("images/button_on_short.png")));
		}catch(Exception e){
			
		}
		this.backBtn.setIconTextGap(0);
		this.backBtn.setVerticalTextPosition(SwingConstants.CENTER);
		this.backBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		this.backBtn.addActionListener(this);
		this.backBtn.setActionCommand("Back");
		this.backBtn.setBackground(Color.BLACK);
		this.backBtn.setForeground(Color.WHITE);
		this.backBtn.setFocusPainted(false);
		this.backBtn.setBorderPainted(false);
		
		this.currentStatus = new JLabel("Playing");
		currentStatus.setFont( UIHelper.getHeadingFont() );
		this.add(Box.createHorizontalStrut(50), BorderLayout.WEST);
		this.add(this.currentStatus, BorderLayout.CENTER);

		
		this.playbackPanel.add(this.skipBackwardBtn);
		this.playbackPanel.add(this.rewindBtn);
		this.playbackPanel.add(this.playPauseBtn);
		this.playbackPanel.add(this.fastForwardBtn);
		this.playbackPanel.add(this.skipForwardBtn);
		this.playbackPanel.add(this.stopBtn);
		this.playbackPanel.add(this.backBtn);
		
	}
	
	protected void showPanel()
	{
		// Cancel any existing timer, so that we don't end up hiding the
		// panel while the user is still moving the mouse.
		if (this.hideTimerTask != null)
			this.hideTimerTask.cancel();
		
		// Show the panel.
		this.playbackPanel.setVisible(true);
		
		// Create a new timer task to hide the panel after a period of time 
		this.hideTimerTask	= new TimerTask()
		{
			public void run()
			{
				SwingWorker<Boolean, Object>	worker;
		
				
				// Invoke the control update to the event dispatch thread.
				worker = new SwingWorker<Boolean, Object>()
				{
					@Override
					protected Boolean doInBackground() throws Exception
					{
						return true;
					}
					
					@Override
					protected void done()
					{
						PlaybackScreen.this.playbackPanel.setVisible(false);
					}
				};
				
				worker.execute();
				
				PlaybackScreen.this.hideTimerTask = null;
			};
		};

		this.hideTimer.schedule(this.hideTimerTask, HIDE_DELAY);
	}

	public static void main(String[] args)
	{
		MainFrame	main	= new MainFrame();
		
		main.add(new PlaybackScreen(null));
		
		main.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String actionCommand = e.getActionCommand();
		if(actionCommand.equals("Play/Pause")){
			if(this.playPauseBtn.getIcon().equals(pauseIcon)){
				this.playPauseBtn.setIcon(playIcon);
				currentStatus.setText("Paused");
			}else{
				this.playPauseBtn.setIcon(pauseIcon);
				currentStatus.setText("Playing");
			}
		}else if(actionCommand.equals("Back")){
			manager.back();
		}else{
			currentStatus.setText(e.getActionCommand());
			this.playPauseBtn.setIcon(playIcon);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		if(keyCode == KeyEvent.VK_SPACE){
			if(this.playPauseBtn.getText().equals("||")){
				this.playPauseBtn.setText("|>");
				currentStatus.setText("Paused");
			}else{
				this.playPauseBtn.setText("||");
				currentStatus.setText("Playing");
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
