package org.socraticgrid.hl7.services.eps.internal.validatationstep.subscription;

import org.socraticgrid.hl7.services.eps.exceptions.AuthenicationRequiredException;
import org.socraticgrid.hl7.services.eps.exceptions.ExpiredException;
import org.socraticgrid.hl7.services.eps.exceptions.FeatureNotAvailableException;
import org.socraticgrid.hl7.services.eps.exceptions.IncompleteDataException;
import org.socraticgrid.hl7.services.eps.exceptions.InvalidDataException;
import org.socraticgrid.hl7.services.eps.exceptions.MediaFormatNotExceptedException;
import org.socraticgrid.hl7.services.eps.exceptions.NoSuchTopicException;
import org.socraticgrid.hl7.services.eps.exceptions.NotAuthorizedException;
import org.socraticgrid.hl7.services.eps.internal.interfaces.SubscriptionValidationStepIFace;
import org.socraticgrid.hl7.services.eps.internal.interfaces.TopicIFace;
import org.socraticgrid.hl7.services.eps.model.Durability;
import org.socraticgrid.hl7.services.eps.model.Subscription;

public class DurabilityMatch implements SubscriptionValidationStepIFace {

	Durability requiredDurability;
	
	public DurabilityMatch()
	{
		
	}
	
	public DurabilityMatch(Durability requiredDurability)
	{
		this.requiredDurability = requiredDurability;
	}
	

	public boolean valdiateSubscription(Subscription subscription,
			TopicIFace topic) throws NotAuthorizedException,
			AuthenicationRequiredException, ExpiredException,
			FeatureNotAvailableException, InvalidDataException,
			MediaFormatNotExceptedException, NoSuchTopicException,
			IncompleteDataException {
		
		if (subscription.getDurability()!= requiredDurability)
		{
			throw new InvalidDataException("Topic is configured for "+requiredDurability.toString()+" Durability only");
		}
		return true;
	}


	/**
	 * @return the requiredDurability
	 */
	public Durability getRequiredDurability() {
		return requiredDurability;
	}


	/**
	 * @param requiredDurability the requiredDurability to set
	 */
	public void setRequiredDurability(Durability requiredDurability) {
		this.requiredDurability = requiredDurability;
	}

}
