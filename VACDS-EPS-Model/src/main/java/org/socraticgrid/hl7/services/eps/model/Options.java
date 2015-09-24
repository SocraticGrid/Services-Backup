package org.socraticgrid.hl7.services.eps.model;

import java.io.Serializable;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 04-Jan-2014 6:15:20 PM
 */
public class Options  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	private AccessModel access;
	private Durability durability;

	public Options(){

	}

	public AccessModel getAccess(){
		return access;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setAccess(AccessModel newVal){
		access = newVal;
	}

	public Durability getDurability(){
		return durability;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setDurability(Durability newVal){
		durability = newVal;
	}

}