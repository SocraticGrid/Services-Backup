package org.socraticgrid.hl7.services.uc.model;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.jws.WebMethod;

/**
 * @author steven
 * 
 */
public class UserContactInfoDelegate extends UserContactInfo {

	private static final long serialVersionUID = 1L;

	@Override
	public Map<String, PhysicalAddress> getAddressesByType() {
		Map<String, PhysicalAddress> addressesByType = new LinkedHashMap<>();
		for(int i=0; i<addresses.size(); i++) {
			addressesByType.put(types.get(i),addresses.get(i));
		}
		return addressesByType;
	}
	
	private LinkedList<PhysicalAddress> addresses = new LinkedList<>();
	private LinkedList<String> types = new LinkedList<>();

	@WebMethod(exclude=true)
	public LinkedList<PhysicalAddress> getAddresses()
	{
		return addresses;
	}
	@WebMethod(exclude=true)
	public void setAddresses(LinkedList<PhysicalAddress> addresses)
	{
		this.addresses = addresses;
	}
	@WebMethod(exclude=true)
	public LinkedList<String> getTypes() {
		return types;
	}
	@WebMethod(exclude=true)
	public void setTypes(LinkedList<String> types) {
		this.types = types;
	}

}