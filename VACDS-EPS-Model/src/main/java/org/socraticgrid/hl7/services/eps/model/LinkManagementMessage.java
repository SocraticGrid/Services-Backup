package org.socraticgrid.hl7.services.eps.model;

import java.io.Serializable;

public class LinkManagementMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String event;

	public LinkManagementMessage() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the event
	 */
	public String getEvent() {
		return event;
	}

	/**
	 * @param event the event to set
	 */
	public void setEvent(String event) {
		this.event = event;
	}
	
}
