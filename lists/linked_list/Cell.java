package list;


public class Cell<T>
{
	T element;
	Pointer next = null;
	
	
	public Cell(T e)
	{
		this.element = e;
	}
}