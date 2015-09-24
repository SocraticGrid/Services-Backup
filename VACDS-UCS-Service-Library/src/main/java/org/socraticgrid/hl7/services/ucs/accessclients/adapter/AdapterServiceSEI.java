package org.socraticgrid.hl7.services.ucs.accessclients.adapter;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.socraticgrid.hl7.services.uc.exceptions.ExceptionType;
import org.socraticgrid.hl7.services.uc.exceptions.FeatureNotSupportedException;
import org.socraticgrid.hl7.services.uc.exceptions.InvalidConversationException;
import org.socraticgrid.hl7.services.uc.exceptions.InvalidMessageException;
import org.socraticgrid.hl7.services.uc.exceptions.ProcessingException;
import org.socraticgrid.hl7.services.uc.exceptions.ReadOnlyException;
import org.socraticgrid.hl7.services.uc.exceptions.UndeliverableMessageException;
import org.socraticgrid.hl7.services.uc.exceptions.UnknownServiceException;
import org.socraticgrid.hl7.services.uc.exceptions.UpdateException;
import org.socraticgrid.hl7.services.uc.model.Conversation;
import org.socraticgrid.hl7.services.uc.model.DeliveryAddress;



@WebService(targetNamespace = "org.socraticgrid.hl7.services.uc", name = "AdapterServiceService")
public interface AdapterServiceSEI {

	@WebMethod
	public boolean conversationReady(Conversation conversation, String adapterIdentity)
			throws InvalidConversationException, UndeliverableMessageException,
			ReadOnlyException, FeatureNotSupportedException;
	
	@WebMethod
	public boolean conversationUpdate(String conversationId,
			Conversation conversation,String adapterIdentity) throws InvalidConversationException,
			FeatureNotSupportedException, ReadOnlyException,
			UnknownServiceException, UpdateException;
	
	@WebMethod
	public boolean postException(ProcessingException exception,
			ExceptionType type, String messageId, DeliveryAddress receiver, String adapterIdentity)
			throws InvalidMessageException, InvalidConversationException;
	//@WebMethod
 
}
