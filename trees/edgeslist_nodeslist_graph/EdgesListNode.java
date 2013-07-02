package graph;


public class EdgesListNode<T> implements NodeInterface
{
	public T info;
	public EdgesListGraph graph;
	
	
	public EdgesListNode(T info, EdgesListGraph graph)
	{
		this.info = info;
		this.graph = graph;
	}
}
