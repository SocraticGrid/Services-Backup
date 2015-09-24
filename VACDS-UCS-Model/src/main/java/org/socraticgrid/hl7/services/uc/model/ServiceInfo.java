/**
 * 
 */
package org.socraticgrid.hl7.services.uc.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jerry Goodnough
 * 
 */
public class ServiceInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public ServiceInfo() {

	}

	String serviceName;
	List<InteractionTypes> interactionTypes;

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public List<InteractionTypes> getInteractionTypes() {
		return interactionTypes;
	}

	public void setInteractionTypes(List<InteractionTypes> interactionTypes) {
		this.interactionTypes = interactionTypes;
	}

}
