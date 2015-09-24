package org.socraticgrid.hl7.services.uc.interfaces;

import java.util.List;

import javax.jws.WebParam;

import org.socraticgrid.hl7.services.uc.exceptions.BadBodyException;
import org.socraticgrid.hl7.services.uc.exceptions.DeliveryException;
import org.socraticgrid.hl7.services.uc.exceptions.FeatureNotSupportedException;
import org.socraticgrid.hl7.services.uc.exceptions.InvalidAddressException;
import org.socraticgrid.hl7.services.uc.exceptions.InvalidContentException;
import org.socraticgrid.hl7.services.uc.exceptions.InvalidMessageException;
import org.socraticgrid.hl7.services.uc.exceptions.InvalidQueryException;
import org.socraticgrid.hl7.services.uc.exceptions.MessageDeliveryTimeoutException;
import org.socraticgrid.hl7.services.uc.exceptions.MissingBodyTypeException;
import org.socraticgrid.hl7.services.uc.exceptions.ReadOnlyException;
import org.socraticgrid.hl7.services.uc.exceptions.ServiceAdapterFaultException;
import org.socraticgrid.hl7.services.uc.exceptions.ServiceOfflineException;
import org.socraticgrid.hl7.services.uc.exceptions.UndeliverableMessageException;
import org.socraticgrid.hl7.services.uc.exceptions.UnknownServiceException;
import org.socraticgrid.hl7.services.uc.exceptions.UnknownUserException;
import org.socraticgrid.hl7.services.uc.exceptions.UpdateException;
import org.socraticgrid.hl7.services.uc.model.CommunicationsPreferences;
import org.socraticgrid.hl7.services.uc.model.Message;
import org.socraticgrid.hl7.services.uc.model.MessageModel;
import org.socraticgrid.hl7.services.uc.model.MessageSummary;
import org.socraticgrid.hl7.services.uc.model.QueryScope;
import org.socraticgrid.hl7.services.uc.model.Recipient;
import org.socraticgrid.hl7.services.uc.model.UserContactInfo;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 16-Dec-2013 3:54:56 PM
 */
public interface ClientIntf {

	/**
	 * 
	 * @param BaseMessage
	 */

	public boolean assertPresence(String userId, String context, String status)
			throws FeatureNotSupportedException, UnknownUserException;

	public boolean cancelMessage(String messageId, boolean requireRetratcion)
			throws InvalidMessageException, FeatureNotSupportedException,
			ServiceOfflineException, ReadOnlyException;

	/**
	 * Create a new Message
	 * 
	 * @param BaseMessage
	 */

	public <T extends Message> MessageModel<T> createMessage(
			MessageModel<T> BaseMessage);

	/**
	 * Find matching messages - TODO: Refine this interface
	 * 
	 * @param query
	 * @return
	 */
	public List<MessageSummary> queryMessage(
			@WebParam(name = "query") String query)
			throws InvalidQueryException;

	public List<String> queryUsers(String query) throws InvalidQueryException;

	public <T extends Message> MessageModel<T> retrieveMessage(String messageId)
			throws InvalidMessageException;

	public UserContactInfo retrieveUser(String userId)
			throws UnknownUserException;

	/**
	 * Send a fully formed message, returning the message Id
	 * 
	 * @param Message
	 */
	public <T extends Message> String sendMessage(MessageModel<T> messageModel)
			throws InvalidMessageException, InvalidContentException,
			MissingBodyTypeException, BadBodyException,
			InvalidAddressException, UnknownServiceException,
			DeliveryException, MessageDeliveryTimeoutException,
			ServiceAdapterFaultException, UndeliverableMessageException,
			FeatureNotSupportedException, ServiceOfflineException,
			UpdateException, ReadOnlyException;

	/**
	 * Send a Message by Id
	 * 
	 * @param MessgaeId
	 */
	public void sendMessageById(String MessageId)
			throws InvalidMessageException, InvalidContentException,
			MissingBodyTypeException, BadBodyException,
			InvalidAddressException, UnknownServiceException,
			DeliveryException, MessageDeliveryTimeoutException,
			ServiceAdapterFaultException, UndeliverableMessageException,
			FeatureNotSupportedException, ServiceOfflineException,
			UpdateException, ReadOnlyException;

	public boolean updateCommunicationsPreferences(String userId,
			CommunicationsPreferences prefs) throws UnknownUserException,
			UpdateException;

	public <T extends Message> boolean updateMessage(String messageId,
			MessageModel<T> newMessageModel, boolean checkUpdate)
			throws InvalidMessageException, InvalidContentException,
			MissingBodyTypeException, BadBodyException,
			InvalidAddressException, UnknownServiceException,
			FeatureNotSupportedException, UpdateException, ReadOnlyException;

	public List<String> findSupportedContent(List<Recipient> recipients, QueryScope scope)
			throws InvalidAddressException, UnknownServiceException,
			FeatureNotSupportedException;

}