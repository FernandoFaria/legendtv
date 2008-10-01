package com.googlecode.legendtv.data.menu;

import java.util.LinkedList;
import java.util.List;

import com.googlecode.legendtv.data.menu.generated.ContentGeneratorReference;
import com.googlecode.legendtv.data.menu.generated.Option;
import com.googlecode.legendtv.data.menu.generated.OptionList;

privileged aspect OptionListImpl
{
	private List<Option>		OptionList.generatedOptionList;
//	private Set<ActionListener>	OptionList.listeners;
	
//	public OptionList.new()
//	{
//		this.listeners	= new HashSet<ActionListener>();
//	}
	
	public List<Option> OptionList.getOptions()
	{
		List<Option>	retVal;
		
		assert (this.getContent() != null);
		
		if (this.generatedOptionList != null)
		{
			retVal = new LinkedList<Option>();
		
			retVal.addAll(this.getContent().getOptions());
			retVal.addAll(this.generatedOptionList);
		}
		
		else
			retVal	= this.getContent().getOptions();
		
		return (retVal);
	}
	
    public void OptionList.bind()
	throws InvalidGeneratorException
    {
		List<ContentGeneratorReference>	generatorReferenceList;
		
		generatorReferenceList		= this.content.getContentGeneratorReferences();
		
		if ((generatorReferenceList != null) && (!generatorReferenceList.isEmpty()))
		{
			this.generatedOptionList	=
				ContentGeneratorReference.bindList(generatorReferenceList,
												   false, null);
//												   (this.generatedOptionList == null),
//												   this);
		}
    }
}
