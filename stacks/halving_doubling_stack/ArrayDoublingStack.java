// IMPLEMENTATION OF A STACK USING A HALVING-DOUBLING ARRAY
//   Author: Nicola Lamonaca

package stack;

import java.util.Iterator;
import exceptions.EmptyStructureException;


public class ArrayDoublingStack<T> implements StackInterface<T>
{
	protected T S[];
	protected int n;
	
	
	// CONSTRUCTOR
	public ArrayDoublingStack()
	{
		this.S = (T[]) new Object[1];
		this.n = 0;
	}
	
	
	// ISEMPTY
	public boolean isEmpty()
	{
		return n == 0;
	}

	
	// PUSH
	public void push(T e)
	{
		if(n == S.length)
		{
			T temp[] = (T[]) new Object[S.length * 2];
			
			for(int i = 0; i < n; i++)
				temp[i] = S[i];
			
			S = temp;
		}
		
		S[n] = e;
		n++;
		
		System.out.println("Element '" + e + "' successfully inserted.");
	}

	
	// TOP
	public T top()
	{
		if(isEmpty())
			throw new EmptyStructureException("Empty stack.");
		
		return S[n-1];
	}

	
	// POP
	public void pop()
	{
		if(n == 0)
			throw new EmptyStructureException("Empty stack.");
		
		// The element to be poped - For informative purposes only
		T popped = S[n-1];
		
		S[n-1] = null;
		n--;
		
		if(n == S.length / 4)
		{
			T temp[] = (T[]) new Object[S.length / 2];
			
			for(int i = 0; i < n; i++)
				temp[i] = S[i];
			
			S = temp;
		}
		
		System.out.println("Element '" + popped + "' successfully popped.");
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
	public Iterator<T> iterator()
	{
		return new ArrayDoublingStackIterator(this.S, this.n);
	}
	
	
	// MAIN
	public static void main(String...args)
	{
		ArrayDoublingStack stack = new ArrayDoublingStack();
		
		// INSERT SOME ELEMENTS
		stack.push("Nicola");
		stack.push("Mark");
		stack.push("Bill");
		stack.push("Stephan");
		
		System.out.println();
		
		// PRINTS : STEPHAN
		System.out.println("Top: " + stack.top());
		
		System.out.println();
		
		// REMOVE 2 ELEMENTS
		stack.pop();
		stack.pop();
		
		System.out.println();
		
		// PRINTS : MARK
		System.out.println("Top: " + stack.top());
		
		System.out.println();
		
		stack.push("Alex");
		stack.push("George");
		
		System.out.println();
		
		// USING THE ITERATOR
		System.out.println("ITERATOR:");
		stack.printAll();
		
		System.out.println();
		
		// PRINTS : GEORGE
		System.out.println("Top: " + stack.top());
	}
}
