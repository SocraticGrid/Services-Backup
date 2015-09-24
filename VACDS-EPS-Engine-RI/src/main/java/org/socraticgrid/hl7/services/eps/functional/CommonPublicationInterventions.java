package org.socraticgrid.hl7.services.eps.functional;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.socraticgrid.hl7.services.eps.interfaces.PSPublicationInterventionIFace;

public class CommonPublicationInterventions {

	private List<PSPublicationInterventionIFace> interventions;

	/**
	 * @return the interventions
	 */
	public List<PSPublicationInterventionIFace> getInterventions() {
		return interventions;
	}

	/**
	 * @param interventions the interventions to set
	 */
	public void setInterventions(List<PSPublicationInterventionIFace> interventions) {
		this.interventions = interventions;
	}
	
	@PostConstruct
	
	protected void initialize()
	{
		if (interventions == null)
		{
			interventions = new LinkedList<PSPublicationInterventionIFace>();
		}
	}
	
}
