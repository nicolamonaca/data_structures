package tree;


public class BinaryNode<T> implements NodeInterface<T>
{
	public T info;
	public BinaryNode parent, leftSon, rightSon;
	public BinaryTree tree;
	
	
	public BinaryNode(T info)
	{
		this.info = info;
	}
}
