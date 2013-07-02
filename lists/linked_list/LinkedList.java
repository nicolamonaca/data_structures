// IMPLEMENTATION OF A LIST USING A LINKED STRUCTURE
//   Author: Nicola Lamonaca

package list;

import java.util.Iterator;
import exceptions.EmptyStructureException;


public class LinkedList<T> implements ListInterface<T>, Iterable<T>
{
	private Pointer firstList = null;
	
	
	// ISEMPTY
	public boolean isEmpty()
	{
		return firstList == null;
	}
	

	// READLIST
	public T readList(PositionInterface p)
	{
		if(isEmpty())
			throw new EmptyStructureException("Empty list.");
		
		if(p == firstList())
			return (T) this.firstList.link.element;
		
		return (T) ((Pointer) p).link.next.link.element;
	}
	

	// WRITELIST
	public void writeList(T e, PositionInterface p)
	{
		if(p == firstList())
			firstList.link.element = e;
		else
			((Pointer) p).link.next.link.element = e;
	}
	

	// FIRSTLIST
	public PositionInterface firstList()
	{
		return null;
	}
	

	// ENDLIST
	public boolean endList(PositionInterface p)
	{
		if(isEmpty())
			return true;
		
		if(p == firstList())
			return false;
		
		return ((Pointer) p).link.next == null;
	}
	

	// SUCCESSOR
	public PositionInterface successor(PositionInterface p)
	{
		if(endList(p))
			throw new IndexOutOfBoundsException("Position is not valid.");
		
		if(isEmpty())
			throw new EmptyStructureException("Empty list.");
		
		if(p == firstList())
			return firstList;
		
		return ((Pointer) p).link.next;
	}
	

	// PREDECESSOR
	public PositionInterface predecessor(PositionInterface p)
	{
		if(p == firstList())
			throw new IndexOutOfBoundsException("Position is not valid.");
		
		if(isEmpty())
			throw new EmptyStructureException("Empty list.");
		
		Pointer pred  = firstList;
		
		// Scan the list, until the record pointing to 'p' is found
		while(pred.link.next != p)
		{
			System.out.println("pred: " + pred);
			pred = pred.link.next;
		}
		
		return pred;
	}

	
	// INSERT
	public void insert(T e, PositionInterface p)
	{
		// Create a new element
		Pointer newRecord = new Pointer(new Cell(e));
		Pointer temp;
		
		if(isEmpty())
			this.firstList = newRecord;
		else
		{
			if(p == firstList()) 
			{
				temp = firstList;
				firstList = newRecord;
				this.firstList.link.next = temp;
			}
			else
			{
				temp = ((Pointer) p).link.next;
				((Pointer) p).link.next = newRecord;
				newRecord.link.next = temp;
			}
		}
		
		System.out.println("Element '" + e + "' successfully inserted.");
	}
	

	// REMOVE
	public void remove(PositionInterface p)
	{
		if(isEmpty())
			throw new EmptyStructureException("Empty list. Cannot remove.");
		else
		{
			if(p == firstList())
				firstList = firstList.link.next;
			else if(endList(p))
				((Pointer) p).link.next = null;
			else
			{
				Pointer temp;
				
				temp = ((Pointer) p).link.next.link.next;
				((Pointer) p).link.next = temp;
			}
		}
		
		System.out.println("Element successfully removed.");
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
		return new LinkedListIterator(this, this.firstList);
	}
	
	
	// MAIN
	public static void main(String...args)
	{
		ListInterface<String> LL = (ListInterface<String>) new LinkedList<String>();
		
		LL.insert("Nicola", LL.firstList());
		LL.insert("Alan", LL.successor(LL.firstList()));
		LL.insert("George", LL.successor(LL.successor(LL.firstList())));
		LL.insert("Haytham", LL.firstList());
		LL.insert("Raz", LL.firstList());
		LL.insert("Logan", LL.firstList());
		LL.insert("Connor", LL.firstList());
		
		LL.writeList("Mark", LL.successor(LL.firstList()));
		
		System.out.println();
		
		// USING THE ITERATOR
		System.out.println("ITERATOR:");
		((LinkedList<String>) LL).printAll();
		
		System.out.println();
		
		LL.remove(LL.successor(LL.firstList()));
		LL.remove(LL.successor(LL.successor(LL.firstList())));
		
		System.out.println();
		
		// USING THE ITERATOR AGAIN
		System.out.println("ITERATOR:");
		((LinkedList<String>) LL).printAll();
	}
}
