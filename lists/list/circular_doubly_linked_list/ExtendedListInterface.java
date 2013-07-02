package list;


public interface ExtendedListInterface<T> extends ListInterface<T>
{
	public PositionInterface endList(ExtendedListInterface list);
	
	public int lenght(ExtendedListInterface list);
}
