package org.socraticgrid.hl7.services.eps.engines.deliveryintervention;

import org.socraticgrid.hl7.services.eps.interfaces.PSDeliveryInterventionIFace;
import org.socraticgrid.hl7.services.eps.model.DeliveryAction;
import org.socraticgrid.hl7.services.eps.model.DeliveryReviewResult;
import org.socraticgrid.hl7.services.eps.model.Message;
import org.socraticgrid.hl7.services.eps.model.Subscription;


public class RejectAllEngine implements PSDeliveryInterventionIFace {

	public RejectAllEngine() {

	}

	@Override
	public DeliveryReviewResult reviewContent(String userid, Message event) {
		DeliveryReviewResult out = new DeliveryReviewResult();
		out.setAction(DeliveryAction.Filter);
		return out;
	}

	@Override
	public DeliveryReviewResult reviewMessage(String userid, Message event) {
		DeliveryReviewResult out = new DeliveryReviewResult();
		out.setAction(DeliveryAction.Filter);
		return out;
	}

	@Override
	public boolean controlTopicAccess(String userId, Subscription subscription,
			String topicId) {
		return false;
	}

}