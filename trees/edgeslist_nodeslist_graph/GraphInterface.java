package graph;

import java.util.List;


public interface GraphInterface<T>
{
	public boolean emptyGraph();
	
	public NodeInterface newNode(T info);
	
	public EdgeInterface newEdge(NodeInterface x, NodeInterface y, T info);
	
	public void addNode(NodeInterface v);
	
	public void addEdge(EdgeInterface e);
	
	public void deleteNode(NodeInterface v);
	
	public void deleteEdge(EdgeInterface e);
	
	public T nodeInfo(NodeInterface v);
	
	public T edgeInfo(EdgeInterface e);
	
	public void changeNodeInfo(NodeInterface v, T info);
	
	public void changeEdgeInfo(EdgeInterface e, T info);
	
	public List adjacentNodes(NodeInterface v);
	
	public EdgeInterface incidentEdge(NodeInterface u, NodeInterface v);
}
