// // IMPLEMENTATION OF A LIST WITH A HALVING-DOUBLING ARRAY
//    Author: Nicola Lamonaca

package list;

import java.util.Iterator;
import exceptions.EmptyStructureException;


public class ArrayDoublingList<T> implements ListInterface<T>, Iterable<T>
{
	private T[] S = (T[]) new Object[1];
	private int n = 0;
	
	
	// CHECKPOSITION
	protected boolean checkPosition(PositionInterface p)
	{
		if(((IndexedPosition)p).index < 0 || ((IndexedPosition)p).index > n)
			return false;
		
		return true;
	}
	

	// ISEMPTY
	public boolean isEmpty()
	{
		return n == 0;
	}
	

	// READLIST
	public T readList(PositionInterface p)
	{
		if(isEmpty())
			throw new EmptyStructureException("Empty list.");
		
		if(!checkPosition(p))
			throw new IndexOutOfBoundsException("Position not valid.");
		
		return S[((IndexedPosition)p).index];
	}

	
	// WRITELIST - Overwrites the element in position 'p'
	public void writeList(T e, PositionInterface p)
	{
		if(isEmpty())
			throw new EmptyStructureException("Empty list.");
		
		S[((IndexedPosition)p).index] = e;
		
		n++;
		
		// CHECK IF THE ARRAY MUST BE RESIZED
		if(n == S.length)
		{
			T[] temp = (T[]) new Object[S.length * 2];
			
			for(IndexedPosition pos = (IndexedPosition) firstList(); !endList(pos); pos = (IndexedPosition) successor(pos))
				temp[pos.index] = S[pos.index];
			
			S = temp;
		}
		
		System.out.println("Element '" + e + "' successfully written.");
	}
	

	// FIRSTLIST
	public PositionInterface firstList()
	{
		return new IndexedPosition();
	}

	
	// ENDLIST
	public boolean endList(PositionInterface p)
	{
		return ((IndexedPosition) p).index == n;
	}

	
	// SUCCESSOR
	public PositionInterface successor(PositionInterface p)
	{
		if(endList(p))
			throw new IndexOutOfBoundsException(((IndexedPosition)p).index + " is the last valid position in the list.");
		
		IndexedPosition pos = new IndexedPosition();
		
		pos.index = ((IndexedPosition)p).index + 1;
		
		return pos;
	}
	

	// PREDECESSOR
	public PositionInterface predecessor(PositionInterface p)
	{
		if((IndexedPosition)p == firstList())
			throw new IndexOutOfBoundsException(((IndexedPosition)p).index + " is the first valid position in the list.");
		
		IndexedPosition pos = new IndexedPosition();
		
		pos.index = ((IndexedPosition)p).index - 1;
		
		return pos;
	}

	
	// INSERT - Inserts an element in position 'p', right-shifting all the elements following position 'p'
	public void insert(T e, PositionInterface p)
	{
		if(!checkPosition(p))
			throw new IndexOutOfBoundsException("Position not valid. Cannot insert.");
		
		// RIGHT-SHIFTING - Creates a blank space in position 'p'
		for(int i = n; i > ((IndexedPosition)p).index; i--)
			S[i] = S[i-1];
		
		// Insert the new element in the blank space in position 'p'
		S[((IndexedPosition)p).index] = e;
		
		n++;
		
		// CHECK IF THE ARRAY MUST BE RESIZED
		if(n == S.length)
		{
			T[] temp = (T[]) new Object[S.length * 2];
			
			for(IndexedPosition pos = (IndexedPosition) firstList(); !endList(pos); pos = (IndexedPosition) successor(pos))
				temp[pos.index] = S[pos.index];
			
			S = temp;
		}
		
		System.out.println("Element '" + e + "' successfully inserted.");
	}

	
	// REMOVE
	public void remove(PositionInterface p)
	{
		if(isEmpty())
			throw new EmptyStructureException("Empty list.");
		
		if(!checkPosition(p))
			throw new IndexOutOfBoundsException("Invalid position.");
		
		// The element to be deleted - For informative purposes only
		T element = readList(p);
		
		// Overwrite the element in position 'p' by left-shifting all the elements in [p+1, n]
		for(int i = ((IndexedPosition)p).index; i < n; i++)
			S[i] = S[i+1];
		
		n--;
		
		// CHECK IF THE ARRAY MUST BE RESIZED
		if(n == S.length / 4)
		{
			T[] temp = (T[]) new Object[S.length / 2];
			
			for(IndexedPosition pos = (IndexedPosition) firstList(); !endList(pos); pos = (IndexedPosition) successor(pos))
				temp[pos.index] = S[pos.index];
			
			S = temp;
		}
		
		System.out.println("Element '" + element + "' successfully removed.");
			
	}
	
	
	// PRINTALL
	private void printAll()
	{
		Iterator<T> itr = this.iterator();
		
		System.out.println(itr.next());
		
		while(itr.hasNext())
			System.out.println(itr.next());
	}
	
	
	// ITERATOR
	public Iterator<T> iterator()
	{
		return new ArrayDoublingListIterator(this, this.n);
	}
	
	
	// MAIN
	public static void main(String...args)
	{
		ListInterface<String> ADL = new ArrayDoublingList<String>();
		
		ADL.insert("Nicola", ADL.firstList());
		ADL.insert("Mark", ADL.firstList());
		ADL.insert("Alan", ADL.firstList());
		ADL.writeList("John", ADL.firstList()); // Overwrites 'Alan'
		ADL.insert("Logan", ADL.firstList());
		
		System.out.println();
		
		// USING THE ITERATOR
		System.out.println("ITERATOR:");
		((ArrayDoublingList<String>) ADL).printAll();
		
		System.out.println();
		
		ADL.remove(ADL.firstList());
		ADL.remove(ADL.firstList());
		
		System.out.println();
		
		// USING THE ITERATOR AGAIN
		System.out.println("ITERATOR:");
		((ArrayDoublingList<String>) ADL).printAll();
	}
}
