package org.socraticgrid.hl7.services.uc.exceptions;

import org.socraticgrid.hl7.services.uc.model.Message;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 16-Dec-2013 3:54:58 PM
 */
public class UnknownUserException extends UCSException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnknownUserException(){

	}



	/**
	 * @param fault
	 * @param msg
	 */
	public UnknownUserException(String fault, Message msg)
	{
		super(fault, msg);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param fault
	 * @param service
	 * @param msg
	 */
	public UnknownUserException(String fault, String service, Message msg)
	{
		super(fault, service, msg);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param fault
	 */
	public UnknownUserException(String fault)
	{
		super(fault);
		// TODO Auto-generated constructor stub
	}

}