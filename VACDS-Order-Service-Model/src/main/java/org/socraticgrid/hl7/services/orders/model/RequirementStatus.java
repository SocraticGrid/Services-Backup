package org.socraticgrid.hl7.services.orders.model;

import java.io.Serializable;

import org.socraticgrid.hl7.services.orders.model.requirements.RequirementStatusCode;

/**
 * This class is used to capture the state/state of a requirement, e.g.
 * �<i>initial�, �in process�, �met�</i>.
 * @author Jerry Goodnough
 * @version 1.0
 * @created 16-Jan-2014 9:12:41 AM
 */
public class RequirementStatus implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private RequirementStatusCode status;



	public RequirementStatusCode getStatus(){
		return status;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setStatus(RequirementStatusCode newVal){
		status = newVal;
	}

}