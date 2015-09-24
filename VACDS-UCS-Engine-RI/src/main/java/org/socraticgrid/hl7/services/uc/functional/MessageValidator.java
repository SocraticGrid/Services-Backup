package org.socraticgrid.hl7.services.uc.functional;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.socraticgrid.hl7.services.uc.exceptions.UCSException;
import org.socraticgrid.hl7.services.uc.internal.interfaces.ValidatorStepIntf;
import org.socraticgrid.hl7.services.uc.model.Message;
import org.socraticgrid.hl7.services.uc.model.MessageModel;

/**
 * The MessageValidator  is used to validate all types of messages - First a 
 * common pass is made and then message specific checks are made - Faild validation will result
 * an exception being thrown.
 * 
 * This class is configured 
 * @author Jerry Goodnough
 * @version 1.0
 * @created 16-Dec-2013 3:54:57 PM
 */
public class MessageValidator
{

	public MessageValidator()
	{

	}


	private List<ValidatorStepIntf> commonValidationSteps;

	public List<ValidatorStepIntf> getCommonValidationSteps()
	{
		return commonValidationSteps;
	}

	public void setCommonValidationSteps(
			List<ValidatorStepIntf> commonValidationSteps)
	{
		this.commonValidationSteps = commonValidationSteps;
	}

	private Map<String, List<ValidatorStepIntf>> typeSpecificValidators;

	public Map<String, List<ValidatorStepIntf>> getTypeSpecificValidators()
	{
		return typeSpecificValidators;
	}

	public void setTypeSpecificValidators(
			Map<String, List<ValidatorStepIntf>> typeSpecificValidators)
	{
		this.typeSpecificValidators = typeSpecificValidators;
	}

	public <T extends Message> boolean validateMessage(
			MessageModel<T> msg) throws UCSException
	{
		// First check the common validation steps
		if (commonValidationSteps != null)
		{
			Iterator<ValidatorStepIntf> itr = commonValidationSteps.iterator();
			while (itr.hasNext())
			{
				itr.next().validateMessage(msg);
			}
		}

		// Now Lookup the chain for the specific type
		String typeName = msg.getMessageType().getHeader().getMessageType().name();
		List<ValidatorStepIntf> chkList = typeSpecificValidators.get(typeName);
		if (chkList != null)
		{
			Iterator<ValidatorStepIntf> itr = chkList.iterator();
			while (itr.hasNext())
			{
				itr.next().validateMessage(msg);
			}
		}

		return true;
	}
}