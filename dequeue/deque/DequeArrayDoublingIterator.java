// IMPLEMENTATION OF AN ITERATOR OVER A DEQUE USING A HALVING-DOUBLING ARRAY
//   Author: Nicola Lamonaca

package deque;

import java.util.Iterator;


public class DequeArrayDoublingIterator<T> implements Iterator<T>
{
	private T[] array;
	private int n;
	private int currentIndex;
	
	
	// CONSTRUCTOR
	public DequeArrayDoublingIterator(T[] array, int n)
	{
		this.array = array;
		this.n = n;
		this.currentIndex = 0;
	}

	// HASNEXT
	public boolean hasNext()
	{
		return currentIndex < n;
	}

	
	// NEXT
	public T next()
	{
		return array[currentIndex++];
	}
	

	// REMOVE
	public void remove()
	{
		throw new UnsupportedOperationException("Operation not supported.");
	}
}
