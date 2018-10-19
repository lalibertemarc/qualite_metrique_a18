package packageModels;

import java.util.ArrayList;
import java.util.List;

public class Class_dec implements Modelable,Metricable {

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

	//Metricable methods
	
	//ANA
	@Override
	public int getAverageMethodArgumentCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	//NOM
	@Override
	public int getMethodCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	//NOA
	@Override
	public int getAttributeCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	//ITC
	@Override
	public int getModelableArgumentCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	//ETC
	@Override
	public int getTimesUsedAsArgument() {
		// TODO Auto-generated method stub
		return 0;
	}

	//CAC
	@Override
	public int getAssociationCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	//DIT
	@Override
	public int getLongestPathLengthToRoot() {
		// TODO Auto-generated method stub
		return 0;
	}

	//CLD
	@Override
	public int getLongestPathLengthtoLeaf() {
		// TODO Auto-generated method stub
		return 0;
	}

	//NOC
	@Override
	public int getDirectSubClassCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	//NOC
	@Override
	public int getSubClassCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	//Required for UI
	@Override
	public List<String> getAllMetrics() {
		ArrayList<String> output = new ArrayList<String>();
		
		output.add("ANA: " + getAverageMethodArgumentCount());
		output.add("NOM: " + getMethodCount());
		output.add("NOA: " + getAttributeCount());
		output.add("ITC: " + getModelableArgumentCount());
		output.add("ETC: " + getTimesUsedAsArgument());
		output.add("CAC: " + getAssociationCount());
		output.add("DIT: " + getLongestPathLengthToRoot());
		output.add("CLD: " + getLongestPathLengthtoLeaf());
		output.add("NOC: " + getDirectSubClassCount());
		output.add("NOD: " + getSubClassCount());
		
		return output;	
	}
	
	//Optionnal Helpers
	@Override
	public int getMethodArgumentCount() {
		// TODO Auto-generated method stub
		return 0;
	}



}