package org.socraticgrid.hl7.services.uc.exceptions;

import java.io.Serializable;

import org.socraticgrid.hl7.services.uc.model.Message;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 17-Dec-2013 2:08:31 PM
 */
public class DeliveryException extends UCSException  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String messageId;

	public DeliveryException(){

	}

	/**
	 * @param fault
	 */
	public DeliveryException(String fault)
	{
		super(fault);
		
	}

	/**
	 * @param fault
	 * @param msg
	 */
	public DeliveryException(String fault, Message msg)
	{
		super(fault, msg);
		
	}

	/**
	 * @param fault
	 * @param service
	 * @param msg
	 */
	public DeliveryException(String fault, String service, Message msg)
	{
		super(fault, service, msg);

	}


	public String getMessageId()
	{
		return messageId;
	}

	public void setMessageId(String messageId)
	{
		this.messageId = messageId;
	}

}