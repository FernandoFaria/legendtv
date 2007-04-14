package com.googlecode.legendtv.test.drivers.faults;

public class FinalizationInfo
{
	private static Boolean	finalized;

	static
	{
		finalized	= false;
	}

	public static void setFinalized(boolean finalized)
	{
		synchronized (FinalizationInfo.finalized)
		{
			FinalizationInfo.finalized	= finalized;
		}
	}
	
	public static boolean isFinalized()
	{
		synchronized (finalized)
		{
			return finalized;
		}
	}
}