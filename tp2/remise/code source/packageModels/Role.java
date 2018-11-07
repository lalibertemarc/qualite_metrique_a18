package packageModels;
/**
 * The Class Role.
 */
public class Role {

	/** The class dec. */
	private String class_dec;
	
	/** The multiplicity. */
	private Multiplicity multiplicity;

	/**
	 * Gets the class dec.
	 *
	 * @return the class dec
	 */
	public String getClass_dec() {
		return this.class_dec;
	}

	/**
	 * Sets the class dec.
	 *
	 * @param class_dec the new class dec
	 */
	public void setClass_dec(String class_dec) {
		this.class_dec = class_dec;
	}

	/**
	 * Gets the multiplicity.
	 *
	 * @return the multiplicity
	 */
	public Multiplicity getMultiplicity() {
		return this.multiplicity;
	}

	/**
	 * Sets the multiplicity.
	 *
	 * @param multiplicity the new multiplicity
	 */
	public void setMultiplicity(Multiplicity multiplicity) {
		this.multiplicity = multiplicity;
	}

	/**
	 * Instantiates a new role.
	 */
	public Role() {
	}

}