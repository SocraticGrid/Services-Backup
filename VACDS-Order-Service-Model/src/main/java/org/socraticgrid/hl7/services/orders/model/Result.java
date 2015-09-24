package org.socraticgrid.hl7.services.orders.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import org.socraticgrid.hl7.services.orders.model.primatives.Code;
import org.socraticgrid.hl7.services.orders.model.primatives.Identifier;

/**
 * For diagnostics like Laboratory, this class represents the 'answer' to the
 * quantative or qualitative request.
 * @author Jerry Goodnough
 * @version 1.0
 * @created 16-Jan-2014 9:12:42 AM
 */
public class Result implements Serializable {

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
	private List<OrderItem> orderedItems=new LinkedList<OrderItem>();
	private List<OrderItem> promisedItems=new LinkedList<OrderItem>();
	private List<ResultAgumentation> agumentations = new LinkedList<ResultAgumentation>();
	/**
	 * Unique identifier for this instantiation of a promise or intent to perform.
	 */
	protected Identifier promisesIdentity;
	/**
	 * The measurements and codified documentation of the lab results.
	 */
	private ResultDetail resultDetail;
	/**
	 * Unique identifier for this instantiation of the documentation of diagnostic lab
	 * results.
	 */
	private Identifier resultIdentity;
	/**
	 * Values for the status attribute are taken from the concept domain ActStatus
	 */
	private Code status;
	/**
	 * Unique identifier for the subject of the testing (the source of the specimen)
	 */
	private Subject subjectIdentity;

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
	 * Unique identifier for this instantiation of a promise or intent to perform.
	 */
	public Identifier getPromisesIdentity(){
		return promisesIdentity;
	}

	/**
	 * Unique identifier for this instantiation of a promise or intent to perform.
	 * 
	 * @param newVal
	 */
	public void setPromisesIdentity(Identifier newVal){
		promisesIdentity = newVal;
	}

	/**
	 * The measurements and codified documentation of the lab results.
	 */
	public ResultDetail getResultDetail(){
		return resultDetail;
	}

	/**
	 * The measurements and codified documentation of the lab results.
	 * 
	 * @param newVal
	 */
	public void setResultDetail(ResultDetail newVal){
		resultDetail = newVal;
	}

	/**
	 * Unique identifier for this instantiation of the documentation of diagnostic lab
	 * results.
	 */
	public Identifier getResultIdentity(){
		return resultIdentity;
	}

	/**
	 * Unique identifier for this instantiation of the documentation of diagnostic lab
	 * results.
	 * 
	 * @param newVal
	 */
	public void setResultIdentity(Identifier newVal){
		resultIdentity = newVal;
	}

	/**
	 * Values for the status attribute are taken from the concept domain ActStatus
	 */
	public Code getStatus(){
		return status;
	}

	/**
	 * Values for the status attribute are taken from the concept domain ActStatus
	 * 
	 * @param newVal
	 */
	public void setStatus(Code newVal){
		status = newVal;
	}

	/**
	 * Unique identifier for the subject of the testing (the source of the specimen)
	 */
	public Subject getSubjectIdentity(){
		return subjectIdentity;
	}

	/**
	 * Unique identifier for the subject of the testing (the source of the specimen)
	 * 
	 * @param newVal
	 */
	public void setSubjectIdentity(Subject newVal){
		subjectIdentity = newVal;
	}

	/**
	 * @return the agumentations
	 */
	public List<ResultAgumentation> getAgumentations() {
		return agumentations;
	}

	/**
	 * @param agumentations the agumentations to set
	 */
	public void setAgumentations(List<ResultAgumentation> agumentations) {
		this.agumentations = agumentations;
	}

}

