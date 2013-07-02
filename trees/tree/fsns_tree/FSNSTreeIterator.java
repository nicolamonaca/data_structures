// IMPLEMENTATION OF AN ITERATOR OVER A TREE USING A LINKED STRUCTURE WITH POINTERS TO THE FIRST SON AND THE NEXT SIBLING
// Author:   Nicola Lamonaca


package tree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class FSNSTreeIterator<T> implements Iterator<T>
{
	private FSNSTree tree;
	private List<FSNSNode> list;
	private int i;
	
	
	// CONSTRUCTOR
	public FSNSTreeIterator(FSNSTree tree, VisitType type)
	{
		this.tree = tree;
		this.list = new LinkedList<FSNSNode>();
		this.i = -1;
		
		if(!tree.emptyTree())
		{
			if(type == VisitType.PRE)
				previsit((FSNSNode) tree.root());
			else if(type == VisitType.IN)
				invisit((FSNSNode) tree.root());
			else if(type == VisitType.POST)
				postvisit((FSNSNode) tree.root());
		}
	}
	
	
	// HASNEXT
	public boolean hasNext()
	{
		return i < list.size() - 1;
	}
	

	// NEXT
	public T next()
	{
		i++;
		return (T) list.get(i).info;
	}

	
	// REMOVE
	public void remove()
	{
		throw new UnsupportedOperationException("Operation not supported.");
	}
	
	
	// INVISIT
	private void invisit(FSNSNode u)
	{
		if(tree.leaf(u))
			list.add(u);
		else
		{
			FSNSNode v = (FSNSNode) tree.firstSon(u);
			invisit(v);
			
			list.add(u);
			
			while(!tree.endSiblings(v))
			{
				v = (FSNSNode) tree.nextSibling(v);
				invisit(v);
			}
		}
	}
	
	
	// PREVISIT
	private void previsit(FSNSNode u)
	{
		list.add(u);
		
		if(!tree.leaf(u))
		{
			FSNSNode v = (FSNSNode) tree.firstSon(u);
			previsit(v);
			
			while(!tree.endSiblings(v))
			{
				v = (FSNSNode) tree.nextSibling(v);
				previsit(v);
			}
		}
	}
	
	
	// POSTVISIT
	private void postvisit(FSNSNode u)
	{
		if(!tree.leaf(u))
		{
			FSNSNode v = (FSNSNode) tree.firstSon(u);
			postvisit(v);
			
			while(!tree.endSiblings(v))
			{
				v = (FSNSNode) tree.nextSibling(v);
				postvisit(v);
			}
		}
		
		list.add(u);
	}
}
