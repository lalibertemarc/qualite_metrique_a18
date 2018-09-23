package testParsing;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import packageModels.Class_dec;
import packageModels.Data_Item;
import packageModels.Model;
import packageModels.Operation;

public class Parser {
	static String _mainFile;
	static boolean isFileCorrupt;
	
	static Pattern modelPattern = Pattern.compile("MODEL (.*)\n");
	static Pattern classPattern = Pattern.compile("CLASS (.*)\n");
	
	
	public static Model getModel(String input)
	{
		_mainFile = input;
		Model outputModel = new Model();
		outputModel.setDetails(_mainFile);
		
		outputModel.setIdentifier(getModelId());
		outputModel.setList_dec(getClasses());
		
		return outputModel;
	}

	private static void checkForCorruption(String input)
	{
		if(input.equals(""))
			isFileCorrupt=true;
		else
			isFileCorrupt=false;
			
	}
	
	private static String getModelId() {
		Matcher matcher = modelPattern.matcher(_mainFile);
		String id = "";
		while(matcher.find())
        {
            id=matcher.group(1);
        }
		
		checkForCorruption(id);
		return id;
	}
	
	private static List<Class_dec> getClasses()
	{
		List<Class_dec> output = new ArrayList<Class_dec>();
		
		Matcher matcher = classPattern.matcher(_mainFile);
		while(matcher.find())
        {
			
			String match = matcher.group(1);
			String[] idAr = match.split(" ");
			if(idAr.length>1)
				continue;
			Class_dec newClass = new Class_dec();
			String id= idAr[0];
			newClass.setIdentifier(id);
			newClass.setDetails(getClassDetail(id));
			newClass.setAttributes(getClassAttribues(id));
			newClass.setOperations(getClassOperations(id));
			
			output.add(newClass);
          
        }
		
		return output;
	}
	
	private static List<Operation> getClassOperations(String id) {
		List<Operation> output = new ArrayList<Operation>();
		String regex = "CLASS "+ id + "\\nATTRIBUTES\\n((.+\\n)+)OPERATIONS\\n((.+\\n)+)";
		Pattern classOperations = Pattern.compile(regex);
		Matcher matcher = classOperations.matcher(_mainFile);
		if(matcher.find())
		{
			String[] opAr = matcher.group(3).split("\n");
			for(int i=0;i<opAr.length;i++)
			{
				Operation newOp = new Operation();
				String item = opAr[i].replaceAll("\\s+","");
				//System.out.println(item);
				if(item.equals(";"))
					continue;
				String regex2 = "(.*):(.*)";
				Pattern opFinder =Pattern.compile(regex2);
				Matcher opMatcher = opFinder.matcher(item);
				
				String name ="";
				String type = "";
				if(opMatcher.find())
				{
					name = opMatcher.group(1);
					type = opMatcher.group(2);
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

	private static List<Data_Item> getClassAttribues(String id) {
		List<Data_Item> output = new ArrayList<Data_Item>();
		String regex = "CLASS "+ id + "\\nATTRIBUTES\\n((.+\\n)+)OPERATIONS";
		Pattern classAttributes = Pattern.compile(regex);
		Matcher matcher = classAttributes.matcher(_mainFile);
		
		if(matcher.find())
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

	
	private static Data_Item getDataItem(String input)
	{
		Data_Item attribute = new Data_Item();
		String[] itemAr = input.split(":");
		attribute.setIdentifier(itemAr[0]);
		attribute.setType(itemAr[1].replaceAll(",",""));
		attribute.setDetails(itemAr[0] + " : "+itemAr[1].replaceAll(",",""));
		return attribute;
	}
	
	private static String getClassDetail(String id)
	{
		String regex = "CLASS "+ id+"\\nATTRIBUTES\\n((.+\\n)+)OPERATIONS\\n((.+\\n)+);";
		Pattern classDetails = Pattern.compile(regex);
		Matcher matcher = classDetails.matcher(_mainFile);
		String details = "";
		
		if(matcher.find())
		{
			details = matcher.group();
		}
		
		return details;
		
	}
	

}
