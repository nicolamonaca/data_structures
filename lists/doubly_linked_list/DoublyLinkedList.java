// IMPLEMENTATION OF A DOUBLY LINKED LIST
//   Author: Nicola Lamonaca


package list;

import java.util.Iterator;
import exceptions.EmptyStructureException;


public class DoublyLinkedList<T> implements ListInterface<T>
{
	private PointerDouble firstList = null;
	
	
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
		
		return (T) ((PointerDouble) p).link.element;
	}

	
	// WRITELIST
	public void writeList(T e, PositionInterface p)
	{
		if(endList(p))
			throw new NullPointerException("Position is not valid. Cannot overwrite value.");
		
		if(p == firstList())
			firstList.link.element = e;
		else
			((PointerDouble) p).link.next.link.element = e;
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
		
		return ((PointerDouble) p).link.next == null;
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
		
		return ((PointerDouble) p).link.next;
	}

	
	// PREDECESSOR
	public PositionInterface predecessor(PositionInterface p)
	{
		if(p == firstList())
			throw new IndexOutOfBoundsException("Position is not valid.");
		
		if(isEmpty())
			throw new EmptyStructureException("Empty list.");
		
		return (PointerDouble) p;
	}

	
	// INSERT
	public void insert(T e, PositionInterface p)
	{
		PointerDouble newRecord = new PointerDouble(new CellDouble(e));
		PointerDouble temp;
		
		if(isEmpty())
			this.firstList = newRecord;
		else
		{
			if(p == firstList())
			{
				firstList.link.pred = newRecord;
				newRecord.link.next = firstList;
				firstList = newRecord;
			}
			else
			{
				temp = ((PointerDouble) p).link.next;
				((PointerDouble) p).link.next = newRecord;
				newRecord.link.next = temp;
				
				if(temp != null)
					temp.link.pred = newRecord;
				
				newRecord.link.pred = (PointerDouble) p;
			}
		}
		
		System.out.println("Element '" + e + "' successfully inserted.");
	}

	
	// REMOVE
	public void remove(PositionInterface p)
	{		
		if(isEmpty())
			throw new EmptyStructureException("Empty list. Cannot remove.");
	
		if(endList(p))
			throw new NullPointerException("Position is not valid. Cannot remove.");
		
		PointerDouble temp;
		PointerDouble toBeDeleted;
		
		if(p == firstList())
		{
			toBeDeleted = (PointerDouble) successor(firstList());
			firstList = firstList.link.next;
		}
		else
		{
			toBeDeleted = ((PointerDouble) p).link.next;
			
			temp = ((PointerDouble) p).link.next.link.next;
			
			((PointerDouble) p).link.next = temp;
			
			if(temp != null)
				temp.link.pred = ((PointerDouble) p);
		}
		
		System.out.print(("Element to be deleted: " + toBeDeleted.link.element));
		System.out.println(" ..done :)");
	}

	
	// PRINTALL
	public void printAll()
	{
		Iterator<T> itr = this.iterator();
		
		System.out.println("\t" + itr.next());
		
		while(itr.hasNext())
			System.out.println("\t" + itr.next());
	}
	
	
	// ITERATOR
	public Iterator<T> iterator()
	{
		return new DoublyLinkedListIterator(this, this.firstList);
	}
	
	
	// MAIN
	public static void main(String...args)
	{
		ListInterface<String> DLL = new DoublyLinkedList();
		
		DLL.insert("Nicola", DLL.firstList());
		DLL.insert("Alan", DLL.successor(DLL.firstList()));
		DLL.insert("George", DLL.successor(DLL.successor(DLL.firstList())));
		DLL.insert("Haytham", DLL.firstList());
		DLL.insert("Raz", DLL.firstList());
		DLL.insert("Logan", DLL.firstList());
		DLL.insert("Connor", DLL.successor(DLL.successor(DLL.firstList())));
		
		DLL.writeList("Mark", DLL.successor(DLL.successor(DLL.firstList())));
		
		System.out.println();
		
		// USING THE ITERATOR
		System.out.println("ITERATOR:");
		((DoublyLinkedList<String>) DLL).printAll();
		
		System.out.println();
		
		DLL.remove(DLL.firstList());
		DLL.remove(DLL.successor(DLL.firstList()));
		
		System.out.println();
		
		// USING THE ITERATOR AGAIN
		System.out.println("ITERATOR:");
		((DoublyLinkedList<String>) DLL).printAll();
	}
}
