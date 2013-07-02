// IMPLEMENTATION OF A DICTIONARY USING A HALVING-DOUBLING ARRAY
//   Author: Nicola Lamonaca

package dictionary;

import java.util.Iterator;
import exceptions.*;


public class ArrayDoublingDictionary<T> implements DictionaryInterface<T>, Iterable<T>
{
	Couple<T>[] S = new Couple[1];
	int n = 0; // Registers the number of elements actually in the array 'S'
	
	// PRIVATE INNER CLASS
	class Couple<T>
	{
		Comparable key;
		T elem;
		
		// Private inner class
		public Couple(Comparable k, T e)
		{
			this.key = k;
			this.elem = e;
		}
		
		public String toString()
		{
			return this.key + ": " + this.elem;
		}
	}
	
	
	// INSERT
	public void insert(Comparable k, T e)
	{
		T obj = search(k);
		
		if(obj != null)
			throw new ExistingKeyException("An element with the same key already exists.");
		
		if(S.length == n)
		{
			Couple[] temp = new Couple[S.length * 2];
			
			for(int i = 0; i < n; i++)
				temp[i] = S[i];
			
			S = temp;
		}
		
		S[n] = new Couple(k, e);
		n++;
		
		System.out.println("Element " + k + " inserted.");
	}
	
	
	// SEARCH - Performs a linear search
	public T search(Comparable k)
	{	
		for(int i = 0; i < n; i++)
			if(k.compareTo(S[i].key) == 0)
				return S[i].elem;
		
		return null;
	}
	
	
	// DELETE
	public void delete(Comparable k)
	{
		int i;
		
		T obj = search(k);
		
		if(obj == null)
			throw new ElementNotFoundException("Element " + k + " not found. Can't delete.");
		
		// Finds the position of the element to be deleted
		for(i = 0; i < n; i++)
			if(k.compareTo(S[i].key) == 0)
				break;
		
		// Left-shifts all the elements to the right of 'S[i]'
		for(int j = i; j < n; j++)
			S[j] = S[j + 1];
		
		// Decreases the number of elements
		n--;
		
		// Checks if the array size can be reduced to 'S.length / 2'
		if(n > 1 && n <= S.length / 4) {
			Couple[] temp = new Couple[S.length / 2];
			
			// Copies all the elements from the old to the new array
			for(int j = 0; j < n; j++)
				temp[j] = S[j];
			
			// Updates the pointer of S to the new array
			S = temp;
		}
		
		System.out.println("Element " + k + " successfully deleted.");
	}
	
	
	// PRINTALL
	public void printAll()
	{
		Iterator itr = this.iterator();
		
		System.out.println(itr.next());
		
		while(itr.hasNext())
			System.out.println(itr.next());
	}
	
	
	// ITERATOR
	public Iterator<T> iterator()
	{
		return new ArrayDoublingDictionaryIterator(this);
	}
	
	
	// MAIN
	public static void main(String[] args)
	{
		DictionaryInterface AD = new ArrayDoublingDictionary();
		
		// INSERT SOME ELEMENTS
		AD.insert(1, "Nicola");
		AD.insert(8, "John");
		AD.insert(3, "Dan");
		AD.insert(5, "Maggie");
		AD.insert(4, "Rupert");
		AD.insert(6, "Cindy");
		AD.insert(7, "Connor");
		AD.insert(2, "Morris");
		AD.insert(9, "Margareth");
//		AD.insert(9, "Margareth"); // Gives 'ExistingKeyException: An element with the same key already exists.'
		
		System.out.println();
		
		// PRINTS ALL THE ELEMENTS
		for(int i = 0; i < ((ArrayDoublingDictionary)AD).n; i++)
			System.out.println(((ArrayDoublingDictionary)AD).S[i]);
		
		System.out.println();
		
		// DELETE SOME ELEMENTS
		AD.delete(6);
		AD.delete(4);
		AD.delete(3);
		AD.delete(2);
		AD.delete(5);
		AD.delete(1);
		AD.delete(7);
		AD.delete(9);
		AD.delete(8);
//		AD.delete(10); // Gives 'ElementNotFoundException: Element 2 not found. Can't delete.'
		
		System.out.println();
		
		// PRINTS ALL THE ELEMENTS AGAIN (IT PRINTS NOTHING)
		for(int i = 0; i < ((ArrayDoublingDictionary)AD).n; i++)
			System.out.println(((ArrayDoublingDictionary)AD).S[i]);
		
		System.out.println("----------------\nArray length: " + ((ArrayDoublingDictionary)AD).S.length + "\n");
		
		System.out.println("4: " + AD.search(4));
		
		System.out.println();
		
		AD.insert(6, "Nicola");
		AD.insert(2, "Cindy");
		AD.insert(1, "Randall");
		AD.insert(5, "Philip");
		
		System.out.println();
		
		// USING THE ITERATOR
		((ArrayDoublingDictionary) AD).printAll();
	}
}
