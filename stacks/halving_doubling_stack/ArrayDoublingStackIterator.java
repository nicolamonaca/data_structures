// IMPLEMENTATION OF AN ITERATOR OVER A STACK USING A HALVING-DOUBLING ARRAY
//   Author: Nicola Lamonaca

package stack;

import java.util.Iterator;

public class ArrayDoublingStackIterator<T> implements Iterator<T>
{
	private T[] stack;
	private int n;
	private int currentIndex;
	
	
	// CONSTRUCTOR
	public ArrayDoublingStackIterator(T[] stack, int n)
	{
		this.stack = stack;
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
		T temp = stack[currentIndex];
		currentIndex++;
		
		return temp;
	}

	
	// REMOVE
	public void remove()
	{
		throw new UnsupportedOperationException("Operation not supported");
	}
}
