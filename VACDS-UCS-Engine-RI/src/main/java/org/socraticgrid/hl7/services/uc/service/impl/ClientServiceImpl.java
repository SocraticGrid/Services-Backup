/**
 * 
 */
package org.socraticgrid.hl7.services.uc.service.impl;

import java.util.LinkedList;
import java.util.List;

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
import org.socraticgrid.hl7.services.uc.exceptions.UCSException;
import org.socraticgrid.hl7.services.uc.exceptions.UndeliverableMessageException;
import org.socraticgrid.hl7.services.uc.exceptions.UnknownServiceException;
import org.socraticgrid.hl7.services.uc.exceptions.UnknownUserException;
import org.socraticgrid.hl7.services.uc.exceptions.UpdateException;
import org.socraticgrid.hl7.services.uc.functional.AddressResolver;
import org.socraticgrid.hl7.services.uc.functional.DeliveryManager;
import org.socraticgrid.hl7.services.uc.functional.EventLogger;
import org.socraticgrid.hl7.services.uc.functional.MessageIdGenerator;
import org.socraticgrid.hl7.services.uc.functional.MessageManager;
import org.socraticgrid.hl7.services.uc.functional.MessageValidator;
import org.socraticgrid.hl7.services.uc.functional.UserManager;
import org.socraticgrid.hl7.services.uc.interfaces.ClientIntf;
import org.socraticgrid.hl7.services.uc.logging.EventLevel;
import org.socraticgrid.hl7.services.uc.logging.LogEntryType;
import org.socraticgrid.hl7.services.uc.model.CommunicationsPreferences;
import org.socraticgrid.hl7.services.uc.model.Message;
import org.socraticgrid.hl7.services.uc.model.MessageModel;
import org.socraticgrid.hl7.services.uc.model.MessageSummary;
import org.socraticgrid.hl7.services.uc.model.QueryScope;
import org.socraticgrid.hl7.services.uc.model.Recipient;
import org.socraticgrid.hl7.services.uc.model.UserContactInfo;
import org.socraticgrid.hl7.services.uc.operational.MessageWrapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Jerry Goodnough
 * 
 */
public class ClientServiceImpl implements ClientIntf {
	
	
	@Autowired
	DeliveryManager deliveryMgr;
	@Autowired
	MessageIdGenerator idGen;
	@Autowired
	MessageManager msgMgr;
	@Autowired
	MessageValidator msgVal;
	@Autowired
	AddressResolver resolver;

	@Autowired
	EventLogger evtLogger;

	@Autowired
	UserManager userMgr;

	public boolean assertPresence(String userId,
			String context,
			String status)
			throws FeatureNotSupportedException, UnknownUserException {
		evtLogger
				.logSummaryEvent(LogEntryType.User_Presence, EventLevel.debug,
						context, "AssertPresense", "Assert " + userId + " as "
								+ status);
		UserContactInfo user = userMgr.findUser(userId);
		if (user == null) {
			UnknownUserException exp = new UnknownUserException("UserId: "
					+ userId + " is not known");
			evtLogger.logUserExceptionEvent("assertPresence", exp);
			throw exp;
		}

		// If presence if not supported
		// throw new FeatureNotSupported("Presence is not a supported feature");

		return false;
	}

	public boolean cancelMessage(
			 String messageId,
			 boolean requireRetratcion)
			throws InvalidMessageException, FeatureNotSupportedException,
			ServiceOfflineException, ReadOnlyException {

		evtLogger.logSummaryEvent(LogEntryType.User_CancelMessage, EventLevel.debug,
				"", "CancelMessage", "Try to cancel message with Id "
						+ messageId);
		MessageModel<? extends Message> messageModel = msgMgr.getMessageById(messageId);
		if (messageModel == null) {
			InvalidMessageException exp = new InvalidMessageException(
					"Message " + messageId + " is not known");
			evtLogger.logUserExceptionEvent("cancelMessage", exp);
			throw exp;
		}
		evtLogger.logSummaryEvent(LogEntryType.User_CancelMessage, EventLevel.info,
				"", "CancelMessage", "Cancel Message", messageModel);
		// TODO Check if message is readonly
		// TODO Check message state -
		// If unsent - Remove the message
		// IfIf
		// return false;
		FeatureNotSupportedException exp = new FeatureNotSupportedException(
				"Cancel is not supported for this message");
		evtLogger.logUserExceptionEvent("cancelMessage", exp, messageModel);
		throw exp;

	}

