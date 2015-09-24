package org.socraticgrid.hl7.services.orders.model.types.orderdetail;

import java.io.Serializable;

import org.socraticgrid.hl7.services.orders.model.OrderDetail;

public class NutritionOrderDetail extends OrderDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String details;

	/**
	 * @return the details
	 */
	public String getDetails() {
		return details;
	}

	/**
	 * @param details the details to set
	 */
	public void setDetails(String details) {
		this.details = details;
	}
	
}
