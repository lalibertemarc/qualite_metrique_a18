package packageModels;

public class Data_Item implements Modelable, Returnable {

	private String identifier;
	private String type;
	private String details;

	public Data_Item() {
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public String getIdentifier() {
		return identifier;
	}

	@Override
	public String getDetails() {
		return details;
	}

	@Override
	public void setIdentifier(String id) {
		identifier=id;
		
	}

	@Override
	public void setDetails(String details) {
		this.details=details;
		
	}

	@Override
	public void setType(String type) {
		this.type = type;
	}



}