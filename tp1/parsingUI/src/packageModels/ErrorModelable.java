package packageModels;

public abstract class ErrorModelable implements Modelable
{
	
	@Override
	public String getIdentifier() {
		return "error";
	}

	@Override
	public String getDetails() {
		return "";
	}

	@Override
	public void setIdentifier(String id) {
		// do nothing
		
	}

	@Override
	public void setDetails(String details) {
		//do nothing
		
	}
	
	abstract public void setMessage(String m);
	abstract public String getMessage();
	
}
