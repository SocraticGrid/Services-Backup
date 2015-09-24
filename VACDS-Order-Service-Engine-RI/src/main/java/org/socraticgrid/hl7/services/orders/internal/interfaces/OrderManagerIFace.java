/**
 * 
 */
package org.socraticgrid.hl7.services.orders.internal.interfaces;

import java.util.List;

import org.socraticgrid.hl7.services.orders.model.Order;
import org.socraticgrid.hl7.services.orders.model.OrderModel;
import org.socraticgrid.hl7.services.orders.model.OrderSummary;
import org.socraticgrid.hl7.services.orders.model.Promise;
import org.socraticgrid.hl7.services.orders.model.Subject;
import org.socraticgrid.hl7.services.orders.model.primatives.Identifier;

/**
 * @author steven
 *
 */
public interface OrderManagerIFace {

	public abstract <T extends Order> void saveOrder(OrderModel<T> order);
	
	public abstract OrderModel<? extends Order> retrieveOrder(Identifier identifier);
	
	public abstract int getOrderSize();
	
	public abstract List<OrderSummary> queryOrders(Subject subject);
	
	public abstract <T extends Order> void updateOrder(OrderModel<T> order);
	
	public abstract void updateOrderPromise(Identifier identifier,Promise promise);
	
	public abstract void saveOrderPromise(Identifier identifier,Promise promise);
	
	public abstract Promise retrievePromise(Identifier identifier);
}
