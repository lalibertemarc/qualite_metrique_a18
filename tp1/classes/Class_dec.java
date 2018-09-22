package packageModels;

import java.util.List;

public class Class_dec implements Modelable {

	private String identifier;
	private List<Data_Item> attributes;
	private List<Operation> operations;
	private String details;
	private List<Class_dec> subclasses;

	public List<Data_Item> getAttributes() {
		return this.attributes;
	}

	public void setAttributes(List<Data_Item> attributes) {
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
	
	}
	public void setSubClasses(List<Class_dec> list)
	{
		this.subclasses= list;
	}

	@Override
	public String getIdentifier() {
		return identifier;
	}

	@Override
	public String getDetails() {
		return details;
	}

	@Override
	public void setIdentifier(String id) {
		identifier=id;
		
	}

	@Override
	public void setDetails(String details) {
		this.details=details;
		
	}


}