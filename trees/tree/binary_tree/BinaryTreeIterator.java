// IMPLEMENTATION OF AN ITERATOR OVER A BINARY TREE USING A LINKED STRUCTURE WITH POINTERS TO THE LEFT SON AND THE RIGHT SON
// Author:   Nicola Lamonaca


package tree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import supportqueue.*;


public class BinaryTreeIterator<T> implements Iterator<T>
{
	private BinaryTree tree;
	private LinkedQueue<BinaryNode> queue;
	private Iterator itrQ;
	private List list;
	private Iterator itrL;
	private VisitType type;
	
	
	// CONSTRUCTOR
	public BinaryTreeIterator(BinaryTree tree, VisitType type)
	{
		this.tree = tree;
		this.queue = new LinkedQueue<BinaryNode>();
		this.list = new LinkedList();
		this.type = type;
		
		if(!tree.emptyTree())
		{
			if(type == VisitType.IN) // INVISIT
			{
				System.out.println("INVISITING..");
				invisit((BinaryNode) tree.root());
				this.itrQ = new LinkedQueueIterator(queue);
			}
			else if(type == VisitType.PRE) // PREVISIT
			{
				System.out.println("PREVISITING..");
				previsit((BinaryNode) tree.root());
				this.itrQ = new LinkedQueueIterator(queue);
			}
			else if(type == VisitType.POST) // POSTVISIT
			{
				System.out.println("POSTVISITING..");
				postvisit((BinaryNode) tree.root());
				this.itrQ = new LinkedQueueIterator(queue);
			}
			else // BFS VISIT
			{
				System.out.println("BREADTH FIRST SEARCH..");
				bfs((BinaryNode) tree.root());
				this.itrL = list.listIterator();
			}
		}
	}
	

	// HASNEXT
	public boolean hasNext()
	{
		if(this.type != VisitType.BFS)
			return itrQ.hasNext();
			
		return itrL.hasNext();
	}

	
	// NEXT
	public T next()
	{
		if(this.type != VisitType.BFS)
			return (T) itrQ.next();
			
		return (T) itrL.next();
	}

	
	// REMOVE
	public void remove()
	{
		throw new UnsupportedOperationException("Operation not supported.");
	}
	
	
	// INVISIT
	private void invisit(BinaryNode u)
	{
		if(!tree.isEmptyLeftSon(u))
			invisit((BinaryNode) tree.leftSon(u));
		
		queue.enqueue(u);
		
		if(!tree.isEmptyRightSon(u))
			invisit((BinaryNode) tree.rightSon(u));
	}
	
	
	// PREVISIT
	private void previsit(BinaryNode u)
	{
		queue.enqueue(u);
		
		if(!tree.isEmptyLeftSon(u))
			previsit((BinaryNode) tree.leftSon(u));
		
		if(!tree.isEmptyRightSon(u))
			previsit((BinaryNode) tree.rightSon(u));
	}
	
	
	// POSTVISIT
	private void postvisit(BinaryNode u)
	{
		if(!tree.isEmptyLeftSon(u))
			postvisit((BinaryNode) tree.leftSon(u));
		
		if(!tree.isEmptyRightSon(u))
			postvisit((BinaryNode) tree.rightSon(u));
		
		queue.enqueue(u);
	}
	
	
	// BREADTHFIRSTSEARCH
	private void bfs(BinaryNode u)
	{
		list = new LinkedList();
		queue.enqueue((BinaryNode) tree.root());
		
		while(!queue.isEmpty())
		{
			BinaryNode v = queue.dequeue();
			
			if(v != null)
			{
				list.add(v);
				queue.enqueue((BinaryNode) v.leftSon);
				queue.enqueue((BinaryNode) v.rightSon);
			}
		}
	}
}
