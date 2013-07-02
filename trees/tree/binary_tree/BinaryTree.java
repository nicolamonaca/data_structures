// IMPLEMENTATION OF A BINARY TREE USING A LINKED STRUCTURE WITH POINTERS TO THE LEFT SON AND THE RIGHT SON
// Author:   Nicola Lamonaca


package tree;

import java.util.Iterator;
import exceptions.*;


public class BinaryTree<T> implements BinaryTreeInterface<T>, Iterable<T>
{
	private BinaryNode root = null;
	
	
	// CHECKNODE
	private boolean checkNode(NodeInterface v)
	{
		if(v == null)
			return true;
		
		if(((BinaryNode) v).tree != this)
			return true;
		
		return false;
	}
	
	
	// EMPTYTREE
	public boolean emptyTree()
	{
		return this.root == null;
	}


	// INSERTROOT
	public void insertRoot(T info)
	{
		this.root = new BinaryNode(info); 
		this.root.tree = this;
	}

	
	// ROOT
	public NodeInterface root()
	{
		return this.root;
	}

	
	// PARENT
	public NodeInterface parent(NodeInterface v) throws InvalidNodeException
	{
		if(checkNode(v))
			throw new InvalidNodeException("Node is not valid.");
		
		return ((BinaryNode) v).parent;
	}

	
	// LEFTSON
	public NodeInterface leftSon(NodeInterface v) throws InvalidNodeException
	{
		if(checkNode(v))
			throw new InvalidNodeException("Node is not valid.");
		
		return ((BinaryNode) v).leftSon;
	}
	
	
	// RIGHTSON
	public NodeInterface rightSon(NodeInterface v) throws InvalidNodeException
	{
		if(checkNode(v))
			throw new InvalidNodeException("Node is not valid.");
		
		return ((BinaryNode) v).rightSon;
	}
	
	
	//ISEMPTYLEFTSON
	boolean isEmptyLeftSon(NodeInterface u)
	{
		return ((BinaryNode) u).leftSon == null;
	}
	
	
	//ISEMPTYRIGHTSON
	boolean isEmptyRightSon(NodeInterface u)
	{
		return ((BinaryNode) u).rightSon == null;
	}
	

	// INSERTLEFTSUBTREE
	public void insertLeftSubtree(NodeInterface v, BinaryTreeInterface t) throws InvalidNodeException, ExistingNodeException
	{
		if(checkNode(v))
			throw new InvalidNodeException("Node is not valid.");
		
		BinaryNode u = (BinaryNode) v;
		
		if(!isEmptyLeftSon(u))
			throw new ExistingNodeException("Already existing node.");
		
		if(((BinaryTree) t).emptyTree())
			return;
		
		u.leftSon = ((BinaryTree) t).root;
		
		((BinaryTree) t).root.parent = u;
		
		((BinaryTree) t).root = null;
		
		updateTreeID(u.leftSon);
	}

	
	// INSERTRIGHTSUBTREE
	public void insertRightSubtree(NodeInterface v, BinaryTreeInterface t)  throws InvalidNodeException, ExistingNodeException
	{
		if(checkNode(v))
			throw new InvalidNodeException("Node is not valid.");
		
		BinaryNode u = (BinaryNode) v;
		
		if(!isEmptyRightSon(u))
			throw new ExistingNodeException("Already existing node.");
		
		if(((BinaryTree) t).emptyTree())
			return;
		
		u.rightSon = ((BinaryTree) t).root;
		
		((BinaryTree) t).root.parent = u;
		
		((BinaryTree) t).root = null;
		
		updateTreeID(u.rightSon);
	}
	
	
	
	// UPDATETREEID
	private void updateTreeID(NodeInterface v)
	{
		((BinaryNode) v).tree = this;
		
		if(leftSon(v) != null)
			updateTreeID(((BinaryNode) v).leftSon);
		
		if(rightSon(v) != null)
			updateTreeID(((BinaryNode) v).rightSon);
	}
	

	
	// PRUNE
	public void prune(NodeInterface v) throws InvalidNodeException
	{
		if(checkNode(v))
			throw new InvalidNodeException("Node is not valid.");
		
		BinaryNode u = (BinaryNode) v;
		
		if(u == this.root)
			this.root = null;
		else
		{
			if(u.parent.leftSon == v)
				u.parent.leftSon = null;
			else if(u.parent.rightSon == null)
				u.parent.rightSon = null;
			
			u.parent = null;
		}
	}

	
	// INFO
	public T info(NodeInterface v) throws InvalidNodeException
	{
		if(checkNode(v))
			throw new InvalidNodeException("Node is not valid.");
		
		return (T) ((BinaryNode) v).info;
	}
	
	
	// CHANGEINFO
	public void changeInfo(NodeInterface v, T info) throws InvalidNodeException
	{
		if(checkNode(v))
			throw new InvalidNodeException("Node is not valid.");
		
		((BinaryNode) v).info = info;
	}
	
	
	// ITERATOR
	public Iterator<T> iterator(VisitType type)
	{
		return new BinaryTreeIterator(this, type);
	}
	
	
	// ITERATOR - NOT USED
	public Iterator<T> iterator()
	{
		return null;
	}
	
	
	// PRINTALL
	private void printAll(VisitType type)
	{
		Iterator<T> itr = this.iterator(type);
		
		System.out.println("\nVISIT TYPE: " + type);
		
		while(itr.hasNext())
			System.out.println(((BinaryNode) itr.next()).info);
	}
	
	
	// MAIN
	public static void main(String...args)
	{
		BinaryTreeInterface<String> A = new BinaryTree<String>();
		BinaryTreeInterface<String> B = new BinaryTree<String>();
		BinaryTreeInterface<String> C = new BinaryTree<String>();
		BinaryTreeInterface<String> D = new BinaryTree<String>();
		BinaryTreeInterface<String> E = new BinaryTree<String>();
		
		A.insertRoot("A");
		B.insertRoot("B");
		C.insertRoot("C");
		D.insertRoot("D");
		E.insertRoot("E");
		
		A.insertLeftSubtree((BinaryNode) A.root(), C);
		A.insertRightSubtree((BinaryNode) A.root(), B);
		A.insertRightSubtree(((BinaryNode) A.root()).leftSon, D);
		A.insertLeftSubtree(((BinaryNode) A.root()).leftSon, E);
		
		//        A
		//       / \
		//      C   B
		//     / \
		//    E   D
		       
		// USING THE ITERATOR
		((BinaryTree) A).printAll(VisitType.PRE);
	}
}
