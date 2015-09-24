package org.socraticgrid.hl7.services.uc.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 16-Dec-2013 3:54:57 PM
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class NotificationMessage extends Message implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@XmlElement(required=true)	
	private NotificationMessageHeader notificationMessageHeader;
	
	public NotificationMessage(String messageId) {
		this.notificationMessageHeader = new NotificationMessageHeader(messageId);
	}
	
	public NotificationMessage(NotificationMessageHeader header) {
		this.notificationMessageHeader = header;
	}

	public NotificationMessage()
	{
		this.notificationMessageHeader = new NotificationMessageHeader();
	}
	
	@Override
	public NotificationMessageHeader getHeader()
	{
		return notificationMessageHeader;
	}


	@Override
	MessageType getMessageType() {
		// TODO Auto-generated method stub
		return MessageType.Notification;
	}
}