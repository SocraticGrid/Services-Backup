package org.socraticgrid.hl7.services.ucs.accessclients.conversation;

import java.util.List;

import javax.jws.WebMethod;
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

@WebService(targetNamespace = "org.socraticgrid.hl7.services.uc", name = "ConversationServiceService")
public interface ConversationServiceSEI {

	@WebMethod
	public String createConversation(Conversation conversation)
			throws InvalidConversationException, InvalidContentException,
			MissingBodyTypeException, BadBodyException,
			InvalidAddressException, FeatureNotSupportedException,
			UnknownServiceException;

	@WebMethod
	public Conversation connectConverstation(String conversationId)
			throws InvalidConversationException, InvalidAddressException,
			UnknownServiceException, FeatureNotSupportedException,
			ServiceAdapterFaultException, UndeliverableMessageException,
			ReadOnlyException;

	@WebMethod
	public Conversation disconnectConverstation(String conversationId)
			throws FeatureNotSupportedException, InvalidConversationException,
			NotConnectedException, UnknownServiceException,
			ServiceAdapterFaultException, UndeliverableMessageException,
			DeliveryException;

	@WebMethod
	public List<Conversation> queryConversions(String query,
			List<QueryFilter> filters) throws FeatureNotSupportedException,
			InvalidQueryException;

	@WebMethod
	public ConversationInfo retrieveConversation(String conversationId)
			throws InvalidConversationException, FeatureNotSupportedException;

	@WebMethod
	public boolean updateConversation(String conversationId,
			Conversation conversation) throws FeatureNotSupportedException,
			InvalidConversationException, InvalidAddressException,
			UnknownServiceException, ServiceAdapterFaultException,
			UpdateException, ReadOnlyException;

	@WebMethod
	public boolean addMessagesToConversation(String conversationId,
			List<String> messageIds) throws InvalidConversationException,
			InvalidMessageException, UnknownServiceException,
			FeatureNotSupportedException, ReadOnlyException;

	@WebMethod
	public boolean removeMessagesFromConversation(String conversationId,
			List<String> messageIds) throws InvalidConversationException,
			InvalidMessageException;

	// @WebMethod

}
