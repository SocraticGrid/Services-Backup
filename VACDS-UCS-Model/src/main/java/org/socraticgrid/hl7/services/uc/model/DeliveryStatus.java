package org.socraticgrid.hl7.services.uc.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 16-Dec-2013 3:54:56 PM
 */
public class DeliveryStatus implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String deliveryStatusId;
	private DeliveryAddress address;
	private String status;
	private String action;
	private Date timestamp;
	private Recipient recipient;


	public DeliveryAddress getAddress()
	{
		return address;
	}

	public void setAddress(DeliveryAddress address)
	{
		this.address = address;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public String getAction()
	{
		return action;
	}

	public void setAction(String action)
	{
		this.action = action;
	}

	public java.util.Date getTimestamp()
	{
		return timestamp;
	}

	public void setTimestamp(java.util.Date timestamp)
	{
		this.timestamp = timestamp;
	}

	public Recipient getRecipient()
	{
		return recipient;
	}

	public void setRecipient(Recipient recipient)
	{
		this.recipient = recipient;
	}

	public String getDeliveryStatusId() {
		return deliveryStatusId;
	}

	public void setDeliveryStatusId(String deliveryStatusId) {
		this.deliveryStatusId = deliveryStatusId;
	}

}