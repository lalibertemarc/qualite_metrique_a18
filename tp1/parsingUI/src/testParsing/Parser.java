package testParsing;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import packageModels.Aggregation;
import packageModels.Association;
import packageModels.Class_dec;
import packageModels.Data_Item;
import packageModels.Model;
import packageModels.Modelable;
import packageModels.Multiplicity;
import packageModels.Operation;
import packageModels.ParsingError;
import packageModels.Role;

public class Parser {
	static String _mainFile;
	static boolean isFileCorrupt;
	static String message;
	static Model outputModel;
	
	/*TODO
	 * redo regex for no attributes
	 * 
	 * */
	
	public static Modelable getModel(String input)
	{
		if(input.length()==0){
			message = "Empty File.";
			return new ParsingError(message);
		}
		_mainFile = input;
		outputModel = new Model();
		
	
		outputModel.setIdentifier(getModelId());
	
		if(!isFileCorrupt)
			outputModel.setDetails(_mainFile);
		else
		{
			isFileCorrupt=false;
			return new ParsingError(message);		
		}
		if(!isFileCorrupt)
			outputModel.setList_dec(getClasses());
		else
		{
			isFileCorrupt=false;
			return new ParsingError(message);		
		}
		if(!isFileCorrupt)
			outputModel.setAssociations(getAssociations());
		else
		{
			isFileCorrupt=false;
			return new ParsingError(message);		
		}
		if(!isFileCorrupt)
			outputModel.setAggregations(getAggregations());
		else
		{
			isFileCorrupt=false;
			return new ParsingError(message);		
		}
		
		return outputModel;
	}




	private static String getModelId() {
		Pattern modelPattern = Pattern.compile("MODEL (.*)\n");
		Matcher matcher = modelPattern.matcher(_mainFile);
		String id = "";
		while(matcher.find())
        {
			if(matcher.group().length()<1 || !id.equals("") || matcher.group(1).equals(""))
			{
				isFileCorrupt = true;
				message="Malformed model name";
				return null;
				
			}
			
            id=matcher.group(1);
        }
		if(id.equals(""))
		{
			isFileCorrupt = true;
			message="Empty model name";
			return null;
			
		}
		return id;
	}
	
	private static List<Class_dec> getClasses()
	{
		List<Class_dec> output = new ArrayList<Class_dec>();
		Pattern classPattern = Pattern.compile("CLASS (.*)\n");
		Matcher matcher = classPattern.matcher(_mainFile);
		while(matcher.find())
        {
			if(matcher.group(1).equals(""))
			{
				isFileCorrupt = true;
				message="Empty class name";
				return null;
				
			}
			
			String match = matcher.group(1);
			String[] idAr = match.split(" ");
			if(idAr.length>1)
				continue;
			Class_dec newClass = new Class_dec();
			String id= idAr[0];
			
			boolean check = checkForClassDuplicates(output, id);
			if(!check)
			{
				isFileCorrupt = true;
				message="Class " + id+" already declared";
				return null;
			}
			
			newClass.setIdentifier(id);
			newClass.setDetails(getClassDetail(id));
			newClass.setAttributes(getClassAttributes(id));
			newClass.setOperations(getClassOperations(id));
			newClass.setSubclasses(getSubclasses(id));
			
			output.add(newClass);
        }
		
		//no classes
		if(output.size()==0)
		{
			isFileCorrupt = true;
			message="No classes declared";
			return null;
			
		}
		return output;
	}
	
	private static boolean checkForClassDuplicates(List<Class_dec> output, String id) {
		
		for(int i=0;i<output.size();i++)
		{
			if(output.get(i).getIdentifier().equals(id))
				return false;
		}
		return true;
	}


	private static List<String> getSubclasses(String id) {
		List<String> output = new ArrayList<String>();
		String regex = "GENERALIZATION "+id+"\\n(.*)SUBCLASSES(.*)\\n;";
		Pattern subClassesPatt = Pattern.compile(regex);
		Matcher matcher = subClassesPatt.matcher(_mainFile);
		
		while(matcher.find())
		{
			if(matcher.group(2).equals("") || matcher.group(2).equals(" ") || matcher.group(2).equals(" \n"))
			{
				isFileCorrupt = true;
				message="Malformed subclasses name, subclasses name cannot be empty.";
				return null;
				
			}
			
			outputModel.setSubClassDetails("<html>"+matcher.group().replaceAll("\n","<br>")+"</html>");
			
			String[] classes = matcher.group(2).split(", ");
			if (classes.length>0) {
				for(int i = 0 ;i<classes.length;i++)
				{
					output.add(classes[i]);
				}
			}	
		}
		
		if(output.size()==0)
			return null;
		return output;
	}

	
	private static List<Data_Item> getClassAttributes(String id) {
		List<Data_Item> output = new ArrayList<Data_Item>();
		String regex = "CLASS "+ id + "\\nATTRIBUTES\\n((.+\\n)+)OPERATIONS";
		Pattern classAttributes = Pattern.compile(regex);
		Matcher matcher = classAttributes.matcher(_mainFile);
		
		while(matcher.find())
		{
			String[] datAr = matcher.group(1).split("\n");
			for(int i=0;i<datAr.length;i++)
			{
				String item = datAr[i].replaceAll("\\s+","");
				Data_Item attribute = getDataItem(item);
				output.add(attribute);
			}	
		}
		
		return output;
	}
	
