package org.socraticgrid.hl7.services.eps.model;

import java.io.Serializable;

public class DeliveryReviewResult implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DeliveryAction action;
	private Message event;
	public DeliveryReviewResult() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the action
	 */
	public DeliveryAction getAction() {
		return action;
	}
	/**
	 * @param action the action to set
	 */
	public void setAction(DeliveryAction action) {
		this.action = action;
	}
	/**
	 * @return the event
	 */
	public Message getEvent() {
		return event;
	}
	/**
	 * @param event the event to set
	 */
	public void setEvent(Message event) {
		this.event = event;
	}
	
}
