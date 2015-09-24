package org.socraticgrid.hl7.services.orders.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.socraticgrid.hl7.services.orders.interfaces.OrderMonitoringIFace;
import org.socraticgrid.hl7.services.orders.model.Order;
import org.socraticgrid.hl7.services.orders.model.OrderModel;
import org.socraticgrid.hl7.services.orders.model.OrderSummary;
import org.socraticgrid.hl7.services.orders.model.Workflow;
import org.socraticgrid.hl7.services.orders.model.WorkflowModel;
import org.socraticgrid.hl7.services.orders.model.primatives.Code;
import org.socraticgrid.hl7.services.orders.model.primatives.Identifier;

public class OrderMonitoringImpl implements OrderMonitoringIFace{

	@Override
	public List<OrderSummary> queryPendingOrders(String context) {
		List<OrderSummary> result = new LinkedList<OrderSummary>();
		return result;
	}

	@Override
	public <T extends Order> OrderModel<T> retrieveOrder(Identifier orderId) {
	
		return null;
	}

	@Override
	public Code retrieveStatus(Identifier orderId) {

		return null;
	}

	@Override
	public <T extends Workflow> WorkflowModel<T> retriveOrderWorkflow(
			Identifier orderId) {

		return null;
	}

	
}
