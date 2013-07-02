// IMPLEMENTATION OF AN ITERATOR OVER A DOUBLY LINKED LIST
//   Author: Nicola Lamonaca


package list;

import java.util.Iterator;


public class DoublyLinkedListIterator<T> implements Iterator<T>
{
	DoublyLinkedList<T> list;
	PointerDouble currentRecord;
	
	
	// CONSTRUCTOR
	public DoublyLinkedListIterator(DoublyLinkedList<T> linkedList, PointerDouble firstList)
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
		PointerDouble temp = null;
		
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
