package com.googlecode.legendtv.util;

import java.util.LinkedList;
import java.util.List;

public class ListUtils
{
	public static <T, U extends List<T>> List<T> collapseList(List<U> list)
    {
    	List<T>	retVal	= null;
    	
    	if (list != null)
    	{
    		retVal	= new LinkedList<T>();
    		
	    	for (List<T> curList : list)
	    	{
	    		retVal.addAll(curList);
	    	}
    	}
    	
    	return (retVal);
    }
}
