package org.socraticgrid.hl7.services.eps.model;

import java.io.Serializable;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 04-Jan-2014 6:15:19 PM
 */
public class ManagementEvent extends Message implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private int eventType;
	
	public ManagementEvent(){

	}

	public int getEventType(){
		return eventType;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setEventType(int newVal){
		eventType = newVal;
	}

}