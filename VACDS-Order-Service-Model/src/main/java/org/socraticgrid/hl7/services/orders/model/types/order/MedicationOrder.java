package org.socraticgrid.hl7.services.orders.model.types.order;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import org.socraticgrid.hl7.services.orders.model.Order;
import org.socraticgrid.hl7.services.orders.model.types.orderdetail.MedicationOrderDetail;
import org.socraticgrid.hl7.services.orders.model.types.orderitems.MedicationOrderItem;

public class MedicationOrder extends Order implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private MedicationOrderDetail orderDetail= new MedicationOrderDetail();
	
	private List<MedicationOrderItem>  items = new LinkedList<MedicationOrderItem>();
	/**
	 * Information relevant for the performance of an order.
	 */

	public MedicationOrderDetail getOrderDetails()
	{
		return orderDetail;
	}

	public void setOrderdetails(MedicationOrderDetail newVal) {

		orderDetail =  newVal;
	}

	
	public List<MedicationOrderItem> getOrdereditems() {
		return items;
	}

	
	public void setOrdereditems(List<MedicationOrderItem> newVal) {
		items=newVal;
	}

	
}
