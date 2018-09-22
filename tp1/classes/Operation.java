package packageModels;

import java.util.List;

public class Operation implements Modelable, Returnable {

	private String identifier;
	private String type;
	private List<Data_Item> arg_list;
	private String details;

	public List<Data_Item> getArg_list() {
		return this.arg_list;
	}

	public void setArg_list(List<Data_Item> arg_list) {
		this.arg_list = arg_list;
	}

	public Operation() {
		
	}

	@Override
	public String getType() {
		return type;
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
		this.identifier = id;		
	}

	@Override
	public void setDetails(String details) {
		this.details = details;		
	}

	@Override
	public void setType(String type) {
		this.type = type;		
	}


}