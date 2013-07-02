// IMPLEMENTATION OF AN ITERATOR OVER A LIST USING A LINKED STRUCTURE
//   Author: Nicola Lamonaca


package list;

import java.util.Iterator;


public class LinkedListIterator<T> implements Iterator<T>
{
	LinkedList<T> list;
	Pointer currentRecord;
	
	
	// CONSTRUCTOR
	public LinkedListIterator(LinkedList<T> linkedList, Pointer firstList)
	{
		this.list = linkedList;
		this.currentRecord = firstList;
	}

	
	// HASNEXT
	public boolean hasNext()
	{
		return this.currentRecord != null;
	}
	

	// NEXT
	public T next()
	{
		Pointer temp = null;
		
		if(this.currentRecord != null)
		{
			temp = currentRecord;
			currentRecord = currentRecord.link.next;
			
			return (T) temp.link.element;
		}
	
		return null;
	}

	
	// REMOVE
	public void remove()
	{
		throw new UnsupportedOperationException("Operation not supported.");
	}
}
