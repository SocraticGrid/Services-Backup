package org.socraticgrid.hl7.services.uc.model;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 16-Dec-2013 3:54:56 PM
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ConversationRequestMessageHeader extends MessageHeader implements Serializable {

	
	ConversationRequestMessageHeader(String messageId) {
		super(messageId);
	}
	
	public ConversationRequestMessageHeader()
	{
		
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@XmlElement(required=true)	
	private Conversation conversation;
	@XmlElement(required=false)	
	private Date requestExpires;


	public Conversation getConversation()
	{
		return conversation;
	}

	public void setConversation(Conversation conversation)
	{
		this.conversation = conversation;
	}

	public Date getRequestExpires()
	{
		return requestExpires;
	}

	public void setRequestExpires(Date requestExpires)
	{
		this.requestExpires = requestExpires;
	}

	@Override
	public MessageType getMessageType() {
		return MessageType.ConversationRequest;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ConversationRequestMessageHeader [conversation=" + conversation
				+ ", requestExpires=" + requestExpires + ", toString()="
				+ super.toString() + "]";
	}


}