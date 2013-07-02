// IMPLEMENTATION OF AN ITERATOR OVER A DICTIONARY USING A DOUBLY LINKED LIST
//   Author: Nicola Lamonaca

package dictionary;

import java.util.Iterator;
import dictionary.LinkedDictionary.Record;


public class LinkedDictionaryIterator<T> implements Iterator<T>
{
	private Record firstRecord;
	private Record currentRecord;

	
	// CONSTRUCTOR
	public LinkedDictionaryIterator(LinkedDictionary<T> dict)
	{
		firstRecord = currentRecord = dict.list;
	}


	// HASNEXT
	public boolean hasNext()
	{
		return currentRecord != firstRecord;
	}


	// NEXT
	public T next()
	{
		T temp = (T) currentRecord.elem;
		currentRecord = currentRecord.next;
			
		return temp;
	}


	// REMOVE
	public void remove()
	{
		throw new UnsupportedOperationException("Operation not supported.");
	}
}