// IMPLEMENTATION OF A TREE USING A LINKED STRUCTURE WITH POINTERS TO THE FIRST SON AND THE NEXT SIBLING
// Author:   Nicola Lamonaca


package tree;

import java.util.Iterator;
import exceptions.*;


public class FSNSTree<T> implements TreeInterface<T>, Iterable<T>
{
	private FSNSNode root = null;

	
	// CHECKNODE
	private boolean checkNode(NodeInterface v)
	{
		if(v == null) 
			return true;
		
		if(((FSNSNode) v).tree != this)
			return true;
			
		return false;
	}
	
	
	// EMPTY
	public boolean emptyTree()
	{
		return root == null;
	}
	

	// INSERTROOT
	public void insertRoot(T info)
	{
		NodeInterface root = new FSNSNode(info);
		
		((FSNSNode) root).tree = this;
		
		this.root = (FSNSNode) root;
	}
	
	
	// ROOT
	public NodeInterface root()
	{
		return (FSNSNode) this.root;
	}
	

	// PARENT
	public NodeInterface parent(NodeInterface v) throws InvalidNodeException
	{
		if(checkNode(v))
			throw new InvalidNodeException("Node is not valid.");
		
		if(v == root())
			return null;
		
		return ((FSNSNode) v).parent;
	}

	
	// LEAF
	public boolean leaf(NodeInterface v) throws InvalidNodeException
	{
		if(checkNode(v))
			throw new InvalidNodeException("Node is not valid.");
		
		return ((FSNSNode) v).firstSon == null;
	}

	
	// FIRSTSON
	public NodeInterface firstSon(NodeInterface v) throws InvalidNodeException
	{
		if(checkNode(v))
			throw new InvalidNodeException("Node is not valid.");
		
		return ((FSNSNode) v).firstSon;
	}
	

	// ENDSIBLINGS
	public boolean endSiblings(NodeInterface v) throws InvalidNodeException
	{
		if(checkNode(v))
			throw new InvalidNodeException("Node is not valid.");
		
		return ((FSNSNode) v).nextSibling == null;
	}
	

	// NEXTSIBLING
	public NodeInterface nextSibling(NodeInterface v) throws InvalidNodeException
	{
		if(checkNode(v))
			throw new InvalidNodeException("Node is not valid.");
		
		return ((FSNSNode) v).nextSibling;
	}

	
	// INSERTFIRSTSUBTREE
	public void insertFirstSubtree(NodeInterface v, TreeInterface t) throws InvalidNodeException
	{
		if(checkNode(v))
			throw new InvalidNodeException("Node is not valid.");
		
		// If 't' is an empty tree it's OK: do nothing
		if(t.emptyTree())
			return;
		
		// Set the next sibling of 't.root()'
		((FSNSNode) t.root()).nextSibling = ((FSNSNode) v).firstSon;
		
		// Set the first son of 'v'
		((FSNSNode) v).firstSon = (FSNSNode) t.root();
		
		// Set the parent of 't.root()'
		((FSNSNode)  t.root()).parent = (FSNSNode) v;
		
		// Reset the old tree's root
		((FSNSTree) t).root = null;
		
		// Reset the reference to 'tree' of each node in 't'
		updateTreeID(((FSNSNode) v).firstSon);
	}


	// INSERTSUBTREE
	public void insertSubtree(NodeInterface v, TreeInterface t) throws InvalidNodeException
	{
		if(checkNode(v))
			throw new InvalidNodeException("Node is not valid.");
		
		// If 't' is an empty tree it's OK: do nothing
		if(t.emptyTree())
			return;
		
		// Save the old nextSibling of 'v'
		FSNSNode nextS = ((FSNSNode) v).nextSibling;
		
		// Set the next sibling of 't.root()' to the old nextSibling of 'v'
		((FSNSNode) t.root()).nextSibling = nextS;
		
		// Set the next sibling of 'v' to the root of 't'
		((FSNSNode) v).nextSibling = (FSNSNode) t.root();
		
		// Set the parent of 't.root()' to the same parent of 'v' (= null if 'v' is the root of 'this')
		((FSNSNode)  t.root()).parent = (FSNSNode) parent(v);
		
		// Reset the old tree's root
		((FSNSTree) t).root = null;
		
		// Reset the reference to 'tree' of each node in 't'
		updateTreeID(((FSNSNode) v).nextSibling);
	}
	
	
	// UPDATETREEID
	private void updateTreeID(NodeInterface v)
	{
		((FSNSNode) v).tree = this;
		
		if(!leaf(v))
		{
			FSNSNode u = (FSNSNode) firstSon(v);
			
			while(!endSiblings(u))
			{
				updateTreeID(u);
				u = (FSNSNode) nextSibling(u);
			}
			
			updateTreeID(u);
		}
	}
	

	// DELETESUBTREE
	public void deleteSubTree(NodeInterface v) throws InvalidNodeException
	{
		if(checkNode(v))
			throw new InvalidNodeException("Node is not valid.");
		
		if(v == root())
		{
			this.root = null;
			return;
		}
		
		FSNSNode u = (FSNSNode) parent(v);
		
		// Update the first son
		if(u.firstSon == v)
			u.firstSon = u.firstSon.nextSibling;
		else
		{
			FSNSNode temp = u.firstSon;
			boolean nodeFound = true;
			
			for(; temp.nextSibling != null; temp = temp.nextSibling)
				if(temp.nextSibling == v)
				{
					nodeFound = true;
					break;
				}
			
			if(nodeFound)
				temp.nextSibling = temp.nextSibling.nextSibling;
		}
		
		((FSNSNode) v).nextSibling = null;
		((FSNSNode) v).parent = null;
	}
	

	// INFO
	public T info(NodeInterface v) throws InvalidNodeException
	{
		if(checkNode(v))
			throw new InvalidNodeException("Node is not valid.");
		
		return (T) ((FSNSNode) v).info;
	}

	
	// CHANGEINFO
	public void changeInfo(NodeInterface v, T newInfo) throws InvalidNodeException
	{
		if(checkNode(v))
			throw new InvalidNodeException("Node is not valid.");
		
		((FSNSNode) v).info = newInfo;
	}


	// ITERATOR
	public Iterator<T> iterator(VisitType type)
	{
		return new FSNSTreeIterator(this, type);
	}
	
	
	// PRINTALL
	public void printAll(VisitType type)
	{
		Iterator<T> itr = this.iterator(type);
		
		System.out.println("VISIT TYPE: " + type);
		
		while(itr.hasNext())
			System.out.println(itr.next());
	}


	// ITERATOR - NOT USED
	public Iterator<T> iterator() { return null; }
	
	
	// MAIN
	public static void main(String...args)
	{
		TreeInterface<String> A = new FSNSTree<String>();
		TreeInterface<String> B = new FSNSTree<String>();
		TreeInterface<String> C = new FSNSTree<String>();
		TreeInterface<String> D = new FSNSTree<String>();
		TreeInterface<String> E = new FSNSTree<String>();
		
		A.insertRoot("A");
		B.insertRoot("B");
		C.insertRoot("C");
		D.insertRoot("D");
		E.insertRoot("E");
		
		A.insertFirstSubtree(A.root(), C);
		A.insertSubtree(((FSNSNode) A.root()).firstSon, B);
		A.insertFirstSubtree(((FSNSNode) A.root()).firstSon, D);
		A.insertFirstSubtree(((FSNSNode) A.root()).firstSon, E);
		
		//        A
		//       / \
		//      C   B
		//     / \
		//    E   D
		       
		// USING THE ITERATOR
		((FSNSTree) A).printAll(VisitType.IN);
	}
}
