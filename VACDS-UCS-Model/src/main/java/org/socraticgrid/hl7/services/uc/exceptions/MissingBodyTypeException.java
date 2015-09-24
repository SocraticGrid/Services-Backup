package org.socraticgrid.hl7.services.uc.exceptions;

import java.io.Serializable;

import org.socraticgrid.hl7.services.uc.model.Message;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 16-Dec-2013 3:54:57 PM
 */
public class MissingBodyTypeException extends InvalidContentException implements
		Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String missingType;

	public MissingBodyTypeException()
	{

	}

	/**
	 * @param fault
	 */
	public MissingBodyTypeException(String fault)
	{
		super(fault);
	}

	/**
	 * @param fault
	 * @param generatingMessage
	 */
	public MissingBodyTypeException(String fault, Message generatingMessage)
	{
		super(fault, generatingMessage);
	}

	/**
	 * @param fault
	 * @param generatingMessage
	 */
	public MissingBodyTypeException(String fault, Message generatingMessage,
			String missingType)
	{
		super(fault, generatingMessage);
		this.missingType = missingType;
	}

	/**
	 * @param fault
	 * @param service
	 * @param msg
	 */
	public MissingBodyTypeException(String fault, String service, Message msg)
	{
		super(fault, service, msg);
	}


	public String getMissingType()
	{
		return this.missingType;
	}

	public void setMissingType(String missingType)
	{
		this.missingType = missingType;
	}

}