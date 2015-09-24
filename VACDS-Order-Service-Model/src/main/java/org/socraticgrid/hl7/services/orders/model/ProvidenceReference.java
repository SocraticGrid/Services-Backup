package org.socraticgrid.hl7.services.orders.model;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 16-Jan-2014 9:12:41 AM
 */
public class ProvidenceReference extends Provenance {

	private String reference;
	private int orderCatelogId;

	public ProvidenceReference(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	public String getReference(){
		return reference;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setReference(String newVal){
		reference = newVal;
	}

	public int getOrderCatelogId(){
		return orderCatelogId;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setOrderCatelogId(int newVal){
		orderCatelogId = newVal;
	}

}