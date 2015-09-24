/**
 * 
 */
package org.socraticgrid.hl7.services.orders.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.socraticgrid.hl7.services.orders.exceptions.OrderingException;
import org.socraticgrid.hl7.services.orders.functional.EventLogger;
import org.socraticgrid.hl7.services.orders.functional.MessageIdGenerator;
import org.socraticgrid.hl7.services.orders.functional.OrderDispatcher;
import org.socraticgrid.hl7.services.orders.functional.OrderValidator;
import org.socraticgrid.hl7.services.orders.interfaces.OrderManagementIFace;
import org.socraticgrid.hl7.services.orders.internal.interfaces.OrderManagerIFace;
import org.socraticgrid.hl7.services.orders.logging.EventLevel;
import org.socraticgrid.hl7.services.orders.logging.LogEntryType;
import org.socraticgrid.hl7.services.orders.model.Order;
import org.socraticgrid.hl7.services.orders.model.OrderModel;
import org.socraticgrid.hl7.services.orders.model.OrderSummary;
import org.socraticgrid.hl7.services.orders.model.Result;
import org.socraticgrid.hl7.services.orders.model.ResultAgumentation;
import org.socraticgrid.hl7.services.orders.model.Subject;
import org.socraticgrid.hl7.services.orders.model.operationresults.CancelOrderResult;
import org.socraticgrid.hl7.services.orders.model.operationresults.ChangeOrderResult;
import org.socraticgrid.hl7.services.orders.model.operationresults.CreateOrderResult;
import org.socraticgrid.hl7.services.orders.model.primatives.Identifier;
import org.socraticgrid.hl7.services.orders.model.status.CancellationStatus;
import org.socraticgrid.hl7.services.orders.model.status.ChangeStatus;
import org.socraticgrid.hl7.services.orders.model.status.CreateStatus;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Jerry Goodnough
 * 
 */


public class OrderManagementImpl implements OrderManagementIFace {
	private final Logger logger = LoggerFactory.getLogger(OrderManagementImpl.class);

	@Autowired
	MessageIdGenerator idGen;
	@Autowired

	OrderValidator orderValidator;
	
	@Autowired
	OrderManagerIFace orderManager;

	@Autowired
	EventLogger evtLogger;
	
	@Autowired
	OrderDispatcher dispatcher;

	@Override
	public CancelOrderResult cancelOrderById(Identifier orderId)
			throws OrderingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends Order> CancelOrderResult cancelOrder(OrderModel<T> order)
			throws OrderingException {
		CancelOrderResult result = new CancelOrderResult();
		result.setStatus(CancellationStatus.NotAllowed);
		return result;
	}

	@Override
	public <T extends Order> ChangeOrderResult changeOrder(Identifier orderId,
			OrderModel<T> order) throws OrderingException {
		ChangeOrderResult result = new ChangeOrderResult();
		result.setStatus(ChangeStatus.NotAllowed);
		return result;
	}

	@Override
	public <T extends Order> CreateOrderResult createOrder(OrderModel<T> order)
			throws OrderingException {
		logger.debug("In Create Order");
		CreateOrderResult createResult = new CreateOrderResult();
		
		evtLogger.logSummaryEvent(LogEntryType.User_CreateOrder, EventLevel.info,
				"", "CreateOrder", "Validating Message", order);
		try {
			orderValidator.validateMessage(order);

		} catch (OrderingException exp) {
			evtLogger.logUserExceptionEvent("sendMessage", exp);
			throw exp;

		} 
		// Assign the Order an Id
		// TODO  - Deal with assigned Order ids
		Identifier orderId = new Identifier();
		orderId.setValue(idGen.getNewMessageId());
		createResult.setOrderIdentity(orderId);
		evtLogger.logSummaryEvent(LogEntryType.User_CreateOrder, EventLevel.info,
				"", "Create Order", "Order Id Generated = " + orderId.getValue());

		order.getType().setOrderIdentity(orderId);
		

		// Create tracking structures

		
		evtLogger.logSummaryEvent(LogEntryType.User_CreateOrder,
				EventLevel.info, "", "Accepted",
				"Saving Order prior to workflow handoff");

		//Save the Order
		orderManager.saveOrder(order);
		
		//TODO Hand off to Order workflow
		
		CreateStatus ok = dispatcher.dispatchOrder(order);
		
		createResult.setStatus(ok);
		
		return createResult;
	}

	@Override
	public List<OrderSummary> queryOrders(Subject subject) {
		return orderManager.queryOrders(subject);
	}

	@Override
	public OrderModel<? extends Order> retrieveOrder(Identifier orderId) {
		
		return orderManager.retrieveOrder(orderId);
	}

	@Override
	public List<Result> retrieveResults(Identifier resultId) {
		List<Result> result = new LinkedList<Result>();
		
		
		return result;
	}
	@Override
	public List<ResultAgumentation> getResultAgumentatons(Identifier resultId)
	{
		List<ResultAgumentation> out = new LinkedList<ResultAgumentation>();
		return out;
	}
	
	

}
