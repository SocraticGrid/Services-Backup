package org.socraticgrid.hl7.services.eps.internal.model;

import java.io.Serializable;
import java.util.Date;

public class PushStatus implements Serializable {

	private Date deliveryTime;
	private boolean delivered;
	private String messageId;
	/**
	 * @return the deliveryTime
	 */
	public Date getDeliveryTime() {
		return deliveryTime;
	}
	/**
	 * @param deliveryTime the deliveryTime to set
	 */
	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
	/**
	 * @return the delivered
	 */
	public boolean isDelivered() {
		return delivered;
	}
	/**
	 * @param delivered the delivered to set
	 */
	public void setDelivered(boolean delivered) {
		this.delivered = delivered;
	}
	/**
	 * @return the messageId
	 */
	public String getMessageId() {
		return messageId;
	}
	/**
	 * @param messageId the messageId to set
	 */
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	
}
