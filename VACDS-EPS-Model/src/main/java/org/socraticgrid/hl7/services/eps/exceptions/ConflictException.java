package org.socraticgrid.hl7.services.eps.exceptions;

import java.io.Serializable;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 04-Jan-2014 6:15:19 PM
 */
public class ConflictException extends PubSubException implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConflictException() {

	}

	public ConflictException(String message) {
		super(message);

	}

}