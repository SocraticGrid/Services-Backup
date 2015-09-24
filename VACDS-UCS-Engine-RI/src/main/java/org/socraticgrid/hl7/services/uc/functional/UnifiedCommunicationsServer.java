package org.socraticgrid.hl7.services.uc.functional;

import java.util.List;

import org.socraticgrid.hl7.services.uc.exceptions.BadBodyException;
import org.socraticgrid.hl7.services.uc.exceptions.DeliveryException;
import org.socraticgrid.hl7.services.uc.exceptions.ExceptionType;
import org.socraticgrid.hl7.services.uc.exceptions.FeatureNotSupportedException;
import org.socraticgrid.hl7.services.uc.exceptions.InvalidAddressException;
import org.socraticgrid.hl7.services.uc.exceptions.InvalidContentException;
import org.socraticgrid.hl7.services.uc.exceptions.InvalidConversationException;
import org.socraticgrid.hl7.services.uc.exceptions.InvalidMessageException;
import org.socraticgrid.hl7.services.uc.exceptions.InvalidQueryException;
import org.socraticgrid.hl7.services.uc.exceptions.MessageDeliveryTimeoutException;
import org.socraticgrid.hl7.services.uc.exceptions.MissingBodyTypeException;
import org.socraticgrid.hl7.services.uc.exceptions.NotConnectedException;
import org.socraticgrid.hl7.services.uc.exceptions.ProcessingException;
import org.socraticgrid.hl7.services.uc.exceptions.ReadOnlyException;
import org.socraticgrid.hl7.services.uc.exceptions.ServiceAdapterFaultException;
import org.socraticgrid.hl7.services.uc.exceptions.ServiceOfflineException;
import org.socraticgrid.hl7.services.uc.exceptions.UndeliverableMessageException;
import org.socraticgrid.hl7.services.uc.exceptions.UnknownServiceException;
import org.socraticgrid.hl7.services.uc.exceptions.UnknownUserException;
import org.socraticgrid.hl7.services.uc.exceptions.UpdateException;
import org.socraticgrid.hl7.services.uc.interfaces.AdapterIntf;
import org.socraticgrid.hl7.services.uc.interfaces.AlertingIntf;
import org.socraticgrid.hl7.services.uc.interfaces.ClientIntf;
import org.socraticgrid.hl7.services.uc.interfaces.ConversationIntf;
import org.socraticgrid.hl7.services.uc.interfaces.ManagementIntf;
import org.socraticgrid.hl7.services.uc.logging.LogEntry;
import org.socraticgrid.hl7.services.uc.model.AlertMessage;
import org.socraticgrid.hl7.services.uc.model.CommunicationsPreferences;
import org.socraticgrid.hl7.services.uc.model.Conversation;
import org.socraticgrid.hl7.services.uc.model.ConversationInfo;
import org.socraticgrid.hl7.services.uc.model.DeliveryAddress;
import org.socraticgrid.hl7.services.uc.model.EventMetadata;
import org.socraticgrid.hl7.services.uc.model.EventType;
import org.socraticgrid.hl7.services.uc.model.Message;
import org.socraticgrid.hl7.services.uc.model.MessageModel;
import org.socraticgrid.hl7.services.uc.model.MessageSummary;
import org.socraticgrid.hl7.services.uc.model.QueryFilter;
import org.socraticgrid.hl7.services.uc.model.QueryScope;
import org.socraticgrid.hl7.services.uc.model.Recipient;
import org.socraticgrid.hl7.services.uc.model.ServiceInfo;
import org.socraticgrid.hl7.services.uc.model.Status;
import org.socraticgrid.hl7.services.uc.model.UserContactInfo;

/**
 * :Prototype as used to track interfaces 
 * 
 * @author Jerry Goodnough
 * @version 1.0
 * @created 17-Dec-2013 3:27:36 PM
 */
