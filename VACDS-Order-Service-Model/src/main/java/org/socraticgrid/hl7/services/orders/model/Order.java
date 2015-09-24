package org.socraticgrid.hl7.services.orders.model;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.jws.WebMethod;

import org.socraticgrid.hl7.services.orders.model.primatives.Code;
import org.socraticgrid.hl7.services.orders.model.primatives.Identifier;
import org.socraticgrid.hl7.services.orders.model.requirements.Requirement;

/**
 * This class represents the request for a service.  In this diagram, the lab
 * order.
 * @author Jerry Goodnough
 * @version 1.0
 * @created 16-Jan-2014 9:12:41 AM
 */
abstract public class Order implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Requirement> requirements = new  LinkedList<Requirement>();
	/**
	 * Unique identifier for this instantiation of a request for performance of
	 * order(s)
	 */
	private Identifier orderIdentity;
	/**
	 * The codes for the testing being requested
	 */
	private List<OrderItem> ordereditems;
	private ClinicalPractitioner orderedBy;
	private Identifier orderEnteredBy;
	private Date orderTime;
	private Set<Provenance> provenance;
	/**
	 * Values for the status attribute are taken from the concept domain ActStatus
	 */
	private Code status;
	/**
	 * The subject who is the source of the specimen.
	 */
	private SubjectModel subjectdetails;
	

	public Order(){

	}

	public void finalize() throws Throwable {

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
	 * Information relevant for the performance of an order.
	 */
	@WebMethod(exclude=true) 
	abstract public  OrderDetail getOrderDetails();


	/**
	 * The codes for the testing being requested
	 */
	//abstract public  List<? extends OrderItem> getOrdereditems();

	/**
	 * The codes for the testing being requested
	 * 
	 * @param newVal
	 */
	//abstract public void setOrdereditems(List<? extends OrderItem> newVal);
	

	public Date getOrderTime(){
		return orderTime;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setOrderTime(Date newVal){
		orderTime = newVal;
	}

	public Set<Provenance> getProvenance(){
		return provenance;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setProvenance(Set<Provenance> newVal){
		provenance = newVal;
	}

	public ClinicalPractitioner getOrderedBy(){
		return orderedBy;
	}

	/**
	 * Values for the status attribute are taken from the concept domain ActStatus
	 */
	public Code getStatus(){
		return status;
	}

	/**
	 * The subject who is the source of the specimen.
	 */
	public SubjectModel getSubjectdetails(){
		return subjectdetails;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setOrderedBy(ClinicalPractitioner newVal){
		orderedBy = newVal;
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
	 * The subject who is the source of the specimen.
	 * 
	 * @param newVal
	 */
	public void setSubjectdetails(SubjectModel newVal){
		subjectdetails = newVal;
	}

	/**
	 * @return the orderEnteredBy
	 */
	public Identifier getOrderEnteredBy() {
		return orderEnteredBy;
	}

	/**
	 * @param orderEnteredBy the orderEnteredBy to set
	 */
	public void setOrderEnteredBy(Identifier orderEnteredBy) {
		this.orderEnteredBy = orderEnteredBy;
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
	
	

}