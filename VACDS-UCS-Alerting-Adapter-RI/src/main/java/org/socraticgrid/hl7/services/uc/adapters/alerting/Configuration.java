package org.socraticgrid.hl7.services.uc.adapters.alerting;

import java.util.HashMap;
import java.util.Map;

public class Configuration {
	private Map<String, String> endpointMap = new HashMap<String, String>();
	private boolean requireAliasUse = false;
	private boolean requireSecureEndpoint = false;

	private String serviceId = "Alert";

	/**
	 * @return the endpointMap
	 */
	public Map<String, String> getEndpointMap() {
		return endpointMap;
	}

	/**
	 * @return the serviceId
	 */
	public String getServiceId() {
		return serviceId;
	}

	/**
	 * @return the requiredAliasUse
	 */
	public boolean isRequireAliasUse() {
		return requireAliasUse;
	}

	/**
	 * @return the requireSecureEndpoint
	 */
	public boolean isRequireSecureEndpoint() {
		return requireSecureEndpoint;
	}

	/**
	 * @param endpointMap
	 *            the endpointMap to set
	 */
	public void setEndpointMap(Map<String, String> endpointMap) {
		this.endpointMap = endpointMap;
	}

	/**
	 * @param requiredAliasUse
	 *            the requiredAliasUse to set
	 */
	public void setRequireAliasUse(boolean requireAliasUse) {
		this.requireAliasUse = requireAliasUse;
	}

	/**
	 * @param requireSecureEndpoint
	 *            the requireSecureEndpoint to set
	 */
	public void setRequireSecureEndpoint(boolean requireSecureEndpoint) {
		this.requireSecureEndpoint = requireSecureEndpoint;
	}

	/**
	 * @param serviceId
	 *            the serviceId to set
	 */
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

}
