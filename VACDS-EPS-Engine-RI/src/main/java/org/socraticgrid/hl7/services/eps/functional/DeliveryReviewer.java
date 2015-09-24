package org.socraticgrid.hl7.services.eps.functional;

import org.socraticgrid.hl7.services.eps.interfaces.PSDeliveryInterventionIFace;
import org.socraticgrid.hl7.services.eps.model.DeliveryReviewResult;
import org.socraticgrid.hl7.services.eps.model.Message;
import org.socraticgrid.hl7.services.eps.model.Subscription;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 04-Jan-2014 6:15:19 PM
 */
public class DeliveryReviewer implements PSDeliveryInterventionIFace {

	@Override
	public DeliveryReviewResult reviewContent(String userid, Message event) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DeliveryReviewResult reviewMessage(String userid, Message event) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean controlTopicAccess(String userId, Subscription subscription,
			String topicId) {
		// TODO Auto-generated method stub
		return false;
	}


}