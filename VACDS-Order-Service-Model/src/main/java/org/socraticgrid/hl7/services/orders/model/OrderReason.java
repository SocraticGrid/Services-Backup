package org.socraticgrid.hl7.services.orders.model;

import java.io.Serializable;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 16-Jan-2014 9:12:41 AM
 */
public class OrderReason extends Provenance implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String backtraceInformation;
	private Text reasoning;
	private String reasoninyEntity;
	private ReasoningEntityType reasoninyEntityType;


	public String getBacktraceInformation(){
		return backtraceInformation;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setBacktraceInformation(String newVal){
		backtraceInformation = newVal;
	}

	public Text getReasoning(){
		return reasoning;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setReasoning(Text newVal){
		reasoning = newVal;
	}

	public String getReasoninyEntity(){
		return reasoninyEntity;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setReasoninyEntity(String newVal){
		reasoninyEntity = newVal;
	}

	public ReasoningEntityType getReasoninyEntityType(){
		return reasoninyEntityType;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setReasoninyEntityType(ReasoningEntityType newVal){
		reasoninyEntityType = newVal;
	}

}