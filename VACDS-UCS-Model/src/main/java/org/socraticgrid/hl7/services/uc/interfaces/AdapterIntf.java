package org.socraticgrid.hl7.services.uc.interfaces;

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

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 16-Dec-2013 3:54:55 PM
 */
public interface AdapterIntf
{

	public boolean conversationReady(Conversation conversation, String adapterIdentity)
			throws InvalidConversationException, UndeliverableMessageException,
			ReadOnlyException, FeatureNotSupportedException;

	public boolean conversationUpdate(String conversationId,
			Conversation conversation, String adapterIdentity) throws InvalidConversationException,
			FeatureNotSupportedException, ReadOnlyException,
			UnknownServiceException, UpdateException;

	public boolean postException(
			ProcessingException exception,
			ExceptionType type, String messageId, DeliveryAddress receiver, String adapterIdentity) throws InvalidMessageException,
			InvalidConversationException;

}