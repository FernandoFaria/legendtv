package com.googlecode.legendtv.ui.console.controls;

import com.googlecode.legendtv.data.menu.Option;
import com.googlecode.legendtv.data.menu.OptionListImpl;
import com.googlecode.legendtv.ui.Control;

public class OptionList extends Control<com.googlecode.legendtv.data.menu.OptionList>
{
	@Override
	public void paint()
	{
		int itemCount	= 0;
		
		for (Option curOption : ((OptionListImpl)this.getDataModel()).getOptions())
		{
			System.out.println(String.format("%d) %s", ++itemCount, curOption.getCaption()));
		}
	}
}
