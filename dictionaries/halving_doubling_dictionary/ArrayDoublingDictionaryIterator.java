// IMPLEMENTATION OF AN ITERATOR OVER A DICTIONARY USING A HALVING-DOUBLING ARRAY
//   Author: Nicola Lamonaca

package dictionary;

import java.util.Iterator;


class ArrayDoublingDictionaryIterator<T> implements Iterator<T>
{
	private int currentIndex;
	private ArrayDoublingDictionary<T> dictionary;

	
	// CONSTRUCTOR
	public ArrayDoublingDictionaryIterator(ArrayDoublingDictionary<T> dictionary)
	{
		this.dictionary = dictionary;
		this.currentIndex = 0;
	}
	

	// HASNEXT
	public boolean hasNext()
	{
		return currentIndex < dictionary.n;
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
