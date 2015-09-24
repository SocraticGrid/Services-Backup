package org.socraticgrid.hl7.services.uc;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.socraticgrid.hl7.services.uc.exceptions.ExceptionType;
import org.socraticgrid.hl7.services.uc.exceptions.FeatureNotSupportedException;
import org.socraticgrid.hl7.services.uc.exceptions.InvalidConversationException;
import org.socraticgrid.hl7.services.uc.exceptions.InvalidMessageException;
import org.socraticgrid.hl7.services.uc.exceptions.ReadOnlyException;
import org.socraticgrid.hl7.services.uc.exceptions.UndeliverableMessageException;
import org.socraticgrid.hl7.services.uc.exceptions.UnknownServiceException;
import org.socraticgrid.hl7.services.uc.exceptions.UpdateException;
import org.socraticgrid.hl7.services.uc.interfaces.AdapterIntf;
import org.socraticgrid.hl7.services.uc.model.Conversation;
import org.socraticgrid.hl7.services.uc.model.DeliveryAddress;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * @author Jerry Goodnough
 *
 */


@WebService(name="adapter"
		, targetNamespace="org.socraticgrid.hl7.services.uc" )
public class AdapterService {

	private Logger logger = LoggerFactory.getLogger(AdapterService.class);

	@Autowired 
	AdapterIntf adapterService;

	public boolean postException(@WebParam(name = "exception") org.socraticgrid.hl7.services.uc.exceptions.ProcessingException exception,@WebParam(name = "type") ExceptionType type, @WebParam(name = "messageId")  String messageId, @WebParam(name = "receiver")DeliveryAddress receiver, @WebParam(name = "adapterIdentity")String adapterIdentity) throws InvalidMessageException, InvalidConversationException {
		
		logger.debug("\n*********\n\t AdapterService.postException(...) message from adapterIdentity "+adapterIdentity+"\n********\n");

		return adapterService.postException(exception, type, messageId, receiver, adapterIdentity);
	}
	
	
	public boolean conversationReady(@WebParam(name = "conversation") Conversation conversation, @WebParam(name = "adapterIdentity")String adapterIdentity) throws InvalidConversationException, UndeliverableMessageException, ReadOnlyException, FeatureNotSupportedException
	{
		
		return adapterService.conversationReady(conversation,adapterIdentity);
	
	}
	
	public boolean conversationUpdate(@WebParam(name = "conversationId") String conversationId,@WebParam(name = "conversation")  Conversation conversation, @WebParam(name = "adapterIdentity")String adapterIdentity) throws InvalidConversationException, FeatureNotSupportedException, ReadOnlyException, UnknownServiceException, UpdateException
	{
		return adapterService.conversationUpdate(conversationId, conversation,adapterIdentity);
	}
	
}