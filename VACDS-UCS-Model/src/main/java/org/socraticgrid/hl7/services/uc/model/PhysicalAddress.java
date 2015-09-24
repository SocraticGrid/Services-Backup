package org.socraticgrid.hl7.services.uc.model;

import java.io.Serializable;

import javax.jws.WebMethod;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;


/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 16-Dec-2013 3:54:55 PM
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class PhysicalAddress extends BaseAddress implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@XmlTransient
	private String addressId;
	@XmlElement(required=true)
	private String address;
	@XmlElement(required=true)
	private String serviceId;

	public PhysicalAddress()
	{
		
	}
	public PhysicalAddress(String serviceId, String address)
	{
		this.serviceId=serviceId;
		this.address=address;
	}
	
	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getServiceId()
	{
		return serviceId;
	}

	public void setServiceId(String serviceId)
	{
		this.serviceId = serviceId;
	}

	//TODO   Evaluate if this member should exist.... 
	@WebMethod(exclude = true)
	@Override
	public String getAddressId() {
		return addressId;
	}
	
	@WebMethod(exclude = true)
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	@WebMethod(exclude = true)
	@Override
	public AddressType getAddressType() {
		
		return AddressType.Physical;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result
				+ ((serviceId == null) ? 0 : serviceId.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof PhysicalAddress))
			return false;
		PhysicalAddress other = (PhysicalAddress) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (serviceId == null) {
			if (other.serviceId != null)
				return false;
		} else if (!serviceId.equals(other.serviceId))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PhysicalAddress [address=" + address + ", serviceId="
				+ serviceId + "]";
	}


}