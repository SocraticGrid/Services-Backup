package org.socraticgrid.hl7.services.orders.exceptions;

import java.io.Serializable;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 16-Jan-2014 9:12:40 AM
 */
public class MalformedOrderException extends OrderingException implements Serializable {

	public MalformedOrderException(String fault) {
		super(fault);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


}