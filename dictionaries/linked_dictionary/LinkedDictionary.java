// IMPLEMENTATION OF A DICTIONARY USING A DOUBLY LINKED LIST
//   Author: Nicola Lamonaca

package dictionary;

import java.util.Iterator;
import exceptions.ElementNotFoundException;
import exceptions.EmptyStructureException;


public class LinkedDictionary<T> implements DictionaryInterface<T>, Iterable<T>
{
	Record list = null;
	
	
	// INNER CLASS
	final class Record
	{
		Comparable key;
		T elem;
		Record prev;
		Record next;
		
		// INNER CLASS' CONSTRUCTOR
		public Record(Comparable k, T e)
		{
			this.key = k;
			this.elem = e;
			this.next = this.prev = null;
		}
		
		// INNER CLASS TOSTRING
		public String toString()
		{
			return this.key + ": " + this.elem;
		}
	}


	// INSERT
	public void insert(Comparable k, T e)
	{
		Record p = new Record(k, e);
		
		if(list == null)
			list = p.prev = p.next = p;
		else
		{
			p.next = list.next;
			list.next.prev = p;
			list.next = p;
			p.prev = list;
		}
		
		System.out.println("Element '" + k + ": " + e + "' successfully inserted.");
	}


	// SEARCH - Returns the elem field of the record
	public T search(Comparable k)
	{
		if(k == null)
			return null;
		
		Record obj = (Record) getElement(k);

		if(obj != null)
			return obj.elem;
		else
			return null;
	}
	
	
	// GETELEMENT - Returns the entire record
	Record getElement(Comparable k)
	{
		if(list == null)
			return null;
		
		for(Record p = list; ; p = p.next)
		{
			if(k.equals(p.key))
				return p;
			
			if(p == list.prev)
				return null;
		}
	}


	// DELETE
	public void delete(Comparable k)
	{
		// If the dictionary is empty
		if(list == null)
			throw new EmptyStructureException("Empty structure. Nothing to delete."); // Do nothing
		
		Record obj = getElement(k);
		
		// If the element is not found
		if(obj == null)
			throw new ElementNotFoundException("Element not found. Nothing to delete."); // Do nothing
		
		// If the element is found and it's the only one in the sequence
		else if(list == list.next)
			list = null; // Set the first element to null
		
		// If the element is found and it's the first in the sequence
		else if(obj == list)
		{
			list.prev.next = list.next;	
			list.next.prev = list.prev;
			list = list.next;
			
			// DEBUG
			// System.out.println("HERE. k = " + k);
			// System.out.println("list.prev.next: " + list.prev.next);
			// System.out.println("list.next.prev: " + list.next.prev);
			// System.out.println("list: " + list);
		}
		
		else
		{
			obj.prev.next = obj.next;
			obj.next.prev = obj.prev;
		}
		
		System.out.println("Element '" + k + "' successfully deleted.");
	}
	
	
	// PRINTALL
	public void printAll()
	{
		Iterator itr = this.iterator();
		
		T element = (T)itr.next();
		System.out.println("\t" + element);
		
		while(itr.hasNext()) {
			element = (T)itr.next();
			
			System.out.println("\t" + element);
	      }
	}


	// ITERATOR
	public Iterator<T> iterator()
	{
		return new LinkedDictionaryIterator(this);
	}


	// MAIN
	public static void main(String[] args)
	{
		LinkedDictionary SC = new LinkedDictionary();
		
		SC.insert(1, "Nicola");
		SC.insert(2, "John");
		SC.insert(3, "Carl");
		SC.insert(4, "Morena");
		// (FINAL ORDER IS: 1-4-3-2)
		
		System.out.println();
		
		// SHOW ALL
		System.out.println("1: " + SC.search(1));
		System.out.println("2: " + SC.search(2));
		System.out.println("3: " + SC.search(3));
		System.out.println("4: " + SC.search(4));
		
		System.out.println();
		
		// DELETE ALL
		SC.delete(2);
		SC.delete(1);
		SC.delete(4);
		SC.delete(3);
		
		System.out.println();
		
		// SHOW ALL AGAIN
		System.out.println("1: " + SC.search(1));
		System.out.println("2: " + SC.search(2));
		System.out.println("3: " + SC.search(3));
		System.out.println("4: " + SC.search(4));
		// (PRINTS nulls)
		
		System.out.println();
		
		// INSERT ONE RECORD AGAIN
		SC.insert(4, "Bike");
		SC.insert(3, "Giraffe");
		SC.insert(6, "Pirate");
		SC.insert(9, "Apple");
		
		System.out.println();
		
		// PRINT THE NEWLY INSERTED RECORD
		System.out.println("4: " + SC.search(4));
		
		// SEARCH FOR A NON-EXISTENT RECORD
		System.out.println("7: " + SC.search(7));
		
		System.out.println();
		
		// USE THE ITERATOR
		System.out.println("ITERATOR:");
		SC.printAll();
	}
}