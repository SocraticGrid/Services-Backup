package org.socraticgrid.hl7.services.eps.exceptions;

import java.io.Serializable;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 04-Jan-2014 6:15:20 PM
 */
public class NoSuchTopicException extends PubSubException implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String topic;

	public NoSuchTopicException() {

	}

	public NoSuchTopicException(String message) {
		super(message);
	}

	public NoSuchTopicException(String message, String topic) {
		super(message);
		this.topic = topic;
	}

	public String getTopic() {
		return topic;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setTopicId(String newVal) {
		topic = newVal;
	}

}