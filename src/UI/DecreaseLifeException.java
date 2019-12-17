package UI;

public class DecreaseLifeException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public DecreaseLifeException() {
		super();
	}
	
	@Override
	public String getMessage() {
		String message = "";
		message = "Index out of range";
		return message;
	}
}
