package org.socraticgrid.hl7.services.uc.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.socraticgrid.hl7.services.uc.exceptions.ExceptionType;
import org.socraticgrid.hl7.services.uc.exceptions.FeatureNotSupportedException;
import org.socraticgrid.hl7.services.uc.exceptions.InvalidConversationException;
import org.socraticgrid.hl7.services.uc.exceptions.InvalidMessageException;
import org.socraticgrid.hl7.services.uc.exceptions.ProcessingException;
import org.socraticgrid.hl7.services.uc.exceptions.ReadOnlyException;
import org.socraticgrid.hl7.services.uc.exceptions.UndeliverableMessageException;
import org.socraticgrid.hl7.services.uc.exceptions.UnknownServiceException;
import org.socraticgrid.hl7.services.uc.exceptions.UpdateException;
import org.socraticgrid.hl7.services.uc.functional.MessageManager;
import org.socraticgrid.hl7.services.uc.functional.WorkflowManager;
import org.socraticgrid.hl7.services.uc.interfaces.AdapterIntf;
import org.socraticgrid.hl7.services.uc.model.Conversation;
import org.socraticgrid.hl7.services.uc.model.DeliveryAddress;
import org.springframework.beans.factory.annotation.Autowired;

public class AdapterServiceImpl implements AdapterIntf
{
	@Autowired 
	MessageManager msgMgr;
	
	@Autowired
	WorkflowManager workflowMgr;
	
	private static Logger log = LoggerFactory.getLogger(AdapterServiceImpl.class);
	
	@Override
	public boolean conversationReady(Conversation conversation, String adapterIdentity)
			throws InvalidConversationException, UndeliverableMessageException,
			ReadOnlyException, FeatureNotSupportedException
	{
		throw new FeatureNotSupportedException("Conversations are not yet implemented");
		
	}

	@Override
	public boolean conversationUpdate(String conversationId,
			Conversation conversation, String adapterIdentity) throws InvalidConversationException,
			FeatureNotSupportedException, ReadOnlyException,
			UnknownServiceException, UpdateException
	{
		throw new FeatureNotSupportedException("Conversations are not yet implemented");
		
	}



	@Override
	public boolean postException(ProcessingException exception, ExceptionType type,
			String messageId, DeliveryAddress receiver, String adapterIdentity) throws InvalidMessageException,
			InvalidConversationException {
		boolean out = false;
		//TODO Validate this message came from the posting adapter
		//TODO 
		log.debug("\n*********\n\t AdapterServiceImpl.postException() received a message from adapter "+adapterIdentity);
		log.debug("\t ProcessingException = "+exception);
		log.debug("\t ExceptionType = "+type);
		log.debug("\t messageId = "+messageId);
		log.debug("\t receiver = "+receiver);
		out = workflowMgr.handleAdapterException(exception, type, messageId,   receiver,  adapterIdentity);
		return out;
	}



}
