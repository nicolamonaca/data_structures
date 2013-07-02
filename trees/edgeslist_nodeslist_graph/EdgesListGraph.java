// IMPLEMENTATION OF A GRAPH USING A LIST OF EDGES AND A LIST OF NODES
//   Author: Nicola Lamonaca


package graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import exceptions.*;


public class EdgesListGraph<T> implements GraphInterface<T>, Iterable<T>
{
	EdgesListNode[] nodes = new EdgesListNode[1];
	EdgesListEdge[] edges = new EdgesListEdge[1];
	int existingNodes = 0;
	int existingEdges = 0;
	
	
	// CHECKNODE
	private boolean checkNode(NodeInterface v)
	{
		if(v == null)
			return true;
		
		if(((EdgesListNode) v).graph != this)
			return true;
		
		return false;
	}
	
	
	// CHECKEDGE
	private boolean checkEdge(EdgeInterface e)
	{
		if(e == null)
			return true;
		
		if(((EdgesListNode) ((EdgesListEdge) e).source).graph != this || ((EdgesListNode) ((EdgesListEdge) e).destination).graph != this)
			return true;
		
		return false;
	}
	
	
	// EMPTYGRAPH
	public boolean emptyGraph()
	{
		return nodes.length == 0 && edges.length == 0;
	}

	
	// NEWNODE
	public NodeInterface newNode(T info)
	{
		return new EdgesListNode(info, this);
	}

	
	// NEWEDGE
	public EdgeInterface newEdge(NodeInterface x, NodeInterface y, T info) throws InvalidNodeException, ExistingEdgeException
	{
		if(checkNode(x))
			throw new InvalidNodeException("Node " + ((EdgesListNode) x).info + " is not valid.");
		
		if(checkNode(y))
			throw new InvalidNodeException("Node " + ((EdgesListNode) y).info + " is not valid.");
		
		if(incidentEdge(x, y) != null)
			throw new ExistingEdgeException("Already existing edge.");
		
		EdgesListEdge newEdge = new EdgesListEdge(x, y, info);
		
		return newEdge;
	}
	

	// ADDNODE
	public void addNode(NodeInterface v) throws InvalidNodeException, ExistingNodeException
	{
		if(checkNode(v))
			throw new InvalidNodeException("Node is not valid.");
		
		EdgesListNode newNode = (EdgesListNode) v;
		
		for(int i = 0; i < existingNodes; i++)
			if(nodes[i] == newNode)
				throw new ExistingNodeException("Already existing node.");
		
		// Check if the array must be resized
		if(existingNodes == nodes.length)
		{
			EdgesListNode[] tempNodes = new EdgesListNode[nodes.length * 2];
			
			System.arraycopy(nodes, 0, tempNodes, 0, existingNodes);
			
			nodes = tempNodes;
		}
		
		nodes[existingNodes] = newNode;
		nodes[existingNodes].graph = this;
		
		existingNodes++;
	}
	

	// ADDEDGE
	public void addEdge(EdgeInterface e) throws ExistingEdgeException
	{			
		EdgesListEdge newEdge = (EdgesListEdge) e;
		
		if(incidentEdge(newEdge.source, newEdge.destination) != null)
			throw new ExistingEdgeException("Already existing edge.");
		
		// Check if the array must be resized
		if(existingEdges == edges.length)
		{
			EdgesListEdge[] tempEdges = new EdgesListEdge[edges.length * 2];
			
			System.arraycopy(edges, 0, tempEdges, 0, existingEdges);
			
			edges = tempEdges;
		}
		
		edges[existingEdges] = newEdge;
		existingEdges++;
	}
	

