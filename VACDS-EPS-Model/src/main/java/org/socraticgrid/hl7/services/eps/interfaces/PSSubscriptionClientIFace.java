package org.socraticgrid.hl7.services.eps.interfaces;

import javax.jws.WebParam;
import javax.jws.WebService;

import org.socraticgrid.hl7.services.eps.model.ManagementEvent;
import org.socraticgrid.hl7.services.eps.model.Message;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 04-Jan-2014 6:15:20 PM
 */
@WebService(name = "PSSubscriptionClient", targetNamespace = "org.socraticgrid.hl7.services.eps.clients")
public interface PSSubscriptionClientIFace {

	public boolean topicEvent(@WebParam(name = "topic") String topic,
			@WebParam(name = "event") Message event);

	public boolean topicManagementEvent(@WebParam(name = "topic") String topic,
			@WebParam(name = "event") ManagementEvent event);

}