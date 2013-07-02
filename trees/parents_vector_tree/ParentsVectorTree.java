// IMPLEMENTATION OF A TREE USING A PARENTS ARRAY
//   Author: Nicola Lamonaca


package tree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import exceptions.*;


public class ParentsVectorTree<T> implements TreeInterface<T>, Iterable<T>
{
	private ParentsVectorNode[] parents;
	private ParentsVectorNode[] nodes;
	private List<NodeInterface> list = new LinkedList<NodeInterface>();
	
	
	// CONSTRUCTOR
	public ParentsVectorTree()
	{
		this.parents = new ParentsVectorNode[0];
		this.nodes = new ParentsVectorNode[0];
	}

	
	//CHECKNODE
	private boolean checkNode(NodeInterface v)
	{
		if(v == null)
			return true;
		
		if(((ParentsVectorNode) v).tree != this)
			return true;
			
		return false;
	}
	
	
	// EMPTYTREE
	public boolean emptyTree()
	{
		return nodes.length == 0;
	}

	
	// INSERTROOT
	public void insertRoot(T info) throws ExistingNodeException
	{
		if(!emptyTree())
			throw new ExistingNodeException("Already existing root.");
		
		parents = new ParentsVectorNode[1];
		parents[0] = null;
		
		nodes = new ParentsVectorNode[1];
		nodes[0] = new ParentsVectorNode<T>(info);
		nodes[0].tree = this;
	}
	

	// ROOT
	public NodeInterface root() throws EmptyStructureException
	{
		if(emptyTree())
			throw new EmptyStructureException("Empty tree.");
		
		return nodes[0];
	}

	
	// PARENT
	public NodeInterface parent(NodeInterface v) throws InvalidNodeException
	{
		if(checkNode(v))
			throw new InvalidNodeException("Node is not valid.");
		
		return parents[((ParentsVectorNode) v).index];
	}

	
	// LEAF
	public boolean leaf(NodeInterface v) throws InvalidNodeException
	{
		if(checkNode(v))
			throw new InvalidNodeException("Node is not valid.");
		
		for(int i = ((ParentsVectorNode) v).index + 1; i < parents.length; i++)
			if(parents[i] == v)
				return false;
		
		return true;
	}
	

	// FIRSTSON
	public NodeInterface firstSon(NodeInterface v) throws InvalidNodeException
	{
		if(checkNode(v))
			throw new InvalidNodeException("Node is not valid.");
		
		for(int i = ((ParentsVectorNode) v).index + 1; i < parents.length; i++)
			if(parents[i] == v)
				return nodes[i];
		
		return null;		
	}
	

	// ENDSIBLINGS
	public boolean endSiblings(NodeInterface v) throws InvalidNodeException
	{
		if(checkNode(v))
			throw new InvalidNodeException("Node is not valid.");
		
		return nextSibling(v) == null;
	}
	

	// NEXTSIBLINGS
	public NodeInterface nextSibling(NodeInterface v) throws InvalidNodeException
	{
		if(checkNode(v))
			throw new InvalidNodeException("Node is not valid.");
		
		int index = ((ParentsVectorNode) v).index;
		
		// If 'v' is the root
		if(((ParentsVectorNode) v) == this.root())
			return null;
		
		// If 'v' is the last element in the 'nodes' array
		if(index == nodes.length - 1)
			return null;
		
		if(parents[index + 1] == parents[index])
			return nodes[index + 1];
		
		return null;
	}
	

