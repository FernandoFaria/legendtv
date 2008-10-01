package com.googlecode.legendtv.test.drivers.faults;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class NativeLoader extends ClassLoader
{
	private String			nativeClassName;
	
	public NativeLoader(String nativeClassName)
	{
		super(NativeLoader.class.getClassLoader());
		
		this.nativeClassName	= nativeClassName;
		
		System.out.println("NativeLoader has been created!");
	}
	
	@Override
	public void finalize() throws Throwable
	{
		super.finalize();
		System.out.println("NativeLoader is finalizing!");
		
		FinalizationInfo.setFinalized(true);
	}

	private byte[] loadClassFile(String fileName)
	throws FileNotFoundException, IOException
	{
		File			classFile	= new File(fileName);
		FileInputStream	classStream	= new FileInputStream(classFile);
		int				length		= (int)classFile.length();
		byte[]			classBytes	= new byte[length];
		
		classStream.read(classBytes, 0, length);
		classStream.close();
		
		return classBytes;
	}
	
	@Override
	protected synchronized Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException
	{
		Class<?>	retVal	= null;
		
		if (name.equals(nativeClassName))
		{
			try
			{
				String	fileName	= name.replaceAll("\\.", "/");
				byte[]	classBytes	= loadClassFile("./" + fileName + ".class");
				
				retVal = super.defineClass(name, classBytes, 0, classBytes.length);
				
				if (resolve)
					super.resolveClass(retVal);
			}
			
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	
		else
			retVal	= super.loadClass(name, resolve);
		
		return (retVal);
	}
}
