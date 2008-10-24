package view.utils;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * A special type of PlainDocument that has a maximum length.
 * This is typically used for JTextFields that need a maximum field length.
 * 
 * @author Guy Paddock (gap7472@rit.edu)
 */
public class LimitedLengthDocument extends PlainDocument
{
	/**
	 * The maximum length, in characters, that this document can hold.
	 */
	private int maxLength;
	
	/**
	 * Initializes a new instance of LimitedLengthDocument that can hold up
	 * to <code>maxLength</code> characters of text.
	 * 
	 * @param maxLength	The maximum number of characters this document can hold.
	 */
	public LimitedLengthDocument(int maxLength)
	{
		this.maxLength	= maxLength;
	}
	
	/**
	 * Inserts some content into the document. Inserting content causes a write
	 * lock to be held while the actual changes are taking place, followed by
	 * notification to the observers on the thread that grabbed the write lock.
	 * 
	 * Attempts to insert strings that cause this document to exceed its
	 * maximum length are truncated.
	 */
	@Override
	public void insertString(int offs, String str, AttributeSet a)
			throws BadLocationException
	{
		int maxInsertLength	= this.maxLength - this.getLength();
		
		if (str.length() > maxInsertLength)
			str = str.substring(0, maxInsertLength);
		
		super.insertString(offs, str, a);
	}
}