	// INSERTFIRSTSUBTREE
	public void insertFirstSubtree(NodeInterface v, TreeInterface t) throws InvalidNodeException
	{
		
		// ** NEEDS TO BE CHECKED THOROUGHLY **
		
		if(checkNode(v))
			throw new InvalidNodeException("Node is not valid.");
		
		int index = ((ParentsVectorNode) v).index; // The index of the element which will become the parent(root(t))
		
		// Create new nodes and parents arrays
		ParentsVectorNode[] tempNodes = new ParentsVectorNode[nodes.length + ((ParentsVectorTree) t).nodes.length];
		ParentsVectorNode[] tempParents = new ParentsVectorNode[parents.length + ((ParentsVectorTree) t).parents.length];
		
		// Copy the first 'index' nodes and parents into the new temp arrays
		System.arraycopy(nodes, 0, tempNodes, 0, index);
		System.arraycopy(parents, 0, tempParents, 0, index);
		
		// Insert the new root
		  tempNodes[index + 1] = (ParentsVectorNode) ((ParentsVectorTree) t).root();
		  tempNodes[index + 1].tree = this;
		  tempNodes[index + 1].index = index + 1;
		tempParents[index + 1] = (ParentsVectorNode) v;
		
		// Insert the remaining nodes
		for(int i = index + 1; i < nodes.length; i++)
		{
			  tempNodes[i + 1] = nodes[i];
			  tempNodes[i + 1].index = i + 1;
			tempParents[i + 1] = parents[i];
		}
		
		int j = 1;
		
		// Copy the remaining nodes into 'tempNodes' and 'tempParents'
		for(int i = nodes.length + 1; i < tempNodes.length; i++)
		{
			  tempNodes[i] = ((ParentsVectorTree) t).nodes[j];
			  tempNodes[i].index = i;
			tempParents[i] = ((ParentsVectorTree) t).parents[j];
			  tempNodes[i].tree = this;
			  
			  j++;
		}
		
		parents = tempParents;
		nodes = tempNodes;
		
		
		return;
	}
	

