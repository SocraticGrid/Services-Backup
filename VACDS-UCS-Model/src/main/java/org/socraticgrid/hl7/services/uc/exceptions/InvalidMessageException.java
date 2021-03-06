package org.socraticgrid.hl7.services.uc.exceptions;

import java.io.Serializable;

import org.socraticgrid.hl7.services.uc.model.Message;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 16-Dec-2013 3:54:56 PM
 */
public class InvalidMessageException extends InvalidInputException implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidMessageException(){

	}
	
	/**
	 * @param fault
	 */
	public InvalidMessageException(String fault)
	{
		super(fault);
	
	}

	public InvalidMessageException(String fault, Message generatingMessage){
		super(fault);
		this.setGeneratingMessage(generatingMessage);
		this.setFault(fault);
	}

	/**
	 * @param fault
	 * @param service
	 * @param msg
	 */
	public InvalidMessageException(String fault, String service, Message msg)
	{
		super(fault, service, msg);
	
	}


}