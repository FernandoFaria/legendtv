package com.googlecode.legendtv.ui.console;

import java.util.HashMap;
import java.util.Map;

import com.googlecode.legendtv.data.menu.DataModel;
import com.googlecode.legendtv.ui.ControlFactory;
import com.googlecode.legendtv.ui.Control;

public class ConsoleControlFactory implements ControlFactory
{
	private static Map<Class<? extends DataModel>, Class<? extends Control<?>>> implMap;
	
	static
	{
		implMap	= new HashMap<Class<? extends DataModel>, Class<? extends Control<?>>>();
		
		implMap.put(com.googlecode.legendtv.data.menu.LabelImpl.class,
					com.googlecode.legendtv.ui.console.controls.Label.class);
		
		implMap.put(com.googlecode.legendtv.data.menu.GridImpl.class,
					com.googlecode.legendtv.ui.console.controls.Grid.class);
		
		implMap.put(com.googlecode.legendtv.data.menu.OptionListImpl.class,
					com.googlecode.legendtv.ui.console.controls.OptionList.class);
	}
	
	/* (non-Javadoc)
     * @see com.googlecode.legendtv.ui.console.controls.ControlFactory#createForModel(T)
     */
	@SuppressWarnings("unchecked")
    public <T extends DataModel> Control<T> createForModel(T model)
    throws InstantiationException
	{
		Control<T>					uiObj;
		Class<? extends Control<T>>	uiClass;
		
		uiObj	= null;
		uiClass	= (Class<? extends Control<T>>)implMap.get(model.getClass());
		
		if (uiClass == null)
			throw new IllegalArgumentException("Model has no known representation in this UI.");
		
		try
		{
			uiObj	= uiClass.newInstance();
			
			uiObj.setDataModel(model);
		}
		
		catch (IllegalAccessException e)
		{
			throw new RuntimeException("Unexpected error: " + e.getMessage());
		}
		
		return uiObj;
	}
}