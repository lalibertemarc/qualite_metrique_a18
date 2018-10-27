package packageModels;


import java.util.ArrayList;
import java.util.List;

public class Model implements Modelable {

	private String identifier;
	private String details;
	private List<Class_dec> list_dec;
	private List<Association> associations;
	private List<Aggregation> aggregations;
	private String subClassDetails;
	private String superClassDetails;

	public List<Class_dec> getList_dec() {
		return this.list_dec;
	}

	public void setList_dec(List<Class_dec> list_dec) {
		this.list_dec = list_dec;
	}

	public List<Association> getAssociations() {
		return this.associations;
	}

	public void setAssociations(List<Association> associations) {
		this.associations = associations;
	}

	public List<Aggregation> getAggregations() {
		return this.aggregations;
	}

	public void setAggregations(List<Aggregation> aggregations) {
		this.aggregations = aggregations;
	}

	public Model() {
	}
	
	
	public void setSubClassDetails(String sc)
	{
		subClassDetails = sc;
	}
	
	public String getSubClassDetails()
	{
		return subClassDetails;
	}
	public void setSuperClassDetails(String sc)
	{
		subClassDetails = sc;
	}
	
	public String getSuperClassDetails()
	{
		return superClassDetails;
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
	public void setSubClasses(){
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
	
	public void getAllClassesTypes() {
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
	
}