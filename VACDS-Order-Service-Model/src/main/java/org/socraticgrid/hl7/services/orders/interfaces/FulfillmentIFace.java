package org.socraticgrid.hl7.services.orders.interfaces;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.socraticgrid.hl7.services.orders.exceptions.OrderingException;
import org.socraticgrid.hl7.services.orders.model.Order;
import org.socraticgrid.hl7.services.orders.model.OrderModel;
import org.socraticgrid.hl7.services.orders.model.Promise;
import org.socraticgrid.hl7.services.orders.model.Result;
import org.socraticgrid.hl7.services.orders.model.ResultAgumentation;
import org.socraticgrid.hl7.services.orders.model.primatives.Identifier;
import org.socraticgrid.hl7.services.orders.model.requirements.CollectionRequirement;
import org.socraticgrid.hl7.services.orders.model.requirements.CounsellingRequirement;
import org.socraticgrid.hl7.services.orders.model.requirements.EndorsementRequirement;
import org.socraticgrid.hl7.services.orders.model.requirements.PresenceRequirement;
import org.socraticgrid.hl7.services.orders.model.requirements.ProceduralRequirement;
import org.socraticgrid.hl7.services.orders.model.requirements.Requirement;
import org.socraticgrid.hl7.services.orders.model.status.CancellationStatus;
import org.socraticgrid.hl7.services.orders.model.status.VerifyStatus;

/**
 * Presented by a fulfillment system to allow the ordering system to request
 * order promises.
 * 
 * @author Jerry Goodnough
 * @version 1.0
 * @created 16-Jan-2014 9:12:40 AM
 */
@WebService(name = "Fulfillment", targetNamespace = "org.socraticgrid.hl7.services.orders.clients")
@XmlSeeAlso({CollectionRequirement.class,CounsellingRequirement.class,EndorsementRequirement.class,ProceduralRequirement.class,PresenceRequirement.class})
public interface FulfillmentIFace {

	public CancellationStatus cancelOrder(
			@WebParam(name = "orderId") Identifier orderId);

	public <T extends Order> OrderModel<T> getProposedOrderReplacement(
			@WebParam(name = "orderId") Identifier orderId);

	public List<ResultAgumentation> getResultAgumentatons(
			@WebParam(name = "resultId") Identifier resultId);

	public <T extends Order> Promise requestFulfillment(
			@WebParam(name = "order") OrderModel<T> order);

	public Result retrieveResultByResultId(
			@WebParam(name = "resultId") Identifier resultId);

	public List<Result> retrieveResultByOrderId(
			@WebParam(name = "orderId") Identifier orderId);

	public Promise updateOrderRequirements(
			@WebParam(name = "orderId") Identifier orderId,
			@WebParam(name = "requirements") List<Requirement> requirements);

	public VerifyStatus verifyOrderRequirement(
			@WebParam(name = "orderId") Identifier orderId,
			@WebParam(name = "requirement") Requirement requirement)
			throws OrderingException;
}