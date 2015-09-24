package org.socraticgrid.hl7.services.eps;

import javax.jws.WebParam;
import javax.jws.WebService;

import org.socraticgrid.hl7.services.eps.exceptions.AuthenicationRequiredException;
import org.socraticgrid.hl7.services.eps.exceptions.FeatureNotAvailableException;
import org.socraticgrid.hl7.services.eps.exceptions.NoSuchTopicException;
import org.socraticgrid.hl7.services.eps.exceptions.NotAuthorizedException;
import org.socraticgrid.hl7.services.eps.interfaces.BrokerMonitoringIFace;
import org.socraticgrid.hl7.services.eps.model.BrokerStatistics;
import org.socraticgrid.hl7.services.eps.model.BrokerStatus;
import org.socraticgrid.hl7.services.eps.model.TopicStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@WebService(name = "brokermonitoring", targetNamespace = "org.socraticgrid.hl7.services.eps")
public class BrokerMonitoringService implements BrokerMonitoringIFace {

	@Autowired
	@Qualifier("BrokerMonitoringtServiceImpl")
	BrokerMonitoringIFace brokerMonitoringImpl;

	public BrokerMonitoringService() {
	};

	@Override
	public BrokerStatus getBrokerStatus() throws NotAuthorizedException,
			AuthenicationRequiredException, NoSuchTopicException,
			FeatureNotAvailableException {
		return brokerMonitoringImpl.getBrokerStatus();
	}

	@Override
	public BrokerStatistics getBrokerStatistics()
			throws NotAuthorizedException, AuthenicationRequiredException,
			NoSuchTopicException, FeatureNotAvailableException {
		return brokerMonitoringImpl.getBrokerStatistics();
	}

	@Override
	public TopicStatistics getTopicStatistics(@WebParam(name = "topic") String topic)
			throws NotAuthorizedException, AuthenicationRequiredException,
			NoSuchTopicException, FeatureNotAvailableException {
		return brokerMonitoringImpl.getTopicStatistics(topic);
	}

}
