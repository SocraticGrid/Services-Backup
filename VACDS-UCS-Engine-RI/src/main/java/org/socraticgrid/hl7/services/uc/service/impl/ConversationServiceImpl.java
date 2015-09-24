package org.socraticgrid.hl7.services.uc.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.jws.WebParam;

import org.socraticgrid.hl7.services.uc.exceptions.BadBodyException;
import org.socraticgrid.hl7.services.uc.exceptions.DeliveryException;
import org.socraticgrid.hl7.services.uc.exceptions.FeatureNotSupportedException;
import org.socraticgrid.hl7.services.uc.exceptions.InvalidAddressException;
import org.socraticgrid.hl7.services.uc.exceptions.InvalidContentException;
import org.socraticgrid.hl7.services.uc.exceptions.InvalidConversationException;
import org.socraticgrid.hl7.services.uc.exceptions.InvalidMessageException;
import org.socraticgrid.hl7.services.uc.exceptions.InvalidQueryException;
import org.socraticgrid.hl7.services.uc.exceptions.MissingBodyTypeException;
import org.socraticgrid.hl7.services.uc.exceptions.NotConnectedException;
import org.socraticgrid.hl7.services.uc.exceptions.ReadOnlyException;
import org.socraticgrid.hl7.services.uc.exceptions.ServiceAdapterFaultException;
import org.socraticgrid.hl7.services.uc.exceptions.UndeliverableMessageException;
import org.socraticgrid.hl7.services.uc.exceptions.UnknownServiceException;
import org.socraticgrid.hl7.services.uc.exceptions.UpdateException;
import org.socraticgrid.hl7.services.uc.interfaces.ConversationIntf;
import org.socraticgrid.hl7.services.uc.model.Conversation;
import org.socraticgrid.hl7.services.uc.model.ConversationInfo;
import org.socraticgrid.hl7.services.uc.model.QueryFilter;
//TODO - Move Implementation Logic to and Implementation class that we delegate
//to to allow other frontends to be used for this service
import org.socraticgrid.hl7.services.uc.model.Recipient;



public class ConversationServiceImpl implements Serializable, ConversationIntf
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Conversation connectConverstation(
			String conversationId)
			throws InvalidConversationException, InvalidAddressException,
			UnknownServiceException, FeatureNotSupportedException,
			ServiceAdapterFaultException, UndeliverableMessageException,
			ReadOnlyException
	{
		throw new FeatureNotSupportedException(
				"Conversations are not supported");
		// return null;
	}

	/**
	 * @param conversation
	 * @return The Conversation Id
	 */
	@Override
	public String createConversation(
			Conversation conversation)
			throws InvalidConversationException, InvalidContentException,
			MissingBodyTypeException, BadBodyException,
			InvalidAddressException, UnknownServiceException,
			FeatureNotSupportedException
	{
		throw new FeatureNotSupportedException(
				"Conversations are not supported");
		// TODO Validate the conversation
		// TODO Assign the conversation an id
		// TODO Save the conversation
		// TODO Return the Conversation id
		// return null;
	}

	@Override
	public Conversation disconnectConverstation(
			String conversationid)
			throws InvalidConversationException, NotConnectedException,
			UnknownServiceException, DeliveryException,
			FeatureNotSupportedException, ServiceAdapterFaultException,
			UndeliverableMessageException
	{
		throw new FeatureNotSupportedException(
				"Conversations are not supported");
		// return null;
	}

	@Override
	public List<Conversation> queryConversions(
			String query,
			 List<QueryFilter> filters)
			throws InvalidQueryException, FeatureNotSupportedException
	{
		throw new FeatureNotSupportedException(
				"Conversations are not supported");
		// return null;
	}

	@Override
	public ConversationInfo retrieveConversation(
		String conversationId)
			throws InvalidConversationException, FeatureNotSupportedException
	{
		throw new FeatureNotSupportedException(
				"Conversations are not supported");
		// return null;
	}

	@Override
	public boolean updateConversation(
			@WebParam(name = "conversationId") String converdationId,
			@WebParam(name = "conversation") Conversation conversation)
			throws InvalidConversationException, InvalidAddressException,
			UnknownServiceException, FeatureNotSupportedException,
			ServiceAdapterFaultException, UpdateException, ReadOnlyException
	{
		throw new FeatureNotSupportedException(
				"Conversations are not supported");
		// return false;
	}
	
	@Override
	public boolean addMessagesToConversation(String conversationId,
			List<String> messageIds) throws InvalidConversationException,
			InvalidMessageException, UnknownServiceException,
			FeatureNotSupportedException, ReadOnlyException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeMessagesFromConversation(String conversationId,
			List<String> messageIds) throws InvalidConversationException,
			InvalidMessageException {
		// TODO Auto-generated method stub
		return false;
	}



}
