package exceptions;


public class InvalidNodeException extends RuntimeException
{
	public InvalidNodeException() {}
	
	public InvalidNodeException(String message)
	{
		super(message);
	}
}
