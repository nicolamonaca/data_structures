// IMPLEMENTATION OF AN ITERATOR OVER A DICTIONARY USING A SORTED ARRAY
//   Author: Nicola Lamonaca

package dictionary;

import java.util.Iterator;


class SortedArrayDictionaryIterator<T> implements Iterator<T>
{
	private int currentIndex;
	private SortedArrayDictionary<T> dictionary;

	
	// CONSTRUCTOR
	public SortedArrayDictionaryIterator(SortedArrayDictionary<T> dictionary)
	{
		this.dictionary = dictionary;
		this.currentIndex = 0;
	}
	

	// HASNEXT
	public boolean hasNext()
	{
		return currentIndex < dictionary.S.length;
	}

	
	// NEXT
	public T next()
	{
		return (T) dictionary.S[currentIndex++];	
	}


	// REMOVE
	public void remove()
	{
		throw new UnsupportedOperationException("Operation not supported.");
	}
}