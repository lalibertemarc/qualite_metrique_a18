package packageModels;

import java.util.*;

public interface Metricable {
	
	//ANA
	float getAverageMethodArgumentCount();
		
	//NOM
	int getMethodCount();
	
	//NOA
	int getAttributeCount();
	
	//ITC
	int getModelableArgumentCount();
	
	//ETC
	int getTimesUsedAsArgument();
	
	//CAC
	int getAssociationCount();
	
	//DIT
	int getLongestPathLengthToRoot();
	
	//DLC
	int getLongestPathLengthtoLeaf();
	
	//NOC
	int getDirectSubClassCount();
	
	//NOD
	int getSubClassCount();
	
	List<String> getAllMetrics();
	
	//helpers
	int getMethodArgumentCount();
	
	
	
	
	

}
