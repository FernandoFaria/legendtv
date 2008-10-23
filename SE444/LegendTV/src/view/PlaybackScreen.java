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
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
	private	Timer		hideTimer;
	private TimerTask	hideTimerTask;
	private JLabel		currentStatus;
	private ScreenManager manager;
	
	public PlaybackScreen( ScreenManager sm)
	{
		this.manager = sm;
		
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

		this.playbackPanel.setBackground(Color.DARK_GRAY);
		this.playbackPanel.setVisible(false);
		this.playbackPanel.setPreferredSize(new Dimension(1, 80));
		
		this.add(this.playbackPanel, BorderLayout.SOUTH);
		
		
		//Set the Buttons 
		this.playPauseBtn = new JButton("||");
		this.playPauseBtn.addActionListener(this);
		this.playPauseBtn.setActionCommand("Play/Pause");
		this.playPauseBtn.setBackground(Color.BLACK);
		this.playPauseBtn.setForeground(Color.WHITE);
		
		this.skipForwardBtn = new JButton("|> |");
		this.skipForwardBtn.addActionListener(this);
		this.skipForwardBtn.setActionCommand("Skip Forward");
		this.skipForwardBtn.setBackground(Color.BLACK);
		this.skipForwardBtn.setForeground(Color.WHITE);
		
		this.fastForwardBtn = new JButton("|> |>");
		this.fastForwardBtn.addActionListener(this);
		this.fastForwardBtn.setActionCommand("Fast Forward");
		this.fastForwardBtn.setBackground(Color.BLACK);
		this.fastForwardBtn.setForeground(Color.WHITE);
		
		this.skipBackwardBtn = new JButton("| <|");
		this.skipBackwardBtn.addActionListener(this);
		this.skipBackwardBtn.setActionCommand("Skip Backward");
		this.skipBackwardBtn.setBackground(Color.BLACK);
		this.skipBackwardBtn.setForeground(Color.WHITE);
		
		this.rewindBtn = new JButton("<| <|");
		this.rewindBtn.addActionListener(this);
		this.rewindBtn.setActionCommand("Rewind");
		this.rewindBtn.setBackground(Color.BLACK);
		this.rewindBtn.setForeground(Color.WHITE);
		
		this.stopBtn = new JButton("Stop");
		this.stopBtn.addActionListener(this);
		this.stopBtn.setActionCommand("Stop");
		this.stopBtn.setBackground(Color.BLACK);
		this.stopBtn.setForeground(Color.WHITE);
		
		this.backBtn = new JButton("Back to Menu");
		this.backBtn.addActionListener(this);
		this.backBtn.setActionCommand("Back");
		this.backBtn.setBackground(Color.BLACK);
		this.backBtn.setForeground(Color.WHITE);
		
		this.currentStatus = new JLabel("Playing");
		this.add(this.currentStatus, BorderLayout.NORTH);

		//this.playPauseBtn.setBackground(this.playbackPanel.getBackground());
		//this.playPauseBtn.setPreferredSize(new Dimension(230, 76));
		
		this.playbackPanel.add(Box.createHorizontalStrut(400));
		this.playbackPanel.add(this.skipBackwardBtn);
		this.playbackPanel.add(this.rewindBtn);
		this.playbackPanel.add(this.playPauseBtn);
		this.playbackPanel.add(this.fastForwardBtn);
		this.playbackPanel.add(this.skipForwardBtn);
		this.playbackPanel.add(Box.createHorizontalStrut(50));
		this.playbackPanel.add(this.stopBtn);
		this.playbackPanel.add(Box.createHorizontalStrut(100));
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
			if(this.playPauseBtn.getText().equals("||")){
				this.playPauseBtn.setText("|>");
				currentStatus.setText("Paused");
			}else{
				this.playPauseBtn.setText("||");
				currentStatus.setText("Playing");
			}
		}else if(actionCommand.equals("Back")){
			manager.back();
		}else{
			currentStatus.setText(e.getActionCommand());
			this.playPauseBtn.setText("|>");
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("It works!");
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
