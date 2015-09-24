package org.socraticgrid.hl7.services.uc.internal.validators;

import org.socraticgrid.hl7.services.uc.exceptions.UCSException;
import org.socraticgrid.hl7.services.uc.functional.ConversationManager;
import org.socraticgrid.hl7.services.uc.internal.interfaces.ValidatorStepIntf;
import org.socraticgrid.hl7.services.uc.model.Conversation;
import org.socraticgrid.hl7.services.uc.model.ConversationRequestMessage;
import org.socraticgrid.hl7.services.uc.model.Message;
import org.socraticgrid.hl7.services.uc.model.MessageHeader;
import org.socraticgrid.hl7.services.uc.model.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;

public class ConversationValidator extends BaseValidator implements	ValidatorStepIntf
{
	
	@Autowired
	private ConversationManager convMgr;
	@Override
	public <T extends Message> boolean validateMessage(MessageModel<T> msg) throws UCSException
	{
		ConversationRequestMessage typeX = (ConversationRequestMessage) msg.getMessageType();

		MessageHeader hdr = typeX.getHeader();
		String convId = hdr.getRelatedConversationId();
		
		if (convId == null)
		{
			return true;
		}
		//OK We have a conversation - Let's make sure it is real
		Conversation con = convMgr.getConversation(convId);
		if (con == null)
		{
			// TODO   - Error not such conversation
		}
		
		// TODO - Make sure the sender is a part of the conversation
		
		// TODO - Lets make sure it is not closed or read only
		
		
		return true;
	}
}
