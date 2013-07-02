// IMPLEMENTATION OF A DICTIONARY USING AN ARRAY WITH OPEN ADRESSING
//   Author: Nicola Lamonaca

package dictionary;

import hash.*;
import java.util.Iterator;
import scan.*;
import exceptions.FullArrayException;


public class OpenHashTable<T> implements DictionaryInterface<T>, Iterable
{
	public Record[] S;
	private HashInterface hFun;
	private ScanInterface cFun;
	public static Object canc = new Object(); // A special value to mark a cell after the element has been deleted
	
	
	// CONSTRUCTOR
	public OpenHashTable(int n, HashInterface h, ScanInterface c)
	{
		this.S = new Record[n];
		this.hFun = h;
		this.cFun = c;
		
		System.out.println("New OpenHashTable[" + n + "] created.");
	}
	
	// INNER CLASS
	class Record<T>
	{
		private Comparable key;
		private T elem;
		
		public Record(Comparable k, T e)
		{
			this.key = k;
			this.elem = e;
		}
		
		public String toString()
		{	
			if(this.elem == canc)
				this.elem = (T) "";
			return this.key + ": " + this.elem;
		}
	}
	

	// INSERT
	public void insert(Comparable k, T e)
	{
		int hk = hFun.h(k, S.length);
		
		for(int i = 0; i < S.length; i++)
		{
			int pos = cFun.c(hk, i, S.length);
			
			if(S[pos] == null || S[pos].elem == canc)
			{
				S[pos] = new Record(k, e);
				System.out.println(S[pos] + " inserted.");
			
				return;
			}
		}
		
		throw new FullArrayException("No more positions available.");
	}


	// SEARCH
	public T search(Comparable k)
	{
		int hk = hFun.h(k, S.length);
		T elem = null;
		
		for(int i = 0; i < S.length; i++)
		{
			int pos = cFun.c(hk, i, S.length);
			
			if(S[pos] != null || S[pos].elem == canc)
				return (T) S[pos].elem;
		}
		
		return null;
	}


	// DELETE
	public void delete(Comparable k)
	{
		int hk = hFun.h(k, S.length);
		
		for(int i = 0; i < S.length; i++)
		{
			int pos = cFun.c(hk, i, S.length);
			
			if(S[pos] == null)
				break;
			
			if(k.compareTo(S[pos].key) == 0 && S[pos].elem != canc)
			{
				S[pos].elem = canc;
				System.out.println("Element '" + k + "' successfully deleted.");
				
				return;
			}
		}
	}
	
	
	// PRINTALL
	private void printAll()
	{
		Iterator itr = this.iterator();

		while(itr.hasNext())
			System.out.println(itr.next());
	}
	
	
	// ITERATOR
	public Iterator iterator()
	{
		return new OpenHashTableIterator(this.S);
	}
	
	
	// MAIN
	public static void main(String...args)
	{
		DictionaryInterface OHT = new OpenHashTable<String>(5, new HashDivision(), new LinearScan());
		
		OHT.insert(1, "Nicola");
		OHT.insert(2, "Mary");
		OHT.insert(3, "Pierre");
		OHT.insert(4, "Tony");
		OHT.insert(5, "Logan");
		
		System.out.println();
		
		System.out.println("5: " + OHT.search(5));
		OHT.delete(5);
		System.out.println("5: " + OHT.search(5));
		
		System.out.println();
		
		// PRINTS EVERYTHING USING THE ITERATOR
		System.out.println("USING THE ITERATOR:");
		((OpenHashTable<String>) OHT).printAll();
	}
}
