package org.socraticgrid.hl7.services.orders.model;

import java.io.Serializable;

public class Workflow implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String id;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	
}
