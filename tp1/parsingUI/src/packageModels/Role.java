package packageModels;

public class Role {

	private String class_dec;
	private Multiplicity multiplicity;

	public String getClass_dec() {
		return this.class_dec;
	}

	public void setClass_dec(String class_dec) {
		this.class_dec = class_dec;
	}

	public Multiplicity getMultiplicity() {
		return this.multiplicity;
	}

	public void setMultiplicity(Multiplicity multiplicity) {
		this.multiplicity = multiplicity;
	}

	public Role() {
	}

}