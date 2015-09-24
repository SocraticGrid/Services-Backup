/**
 * 
 */
package org.socraticgrid.hl7.services.eps.service.impl;

import java.util.LinkedList;
import java.util.List;

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
import org.socraticgrid.hl7.services.eps.interfaces.BrokerManagementIFace;
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
 *
 */
public class BrokerManagementServiceImpl implements BrokerManagementIFace {
	public BrokerManagementServiceImpl()
	{
		
	}

	@Override
	public CreationResult createTopic(String parentTopic, String topicName,
			Topic topicOptions) throws NotAuthorizedException,
			AuthenicationRequiredException, ConflictException,
			NoSuchTopicException, MediaFormatNotExceptedException,
			ExpiredException, FeatureNotAvailableException,
			InvalidDataException, IncompleteDataException {
		throw new FeatureNotAvailableException();
	}

	@Override
	public boolean deleteTopic(String topic) throws NotAuthorizedException,
			AuthenicationRequiredException, ConflictException,
			NoSuchTopicException {
		throw new NoSuchTopicException();
	}

	@Override
	public boolean configureTopic(String topic, Topic topicOptions)
			throws NotAuthorizedException, AuthenicationRequiredException,
			ConflictException, NoSuchTopicException,
			MediaFormatNotExceptedException, ExpiredException,
			FeatureNotAvailableException, InvalidDataException,
			IncompleteDataException {
		throw new FeatureNotAvailableException();
	}

	@Override
	public TopicMetadata getTopicMetadata(String topic)
			throws NotAuthorizedException, AuthenicationRequiredException,
			ExpiredException, NoSuchTopicException {
		throw new NoSuchTopicException();
	}

	@Override
	public List<PublicationContract> getPublishersForTopic(String topic)
			throws NotAuthorizedException, AuthenicationRequiredException,
			ExpiredException, NoSuchTopicException {
		throw new NoSuchTopicException();
	}

	@Override
	public List<Subscription> getSubscriptionsForTopic(String topic)
			throws NotAuthorizedException, AuthenicationRequiredException,
			ExpiredException, NoSuchTopicException {
		throw new NoSuchTopicException();
	}

	@Override
	public boolean purgeAllTopicEvents(String topic)
			throws NotAuthorizedException, AuthenicationRequiredException,
			NoSuchTopicException {
		throw new NoSuchTopicException();
	}

	@Override
	public boolean createUser(User userInfo) throws NotAuthorizedException,
			AuthenicationRequiredException, ConflictException,
			InvalidDataException, IncompleteDataException {
		return false;
	}

	@Override
	public boolean deleteUser(String userId, RemoveContentScope scope)
			throws NotAuthorizedException, AuthenicationRequiredException,
			NoSuchItemException {
		throw new NoSuchItemException();
	}

	@Override
	public User getUser(String userId) throws NotAuthorizedException,
			AuthenicationRequiredException, InvalidDataException,
			NoSuchItemException {
		throw new NoSuchItemException();
	}

	@Override
	public List<User> discoverUser(String query) throws NotAuthorizedException,
			AuthenicationRequiredException, InvalidDataException,
			NoSuchItemException {
		List<User> out = new LinkedList<User>();
		return out;
	}

	@Override
	public void moveTopic(String sourceTopic, String destinationPath)
			throws NotAuthorizedException, AuthenicationRequiredException,
			ConflictException, NoSuchTopicException {
		throw new NoSuchTopicException();
		
	}

	@Override
	public void updateUser(String userId, Options options, User userData)
			throws NotAuthorizedException, AuthenicationRequiredException,
			NoSuchItemException, MediaFormatNotExceptedException,
			InvalidDataException, IncompleteDataException {
		throw new NoSuchItemException();
		
	}

	@Override
	public List<BrokerAccessRequest> getBrokerAccessRequests()
			throws NotAuthorizedException, AuthenicationRequiredException,FeatureNotAvailableException {
		List<BrokerAccessRequest> out = new LinkedList<BrokerAccessRequest>();
		throw new FeatureNotAvailableException();
	}

	@Override
	public boolean grantBrokerAccessRequest(List<BrokerAccessRequest> request)
			throws NotAuthorizedException, AuthenicationRequiredException,
			NoSuchItemException, InvalidDataException, IncompleteDataException, FeatureNotAvailableException {
		throw new FeatureNotAvailableException();
	}

	@Override
	public boolean rejectBrokerAccessRequest(List<BrokerAccessRequest> requests)
			throws NotAuthorizedException, AuthenicationRequiredException,
			NoSuchItemException, InvalidDataException, IncompleteDataException,FeatureNotAvailableException {
	
		throw new FeatureNotAvailableException();
		
	}
	
}
