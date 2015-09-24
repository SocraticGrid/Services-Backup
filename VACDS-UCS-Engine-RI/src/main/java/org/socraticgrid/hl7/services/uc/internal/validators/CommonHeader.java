package org.socraticgrid.hl7.services.uc.internal.validators;

import org.socraticgrid.hl7.services.uc.exceptions.UCSException;
import org.socraticgrid.hl7.services.uc.internal.interfaces.ValidatorStepIntf;
import org.socraticgrid.hl7.services.uc.model.Message;
import org.socraticgrid.hl7.services.uc.model.MessageHeader;
import org.socraticgrid.hl7.services.uc.model.MessageModel;

public class CommonHeader extends BaseValidator implements ValidatorStepIntf
{

	@Override
	public <T extends Message> boolean validateMessage(MessageModel<T> msg)
			throws UCSException
	{
		T typeX = msg.getMessageType();

		MessageHeader hdr = typeX.getHeader();
		if(hdr.getRecipientsList() == null)
		{
			throwException("Null recipients list",typeX);
		}
		if (hdr.getRecipientsList().isEmpty())
		{
			throwException("Recipients list is empty",typeX);
		}
		
		
		// TODO:  validate the sender is the correct user
		
		return true;
	}

}
