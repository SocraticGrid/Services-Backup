package org.socraticgrid.hl7.services.orders.functional;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.socraticgrid.hl7.services.orders.exceptions.OrderingException;
import org.socraticgrid.hl7.services.orders.internal.interfaces.ValidatorStepIFace;
import org.socraticgrid.hl7.services.orders.model.Order;
import org.socraticgrid.hl7.services.orders.model.OrderModel;

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
public class OrderValidator
{

	public OrderValidator()
	{

	}


	private List<ValidatorStepIFace> commonValidationSteps;

	public List<ValidatorStepIFace> getCommonValidationSteps()
	{
		return commonValidationSteps;
	}

	public void setCommonValidationSteps(
			List<ValidatorStepIFace> commonValidationSteps)
	{
		this.commonValidationSteps = commonValidationSteps;
	}

	private Map<String, List<ValidatorStepIFace>> typeSpecificValidators;

	public Map<String, List<ValidatorStepIFace>> getTypeSpecificValidators()
	{
		return typeSpecificValidators;
	}

	public void setTypeSpecificValidators(
			Map<String, List<ValidatorStepIFace>> typeSpecificValidators)
	{
		this.typeSpecificValidators = typeSpecificValidators;
	}

	public <T extends Order> boolean validateMessage(
			OrderModel<T> order) throws OrderingException
	{
		// First check the common validation steps
		if (commonValidationSteps != null)
		{
			Iterator<ValidatorStepIFace> itr = commonValidationSteps.iterator();
			while (itr.hasNext())
			{
				itr.next().validateOrder(order);
			}
		}

		// Now Lookup the chain for the specific type
		String typeName = order.getType().getClass().getSimpleName();
		List<ValidatorStepIFace> chkList = typeSpecificValidators.get(typeName);
		if (chkList != null)
		{
			Iterator<ValidatorStepIFace> itr = chkList.iterator();
			while (itr.hasNext())
			{
				itr.next().validateOrder(order);
			}
		}

		return true;
	}
}