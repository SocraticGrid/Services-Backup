package org.socraticgrid.hl7.services.eps.exceptions;

import java.io.Serializable;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 04-Jan-2014 6:15:19 PM
 */
public class IncompleteDataException extends InvalidDataException implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String missingElementName;

	public IncompleteDataException(){

	}

	public IncompleteDataException(String string) {
		super(string);
		
	}

	public IncompleteDataException(String string, String messingElementName) {
		super(string);
		this.missingElementName = messingElementName;
	}

	public String getMissingElementName(){
		return missingElementName;
	}
	/**
	 * 
	 * @param newVal
	 */
	public void setMissingElementName(String newVal){
		missingElementName = newVal;
	}

}