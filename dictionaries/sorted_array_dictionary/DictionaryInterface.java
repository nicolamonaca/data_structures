package dictionary;


public interface DictionaryInterface<T> 
{
	public void insert(Comparable k, T e);
	
	public T search(Comparable k);
	
	public void delete(Comparable k);
}