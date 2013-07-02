// IMPLEMENTATION OF AN ITERATOR OVER A CIRCULAR DOUBLY LINKED LIST
//   Author: Nicola Lamonaca


package list;

import java.util.Iterator;


public class CircularDoublyLinkedListIterator<T> implements Iterator<T>
{
	CircularDoublyLinkedList<T> list;
	PointerDouble currentRecord;
	
	
	// CONSTRUCTOR
	public CircularDoublyLinkedListIterator(CircularDoublyLinkedList<T> linkedList)
	{
		this.list = linkedList;
		this.currentRecord = (PointerDouble) list.successor(list.firstList());
	}

	
	// HASNEXT
	public boolean hasNext()
	{
		return currentRecord != list.getDummy();
	}
	

	// NEXT
	public T next()
	{	
		PointerDouble temp = null;
		
		temp = currentRecord;
		currentRecord = currentRecord.link.next;
			
		return (T) temp.link.element;
	}

	
	// REMOVE
	public void remove()
	{
		throw new UnsupportedOperationException("Operation not supported.");
	}
}
