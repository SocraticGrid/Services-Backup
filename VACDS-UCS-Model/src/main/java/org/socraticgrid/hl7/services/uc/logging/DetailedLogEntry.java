package org.socraticgrid.hl7.services.uc.logging;
import java.io.Serializable;

import org.socraticgrid.hl7.services.uc.model.Message;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 16-Dec-2013 3:54:56 PM
 */
public class DetailedLogEntry extends LogEntry implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Message message;

	public DetailedLogEntry(){

	}

	public Message getMessage()
	{
		return message;
	}

	public void setMessage(Message message)
	{
		this.message = message;
	}


}