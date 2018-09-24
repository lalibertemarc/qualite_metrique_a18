package testParsing;

import java.util.List;

import packageModels.Aggregation;
import packageModels.Association;
import packageModels.Class_dec;
import packageModels.Data_Item;
import packageModels.Model;
import packageModels.Operation;



public class PrinterHelper {
	
	public static void printModel(Model m)
	{
		System.out.println("MODEL Name: "+m.getIdentifier());
		System.out.println("");
		//System.out.println("MODEL Details: "+m.getDetails());
		
		printClasses(m.getList_dec());
		printAssociation(m.getAssociations());
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
			System.out.println("Class Details:\n"+l.get(i).getDetails());
			
		}
	}
	
	private static void printAttributes(List<Data_Item> l)
	{
		System.out.println("ATTRIBUTES");
		for(int i=0;i<l.size();i++)
		{
			System.out.println("ATT name: "+l.get(i).getIdentifier());
			System.out.println("ATT type: "+l.get(i).getType());
			System.out.println("ATT details: "+l.get(i).getDetails());
			System.out.println("");
		
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
	
	private static void printAssociation(List<Association> l) {
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

}
