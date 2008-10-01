package com.googlecode.legendtv.data.menu;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.googlecode.legendtv.data.menu.generated.Menu;
import com.googlecode.legendtv.data.menu.generated.MenuList;

privileged aspect MenuListImpl
{
	private HashMap<String, Menu>	MenuList.menuMap;
	
	public static MenuList MenuList.load(String filename)
	throws JAXBException
	{
		String			packageName;
		JAXBContext		jxContext;
		Unmarshaller	unmarshaller;
		MenuList		rootNode;
		
		
		packageName		= MenuListImpl.class.getPackage().getName();
		jxContext		= JAXBContext.newInstance(packageName);
		unmarshaller	= jxContext.createUnmarshaller();
		
		rootNode		= (MenuList)unmarshaller.unmarshal(new File(filename));
		
		return (rootNode);
	}

	public Map<String, Menu> MenuList.getMenuMap()
	{		
		return (this.menuMap);
	}
	
	public Menu MenuList.getMenu(String name)
	{
		return (this.menuMap.get(name));
	}

	private void MenuList.fillMenuMap()
	{
		this.menuMap	= new HashMap<String, Menu>();
		
		assert (this.menus != null);
		
		for (Menu curMenu : this.menus)
		{
			this.menuMap.put(curMenu.getName(), curMenu);
		}
	}
	
	/**
	 * Advice to ensure that a MenuList's menu map is filled before it is accessed.
	 * 
	 * @param instance	The MenuList instance.
	 */
	before(MenuList instance) :  this(instance) && (execution(* MenuList.getMenuMap(..)) ||
	 							 					execution(* MenuList.getMenu(..)))
	{
		if (instance.menuMap == null)
			instance.fillMenuMap();
		
		assert (instance.menuMap != null);
	}
}

aspect SetParentMenuListAspect
{
	/**
	 * Advice that keeps menus informed when their containing list changes.
	 * 
	 * @param	container	The MenuList instance that contains the menus.
	 * @param	menus		The list of menus.
	 */
	after(MenuList container, List<Menu> menus) : set(private List<Menu> MenuList.menus) &&
												  this(container) && args(menus)
	{
		for (Menu currentMenu : menus)
		{
			currentMenu.setContainingList(container);
		}
	}

	/**
	 * Advice that keeps menus from referencing a containing list that no longer contains them.
	 * 
	 * @param	menus		The list of menus.
	 */
	before(List<Menu> menus) : set(private List<Menu> MenuList.menus) && args(menus)
	{
		for (Menu currentMenu : menus)
		{
			currentMenu.setContainingList(null);
		}
	}
}
