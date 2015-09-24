package org.socraticgrid.hl7.services.orders.model.types.orderdetail;

import java.io.Serializable;

import org.socraticgrid.hl7.services.orders.model.OrderDetail;
import org.socraticgrid.hl7.services.orders.model.primatives.Code;

public class LabOrderDetail extends OrderDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Code lab;

	/**
	 * @return the lab
	 */
	public Code getLab() {
		return lab;
	}

	/**
	 * @param lab the lab to set
	 */
	public void setLab(Code lab) {
		this.lab = lab;
	}
	
}
