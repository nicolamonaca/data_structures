// IMPLEMENTATION OF AN ITERATOR OVER A DICTIONARY USING A HASHTABLE WITH COLLISIONS LISTS
//   Author: Nicola Lamonaca

package dictionary;

import java.util.Hashtable;
import java.util.Iterator;
import dictionary.LinkedDictionary.Record;
import exceptions.IndexNotFoundException;


public class HashTableWithCollisionsListsIterator<T> implements Iterator
{
	private HashTableWithCollisionsLists hashTable;
	private static Record firstRecord;
	private static Record currentRecord;
	private static int lastValidIndex = -1;
	
	
	// CONSTRUCTOR
	public HashTableWithCollisionsListsIterator(HashTableWithCollisionsLists hashTable)
	{
		this.hashTable = hashTable;
		this.firstRecord = this.currentRecord = hashTable.S[findNextValidIndex()].list;
	}
	
	
	// FINDNEXTVALIDINDEX - We cannot simply linearly scan the array, as some cells may still be unallocated
	private int findNextValidIndex()
	{
		for(int i = lastValidIndex + 1; i < hashTable.S.length; i++)
		{
			if(hashTable.S[i] != null)
			{
				lastValidIndex = i;
				return i;
			}
		}
		
		return -1;
		
		//throw new IndexNotFoundException("No valid indices found.");
	}
	
	
	// HASNEXT
	public boolean hasNext()
	{	
		// Check if there are cells still available in the array
		return currentRecord != null;
	}
	
	
	// NEXT
	public Record next()
	{
		// This divides up the various lists
		if(currentRecord == firstRecord)
			System.out.println("----------");
		
		// Go through the list
		Record temp = currentRecord;
		
		currentRecord = currentRecord.next;
		
		// If the last element in the list was reached..
		if((currentRecord == firstRecord)) {
				// ..Move to the next list in the array
				this.firstRecord = this.currentRecord = hashTable.S[findNextValidIndex()].list;
		}
		
		return temp;
	}
	
	
	// REMOVE
	public void remove()
	{
		throw new UnsupportedOperationException("Operation not supported");
	}
}