package org.socraticgrid.hl7.services.orders.model;

import java.io.Serializable;

import org.socraticgrid.hl7.services.orders.model.primatives.Code;
import org.socraticgrid.hl7.services.orders.model.primatives.Identifier;

/**
 * This class is used to communicate the actual service either being requested or
 * that has been performed.
 * @author Jerry Goodnough
 * @version 1.0
 * @created 16-Jan-2014 9:12:42 AM
 */
public class Test extends OrderItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Unique identifier for a test.  This may be the same as the test code.
	 */
	private Identifier identity;
	/**
	 * Values for the status attribute are taken from the concept domain
	 * LabOrderableObservationCode
	 */
	private Code testCode;

	/**
	 * Values for the status attribute are taken from the concept domain
	 * LabOrderableObservationCode
	 */
	public Code getTestCode(){
		return testCode;
	}

	/**
	 * Values for the status attribute are taken from the concept domain
	 * LabOrderableObservationCode
	 * 
	 * @param newVal
	 */
	public void setTestCode(Code newVal){
		testCode = newVal;
	}

	/**
	 * Unique identifier for a test.  This may be the same as the test code.
	 */
	public Identifier getIdentity(){
		return identity;
	}

	/**
	 * Unique identifier for a test.  This may be the same as the test code.
	 * 
	 * @param newVal
	 */
	public void setIdentity(Identifier newVal){
		identity = newVal;
	}

}