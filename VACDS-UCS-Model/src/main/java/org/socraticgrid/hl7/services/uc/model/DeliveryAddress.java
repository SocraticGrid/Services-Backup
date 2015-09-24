package org.socraticgrid.hl7.services.uc.model;

import java.io.Serializable;

import javax.jws.WebMethod;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Provides a mechanism for specifying the destination of an message Three
 * possible forms are a physical address, known user (Party) or a group.
 * 
 * @author Jerry Goodnough
 * @version 1.0
 * @updated 16-Dec-2013 4:55:01 PM
 */


@XmlAccessorType(XmlAccessType.FIELD)
public class DeliveryAddress implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@XmlElements(value = { 
            @XmlElement(name="physicaladdress", 
                        type=PhysicalAddress.class),
            @XmlElement(name="party", 
                        type=PartyAddress.class),
            @XmlElement(name="group", 
                        type=GroupAddress.class), 
 
    })
	private BaseAddress address;
	
	public BaseAddress getAddress(){
		return address;
	}
	
	public void setAddress(BaseAddress address)
	{
		this.address = address;
	}
	
	@WebMethod(exclude = true)
	public AddressType getAddressType()
	{
		return address.getAddressType();
	}
	
	@WebMethod(exclude = true)
	public PhysicalAddress getPhysicalAddress()
	{
		if(address instanceof PhysicalAddress){
			return (PhysicalAddress) address;
		}
		else {
			return null;
		}
	}
	
	@WebMethod(exclude = true)
	public PartyAddress getPartyAddress()
	{
		if(address instanceof PartyAddress){
			return (PartyAddress) address;
		}
		else {
			return null;
		}
	}
	@WebMethod(exclude = true)
	public GroupAddress getGroupAddress()
	{
		if(address instanceof GroupAddress){
			return (GroupAddress) address;
		}
		else {
			return null;
		}
	}
	
	public DeliveryAddress()
	{
		
	}
	
	public DeliveryAddress(BaseAddress address)
	{
		this.address=address;
	}

	@XmlTransient
	private String deliveryAddressId;

	/**
	 * @return the addressId
	 */
	@WebMethod(exclude = true)
	public String getDeliveryAddressId() {
		return deliveryAddressId;
	}

	/**
	 * @param addressId the addressId to set
	 */
	@WebMethod(exclude = true)
	public void setDeliveryAddressId(String addressId) {
		this.deliveryAddressId = addressId;
	}
	
	/**
	 * To build from persistence.
	 * @param address
	 */
	@WebMethod(exclude = true)
	private void setAddressAdapter(AddressPersistenceAdapter address)
	{
		this.address = address.getMaskedAddress();
	}
	@WebMethod(exclude = true)
	private AddressPersistenceAdapter getAddressAdapter()
	{
		return null;
	}
	@WebMethod(exclude = true)
	private String getAddressId() {
		return address.getAddressId();
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof DeliveryAddress))
			return false;
		DeliveryAddress other = (DeliveryAddress) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DeliveryAddress [address=" + address + "]";
	}

}