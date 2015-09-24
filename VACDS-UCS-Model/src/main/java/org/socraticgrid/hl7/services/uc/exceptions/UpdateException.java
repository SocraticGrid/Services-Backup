package org.socraticgrid.hl7.services.uc.exceptions;

import java.io.Serializable;

import org.socraticgrid.hl7.services.uc.model.Message;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 16-Dec-2013 3:54:58 PM
 */
public class UpdateException extends UCSException implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UpdateException(){

	}

	/**
	 * @param fault
	 */
	public UpdateException(String fault)
	{
		super(fault);
	}

	/**
	 * @param fault
	 * @param msg
	 */
	public UpdateException(String fault, Message msg)
	{
		super(fault, msg);
	}

	/**
	 * @param fault
	 * @param service
	 * @param msg
	 */
	public UpdateException(String fault, String service, Message msg)
	{
		super(fault, service, msg);
	}


}