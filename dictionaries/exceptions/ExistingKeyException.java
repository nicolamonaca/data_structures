package exceptions;


public class ExistingKeyException extends RuntimeException
{
	public ExistingKeyException() {}
	
	public ExistingKeyException(String message)
	{
		super(message);
	}
}
