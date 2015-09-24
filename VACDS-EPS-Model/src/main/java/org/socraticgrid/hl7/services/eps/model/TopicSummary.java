package org.socraticgrid.hl7.services.eps.model;

import java.io.Serializable;

public class TopicSummary  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private String name;
	private String topicId;
	private String description;
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
	 * @return the topicId
	 */
	public String getTopicId() {
		return topicId;
	}
	/**
	 * @param topicId the topicId to set
	 */
	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	public TopicSummary() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
