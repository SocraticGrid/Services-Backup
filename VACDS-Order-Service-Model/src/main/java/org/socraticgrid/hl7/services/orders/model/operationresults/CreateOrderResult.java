package org.socraticgrid.hl7.services.orders.model.operationresults;

import java.io.Serializable;

import org.socraticgrid.hl7.services.orders.model.primatives.Identifier;
import org.socraticgrid.hl7.services.orders.model.status.CreateStatus;

public class CreateOrderResult implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private CreateStatus status;
	private Identifier orderIdentity;

	/**
	 * @return the status
	 */
	public CreateStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(CreateStatus status) {
		this.status = status;
	}

	/**
	 * @return the orderIdentity
	 */
	public Identifier getOrderIdentity() {
		return orderIdentity;
	}

	/**
	 * @param orderIdentity the orderIdentity to set
	 */
	public void setOrderIdentity(Identifier orderIdentity) {
		this.orderIdentity = orderIdentity;
	}
	
}
