package org.socraticgrid.hl7.services.eps.model;

import java.io.Serializable;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 04-Jan-2014 6:15:19 PM
 */
public class Affiliation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private AffiliationRole affiliationRole;
	private String userId;

	public Affiliation(){

	}

	public AffiliationRole getAffiliationRole(){
		return affiliationRole;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setAffiliationRole(AffiliationRole newVal){
		affiliationRole = newVal;
	}

	public String getUserId(){
		return userId;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setUserId(String newVal){
		userId = newVal;
	}

}