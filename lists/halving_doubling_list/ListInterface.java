package list;


public interface ListInterface<T>
{
	public boolean isEmpty();
	
	public T readList(PositionInterface p);
	
	public void writeList(T e, PositionInterface p);
	
	public PositionInterface firstList();
	
	public boolean endList(PositionInterface p);
	
	public PositionInterface successor(PositionInterface p);
	
	public PositionInterface predecessor(PositionInterface p);
	
	public void insert(T e, PositionInterface p);
	
	public void remove(PositionInterface p);
}
