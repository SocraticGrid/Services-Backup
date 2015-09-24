package org.socraticgrid.hl7.services.orders.model;

import java.io.Serializable;

import org.socraticgrid.hl7.services.orders.model.primatives.Identifier;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 16-Jan-2014 9:12:42 AM
 */
public abstract class Subject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * Unique identifier for the subject of the testing (the source of the specimen)
	 */
	private Identifier identity;

	/**
	 * Unique identifier for the subject of the testing (the source of the specimen)
	 */
	public Identifier getIdentity() {
		return identity;
	};

	/**
	 * Unique identifier for the subject of the testing (the source of the specimen)
	 * 
	 * @param newVal
	 */
	public void setIdentity(Identifier newVal){
		identity = newVal;
	}

}