// IMPLEMENTATION OF A DEQUE USING A DOUBLY LINKED LIST
//   Author: Nicola Lamonaca

package deque;

import java.util.Iterator;
import exceptions.EmptyStructureException;


public class DequeDoublyLinkedList<T> implements DequeInterface<T>, Iterable<T>
{
	private Record<T> list;
	private Record<T> end;
	

	// INNER CLASS
	class Record<T>
	{
		T elem;
		Record next;
		Record prev;
		
		public Record(T e)
		{
			this.elem = e;
			this.next = this.prev = null;
		}
	}

	
	// CONSTRUCTOR
	public DequeDoublyLinkedList()
	{
		this.list = this.end = null;
	}
	

	// ISEMPTY
	public boolean isEmpty()
	{
		return this.list == null;
	}

	
	// ENQUEUE - Inserts an element on the BOTTOM
	public void enqueue(T e)
	{
		Record p = new Record(e);
		
		if(isEmpty())
		{
			list = p.prev = p.next = p;
		
			// Update the pointer to 'end'
			end = p;
		}
		else
		{
			p.next = list;
			list.prev = p;
			list = p;
		}

		System.out.println("Element '" + e + "' successfully enqueued.");
	}

	
	// DEQUEUE - Removes an element from the BOTTOM
	public void dequeue()
	{
		// If the list is empty
		if(isEmpty())
			throw new EmptyStructureException("Empty Deque.");
		
		T dequeued = this.list.elem;
		
		// If the element is the only one in the sequence
		if(list == end)
			list = null;
		else
			list = list.next;
			
		System.out.println("Element '" + dequeued + "' successfully dequeued.");
	}

	
	// FIRST - Returns the BOTTOM
	public T first()
	{
		if(isEmpty())
			throw new EmptyStructureException("Empty Deque.");
		
		if(end == null)
			return (T) null;
		
		return list.elem;
	}

	
	// PUSH - Inserts an element on the FRONT
	public void push(T e)
	{
		Record p = new Record(e);
		
		if(isEmpty())
			list = p.prev = p.next = p;
		else
		{
			p.prev = end; // <-
			end.next = p; // ->
			
			end = p;
		}
		
		// Update the pointer to 'end'
		end = p;
		
		System.out.println("Element '" + e + "' successfully pushed.");
	}

	
	// POP - Removes an element from the FRONT
	public void pop()
	{
		if(isEmpty())
			throw new EmptyStructureException("Empty Deque.");
		
		T popped = end.elem;
		
		// UPDATE THE END
		end = end.prev;
		
		System.out.println("Element '" + popped + "' successfully popped.");
	}

	
	// TOP - Returns the FRONT
	public T top()
	{
		if(isEmpty())
			throw new EmptyStructureException("Empty Deque.");
		
		if(end == null)
			return (T) null;
		
		return end.elem;
	}
	
	
	// PRINTALL
	private void printAll()
	{
		Iterator<T> itr = this.iterator();
		
		//System.out.println("\t" + itr.next());
		
		while(itr.hasNext())
			System.out.println("\t" + itr.next());
	}


	// ITERATOR
	public Iterator<T> iterator()
	{
		return new DequeDoublyLinkedListIterator(this.list, this.end);
	}
	
	
	// MAIN
	public static void main(String...args)
	{
		DequeInterface<String> DEK = new DequeDoublyLinkedList<String>();
		
		// INSERT SOME ELEMENTS
		DEK.push("Nicola");
		DEK.enqueue("Mark");
		DEK.push("Alan");
		DEK.enqueue("George");
		DEK.push("Haytham");
		DEK.enqueue("Michael");
		
		System.out.println();
		
		// USING THE ITERATOR
		System.out.println("ITERATOR:");
		((DequeDoublyLinkedList<String>) DEK).printAll();
	
		System.out.println();
		
		// PRINT THE FRONT AND THE BOTTOM
		System.out.println("Bottom: " + DEK.first());
		System.out.println("Top: " + DEK.top());
		
		System.out.println();
		
		// REMOVE SOME ELEMENTS
		DEK.pop();
		DEK.dequeue();
		DEK.dequeue();
		
		System.out.println();
		
		// USING THE ITERATOR AGAIN
		System.out.println("ITERATOR:");
		((DequeDoublyLinkedList<String>) DEK).printAll();
		
		System.out.println();
		
		// PRINT THE NEW FRONT AND BOTTOM
		System.out.println("Bottom: " + DEK.first());
		System.out.println("Top: " + DEK.top());
	}
}

//             -------------------------------------
//  BOTTOM --> |   |   |   |   |   |   |   |   |   | <-- FRONT
//             -------------------------------------