package list;


public class Pointer implements PositionInterface
{
	public Cell link;
	
	
	public Pointer(Cell c)
	{
		this.link = c;
	}
	
	
	public String toString()
	{
		return this.link.element.toString();
	}
}