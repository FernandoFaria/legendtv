package view.utils;

import java.awt.Component;
import java.awt.Container;
import java.awt.FocusTraversalPolicy;
import java.util.ArrayList;
import java.util.List;

public class ListBasedFocusTraversalPolicy extends FocusTraversalPolicy
{
	private List<Component> focusPath;

	public ListBasedFocusTraversalPolicy(List<Component> focusPath)
	{
		this.focusPath = new ArrayList<Component>(focusPath);
	}

	@Override
	public Component getFirstComponent(Container container)
	{
		return this.focusPath.get(0);
	}

	@Override
	public Component getComponentAfter(Container container,
			Component component)
	{
		int				curIndex;
		Component		retComponent	= null;
		
		curIndex		= this.focusPath.indexOf(component);
		
		if (curIndex != -1)
		{
			if (curIndex == (this.focusPath.size() - 1))
				retComponent	= this.getFirstComponent(container);
			
			else
				retComponent	= this.focusPath.get(curIndex + 1);
		}
		
		return retComponent;
	}

	@Override
	public Component getComponentBefore(Container container,
			Component component)
	{
		int				curIndex;
		Component		retComponent	= null;
		
		curIndex		= this.focusPath.indexOf(component);
		
		if ((curIndex != -1) && (curIndex > 0))
			retComponent	= this.focusPath.get(curIndex - 1);
		
		return retComponent;
	}

	@Override
	public Component getDefaultComponent(Container container)
	{
		return this.getFirstComponent(container);
	}

	@Override
	public Component getLastComponent(Container container)
	{
		return this.focusPath.get(this.focusPath.size() - 1);
	}
}