	// DELETENODE
	public void deleteNode(NodeInterface v) throws InvalidNodeException
	{
		if(checkNode(v))
			throw new InvalidNodeException("Node is not valid.");

		List<EdgesListEdge> list = new ArrayList();
		list = adjacentNodes(v);
		int oldExistingEdges = existingEdges;
		
		System.out.println("DELETING NODE '" + ((EdgesListNode) v).info + "' ..");
		System.out.println("   ALSO DELETING EDGES:");
		
		Iterator itr = list.iterator();
		
		while(itr.hasNext())
			System.out.println("   " + ((EdgesListEdge) itr.next()).info);
		
		System.out.println();
		
		// Find and delete all the edges involving 'v'
		for(int i = 0; i < edges.length; i++)
			if(edges[i] != null)
			{
				if(edges[i].source == v || edges[i].destination == v)
				{
					edges[i] = null;
					existingEdges--;
				}
			}
		
		// Find and delete the node 'v' in the array 'nodes'
		for(int i = 0; i < nodes.length; i++)
			if(nodes[i] == (EdgesListNode) v)
			{
				nodes[i] = null;
				existingNodes--;
			}
		
		// CHECK IF THE ARRAY 'nodes' MUST BE RESIZED
		if(existingNodes <= nodes.length / 4)
		{
			EdgesListNode[] tempNodes = new EdgesListNode[nodes.length / 2];
			int tempIndex = 0;
			
			for(int i = 0; i < nodes.length; i++)
			{
				if(nodes[i] != null) {
					tempNodes[tempIndex] = nodes[i];
					tempIndex++;
				}
			}	
		}
		
		// CHECK IF THE ARRAY 'edges' MUST BE RESIZED
		if(existingEdges <= edges.length / 4)
		{
			EdgesListEdge[] tempEdges = new EdgesListEdge[nodes.length / 2];
			int tempIndex = 0;
			
			for(int i = 0; i < edges.length; i++)
			{
				if(edges[i] != null) {
					tempEdges[tempIndex] = edges[i];
					tempIndex++;
				}
			}
			
			edges = tempEdges;
		}
	}
	

	// DELETEEDGE
	public void deleteEdge(EdgeInterface e) throws InvalidEdgeException
	{
		if(checkEdge(e))
			throw new InvalidEdgeException("Edge is not valid.");
		
		System.out.println("DELETING EDGE '" + ((EdgesListEdge) e).info + "' ..\n");
		
		for(int i = 0; i < edges.length; i++)
		{
			if(edges[i] != null)
			{
				if((edges[i].source == ((EdgesListEdge) e).source) && (edges[i].destination == ((EdgesListEdge) e).destination))
				{
					edges[i] = null;
					existingEdges--;
				}
			}
		}
		
		// CHECK IF THE ARRAY 'edges' MUST BE RESIZED
		if(existingEdges <= edges.length / 4)
		{
			EdgesListEdge[] tempEdges = new EdgesListEdge[nodes.length / 2];
			int tempIndex = 0;
			
			for(int i = 0; i < edges.length; i++)
			{
				if(edges[i] != null) {
					tempEdges[tempIndex] = edges[i];
					tempIndex++;
				}
			}
			
			edges = tempEdges;
		}
	}
	

	// NODEINFO
	public T nodeInfo(NodeInterface v) throws InvalidNodeException
	{
		if(checkNode(v))
			throw new InvalidNodeException("Node is not valid.");
		
		return (T) ((EdgesListNode) v).info;
	}

	
	// EDGEINFO
	public T edgeInfo(EdgeInterface e) throws InvalidEdgeException
	{
		if(checkEdge(e))
			throw new InvalidEdgeException("Edge is not valid.");
		
		return (T) ((EdgesListEdge) e).info;
	}
	

