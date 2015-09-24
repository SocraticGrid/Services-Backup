package org.socraticgrid.hl7.services.orders.model;

import java.io.Serializable;

import org.socraticgrid.hl7.services.orders.model.primatives.Identifier;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 16-Jan-2014 9:12:40 AM
 */
public class ClinicalPractitioner implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Identifier id;

	/**
	 * @return the id
	 */
	public Identifier getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Identifier id) {
		this.id = id;
	}
	
	private String name;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}