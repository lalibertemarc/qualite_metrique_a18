package packageModels;

import java.util.ArrayList;
import java.util.List;

public class Class_dec implements Modelable,Metricable {

	private String identifier;
	private String details;
	private List<Data_Item> attributes;
	private List<Operation> operations;
	private List<String> subclasses;
	private List<Class_dec> subClass;
	private boolean hasAssociations = false;
	private boolean hasAggregations = false;
	private boolean isSuperClass = false;
	private boolean isSubClass = false;
	private List<String> allClassesTypes;
	private List<String> assoList = new ArrayList<String>();
	private List<String> aggrList = new ArrayList<String>();
	private Class_dec superClasse;
	private List<Operation> operationOthersClasses;

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

	
	public Class_dec getSuperclasse() {
		return this.superClasse;
	}

	public void setSuperclasse(Class_dec superclasse) {
		this.superClasse = superclasse;
	}
	
	
	
	public boolean hasAggregations() {
		return hasAggregations;
	}
	
	public void setAggrFlag(boolean b)
	{
		hasAggregations=b;
	}

	public void setAggrList(List<String> s)
	{
		this.aggrList = s;
	}
	
	public List<String> getAggrList()
	{
		return aggrList;
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
	
	public boolean isSubClass() {
		return isSubClass;
	}
	public void setSubClassFlag(boolean b)
	{
		isSubClass=b;
	}
	
	public boolean isSuperClass() {
		return isSuperClass;
	}
	public void setSuperClassFlag(boolean b)
	{
		isSuperClass=b;
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
	
	public void setAllClassesTypes(List<String> s) {
		this.allClassesTypes=s;
	}
	
	public List<String> getAllClassesTypes() {
		return this.allClassesTypes;
	}
	
	public void setOtherOperations(List<Operation> op) {
		this.operationOthersClasses=op;
	}
	
	public List<Operation> getOtherOperations() {
		return this.operationOthersClasses;
	}
	
	public void setSubClass(List<Class_dec> c) {
		this.subClass=c;
	}
	
	public List<Class_dec> getSubClass() {
		return this.subClass;
	}

	//Metricable methods
	
	/*
	use this for metrics
	get all classes from Parser
	ArrayList<Class_dec> allClasses = (ArrayList<Class_dec>) Parser.getAllClasses();
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

	//CAC 6
	@Override
	public int getAssociationCount() {
		// TODO Auto-generated method stub
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

	//DIT 7
	@Override
	public int getLongestPathLengthToRoot() {
		// TODO Auto-generated method stub
		return 0;
	}

	//CLD 8
	@Override
	public int getLongestPathLengthtoLeaf() {
		// TODO Auto-generated method stub
		return 0;
	}

	//NOC 9
	@Override
	public int getDirectSubClassCount() {
		int count = 0;
		if(this.isSuperClass) {
				count = this.subclasses.size();
		}
		return count;
	}

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
	
	public static List<Operation> getLocalOperations(Class_dec c){
		List<Operation> op=getInheritedOperations(c);
		List<Operation> local = new ArrayList<Operation>();
		local.addAll(c.getOperations());
		
		for(int i=0; i<local.size(); i++) {
			for (int j=0; j<op.size(); j++) {
				if(local.get(i).getIdentifier().equals(op.get(j).getIdentifier())) {
					local.remove(i);
				}
			}
		}
		return local;
	}
	
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