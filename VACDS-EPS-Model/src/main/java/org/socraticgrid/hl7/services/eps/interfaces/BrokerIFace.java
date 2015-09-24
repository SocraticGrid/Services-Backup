package org.socraticgrid.hl7.services.eps.interfaces;

import java.util.Date;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import org.socraticgrid.hl7.services.eps.exceptions.AuthenicationRequiredException;
import org.socraticgrid.hl7.services.eps.exceptions.ConflictException;
import org.socraticgrid.hl7.services.eps.exceptions.ExpiredException;
import org.socraticgrid.hl7.services.eps.exceptions.FeatureNotAvailableException;
import org.socraticgrid.hl7.services.eps.exceptions.IncompleteDataException;
import org.socraticgrid.hl7.services.eps.exceptions.InvalidDataException;
import org.socraticgrid.hl7.services.eps.exceptions.NoSuchTopicException;
import org.socraticgrid.hl7.services.eps.exceptions.NotAuthorizedException;
import org.socraticgrid.hl7.services.eps.model.Capabilities;
import org.socraticgrid.hl7.services.eps.model.MessageSummary;
import org.socraticgrid.hl7.services.eps.model.Options;
import org.socraticgrid.hl7.services.eps.model.RequestStatus;
import org.socraticgrid.hl7.services.eps.model.TopicSummary;
import org.socraticgrid.hl7.services.eps.model.User;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 04-Jan-2014 6:15:19 PM
 */
@WebService(name = "broker", targetNamespace = "org.socraticgrid.hl7.services.eps")
public interface BrokerIFace {

	public List<TopicSummary> discoverTopics(@WebParam(name = "query")String query)
			throws NotAuthorizedException, AuthenicationRequiredException,
			ConflictException, ExpiredException, NoSuchTopicException;

	/**
	 * 
	 * @param Id
	 */
	public List<TopicSummary> getSubTopics(@WebParam(name = "query")String topic)
			throws NotAuthorizedException, AuthenicationRequiredException,
			ExpiredException, NoSuchTopicException;

	public Options getTopicOptions(@WebParam(name = "topic")String topic) throws NotAuthorizedException,
			AuthenicationRequiredException, FeatureNotAvailableException,
			NoSuchTopicException;

	public List<MessageSummary> discoverEventsForTopic(@WebParam(name = "topic")String topic,
			@WebParam(name = "start")Date start, @WebParam(name = "end")Date end) throws NotAuthorizedException,
			AuthenicationRequiredException, InvalidDataException,
			ExpiredException, NoSuchTopicException;

	public Capabilities discoverCapabilities() throws NotAuthorizedException,
			AuthenicationRequiredException;

	public RequestStatus requestAccess(@WebParam(name = "userInfo")User userInfo) throws ConflictException,
			FeatureNotAvailableException, InvalidDataException,
			IncompleteDataException;

}