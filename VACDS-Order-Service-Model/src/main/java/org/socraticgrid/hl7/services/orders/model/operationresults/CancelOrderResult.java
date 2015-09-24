package org.socraticgrid.hl7.services.orders.model.operationresults;

import java.io.Serializable;

import org.socraticgrid.hl7.services.orders.model.primatives.Identifier;
import org.socraticgrid.hl7.services.orders.model.status.CancellationStatus;

public class CancelOrderResult implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private CancellationStatus status;
	private Identifier orderIdentity;

	/**
	 * @return the status
	 */
	public CancellationStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(CancellationStatus status) {
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
