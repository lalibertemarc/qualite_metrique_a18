public class Operation implements Modelable, Returnable {

	private string identifier;
	private string type;
	private List<Data_item> arg_list;
	private string details;

	public List<Data_item> getArg_list() {
		return this.arg_list;
	}

	public void setArg_list(List<Data_item> arg_list) {
		this.arg_list = arg_list;
	}

	public Operation() {
		// TODO - implement Operation.Operation
		throw new UnsupportedOperationException();
	}

}