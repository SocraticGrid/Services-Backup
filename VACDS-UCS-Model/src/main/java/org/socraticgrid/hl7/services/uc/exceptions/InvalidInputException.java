package org.socraticgrid.hl7.services.uc.exceptions;

import java.io.Serializable;

import org.socraticgrid.hl7.services.uc.model.Message;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 16-Dec-2013 3:54:56 PM
 */
public class InvalidInputException extends UCSException implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidInputException()
	{

	}

	public InvalidInputException(String fault)
	{
		super(fault);

	}

	public InvalidInputException(String fault, Message generatingMessage)
	{
		super(fault);
		this.setGeneratingMessage(generatingMessage);
		this.setFault(fault);
	}

	public InvalidInputException(String fault, String service, Message msg)
	{
		super(fault, service, msg);

	}



}