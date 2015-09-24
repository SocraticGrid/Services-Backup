package org.socraticgrid.hl7.services.ucs.accessclients.client;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

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

@WebService(targetNamespace = "org.socraticgrid.hl7.services.uc", name = "ClientServiceService")
public interface  ClientServiceSEI   {

	@WebMethod
	public boolean assertPresence(String userId, String context, String status)
			throws FeatureNotSupportedException, UnknownUserException;

	@WebMethod
	public boolean cancelMessage(String messageId, boolean requireRetratcion)
			throws InvalidMessageException, FeatureNotSupportedException,
			ServiceOfflineException, ReadOnlyException;
	
	@WebMethod
	public <T extends Message> MessageModel<T> createMessage(
			MessageModel<T> BaseMessage);
	
	@WebMethod
	public List<MessageSummary> queryMessage(String query)
			throws InvalidQueryException;
	
	@WebMethod
	public List<String> queryUsers(String query) throws InvalidQueryException;
	
	@WebMethod
	public <T extends Message> MessageModel<T> retrieveMessage(String messageId)
			throws InvalidMessageException;

	@WebMethod
	public UserContactInfo retrieveUser(String userId)
			throws UnknownUserException;
	@WebMethod
	public <T extends Message> String sendMessage(MessageModel<T> messageModel)
			throws InvalidMessageException, InvalidContentException,
			MissingBodyTypeException, BadBodyException,
			InvalidAddressException, UnknownServiceException,
			DeliveryException, MessageDeliveryTimeoutException,
			ServiceAdapterFaultException, UndeliverableMessageException,
			FeatureNotSupportedException, ServiceOfflineException,
			UpdateException, ReadOnlyException;
	
	@WebMethod
	public void sendMessageById(String MessageId)
			throws InvalidMessageException, InvalidContentException,
			MissingBodyTypeException, BadBodyException,
			InvalidAddressException, UnknownServiceException,
			DeliveryException, MessageDeliveryTimeoutException,
			ServiceAdapterFaultException, UndeliverableMessageException,
			FeatureNotSupportedException, ServiceOfflineException,
			UpdateException, ReadOnlyException;
	
	@WebMethod
	public boolean updateCommunicationsPreferences(String userId,
			CommunicationsPreferences prefs) throws UnknownUserException,
			UpdateException;
	
	@WebMethod
	public <T extends Message> boolean updateMessage(String messageId,
			MessageModel<T> newMessageModel, boolean checkUpdate)
			throws InvalidMessageException, InvalidContentException,
			MissingBodyTypeException, BadBodyException,
			InvalidAddressException, UnknownServiceException,
			FeatureNotSupportedException, UpdateException, ReadOnlyException;
	@WebMethod
	public List<String> findSupportedContent(List<Recipient> recipients,
			QueryScope scope) throws InvalidAddressException,
			UnknownServiceException, FeatureNotSupportedException;
	//@WebMethod
	

}
