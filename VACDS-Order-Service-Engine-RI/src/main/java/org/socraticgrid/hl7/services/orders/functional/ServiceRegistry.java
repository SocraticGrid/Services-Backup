package org.socraticgrid.hl7.services.orders.functional;
import java.util.HashMap;
import java.util.Set;

import org.socraticgrid.hl7.services.orders.interfaces.FulfillmentIFace;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 16-Dec-2013 3:54:57 PM
 */
public class ServiceRegistry {


	private HashMap<String,FulfillmentIFace> serviceMap = new HashMap<String,FulfillmentIFace>();

	public ServiceRegistry(){

	}

	/**
	 * @return the serviceMap
	 */
	public HashMap<String, FulfillmentIFace> getServiceMap() {
		return serviceMap;
	}

	/**
	 * @param serviceMap the serviceMap to set
	 */
	public void setServiceMap(HashMap<String, FulfillmentIFace> serviceMap) {
		this.serviceMap = serviceMap;
	}

	public FulfillmentIFace getService(String name)
	{
		return serviceMap.get(name);
	}
	
	public Set<String> getServiceNames()
	{
		return serviceMap.keySet();
	}
}