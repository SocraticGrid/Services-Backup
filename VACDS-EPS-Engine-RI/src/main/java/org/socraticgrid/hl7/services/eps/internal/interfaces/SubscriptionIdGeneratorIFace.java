package org.socraticgrid.hl7.services.eps.internal.interfaces;

import org.socraticgrid.hl7.services.eps.model.Subscription;

public interface SubscriptionIdGeneratorIFace {
	String generateSubscriptionId(TopicIFace topic, Subscription subscription, String topicContext);
}
