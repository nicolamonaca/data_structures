package exceptions;


public class EmptyStructureException extends RuntimeException
{
	public EmptyStructureException() {}
	
	public EmptyStructureException(String message)
	{
		super(message);
	}
}
