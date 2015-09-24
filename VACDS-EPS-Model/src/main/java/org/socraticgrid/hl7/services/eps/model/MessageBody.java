package org.socraticgrid.hl7.services.eps.model;

import java.io.Serializable;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 04-Jan-2014 6:15:20 PM
 */
public class MessageBody implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private String type;
	private String body;

	public MessageBody(){

	}

	public void finalize() throws Throwable {

	}

	public String getType(){
		return type;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setType(String newVal){
		type = newVal;
	}

	public String getBody(){
		return body;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setBody(String newVal){
		body = newVal;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MessageBody [type=" + type + ", body=" + body + "]";
	}

}