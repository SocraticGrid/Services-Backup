package org.socraticgrid.hl7.services.uc.exceptions;

import java.io.Serializable;

import org.socraticgrid.hl7.services.uc.model.Message;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 16-Dec-2013 3:54:56 PM
 */
public class BadBodyException extends InvalidContentException implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String faultDescription;
	public int indexToInvalidBody;

	public BadBodyException(){

	}
	

	/**
	 * @param fault
	 * @param generatingMessage
	 */
	public BadBodyException(String fault, Message generatingMessage)
	{
		super(fault, generatingMessage);
		
	}


	/**
	 * @param fault
	 * @param service
	 * @param msg
	 */
	public BadBodyException(String fault, String service, Message msg)
	{
		super(fault, service, msg);
		
	}
	/**
	 * @param fault
	 * @param service
	 * @param msg
	 * @param faultDesc
	 * @param index
	 */
	public BadBodyException(String fault, String service, Message msg,String faultDesc, int index)
	{
		super(fault, service, msg);
		this.faultDescription=faultDesc;
		this.indexToInvalidBody=index;
	}


	/**
	 * @param fault
	 */
	public BadBodyException(String fault)
	{
		super(fault);
		
	}


	public String getFaultDescription()
	{
		return faultDescription;
	}

	public void setFaultDescription(String faultDescription)
	{
		this.faultDescription = faultDescription;
	}

	public int getIndexToInvalidBody()
	{
		return indexToInvalidBody;
	}

	public void setIndexToInvalidBody(int indexToInvalidBody)
	{
		this.indexToInvalidBody = indexToInvalidBody;
	}

}