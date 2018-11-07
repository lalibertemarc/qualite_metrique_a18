package packageModels;
/**
 * The Class ErrorModelable.
 * Abstract class to add abastraction to ParsingError
 * and make code cleaner in ParsingError
 */
public abstract class ErrorModelable implements Modelable
{
	
	/* (non-Javadoc)
	 * @see packageModels.Modelable#getIdentifier()
	 */
	@Override
	public String getIdentifier() {
		return "error";
	}

	/* (non-Javadoc)
	 * @see packageModels.Modelable#getDetails()
	 */
	@Override
	public String getDetails() {
		return "";
	}

	/* (non-Javadoc)
	 * @see packageModels.Modelable#setIdentifier(java.lang.String)
	 */
	@Override
	public void setIdentifier(String id) {
		// do nothing
		
	}

	/* (non-Javadoc)
	 * @see packageModels.Modelable#setDetails(java.lang.String)
	 */
	@Override
	public void setDetails(String details) {
		//do nothing
		
	}
	
	/**
	 * Sets the message.
	 *
	 * @param m the new message
	 */
	abstract public void setMessage(String m);
	
	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	abstract public String getMessage();
	
}
