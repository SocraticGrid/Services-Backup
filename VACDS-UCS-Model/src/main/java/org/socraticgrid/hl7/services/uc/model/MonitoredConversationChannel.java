package org.socraticgrid.hl7.services.uc.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 16-Dec-2013 3:54:57 PM
 */
public class MonitoredConversationChannel extends ConversationChannel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public List<Message> getMessages()
	{
		return messages;
	}

	public void setMessages(List<Message> messages)
	{
		this.messages = messages;
	}

	public List<Message> messages;

	public MonitoredConversationChannel(){

	}

}