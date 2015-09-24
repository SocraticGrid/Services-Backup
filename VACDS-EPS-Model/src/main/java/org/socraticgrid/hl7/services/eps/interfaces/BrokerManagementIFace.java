package org.socraticgrid.hl7.services.eps.interfaces;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import org.socraticgrid.hl7.services.eps.exceptions.AuthenicationRequiredException;
import org.socraticgrid.hl7.services.eps.exceptions.ConflictException;
import org.socraticgrid.hl7.services.eps.exceptions.ExpiredException;
import org.socraticgrid.hl7.services.eps.exceptions.FeatureNotAvailableException;
import org.socraticgrid.hl7.services.eps.exceptions.IncompleteDataException;
import org.socraticgrid.hl7.services.eps.exceptions.InvalidDataException;
import org.socraticgrid.hl7.services.eps.exceptions.MediaFormatNotExceptedException;
import org.socraticgrid.hl7.services.eps.exceptions.NoSuchItemException;
import org.socraticgrid.hl7.services.eps.exceptions.NoSuchTopicException;
import org.socraticgrid.hl7.services.eps.exceptions.NotAuthorizedException;
import org.socraticgrid.hl7.services.eps.model.BrokerAccessRequest;
import org.socraticgrid.hl7.services.eps.model.CreationResult;
import org.socraticgrid.hl7.services.eps.model.Options;
import org.socraticgrid.hl7.services.eps.model.PublicationContract;
import org.socraticgrid.hl7.services.eps.model.RemoveContentScope;
import org.socraticgrid.hl7.services.eps.model.Subscription;
import org.socraticgrid.hl7.services.eps.model.Topic;
import org.socraticgrid.hl7.services.eps.model.TopicMetadata;
import org.socraticgrid.hl7.services.eps.model.User;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 04-Jan-2014 6:15:19 PM
 */
@WebService(name = "brokermanagement", targetNamespace = "org.socraticgrid.hl7.services.eps")
public interface BrokerManagementIFace {

	public CreationResult createTopic(
			@WebParam(name = "parentTopic") String parentTopic,
			@WebParam(name = "topicName") String topicName,
			@WebParam(name = "topicOptions") Topic topicOptions)
			throws NotAuthorizedException, AuthenicationRequiredException,
			ConflictException, NoSuchTopicException,
			MediaFormatNotExceptedException, ExpiredException,
			FeatureNotAvailableException, InvalidDataException,
			IncompleteDataException;

	public boolean deleteTopic(@WebParam(name = "topic") String topic)
			throws NotAuthorizedException, AuthenicationRequiredException,
			ConflictException, NoSuchTopicException;

	public boolean configureTopic(@WebParam(name = "topic") String topic,
			@WebParam(name = "topicOptions") Topic topicOptions)
			throws NotAuthorizedException, AuthenicationRequiredException,
			ConflictException, NoSuchTopicException,
			MediaFormatNotExceptedException, ExpiredException,
			FeatureNotAvailableException, InvalidDataException,
			IncompleteDataException;

	public TopicMetadata getTopicMetadata(@WebParam(name = "topic") String topic)
			throws NotAuthorizedException, AuthenicationRequiredException,
			ExpiredException, NoSuchTopicException;

	public List<PublicationContract> getPublishersForTopic(
			@WebParam(name = "topic") String topic)
			throws NotAuthorizedException, AuthenicationRequiredException,
			ExpiredException, NoSuchTopicException;

	public List<Subscription> getSubscriptionsForTopic(
			@WebParam(name = "topic") String topic)
			throws NotAuthorizedException, AuthenicationRequiredException,
			ExpiredException, NoSuchTopicException;

	public boolean purgeAllTopicEvents(@WebParam(name = "topic") String topic)
			throws NotAuthorizedException, AuthenicationRequiredException,
			NoSuchTopicException;

	public boolean createUser(@WebParam(name = "userInfo") User userInfo)
			throws NotAuthorizedException, AuthenicationRequiredException,
			ConflictException, InvalidDataException, IncompleteDataException;

	public boolean deleteUser(@WebParam(name = "userId") String userId,
			@WebParam(name = "topic") RemoveContentScope scope)
			throws NotAuthorizedException, AuthenicationRequiredException,
			NoSuchItemException;

	public User getUser(@WebParam(name = "userId") String userId)
			throws NotAuthorizedException, AuthenicationRequiredException,
			InvalidDataException, NoSuchItemException;

	public List<User> discoverUser(@WebParam(name = "query") String query)
			throws NotAuthorizedException, AuthenicationRequiredException,
			InvalidDataException, NoSuchItemException;

	public void moveTopic(@WebParam(name = "sourceTopic") String sourceTopic,
			@WebParam(name = "destinationPath") String destinationPath)
			throws NotAuthorizedException, AuthenicationRequiredException,
			ConflictException, NoSuchTopicException;

	public void updateUser(@WebParam(name = "topic") String userId,
			@WebParam(name = "options") Options options,
			@WebParam(name = "userData") User userData)
			throws NotAuthorizedException, AuthenicationRequiredException,
			NoSuchItemException, MediaFormatNotExceptedException,
			InvalidDataException, IncompleteDataException;

	public List<BrokerAccessRequest> getBrokerAccessRequests()
			throws NotAuthorizedException, AuthenicationRequiredException,
			FeatureNotAvailableException;

	public boolean grantBrokerAccessRequest(
			@WebParam(name = "request") List<BrokerAccessRequest> request)
			throws NotAuthorizedException, AuthenicationRequiredException,
			NoSuchItemException, InvalidDataException, IncompleteDataException,
			FeatureNotAvailableException;

	public boolean rejectBrokerAccessRequest(
			@WebParam(name = "requests") List<BrokerAccessRequest> requests)
			throws NotAuthorizedException, AuthenicationRequiredException,
			NoSuchItemException, InvalidDataException, IncompleteDataException,
			FeatureNotAvailableException;
}