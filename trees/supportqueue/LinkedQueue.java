// IMPLEMENTATION OF A QUEUE USING A LINKED STRUCTURE
//   Author: Nicola Lamonaca

package supportqueue;

import java.util.Iterator;
import exceptions.EmptyStructureException;


public class LinkedQueue<T> implements QueueInterface<T>, Iterable<T>
{
	private Record begin;
	private Record end;
	
	
	// CONSTRUCTOR
	public LinkedQueue()
	{
		this.begin = this.end = null;
	}
	
	// INNER CLASS
	class Record
	{
		T elem;
		Record next;
		
		public Record(T e)
		{
			this.elem = e;
		}
	}
	
	
	// GETBEGIN
	public Record getBegin()
	{	
		return this.begin;
	}
	
	
	// ISEMPTY
	public boolean isEmpty()
	{
		return this.begin == null;
	}

	
	// ENQUEUE
	public void enqueue(T e)
	{
		if(isEmpty())
			this.begin = this.end = new Record(e);
		else
		{
			end.next = new Record(e);
			end = end.next;
		}
	}

	
	// FIRST
	public T first()
	{
		if(isEmpty())
			throw new EmptyStructureException("Empty queue.");
		
		return begin.elem;
	}

	
	// DEQUEUE
	public T dequeue()
	{
		if(isEmpty())
			throw new EmptyStructureException("Empty queue.");
		
		T dequeued = begin.elem;
		
		begin.elem = null;
		begin = begin.next;
		
		return dequeued;
	}
	
	
	// PRINTALL
	private void printAll()
	{
		Iterator itr = this.iterator();
		
		System.out.println("\t" + itr.next());
		
		while(itr.hasNext())
			System.out.println("\t" + itr.next());
	}
	
	
	// ITERATOR
	public Iterator iterator()
	{
		return new LinkedQueueIterator(this);
	}
	
	
	// MAIN
	public static void main(String...args)
	{
		LinkedQueue<String> LQ = new LinkedQueue();
		
		LQ.enqueue("Nicola");
		LQ.enqueue("Isabel");
		LQ.enqueue("Anna");
		LQ.enqueue("Nathan");
		LQ.enqueue("Haytham");
		
		System.out.println();
		
		// USING THE ITERATOR
		System.out.println("ITERATOR:");
		LQ.printAll();
		
		System.out.println();
		
		System.out.println("First element is: " + LQ.first());
		
		LQ.dequeue();
		
		System.out.println("First element is: " + LQ.first());
	}
}
