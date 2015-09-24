package org.socraticgrid.hl7.services.orders.model.requirements;

import java.io.Serializable;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 16-Jan-2014 9:12:41 AM
 */
public class ProceduralRequirement extends Requirement implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ProceduralRequirement()
	{
		super();
		type=RequirementType.Procedural;
	}
	
	@Override 
	public void setType(RequirementType newVal){
		//
	}
	
}