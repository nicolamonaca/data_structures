// IMPLEMENTATION OF A DICTIONARY USING A SORTED ARRAY
//   Author: Nicola Lamonaca

package dictionary;

import java.util.Iterator;
import exceptions.*;


public class SortedArrayDictionary<T> implements DictionaryInterface<T>, Iterable<T>
{
	Couple<T> S[];
	
	
	// CONSTRUCTOR
	public SortedArrayDictionary()
	{
		this.S  = new Couple[0];
	}
	
	
	// INNER CLASS
	class Couple<T>
	{
		private Comparable key;
		private T elem;
		
		public String toString()
		{
			return this.key + ": " + this.elem;
		}
		
		Couple(Comparable k, T e)
		{
			this.key = k;
			this.elem = e;
		}
	}

	
	// INSERT
	public void insert(Comparable k, T e)
	{
		T obj = this.search(k);
		
		if(obj != null)
			throw new ExistingKeyException("An element with the same key already exists.");
		
		
		int i, j; // Used in cycles
		Couple[] temp = new Couple[S.length + 1]; // Support array
	
		// Copy all the elements from 'S' into 'temp'
		for(i = 0; i < S.length; i++)
			temp[i] = S[i];
		
		S = temp; // Sets the reference to 'S' to the memory area allocated for 'temp'

		// Finds 'i', the position where '(k, e)' is to be inserted
		for(i = 0; i < S.length - 1; i++)
		{
			if(k.compareTo(S[i].key) <= 0)
				break;
		}
	
		for(j = S.length - 1; j > i; j--)
			S[j] = S[j - 1]; // Right-shifts all the elements to the right of 'i'
	
		S[i] = new Couple(k, e); // Puts '(k, e)' into 'S[i]'
		
		System.out.println("Element " + k + " inserted.");
	}

	
	// DELETE
	public void delete(Comparable k)
	{
		int i, j;
		
		if(S.length == 1)
		{
			if(k.compareTo(S[0].key) == 0)
				S = new Couple[0];
		}
		else if(S.length > 1)
		{	
			T elem = this.search(k);
			
			if(elem != null)
			{
				Couple[] temp = new Couple[S.length - 1];
				
				// Finds the position 'i' of the item to be deleted
				for(i = 0; i < S.length - 1; i++)
				{
					if(k.compareTo(S[i].key) == 0)
						break;
				}
				
				// Copies the first items which index is strictly less than 'i' from 'S' into 'temp'
				for(int h = 0; h < i; h++)
				{
					temp[h] = S[h];
				}
				
				// Left-shifts all the remaining items
				for(j = i; j < S.length - 1; j++)
				{
					temp[j] = S[j + 1];
				}
				
				S = temp; // Sets the reference to 'S' to the memory area allocated for 'temp'
				
				System.out.println("Element " + k + " deleted.");
			}
			else
				throw new ElementNotFoundException("Element " + k + " not found. Can't delete.");
		}
	}
	
	
	// SEARCH - Performs a binary search
	public T search(Comparable k)
	{
		int i = 0;
		int j = S.length;
		int m = 0;
			
		while(i < j)
		{
			m = (i + j) / 2;
				
			if(k.compareTo(S[m].key) == 0)
				return S[m].elem;
			else if(k.compareTo(S[m].key) < 0)
				j = m;
			else
				i = m + 1;
		}
			
		return null;
		
	}
	
	
	// PRINTALL
	private void printAll()
	{
		Iterator<T> itr = iterator();
		
		System.out.println(itr.next());
		
		while(itr.hasNext())
			System.out.println(itr.next());
	}
	
	
	// ITERATOR
	public Iterator<T> iterator()
	{
		return new SortedArrayDictionaryIterator(this);
	}

	
	// MAIN
	public static void main(String args[])
	{
		DictionaryInterface DA = new SortedArrayDictionary();
		
		DA.insert(1, "Mark");
		DA.insert(2, "Tony");
		DA.insert(3, "Abraham");
//		DA.insert(3, "Abraham"); // Gives 'ExistingKeyException: An element with the same key already exists.'
		DA.insert(4, "Nick");
		DA.insert(5, "Margot");
		DA.insert(6, "Paul");
		
		System.out.println();
		
		System.out.println("1: " + DA.search(1));
		System.out.println("2: " + DA.search(2));
		System.out.println("3: " + DA.search(3));
		System.out.println("4: " + DA.search(4));
		System.out.println("5: " + DA.search(5));
		System.out.println("6: " + DA.search(6));
		
		System.out.println();
		
		DA.delete(5);
		DA.delete(3);
		DA.delete(2);
//		DA.delete(2); // Gives 'ElementNotFoundException: Element 2 not found. Can't delete.'
		DA.delete(4);
		DA.delete(1);
//		DA.delete(1); // Gives 'EmptyStructureException: Empty structure.'
		
		System.out.println();
		
		System.out.println("1: " + DA.search(1));
		System.out.println("2: " + DA.search(2));
		System.out.println("3: " + DA.search(3));
		System.out.println("4: " + DA.search(4));
		System.out.println("5: " + DA.search(5));
		System.out.println("6: " + DA.search(6));
		
		System.out.println();
		
		DA.insert(3, "Philip");
		DA.insert(1, "Mark");
		DA.insert(2, "Tony");
		
		System.out.println();
		
		// USING THE ITERATOR
		((SortedArrayDictionary) DA).printAll();
	}
}
