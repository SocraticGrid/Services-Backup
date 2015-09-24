package org.socraticgrid.hl7.services.orders.logging;

import java.io.Serializable;

import org.socraticgrid.hl7.services.orders.model.primatives.Identifier;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 16-Dec-2013 3:54:57 PM
 */
public class SummaryLogEntry extends LogEntry implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @return the orderId
	 */
	public Identifier getOrderId() {
		return orderId;
	}
	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(Identifier orderId) {
		this.orderId = orderId;
	}
	private Identifier orderId;
	public SummaryLogEntry(){

	}




}