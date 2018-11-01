package packageModels;

/**
 * The Class Aggregation.
 */
public class Aggregation implements Modelable{

	/** The container. */
	private Role container;
	
	/** The parts. */
	private Role parts;
	
	/** The details. */
	private String details;

	/**
	 * Gets the container.
	 *
	 * @return the container
	 */
	public Role getContainer() {
		return this.container;
	}

	/**
	 * Sets the container.
	 *
	 * @param container the new container
	 */
	public void setContainer(Role container) {
		this.container = container;
	}

	/**
	 * Gets the parts.
	 *
	 * @return the parts
	 */
	public Role getParts() {
		return this.parts;
	}

	/**
	 * Sets the parts.
	 *
	 * @param parts the new parts
	 */
	public void setParts(Role parts) {
		this.parts = parts;
	}

	/**
	 * Instantiates a new aggregation.
	 */
	public Aggregation() {
		
	}

	/* (non-Javadoc)
	 * @see packageModels.Modelable#getIdentifier()
	 */
	@Override
	public String getIdentifier() {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see packageModels.Modelable#setDetails(java.lang.String)
	 */
	@Override
	public void setDetails(String details) {
		this.details = details;
		
	}


}