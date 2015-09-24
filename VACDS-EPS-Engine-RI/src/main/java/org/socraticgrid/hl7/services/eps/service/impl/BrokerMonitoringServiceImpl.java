/**
 * 
 */
package org.socraticgrid.hl7.services.eps.service.impl;

import org.socraticgrid.hl7.services.eps.exceptions.AuthenicationRequiredException;
import org.socraticgrid.hl7.services.eps.exceptions.FeatureNotAvailableException;
import org.socraticgrid.hl7.services.eps.exceptions.NoSuchTopicException;
import org.socraticgrid.hl7.services.eps.exceptions.NotAuthorizedException;
import org.socraticgrid.hl7.services.eps.interfaces.BrokerMonitoringIFace;
import org.socraticgrid.hl7.services.eps.model.BrokerStatistics;
import org.socraticgrid.hl7.services.eps.model.BrokerStatus;
import org.socraticgrid.hl7.services.eps.model.TopicStatistics;

/**
 * @author Jerry Goodnough
 *
 */
public class BrokerMonitoringServiceImpl implements BrokerMonitoringIFace {

	/**
	 * 
	 */
	public BrokerMonitoringServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.socraticgrid.hl7.services.eps.interfaces.BrokerMonitoringIFace#getBrokerStatus()
	 */
	@Override
	public BrokerStatus getBrokerStatus() throws NotAuthorizedException,
			AuthenicationRequiredException, NoSuchTopicException,
			FeatureNotAvailableException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.socraticgrid.hl7.services.eps.interfaces.BrokerMonitoringIFace#getBrokerStatistics()
	 */
	@Override
	public BrokerStatistics getBrokerStatistics()
			throws NotAuthorizedException, AuthenicationRequiredException,
			NoSuchTopicException, FeatureNotAvailableException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.socraticgrid.hl7.services.eps.interfaces.BrokerMonitoringIFace#getTopicStatistics(java.lang.String)
	 */
	@Override
	public TopicStatistics getTopicStatistics(String topic)
			throws NotAuthorizedException, AuthenicationRequiredException,
			NoSuchTopicException, FeatureNotAvailableException {
		// TODO Auto-generated method stub
		return null;
	}

}
