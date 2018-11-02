package packageModels;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class Class_dec.
 */
public class Class_dec implements Modelable,Metricable {

	/** The identifier. */
	private String identifier;
	
	/** The details. */
	private String details;
	
	/** The attributes. */
	private List<Data_Item> attributes;
	
	/** The operations. */
	private List<Operation> operations;
	
	/** The subclasses List.
	 * Used mostly by Parser */
	private List<String> subclasses;
	
	/** The sub class list. 
	 * List is as Class_dec to help with UI, inheritance tree and metrics*/
	private List<Class_dec> subClass;
	
	/** The has associations. */
	private boolean hasAssociations = false;
	
	/** The has aggregations. */
	private boolean hasAggregations = false;
	
	/** The is super class. */
	private boolean isSuperClass = false;
	
	/** The is sub class. */
	private boolean isSubClass = false;
	
	private int numberSubClasses;
	
	/** The all classes types. */
	private List<String> allClassesTypes;
	
	/** The asso list. */
	private List<String> assoList = new ArrayList<String>();
	
	/** The aggr list. */
	private List<String> aggrList = new ArrayList<String>();
	
	/** The super classe. */
	private Class_dec superClasse;
	
	/** The operation others classes. */
	private List<Operation> operationOthersClasses;

	/**
	 * Gets the attributes.
	 *
	 * @return the attributes
	 */
	public List<Data_Item> getAttributes() {
		return this.attributes;
	}

	/**
	 * Sets the attributes.
	 *
	 * @param attributes the new attributes
	 */
	public void setAttributes(List<Data_Item> attributes) {
		this.attributes = attributes;
	}

	
	/**
	 * Gets the operations.
	 *
	 * @return the operations
	 */
	public List<Operation> getOperations() {
		return this.operations;
	}

