package org.socraticgrid.hl7.services.eps.model;

import java.io.Serializable;

public class TopicStatistics  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long activeMessages;
	private long activeSubscriptions;
	private long totalMessages;
	private long totalSubscriptions;
	private long deliveryFaults;
	private long pendingDelivery;
	
	public TopicStatistics() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the activeMessages
	 */
	public long getActiveMessages() {
		return activeMessages;
	}
	/**
	 * @param activeMessages the activeMessages to set
	 */
	public void setActiveMessages(long activeMessages) {
		this.activeMessages = activeMessages;
	}
	/**
	 * @return the activeSubscriptions
	 */
	public long getActiveSubscriptions() {
		return activeSubscriptions;
	}
	/**
	 * @param activeSubscriptions the activeSubscriptions to set
	 */
	public void setActiveSubscriptions(long activeSubscriptions) {
		this.activeSubscriptions = activeSubscriptions;
	}
	/**
	 * @return the totalMessages
	 */
	public long getTotalMessages() {
		return totalMessages;
	}
	/**
	 * @param totalMessages the totalMessages to set
	 */
	public void setTotalMessages(long totalMessages) {
		this.totalMessages = totalMessages;
	}
	/**
	 * @return the totalSubscriptions
	 */
	public long getTotalSubscriptions() {
		return totalSubscriptions;
	}
	/**
	 * @param totalSubscriptions the totalSubscriptions to set
	 */
	public void setTotalSubscriptions(long totalSubscriptions) {
		this.totalSubscriptions = totalSubscriptions;
	}
	/**
	 * @return the deliveryFaults
	 */
	public long getDeliveryFaults() {
		return deliveryFaults;
	}
	/**
	 * @param deliveryFaults the deliveryFaults to set
	 */
	public void setDeliveryFaults(long deliveryFaults) {
		this.deliveryFaults = deliveryFaults;
	}
	/**
	 * @return the pendingDelivery
	 */
	public long getPendingDelivery() {
		return pendingDelivery;
	}
	/**
	 * @param pendingDelivery the pendingDelivery to set
	 */
	public void setPendingDelivery(long pendingDelivery) {
		this.pendingDelivery = pendingDelivery;
	}
	
}
