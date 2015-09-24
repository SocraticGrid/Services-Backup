package org.socraticgrid.hl7.services.orders.internal.validators;

import org.socraticgrid.hl7.services.orders.exceptions.MalformedOrderException;
import org.socraticgrid.hl7.services.orders.exceptions.OrderingException;
import org.socraticgrid.hl7.services.orders.internal.interfaces.ValidatorStepIFace;
import org.socraticgrid.hl7.services.orders.model.Order;
import org.socraticgrid.hl7.services.orders.model.OrderModel;

public class CommonStructure extends BaseValidator implements ValidatorStepIFace
{

	@Override
	public <T extends Order> boolean validateOrder(OrderModel<T> order)
			throws OrderingException {
		
		boolean out = true;

		
		//Check Subject
		if (order.getType().getSubjectdetails()==null)
		{
			throw new MalformedOrderException("Missing Subject Details");
		}
		else
		{
			if (order.getType().getSubjectdetails().getSubject()==null)
			{
				throw new MalformedOrderException("Missing Subject");
			}
			else
			{
				if (order.getType().getSubjectdetails().getSubject().getIdentity()==null)
				{
					throw new MalformedOrderException("Missing Subject Identity");
				}
				else
				{
					if(order.getType().getSubjectdetails().getSubject().getIdentity().getValue()==null)
					{
						throw new MalformedOrderException("Missing Subject Indentity Missing Value");
					}
				}
			}
		}
		
		
		return out;
	}

	
}
