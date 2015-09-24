package org.socraticgrid.hl7.services.orders.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.socraticgrid.hl7.services.orders.model.primatives.Code;
import org.socraticgrid.hl7.services.orders.model.primatives.Identifier;
import org.socraticgrid.hl7.services.orders.model.requirements.Requirement;

/**
 * This class represents the intent of the fulfillment to perform certain
 * action(s).  This may or may not be the same as the requested acion(s).
 * @author Jerry Goodnough
 * @version 1.0
 * @created 16-Jan-2014 9:12:41 AM
 */
public class Promise implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * Supplemental requirements introduced by the Fulfillment service
	 */
	private List<Requirement> requirements = new ArrayList<Requirement>(0);
	
	/*
	 * Status of the promise
	 */
    private Code status;
	/**
	 * Unique identifier for this instantiation of a fulfillment status
	 */
	private Identifier fulfillmentIdentity;
	/**
	 * Unique identifier for this instantiation of a request for performance of
	 * order(s)
	 */
	private Identifier orderIdentity;
	
	private List<OrderItem> orderedItems = new LinkedList<OrderItem>();
	/**
	 * Unique identifier for this instantiation of a promise or intent to perform.
	 */
	private Identifier promiseIdentity;
	/**
	 * The codes for the tests which the lab has an intent to perform.  This may be
	 * different than the ordered test codes.
	 */
	private  List<OrderItem> promisedItems = new LinkedList<OrderItem>();;

	public Promise(){

	}



	/**
	 * Unique identifier for this instantiation of a fulfillment status
	 */
	public Identifier getFulfillmentIdentity(){
		return fulfillmentIdentity;
	}

	/**
	 * Unique identifier for this instantiation of a fulfillment status
	 * 
	 * @param newVal
	 */
	public void setFulfillmentIdentity(Identifier newVal){
		fulfillmentIdentity = newVal;
	}

	/**
	 * Unique identifier for this instantiation of a request for performance of
	 * order(s)
	 */
	public Identifier getOrderIdentity(){
		return orderIdentity;
	}

	/**
	 * Unique identifier for this instantiation of a request for performance of
	 * order(s)
	 * 
	 * @param newVal
	 */
	public void setOrderIdentity(Identifier newVal){
		orderIdentity = newVal;
	}

	public List<OrderItem> getOrderedItems(){
		return orderedItems;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setOrderedItems(List<OrderItem> newVal){
		orderedItems = newVal;
	}

	/**
	 * Unique identifier for this instantiation of a promise or intent to perform.
	 */
	public Identifier getPromiseIdentity(){
		return promiseIdentity;
	}

	/**
	 * Unique identifier for this instantiation of a promise or intent to perform.
	 * 
	 * @param newVal
	 */
	public void setPromiseIdentity(Identifier newVal){
		promiseIdentity = newVal;
	}

	/**
	 * The codes for the tests which the lab has an intent to perform.  This may be
	 * different than the ordered test codes.
	 */
	public  List<OrderItem> getPromisedItems(){
		return promisedItems;
	}

	/**
	 * The codes for the tests which the lab has an intent to perform.  This may be
	 * different than the ordered test codes.
	 * 
	 * @param newVal
	 */
	public void setPromisedItems( List<OrderItem> newVal){
		promisedItems = newVal;
	}



	/**
	 * @return the requirements
	 */
	public List<Requirement> getRequirements() {
		return requirements;
	}



	/**
	 * @param requirements the requirements to set
	 */
	public void setRequirements(List<Requirement> requirements) {
		this.requirements = requirements;
	}



	/**
	 * @return the status
	 */
	public Code getStatus() {
		return status;
	}



	/**
	 * @param status the status to set
	 */
	public void setStatus(Code status) {
		this.status = status;
	}
	
	

}