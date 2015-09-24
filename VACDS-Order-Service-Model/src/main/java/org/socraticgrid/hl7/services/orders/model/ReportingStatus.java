package org.socraticgrid.hl7.services.orders.model;

import java.io.Serializable;

import org.socraticgrid.hl7.services.orders.model.primatives.Code;
import org.socraticgrid.hl7.services.orders.model.primatives.Identifier;

/**
 * This class is used to communicate the business status of the result.  Normal
 * values include preliminary, final, corrected.
 * @author Jerry Goodnough
 * @version 1.0
 * @created 16-Jan-2014 9:12:41 AM
 */
public class ReportingStatus implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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

}