package org.socraticgrid.hl7.services.orders.model.types.order;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import org.socraticgrid.hl7.services.orders.model.Order;
import org.socraticgrid.hl7.services.orders.model.types.orderdetail.NursingOrderDetail;
import org.socraticgrid.hl7.services.orders.model.types.orderitems.NursingOrderItem;

public class NursingOrder extends Order implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private NursingOrderDetail orderDetail= new NursingOrderDetail();
	
	private List<NursingOrderItem>  items = new LinkedList<NursingOrderItem>();
	
	/**
	 * Information relevant for the performance of an order.
	 */

	public NursingOrderDetail getOrderDetails()
	{
		return orderDetail;
	}

	public void setOrderdetails(NursingOrderDetail newVal) {

		orderDetail =  newVal;
	}
	public List<NursingOrderItem> getOrdereditems() {
		return items;
	}


	public void setOrdereditems(List<NursingOrderItem> newVal) {
		items = newVal;
	}
}

