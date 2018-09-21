public class Class_dec implements Modelable {

	private string identifier;
	private List<Data_item> attributes;
	private List<Operation> operations;
	private string details;
	private List<Class_dec> subclasses;

	public List<Data_item> getAttributes() {
		return this.attributes;
	}

	public void setAttributes(List<Data_item> attributes) {
		this.attributes = attributes;
	}

	public List<Operation> getOperations() {
		return this.operations;
	}

	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}

	public List<Class_dec> getSubclasses() {
		return this.subclasses;
	}

	public void setSubclasses(List<Class_dec> subclasses) {
		this.subclasses = subclasses;
	}

	public boolean hasAssociation() {
		// TODO - implement Class_dec.hasAssociation
		throw new UnsupportedOperationException();
	}

	public boolean hasAggregation() {
		// TODO - implement Class_dec.hasAggregation
		throw new UnsupportedOperationException();
	}

	public Class_dec() {
		// TODO - implement Class_dec.Class_dec
		throw new UnsupportedOperationException();
	}

}