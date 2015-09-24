package org.socraticgrid.hl7.services.orders.internal.validators;

import org.socraticgrid.hl7.services.orders.exceptions.OrderingException;
import org.socraticgrid.hl7.services.orders.model.Order;

public class BaseValidator
{

	public void throwException(String fault, Order order) throws OrderingException
	{
		OrderingException exp = new OrderingException(fault);
		
		throw exp;
	}

}
