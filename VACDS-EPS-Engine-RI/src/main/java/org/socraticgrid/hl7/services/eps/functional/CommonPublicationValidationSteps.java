package org.socraticgrid.hl7.services.eps.functional;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.socraticgrid.hl7.services.eps.internal.interfaces.PublicationValidationStepIFace;

public class CommonPublicationValidationSteps {
	private List<PublicationValidationStepIFace> validationSteps;

	/**
	 * @return the validationSteps
	 */
	public List<PublicationValidationStepIFace> getValidationSteps() {
		return validationSteps;
	}

	/**
	 * @param validationSteps the validationSteps to set
	 */
	public void setValidationSteps(List<PublicationValidationStepIFace> validationSteps) {
		this.validationSteps = validationSteps;
	}
	@PostConstruct
	
	protected void initialize()
	{
		if (validationSteps == null)
		{
			validationSteps = new LinkedList<PublicationValidationStepIFace>();
		}
	}
	
}
