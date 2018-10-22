package packageModels;

import java.util.ArrayList;
import java.util.List;

public class Class_dec implements Modelable {

	private String identifier;
	private String details;
	private List<Data_Item> attributes;
	private List<Operation> operations;
	private List<String> subclasses;
	private boolean hasAssociations = false;
	private List<String> assoList = new ArrayList<String>();

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

	public List<String> getSubclasses() {
		return this.subclasses;
	}

	public void setSubclasses(List<String> subclasses) {
		this.subclasses = subclasses;
	}

	public boolean hasAssociation() {
		return hasAssociations;
	}
	public void setAssoFlag(boolean b)
	{
		hasAssociations=b;
	}

	public void addAssoToList(String s)
	{
		assoList.add(s);
	}
	
	public List<String> getAssoList()
	{
		return assoList;
	}
	public boolean hasAggregation() {
		// TODO - implement Class_dec.hasAggregation
		throw new UnsupportedOperationException();
	}

	public Class_dec() {
	
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