// IMPLEMENTATION OF AN ITERATOR OVER A GRAPH USING A LIST OF EDGES AND A LIST OF NODES
//   Author: Nicola Lamonaca


package graph;

import java.util.Iterator;


public class EdgesListGraphIterator<T> implements Iterator<T>
{
	private Object[] array;
	private EdgesListGraph graph;
	private int existingEntities;
	private int currentEntityIndex;
	private GraphIterationEntities entity;
	
	
	// CONSTRUCTOR
	public EdgesListGraphIterator(EdgesListGraph graph, GraphIterationEntities entity)
	{
		this.graph = graph;
		this.currentEntityIndex = 0;
		this.entity = entity;
		
		if(entity == GraphIterationEntities.NODES)
		{
			System.out.println("NODES");
			array = graph.nodes;
			existingEntities = graph.existingNodes;
		}
		else if(entity == GraphIterationEntities.EDGES)
		{
			System.out.println("EDGES");
			array = graph.edges;
			existingEntities = graph.existingEdges;
		}
	}
	
	
	// HASNEXT
	public boolean hasNext()
	{
		return currentEntityIndex < array.length;
	}

	
	// NEXT
	public T next()
	{
		// EDGES
		if(entity == GraphIterationEntities.EDGES)
		{
			EdgesListEdge tempEdge = (EdgesListEdge) array[currentEntityIndex];
			
			// Manage potential holes in the array due to deletion of edges
			if(tempEdge == null)
			{
				currentEntityIndex++;
			}
			else
			{
				tempEdge = (EdgesListEdge) array[currentEntityIndex];
				currentEntityIndex++;
				
				return (T) tempEdge.info;
			}
		}
		
		// NODES
		else if(entity == GraphIterationEntities.NODES)
		{
			EdgesListNode tempNode = (EdgesListNode) array[currentEntityIndex];
			
			// Manage potential holes in the array due to deletion of nodes
			if(tempNode == null)
			{
				currentEntityIndex++;
			}
			else
			{
				tempNode = (EdgesListNode) array[currentEntityIndex];
				currentEntityIndex++;
				
				return (T) tempNode.info;
			}
		}
		
		return null;
	}

	
	// REMOVE
	public void remove()
	{
		throw new UnsupportedOperationException("Operation not supported.");
	}
}