	// INSERTSUBTREE
	public void insertSubtree(NodeInterface v, TreeInterface t) throws InvalidNodeException
	{
		if(checkNode(v))
			throw new InvalidNodeException("Node is not valid.");
		
		int index = ((ParentsVectorNode) v).index;
		
		// Create new nodes and parents arrays
		ParentsVectorNode[] tempNodes = new ParentsVectorNode[nodes.length + ((ParentsVectorTree) t).nodes.length];
		ParentsVectorNode[] tempParents = new ParentsVectorNode[parents.length + ((ParentsVectorTree) t).parents.length];
		
		// Copy all nodes and parents into the new temp arrays
		System.arraycopy(nodes, 0, tempNodes, 0, index + 1);
		System.arraycopy(parents, 0, tempParents, 0, index + 1);
		
		// Insert the new root
		  tempNodes[index + 1] = (ParentsVectorNode) ((ParentsVectorTree) t).root();
		  tempNodes[index + 1].tree = this;
		  tempNodes[index + 1].index = index + 1;
		tempParents[index + 1] = (ParentsVectorNode) v;
		
		// Insert the remaining nodes
		for(int i = index + 1; i < nodes.length; i++)
		{
			  tempNodes[i + 1] = nodes[i];
			  tempNodes[i + 1].index = i + 1;
			tempParents[i + 1] = parents[i];
		}
		
		int j = 1;
		
		for(int i = nodes.length + 1; i < tempNodes.length; i++)
		{
			  tempNodes[i] = ((ParentsVectorTree) t).nodes[j];
			  tempNodes[i].index = i;
			tempParents[i] = ((ParentsVectorTree) t).parents[j];
			  tempNodes[i].tree = this;
			  
			  j++;
		}
		
		parents = tempParents;
		nodes = tempNodes;
	}

	
	// DELETESUBTREE
	public void deleteSubTree(NodeInterface v) throws InvalidNodeException
	{
		NodeInterface temp;
		
		if(checkNode(v))
			throw new InvalidNodeException("Node is not valid.");
		
		// If 'v' is an inner node
		if(!leaf(v))
		{
			// Be 'temp' its first son
			temp = (ParentsVectorNode) firstSon(v);
			
			LinkedList<ParentsVectorNode> siblings = new LinkedList<ParentsVectorNode>();
			
			// Add the first son of 'v' into 'siblings'
			siblings.add((ParentsVectorNode) temp);
			
			// While 'temp' has more siblings
			while(!endSiblings(temp))
			{
				// Get 'em
				temp = nextSibling(temp);
				
				// .. And add 'em all into 'siblings'
				siblings.add((ParentsVectorNode) temp);
			}
			
			// Obtain an iterator over 'siblings'
			Iterator<ParentsVectorNode> itr = siblings.iterator();
			
			// For each sibling ..
			while(itr.hasNext())
			{
				// Get one
				temp = itr.next();
				
				 // .. And recursively delete its subtree until a leaf node is met
				deleteSubTree(temp);
			}	
		}
		
		// .. In which case delete that node (BASE CASE OF THE RECURSION)
		deleteNode(v);	
	}
	
	
	// DELETENODE
	private void deleteNode(NodeInterface v)
	{
		int n = parents.length;
		
		ParentsVectorNode[] tempNodes = new ParentsVectorNode[n - 1];
		
		// We copy all the nodes from 'nodes' into 'tempNodes'
		System.arraycopy(nodes, 0, tempNodes, 0, ((ParentsVectorNode) v).index);
		// .. Skipping the middle node 'v'
		System.arraycopy(nodes, ((ParentsVectorNode) v).index + 1, tempNodes, ((ParentsVectorNode) v).index, tempNodes.length - ((ParentsVectorNode) v).index);
		
		nodes = tempNodes;
		
		// (We reuse the same variable for the nodes array also for the parents array)
		tempNodes = new ParentsVectorNode[n - 1];
		
		// We copy all the nodes from 'parents' into 'tempNodes'
		System.arraycopy(parents, 0, tempNodes, 0, ((ParentsVectorNode) v).index);
		// .. Skipping the middle node given by parents[v.index]
		System.arraycopy(parents, ((ParentsVectorNode) v).index + 1, tempNodes, ((ParentsVectorNode) v).index, tempNodes.length - ((ParentsVectorNode) v).index);
		
		parents = tempNodes;
		
		// Reset all indexes
		for(int i  = 0; i < nodes.length; i++)
			nodes[i].index = i;
	}
	
	
	// INFO
	public T info(NodeInterface v) throws InvalidNodeException
	{
		if(checkNode(v))
			throw new InvalidNodeException("Node is not valid.");
		
		return (T) ((ParentsVectorNode) v).info;
	}

	
	// CHANGEINFO
	public void changeInfo(NodeInterface v, T newInfo) throws InvalidNodeException
	{
		if(checkNode(v))
			throw new InvalidNodeException("Node is not valid");
		
		if(newInfo != ((ParentsVectorNode) v).info)
			((ParentsVectorNode) v).info = newInfo;
	}


	// ITERATOR
	public Iterator<T> iterator(VisitType type)
	{
		return new ParentsVectorTreeIterator(this, type);
	}
	
	
	// ITERATOR - NOT USED
	public Iterator<T> iterator() { return null; }
	
	
	// PRINTALL
	private void printAll(VisitType type)
	{
		Iterator<T> itr = this.iterator(type);
		
		System.out.println("VISIT TYPE: " + type);
		
		while(itr.hasNext())
			System.out.println(itr.next());
	}
	
	
	// MAIN
	public static void main(String...args)
	{
		TreeInterface<String> A = new ParentsVectorTree();
		TreeInterface<String> B = new ParentsVectorTree();
		TreeInterface<String> C = new ParentsVectorTree();
		TreeInterface<String> D = new ParentsVectorTree();
		TreeInterface<String> E = new ParentsVectorTree();
		
		A.insertRoot("A");
		B.insertRoot("B");
		C.insertRoot("C");
		D.insertRoot("D");
		E.insertRoot("E");
		
		A.insertSubtree(A.root(), C);
		A.insertSubtree(A.root(), B);
		A.insertSubtree(C.root(), D);
		A.insertSubtree(C.root(), E);
		
		//        A
		//       / \
		//      B   C
		//     / \
		//    E   D
		
		// USING THE ITERATOR
		((ParentsVectorTree) A).printAll(VisitType.PRE);
	}
}
