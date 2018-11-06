package packageModels;

/**
 * The Class ParsingError.
 * Returned by the Parser in case of corrupted file.
 */
public class ParsingError extends ErrorModelable{
	
	/** The message. */
	private String message;
	
	/**
	 * Instantiates a new parsing error.
	 *
	 * @param m the m
	 */
	public ParsingError(String m)
	{
		super();
		message = m;
	}
	
	/* (non-Javadoc)
	 * @see packageModels.ErrorModelable#setMessage(java.lang.String)
	 */
	public void setMessage(String m) { message = m;}

	/* (non-Javadoc)
	 * @see packageModels.ErrorModelable#getMessage()
	 */
	public String getMessage() {return message;}

}
