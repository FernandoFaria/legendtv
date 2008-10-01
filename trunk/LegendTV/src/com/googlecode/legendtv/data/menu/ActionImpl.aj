package com.googlecode.legendtv.data.menu;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.googlecode.legendtv.data.menu.generated.Action;

/**
 * Implementation class for the schema-derived Action data type.
 * This class provides the type with methods that act on the Action data marshalled from XML.
 * 
 * @author Guy Paddock
 */
privileged aspect ActionImpl
{
	/**
	 * Regular expression used for breaking a target name into a class name and method name.
	 */
	private static final Pattern Action.CLASS_METHOD_REGEX	= Pattern.compile("^([^.])\\.([^.])$");

	/**
	 * Invokes this action.
	 * 
	 * @throws InvocationTargetException	If this action does not have an action type set.
	 */
	public void Action.invoke()
	throws InvocationTargetException
	{
		if (this.type == null)
			throw new IllegalStateException("Action type is not set.");
		
		switch (this.type)
		{
			case MENU:
				invokeMenu();
				break;
				
			case METHOD:
				invokeMethod();
				break;
				
			default:
				assert false;
		}
	}
	
	/**
	 * Method called to invoke this action when its action type is ActionType.MENU.
	 */
	private void Action.invokeMenu()
	{
		// Listeners handle this type of event; we don't do anything here.
	}
	
	/**
	 * Method called to invoke this action when its action type is ActionType.METHOD.
	 */
	private void Action.invokeMethod()
	throws InvocationTargetException
	{
		if (this.target == null)
			throw new IllegalStateException("Target is not set.");
		
		else
		{
			Matcher		targetPatternMatcher;
			
			targetPatternMatcher	= CLASS_METHOD_REGEX.matcher(this.target);
			
			if (!targetPatternMatcher.find())
			{
				throw new IllegalArgumentException(
					"Target name is not in the expected format of \"<package>.<class>.<method>\".");
			}
			
			else
			{
				assert (targetPatternMatcher.groupCount() == 2);
				
				invokeMethod(targetPatternMatcher.group(0),
							 targetPatternMatcher.group(1));
			}
		}
	}
	
	/**
	 * Private utility method for invoking a named method in a named class.
	 * The named class must be instantiable.
	 * 
	 * @param targetClassName				The name of the class that defines the target method.
	 * @param targetMethodName				The name of the target method.
	 * @throws InvocationTargetException	If the specified method cannot be invoked. This will happen in the following
	 * 										cases:
	 * 										<ul>
	 * 											<li>The class containing the target method cannot be found.</li>
	 * 											<li>The class containing the target method cannot be instantiated.</li>
	 * 											<li>The target method does not exist in the containing class.</li>
	 * 											<li>The target method is not publicly visible.</li>
	 * 										</ul>
	 */
	private static void Action.invokeMethod(String targetClassName, String targetMethodName)
	throws InvocationTargetException
    {
		try
		{
			Class<?>	targetClass;
			Object		targetObj;
			Method		targetMethod;
			
			targetClass		= Class.forName(targetClassName);
			targetObj		= targetClass.newInstance();
			targetMethod	= targetClass.getMethod(targetMethodName, (Class<?>[])null);
			
			targetMethod.invoke(targetObj, (Object[])null);
		}
		
		catch (ClassNotFoundException e)
		{
			throw new InvocationTargetException(e, "Target class '" + targetClassName + "' not found.");
		}
        
        catch (InstantiationException e)
        {
        	throw new InvocationTargetException(e, "Unable to instantiate target class  '" + targetClassName + "'.");
        }
		
		catch (NoSuchMethodException e)
		{
			throw new InvocationTargetException(e, "Unable to invoke target method '" + targetMethodName + "'.");
		}
		
        catch (IllegalAccessException e)
        {
        	throw new InvocationTargetException(e, "Unable to invoke target method '" + targetMethodName + "'.");
        }	    
    }		
}