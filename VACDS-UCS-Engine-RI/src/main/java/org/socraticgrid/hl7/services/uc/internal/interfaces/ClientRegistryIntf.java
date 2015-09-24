package org.socraticgrid.hl7.services.uc.internal.interfaces;

public interface ClientRegistryIntf {
	public String getClientEndpoint(String clientName);
	
	public void setClientEndpoint(String clientName, String endpoint);
}
