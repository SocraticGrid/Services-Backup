package org.socraticgrid.hl7.services.orders.internal.validators;


import org.socraticgrid.hl7.services.orders.exceptions.MalformedOrderException;
import org.socraticgrid.hl7.services.orders.exceptions.OrderingException;
import org.socraticgrid.hl7.services.orders.internal.interfaces.ValidatorStepIFace;
import org.socraticgrid.hl7.services.orders.model.Order;
import org.socraticgrid.hl7.services.orders.model.OrderModel;



public class OrderCompleteness extends BaseValidator implements ValidatorStepIFace
{

	
		@Override
		public <T extends Order> boolean validateOrder(OrderModel<T> order)
				throws OrderingException {
		
			if (order.getType().getOrderDetails()==null)
			{
				throw new MalformedOrderException("Missing Order Details");
			}
			if (order.getType().getOrderTime()==null)
			{
				throw new MalformedOrderException("Missing Order Date");
			}
			if (order.getType().getOrderedBy()==null)
			{
				throw new MalformedOrderException("Missing OrderedBy");
			}
			
			return true;
		}

	
}
