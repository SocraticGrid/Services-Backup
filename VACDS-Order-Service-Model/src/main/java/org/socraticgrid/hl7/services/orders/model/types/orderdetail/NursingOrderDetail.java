package org.socraticgrid.hl7.services.orders.model.types.orderdetail;

import java.io.Serializable;

import org.socraticgrid.hl7.services.orders.model.OrderDetail;

public class NursingOrderDetail extends OrderDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String action;

	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @param action the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}
}
