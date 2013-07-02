// IMPLEMENTATION OF AN ITERATOR OVER A QUEUE USING A LINKED STRUCTURE
//   Author: Nicola Lamonaca

package queue;

import java.util.Iterator;
import queue.LinkedQueue.Record;


public class LinkedQueueIterator<T> implements Iterator<T>
{
	private LinkedQueue queue;
	private Record firstRecord;
	private Record currentRecord;

	
	// CONSTRUCTOR
	public LinkedQueueIterator(LinkedQueue queue)
	{
		this.queue = queue;
		this.firstRecord = this.currentRecord = queue.getBegin();
	}
	
	
	// HASNEXT
	public boolean hasNext()
	{
		return currentRecord != null;
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