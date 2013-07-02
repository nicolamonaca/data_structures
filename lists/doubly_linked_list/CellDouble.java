package list;


public class CellDouble<T>
{
	T element;
	PointerDouble next;
	PointerDouble pred;
	
	
	public CellDouble()
	{
		this.next = this.pred = null;
	}
	
	
	public CellDouble(T e)
	{
		this.element = e;
		this.next = this.pred = null;
	}
}
