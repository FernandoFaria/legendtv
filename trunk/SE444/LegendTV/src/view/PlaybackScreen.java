package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import javax.swing.event.MouseInputAdapter;

import view.controls.SVGButton;
import view.utils.UIHelper;

public class PlaybackScreen extends JComponent
{
	private static int HIDE_DELAY	= 5000;
	
	private JPanel 		playbackPanel;
	private SVGButton	playBtn;
	private	Timer		hideTimer;
	private TimerTask	hideTimerTask;
	
	public PlaybackScreen()
	{
		layoutControls();
		
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

		this.playbackPanel.setBackground(Color.WHITE);
		this.playbackPanel.setVisible(false);
		this.playbackPanel.setPreferredSize(new Dimension(1, 80));
		
		this.add(this.playbackPanel, BorderLayout.SOUTH);
		
		this.playBtn	= UIHelper.createButton("Play", new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub	
			}
		});	

		this.playBtn.setBackground(this.playbackPanel.getBackground());
		this.playBtn.setPreferredSize(new Dimension(230, 76));
		
		this.playbackPanel.add(this.playBtn);
	}
	
	protected void showPanel()
	{
		// Cancel any existing timer, so that we don't end up hiding the
		// panel while the user is still moving the mouse.
		if (this.hideTimerTask != null)
			this.hideTimerTask.cancel();
		
		// Show the panel.
		System.out.println("Showing...");
		this.playbackPanel.setVisible(true);
		
		// Create a new timer task to hide the panel after a period of time 
		this.hideTimerTask	= new TimerTask()
		{
			public void run()
			{
				SwingWorker<Boolean, Object>	worker;
		
				
				System.out.println("Setting up for hide...");
				
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
						System.out.println("Hiding...");
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
		
		main.add(new PlaybackScreen());
		
		main.setVisible(true);
	}
}
