package org.socraticgrid.hl7.services.uc.model;

import java.io.Serializable;
import java.util.HashMap;

public class CommunicationsPreferences implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public CommunicationsPreferences() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public HashMap<String, PhysicalAddress> getAddessesByType() {
		return addessesByType;
	}

	public void setAddessesByType(HashMap<String, PhysicalAddress> addessesByType) {
		this.addessesByType = addessesByType;
	}

	public PhysicalAddress getPreferredAddress() {
		return preferredAddress;
	}

	public void setPreferredAddress(PhysicalAddress preferredAddress) {
		this.preferredAddress = preferredAddress;
	}

	private String endpoint; // Service endpoint (UCS Client)
	private HashMap<String, PhysicalAddress> addessesByType;
	private PhysicalAddress preferredAddress;
}