public class UnifiedCommunicationsServer implements AlertingIntf, AdapterIntf,
		ManagementIntf, ClientIntf, ConversationIntf
{

	public UnifiedCommunicationsServer()
	{

	}

	@Override
	public String createConversation(Conversation conversation)
			throws InvalidConversationException, InvalidContentException,
			MissingBodyTypeException, BadBodyException,
			InvalidAddressException, FeatureNotSupportedException,
			UnknownServiceException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Conversation connectConverstation(String conversationId)
			throws InvalidConversationException, InvalidAddressException,
			UnknownServiceException, FeatureNotSupportedException,
			ServiceAdapterFaultException, UndeliverableMessageException,
			ReadOnlyException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Conversation disconnectConverstation(String conversationId)
			throws FeatureNotSupportedException, InvalidConversationException,
			NotConnectedException, UnknownServiceException,
			ServiceAdapterFaultException, UndeliverableMessageException,
			DeliveryException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Conversation> queryConversions(String query,
			List<QueryFilter> filters) throws FeatureNotSupportedException,
			InvalidQueryException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConversationInfo retrieveConversation(String conversationId)
			throws InvalidConversationException, FeatureNotSupportedException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateConversation(String conversationId,
			Conversation conversation) throws FeatureNotSupportedException,
			InvalidConversationException, InvalidAddressException,
			UnknownServiceException, ServiceAdapterFaultException,
			UpdateException, ReadOnlyException
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean assertPresence(String userId, String context, String status)
			throws FeatureNotSupportedException, UnknownUserException
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean cancelMessage(String messageId, boolean requireRetratcion)
			throws InvalidMessageException, FeatureNotSupportedException,
			ServiceOfflineException, ReadOnlyException
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T extends Message> MessageModel<T> createMessage(
			MessageModel<T> BaseMessage)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MessageSummary> queryMessage(String query)
			throws InvalidQueryException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> queryUsers(String query) throws InvalidQueryException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends Message> MessageModel<T> retrieveMessage(
			String messageId) throws InvalidMessageException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserContactInfo retrieveUser(String userId)
			throws UnknownUserException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends Message> String sendMessage(
			MessageModel<T> messageModel) throws InvalidMessageException,
			InvalidContentException, MissingBodyTypeException,
			BadBodyException, InvalidAddressException, UnknownServiceException,
			DeliveryException, MessageDeliveryTimeoutException,
			ServiceAdapterFaultException, UndeliverableMessageException,
			FeatureNotSupportedException, ServiceOfflineException,
			UpdateException, ReadOnlyException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sendMessageById(String MessageId)
			throws InvalidMessageException, InvalidContentException,
			MissingBodyTypeException, BadBodyException,
			InvalidAddressException, UnknownServiceException,
			DeliveryException, MessageDeliveryTimeoutException,
			ServiceAdapterFaultException, UndeliverableMessageException,
			FeatureNotSupportedException, ServiceOfflineException,
			UpdateException, ReadOnlyException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean updateCommunicationsPreferences(String userId,
			CommunicationsPreferences prefs) throws UnknownUserException,
			UpdateException
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T extends Message> boolean updateMessage(String messageId,
			MessageModel<T> newMessageModel, boolean checkUpdate)
			throws InvalidMessageException, InvalidContentException,
			MissingBodyTypeException, BadBodyException,
			InvalidAddressException, UnknownServiceException,
			FeatureNotSupportedException, UpdateException, ReadOnlyException
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<ServiceInfo> discoverChannels()
			throws FeatureNotSupportedException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EventMetadata> getEventMetaData(EventType evtenType,
			List<String> eventId) throws InvalidMessageException,
			InvalidConversationException, FeatureNotSupportedException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EventMetadata> queryEventMetaData(String query)
			throws InvalidQueryException, FeatureNotSupportedException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Status> getStatus(String capablityType, List<String> capabilityIds)
			throws InvalidMessageException, InvalidContentException,
			InvalidConversationException, UnknownServiceException,
			ServiceAdapterFaultException, FeatureNotSupportedException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Conversation> getConversations(List<String> conversationIds)
			throws InvalidContentException, FeatureNotSupportedException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends Message> List<MessageModel<T>> getMessages(
			List<String> messageIds) throws InvalidMessageException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LogEntry> getEvents(List<String> messageIds)
			throws InvalidMessageException, FeatureNotSupportedException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean resumeChannel(List<String> channelIds)
			throws UnknownServiceException, ServiceAdapterFaultException,
			FeatureNotSupportedException
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean suspendChannel(List<String> channelIds)
			throws UnknownServiceException, ServiceAdapterFaultException,
			FeatureNotSupportedException
	{
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean updateAlert(AlertMessage alert) throws InvalidMessageException,
			InvalidContentException, UnknownServiceException,
			ServiceAdapterFaultException, UpdateException, ReadOnlyException
	{
		// TODO Auto-generated method stub
		return false;
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

	@Override
	public List<String> findSupportedContent(List<Recipient> recipients, QueryScope scope)
			throws InvalidAddressException, UnknownServiceException,
			FeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean conversationReady(Conversation conversation,
			String adapterIdentity) throws InvalidConversationException,
			UndeliverableMessageException, ReadOnlyException,
			FeatureNotSupportedException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean conversationUpdate(String conversationId,
			Conversation conversation, String adapterIdentity)
			throws InvalidConversationException, FeatureNotSupportedException,
			ReadOnlyException, UnknownServiceException, UpdateException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean postException(ProcessingException exception,
			ExceptionType type, String messageId, DeliveryAddress receiver,
			String adapterIdentity) throws InvalidMessageException,
			InvalidConversationException {
		// TODO Auto-generated method stub
		return false;
	}


}