	/**
	 * Sets the operations.
	 *
	 * @param operations the new operations
	 */
	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}

	
	/**
	 * Gets the subclasses.
	 *
	 * @return the subclasses
	 */
	public List<String> getSubclasses() {
		return this.subclasses;
	}

	/**
	 * Sets the subclasses.
	 *
	 * @param subclasses the new subclasses
	 */
	public void setSubclasses(List<String> subclasses) {
		this.subclasses = subclasses;
	}

	
	/**
	 * Gets the superclasse.
	 *
	 * @return the superclasse
	 */
	public Class_dec getSuperclasse() {
		return this.superClasse;
	}

	/**
	 * Sets the superclasse.
	 *
	 * @param superclasse the new superclasse
	 */
	public void setSuperclasse(Class_dec superclasse) {
		this.superClasse = superclasse;
	}
	
	
	
	/**
	 * Checks for aggregations.
	 *
	 * @return true, if successful
	 */
	public boolean hasAggregations() {
		return hasAggregations;
	}
	
	/**
	 * Sets the aggregation flag.
	 *
	 * @param b the new aggr flag
	 */
	public void setAggrFlag(boolean b)
	{
		hasAggregations=b;
	}

	/**
	 * Sets the aggregation list.
	 *
	 * @param s the new aggr list
	 */
	public void setAggrList(List<String> s)
	{
		this.aggrList = s;
	}
	
	/**
	 * Gets the aggregation list.
	 *
	 * @return the aggr list
	 */
	public List<String> getAggrList()
	{
		return aggrList;
	}
	
	
	/**
	 * Checks for association.
	 *
	 * @return true, if successful
	 */
	public boolean hasAssociation() {
		return hasAssociations;
	}
	
	/**
	 * Sets the association flag.
	 *
	 * @param b the new asso flag
	 */
	public void setAssoFlag(boolean b)
	{
		hasAssociations=b;
	}

	/**
	 * Adds the association to list.
	 *
	 * @param s the s
	 */
	public void addAssoToList(String s)
	{
		assoList.add(s);
	}
	
	/**
	 * Gets the associationo list.
	 *
	 * @return the asso list
	 */
	public List<String> getAssoList()
	{
		return assoList;
	}
	
	/**
	 * Checks if is sub class.
	 *
	 * @return true, if is sub class
	 */
	public boolean isSubClass() {
		return isSubClass;
	}
	
	/**
	 * Sets the sub class flag.
	 *
	 * @param b the new sub class flag
	 */
	public void setSubClassFlag(boolean b)
	{
		isSubClass=b;
	}
	
	/**
	 * Checks if is super class.
	 *
	 * @return true, if is super class
	 */
	public boolean isSuperClass() {
		return isSuperClass;
	}
	
	/**
	 * Sets the super class flag.
	 *
	 * @param b the new super class flag
	 */
	public void setSuperClassFlag(boolean b)
	{
		isSuperClass=b;
	}
	

	/**
	 * Instantiates a new class dec.
	 */
	public Class_dec() {
	}

	/* (non-Javadoc)
	 * @see packageModels.Modelable#getIdentifier()
	 */
	@Override
	public String getIdentifier() {
		return identifier;
	}

	/* (non-Javadoc)
	 * @see packageModels.Modelable#getDetails()
	 */
	@Override
	public String getDetails() {
		return details;
	}

	/* (non-Javadoc)
	 * @see packageModels.Modelable#setIdentifier(java.lang.String)
	 */
	@Override
	public void setIdentifier(String id) {
		identifier=id;
		
	}

	/* (non-Javadoc)
	 * @see packageModels.Modelable#setDetails(java.lang.String)
	 */
	@Override
	public void setDetails(String details) {
		this.details=details;
		
	}
	
	/**
	 * Sets the all classes types.
	 *
	 * @param s the new all classes types
	 */
	public void setAllClassesTypes(List<String> s) {
		this.allClassesTypes=s;
	}
	
	/**
	 * Gets the all classes types.
	 *
	 * @return the all classes types
	 */
	public List<String> getAllClassesTypes() {
		return this.allClassesTypes;
	}
	
	/**
	 * Sets the other operations.
	 *
	 * @param op the new other operations
	 */
	public void setOtherOperations(List<Operation> op) {
		this.operationOthersClasses=op;
	}
	
	/**
	 * Gets the other operations.
	 *
	 * @return the other operations
	 */
	public List<Operation> getOtherOperations() {
		return this.operationOthersClasses;
	}
	
	/**
	 * Sets the sub class.
	 *
	 * @param c the new sub class
	 */
	public void setSubClass(List<Class_dec> c) {
		this.subClass=c;
	}
	
	/**
	 * Gets the sub class.
	 *
	 * @return the sub class
	 */
	public List<Class_dec> getSubClass() {
		return this.subClass;
	}

	//Metricable methods
	
	/*
	use this for metrics
	get all classes from Parser
	ArrayList<Class_dec> allClasses = (ArrayList<Class_dec>) Parser.getAllClasses();
	*/
	
	/* (non-Javadoc)
	 * @see packageModels.Metricable#getAverageMethodArgumentCount()
	 */
	//ANA 1
	@Override
	public float getAverageMethodArgumentCount() {
		float count = 0;
		float arg = 0;
		if (this.isSubClass&&this.operations!=null)
		{
			if(this.operations.size()==0) 
			{
				count = 0;
			}
			else 
			{
				if(getLocalOperations(this)!=null) 
				{
					if(getLocalOperations(this).size()>0) 
					{
						for(int i=0; i<getLocalOperations(this).size(); i++) 
						{
							if(getLocalOperations(this).get(i).getArg_list()!=null) 
							{
								arg+=getLocalOperations(this).get(i).getArg_list().size();
							}
						}
						count = arg/getLocalOperations(this).size();
					}
					else {
						count = 0;
					}
				}
			}
		}
		else {
			if(this.operations!=null) 
			{
				if(this.operations.size()>0) 
				{
					for(int i=0; i<this.operations.size(); i++) 
					{
						if(this.operations.get(i).getArg_list()!=null) 
						{
							arg+=this.operations.get(i).getArg_list().size();
						}
					}
					count =arg/this.operations.size();
				}
				else {
					count=0;
				}
			}
		}
		return count;
	}

	/* (non-Javadoc)
	 * @see packageModels.Metricable#getMethodCount()
	 */
	//NOM 2
	@Override
	public int getMethodCount() {
		int count = 0;
		if(this.isSubClass) 
		{
			List<Operation> op = getInheritedOperations(this);
			List<Operation> loc = getLocalOperations(this);
			count = op.size()+loc.size();
		}
		else 
		{
			if(this.operations!=null) 
			{
				count = this.operations.size();
			}
		}
		return count;
	}

	
	/* (non-Javadoc)
	 * @see packageModels.Metricable#getAttributeCount()
	 */
	//NOA 3
	@Override
	public int getAttributeCount() {
		int count = 0;
		if(this.isSubClass) 
		{
			List<Data_Item> att = getInheritedAttributes(this);
			List<Data_Item> loc = getLocalAttributes(this);
			
			count = att.size()+loc.size();
		}
		else 
		{
			if(this.attributes!=null) {
				count = this.attributes.size();
			}
		}
		return count;
	}

	/* (non-Javadoc)
	 * @see packageModels.Metricable#getModelableArgumentCount()
	 */
	//ITC 4
	@Override
	public int getModelableArgumentCount() {
		int count = 0;
		if(this.operations!=null) {
			for(int i=0; i<this.operations.size(); i++) 
			{
				if(this.operations.get(i).getArg_list()!=null) 
				{
					for(int j=0; j<this.operations.get(i).getArg_list().size(); j++) 
					{
						String argType = this.operations.get(i).getArg_list().get(j).getType();
						for(int k=0; k<this.allClassesTypes.size(); k++) 
						{
							if(argType.equals(this.allClassesTypes.get(k))) 
							{
								count+=1;
							}
						}
						
					}
				}
			}
		}
		return count;
	}

	/* (non-Javadoc)
	 * @see packageModels.Metricable#getTimesUsedAsArgument()
	 */
	//ETC 5
	@Override
	public int getTimesUsedAsArgument() {
		int count =0;
		if(this.operationOthersClasses!=null) {
			for (int i=0; i<this.operationOthersClasses.size();i++) {
				if(this.operationOthersClasses.get(i).getArg_list()!=null) {
					List<Data_Item> args = this.operationOthersClasses.get(i).getArg_list();
					for (int j=0; j<args.size();j++) {
						String currentType =args.get(j).getType();
						if(this.getIdentifier().equals(currentType)) {
							count+=1;
						}
					}
				}
			}
		}
		
		
		return count;
	}

	/* (non-Javadoc)
	 * @see packageModels.Metricable#getAssociationCount()
	 */
	//CAC 6
	@Override
	public int getAssociationCount() {
		int count = 0;
		int countAsso = 0;
		int countAggr = 0;
		if(this.isSubClass) 
		{
			List<String> g = getInheritedGeneralizations(this);
			List<String> loc = getLocalGeneralizations(this);
			count = g.size()+loc.size();
		}
		else 
		{
			if(this.hasAggregations)	
			{
				countAggr = this.getAggrList().size();
			}
			if(this.hasAssociations) {
				countAsso = this.getAssoList().size();
			}
			count =countAsso+countAggr;
		}
		return count;
	}

	/* (non-Javadoc)
	 * @see packageModels.Metricable#getLongestPathLengthToRoot()
	 */
	//DIT 7
	@Override
	public int getLongestPathLengthToRoot() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see packageModels.Metricable#getLongestPathLengthtoLeaf()
	 */
	
	//CLD 8
	@Override
	public int getLongestPathLengthtoLeaf() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see packageModels.Metricable#getDirectSubClassCount()
	 */
	//NOC 9
	@Override
	public int getDirectSubClassCount() {
		int count = 0;
		if(this.isSuperClass) {
				count = this.subclasses.size();
		}
		return count;
	}

	/* (non-Javadoc)
	 * @see packageModels.Metricable#getSubClassCount()
	 */
	//NOD 10
	@Override
	public int getSubClassCount() {
		// TODO Auto-generated method stub
		int count = 0;
		//if(this.isSuperClass) {
			//count=numberSubClasses(this);
		//}	
		return count;
	}
	

	/**
	 * Number sub classes.
	 *
	 * @param c the c
	 * @return the int
	 */
	public static int numberSubClasses(Class_dec c) {
		int count=0;
		if (!c.isSuperClass) {
			count=0;
		}
		else {
			if(c.subClass.size()==1) {
				count = c.subClass.size();
				c =null;
			}
			else {
				for(int i=0; i<c.subClass.size();i++) {
					if(c.subClass.get(i).isSuperClass) {
						count+=numberSubClasses(c.subClass.get(i));
						System.out.println(count);
					}
				}
			}
			
			//count+=c.subClass.size();
		}
		return count;
	}
	
	
	

	/* (non-Javadoc)
	 * @see packageModels.Metricable#getAllMetrics()
	 */
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
	
	/* (non-Javadoc)
	 * @see packageModels.Metricable#getMethodArgumentCount()
	 */
	//Optionnal Helpers
	@Override
	public int getMethodArgumentCount() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * Gets the inherited attributes.
	 *
	 * @param c the c
	 * @return the inherited attributes
	 */
	public static List<Data_Item> getInheritedAttributes(Class_dec c){
		List<Data_Item> att = new ArrayList<Data_Item>();
		boolean equalFlag = true;
		boolean subClass1 = true;
		while(c.isSubClass()){
			if(subClass1){
				subClass1 =false;
			}
			else {
				if(c.getAttributes()!=null){
					for(int i=0; i<c.getAttributes().size(); i++) {
						for (int j=0; j<att.size(); j++) {
							if(att.get(j).getIdentifier().equals(c.getAttributes().get(i).getIdentifier())) {
								equalFlag=false;
							}
						}
						if(equalFlag) {
							att.add(c.getAttributes().get(i));
						}
					}
				}
			}
			
			c = c.getSuperclasse();
			if(!c.isSubClass) {
				if(c.getAttributes()!=null){
					for(int i=0; i<c.getAttributes().size(); i++) {
						for (int j=0; j<att.size(); j++) {
							if(att.get(j).getIdentifier().equals(c.getAttributes().get(i).getIdentifier())) {
								equalFlag=false;
							}
						}
						if(equalFlag) {
							att.add(c.getAttributes().get(i));
						}
					}
				}
			}
		}
		return att;
	}
	
	/**
	 * Gets the local attributes.
	 *
	 * @param c the c
	 * @return the local attributes
	 */
	public static List<Data_Item> getLocalAttributes(Class_dec c){
		List<Data_Item> att=getInheritedAttributes(c);
		List<Data_Item> loc = new ArrayList<Data_Item>();
		loc.addAll(c.getAttributes());
		
		for(int i=0; i<loc.size(); i++) {
			for (int j=0; j<att.size(); j++) {
				if(loc.get(i).getIdentifier().equals(att.get(j).getIdentifier())) {
					System.out.println("indice "+i+" je supprime attribut "+loc.get(i).getIdentifier());
					loc.remove(i);
				}
			}
		}
		
		return loc;
	}
	
	/**
	 * Gets the local operations.
	 *
	 * @param c the c
	 * @return the local operations
	 */
	public static List<Operation> getLocalOperations(Class_dec c){
		boolean flagLocal = true;
		List<Operation> op=getInheritedOperations(c);
		List<Operation> local = new ArrayList<Operation>();
		local.addAll(c.getOperations());
		
		if(op.size()>0) {
			for(int i=0; i<c.getOperations().size(); i++) {
				for (int j=0; j<op.size(); j++) {
					if(c.getOperations().get(i).getIdentifier().equals(op.get(j).getIdentifier())) {
						flagLocal = false;
					}
				}
				if(flagLocal) {
					local.add(c.getOperations().get(i));
				}
			}
		}
		return local;
	}
	
	/**
	 * Gets the inherited operations.
	 *
	 * @param c the c
	 * @return the inherited operations
	 */
	public static List<Operation> getInheritedOperations(Class_dec c){
		List<Operation> op = new ArrayList<Operation>();
		boolean equalFlag = true;
		boolean subClass1 = true;
		while(c.isSubClass()){
			if(subClass1){
				subClass1 =false;
			}
			else {
				if(c.getOperations()!=null){
					for(int i=0; i<c.getOperations().size(); i++) {
						for (int j=0; j<op.size(); j++) {
							if(op.get(j).getIdentifier().equals(c.getOperations().get(i).getIdentifier())) {
								equalFlag=false;
							}
						}
						if(equalFlag) {
							op.add(c.getOperations().get(i));
						}
					}
				}
			}
			
			c = c.getSuperclasse();
			if(!c.isSubClass) {
				if(c.getOperations()!=null){
					for(int i=0; i<c.getOperations().size(); i++) {
						for (int j=0; j<op.size(); j++) {
							if(op.get(j).getIdentifier().equals(c.getOperations().get(i).getIdentifier())) {
								equalFlag=false;
							}
						}
						if(equalFlag) {
							op.add(c.getOperations().get(i));
						}
					}
				}
			}
		}
		return op;
	}
	
	/**
	 * Gets the local generalizations.
	 *
	 * @param c the c
	 * @return the local generalizations
	 */
	public static List<String> getLocalGeneralizations(Class_dec c){
		List<String> g = getInheritedGeneralizations(c);
		List<String> loc = new ArrayList<String>();
		loc.addAll(c.getAssoList());
		loc.addAll(c.getAggrList());
		
		for(int i=0; i<loc.size(); i++) {
			for (int j=0; j<g.size(); j++) {
				if(loc.get(i).equals(g.get(j))) {
					loc.remove(i);
				}
			}
		}
		return loc;
	}
	
	/**
	 * Gets the inherited generalizations.
	 *
	 * @param c the c
	 * @return the inherited generalizations
	 */
	public static List<String> getInheritedGeneralizations(Class_dec c){
		List<String> g = new ArrayList<String>();
		boolean equalFlag = true;
		boolean subClass1 = true;
		while(c.isSubClass()){
			if(subClass1){
				subClass1 =false;
			}
			else {
				if(c.getAggrList()!=null){
					for(int i=0; i<c.getAggrList().size(); i++) {
						for (int j=0; j<g.size(); j++) {
							if(g.get(j).equals(c.getAggrList().get(i))) {
								equalFlag=false;
							}
						}
						if(equalFlag) {
							g.add(c.getAggrList().get(i));
						}
					}
				}
				if(c.getAssoList()!=null){
					for(int i=0; i<c.getAssoList().size(); i++) {
						for (int j=0; j<g.size(); j++) {
							if(g.get(j).equals(c.getAssoList().get(i))) {
								equalFlag=false;
							}
						}
						if(equalFlag) {
							g.add(c.getAssoList().get(i));
						}
					}
				}
			}
			
			c = c.getSuperclasse();
			if(!c.isSubClass) {
				if(c.getAggrList()!=null){
					for(int i=0; i<c.getAggrList().size(); i++) {
						for (int j=0; j<g.size(); j++) {
							if(g.get(j).equals(c.getAggrList().get(i))) {
								equalFlag=false;
							}
						}
						if(equalFlag) {
							g.add(c.getAggrList().get(i));
						}
					}
				}
				if(c.getAssoList()!=null){
					for(int i=0; i<c.getAssoList().size(); i++) {
						for (int j=0; j<g.size(); j++) {
							if(g.get(j).equals(c.getAssoList().get(i))) {
								equalFlag=false;
							}
						}
						if(equalFlag) {
							g.add(c.getAssoList().get(i));
						}
					}
				}
			}
		}
		return g;
	}	
}