	private static List<Operation> getClassOperations(String id) {
		List<Operation> output = new ArrayList<Operation>();
		String regex = "CLASS "+ id + "\\nATTRIBUTES\\n((.+\\n)+)OPERATIONS\\n((.+\\n)+)";
		Pattern classOperations = Pattern.compile(regex);
		Matcher matcher = classOperations.matcher(_mainFile);
		while(matcher.find())
		{
			String[] opAr = matcher.group(3).split("\n");
			for(int i=0;i<opAr.length;i++)
			{
				Operation newOp = new Operation();
				String item = opAr[i];
				
				if(item.equals(";"))
					continue;
				String regex2 = "(.*):(.*)";
				Pattern opFinder =Pattern.compile(regex2);
				Matcher opMatcher = opFinder.matcher(item);
				
				String name ="";
				String type = "";
				if(opMatcher.find())
				{
					name = opMatcher.group(1).replace("     ", "");
					type = opMatcher.group(2).replace(",", "");
				}
				
				if(name.equals("") || type.equals("") || name.equals(" ") || type.equals(" "))
				{
					isFileCorrupt = true;
					message="Malformed operation declaration, name or type cannot be empty";
					return null;
				}
				newOp.setArg_list(getOpArgs(name));
				newOp.setIdentifier(name);
				newOp.setDetails(item);
				newOp.setType(type);
				output.add(newOp);
			}
		}
		
		return output;
	}

	private static List<Data_Item> getOpArgs(String string) {
		String regex = "\\((.*?)\\)";
		Pattern opArgsPattern = Pattern.compile(regex);
		Matcher matcher = opArgsPattern.matcher(string);
		List<Data_Item> output = new ArrayList<Data_Item>();
		
		if(matcher.find())
		{
			if(matcher.group(1).equals(""))
				return null;
			String[] datAr = matcher.group(1).split(",");
			for(int i=0;i<datAr.length;i++)
			{
				String item = datAr[i].replaceAll("\\s+","");
				Data_Item attribute = getDataItem(item);
				output.add(attribute);
			}
		}
		return output;
	}

	
	private static Data_Item getDataItem(String input)
	{
		Data_Item attribute = new Data_Item();
		String[] itemAr = input.split(":");
		
		if(itemAr.length<2 || itemAr[0].equals("") || itemAr[1].equals(""))
		{
			isFileCorrupt = true;
			message="Malformed data item";
			return null;
		}
		attribute.setIdentifier(itemAr[0]);
		attribute.setType(itemAr[1].replaceAll(",",""));
		attribute.setDetails(itemAr[0] + " : "+itemAr[1].replaceAll(",",""));
		return attribute;
	}
	
	private static String getClassDetail(String id)
	{
		String regex = "CLASS "+ id+"\\nATTRIBUTES\\n((.+\\n)+)OPERATIONS\\n((.+\\n)+)";
		Pattern classDetails = Pattern.compile(regex);
		Matcher matcher = classDetails.matcher(_mainFile);
		String details = "";
		
		if(matcher.find())
		{
			details = matcher.group();
		}
			
		return details;	
	}
	
	private static List<Association> getAssociations()
	{
		String regex = "RELATION (.*)\\n(.*)\\n(.*)\\n(.*)\\n";
		Pattern assoPattern = Pattern.compile(regex);
		Matcher matcher = assoPattern.matcher(_mainFile);
		List<Association> output = new ArrayList<Association>();
		
		while(matcher.find())
		{
			Association newAsso = new Association();
			String assoId = matcher.group(1).replaceAll(" ", "");
			newAsso.setIdentifier(assoId);
			Role role1 = getRole(matcher.group(3));
			Role role2 = getRole(matcher.group(4));
			newAsso.setRole1(role1);			
			newAsso.setRole2(role2);
			
			if(role1 == null || role2 == null)
			{
				isFileCorrupt = true;
				message="Malformed role declaration";
				return null;	
			}
			if(role1.getMultiplicity() == null || role2.getMultiplicity() == null)
			{
				isFileCorrupt = true;
				message="Mutliplicity does not exists";
				return null;
			}
			setClassAssociations(assoId, role1, role2);
			newAsso.setDetails(matcher.group());
			output.add(newAsso);
		}
		
		return output;
	}
	
