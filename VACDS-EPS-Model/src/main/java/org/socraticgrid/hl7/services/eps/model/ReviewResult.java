package org.socraticgrid.hl7.services.eps.model;

import java.io.Serializable;

public class ReviewResult  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Message event;
	private ReviewAction action;
	private boolean altered;
	
	public ReviewResult() {
		super();
		// TODO Auto-generated constructor stub
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
	/**
	 * @return the action
	 */
	public ReviewAction getAction() {
		return action;
	}
	/**
	 * @param action the action to set
	 */
	public void setAction(ReviewAction action) {
		this.action = action;
	}
	/**
	 * @return the altered
	 */
	public boolean isAltered() {
		return altered;
	}
	/**
	 * @param altered the altered to set
	 */
	public void setAltered(boolean altered) {
		this.altered = altered;
	}
	
}
