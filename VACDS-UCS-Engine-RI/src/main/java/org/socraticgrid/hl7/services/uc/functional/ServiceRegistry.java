package org.socraticgrid.hl7.services.uc.functional;
import java.util.HashMap;
import java.util.Set;

import org.socraticgrid.hl7.services.uc.interfaces.UCSAdapterIntf;
import org.socraticgrid.hl7.services.uc.internal.interfaces.ServiceRegistryIntf;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 16-Dec-2013 3:54:57 PM
 */
public class ServiceRegistry implements ServiceRegistryIntf {

	private String serverId="AnonymousUCSServer";
	
	public HashMap<String, UCSAdapterIntf> getAdapterMap()
	{
		return adapterMap;
	}

	public void setAdapterMap(HashMap<String, UCSAdapterIntf> adapterMap)
	{
		this.adapterMap = adapterMap;
	}

	private HashMap<String,UCSAdapterIntf> adapterMap = new HashMap<String,UCSAdapterIntf>();

	public ServiceRegistry(){

	}

	public UCSAdapterIntf getAdapter(String name)
	{
		return adapterMap.get(name);
	}
	
	public Set<String> getServiceNames()
	{
		return adapterMap.keySet();
	}

	/**
	 * @return the serverId
	 */
	public String getServerId() {
		return serverId;
	}

	/**
	 * @param serverId the serverId to set
	 */
	public void setServerId(String serverId) {
		this.serverId = serverId;
	}
}