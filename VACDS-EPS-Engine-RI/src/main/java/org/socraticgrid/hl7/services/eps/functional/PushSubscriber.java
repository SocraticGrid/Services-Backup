package org.socraticgrid.hl7.services.eps.functional;

import org.socraticgrid.hl7.services.eps.interfaces.PSSubscriptionClientIFace;
import org.socraticgrid.hl7.services.eps.model.ManagementEvent;
import org.socraticgrid.hl7.services.eps.model.Message;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 04-Jan-2014 6:15:20 PM
 */
public class PushSubscriber extends Subscriber implements PSSubscriptionClientIFace {

	public PushSubscriber(){

	}

	@Override
	public boolean topicEvent(String topic, Message event) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean topicManagementEvent(String topic, ManagementEvent event) {
		// TODO Auto-generated method stub
		return false;
	}


}