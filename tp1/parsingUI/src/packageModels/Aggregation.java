package packageModels;

public class Aggregation implements Modelable{

	private Role container;
	private Role parts;
	private String details;

	public Role getContainer() {
		return this.container;
	}

	public void setContainer(Role container) {
		this.container = container;
	}

	public Role getParts() {
		return this.parts;
	}

	public void setParts(Role parts) {
		this.parts = parts;
	}

	public Aggregation() {
		
	}

	@Override
	public String getIdentifier() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDetails() {
		return this.details;
	}

	@Override
	public void setIdentifier(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDetails(String details) {
		this.details = details;
		
	}


}