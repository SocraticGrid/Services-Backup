package org.socraticgrid.hl7.services.uc.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;

/**
 * Provide as summary of the message - Currently a delegate to the message header
 * @author Jerry Goodnough
 *
 */
public class MessageSummary implements Serializable
{

	/**
	 * 
	 */
	public MessageSummary()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MessageHeader hdr;
	
	public MessageSummary(MessageHeader hdr)
	{
		this.hdr=hdr;
	}

	public MessageType getMessageType()
	{
		return hdr.getMessageType();
	}

	public String getMessageId()
	{
		return hdr.getMessageId();
	}

	public DeliveryAddress getSender()
	{
		return hdr.getSender();
	}

	public Set<Recipient> getRecipientsList()
	{
		return hdr.getRecipientsList();
	}

	public String getSubject()
	{
		return hdr.getSubject();
	}

	public String getRelatedConversationId()
	{
		return hdr.getRelatedConversationId();
	}

	public Date getCreated()
	{
		return hdr.getCreated();
	}

	public Date getLastModified()
	{
		return hdr.getLastModified();
	}

	public DeliveryGuarantee getDeliveryGuarantee()
	{
		return hdr.getDeliveryGuarantee();
	}

	public List<DeliveryStatus> getDeliveryStatusList()
	{
		return hdr.getDeliveryStatusList();
	}

	public MessageDynamics getDynamics()
	{
		return hdr.getDynamics();
	}

	public int getPriority()
	{
		return hdr.getPriority();
	}

	public boolean isReceiptNotification()
	{
		return hdr.isReceiptNotification();
	}

	public boolean isRetainFullyInLog()
	{
		return hdr.isRetainFullyInLog();
	}

	public int getTimeout()
	{
		return hdr.getTimeout();
	}

	public Properties getProperties()
	{
		return hdr.getProperties();
	}

	public String getRelatedMessageId()
	{
		return hdr.getRelatedMessageId();
	}
	
	
}
