package org.socraticgrid.hl7.services.eps.model;

import java.io.Serializable;
import java.util.List;

public class FederationMap implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FederationMap() {
		super();
		// TODO Auto-generated constructor stub
	}

	private List<FederationMapping> mappings;

	/**
	 * @return the mappings
	 */
	public List<FederationMapping> getMappings() {
		return mappings;
	}

	/**
	 * @param mappings the mappings to set
	 */
	public void setMappings(List<FederationMapping> mappings) {
		this.mappings = mappings;
	}
	
	
}

