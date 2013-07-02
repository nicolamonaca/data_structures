// IMPLEMENTATION OF A DICTIONARY USING A HASHTABLE WITH COLLISIONS LISTS
//   Author: Nicola Lamonaca

package dictionary;

import hash.*;
import java.util.Iterator;


public class HashTableWithCollisionsLists<T> implements DictionaryInterface<T>, Iterable
{
	public LinkedDictionary[] S;
	private HashInterface fHash;
	
	
	// CONSTRUCTOR
	public HashTableWithCollisionsLists(HashInterface hash, int n)
	{
		this.fHash = hash;
		S = new LinkedDictionary[n];
	}
			
			
	// INSERT
	public void insert(Comparable k, T e)
	{
		int pos = fHash.h(k, S.length);
		
		if(S[pos] == null)
			S[pos] = new LinkedDictionary<T>();
		
		S[pos].insert(k, e);
	}


	// SEARCH
	public T search(Comparable k)
	{
		int pos = fHash.h(k, S.length);
		
		return null;
	}


	// DELETE
	public void delete(Comparable k)
	{
		int pos = fHash.h(k, S.length);
		
		if(S[pos] == null)
			return;
		
		S[pos].delete(k);
	}


	// ITERATOR
	public Iterator iterator()
	{
		return new HashTableWithCollisionsListsIterator(this);
	}
	
	
	// PRINTALL
	private void printAll()
	{
		Iterator itr = iterator();
		
		System.out.println(itr.next());
		
		while(itr.hasNext())
			System.out.println(itr.next());
	}
	
	
	// MAIN
	public static void main(String...args)
	{
		DictionaryInterface CL = new HashTableWithCollisionsLists<String>(new HashDivision(), 5);
		
		CL.insert(1, "Nicola");
		CL.insert(2, "Matt");
		CL.insert(2, "John");
		CL.insert(4, "Franz");
		CL.insert(2, "Michael");
		CL.insert(3, "Francis");
		CL.insert(2, "George");
		CL.insert(4, "Mark");
		CL.insert(3, "Cody");
		CL.insert(5, "Ryan");
		CL.insert(5, "Zack");
		CL.insert(4, "Logan");
		
		System.out.println();
		
		// USING THE ITERATOR
		((HashTableWithCollisionsLists) CL).printAll();
	}
}
