package packageModels;
/**
 * The Interface Returnable.
 * Used when components can be returned like a operation (method)
 * or Data_item.
 */
public interface Returnable {

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	String getType();
	
	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	void setType(String type);
}