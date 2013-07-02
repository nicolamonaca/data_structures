package graph;


public class EdgesListEdge<T> implements EdgeInterface
{
	public T info;
	public NodeInterface source, destination;
	
	
	public EdgesListEdge(NodeInterface x, NodeInterface y, T info)
	{
		this.source = x;
		this.destination = y;
		this.info = info;
	}
}
