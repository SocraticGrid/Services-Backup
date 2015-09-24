package org.socraticgrid.hl7.services.eps.model;

import java.io.Serializable;
import java.util.List;

public class FederationMapping implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String topic;
	private List<FederationMapEntry> partners;
	private List<FederationMapping> childMappings;
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
	 * @return the partners
	 */
	public List<FederationMapEntry> getPartners() {
		return partners;
	}
	/**
	 * @param partners the partners to set
	 */
	public void setPartners(List<FederationMapEntry> partners) {
		this.partners = partners;
	}
	/**
	 * @return the childMappings
	 */
	public List<FederationMapping> getChildMappings() {
		return childMappings;
	}
	/**
	 * @param childMappings the childMappings to set
	 */
	public void setChildMappings(List<FederationMapping> childMappings) {
		this.childMappings = childMappings;
	}
	public FederationMapping() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
