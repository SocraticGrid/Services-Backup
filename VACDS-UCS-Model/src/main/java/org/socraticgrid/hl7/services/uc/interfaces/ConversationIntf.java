package org.socraticgrid.hl7.services.uc.interfaces;

import java.util.List;

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

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 16-Dec-2013 3:54:56 PM
 */
public interface ConversationIntf {

	public String createConversation(Conversation conversation)
			throws InvalidConversationException, InvalidContentException,
			MissingBodyTypeException, BadBodyException,
			InvalidAddressException, FeatureNotSupportedException,
			UnknownServiceException;

	public Conversation connectConverstation(String conversationId)
			throws InvalidConversationException, InvalidAddressException,
			UnknownServiceException, FeatureNotSupportedException,
			ServiceAdapterFaultException, UndeliverableMessageException,
			ReadOnlyException;

	public Conversation disconnectConverstation(String conversationId)
			throws FeatureNotSupportedException, InvalidConversationException,
			NotConnectedException, UnknownServiceException,
			ServiceAdapterFaultException, UndeliverableMessageException,
			DeliveryException;

	public List<Conversation> queryConversions(String query,
			List<QueryFilter> filters) throws FeatureNotSupportedException,
			InvalidQueryException;

	public ConversationInfo retrieveConversation(String conversationId)
			throws InvalidConversationException, FeatureNotSupportedException;

	public boolean updateConversation(String conversationId,
			Conversation conversation) throws FeatureNotSupportedException,
			InvalidConversationException, InvalidAddressException,
			UnknownServiceException, ServiceAdapterFaultException,
			UpdateException, ReadOnlyException;

	public boolean addMessagesToConversation(String conversationId,
			List<String> messageIds) throws InvalidConversationException,
			InvalidMessageException, UnknownServiceException,
			FeatureNotSupportedException, ReadOnlyException;

	public boolean removeMessagesFromConversation(String conversationId,
			List<String> messageIds) throws InvalidConversationException,
			InvalidMessageException;
}