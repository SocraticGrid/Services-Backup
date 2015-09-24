package org.socraticgrid.hl7.services.orders.logging;
import java.io.Serializable;

import org.socraticgrid.hl7.services.orders.model.Order;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 16-Dec-2013 3:54:56 PM
 */
public class DetailedLogEntry extends LogEntry implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Order order;

	public DetailedLogEntry(){

	}

	public Order getOrder()
	{
		return order;
	}

	public void setOrder(Order order)
	{
		this.order = order;
	}


}