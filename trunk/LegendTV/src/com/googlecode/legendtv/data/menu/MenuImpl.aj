package com.googlecode.legendtv.data.menu;

import com.googlecode.legendtv.data.menu.generated.Control;
import com.googlecode.legendtv.data.menu.generated.Menu;
import com.googlecode.legendtv.data.menu.generated.MenuList;

privileged aspect MenuImpl
{
	private MenuList	Menu.containingList;
	private Menu		Menu.previousMenu;
	
	public MenuList Menu.getContainingList()
    {
    	return containingList;
    }

	public void Menu.setContainingList(MenuList containingList)
    {
    	this.containingList = containingList;
    }

	public Menu Menu.getPreviousMenu()
    {
		if (this.previousMenu == null)
			fillPreviousMenu();
		
    	return previousMenu;
    }
	
	private void Menu.fillPreviousMenu()
	{
		assert (this.containingList != null);
		
		this.previousMenu	= this.containingList.getMenu(this.previousMenuName);
	}	
	
	public void Menu.bind()
	throws InvalidGeneratorException
	{
		for (Control curControl : this.controlList)
		{
			((DataModel)curControl).bind();
		}
	}
}