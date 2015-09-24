package org.socraticgrid.hl7.services.orders.interfaces;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import org.socraticgrid.hl7.services.orders.exceptions.OrderingException;
import org.socraticgrid.hl7.services.orders.model.Order;
import org.socraticgrid.hl7.services.orders.model.OrderModel;
import org.socraticgrid.hl7.services.orders.model.OrderSummary;
import org.socraticgrid.hl7.services.orders.model.Subject;
import org.socraticgrid.hl7.services.orders.model.Workflow;
import org.socraticgrid.hl7.services.orders.model.WorkflowModel;
import org.socraticgrid.hl7.services.orders.model.primatives.Code;
import org.socraticgrid.hl7.services.orders.model.primatives.Identifier;
import org.socraticgrid.hl7.services.orders.model.requirements.Requirement;
import org.socraticgrid.hl7.services.orders.model.status.ChangeStatus;
import org.socraticgrid.hl7.services.orders.model.status.UpdateStatus;
import org.socraticgrid.hl7.services.orders.model.status.VerifyStatus;

/**
 * Used to update order work status - This includes routing and analtye
 * collection.
 * 
 * @author Jerry Goodnough
 * @version 1.0
 * @created 16-Jan-2014 9:12:41 AM
 */
@WebService(name = "orderworkflow", targetNamespace = "org.socraticgrid.hl7.services.orders")
public interface OrderWorkflowIFace {

	public List<OrderSummary> queryOrders(
			@WebParam(name = "subject") Subject subject);

	public <T extends Order> OrderModel<T> retrieveOrder(
			@WebParam(name = "orderId") Identifier orderId);

	public <T extends Order> ChangeStatus changeOrder(
			@WebParam(name = "orderId") Identifier orderId,
			@WebParam(name = "updatedOrder") OrderModel<T> updatedOrder)
			throws OrderingException;

	public <T extends Workflow> WorkflowModel<T> getOrderWorkflow(
			@WebParam(name = "orderId") Identifier orderId);

	public List<Requirement> queryOrderRequirements(
			@WebParam(name = "orderId") Identifier orderId);

	public UpdateStatus updateOrderRequirements(
			@WebParam(name = "orderId") Identifier orderId,
			@WebParam(name = "updatedRequirements") List<Requirement> updatedRequirements)
			throws OrderingException;

	public UpdateStatus updateOrderStatus(
			@WebParam(name = "orderId") Identifier orderIdentifier,
			@WebParam(name = "status") Code status) throws OrderingException;

	public <T extends Workflow> UpdateStatus updateOrderWorkflow(
			@WebParam(name = "orderId") Identifier orderId,
			@WebParam(name = "workflowModel") WorkflowModel<T> workflowModel);

	public VerifyStatus verifyOrderRequirement(
			@WebParam(name = "orderId") Identifier orderId,
			@WebParam(name = "requirement") Requirement requirement)
			throws OrderingException;
}