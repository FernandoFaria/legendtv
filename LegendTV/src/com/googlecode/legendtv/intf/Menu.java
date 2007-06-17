package com.googlecode.legendtv.intf;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import com.googlecode.legendtv.util.XMLNavigator;
import com.sun.tools.javac.util.Pair;

interface ParseDelegate
{
	public boolean parse() throws XMLStreamException;
}

public class Menu
{
	private String	name;
	private String	title;
	
	public Menu(String filename, String name)
	throws FileNotFoundException, XMLStreamException
	{
		this.name	= name;
		
		parseFile(filename);
	}

	@SuppressWarnings("unchecked")
    private void parseFile(String filename)
	throws FileNotFoundException, XMLStreamException
    {
		XMLInputFactory	factory		= XMLInputFactory.newInstance();
		FileReader		fileReader	= new FileReader(filename);
		XMLStreamReader	xmlReader	= factory.createXMLStreamReader(fileReader);
		XMLNavigator	nav			= new XMLNavigator(xmlReader);
		
		if (nav.navigateTo("menu", new Pair<String, String>("name", name)))
		{
			nav.setStayInElement(true);
			
			if (nav.navigateTo("title"))
			{
			}
		}
    }
}