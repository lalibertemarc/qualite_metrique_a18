package packageModels;

public class ParsingError implements Modelable{
	private String identifier;
	private String message;
	
	
	public ParsingError(String m)
	{
		message = m;
	}
	
	@Override
	public String getIdentifier() {
		return "error";
	}

	@Override
	public String getDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setIdentifier(String id) {
		identifier= id;
		
	}

	@Override
	public void setDetails(String details) {
		// TODO Auto-generated method stub
		
	}
	public void setMessage(String m) { message = m;}

	public String getMessage() {return message;}

}
