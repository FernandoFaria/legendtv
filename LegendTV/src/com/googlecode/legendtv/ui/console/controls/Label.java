package com.googlecode.legendtv.ui.console.controls;

import com.googlecode.legendtv.ui.Control;

public class Label extends Control<com.googlecode.legendtv.data.menu.Label>
{
	@Override
    public void paint()
	{
		System.out.println(this.getDataModel().getContent().getText());
    }
}
