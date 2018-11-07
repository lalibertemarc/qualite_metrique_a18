package packageModels;

import java.util.*;

/**
 * The Interface Metricable.
 * Used to calculate class_dec metrics
 */
public interface Metricable {
	
	/**
	 * Gets the average method argument count.
	 *
	 * @return the average method argument count
	 */
	//ANA
	float getAverageMethodArgumentCount();
		
	/**
	 * Gets the method count.
	 *
	 * @return the method count
	 */
	//NOM
	int getMethodCount();
	
	/**
	 * Gets the attribute count.
	 *
	 * @return the attribute count
	 */
	//NOA
	int getAttributeCount();
	
	/**
	 * Gets the count for each Modelable classes used as arguments.
	 *
	 * @return the modelable argument count
	 */
	//ITC
	int getModelableArgumentCount();
	
	/**
	 * Gets the times class_dec used as argument.
	 *
	 * @return the times used as argument
	 */
	//ETC
	int getTimesUsedAsArgument();
	
	/**
	 * Gets the association count.
	 *
	 * @return the association count
	 */
	//CAC
	int getAssociationCount();
	
	/**
	 * Gets the longest path length to root.
	 *
	 * @return the longest path length to root
	 */
	//DIT
	int getLongestPathLengthToRoot();
	
	/**
	 * Gets the longest path lengthto leaf.
	 *
	 * @return the longest path lengthto leaf
	 */
	//DLC
	int getLongestPathLengthtoLeaf();
	
	/**
	 * Gets the direct sub class count.
	 *
	 * @return the direct sub class count
	 */
	//NOC
	int getDirectSubClassCount();
	
	/**
	 * Gets the sub class count.
	 *
	 * @return the sub class count
	 */
	//NOD
	int getSubClassCount();
	
	/**
	 * Gets the all metrics.
	 *
	 * @return the all metrics
	 */
	List<String> getAllMetrics();
	
	/**
	 * Gets the method argument count.
	 *
	 * @return the method argument count
	 */
	//helpers
	int getMethodArgumentCount();

}
