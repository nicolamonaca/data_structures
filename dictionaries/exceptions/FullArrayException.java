package exceptions;

public class FullArrayException extends RuntimeException
{
	public FullArrayException() {}
	
	public FullArrayException(String message)
	{
		super(message);
	}
}
