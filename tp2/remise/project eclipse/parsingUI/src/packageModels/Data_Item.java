package packageModels;

// TODO: Auto-generated Javadoc
/**
 * The Class Data_Item.
 */
public class Data_Item implements Modelable, Returnable {

	/** The identifier. */
	private String identifier;
	
	/** The type. */
	private String type;
	
	/** The details. */
	private String details;

	/**
	 * Instantiates a new data item.
	 */
	public Data_Item() {
	}

	/* (non-Javadoc)
	 * @see packageModels.Returnable#getType()
	 */
	@Override
	public String getType() {
		return type;
	}

	/* (non-Javadoc)
	 * @see packageModels.Modelable#getIdentifier()
	 */
	@Override
	public String getIdentifier() {
		return identifier;
	}

	/* (non-Javadoc)
	 * @see packageModels.Modelable#getDetails()
	 */
	@Override
	public String getDetails() {
		return details;
	}

	/* (non-Javadoc)
	 * @see packageModels.Modelable#setIdentifier(java.lang.String)
	 */
	@Override
	public void setIdentifier(String id) {
		identifier=id;
		
	}

	/* (non-Javadoc)
	 * @see packageModels.Modelable#setDetails(java.lang.String)
	 */
	@Override
	public void setDetails(String details) {
		this.details=details;
		
	}

	/* (non-Javadoc)
	 * @see packageModels.Returnable#setType(java.lang.String)
	 */
	@Override
	public void setType(String type) {
		this.type = type;
	}



}