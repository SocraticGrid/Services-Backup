package org.socraticgrid.hl7.services.uc.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 16-Dec-2013 3:54:56 PM
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ConversationRequestMessage extends Message implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@XmlElement(required=true)	
	private ConversationRequestMessageHeader conversationMessageHeader;

	public ConversationRequestMessage(String messageId) {
		this.conversationMessageHeader  = new ConversationRequestMessageHeader(messageId);
	}

	public ConversationRequestMessage(ConversationRequestMessageHeader header) {
		this.conversationMessageHeader  = header;
	}
	
	public ConversationRequestMessage()
	{
		
	}

	@Override
	public ConversationRequestMessageHeader getHeader()
	{
		return conversationMessageHeader;
	}

	@Override
	MessageType getMessageType() {
		return MessageType.ConversationRequest;
	}

}