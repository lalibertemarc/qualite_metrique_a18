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
		// TODO - implement Association.Association
		throw new UnsupportedOperationException();
	}

	@Override
	public String getIdentifier() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDetails() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void setIdentifier(String id) {
		// TODO Auto-generated method stub
	}

	@Override
	public void setDetails(String details) {
		// TODO Auto-generated method stub
	}

}