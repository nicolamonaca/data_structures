// IMPLEMENTATION OF AN ITERATOR OVER A DEQUE USING A HALVING-DOUBLING ARRAY
//   Author: Nicola Lamonaca

package deque;

import java.util.Iterator;
import deque.DequeDoublyLinkedList.Record;


public class DequeDoublyLinkedListIterator<T> implements Iterator<T>
{
	private Record currentRecord;
	private Record end;
	
	
	// CONSTRUCTOR
	public DequeDoublyLinkedListIterator(Record begin, Record end)
	{
		this.currentRecord = begin;
		this.end = end;
	}

	
	// HASNEXT
	public boolean hasNext()
	{
		return currentRecord != end.next;
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