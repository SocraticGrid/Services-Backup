package org.socraticgrid.hl7.services.eps.interfaces;

import javax.jws.WebParam;
import javax.jws.WebService;

import org.socraticgrid.hl7.services.eps.model.DeliveryReviewResult;
import org.socraticgrid.hl7.services.eps.model.Message;
import org.socraticgrid.hl7.services.eps.model.Subscription;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 04-Jan-2014 6:15:20 PM
 */
@WebService(name = "PSDeliveryIntervention", targetNamespace = "org.socraticgrid.hl7.services.eps.clients")
public interface PSDeliveryInterventionIFace {

	public DeliveryReviewResult reviewContent(
			@WebParam(name = "userId") String userid,
			@WebParam(name = "event") Message event);

	public DeliveryReviewResult reviewMessage(
			@WebParam(name = "userId") String userid,
			@WebParam(name = "event") Message event);

	/**
	 * 
	 * @param userId
	 * @param subscription
	 * @param topic
	 * @return True if access is allowed
	 */
	public boolean controlTopicAccess(@WebParam(name = "userId") String userId,
			@WebParam(name = "subscription") Subscription subscription,
			String topic);

}