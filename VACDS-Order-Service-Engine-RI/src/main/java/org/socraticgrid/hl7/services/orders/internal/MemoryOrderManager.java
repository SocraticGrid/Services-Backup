/**
 * 
 */
package org.socraticgrid.hl7.services.orders.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.socraticgrid.hl7.services.orders.internal.interfaces.OrderManagerIFace;
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
public class MemoryOrderManager implements OrderManagerIFace {
	
	private static final Logger log = LoggerFactory.getLogger(MemoryOrderManager.class);
	private static final ConcurrentHashMap<Identifier,OrderModel<? extends Order>> orderMap = new ConcurrentHashMap<>();
	private static final ConcurrentHashMap<Identifier,Promise> promiseMap = new ConcurrentHashMap<>();
	/* (non-Javadoc)
	 * @see org.socraticgrid.hl7.services.orders.internal.interfaces.OrderManagerIFace#saveOrder(org.socraticgrid.hl7.services.orders.model.OrderModel)
	 */
	@Override
	public <T extends Order> void saveOrder(OrderModel<T> order) {
		Identifier identifier = order.getType().getOrderIdentity();
		orderMap.put(identifier, order);
	}

	/* (non-Javadoc)
	 * @see org.socraticgrid.hl7.services.orders.internal.interfaces.OrderManagerIFace#retrieveOrder(org.socraticgrid.hl7.services.orders.model.primatives.Identifier)
	 */
	@Override
	public OrderModel<? extends Order> retrieveOrder(Identifier identifier) {
		return orderMap.get(identifier);
	}

	@Override
	public int getOrderSize() {
		return orderMap.size();
	}

	@Override
	public List<OrderSummary> queryOrders(Subject subject) {
		List<OrderSummary> orders = new ArrayList<>();
		
		/**
		 * Check to see if Subject is a Patient and if so get an identifier
		 */
		Identifier identifier = subject.getIdentity();
		if(identifier==null) {
			return orders;
		}
		
		for(OrderModel<? extends Order> orderModel : orderMap.values()) {
			
			Identifier storedIdentifier = orderModel.getType().getSubjectdetails().getSubject().getIdentity();
			if(storedIdentifier != null
				&& identifier != null
				&& storedIdentifier.equals(identifier)){
				OrderSummary summary = new OrderSummary();
				summary.setOrderIdentity(orderModel.getType().getOrderIdentity().getValue());
				orders.add(summary);
			}
		}
		return orders;
	}

	@Override
	public <T extends Order> void updateOrder(OrderModel<T> order) {
		Identifier identifier = order.getType().getOrderIdentity();
		orderMap.put(identifier, order);
		
	}

	@Override
	public void updateOrderPromise(Identifier identifier,
			Promise promise) {
		
		promiseMap.put(identifier, promise);
		
	}

	@Override
	public void saveOrderPromise(Identifier identifier, Promise promise) {
		promiseMap.put(identifier, promise);
		
	}

	@Override
	public Promise retrievePromise(Identifier identifier) {
		
		return promiseMap.get(identifier);
	}
	
	
	

}
