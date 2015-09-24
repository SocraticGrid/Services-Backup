package org.socraticgrid.hl7.services.uc.model;

import java.io.Serializable;

import javax.jws.WebMethod;

public abstract class BaseAddress implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@WebMethod(exclude = true)
	abstract public AddressType getAddressType();
	@WebMethod(exclude = true)
	abstract public String getAddressId();

	@Override
	public boolean equals(Object obj){
		/*
		if(o==null){
			return false;
		}
		if(o instanceof BaseAddress){
			return getAddressId().equals(((BaseAddress)o).getAddressId());
		}
		return false;
		*/
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof BaseAddress))
			return false;
		BaseAddress other = (BaseAddress) obj;
		if (this.getAddressId()  == null) {
			if (other.getAddressId() != null)
				return false;
		} else if (!this.getAddressId().equals(other.getAddressId()))
			return false;
		return true;
	}
	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (( getAddressId() == null) ? 0 :  getAddressId().hashCode());
		return result;
		//return getAddressId().hashCode();
	}
}
