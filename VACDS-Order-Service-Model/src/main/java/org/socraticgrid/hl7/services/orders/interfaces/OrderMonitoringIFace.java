package org.socraticgrid.hl7.services.orders.interfaces;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import org.socraticgrid.hl7.services.orders.model.Order;
import org.socraticgrid.hl7.services.orders.model.OrderModel;
import org.socraticgrid.hl7.services.orders.model.OrderSummary;
import org.socraticgrid.hl7.services.orders.model.Workflow;
import org.socraticgrid.hl7.services.orders.model.WorkflowModel;
import org.socraticgrid.hl7.services.orders.model.primatives.Code;
import org.socraticgrid.hl7.services.orders.model.primatives.Identifier;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 16-Jan-2014 9:12:41 AM
 */
@WebService(name = "ordermonitoring", targetNamespace = "org.socraticgrid.hl7.services.orders")
public interface OrderMonitoringIFace {

	public List<OrderSummary> queryPendingOrders(
			@WebParam(name = "context") String context);

	public <T extends Order> OrderModel<T> retrieveOrder(
			@WebParam(name = "orderId") Identifier orderId);

	public Code retrieveStatus(@WebParam(name = "orderId") Identifier orderId);

	public <T extends Workflow> WorkflowModel<T> retriveOrderWorkflow(
			@WebParam(name = "orderId") Identifier orderId);

}