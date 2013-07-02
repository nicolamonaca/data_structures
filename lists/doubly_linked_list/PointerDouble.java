package list;


public class PointerDouble implements PositionInterface
{
	public CellDouble link;
	
	
	public PointerDouble(CellDouble c)
	{
		this.link = c;
	}
}
