package queue;


public interface QueueInterface<T>
{
	public boolean isEmpty();
	
	public void enqueue(T e);
	
	public T first();
	
	public void dequeue();
}
