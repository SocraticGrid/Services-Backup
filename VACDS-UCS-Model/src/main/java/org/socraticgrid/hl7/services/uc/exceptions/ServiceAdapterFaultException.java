package org.socraticgrid.hl7.services.uc.exceptions;

import java.io.Serializable;

import org.socraticgrid.hl7.services.uc.model.Message;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 16-Dec-2013 3:54:57 PM
 */
public class ServiceAdapterFaultException extends DeliveryException implements
		Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ServiceAdapterFaultException()
	{

	}

	/**
	 * @param fault
	 */
	public ServiceAdapterFaultException(String fault)
	{
		super(fault);
	}

	/**
	 * @param fault
	 * @param msg
	 */
	public ServiceAdapterFaultException(String fault, Message msg)
	{
		super(fault, msg);
	}

	/**
	 * @param fault
	 * @param service
	 * @param msg
	 */
	public ServiceAdapterFaultException(String fault, String service, Message msg)
	{
		super(fault, service, msg);
	}


}