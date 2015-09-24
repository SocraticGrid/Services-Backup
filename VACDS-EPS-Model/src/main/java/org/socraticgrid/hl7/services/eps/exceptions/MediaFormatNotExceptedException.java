package org.socraticgrid.hl7.services.eps.exceptions;

import java.io.Serializable;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 04-Jan-2014 6:15:19 PM
 */
public class MediaFormatNotExceptedException extends InvalidDataException
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String format;

	public MediaFormatNotExceptedException() {

	}

	public MediaFormatNotExceptedException(String string) {
		super(string);
	}

	public MediaFormatNotExceptedException(String string, String format) {
		super(string);
		this.format = format;
	}

	public String getFormatId() {
		return format;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setFormatId(String newVal) {
		format = newVal;
	}

}