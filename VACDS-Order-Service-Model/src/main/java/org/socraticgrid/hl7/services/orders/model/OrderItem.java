package org.socraticgrid.hl7.services.orders.model;

import java.io.Serializable;
import java.util.Set;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 16-Jan-2014 9:12:41 AM
 */
abstract public class OrderItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private int type;
	



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