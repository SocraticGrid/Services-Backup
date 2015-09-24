package org.socraticgrid.hl7.services.eps.model;

import java.io.Serializable;

public class BrokerAccessRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	User userInfo;

	/**
	 * @return the userInfo
	 */
	public User getUserInfo() {
		return userInfo;
	}

	/**
	 * @param userInfo the userInfo to set
	 */
	public void setUserInfo(User userInfo) {
		this.userInfo = userInfo;
	}
}
