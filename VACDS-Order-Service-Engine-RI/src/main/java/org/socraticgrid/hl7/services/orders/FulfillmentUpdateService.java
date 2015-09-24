package org.socraticgrid.hl7.services.orders;


import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.socraticgrid.hl7.services.orders.interfaces.FulfillmentUpdateIFace;
import org.socraticgrid.hl7.services.orders.model.Order;
import org.socraticgrid.hl7.services.orders.model.OrderModel;
import org.socraticgrid.hl7.services.orders.model.Result;
import org.socraticgrid.hl7.services.orders.model.ResultAgumentation;
import org.socraticgrid.hl7.services.orders.model.primatives.Code;
import org.socraticgrid.hl7.services.orders.model.primatives.Identifier;
import org.socraticgrid.hl7.services.orders.model.status.UpdateStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@WebService(name = "fulfillmentupdate", targetNamespace = "org.socraticgrid.hl7.services.orders")
public class FulfillmentUpdateService  implements FulfillmentUpdateIFace {

	private final Logger logger = LoggerFactory.getLogger(FulfillmentUpdateService.class);
	
	@Autowired
	@Qualifier("FulfillmentUpdateServiceImpl")
	FulfillmentUpdateIFace fulfillmentUpdateService;
	
	public <T extends Order> boolean proposeOrderReplacement(@WebParam(name = "orderId")Identifier orderId,
			@WebParam(name = "order")OrderModel<T> order) {
	
		return fulfillmentUpdateService.proposeOrderReplacement(orderId, order);
	}


	public boolean submitResultAgmentation(@WebParam(name = "resultIdentifer")Identifier resultId,
			@WebParam(name = "agumentations")List<ResultAgumentation> agumentations) {
		
		return fulfillmentUpdateService.submitResultAgmentation(resultId, agumentations);
	}


	public UpdateStatus updateOrderStatus(@WebParam(name = "orderId")Identifier orderId,@WebParam(name = "status") Code status) {
		
		return fulfillmentUpdateService.updateOrderStatus(orderId, status);
	}


	public UpdateStatus updateResult(@WebParam(name = "orderId")Identifier orderId, @WebParam(name = "result")Result result) {
	
		return fulfillmentUpdateService.updateResult(orderId, result);
	}


	public UpdateStatus updateResultStatus(@WebParam(name = "resultIdentifier")Identifier resultIdentifier, @WebParam(name = "status")Code status) {
	
		return fulfillmentUpdateService.updateResultStatus(resultIdentifier, status);
	}

}
