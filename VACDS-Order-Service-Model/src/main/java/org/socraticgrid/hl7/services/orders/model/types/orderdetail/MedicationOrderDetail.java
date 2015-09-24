package org.socraticgrid.hl7.services.orders.model.types.orderdetail;

import java.io.Serializable;

import org.socraticgrid.hl7.services.orders.model.OrderDetail;

public class MedicationOrderDetail extends OrderDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String medication;

	/**
	 * @return the medication
	 */
	public String getMedication() {
		return medication;
	}

	/**
	 * @param medication the medication to set
	 */
	public void setMedication(String medication) {
		this.medication = medication;
	}
}
