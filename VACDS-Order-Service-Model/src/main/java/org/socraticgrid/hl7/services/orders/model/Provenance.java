package org.socraticgrid.hl7.services.orders.model;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 16-Jan-2014 9:12:41 AM
 */
public class Provenance {

	private int type;

	public Provenance(){

	}

	public void finalize() throws Throwable {

	}

	public int getType(){
		return type;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setType(int newVal){
		type = newVal;
	}

}