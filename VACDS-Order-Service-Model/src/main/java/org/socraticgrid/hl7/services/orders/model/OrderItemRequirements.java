package org.socraticgrid.hl7.services.orders.model;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import org.socraticgrid.hl7.services.orders.model.requirements.Requirement;

/**
 * This class is used to hold metadata regarding orderable items in a catalog, for
 * example, requirements for submitting an order of that type.
 * @author Jerry Goodnough
 * @version 1.0
 * @created 16-Jan-2014 9:12:41 AM
 */
public class OrderItemRequirements implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<Requirement> requirements = new LinkedList<Requirement>();

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