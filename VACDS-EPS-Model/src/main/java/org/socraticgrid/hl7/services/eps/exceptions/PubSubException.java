package org.socraticgrid.hl7.services.eps.exceptions;

import java.io.Serializable;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 04-Jan-2014 6:15:20 PM
 */
public class PubSubException extends Exception implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String message;

	public PubSubException(){

	}
	
	public PubSubException(String message)
	{
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setMessage(String newVal){
		message = newVal;
	}

}