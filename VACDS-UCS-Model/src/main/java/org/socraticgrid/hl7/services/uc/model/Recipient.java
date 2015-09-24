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
 * @created 16-Dec-2013 3:54:57 PM
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Recipient implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@XmlTransient
	private String recipientId;
	@XmlElement(required=true)
	private DeliveryAddress deliveryAddress;
	@XmlElement(required=false)
	private boolean deliveryReceipt=false;
	@XmlElement(required=false)
	private boolean readReceipt=false;
	
	@XmlElement(required=false)
	private Role role=new Role();
	
	@XmlElement(required=false)
	private Visibility visibility=Visibility.Public;


	public DeliveryAddress getDeliveryAddress()
	{
		return deliveryAddress;
	}

	public void setDeliveryAddress(DeliveryAddress address)
	{
		this.deliveryAddress = address;
		
	}

	public boolean isDeliveryReceipt()
	{
		return deliveryReceipt;
	}
	public void setDeliveryReceipt(boolean deliveryReceipt)
	{
		this.deliveryReceipt = deliveryReceipt;
	}

	public boolean isReadReceipt()
	{
		return readReceipt;
	}
	public void setReadReceipt(boolean readReceipt)
	{
		this.readReceipt = readReceipt;
	}

	public Role getRole()
	{
		return role;
	}

	public void setRole(Role role)
	{
		this.role = role;
	}

	public Visibility getVisibility()
	{
		return visibility;
	}

	public void setVisibility(Visibility visibility)
	{
		this.visibility = visibility;
	}

	@WebMethod(exclude = true)
	public String getRecipientId() {
		return recipientId;
	}
	@WebMethod(exclude = true)
	public void setRecipientId(String recipientId) {
		this.recipientId = recipientId;
	}
	//TODO   Suspect we do not want the following member
	@WebMethod(exclude = true)
	public String getDeliveryAddressId() {
		return deliveryAddress.getDeliveryAddressId();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Recipient [recipientId=" + recipientId + ", deliveryAddress="
				+ deliveryAddress + ", deliveryReceipt=" + deliveryReceipt
				+ ", readReceipt=" + readReceipt + ", role=" + role
				+ ", visibility=" + visibility + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((recipientId == null) ? 0 : recipientId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Recipient other = (Recipient) obj;
		if (recipientId == null) {
			if (other.recipientId != null)
				return false;
		} else if ( recipientId != null 
				&& other.recipientId != null ) {
			System.out.println("********* recipientId");
			return recipientId.equals(other.recipientId);
		}
		System.out.println("********* deliveryAddress");
		if (deliveryAddress == null) {
			if (other.deliveryAddress != null)
				return false;
		} else if (!deliveryAddress.equals(other.deliveryAddress))
			return false;
		System.out.println("********* deliveryReceipt");
		if (deliveryReceipt != other.deliveryReceipt)
			return false;
		System.out.println("********* readReceipt");
		if (readReceipt != other.readReceipt)
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (visibility != other.visibility)
			return false;
		return true;
	}


	

}