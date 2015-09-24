package org.socraticgrid.hl7.services.eps.model;

import java.io.Serializable;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 04-Jan-2014 6:15:20 PM
 */
public class PublicationContract  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	private User publisher;
	private String onDemandEndpoint;
	private boolean supportsPublishOnDemand;

	public PublicationContract(){

	}

	public User getPublisher(){
		return publisher;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setPublisher(User newVal){
		publisher = newVal;
	}

	public String getOnDemandEndpoint(){
		return onDemandEndpoint;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setOnDemandEndpoint(String newVal){
		onDemandEndpoint = newVal;
	}

	public boolean isSupportsPublishOnDemand(){
		return supportsPublishOnDemand;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setSupportsPublishOnDemand(boolean newVal){
		supportsPublishOnDemand = newVal;
	}

}