	public <T extends Message> MessageModel<T> createMessage(
			 MessageModel<T> messageModel) {

		// Assign the message an Id
		evtLogger.logSummaryEvent(LogEntryType.User_Modification, EventLevel.debug,
				"", "CreateMessage", "Creating Message", messageModel);
		String messageId = idGen.getNewMessageId();
		evtLogger.logSummaryEvent(LogEntryType.User_Modification, EventLevel.debug,
				"", "CreateMessage", "Id Generated as " + messageId,
				messageModel);
		messageModel.getMessageType().getHeader().setMessageId(messageId);

		evtLogger.logSummaryEvent(LogEntryType.User_Modification, EventLevel.debug,
				"", "CreateMessage", "Saving message", messageModel);
		// Save the Message
		msgMgr.saveMessage(messageModel);
		evtLogger.logSummaryEvent(LogEntryType.User_Modification, EventLevel.info,
				"", "CreateMessage", "Message Created", messageModel);
		return messageModel;

	}

	public List<MessageSummary> queryMessage(
			String query)
			throws InvalidQueryException {
		// DO do update the output form
		return null;
	}

	public List<String> queryUsers( String query)
			throws InvalidQueryException {
		return null;
	}

	@SuppressWarnings("unchecked")
	public  MessageModel<? extends Message> retrieveMessage(
			String messageId) throws InvalidMessageException {
		evtLogger.logSummaryEvent(LogEntryType.User_RetrieveMessage,
				EventLevel.debug, "", "RetrieveMessage",
				"Retrieve Message with Id = " + messageId);
		MessageModel<? extends Message> messageModel = msgMgr.getMessageById(messageId);
		if (messageModel == null) {
			InvalidMessageException exp = new InvalidMessageException(
					"Message " + messageId + " is not known");
			evtLogger.logUserExceptionEvent("retrieveMessage", exp);
			throw exp;
		}
		evtLogger.logSummaryEvent(LogEntryType.User_RetrieveMessage,
				EventLevel.info, "", "RetrieveMessage",
				"Retrieved Message with Id = " + messageId, messageModel);
		return messageModel;
	}

	public UserContactInfo retrieveUser( String userId)
			throws UnknownUserException {
		return null;
	}

	public <T extends Message> String sendMessage(
			MessageModel<T> messageModel)
			throws InvalidMessageException, InvalidContentException,
			MissingBodyTypeException, BadBodyException,
			InvalidAddressException, UnknownServiceException,
			DeliveryException, MessageDeliveryTimeoutException,
			ServiceAdapterFaultException, UndeliverableMessageException,
			FeatureNotSupportedException, ServiceOfflineException,
			UpdateException, ReadOnlyException {
		evtLogger.logSummaryEvent(LogEntryType.User_SendMessage, EventLevel.info,
				"", "SendMessage", "Validating Message", messageModel);
		try {
			msgVal.validateMessage(messageModel);

		} catch (InvalidMessageException | DeliveryException
				| FeatureNotSupportedException | ServiceOfflineException
				| UpdateException | ReadOnlyException exp) {
			evtLogger.logUserExceptionEvent("sendMessage", exp);
			throw exp;

		} catch (UCSException exp) {
			evtLogger.logUserExceptionEvent("sendMessage", exp);
			throw new InvalidMessageException(exp.getFault(),
					exp.getGeneratingMessage());
		}

		// Assign the message and Id

		String messageId = idGen.getNewMessageId();
		evtLogger.logSummaryEvent(LogEntryType.User_SendMessage, EventLevel.info,
				"", "SendMessage", "Message Id Generated = " + messageId);

		messageModel.getMessageType().getHeader().setMessageId(messageId);

		// Create tracking structures

		MessageWrapper<T> wrapper = new MessageWrapper<>(messageModel);
		evtLogger.logSummaryEvent(LogEntryType.User_SendMessage, EventLevel.info,
				"", "SendMessage", "Resolving Addressess");
		resolver.resolveAddresses(wrapper);
		evtLogger.logSummaryEvent(LogEntryType.User_AcceptanceForDelivery,
				EventLevel.info, "", "SendMessage",
				"Saving Message prior to requesting delivery");
		// Save the message
		msgMgr.saveMessage(messageModel);

		// TODO: Save Tracking Structures
		evtLogger.logSummaryEvent(LogEntryType.User_AcceptanceForDelivery,
				EventLevel.info, "", "SendMessage", "Hand off for delivery");
		// Hand off to delivery manager
		deliveryMgr.deliverMessage(wrapper);

		return messageId;
	}

