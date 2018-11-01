package packageModels;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class Model.
 * Represents the whole class diagram.
 */
public class Model implements Modelable {

	/** The identifier. */
	private String identifier;
	
	/** The details. */
	private String details;
	
	/** The list dec. */
	private List<Class_dec> list_dec;
	
	/** The associations. */
	private List<Association> associations;
	
	/** The aggregations. */
	private List<Aggregation> aggregations;
	
	/** The sub class details. */
	private List<String> subClassDetails = new ArrayList<String>();
	
	/** The super class details. */
	private String superClassDetails;

	/**
	 * Gets the list dec.
	 *
	 * @return the list dec
	 */
	public List<Class_dec> getList_dec() {
		return this.list_dec;
	}

	/**
	 * Sets the list dec.
	 *
	 * @param list_dec the new list dec
	 */
	public void setList_dec(List<Class_dec> list_dec) {
		this.list_dec = list_dec;
	}

	/**
	 * Gets the associations.
	 *
	 * @return the associations
	 */
	public List<Association> getAssociations() {
		return this.associations;
	}

	/**
	 * Sets the associations.
	 *
	 * @param associations the new associations
	 */
	public void setAssociations(List<Association> associations) {
		this.associations = associations;
	}

	/**
	 * Gets the aggregations.
	 *
	 * @return the aggregations
	 */
	public List<Aggregation> getAggregations() {
		return this.aggregations;
	}

	/**
	 * Sets the aggregations.
	 *
	 * @param aggregations the new aggregations
	 */
	public void setAggregations(List<Aggregation> aggregations) {
		this.aggregations = aggregations;
	}

	/**
	 * Instantiates a new model.
	 */
	public Model() {
	}
	
	
	/**
	 * Sets the sub class details.
	 *
	 * @param sc the new sub class details
	 */
	public void setSubClassDetails(List<String> sc)
	{
		subClassDetails = sc;
	}
	
	/**
	 * Gets the sub class details.
	 *
	 * @return the sub class details
	 */
	public List<String> getSubClassDetails()
	{
		return subClassDetails;
	}
	
	/**
	 * Sets the super class details.
	 *
	 * @param sc the new super class details
	 */
	public void setSuperClassDetails(List<String> sc)
	{
		subClassDetails = sc;
	}
	
	/**
	 * Gets the super class details.
	 *
	 * @return the super class details
	 */
	public String getSuperClassDetails()
	{
		return superClassDetails;
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
	 * Converts the string subclass list in
	 * class_d
	 *
	 * 
	 */
	public void SetAllSubClasses() {
		List<String> subclasses=new ArrayList<String>();
		List<Class_dec> sc = new ArrayList<Class_dec>();
		for(int i=0 ; i<this.list_dec.size() ;i++)
		{
			if(this.list_dec.get(i).getSubclasses()!=null)
			{	
				subclasses = this.list_dec.get(i).getSubclasses();
			}
			for(int j=0; j<subclasses.size(); j++) {
				for(int k=0; k<this.list_dec.size(); k++) {
					if(subclasses.get(j).equals(this.list_dec.get(k).getIdentifier())) {
						sc.add(this.list_dec.get(k));
					}
				}
			}
			this.list_dec.get(i).setSubClass(sc);
		}	
	}
	
	/**
	 * Sets the sub classes flags for all class_dec.
	 */
	public void setSubClassesFlags(){
		for(int i=0 ; i<this.list_dec.size() ;i++)
		{
			if(this.list_dec.get(i).getSubclasses()!=null)
			{	
				this.list_dec.get(i).setSuperClassFlag(true);
				List<String> subclasses = this.list_dec.get(i).getSubclasses();
				
				for(int j =0; j<this.list_dec.size(); j++) 
				{
					for (int k = 0; k<subclasses.size(); k++) 
					{
						if(this.list_dec.get(j).getIdentifier().equals(subclasses.get(k)) ) 
						{
							this.list_dec.get(j).setSubClassFlag(true);
							this.list_dec.get(j).setSuperclasse(this.list_dec.get(i));
						}
					}
				}
			}
		}
	}
	
	/**
	 * Sets the all classes types.
	 *
	 */
	public void setAllClassesTypes() {
		List<String> s = new ArrayList<String>();
		for(int i=0 ; i<this.list_dec.size() ;i++)
		{
			s.add(this.list_dec.get(i).getIdentifier());
		}
		for(int i=0; i<this.list_dec.size() ;i++) {
			List<String> str=new ArrayList<String>();
			str.addAll(s);
			str.remove(this.list_dec.get(i).getIdentifier());
			this.list_dec.get(i).setAllClassesTypes(str);
		}
	}
	
	/**
	 * Sets the other operations.
	 */
	public void setOtherOperations() {
		List<Operation> op = new ArrayList<Operation>();
		for(int i=0; i<this.list_dec.size() ;i++) {
			if(this.list_dec.get(i).getOperations()!=null) {
				op.addAll(this.list_dec.get(i).getOperations());
			}
		}
		for(int i=0; i<this.list_dec.size() ;i++) {
			List<Operation> oP = new ArrayList<Operation>();
			oP.addAll(op);
			oP.remove(this.list_dec.get(i).getOperations());
			this.list_dec.get(i).setOtherOperations(oP);
		}	
	}
	
	/**
	 * Sets the aggregations.
	 */
	public void setAggregations(){
		boolean condition1 = false;
		boolean condition2 = false;
		for(int i=0; i< this.list_dec.size(); i++) {
			List<String> agg = new ArrayList<String>();
			for (int j =0; j<this.aggregations.size(); j++) {
				condition1 =this.list_dec.get(i).getIdentifier().
						equals(this.aggregations.get(j).getContainer().getClass_dec());
				condition2 =this.list_dec.get(i).getIdentifier().
						equals(this.aggregations.get(j).getParts().getClass_dec()) ;
				if(condition1||condition2) {
					agg.add(this.aggregations.get(j).getIdentifier());
				}
			}
			if(agg.size()>0) {
				this.list_dec.get(i).setAggrList(agg);
				this.list_dec.get(i).setAggrFlag(true);
			}
			
	
			
		}
	}
	
}