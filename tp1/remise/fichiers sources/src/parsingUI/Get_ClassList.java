package parsingUI;

import java.util.ArrayList;
import java.util.List;

import packageModels.Aggregation;
import packageModels.Association;
import packageModels.Class_dec;
import packageModels.Data_Item;
import packageModels.Model;
import packageModels.Operation;
import packageModels.Role;

public class Get_ClassList {
	
private static ArrayList<String> s;

	public static ArrayList<String> stringClasses(List<Class_dec> l)
	{
		s = null;
		for(int i=0;i<l.size();i++)
		{
			s.add(l.get(i).getIdentifier());
		}
		return s;
	}
}
