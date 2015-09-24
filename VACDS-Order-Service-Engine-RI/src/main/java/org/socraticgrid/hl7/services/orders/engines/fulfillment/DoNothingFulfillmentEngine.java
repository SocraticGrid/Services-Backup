package org.socraticgrid.hl7.services.orders.engines.fulfillment;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.socraticgrid.hl7.services.orders.exceptions.OrderingException;
import org.socraticgrid.hl7.services.orders.interfaces.FulfillmentIFace;
import org.socraticgrid.hl7.services.orders.model.Order;
import org.socraticgrid.hl7.services.orders.model.OrderModel;
import org.socraticgrid.hl7.services.orders.model.Promise;
import org.socraticgrid.hl7.services.orders.model.Result;
import org.socraticgrid.hl7.services.orders.model.ResultAgumentation;
import org.socraticgrid.hl7.services.orders.model.primatives.Code;
import org.socraticgrid.hl7.services.orders.model.primatives.Identifier;
import org.socraticgrid.hl7.services.orders.model.requirements.Requirement;
import org.socraticgrid.hl7.services.orders.model.status.CancellationStatus;
import org.socraticgrid.hl7.services.orders.model.status.VerifyStatus;

public class DoNothingFulfillmentEngine implements FulfillmentIFace {
	private final Logger logger = LoggerFactory.getLogger(DoNothingFulfillmentEngine.class);
	
	@Override
	public CancellationStatus cancelOrder(Identifier arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends Order> OrderModel<T> getProposedOrderReplacement(
			Identifier arg0) {
	
		return null;
	}

	@Override
	public List<ResultAgumentation> getResultAgumentatons(Identifier arg0) {
		// TODO Auto-generated method stub
		List<ResultAgumentation> out =  new LinkedList<ResultAgumentation>();
		return out;
	}

	@Override
	public <T extends Order> Promise requestFulfillment(OrderModel<T> arg0) {
		Promise out = new Promise();
		Code status = new Code();
		status.setCode("Complete");
		status.setCodeSystem("TestFulfillement");
		out.setStatus(status);
		
		logger.debug("In Do Nothing Engine - requestFulfillment ");
		return out;
	}

	@Override
	public List<Result> retrieveResultByOrderId(Identifier arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result retrieveResultByResultId(Identifier arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Promise updateOrderRequirements(Identifier orderId,
			List<Requirement> requirements) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VerifyStatus verifyOrderRequirement(Identifier orderId,
			Requirement requirement) throws OrderingException {
		// TODO Auto-generated method stub
		return null;
	}

}
