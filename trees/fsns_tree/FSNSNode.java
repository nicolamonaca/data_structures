package tree;


public class FSNSNode<T> implements NodeInterface<T> 
{
	T info;
	FSNSNode parent, firstSon, nextSibling;
	FSNSTree tree;
	
	
	// CONSTRUCTOR
	public FSNSNode(T info)
	{
		this.info = info;
	}
}
