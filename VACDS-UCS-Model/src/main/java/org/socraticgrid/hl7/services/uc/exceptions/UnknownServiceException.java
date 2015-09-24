package org.socraticgrid.hl7.services.uc.exceptions;

import java.io.Serializable;

import org.socraticgrid.hl7.services.uc.model.Message;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 16-Dec-2013 3:54:58 PM
 */
public class UnknownServiceException extends InvalidMessageException implements
		Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String serviceName;

	public UnknownServiceException()
	{

	}

	/**
	 * @param fault
	 */
	public UnknownServiceException(String fault)
	{
		super(fault);
	}

	/**
	 * @param fault
	 * @param generatingMessage
	 */
	public UnknownServiceException(String fault, Message generatingMessage)
	{
		super(fault, generatingMessage);
	}

	/**
	 * @param fault
	 * @param service
	 * @param msg
	 */
	public UnknownServiceException(String fault, String service, Message msg)
	{
		super(fault, service, msg);
	}


	public String getServiceName()
	{
		return serviceName;
	}

	public void setServiceName(String serviceName)
	{
		this.serviceName = serviceName;
	}

}