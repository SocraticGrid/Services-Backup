package org.socraticgrid.hl7.services.orders.model;

import java.io.Serializable;

import org.socraticgrid.hl7.services.orders.model.primatives.Identifier;

public class CatelogItemType implements Serializable {

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
}
