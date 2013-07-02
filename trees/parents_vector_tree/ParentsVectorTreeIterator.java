// IMPLEMENTATION OF AN ITERATOR OF A TREE USING A PARENTS ARRAY
//   Author: Nicola Lamonaca


package tree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class ParentsVectorTreeIterator<T> implements Iterator<T> 
{
	private ParentsVectorTree<T> tree;
	private List<NodeInterface> list = new LinkedList<NodeInterface>();
	private int i = -1;
	
	
	// CONSTRUCTOR
	public ParentsVectorTreeIterator(ParentsVectorTree t, VisitType type)
	{
		this.tree = t;
		
		if(!tree.emptyTree())
		{
			if(type == VisitType.IN)
				invisit((ParentsVectorNode) tree.root());
			else if(type == VisitType.PRE)
				previsit((ParentsVectorNode) tree.root());
			else if(type == VisitType.POST)
				postvisit((ParentsVectorNode) tree.root());
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
		return (T) ((ParentsVectorNode) list.get(i)).info; 
	}

	
	// REMOVE
	public void remove()
	{
		throw new UnsupportedOperationException("Operation not supported.");
	}
	
	
	// INVISIT
	private void invisit(ParentsVectorNode u)
	{
		if(tree.leaf(u))
			list.add(u);
		else
		{
			ParentsVectorNode v = (ParentsVectorNode) tree.firstSon(u);
			invisit(v);
			
			list.add(u);
			
			while(!tree.endSiblings(v))
			{
				v = (ParentsVectorNode) tree.nextSibling(v);
				invisit(v);
			}
		}
	}
	
	
	// PREVISIT
	private void previsit(ParentsVectorNode u)
	{
		list.add(u);
		
		if(!tree.leaf(u))
		{
			ParentsVectorNode v = (ParentsVectorNode) tree.firstSon(u);
			previsit(v);
			
			while(!tree.endSiblings(v))
			{
				v = (ParentsVectorNode) tree.nextSibling(v);
				previsit(v);
			}
		}
	}
	
	
	// POSTVISIT
	private void postvisit(ParentsVectorNode u)
	{
		if(!tree.leaf(u))
		{
			ParentsVectorNode v = (ParentsVectorNode) tree.firstSon(u);
			
			postvisit(v);
			
			while(!tree.endSiblings(v))
			{
				v = (ParentsVectorNode) tree.nextSibling(v);
				postvisit(v);
			}
		}
		
		list.add(u);
	}
}