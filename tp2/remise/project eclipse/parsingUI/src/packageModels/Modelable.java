package packageModels;
/**
 * The Interface Modelable.
 * Used to define the main components of the class diagram
 */
public interface Modelable {

	/**
	 * Gets the identifier.
	 *
	 * @return the identifier
	 */
	String getIdentifier();

	/**
	 * Gets the details.
	 *
	 * @return the details
	 */
	String getDetails();

	/**
	 * Sets the identifier.
	 *
	 * @param id the new identifier
	 */
	void setIdentifier(String id);
	
	/**
	 * Sets the details.
	 *
	 * @param details the new details
	 */
	void setDetails(String details);
}