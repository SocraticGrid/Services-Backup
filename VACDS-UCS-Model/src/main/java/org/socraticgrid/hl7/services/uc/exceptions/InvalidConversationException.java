package org.socraticgrid.hl7.services.uc.exceptions;

import java.io.Serializable;

import org.socraticgrid.hl7.services.uc.model.Message;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 16-Dec-2013 3:54:56 PM
 */
public class InvalidConversationException extends InvalidInputException
		implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidConversationException()
	{

	}

	/**
	 * @param fault
	 */
	public InvalidConversationException(String fault)
	{
		super(fault);

	}

	/**
	 * @param fault
	 * @param generatingMessage
	 */
	public InvalidConversationException(String fault, Message generatingMessage)
	{
		super(fault, generatingMessage);

	}

	/**
	 * @param fault
	 * @param service
	 * @param msg
	 */
	public InvalidConversationException(String fault, String service,
			Message msg)
	{
		super(fault, service, msg);

	}



}