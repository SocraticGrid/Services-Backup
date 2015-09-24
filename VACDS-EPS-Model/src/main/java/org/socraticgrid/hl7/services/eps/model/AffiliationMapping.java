package org.socraticgrid.hl7.services.eps.model;

import java.io.Serializable;

public class AffiliationMapping implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String topic;
	private Affiliation affiliation;
	
	
	
	public AffiliationMapping() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the topic
	 */
	public String getTopic() {
		return topic;
	}
	/**
	 * @param topic the topic to set
	 */
	public void setTopic(String topic) {
		this.topic = topic;
	}
	/**
	 * @return the affiliation
	 */
	public Affiliation getAffiliation() {
		return affiliation;
	}
	/**
	 * @param affiliation the affiliation to set
	 */
	public void setAffiliation(Affiliation affiliation) {
		this.affiliation = affiliation;
	}
	
	
}
