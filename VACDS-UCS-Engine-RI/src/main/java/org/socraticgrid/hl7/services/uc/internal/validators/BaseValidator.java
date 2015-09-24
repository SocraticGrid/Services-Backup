package org.socraticgrid.hl7.services.uc.internal.validators;

import java.util.List;

import org.socraticgrid.hl7.services.uc.exceptions.InvalidMessageException;
import org.socraticgrid.hl7.services.uc.exceptions.ProcessingException;
import org.socraticgrid.hl7.services.uc.exceptions.UCSException;
import org.socraticgrid.hl7.services.uc.model.Message;

public class BaseValidator
{

	public void throwException(String fault, Message msg) throws UCSException
	{
		InvalidMessageException throwExp = new InvalidMessageException(fault,msg);
		ProcessingException exp = new ProcessingException();
		exp.setGeneratingMessage(msg.getHeader().getMessageId());
		exp.setIssuingService("Core");
		exp.setFault(fault);
		
		List<ProcessingException> expList = msg.getExceptions();
		expList.add(exp);
		throw throwExp;
	}

}
