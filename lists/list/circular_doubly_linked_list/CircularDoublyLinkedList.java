// IMPLEMENTATION OF A CIRCULAR DOUBLY LINKED LIST
//   Author: Nicola Lamonaca


package list;

import java.util.Iterator;
import exceptions.EmptyStructureException;


public class CircularDoublyLinkedList<T> implements ExtendedListInterface<T>, Iterable<T>
{
	private PointerDouble firstList = null;
	private int n;
	private PointerDouble dummy;
	
	
	// CONSTRUCTOR
	public CircularDoublyLinkedList()
	{
		dummy = new PointerDouble(new CellDouble(null));
		firstList = dummy;
	}
	
	
	// GETDUMMY
	PositionInterface getDummy()
	{
		return this.dummy;
	}
	
	
	// ISEMPTY
	public boolean isEmpty()
	{  
		return this.dummy.link.next == null;
	}
	
	
	// FIRSTLIST
	public PositionInterface firstList()
	{
		return this.dummy;
	}

	
	// READLIST
	public T readList(PositionInterface p)
	{
		if(isEmpty())
			throw new EmptyStructureException("Empty list.");
		
		if(p == firstList())
			return (T) this.dummy.link.next.link.element;
		
		return (T) ((PointerDouble) p).link.next.link.element;
	}

	
	// WRITELIST
	public void writeList(T e, PositionInterface p)
	{
		if(endList(p))
			throw new NullPointerException("Position is not valid. Cannot overwrite value.");
		
		if(p == firstList())
			dummy.link.next.link.element = e;
		else
			((PointerDouble) p).link.next.link.element = e;
	}

	
	// ENDLIST
	public boolean endList(PositionInterface p)
	{
		if(isEmpty())
			return true;
		
		if(p == firstList())
			return false;
		
		return ((PointerDouble) p).link.next == dummy;
	}
	
	
	// ENDLIST
	public PositionInterface endList(ExtendedListInterface list)
	{
		return this.dummy.link.pred;
	}


	// LENGTH
	public int lenght(ExtendedListInterface list)
	{
		return this.n;
	}

	
	// SUCCESSOR
	public PositionInterface successor(PositionInterface p)
	{	
		if(isEmpty())
			throw new EmptyStructureException("Empty list.");
		
		if(endList(p))
			return dummy;
		
		return ((PointerDouble) p).link.next;
	}

	
	// PREDECESSOR
	public PositionInterface predecessor(PositionInterface p)
	{	
		if(isEmpty())
			throw new EmptyStructureException("Empty list.");
		
		if(p == firstList())
			return dummy.link.pred;
		
		return (PointerDouble) p;
	}

	
	// INSERT
	public void insert(T e, PositionInterface p)
	{
		PointerDouble newRecord = new PointerDouble(new CellDouble(e));
		PointerDouble temp;
		
		if(isEmpty())
		{
			this.dummy.link.next = this.dummy.link.pred = newRecord;
			newRecord.link.next = newRecord.link.pred = this.dummy;
		}
		else
		{
			temp = ((PointerDouble) p).link.next;
				
			newRecord.link.next = temp;
			temp.link.pred = newRecord;
			((PointerDouble) p).link.next = newRecord;
			newRecord.link.pred = (PointerDouble) p;
		}
		
		n++;
		
		System.out.println("Element '" + e + "' successfully inserted.");
	}

	
	// REMOVE
	public void remove(PositionInterface p)
	{
		if(isEmpty())
			throw new EmptyStructureException("Empty list. Cannot remove.");
		if(endList(p))
			throw new NullPointerException("Position is not valid. Cannot remove.");
		
		PointerDouble toBeDeleted = ((PointerDouble) p).link.next;
		System.out.print(("Element to be deleted: " + toBeDeleted.link.element));
		
		PointerDouble predP = (PointerDouble) p;
		PointerDouble succP = ((PointerDouble) p).link.next.link.next;
		
		if(p == null) // In other words: if predP == dummy
			dummy.link.next = succP;
		else
			predP.link.next = succP; // Without the check, we'd get a 'NullPointerException' if 'p == firstList' (if 'p' refers to the dummy node)
		
		succP.link.pred = predP;
		
		n--;
		
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
		return new CircularDoublyLinkedListIterator(this);
	}
	
	
	// MAIN
	public static void main(String...args)
	{
		ListInterface<String> DLL = new CircularDoublyLinkedList();
		
		DLL.insert("Nicola", DLL.firstList());
		DLL.insert("Alan", DLL.successor(DLL.firstList()));
		DLL.insert("George", DLL.successor(DLL.successor(DLL.firstList())));
		DLL.insert("Haytham", DLL.firstList());
		DLL.insert("Raz", DLL.firstList());
		DLL.insert("Logan", DLL.firstList());
		DLL.insert("Connor", DLL.successor(DLL.firstList()));
		
		DLL.writeList("Mark", DLL.successor(DLL.firstList()));
		
		System.out.println();
		
		// USING THE ITERATOR
		System.out.println("ITERATOR:");
		((CircularDoublyLinkedList<String>) DLL).printAll();
		
		System.out.println();
		
		DLL.remove(DLL.firstList());
		DLL.remove(DLL.successor(DLL.successor((DLL.successor(DLL.firstList())))));
		
		System.out.println();
		
		// USING THE ITERATOR AGAIN
		System.out.println("ITERATOR:");
		((CircularDoublyLinkedList<String>) DLL).printAll();
	}
}
