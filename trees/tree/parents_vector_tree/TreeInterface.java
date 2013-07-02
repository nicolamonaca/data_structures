package tree;

import java.util.Iterator;


public interface TreeInterface<T>
{
	public boolean emptyTree();
	
	public void insertRoot(T info);
	
	public NodeInterface root();
	
	public NodeInterface parent(NodeInterface v);
	
	public boolean leaf(NodeInterface v);
	
	public NodeInterface firstSon(NodeInterface v);
	
	public boolean endSiblings(NodeInterface v);
	
	public NodeInterface nextSibling(NodeInterface v);
	
	public void insertFirstSubtree(NodeInterface v, TreeInterface t);
	
	public void insertSubtree(NodeInterface v, TreeInterface t);
	
	public void deleteSubTree(NodeInterface v);
	
	public T info(NodeInterface v);
	
	public void changeInfo(NodeInterface v, T newInfo);
}
