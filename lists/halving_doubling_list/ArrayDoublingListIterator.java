// IMPLEMENTATION OF AN ITERATOR OVER A LIST WITH A HALVING-DOUBLING ARRAY
//    Author: Nicola Lamonaca

package list;

import java.util.Iterator;


public class ArrayDoublingListIterator<T> implements Iterator<T>
{
	private ArrayDoublingList list;
	private PositionInterface firstList;
	private PositionInterface currentRecord;
	private int n;
	private int i;
	
	
	// CONSTRUCTOR
	public ArrayDoublingListIterator(ArrayDoublingList list, int n)
	{
		this.list = list;
		this.n = n;
		this.i = 0;
		this.firstList = this.currentRecord = list.firstList();
	}
	
	
	// HASNEXT
	public boolean hasNext()
	{
		return i < n-1;
	}

	
	// NEXT
	public T next()
	{
		PositionInterface temp = currentRecord;
		currentRecord = list.successor(currentRecord);
		i++;
		
		return (T) list.readList(temp);
	}

	
	// REMOVE
	public void remove()
	{
		throw new UnsupportedOperationException("Operation not supported.");
	}
}
