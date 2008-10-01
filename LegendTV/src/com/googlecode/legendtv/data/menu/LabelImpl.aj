package com.googlecode.legendtv.data.menu;

import com.googlecode.legendtv.data.menu.generated.ContentGeneratorReference;
import com.googlecode.legendtv.data.menu.generated.Label;

privileged aspect LabelImpl
{
	@SuppressWarnings("unchecked")
	public void Label.bind() throws InvalidGeneratorException
    {
		ContentGeneratorReference	generatorReference;
    	String						generatedText;

    	generatorReference	= this.content.getDynamicContentReference();
    	
    	if (generatorReference != null)
    	{
//    		if (this.content.getText() == null)
//    			generatorReference.addChangeListener(this);
    		    		
    		generatedText	= (String)generatorReference.getContent();
    		
    		this.content.setText(generatedText);
    	}
    }

    public void Label.contentChanged(ContentGenerator<?> source)
    {
	    try
        {
	        bind();
        }
	    
        catch (InvalidGeneratorException e)
        {
	        // This should never happen
	        e.printStackTrace();
        }
    }
}
