package org.socraticgrid.hl7.services.orders.model.types.order;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import org.socraticgrid.hl7.services.orders.model.Order;
import org.socraticgrid.hl7.services.orders.model.types.orderdetail.LabOrderDetail;
import org.socraticgrid.hl7.services.orders.model.types.orderitems.LabOrderItem;

public class LabOrder extends Order implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private LabOrderDetail orderDetail= new LabOrderDetail();
	/**
	 * Information relevant for the performance of an order.
	 */

	private List<LabOrderItem> items = new LinkedList<LabOrderItem> ();
	
	public LabOrderDetail getOrderDetails()
	{
		return orderDetail;
	}

	public void setOrderdetails(LabOrderDetail newVal) {

		orderDetail =  newVal;
	}


	public List<LabOrderItem> getOrdereditems() {
		return items;
	}

	public void setOrdereditems(List<LabOrderItem> newVal) {
		items=newVal;
		
	}

	

}
