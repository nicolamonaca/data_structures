package exceptions;


public class ExistingNodeException extends RuntimeException
{
	public ExistingNodeException() {}
	
	public ExistingNodeException(String message)
	{
		super(message);
	}
}