package org.socraticgrid.hl7.services.eps.model;

import java.io.Serializable;

public class BrokerStatus implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BrokerStatus() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	private String name;
	private OperationalState state;

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
	/**
	 * @return the state
	 */
	public OperationalState getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(OperationalState state) {
		this.state = state;
	}
	

}
