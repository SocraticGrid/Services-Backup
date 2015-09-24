package org.socraticgrid.hl7.services.uc;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

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
import org.socraticgrid.hl7.services.uc.model.Conversation;
import org.socraticgrid.hl7.services.uc.model.ConversationInfo;
import org.socraticgrid.hl7.services.uc.model.QueryFilter;
import org.springframework.beans.factory.annotation.Autowired;
//TODO - Move Implementation Logic to and Implementation class that we delegate
//to to allow other frontends to be used for this service

@WebService(name = "conversation", targetNamespace = "org.socraticgrid.hl7.services.uc")
public class ConversationService {

	/**
	 * 
	 */

	@Autowired
	private org.socraticgrid.hl7.services.uc.interfaces.ConversationIntf conversationService;

	public Conversation connectConverstation(
			@WebParam(name = "conversationId") String conversationId)
			throws InvalidConversationException, InvalidAddressException,
			UnknownServiceException, FeatureNotSupportedException,
			ServiceAdapterFaultException, UndeliverableMessageException,
			ReadOnlyException {
		return conversationService.connectConverstation(conversationId);
	}

	/**
	 * @param conversation
	 * @return The Conversation Id
	 */
	public String createConversation(
			@WebParam(name = "conversation") Conversation conversation)
			throws InvalidConversationException, InvalidContentException,
			MissingBodyTypeException, BadBodyException,
			InvalidAddressException, UnknownServiceException,
			FeatureNotSupportedException {
		return conversationService.createConversation(conversation);
	}

	public Conversation disconnectConverstation(
			@WebParam(name = "conversationId") String conversationId)
			throws InvalidConversationException, NotConnectedException,
			UnknownServiceException, DeliveryException,
			FeatureNotSupportedException, ServiceAdapterFaultException,
			UndeliverableMessageException {
		return conversationService.disconnectConverstation(conversationId);
	}

	public List<Conversation> queryConversions(
			@WebParam(name = "query") String query,
			@WebParam(name = "filters") List<QueryFilter> filters)
			throws InvalidQueryException, FeatureNotSupportedException {
		return conversationService.queryConversions(query, filters);
	}

	public ConversationInfo retrieveConversation(
			@WebParam(name = "conversationId") String conversationId)
			throws InvalidConversationException, FeatureNotSupportedException {
		return conversationService.retrieveConversation(conversationId);
	}

	public boolean updateConversation(
			@WebParam(name = "conversationId") String conversationId,
			@WebParam(name = "conversation") Conversation conversation)
			throws InvalidConversationException, InvalidAddressException,
			UnknownServiceException, FeatureNotSupportedException,
			ServiceAdapterFaultException, UpdateException, ReadOnlyException {
		return conversationService.updateConversation(conversationId,
				conversation);
	}

	public boolean addMessagesToConversation(
			@WebParam(name = "conversationId") String conversationId,
			@WebParam(name = "messageIds") List<String> messageIds)
			throws InvalidConversationException, InvalidMessageException,
			UnknownServiceException, FeatureNotSupportedException,
			ReadOnlyException {
		return conversationService.addMessagesToConversation(conversationId,
				messageIds);
	}

	public boolean removeMessagesFromConversation(
			@WebParam(name = "conversationId") String conversationId,
			@WebParam(name = "messageIds") List<String> messageIds)
			throws InvalidConversationException, InvalidMessageException {
		return conversationService.removeMessagesFromConversation(conversationId,
				messageIds);
	}
}
