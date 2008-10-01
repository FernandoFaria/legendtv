package com.googlecode.legendtv.data.menu;

import java.util.LinkedList;
import java.util.List;

import com.googlecode.legendtv.data.menu.generated.ContentGeneratorReference;
import com.googlecode.legendtv.util.ListUtils;

/**
 * Implementation class for the schema-derived ContentGeneratorReference data type.
 * This class provides methods that act on the ContentGeneratorReference data marshalled from XML.
 * 
 * @author Guy Paddock
 *
 * @param <T>	The type of content that this generator generates.
 */
privileged aspect ContentGeneratorReferenceImpl
{
	/**
	 * The content generator this instance references.
	 */
	private ContentGenerator<?>					ContentGeneratorReference.generator;

	/**
	 * The content change listeners that will be informed when the generated content changes.
	 */
	private List<ContentChangeListener>			ContentGeneratorReference.listeners;
	
	private ContentGeneratorReference			ContentGeneratorReference.linkedGeneratorReference;
	
	before(ContentGeneratorReference instance) :
		execution(void ContentGeneratorReference.setGeneratorName(..)) &&
		this(instance)
	{
		instance.generator	= null;
	}
	
    before(ContentGeneratorReference instance) :
    	execution(void ContentGeneratorReference.setLinkedReferenceName(..)) &&
    	this(instance)
    {
//	    if (instance.linkedGeneratorReference != null)
//	    	instance.linkedGeneratorReference.removeChangeListener(instance);
    }

	public Object ContentGeneratorReference.getContent()
	throws InvalidGeneratorException
	{
		if (this.generator == null)
			fillInGenerator();
		
		assert (this.generator != null);
		
		return (this.generator.getContent());
	}
	
	private void ContentGeneratorReference.fillInGenerator()
	throws InvalidGeneratorException
	{
		if (this.generatorName == null)
			throw new IllegalStateException("Generator name is not set.");
		
		this.generator	= ContentGenerator.forName(this.generatorName);
		
		assert (this.generator != null);
	}
	
	void ContentGeneratorReference.contentChanged()
	{
		if (this.listeners != null)
		{
			for (ContentChangeListener currentListener : this.listeners)
			{
				currentListener.contentChanged(this.generator);
			}
		}
	}

	@SuppressWarnings("unchecked")
    public static <T> List<T> ContentGeneratorReference.generateAllContent(List<ContentGeneratorReference> generatorReferenceList)
    throws InvalidGeneratorException
    {
    	List<T>	returnValue	= new LinkedList<T>();
    	
    	if (generatorReferenceList == null)
    		throw new IllegalArgumentException("generatorReferenceList cannot be null");
    	
    	for (ContentGeneratorReference currentReference : generatorReferenceList)
		{
    		T	generatedContent;
    		
    		generatedContent	= (T)currentReference.getContent();
    		
    		returnValue.add(generatedContent);
		}
    	
    	return (returnValue);
    }
	
	@SuppressWarnings("unchecked")
    public static <T> List<T> ContentGeneratorReference.bindList(List<ContentGeneratorReference> generatorReferenceList,
    															 boolean addAsListener, ContentChangeListener listener)
    throws InvalidGeneratorException
    {
    	List<List<T>>	generatedListList;
    	List<T>			returnValue;
    	
    	if (generatorReferenceList == null)
    		throw new IllegalArgumentException("dynRefList cannot be null.");
    	
    	if (addAsListener && (listener == null))
    		throw new IllegalArgumentException("listener cannot be null when addAsListener is true.");
    	
//		if (addAsListener)
//		{
//			for (ContentGeneratorReference curDynContent : generatorReferenceList)
//		    {
//		    	curDynContent.addChangeListener(listener);
//		    }
//		}
    		
		generatedListList	= ContentGeneratorReference.generateAllContent(generatorReferenceList);
		returnValue			= ListUtils.collapseList(generatedListList);
		
		return (returnValue);
    }
}
