/**
 * 
 */
package org.socraticgrid.hl7.services.uc.model;


/**
 * @author steven
 * Feb 7, 2014
 *
 */
public class StatusByReceiverDelegate {
	
	private String key;
	private AlertStatus alertStatus;
	private DeliveryAddress deliveryAddress;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public AlertStatus getAlertStatus() {
		return alertStatus;
	}
	public void setAlertStatus(AlertStatus alertStatus) {
		this.alertStatus = alertStatus;
	}
	public DeliveryAddress getDeliveryAddress() {
		return deliveryAddress;
	}
	public void setDeliveryAddress(DeliveryAddress deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	
	@Override
	public boolean equals(Object o){
		if(o==null){
			return false;
		}
		if(o instanceof StatusByReceiverDelegate){
			return getKey().equals(((StatusByReceiverDelegate)o).getKey());
		}
		return false;
	}
	@Override
	public int hashCode() {
		return getKey().hashCode();
	}

	
}
