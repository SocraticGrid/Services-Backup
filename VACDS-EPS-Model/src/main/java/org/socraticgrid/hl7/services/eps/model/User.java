package org.socraticgrid.hl7.services.eps.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 04-Jan-2014 6:15:21 PM
 */
public class User  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	private NotificationAddress defaultNotificationAddress;
	private NotificationAddress defaultOnDemandEndpoint;
	private String name;
	private List<Role> privledges;
	private boolean supportsPublishOnDemand;
	private String userId;

	public User(){

	}

	public NotificationAddress getDefaultNotificationAddress(){
		return defaultNotificationAddress;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setDefaultNotificationAddress(NotificationAddress newVal){
		defaultNotificationAddress = newVal;
	}

	public NotificationAddress getDefaultOnDemandEndpoint(){
		return defaultOnDemandEndpoint;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setDefaultOnDemandEndpoint(NotificationAddress newVal){
		defaultOnDemandEndpoint = newVal;
	}

	public String getName(){
		return name;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setName(String newVal){
		name = newVal;
	}

	public List<Role> getPrivledges(){
		return privledges;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setPrivledges(List<Role> newVal){
		privledges = newVal;
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