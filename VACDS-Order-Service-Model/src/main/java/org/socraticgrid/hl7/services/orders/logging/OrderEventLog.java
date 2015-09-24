package org.socraticgrid.hl7.services.orders.logging;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 16-Dec-2013 3:54:57 PM
 */
public class OrderEventLog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public List<LogEntry> getLogEnties()
	{
		return logEnties;
	}

	public void setLogEnties(List<LogEntry> logEnties)
	{
		this.logEnties = logEnties;
	}

	public List<LogEntry> logEnties;

	public OrderEventLog(){

	}

}