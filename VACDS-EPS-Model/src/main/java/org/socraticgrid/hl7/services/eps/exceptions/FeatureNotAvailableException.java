package org.socraticgrid.hl7.services.eps.exceptions;

import java.io.Serializable;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 04-Jan-2014 6:15:19 PM
 */
public class FeatureNotAvailableException extends PubSubException implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String canonicFeatureName;

	public FeatureNotAvailableException(){

	}

	public FeatureNotAvailableException(String message) {
		super(message);
	
	}

	public FeatureNotAvailableException(String message, String featureName) {
		super(message);
		canonicFeatureName = featureName;
	}

	public String getCanonicFeatureName(){
		return canonicFeatureName;
	}
	/**
	 * 
	 * @param newVal
	 */
	public void setCanonicFeatureName(String newVal){
		canonicFeatureName = newVal;
	}
	
}