package org.socraticgrid.hl7.services.orders.model.requirements;

import java.io.Serializable;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 16-Jan-2014 9:12:40 AM
 */
public class CounsellingRequirement extends Requirement implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CounsellingRequirement()
	{
		super();
		type = RequirementType.Counselling;
	}
	
	@Override 
	public void setType(RequirementType newVal){
		//
	}

}