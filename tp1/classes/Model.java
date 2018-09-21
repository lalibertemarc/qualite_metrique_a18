public class Model implements Modelable {

	private string identifier;
	private List<Class_dec> list_dec;
	private string details;
	private List<Association> associations;
	private List<Aggregation> aggregations;

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
		// TODO - implement Model.Model
		throw new UnsupportedOperationException();
	}

}