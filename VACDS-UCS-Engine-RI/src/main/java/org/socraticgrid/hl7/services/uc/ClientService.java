/**
 * 
 */
package org.socraticgrid.hl7.services.uc;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.socraticgrid.hl7.services.uc.interfaces.ClientIntf;
import org.socraticgrid.hl7.services.uc.model.CommunicationsPreferences;
import org.socraticgrid.hl7.services.uc.model.Message;
import org.socraticgrid.hl7.services.uc.model.MessageModel;
import org.socraticgrid.hl7.services.uc.model.MessageSummary;
import org.socraticgrid.hl7.services.uc.model.QueryScope;
import org.socraticgrid.hl7.services.uc.model.Recipient;
import org.socraticgrid.hl7.services.uc.model.UserContactInfo;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Provides a SOAP front end for the client service
 * 
 * @author Steven Elliott and Jerry Goodnough
 * 
 */


@WebService(name = "client", targetNamespace = "org.socraticgrid.hl7.services.uc")
public class ClientService
{

	private final Logger logger = LoggerFactory.getLogger(ClientService.class);
	
	
	@Autowired
	ClientIntf clientService;

	public boolean assertPresence(@WebParam(name = "userId") String userId,
			@WebParam(name = "context") String context,
			@WebParam(name = "status") String status)
			throws FeatureNotSupportedException, UnknownUserException
	{
		return clientService.assertPresence(userId, context, status);
	}

	public boolean cancelMessage(
			@WebParam(name = "messageId") String messageId,
			@WebParam(name = "requireRetraction") boolean requireRetratcion)
			throws InvalidMessageException, FeatureNotSupportedException,
			ServiceOfflineException, ReadOnlyException
	{

		return clientService.cancelMessage(messageId, requireRetratcion);

	}

	public <T extends Message> MessageModel<T> createMessage(
			@WebParam(name = "message") MessageModel<T> messageModel)
	{

		return clientService.createMessage(messageModel);

	}

	public List<MessageSummary> queryMessage(
			@WebParam(name = "query") String query)
			throws InvalidQueryException
	{
		return clientService.queryMessage(query);
	}

	public List<String> queryUsers(@WebParam(name = "query") String query)
			throws InvalidQueryException
	{
		return clientService.queryUsers(query);
	}

	public <T extends Message> MessageModel<T> retieveMessage(
			@WebParam(name = "messageId") String messageId)
			throws InvalidMessageException
	{
		return clientService.retrieveMessage(messageId);
	}

	public UserContactInfo retrieveUser(@WebParam(name = "userId") String userId)
			throws UnknownUserException
	{
		return clientService.retrieveUser(userId);
	}

	public <T extends Message> String sendMessage(
			@WebParam(name = "message") MessageModel<T> messageModel)
			throws InvalidMessageException, InvalidContentException,
			MissingBodyTypeException, BadBodyException,
			InvalidAddressException, UnknownServiceException,
			DeliveryException, MessageDeliveryTimeoutException,
			ServiceAdapterFaultException, UndeliverableMessageException,
			FeatureNotSupportedException, ServiceOfflineException,
			UpdateException, ReadOnlyException
	{
		if (clientService == null)
		{
			logger.error("Client Service Impl is not initialized");
			return null;
		}
		logger.info("Send Message Called");
		logger.info("Client Servive = "+clientService);
		logger.info("MessageModel = "+messageModel);
		return clientService.sendMessage(messageModel);
	}

	public void sendMessageById(@WebParam(name = "messageId") String messageId)
			throws InvalidMessageException, InvalidContentException,
			MissingBodyTypeException, BadBodyException,
			InvalidAddressException, UnknownServiceException,
			DeliveryException, MessageDeliveryTimeoutException,
			ServiceAdapterFaultException, UndeliverableMessageException,
			FeatureNotSupportedException, ServiceOfflineException,
			UpdateException, ReadOnlyException
	{
		clientService.sendMessageById(messageId);
	}

	public boolean updateCommunicationsPreferences(
			@WebParam(name = "userId") String userId,
			@WebParam(name = "commPrefs") CommunicationsPreferences prefs)
			throws UnknownUserException, UpdateException
	{
		return clientService.updateCommunicationsPreferences(userId, prefs);
	}

	public <T extends Message> boolean updateMessage(
			@WebParam(name = "messageId") String messageId,
			@WebParam(name = "message") MessageModel<T> newMessageModel,
			boolean checkUpdate) throws InvalidMessageException,
			InvalidContentException, MissingBodyTypeException,
			BadBodyException, InvalidAddressException, UnknownServiceException,
			FeatureNotSupportedException, UpdateException, ReadOnlyException
	{

		return clientService.updateMessage(messageId, newMessageModel, checkUpdate);
	}


	public List<String> findSupportedContent(@WebParam(name = "recipients")List<Recipient> recipients, @WebParam(name = "scope")QueryScope scope)
			throws InvalidAddressException, UnknownServiceException,
			FeatureNotSupportedException {
		return clientService.findSupportedContent(recipients,scope);
	}

}
