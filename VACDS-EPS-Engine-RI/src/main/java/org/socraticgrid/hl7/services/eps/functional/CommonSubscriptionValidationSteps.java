package org.socraticgrid.hl7.services.eps.functional;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.socraticgrid.hl7.services.eps.internal.interfaces.SubscriptionValidationStepIFace;

public class CommonSubscriptionValidationSteps {
	private List<SubscriptionValidationStepIFace> validationSteps;

	/**
	 * @return the validationSteps
	 */
	public List<SubscriptionValidationStepIFace> getValidationSteps() {
		return validationSteps;
	}

	/**
	 * @param validationSteps the validationSteps to set
	 */
	public void setValidationSteps(List<SubscriptionValidationStepIFace> validationSteps) {
		this.validationSteps = validationSteps;
	}
	@PostConstruct
	
	protected void initialize()
	{
		if (validationSteps == null)
		{
			validationSteps = new LinkedList<SubscriptionValidationStepIFace>();
		}
	}
	
}
