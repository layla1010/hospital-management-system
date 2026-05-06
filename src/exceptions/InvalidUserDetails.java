package exceptions;

public class InvalidUserDetails extends Exception{
	private static final long serialVersionUID = 1L;
	public InvalidUserDetails(String message) {
		super(message);
	}

}
