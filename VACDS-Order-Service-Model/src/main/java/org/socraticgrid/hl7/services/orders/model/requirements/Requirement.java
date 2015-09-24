package org.socraticgrid.hl7.services.orders.model.requirements;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Constraints or preconditions to the acceptance or fulfillment of an order.
 * Requirements can be about the collection of a specimen, counseling, patient
 * presence or procedure / sequence.
 * @author Jerry Goodnough
 * @version 1.0
 * @created 16-Jan-2014 9:12:41 AM
 */
@XmlTransient
@XmlSeeAlso({CollectionRequirement.class,CounsellingRequirement.class,EndorsementRequirement.class,ProceduralRequirement.class,PresenceRequirement.class})
public abstract class Requirement implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String orignator;
	private RequirementStatusCode status;
	
	/**
	 * Hard Prerequisite - Reject if not present on create.
	 * Additional Workflow (e.g Analyte collection)
	 */
	protected RequirementType type;


	public String getId(){
		return id;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setId(String newVal){
		id = newVal;
	}

	/**
	 * Hard Prerequisite - Reject if not present on create.
	 * Additional Workflow (e.g Analyte collection)
	 */
	public RequirementType getType(){
		return type;
	}

	/**
	 * Hard Prerequisite - Reject if not present on create.
	 * Additional Workflow (e.g Analyte collection)
	 * 
	 * @param newVal
	 */
	public void setType(RequirementType newVal){
		type = newVal;
	}

	/**
	 * @return the orignator
	 */
	public String getOrignator() {
		return orignator;
	}

	/**
	 * @param orignator the orignator to set
	 */
	public void setOrignator(String orignator) {
		this.orignator = orignator;
	}

	/**
	 * @return the status
	 */
	public RequirementStatusCode getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(RequirementStatusCode status) {
		this.status = status;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((orignator == null) ? 0 : orignator.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}

		//We require and exact class match
		if (obj.getClass() != getClass())
		{
			return false;
		}
	
		Requirement other = (Requirement) obj;
		
		//Id is used for comparison if and only if both are non null
		if (id != null) 
		{
			if (other.id != null) 
			{
				if (!id.equals(other.id))
				{
					return false;
				}
			}
		} 

		if (orignator == null) {
			if (other.orignator != null) {
				return false;
			}
		} else if (!orignator.equals(other.orignator)) {
			return false;
		}
		if (type != other.type) {
			return false;
		}
		return true;
	}
	
	
	

}