package packageModels;


import java.util.List;

public class Model implements Modelable {

	private String identifier;
	private String details;
	private List<Class_dec> list_dec;
	private List<Association> associations;
	private List<Aggregation> aggregations;
	private String subClassDetails;

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