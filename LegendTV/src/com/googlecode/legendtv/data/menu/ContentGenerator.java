package com.googlecode.legendtv.data.menu;

import com.googlecode.legendtv.data.menu.generated.ContentGeneratorReference;

/**
 * Abstract parent class for dynamic content generators.
 * 
 * @author Guy Paddock
 * @param <T>	The type of content that the generator generates.
 */
public abstract class ContentGenerator<T>
{
	/**
	 * Method that clients will call to obtain the content of this generator.
	 * This method is abstract and must be overloaded by child classes.
	 * 
	 * @return	The content of this generator.
	 */
	public abstract T getContent();

	/**
	 * Static method for obtaining the content generator with the given class name.
	 * 
	 * @param <U>	The type of content that the generator generates.
	 * @param name	The package-qualified class name of the generator to instantiate.
	 * 
	 * @return		A new instance of the named generator.
	 * 
	 * @throws InvalidGeneratorException	If the named generator cannot be instantiated.
	 * 										This can be for the following reasons:
	 * 										<ul>
	 * 											<li>The named generator class cannot be found.</li>
	 * 											<li>The named generator has a private constructor.</li>
	 * 											<li>The named generator is not publicly visible.</li>
	 * 										</ul> 
	 */
	@SuppressWarnings("unchecked")
    public static <U> ContentGenerator<U> forName(String name)
    throws InvalidGeneratorException
	{
		Class<?>			classObj;
		ContentGenerator<U>	provider;

		try
		{
			classObj	= Class.forName(name);
			provider	= (ContentGenerator<U>)classObj.newInstance();
			
			return (provider);
		}
		
		catch (ClassNotFoundException e)
		{
			throw new InvalidGeneratorException("The content generator '" + name + "' could not be found.");
		}
		
		catch (InstantiationException e)
		{
			throw new InvalidGeneratorException(
				String.format("Unable to instantiate content generator '%s': %s", name, e.getMessage()));
		}
		
		catch (IllegalAccessException e)
		{
			throw new InvalidGeneratorException(
				String.format("Unable to instantiate content generator '%s': %s", name, e.getMessage()));
		}
	}
}