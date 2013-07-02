package exceptions;


public class ExistingEdgeException extends RuntimeException
{
	public ExistingEdgeException() {}
	
	public ExistingEdgeException(String message)
	{
		super(message);
	}
}
