package org.socraticgrid.hl7.services.orders.model;

import java.io.Serializable;

import org.socraticgrid.hl7.services.orders.model.primatives.Code;
import org.socraticgrid.hl7.services.orders.model.primatives.Quantity;

/**
 * The result details, qualitative and quantative measurements.
 * @author Jerry Goodnough
 * @version 1.0
 * @created 16-Jan-2014 9:12:42 AM
 */
public class ResultDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * Measurements made and documented using codes and interpretion.
	 */
	private Code qualitativeMeasurement;
	/**
	 * Measurements which are numeric/units based.
	 */
	private Quantity quantitativeMeasurement;
	/**
	 * Descriptive text about the result.
	 */
	private String descriptive;



	/**
	 * Measurements which are numeric/units based.
	 */
	public Quantity getQuantitativeMeasurement(){
		return quantitativeMeasurement;
	}

	/**
	 * Measurements which are numeric/units based.
	 * 
	 * @param newVal
	 */
	public void setQuantitativeMeasurement(Quantity newVal){
		quantitativeMeasurement = newVal;
	}

	/**
	 * Descriptive text about the result.
	 */
	public String getDescriptive(){
		return descriptive;
	}

	/**
	 * Measurements made and documented using codes and interpretion.
	 */
	public Code getQualitativeMeasurement(){
		return qualitativeMeasurement;
	}

	/**
	 * Descriptive text about the result.
	 * 
	 * @param newVal
	 */
	public void setDescriptive(String newVal){
		descriptive = newVal;
	}

	/**
	 * Measurements made and documented using codes and interpretion.
	 * 
	 * @param newVal
	 */
	public void setQualitativeMeasurement(Code newVal){
		qualitativeMeasurement = newVal;
	}

}