	// CHANGENODEINFO
	public void changeNodeInfo(NodeInterface v, T info) throws InvalidNodeException
	{
		if(checkNode(v))
			throw new InvalidNodeException("Node is not valid.");
		
		((EdgesListNode) v).info = info;
	}

	
	// CHANGEEDGEINFO
	public void changeEdgeInfo(EdgeInterface e, T info) throws InvalidEdgeException
	{
		if(checkEdge(e))
			throw new InvalidEdgeException("Edge is not valid.");
		
		((EdgesListEdge) e).info = info;
	}

	
	// ADJACENTNODES
	public List adjacentNodes(NodeInterface v)
	{
		if(checkNode(v))
			throw new InvalidNodeException("Node is not valid.");
		
		List adjacence = new ArrayList();
		
		for(int i = 0; i < edges.length; i++)
		{
			if(edges[i] != null)
			{
				if((edges[i].source == v) || (edges[i].destination == v))
					adjacence.add(edges[i]);
			}
		}
		
		return adjacence;
	}

	
	// INCIDENTEDGE
	public EdgeInterface incidentEdge(NodeInterface u, NodeInterface v) throws InvalidNodeException
	{
		if(checkNode(u))
			throw new InvalidNodeException("Node " + ((EdgesListNode) u).info + " is not valid.");
		
		if(checkNode(v))
			throw new InvalidNodeException("Node " + ((EdgesListNode) v).info + " is not valid.");
		
		for(int i = 0; i < existingEdges; i++)
			if(edges[i].source == u && edges[i].destination == v)
				return edges[i];
		
		return null;
	}


	// ITERATOR
	public Iterator<T> iterator()
	{
		return new EdgesListGraphIterator(this, GraphIterationEntities.EDGES);
	}
	
	
	// PRINTALL
	private void printAll()
	{
		Iterator<T> itr = this.iterator();
		
		while(itr.hasNext())
		{
			T next = itr.next();
			
			if(next != null)
				System.out.println(next);
		}
	}
	
	
	// MAIN
	public static void main(String...args)
	{
		// Create a new graph
		EdgesListGraph G1 = new EdgesListGraph();
		
		// Create some nodes
		NodeInterface A = G1.newNode("A");
		NodeInterface B = G1.newNode("B");
		NodeInterface C = G1.newNode("C");
		NodeInterface D = G1.newNode("D");
		NodeInterface E = G1.newNode("E");
		NodeInterface F = G1.newNode("F");
		
		// Add the nodes to 'G1'
		G1.addNode(A);
		G1.addNode(B);
		G1.addNode(C);
		G1.addNode(D);
		G1.addNode(E);
		G1.addNode(F);
		
		// Create some edges
		EdgeInterface AB = G1.newEdge(A, B, "A <--> B");
		EdgeInterface CD = G1.newEdge(C, D, "C <--> D");
		EdgeInterface EF = G1.newEdge(E, F, "E <--> F");
		EdgeInterface AC = G1.newEdge(A, C, "A <--> C");
		EdgeInterface DB = G1.newEdge(D, B, "D <--> B");
		EdgeInterface FC = G1.newEdge(F, C, "F <--> C");
		
		// Add The edges to 'G1'
		G1.addEdge(AB);
		G1.addEdge(CD);
		G1.addEdge(EF);
		G1.addEdge(AC);
		G1.addEdge(DB);
		G1.addEdge(FC);
		
		//     A -- B
		//     |    |
		//     C -- D
		//     |
		//     F -- E
		
		// USING THE ITERATOR
		System.out.print("ITERATING OVER: ");
		G1.printAll();
		
		System.out.println();
		
		// TESTING INCIDENTEDGE
		System.out.println("Edge incidenting 'A' and 'B' is '" + ((EdgesListEdge) G1.incidentEdge(A, B)).info + "'.");
		
		System.out.println();
		
		// TESTING ADJACENT NODES
		List listEF = new ArrayList();
		listEF = G1.adjacentNodes(C);
		
		System.out.println("Nodes adjacent to 'C' are");
		for(int i = 0; i < listEF.size(); i++)
			System.out.println(((EdgesListEdge) listEF.get(i)).info);
		
		System.out.println();
		
		// DELETE node 'C'
		//G1.deleteEdge(AB);
		//G1.deleteEdge(DB);
		//G1.deleteEdge(CD);
		G1.deleteEdge(EF);
		G1.deleteEdge(FC);
		//G1.deleteEdge(AC);
		
		//G1.deleteNode(A);
		//G1.deleteNode(B);
		G1.deleteNode(C);
		//G1.deleteNode(D);
		//G1.deleteNode(E);
		//G1.deleteNode(F);
		
		// USING THE ITERATOR AGAIN
		System.out.print("ITERATING OVER: ");
		G1.printAll();
	}
}
