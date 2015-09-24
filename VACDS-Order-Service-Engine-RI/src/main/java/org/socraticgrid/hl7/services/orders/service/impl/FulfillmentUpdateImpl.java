package org.socraticgrid.hl7.services.orders.service.impl;

import java.util.List;

import org.socraticgrid.hl7.services.orders.interfaces.FulfillmentUpdateIFace;
import org.socraticgrid.hl7.services.orders.model.Order;
import org.socraticgrid.hl7.services.orders.model.OrderModel;
import org.socraticgrid.hl7.services.orders.model.Result;
import org.socraticgrid.hl7.services.orders.model.ResultAgumentation;
import org.socraticgrid.hl7.services.orders.model.primatives.Code;
import org.socraticgrid.hl7.services.orders.model.primatives.Identifier;
import org.socraticgrid.hl7.services.orders.model.status.UpdateStatus;

public class FulfillmentUpdateImpl implements FulfillmentUpdateIFace{

	@Override
	public <T extends Order> boolean proposeOrderReplacement(Identifier orderId,
			OrderModel<T> order) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean submitResultAgmentation(Identifier resultId,
			List<ResultAgumentation> agumentation) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public UpdateStatus updateOrderStatus(Identifier orderId, Code status) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public UpdateStatus updateResult(Identifier resultId, Result result) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public UpdateStatus updateResultStatus(Identifier resultId, Code status) {
		// TODO Auto-generated method stub
		return null;
	}

}
