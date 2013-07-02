package exceptions;


public class InvalidEdgeException extends RuntimeException
{
	public InvalidEdgeException() {}
	
	public InvalidEdgeException(String message)
	{
		super(message);
	}
}