	public void sendMessageById( String messageId)
			throws InvalidMessageException, InvalidContentException,
			MissingBodyTypeException, BadBodyException,
			InvalidAddressException, UnknownServiceException,
			DeliveryException, MessageDeliveryTimeoutException,
			ServiceAdapterFaultException, UndeliverableMessageException,
			FeatureNotSupportedException, ServiceOfflineException,
			UpdateException, ReadOnlyException {

		evtLogger.logSummaryEvent(LogEntryType.User_SendMessage, EventLevel.debug,
				"", "SendMessage", "Fetching message with Id = " + messageId);

		MessageModel<? extends Message> messageModel = msgMgr.getMessageById(messageId);
		if (messageModel == null) {
			InvalidMessageException exp = new InvalidMessageException(
					"Message " + messageId + " is not known");
			evtLogger.logUserExceptionEvent("SendMessage", exp);
			throw exp;
		}
		evtLogger.logSummaryEvent(LogEntryType.User_SendMessage, EventLevel.debug,
				"", "SendMessage", "Validating message", messageModel);

		try {
			msgVal.validateMessage(messageModel);
		} catch (InvalidMessageException | DeliveryException
				| FeatureNotSupportedException | ServiceOfflineException
				| UpdateException | ReadOnlyException exp) {
			evtLogger.logUserExceptionEvent("sendMessage", exp);
			throw exp;

		} catch (UCSException exp) {
			evtLogger.logUserExceptionEvent("sendMessage", exp);
			throw new InvalidMessageException(exp.getFault(),
					exp.getGeneratingMessage());
		}

		// Create tracking structures and Save as required
		MessageWrapper<? extends Message> wrapper = new MessageWrapper<>(messageModel);
		
		// Call Address resolution
		evtLogger.logSummaryEvent(LogEntryType.User_SendMessage, EventLevel.debug,
				"", "SendMessage", "Resolving Addressess");
		resolver.resolveAddresses(wrapper);

		// Hand off to delivery manager
		evtLogger.logSummaryEvent(LogEntryType.User_AcceptanceForDelivery,
				EventLevel.debug, "", "SendMessage", "Hand off for delivery");
		deliveryMgr.deliverMessage(wrapper);
		return;
	}

	public boolean updateCommunicationsPreferences(
			 String userId,
			 CommunicationsPreferences prefs)
			throws UnknownUserException, UpdateException {
		return false;
	}

	public <T extends Message> boolean updateMessage(
			String messageId,
			 MessageModel<T> newMessageModel,
			boolean checkUpdate) throws InvalidMessageException,
			InvalidContentException, MissingBodyTypeException,
			BadBodyException, InvalidAddressException, UnknownServiceException,
			FeatureNotSupportedException, UpdateException, ReadOnlyException {

		MessageModel<? extends Message> messageModel = null;
		messageModel = msgMgr.getMessageById(messageId);
		if (messageModel == null) {
			InvalidMessageException exp = new InvalidMessageException("Message " + messageId
					+ " is not known");
			evtLogger.logUserExceptionEvent("updateMessage", exp);
		}

		// TODO Check if message is readonly
		// TODO Check message state -

		// Validate the message update
		// Implement the message update

		FeatureNotSupportedException exp = new FeatureNotSupportedException("Update is not yet implemented");
		evtLogger.logUserExceptionEvent("updateMessage", exp);
		throw exp;
		// return false;
	}
	


	@Override
	public List<String> findSupportedContent(List<Recipient> recipients, QueryScope scope)
			throws InvalidAddressException, UnknownServiceException,
			FeatureNotSupportedException {
		List<String> result = new LinkedList<String>();
		return result;
	}
}
