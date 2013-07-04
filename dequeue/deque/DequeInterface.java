package deque;


public interface DequeInterface<T>
{
	public boolean isEmpty();
	
	// QUEUE
	public void enqueue(T e); // Inserts an element on the BOTTOM
	
	public void dequeue(); // Removes an element from the BOTTOM
	
	public T first(); // Returns the BOTTOM
	// END QUEUE
	
	// STACK
	public void push(T e); // Inserts an element on the FRONT
	
	public void pop(); // Removes an element from the FRONT
	
	public T top(); // Returns the FRONT
	// END STACK
}

/*
          -------------------------------------
BOTTOM -->|   |   |   |   |   |   |   |   |   | <-- FRONT
          -------------------------------------
*/