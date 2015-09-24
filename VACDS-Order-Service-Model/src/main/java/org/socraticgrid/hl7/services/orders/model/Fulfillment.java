package org.socraticgrid.hl7.services.orders.model;

import java.io.Serializable;
import java.util.List;

import org.socraticgrid.hl7.services.orders.model.primatives.Code;
import org.socraticgrid.hl7.services.orders.model.primatives.Identifier;

/**
 * This class is used to communicate that status of the performance of a request.
 * @author Jerry Goodnough
 * @version 1.0
 * @created 16-Jan-2014 9:12:40 AM
 */
public class Fulfillment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Unique identifier for this instantiation of a fulfillment status
	 */
	private Identifier fulfillmentIdentity;
	/**
	 * Unique identifier for this instantiation of a request for performance of
	 * order(s)
	 */
	private Identifier orderIdentity;
	/**
	 * The codes for the testing being requested
	 */
	private List<OrderItem> orderedItems;
	private List<OrderItem> promisedItems;
	/**
	 * Values for the status attribute are taken from the concept domain
	 * LabBusinessProcessStatus
	 */
	private Code status;
	/**
	 * Unique identifier for the subject of the testing (the source of the specimen)
	 */
	private Identifier subjectIdentity;


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

	/**
	 * The codes for the testing being requested
	 */
	public List<OrderItem> getOrderedItems(){
		return orderedItems;
	}

	/**
	 * The codes for the testing being requested
	 * 
	 * @param newVal
	 */
	public void setOrderedItems(List<OrderItem> newVal){
		orderedItems = newVal;
	}

	public List<OrderItem> getPromisedItems(){
		return promisedItems;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setPromisedItems(List<OrderItem> newVal){
		promisedItems = newVal;
	}

	/**
	 * Values for the status attribute are taken from the concept domain
	 * LabBusinessProcessStatus
	 */
	public Code getStatus(){
		return status;
	}

	/**
	 * Values for the status attribute are taken from the concept domain
	 * LabBusinessProcessStatus
	 * 
	 * @param newVal
	 */
	public void setStatus(Code newVal){
		status = newVal;
	}

	/**
	 * Unique identifier for the subject of the testing (the source of the specimen)
	 */
	public Identifier getSubjectIdentity(){
		return subjectIdentity;
	}

	/**
	 * Unique identifier for the subject of the testing (the source of the specimen)
	 * 
	 * @param newVal
	 */
	public void setSubjectIdentity(Identifier newVal){
		subjectIdentity = newVal;
	}

}