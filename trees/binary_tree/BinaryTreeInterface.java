package tree;


public interface BinaryTreeInterface<T>
{
	public boolean emptyTree();
	
	public void insertRoot(T info);
	
	public NodeInterface root();
	
	public NodeInterface parent(NodeInterface v);
	
	public NodeInterface leftSon(NodeInterface v);
	
	public NodeInterface rightSon(NodeInterface v);
	
	public void insertLeftSubtree(NodeInterface v, BinaryTreeInterface tree);
	
	public void insertRightSubtree(NodeInterface v, BinaryTreeInterface tree);

	public void prune(NodeInterface v);
	
	public T info(NodeInterface v);
	
	public void changeInfo(NodeInterface v, T info);
}
