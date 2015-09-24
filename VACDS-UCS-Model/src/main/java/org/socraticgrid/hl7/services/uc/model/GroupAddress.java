package org.socraticgrid.hl7.services.uc.model;

import javax.jws.WebMethod;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlAccessorType(XmlAccessType.FIELD)
public class GroupAddress extends BaseAddress {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@XmlTransient
	private String addressId;
	@WebMethod(exclude = true)
	@Override
	public String getAddressId() {
		return addressId;
	}
	@WebMethod(exclude = true)
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	
	
	@XmlElement(required=true)
	private String name;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	@WebMethod(exclude = true)
	@Override
	public AddressType getAddressType() {
		
		return AddressType.Group;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		if (!(obj instanceof GroupAddress))
			return false;
		GroupAddress other = (GroupAddress) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GroupAddress [name=" + name + "]";
	}
	
	
	
}
