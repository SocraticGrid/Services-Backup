package org.socraticgrid.hl7.services.eps.clients.pssubscriptionclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.socraticgrid.hl7.services.eps.model.ManagementEvent;
import org.socraticgrid.hl7.services.eps.interfaces.PSSubscriptionClientIFace;
import org.socraticgrid.hl7.services.eps.model.Message;

public class PSSubscriptionClientImpl implements PSSubscriptionClientIFace {

	public PSSubscriptionClientImpl() {
		// TODO Auto-generated constructor stub
	}
	private final Logger logger = LoggerFactory.getLogger(PSSubscriptionClientImpl.class);
	@Override
	public boolean topicEvent(String topic, Message event) {
		logger.info("Event for topic "+topic+", "+event.getHeader().getSubject());
		if (logger.isDebugEnabled())
		{
			logger.debug(event.toString());
		}
		return true;
	}

	@Override
	public boolean topicManagementEvent(String topic, ManagementEvent event) {
		logger.info("Management for topic "+topic+", "+event.getHeader().getSubject());
		
		if (logger.isDebugEnabled())
		{
			logger.debug(event.toString());
		}

		return true;
	}

}
