package org.socraticgrid.hl7.services.eps.clients.psdeliveryintervention;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.socraticgrid.hl7.services.eps.interfaces.PSDeliveryInterventionIFace;
import org.socraticgrid.hl7.services.eps.model.DeliveryReviewResult;
import org.socraticgrid.hl7.services.eps.model.Message;
import org.socraticgrid.hl7.services.eps.model.Subscription;

@WebService(name = "PSDeliveryIntervention", targetNamespace = "org.socraticgrid.hl7.services.eps.clients")
public class PSDeliveryInterventionService implements
		PSDeliveryInterventionIFace {

	private PSDeliveryInterventionIFace deliveryInterventionImpl;

	@WebMethod(exclude = true)
	public PSDeliveryInterventionIFace getDeliveryInterventionImpl() {
		return deliveryInterventionImpl;
	}

	@WebMethod(exclude = true)
	public void setDeliveryInterventionImpl(
			PSDeliveryInterventionIFace deliveryInterventionImpl) {
		this.deliveryInterventionImpl = deliveryInterventionImpl;
	}

	public PSDeliveryInterventionService() {
	}

	@Override
	public DeliveryReviewResult reviewContent(
			@WebParam(name = "userId") String userid,
			@WebParam(name = "event") Message event) {
		return deliveryInterventionImpl.reviewContent(userid, event);
	}

	@Override
	public DeliveryReviewResult reviewMessage(
			@WebParam(name = "userId") String userid,
			@WebParam(name = "event") Message event) {
		return deliveryInterventionImpl.reviewMessage(userid, event);
	}

	/**
	 * 
	 * @param userId
	 * @param subscription
	 * @param topicId
	 * @return True iif access is allowed
	 */
	@Override
	public boolean controlTopicAccess(@WebParam(name = "userId") String userId,
			@WebParam(name = "subscription") Subscription subscription,
			@WebParam(name = "topic") String topic) {
		return deliveryInterventionImpl.controlTopicAccess(userId,
				subscription, topic);
	}

}
