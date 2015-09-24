package org.socraticgrid.hl7.services.uc.model;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 16-Dec-2013 3:54:57 PM
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class NotificationMessageHeader extends MessageHeader implements Serializable {

	
	NotificationMessageHeader(String messageId) {
		super(messageId);
	}
	
	public NotificationMessageHeader()
	{
		
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@XmlElement(required=false)	
	private boolean replyAllowed=false;
	@XmlElement(required=false)	
	private UserContactInfo replyTo;
	@XmlElement(required=false)	
	private Set<Recipient> visableReceivers = new HashSet<>();

	public boolean isReplyAllowed()
	{
		return replyAllowed;
	}

	public void setReplyAllowed(boolean replyAllowed)
	{
		this.replyAllowed = replyAllowed;
	}

	public UserContactInfo getReplyTo()
	{
		return replyTo;
	}

	public void setReplyTo(UserContactInfo replyTo)
	{
		this.replyTo = replyTo;
	}

	public Set<Recipient> getVisableReceivers()
	{
		return visableReceivers;
	}

	public void setVisableReceivers(Set<Recipient> visableRecevers)
	{
		this.visableReceivers = visableRecevers;
	}

	@Override
	public MessageType getMessageType() {
		return MessageType.Notification;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "NotificationMessageHeader [replyAllowed=" + replyAllowed
				+ ", replyTo=" + replyTo + ", visableReceivers="
				+ visableReceivers + ", toString()=" + super.toString() + "]";
	}

}