package org.socraticgrid.hl7.services.uc.internal.interfaces;

import java.util.HashMap;
import java.util.Set;

import org.socraticgrid.hl7.services.uc.interfaces.UCSAdapterIntf;

public interface ServiceRegistryIntf {

	
	public HashMap<String, UCSAdapterIntf> getAdapterMap();
	
	public void setAdapterMap(HashMap<String, UCSAdapterIntf> adapterMap);

	
	public UCSAdapterIntf getAdapter(String name);

	public Set<String> getServiceNames();

	/**
	 * @return the serverId
	 */
	public String getServerId();
	/**
	 * @param serverId the serverId to set
	 */
	public void setServerId(String serverId);
}