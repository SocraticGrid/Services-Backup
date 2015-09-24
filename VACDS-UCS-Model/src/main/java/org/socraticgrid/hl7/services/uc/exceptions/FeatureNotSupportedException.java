package org.socraticgrid.hl7.services.uc.exceptions;

import org.socraticgrid.hl7.services.uc.model.Message;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 16-Dec-2013 3:54:56 PM
 */
public class FeatureNotSupportedException extends UCSException {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FeatureNotSupportedException(){

	}

	public FeatureNotSupportedException(String fault, Message msg)
	{
		super(fault, msg);
	
	}

	public FeatureNotSupportedException(String fault, String service,
			Message msg)
	{
		super(fault, service, msg);
	
	}

	public FeatureNotSupportedException(String fault)
	{
		super(fault);
	
	}



}

