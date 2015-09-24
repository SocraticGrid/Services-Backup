package org.socraticgrid.hl7.services.orders.model;

import java.io.Serializable;

import org.socraticgrid.hl7.services.orders.model.primatives.Code;

/**
 * A material sample obtained from a biological or a physical entity, or the
 * environment, for the purposes of diagnostic and environmental testing.
 * Specimens are a common source of analytes.
 * @author Jerry Goodnough
 * @version 1.0
 * @created 16-Jan-2014 9:12:42 AM
 */
public class Specimen extends AnalyzableEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Code specimenType;

	/**
	 * @return the specimenType
	 */
	public Code getSpecimenType() {
		return specimenType;
	}

	/**
	 * @param specimenType the specimenType to set
	 */
	public void setSpecimenType(Code specimenType) {
		this.specimenType = specimenType;
	}
}