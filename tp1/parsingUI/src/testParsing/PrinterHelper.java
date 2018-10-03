package testParsing;

import java.util.List;

import packageModels.Aggregation;
import packageModels.Association;
import packageModels.Class_dec;
import packageModels.Data_Item;
import packageModels.Model;
import packageModels.Modelable;
import packageModels.Operation;
import packageModels.ParsingError;
import packageModels.Role;



public class PrinterHelper {
	
	public static void printModel(Modelable m)
	{
		if(m instanceof ParsingError) {
			System.out.println(((ParsingError)m).getMessage());
			return;
		}
			
		System.out.println("MODEL Name: "+m.getIdentifier());
		System.out.println("");
		//System.out.println("MODEL Details: "+m.getDetails());
		
		printClasses(((Model)m).getList_dec());
		printAssociation(((Model)m).getAssociations());
		printAggregation(((Model)m).getAggregations());
		//System.out.println(m.getDetails());
	}
	

	private static void printClasses(List<Class_dec> l)
	{
		for(int i=0;i<l.size();i++)
		{
			System.out.println("Class Name: "+l.get(i).getIdentifier());
			System.out.println("");
			printAttributes(l.get(i).getAttributes());
			System.out.println("");
			printOperations(l.get(i).getOperations());
			printSubclasses(l.get(i).getSubclasses());
			System.out.println("Class Details:\n"+l.get(i).getDetails());
			System.out.println("\n");
			
		}
	}
	
	private static void printSubclasses(List<String> l) {
		System.out.println("SUBCLASSES");
		if(l==null)
		{
			System.out.println("NO SUBCLASSES");
			System.out.println("");
			return;
		}
		for(int i=0;i<l.size();i++)
		{
			System.out.println(l.get(i));
		}
		System.out.println("");
	}


	private static void printAttributes(List<Data_Item> l)
	{
		System.out.println("ATTRIBUTES");
		for(int i=0;i<l.size();i++)
		{
			System.out.println("ATT name: "+l.get(i).getIdentifier());
			System.out.println("ATT type: "+l.get(i).getType());
			System.out.println("ATT details: "+l.get(i).getDetails());
			System.out.println("\n");
		
		}
	}
	
	private static void printOperations(List<Operation>l)
	{
		System.out.println("OPERATIONS");
		for(int i=0;i<l.size();i++)
		{
			System.out.println("OP name: "+l.get(i).getIdentifier());
			System.out.println("OP type: "+l.get(i).getType());
			System.out.println("OP details: "+l.get(i).getDetails());
			if(l.get(i).getArg_list() != null)
			{
				printArgList(l.get(i).getArg_list());
			}else
				System.out.println("NO Args");
				System.out.println("");
			
			
		}
	}
	
	private static void printArgList(List<Data_Item> l)
	{
		System.out.println("ARG LIST");
		for(int i=0;i<l.size();i++)
		{
			System.out.println("Arg name: "+l.get(i).getIdentifier());
			System.out.println("Arg type: "+l.get(i).getType());
			System.out.println("Arg details: "+l.get(i).getDetails());
			System.out.println("");
		
		}
	}
	
	private static void printAssociation(List<Association> l) 
	{
		System.out.println("Associations");
		for(int i=0;i<l.size();i++)
		{
			System.out.println("Asso name: "+l.get(i).getIdentifier());
			System.out.println("Asso details: "+l.get(i).getDetails());
			System.out.println("Asso role1 : "+l.get(i).getRole1().getClass_dec()+" : "+l.get(i).getRole1().getMultiplicity());
			System.out.println("Asso role2 : "+l.get(i).getRole2().getClass_dec()+" : "+l.get(i).getRole2().getMultiplicity());
			System.out.println("");	
		}
	
	}
	
	private static void printAggregation(List<Aggregation> l) {
		System.out.println("Aggregations");
		for(int i=0;i<l.size();i++)
		{
			System.out.println("CONTAINER");
			Role container = l.get(i).getContainer();
			System.out.println(container.getClass_dec());
			System.out.println(container.getMultiplicity());
			System.out.println("Parts");
			Role parts = l.get(i).getParts();
			System.out.println(parts.getClass_dec());
			System.out.println(parts.getMultiplicity());
		}
		
	}
	
	
	

}
