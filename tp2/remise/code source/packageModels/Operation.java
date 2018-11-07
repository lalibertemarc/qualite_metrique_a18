package packageModels;

import java.util.List;
/**
 * The Class Operation.
 */
public class Operation implements Modelable, Returnable {

	/** The identifier. */
	private String identifier;
	
	/** The type. */
	private String type;
	
	/** The arg list. */
	private List<Data_Item> arg_list;
	
	/** The details. */
	private String details;

	/**
	 * Gets the arg list.
	 *
	 * @return the arg list
	 */
	public List<Data_Item> getArg_list() {
		return this.arg_list;
	}

	/**
	 * Sets the arg list.
	 *
	 * @param arg_list the new arg list
	 */
	public void setArg_list(List<Data_Item> arg_list) {
		this.arg_list = arg_list;
	}

	/**
	 * Instantiates a new operation.
	 */
	public Operation() {
		
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
		this.identifier = id;		
	}

	/* (non-Javadoc)
	 * @see packageModels.Modelable#setDetails(java.lang.String)
	 */
	@Override
	public void setDetails(String details) {
		this.details = details;		
	}

	/* (non-Javadoc)
	 * @see packageModels.Returnable#setType(java.lang.String)
	 */
	@Override
	public void setType(String type) {
		this.type = type;		
	}


}