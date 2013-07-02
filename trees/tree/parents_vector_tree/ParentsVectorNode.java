package tree;


public class ParentsVectorNode<T> implements NodeInterface<T>
{
	public T info;
	public int index;
	public TreeInterface tree;
	
	
	public ParentsVectorNode(T info)
	{
		this.info = info;
	}
}
