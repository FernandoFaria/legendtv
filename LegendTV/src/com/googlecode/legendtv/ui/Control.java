package com.googlecode.legendtv.ui;

import com.googlecode.legendtv.data.menu.DataModel;

public abstract class Control<T extends DataModel>
{
	private T dataModel;
	
	public T getDataModel()
	{
		return (this.dataModel);
	}
	
	public void setDataModel(T dataModel)
	{
		this.dataModel	= dataModel;
	}

	public abstract void paint();	
}
