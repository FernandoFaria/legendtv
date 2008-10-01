package com.googlecode.legendtv.ui;

import com.googlecode.legendtv.data.menu.DataModel;

public interface ControlFactory
{
	public abstract <T extends DataModel> Control<T> createForModel(T model)
	throws InstantiationException;
}
