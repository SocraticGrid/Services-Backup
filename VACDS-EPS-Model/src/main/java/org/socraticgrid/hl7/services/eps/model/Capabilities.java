package org.socraticgrid.hl7.services.eps.model;

import java.io.Serializable;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 04-Jan-2014 6:15:19 PM
 */
public class Capabilities implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean durableTopics;
	private boolean publishOnDeman;
	private boolean pushEvents;

	public Capabilities(){

	}


	public boolean isDurableTopics(){
		return durableTopics;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setDurableTopics(boolean newVal){
		durableTopics = newVal;
	}

	public boolean isPublishOnDeman(){
		return publishOnDeman;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setPublishOnDeman(boolean newVal){
		publishOnDeman = newVal;
	}

	public boolean isPushEvents(){
		return pushEvents;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setPushEvents(boolean newVal){
		pushEvents = newVal;
	}

}