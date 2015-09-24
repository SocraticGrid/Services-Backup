package org.socraticgrid.hl7.services.eps.internal;

import org.socraticgrid.hl7.services.eps.internal.interfaces.SubscriptionIdGeneratorIFace;
import org.socraticgrid.hl7.services.eps.internal.interfaces.TopicIFace;
import org.socraticgrid.hl7.services.eps.model.Subscription;

public class SerialSubscriptionIdGenerator implements
		SubscriptionIdGeneratorIFace {

	private static int idCount=0;
	
	@Override
	synchronized public String generateSubscriptionId(TopicIFace topic,
			Subscription subscription, String topicContext) {
		return Integer.toString(idCount++);
	}

}
