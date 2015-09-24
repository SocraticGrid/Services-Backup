package org.socraticgrid.hl7.services.orders.model.types.order;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import org.socraticgrid.hl7.services.orders.model.Order;
import org.socraticgrid.hl7.services.orders.model.OrderItem;
import org.socraticgrid.hl7.services.orders.model.types.orderdetail.NutritionOrderDetail;
import org.socraticgrid.hl7.services.orders.model.types.orderitems.NutritionOrderItem;

public class NutritionOrder extends Order implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private NutritionOrderDetail orderDetail= new NutritionOrderDetail();
	
	private List<NutritionOrderItem>  items = new LinkedList<NutritionOrderItem>();
	/**
	 * Information relevant for the performance of an order.
	 */

	public NutritionOrderDetail getOrderDetails()
	{
		return orderDetail;
	}

	public void setOrderdetails(NutritionOrderDetail newVal) {

		orderDetail =  newVal;
	}


	public List<NutritionOrderItem> getOrdereditems() {
		return items;
	}


	public void setOrdereditems(List<NutritionOrderItem> newVal) {
		items = newVal;
	}
}
