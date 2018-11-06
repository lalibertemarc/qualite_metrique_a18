package packageModels;
/**
 * The Class Association.
 */
public class Association implements Modelable {
	
	/** The role 1. */
	private Role role1;
	
	/** The role 2. */
	private Role role2;
	
	/** The identifier. */
	private String identifier;
	
	/** The details. */
	private String details;

	/**
	 * Gets the role 1.
	 *
	 * @return the role 1
	 */
	public Role getRole1() {
		return this.role1;
	}

	/**
	 * Sets the role 1.
	 *
	 * @param role1 the new role 1
	 */
	public void setRole1(Role role1) {
		this.role1 = role1;
	}

	/**
	 * Gets the role 2.
	 *
	 * @return the role 2
	 */
	public Role getRole2() {
		return this.role2;
	}

	/**
	 * Sets the role 2.
	 *
	 * @param role2 the new role 2
	 */
	public void setRole2(Role role2) {
		this.role2 = role2;
	}

	/**
	 * Instantiates a new association.
	 */
	public Association() {

	}

	/* (non-Javadoc)
	 * @see packageModels.Modelable#getIdentifier()
	 */
	@Override
	public String getIdentifier() {
		return this.identifier;
	}

	/* (non-Javadoc)
	 * @see packageModels.Modelable#getDetails()
	 */
	@Override
	public String getDetails() {
		return this.details;
	}


	/* (non-Javadoc)
	 * @see packageModels.Modelable#setIdentifier(java.lang.String)
	 */
	@Override
	public void setIdentifier(String id) {
		this.identifier = id;
	}

	/* (non-Javadoc)
	 * @see packageModels.Modelable#setDetails(java.lang.String)
	 */
	@Override
	public void setDetails(String details) {
		this.details = details;
	}

}