	private static void setClassAssociations(String assoId, Role role1, Role role2) {
		
		String name1 = role1.getClass_dec();
		String name2 = role2.getClass_dec();
		
		for(int i=0;i<outputModel.getList_dec().size();i++)
		{
			String classId = outputModel.getList_dec().get(i).getIdentifier();
			if(outputModel.getList_dec().get(i).getIdentifier().equals(name1))
			{
				outputModel.getList_dec().get(i).setAssoFlag(true);
				outputModel.getList_dec().get(i).addAssoToList("(R) "+ assoId);
			}
			if(outputModel.getList_dec().get(i).getIdentifier().equals(name2))
			{
				outputModel.getList_dec().get(i).setAssoFlag(true);
				outputModel.getList_dec().get(i).addAssoToList("(R) "+ assoId);
			}
		}
		
	}


	private static Role getRole(String input)
	{
		String regex = "CLASS (.*) (.*)";
		Pattern rolePattern = Pattern.compile(regex);
		Matcher matcher = rolePattern.matcher(input);
		
		Role role = null;
		if(matcher.find())
		{
			role = new Role();
			String classdec = matcher.group(1);
			if(classdec.equals("") || classdec==null)
			{
				isFileCorrupt = true;
				message="Malformed role declaration";
				return null;
			}
			role.setClass_dec(classdec);
			
			Multiplicity mul = getMultiplicity(matcher.group(2).replaceAll(",",""));
			role.setMultiplicity(mul);
		}
		return role;
	}

	
	private static Multiplicity getMultiplicity(String input) {
		
		switch(input)
		{
		case "ONE":
			return Multiplicity.ONE;
		case "MANY":
			return Multiplicity.MANY;
		case "ONE_OR_MANY":
			return Multiplicity.ONE_OR_MANY;
		case "OPTIONALLY_ONE":
			return Multiplicity.OPTIONALLY_ONE;
		case "UNDEFINED":
			return Multiplicity.UNDEFINED;
		default:
			return null;
		}
	}
	
	private static List<Aggregation> getAggregations() {
		String regex = "AGGREGATION(.*)\\n(.*)\\n(.*)\\n(.*)\\n(.*)\\n;";
		Pattern aggPattern = Pattern.compile(regex);
		Matcher matcher = aggPattern.matcher(_mainFile);
		List<Aggregation> output = new ArrayList<Aggregation>();
		
		while(matcher.find())
		{
			Aggregation aggregation = new Aggregation();
			Role container = getRole(matcher.group(3));
			Role parts = getRole(matcher.group(5));
			
			aggregation.setContainer(getRole(matcher.group(3)));
			aggregation.setParts(getRole(matcher.group(5)));
			aggregation.setDetails(matcher.group());
			setClassAggregations(container, parts);
			output.add(aggregation);
		}
		
		return output;
	}
	
	private static void setClassAggregations(Role container, Role parts) {
		
		String name1 = container.getClass_dec();
		String name2 = parts.getClass_dec();
		
		for(int i=0;i<outputModel.getList_dec().size();i++)
		{
			if(outputModel.getList_dec().get(i).getIdentifier().equals(name1))
			{
				outputModel.getList_dec().get(i).setAggrFlag(true);
				outputModel.getList_dec().get(i).addAssoToList("(A) "+ parts);
			}
			if(outputModel.getList_dec().get(i).getIdentifier().equals(name2))
			{
				outputModel.getList_dec().get(i).setAggrFlag(true);
				outputModel.getList_dec().get(i).addAggrToList("(A) "+ container);
			}
		}
		
	}
	


	//helper classes
	public static Class_dec findClassById(String name)
	{
		for(int i=0 ; i<outputModel.getList_dec().size() ;i++)
		{
			if(outputModel.getList_dec().get(i).getIdentifier().equals(name))
			{
				return outputModel.getList_dec().get(i);
			}
		}
		return null;
	}
	
	public static List<Class_dec> getAllClasses()
	{
		return outputModel.getList_dec();
	}			
}
