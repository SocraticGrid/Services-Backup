package org.socraticgrid.hl7.services.uc.internal.validators;


import org.socraticgrid.hl7.services.uc.exceptions.UCSException;
import org.socraticgrid.hl7.services.uc.internal.interfaces.ValidatorStepIntf;
import org.socraticgrid.hl7.services.uc.model.Message;
import org.socraticgrid.hl7.services.uc.model.MessageModel;
import org.socraticgrid.hl7.services.uc.model.SimpleMessage;
import org.socraticgrid.hl7.services.uc.model.SimpleMessageHeader;



public class SimpleMessageCompleteness extends BaseValidator implements ValidatorStepIntf
{

	@Override
	public <T extends Message> boolean validateMessage(MessageModel<T> msg)
			throws UCSException
	{
			SimpleMessage typeX = (SimpleMessage) msg.getMessageType();

			SimpleMessageHeader hdr = typeX.getHeader();
			if (hdr.getReplyTo() == null)
			{
				throwException("Null ReplyTo address",typeX);
			}
			//TODO: Validate the replyTo address is valid
			return false;
	}

}
