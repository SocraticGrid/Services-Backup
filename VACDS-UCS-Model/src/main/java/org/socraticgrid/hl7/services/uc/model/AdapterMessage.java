package org.socraticgrid.hl7.services.uc.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 16-Dec-2013 3:54:55 PM
 */
public class AdapterMessage implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<PhysicalAddress> addressesList;

	private Message message;

	public AdapterMessage()
	{

	}

	/**
	 * @param messageType
	 * @param addressesList
	 */
	public AdapterMessage(Message message, List<PhysicalAddress> addressesList)
	{
		
		this.message = message;
		this.addressesList = addressesList;
	}


	public List<PhysicalAddress> getAddressesList()
	{
		return addressesList;
	}

	public Message getMessageType()
	{
		return message;
	}

	public void setAddressesList(List<PhysicalAddress> addressesList)
	{
		this.addressesList = addressesList;
	}

	public void setMessageType(Message message)
	{
		this.message = message;
	}

}