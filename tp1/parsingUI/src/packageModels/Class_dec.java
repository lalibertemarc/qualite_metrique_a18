package packageModels;

import java.util.ArrayList;
import java.util.List;

import testParsing.Parser;

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
		return false;
		//throw new UnsupportedOperationException();
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
	
	/*
	use this for metrics
	get all classes from Parser
	ArrayList<Class_dec> allClasses = (ArrayList<Class_dec>) Parser.getAllClasses();
	*/
	
	//ANA
	@Override
	public int getAverageMethodArgumentCount() {
		// TODO Auto-generated method stub
		int count = 0;
		return count;
	}

	//NOM
	@Override
	public int getMethodCount() {
		// TODO Auto-generated method stub
		int count = 0;
		if(this.operations!=null) {
				count = this.operations.size();
		}
		return count;
	}

	//NOA
	@Override
	public int getAttributeCount() {
		// TODO Auto-generated method stub
		int count = 0;
		if(this.attributes!=null) {
				count = this.attributes.size();
		}
		return count;
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
		int countAss = 0;
		int countAgg = 0;
		if(this.hasAggregation()) {
		// TODO 
		}
		if(this.hasAssociations) {
			countAss = this.assoList.size();
		}
		return countAss+countAgg;
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
		int count = 0;
		if(this.subclasses!=null) {
				count = this.subclasses.size();
		}
		return count;
	}

	//NOD
	@Override
	public int getSubClassCount() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public static int count(Class_dec ci) {
		// TODO calcul recursif
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