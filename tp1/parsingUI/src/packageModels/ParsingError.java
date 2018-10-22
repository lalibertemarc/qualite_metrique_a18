package packageModels;

public class ParsingError extends ErrorModelable{
	private String message;
	
	public ParsingError(String m)
	{
		super();
		message = m;
	}
	
	public void setMessage(String m) { message = m;}

	public String getMessage() {return message;}

}
