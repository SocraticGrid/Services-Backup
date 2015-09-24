package org.socraticgrid.hl7.services.orders.exceptions;

import java.io.Serializable;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 16-Jan-2014 9:12:42 AM
 */
public class UnfulfillableOrderException extends OrderingException implements Serializable {

	public UnfulfillableOrderException(String fault) {
		super(fault);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


}