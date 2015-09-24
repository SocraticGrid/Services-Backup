package org.socraticgrid.hl7.services.uc.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.jws.WebMethod;

/**
 * @version 1.0
 * @created 16-Dec-2013 3:54:56 PM
 */
public class Conversation implements Serializable
{

	private static final long serialVersionUID = 1L;
	private String conversationId;
	private Set<ConversationChannel> channels = new HashSet<>();
	private UserContactInfo owner;

	private Set<DeliveryAddress> participants = new HashSet<>();
	
	public Set<DeliveryAddress> getParticipants()
	{
		return participants;
	}

	public void setParticipants(Set<DeliveryAddress> participants)
	{
		this.participants = participants;
	}


	public String getConversationId()
	{
		return conversationId;
	}

	public void setConversationId(String id)
	{
		this.conversationId = id;
	}

	public Set<ConversationChannel> getChannels()
	{
		return channels;
	}

	public void setChannels(Set<ConversationChannel> channels)
	{
		this.channels = channels;
	}

	public UserContactInfo getOwner()
	{
		return owner;
	}

	public void setOwner(UserContactInfo owner)
	{
		this.owner = owner;
	}
	
	@WebMethod(exclude=true)
	private String getOwnerId(){
		if(owner==null) {
			return null;
		}
		return owner.getUserContactInfoId();
	}
}