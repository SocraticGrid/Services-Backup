package org.socraticgrid.hl7.services.orders.internal.interfaces;

import org.socraticgrid.hl7.services.orders.exceptions.OrderingException;
import org.socraticgrid.hl7.services.orders.model.Order;
import org.socraticgrid.hl7.services.orders.model.OrderModel;



/**
 * Interface for 
 * @author Jerry Goodnough
 * @version 1.0
 * @created 16-Dec-2013 3:54:58 PM
 */
public interface ValidatorStepIFace {
	public  <T extends Order>  boolean validateOrder(OrderModel<T> order) throws OrderingException;
}