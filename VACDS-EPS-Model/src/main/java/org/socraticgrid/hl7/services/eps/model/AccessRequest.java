package org.socraticgrid.hl7.services.eps.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 04-Jan-2014 6:15:19 PM
 */
public class AccessRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User userId;
	private String topic;
	List<AffiliationRole> roles;

	public AccessRequest(){

	}


	public User getUserId(){
		return userId;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setUserId(User newVal){
		userId = newVal;
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
	 * @return the roles
	 */
	public List<AffiliationRole> getRoles() {
		return roles;
	}


	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List<AffiliationRole> roles) {
		this.roles = roles;
	}

}