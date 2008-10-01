package com.googlecode.legendtv.ui.console;

import java.util.LinkedList;
import java.util.List;

import com.googlecode.legendtv.data.menu.DataModel;
import com.googlecode.legendtv.data.menu.MenuImpl;
import com.googlecode.legendtv.ui.ControlFactory;
import com.googlecode.legendtv.ui.Control;

public class Menu
{
	private MenuImpl			dataModel;
	private String				banner;
	private List<Control<?>>	controls;
	
	public Menu()
	{
		this(null);
	}
	
	public Menu(MenuImpl dataModel)
	{
		this.setDataModel(dataModel);
		
		this.banner	= null;
	}

	public void setBanner(String banner)
    {
		this.banner = banner;
    }

	public String getBanner()
    {
	    return banner;
    }

	public MenuImpl getDataModel()
    {
	    return dataModel;
    }

	public void setDataModel(MenuImpl dataModel)
    {
	    this.dataModel	= dataModel;
	    this.controls	= null;
	    
	    if (dataModel != null)
	    {
	    	ControlFactory	factory	= new ConsoleControlFactory();
	    	
	    	this.controls	= new LinkedList<Control<?>>();
	    	
		    for (com.googlecode.legendtv.data.menu.Control curControl : this.dataModel.getControlList())
		    {
		    	try
	            {
		            this.controls.add(factory.createForModel((DataModel)curControl));
	            }
		    	
	            catch (InstantiationException e)
	            {
		            // TODO Auto-generated catch block
		            e.printStackTrace();
	            }
		    }
	    }
    }

	public List<Control<?>> getControls()
    {
	    return (this.controls);
    }

	public void paint()
    {
		if (this.dataModel != null)
		{
			paintBanner();
			paintControls();
		}
    }
	
	private void paintBanner()
	{
		StringBuffer	bannerText	= new StringBuffer();
		
		if (this.banner != null)
			bannerText.append(this.banner + " - ");
		
		bannerText.append(this.getDataModel().getTitle());
		System.out.println(bannerText.toString());
		
		for (int i = 0; i < bannerText.length(); ++i)
		{
			System.out.print("=");
		}
		
		System.out.println();
	}
	
	private void paintControls()
	{
		for (Control<?> curControl : this.controls)
		{
			curControl.paint();
		}
	}
}