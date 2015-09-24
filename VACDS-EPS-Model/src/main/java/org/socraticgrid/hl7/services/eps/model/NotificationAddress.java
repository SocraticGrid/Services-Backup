package org.socraticgrid.hl7.services.eps.model;

import java.io.Serializable;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 04-Jan-2014 6:15:20 PM
 */
public class NotificationAddress implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	private AddressType Type;
	private String Endpoint;

	public NotificationAddress(){

	}

	public AddressType getType(){
		return Type;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setType(AddressType newVal){
		Type = newVal;
	}

	public String getEndpoint(){
		return Endpoint;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setEndpoint(String newVal){
		Endpoint = newVal;
	}

}