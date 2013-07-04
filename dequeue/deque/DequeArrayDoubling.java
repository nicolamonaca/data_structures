// IMPLEMENTATION OF A DEQUE USING A HALVING-DOUBLING ARRAY
//   Author: Nicola Lamonaca

package deque;

import java.util.Iterator;
import exceptions.EmptyStructureException;


public class DequeArrayDoubling<T> implements DequeInterface<T>, Iterable<T>
{
	private T[] S;
	private int n; // The actual number of elements in 'S'
	
	
	// CONSTRUCTOR
	public DequeArrayDoubling()
	{
		this.S = (T[]) new Object[1];
		this.n = 0;
	}
	

	// ISEMPTY
	public boolean isEmpty()
	{
		return n == 0;
	}

	
	// ENQUEUE - Inserts an element on the BOTTOM
	public void enqueue(T e)
	{
		// CHECK IF THE ARRAY MUST BE RESIZED
		if(n == S.length)
		{
			T[] temp = (T[]) new Object[S.length * 2];
			
			for(int i = 0; i < n; i++)
				temp[i] = S[i];
			
			S = temp;
		}
		
		// Right-shift all the elements
		for(int i = n; i > 0; i--)
			S[i] = S[i-1];
		
		n++;
		S[0] = e;
		
		System.out.println("Element '" + e + "' successfully enqueued.");
		
	}

	
	// DEQUEUE - Removes an element from the BOTTOM
	public void dequeue()
	{
		if(isEmpty())
			throw new EmptyStructureException("Empty Deque.");
		
		T dequeued = S[0]; // Save the element in order to print it
		
		S[0] = null;
		n--;
		
		// CHECK IF THE ARRAY MUST BE RESIZED
		if(n == S.length / 4)
		{
			T[] temp = (T[]) new Object[S.length / 2];
			
			for(int i = 0; i < n; i++)
				temp[i] = S[i+1];
			
			S = temp;
		}
		
		// Left-shift all the elements
		for(int i = 1; i <= n; i++)
			S[i-1] = S[i];
		
		S[n] = null; // Only for security reasons, we delete the last element left in the array from the previous 'for' cycle
			
		
		System.out.println("Element '" + dequeued + "' successfully dequeued.");
	}

	
	// FIRST - Returns the BOTTOM
	public T first()
	{
		if(isEmpty())
			throw new EmptyStructureException("Empty Deque.");
		
		return S[0];
	}

	
	// PUSH - Inserts an element on the FRONT
	public void push(T e)
	{
		// CHECK IF THE ARRAY MUST BE RESIZED
		if(n == S.length)
		{
			T[] temp = (T[]) new Object[S.length * 2];
			
			for(int i = 0; i < n; i++)
				temp[i] = S[i];
			
			S = temp;
		}
		
		S[n] = e;
		n++;
		
		System.out.println("Element '" + e + "' successfully pushed.");
	}

	
	// POP - Removes an element from the FRONT
	public void pop()
	{
		if(isEmpty())
			throw new EmptyStructureException("Empty Deque.");
		
		T popped = S[n-1]; // Save the element in order to print it
		
		S[n-1] = null;
		n--;
		
		// CHECK IF THE ARRAY MUST BE RESIZED
		if(n == S.length / 4)
		{
			T[] temp = (T[]) new Object[S.length / 2];
			
			for(int i = 0; i < n; i++)
				temp[i] = S[i];
			
			S = temp;
		}
		
		System.out.println("Element '" + popped + "' successfully popped.");
	}

	
	// TOP - Returns the FRONT
	public T top()
	{
		if(isEmpty())
			throw new EmptyStructureException("Empty Deque.");
		
		return S[n-1];
	}
	
	
	// PRINTALL
	private void printAll()
	{
		Iterator<T> itr = this.iterator();
		
		System.out.println("\t" + itr.next());
		
		while(itr.hasNext())
			System.out.println("\t" + itr.next());
	}


	// ITERATOR
	public Iterator<T> iterator()
	{
		return new DequeArrayDoublingIterator(this.S, this.n);
	}
	
	
	// MAIN
	public static void main(String...args)
	{
		DequeInterface<String> DEK = new DequeArrayDoubling<String>();
		
		// INSERT SOME ELEMENTS
		DEK.push("Nicola");
		DEK.enqueue("Mark");
		DEK.push("Alan");
		DEK.enqueue("George");
		DEK.push("Haytham");
		
		System.out.println();
		
		// USING THE ITERATOR
		System.out.println("ITERATOR:");
		((DequeArrayDoubling<String>) DEK).printAll();
		
		System.out.println();
		
		// PRINT THE FRONT AND THE BOTTOM
		System.out.println("Top: " + DEK.top());
		System.out.println("Bottom: " + DEK.first());
		
		System.out.println();
		
		// REMOVE SOME ELEMENTS
		DEK.pop();
		DEK.dequeue();
		
		System.out.println();
		
		// USING THE ITERATOR AGAIN
		System.out.println("ITERATOR:");
		((DequeArrayDoubling<String>) DEK).printAll();
		
		System.out.println();
		
		// PRINT THE NEW FRONT AND BOTTOM
		System.out.println("Top: " + DEK.top());
		System.out.println("Bottom: " + DEK.first());
	}
}

//             -------------------------------------
//  BOTTOM --> |   |   |   |   |   |   |   |   |   | <-- FRONT
//             -------------------------------------