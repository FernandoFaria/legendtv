package org.legendtv.test.drivers.faults;

public class RestoreThread extends Thread
{
	private Thread crashThread;
	
	public RestoreThread()
	{
		super();
		createAndStartThread();
	}
	
	private void createAndStartThread()
	{
		crashThread	= new CrashThread();
		
		FinalizationInfo.setFinalized(false);
		
		crashThread.start();
	}
	
	public void run()
	{
		while (true)
		{
			if (!crashThread.isAlive())
			{
				System.out.println("Other thread died! Cleaning up...");
				
				// Wait until the class loader has been finalized
				while (!FinalizationInfo.isFinalized())
				{
					System.gc();
				
					try
					{
						synchronized (this)
						{
							this.wait(100);
						}
					}
					
					catch (InterruptedException e)
					{
					}
				}
				
				System.out.println("Respawning...");
				createAndStartThread();
				
				System.out.println();
			}
			
			try
			{
				synchronized (this)
				{
					this.wait(500);
				}
			}
			
			catch (InterruptedException e)
			{
			}
		}
	}
	
	public static void main(String[] args)
	{
		(new RestoreThread()).start();
	}
}