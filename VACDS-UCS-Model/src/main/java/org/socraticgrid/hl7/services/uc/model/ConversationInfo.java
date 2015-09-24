package org.socraticgrid.hl7.services.uc.model;

import java.io.Serializable;
import java.util.List;

public class ConversationInfo implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Conversation conversation;
	List<String> messages;

	/**
	 * 
	 */
	public ConversationInfo()
	{
		super();

	}

	public Conversation getConversation()
	{
		return conversation;
	}

	public List<String> getMessages()
	{
		return messages;
	}

	public void setConversation(Conversation conversation)
	{
		this.conversation = conversation;
	}

	public void setMessages(List<String> messages)
	{
		this.messages = messages;
	}

}
