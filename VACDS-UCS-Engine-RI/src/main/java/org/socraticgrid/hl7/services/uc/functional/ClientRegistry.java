package org.socraticgrid.hl7.services.uc.functional;

import java.util.HashMap;

import org.socraticgrid.hl7.services.uc.internal.interfaces.ClientRegistryIntf;

public class ClientRegistry implements ClientRegistryIntf {
	
	
	public HashMap<String, String> getClientEndpointMap()
	{
		return clientMap;
	}

	public void setClientEndpointMap(HashMap<String, String> clientMap)
	{
		this.clientMap = clientMap;
	}

	private HashMap<String,String> clientMap = new HashMap<String,String>();
	
	public ClientRegistry()
	{
		
	}
	
	public String getClientEndpoint(String clientName)
	{
		return clientMap.get(clientName);
	}
	
	public void setClientEndpoint(String clientName, String endpoint)
	{
		
	}
}
