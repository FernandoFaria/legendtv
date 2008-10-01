package com.googlecode.legendtv.data.menu;

import java.util.LinkedList;
import java.util.List;

import com.googlecode.legendtv.data.menu.generated.ContentGeneratorReference;
import com.googlecode.legendtv.data.menu.generated.Grid;
import com.googlecode.legendtv.data.menu.generated.TableContent.Row;

privileged aspect GridImpl
{
	private List<String>	Grid.generatedHeaderList;
	private List<Row>		Grid.generatedRowList;

	public List<String> Grid.getHeaders()
	{
		List<String>	retVal	= new LinkedList<String>();
		
		retVal.addAll(this.headerList.getHeaders());
		
		if (generatedHeaderList != null)
			retVal.addAll(this.generatedHeaderList);

		return (retVal);
	}
	
	public List<Row> Grid.getRows()
	{
		List<Row>	retVal	= new LinkedList<Row>();
		
		retVal.addAll(this.content.getRows());
		
		if (generatedHeaderList != null)
			retVal.addAll(this.generatedRowList);

		return (retVal);
	}

    public void Grid.bind()
    throws InvalidGeneratorException
    {   
		bindHeaders();
		bindRows();
		bindLegend();		
    }    

    private void Grid.bindHeaders()
    throws InvalidGeneratorException
    {
    	List<ContentGeneratorReference>	generatorReferenceList;
    	
    	generatorReferenceList	= this.headerList.getContentGeneratorReferences();
    	
    	if ((generatorReferenceList != null) && (generatorReferenceList.size() > 0))
    	{
    		this.generatedHeaderList	=
    			ContentGeneratorReference.bindList(generatorReferenceList,
    											   false, null);
//												   (this.generatedHeaderList == null),
//												   this);
    	}
    }
    
    private void Grid.bindRows()
    throws InvalidGeneratorException
    {
    	List<ContentGeneratorReference>	generatorReferenceList;
    	
    	generatorReferenceList	= this.content.getContentGeneratorReferences();
    	
    	if ((generatorReferenceList != null) && (generatorReferenceList.size() > 0))
    	{
    		this.generatedRowList	=
    			ContentGeneratorReference.bindList(generatorReferenceList,
												   false, null);
//												   (this.generatedRowList == null),
//												   this);
    	}
    }

    private void Grid.bindLegend()
    throws InvalidGeneratorException
    {
    	ContentGeneratorReference	generatorReference;
    	String						generatedString;

    	generatorReference	= this.legend.getDynamicContentReference();
    	
    	if (generatorReference != null)
    	{
//    		if (this.legend.getText() == null)
//    			generatorReference.addChangeListener(this);
    		    		
    		generatedString	= (String)generatorReference.getContent();
    		this.legend.setText(generatedString);
    	}
    }
}
