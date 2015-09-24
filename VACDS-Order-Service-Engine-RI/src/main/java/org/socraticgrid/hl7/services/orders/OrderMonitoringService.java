package org.socraticgrid.hl7.services.orders;


import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.socraticgrid.hl7.services.orders.interfaces.OrderMonitoringIFace;
import org.socraticgrid.hl7.services.orders.model.Order;
import org.socraticgrid.hl7.services.orders.model.OrderModel;
import org.socraticgrid.hl7.services.orders.model.OrderSummary;
import org.socraticgrid.hl7.services.orders.model.Workflow;
import org.socraticgrid.hl7.services.orders.model.WorkflowModel;
import org.socraticgrid.hl7.services.orders.model.primatives.Code;
import org.socraticgrid.hl7.services.orders.model.primatives.Identifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


@WebService(name = "ordermonitoring", targetNamespace = "org.socraticgrid.hl7.services.orders")
public class OrderMonitoringService implements OrderMonitoringIFace{
	private final Logger logger = LoggerFactory
			.getLogger(OrderMonitoringService.class);

	@Autowired
	@Qualifier("OrderMonitoringServiceImp")
	OrderMonitoringIFace service;

	@Override
	public List<OrderSummary> queryPendingOrders(@WebParam(name = "context")String context) {
		return service.queryPendingOrders(context);
	}

	@Override
	public <T extends Order> OrderModel<T> retrieveOrder(@WebParam(name = "orderId")Identifier orderId) {
		return service.retrieveOrder(orderId);
	}

	@Override
	public Code retrieveStatus(@WebParam(name = "orderId")Identifier orderId) {
		return service.retrieveStatus(orderId);
	}

	@Override
	public <T extends Workflow> WorkflowModel<T> retriveOrderWorkflow(
			@WebParam(name = "orderId")Identifier orderId) {
		return service.retriveOrderWorkflow(orderId);
	}


}
