package org.socraticgrid.hl7.services.eps.exceptions;

import java.io.Serializable;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 04-Jan-2014 6:15:19 PM
 */
public class AuthenicationRequiredException extends PubSubException implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AuthenicationRequiredException() {

	}

	public AuthenicationRequiredException(String message) {
		super(message);

	}

}