package org.socraticgrid.hl7.services.orders.model;

import java.io.Serializable;

public class OrderSummary implements Serializable {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	String orderIdentity;

	/**
	 * @return the orderIdentity
	 */
	public String getOrderIdentity() {
		return orderIdentity;
	}

	/**
	 * @param orderIdentity the orderIdentity to set
	 */
	public void setOrderIdentity(String orderIdentity) {
		this.orderIdentity = orderIdentity;
	}

}
