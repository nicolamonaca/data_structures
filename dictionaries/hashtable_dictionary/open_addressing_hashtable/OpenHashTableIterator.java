// IMPLEMENTATION OF AN ITERATOR OVER A DICTIONARY USING AN ARRAY WITH OPEN ADRESSING
//   Author: Nicola Lamonaca

package dictionary;

import java.util.Iterator;
import dictionary.OpenHashTable.Record;


public class OpenHashTableIterator implements Iterator
{
	private Record[] S;
	private int currentIndex;
	
	
	// CONSTRUCTOR
	public OpenHashTableIterator(Record[] array)
	{
		this.S = array;
		this.currentIndex = 0;
	}
	
	
	// HASNEXT
	public boolean hasNext()
	{
		return currentIndex < S.length;
	}

	
	// NEXT
	public Object next()
	{
		int temp = currentIndex;
		currentIndex++;
		return S[temp];
	}

	
	// REMOVE
	public void remove()
	{
		throw new UnsupportedOperationException("Operation not supported.");
	}
}
