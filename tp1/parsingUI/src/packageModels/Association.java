package packageModels;
public class Association implements Modelable {
	
	private Role role1;
	private Role role2;
	private String identifier;
	private String details;

	public Role getRole1() {
		return this.role1;
	}

	public void setRole1(Role role1) {
		this.role1 = role1;
	}

	public Role getRole2() {
		return this.role2;
	}

	public void setRole2(Role role2) {
		this.role2 = role2;
	}

	public Association() {

	}

	@Override
	public String getIdentifier() {
		return this.identifier;
	}

	@Override
	public String getDetails() {
		return this.details;
	}


	@Override
	public void setIdentifier(String id) {
		this.identifier = id;
	}

	@Override
	public void setDetails(String details) {
		this.details = details;
	}

}