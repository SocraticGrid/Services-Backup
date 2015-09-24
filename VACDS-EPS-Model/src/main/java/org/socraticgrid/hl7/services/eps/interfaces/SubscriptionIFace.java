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
import org.socraticgrid.hl7.services.eps.exceptions.MediaFormatNotExceptedException;
import org.socraticgrid.hl7.services.eps.exceptions.NoSuchItemException;
import org.socraticgrid.hl7.services.eps.exceptions.NoSuchTopicException;
import org.socraticgrid.hl7.services.eps.exceptions.NotAuthorizedException;
import org.socraticgrid.hl7.services.eps.model.Message;
import org.socraticgrid.hl7.services.eps.model.MessageSummary;
import org.socraticgrid.hl7.services.eps.model.Options;
import org.socraticgrid.hl7.services.eps.model.PresenceState;
import org.socraticgrid.hl7.services.eps.model.PullRange;
import org.socraticgrid.hl7.services.eps.model.SubscriptionType;

/**
 * Subscription
 * 
 * @author Jerry Goodnough
 * @version 1.0
 * @created 04-Jan-2014 6:15:20 PM
 */
@WebService(name = "subscriptionService", targetNamespace = "org.socraticgrid.hl7.services.eps")
public interface SubscriptionIFace {

	public String subscribe(@WebParam(name = "topics") List<String> topics,
			@WebParam(name = "type") SubscriptionType type,
			@WebParam(name = "options") Options options,
			@WebParam(name = "callbackAddress") String callbackAddress)
			throws NotAuthorizedException, AuthenicationRequiredException,
			ExpiredException, FeatureNotAvailableException,
			InvalidDataException, MediaFormatNotExceptedException,
			NoSuchTopicException, IncompleteDataException;

	public boolean unsubscribe(@WebParam(name = "topics") List<String> topics,
			@WebParam(name = "subscriberId") String subscriberId,
			@WebParam(name = "subscriptionid") String subscriptionid)
			throws NotAuthorizedException, AuthenicationRequiredException,
			NoSuchTopicException, NoSuchItemException, InvalidDataException;

	public boolean unsubscribeByOptions(
			@WebParam(name = "topics") List<String> topics,
			@WebParam(name = "subscriberId") String subscriberId,
			@WebParam(name = "options") Options options)
			throws NotAuthorizedException, AuthenicationRequiredException,
			NoSuchTopicException, NoSuchItemException, InvalidDataException;

	public List<Message> retrieveEvents(@WebParam(name = "topic") String topic,
			@WebParam(name = "pullRange") PullRange pullRange,
			@WebParam(name = "start") Date start,
			@WebParam(name = "end") Date end,
			@WebParam(name = "mediaForms") List<String> mediaForms)
			throws NotAuthorizedException, AuthenicationRequiredException,
			NoSuchTopicException, ExpiredException,
			FeatureNotAvailableException, MediaFormatNotExceptedException,
			NoSuchItemException;

	public List<MessageSummary> retrieveEventSummaries(
			@WebParam(name = "topic") String topic,
			@WebParam(name = "pullRange") PullRange pullRange,
			@WebParam(name = "start") Date start,
			@WebParam(name = "end") Date end,
			@WebParam(name = "mediaForms") List<String> mediaForms)
			throws NotAuthorizedException, AuthenicationRequiredException,
			NoSuchTopicException, ExpiredException,
			FeatureNotAvailableException, MediaFormatNotExceptedException,
			NoSuchItemException;

	public List<Message> retrieveEvent(@WebParam(name = "topic") String topic,
			@WebParam(name = "mediaForms") List<String> mediaForms)
			throws NotAuthorizedException, AuthenicationRequiredException,
			NoSuchTopicException, ExpiredException,
			FeatureNotAvailableException, MediaFormatNotExceptedException,
			NoSuchItemException;

	public boolean replayEvents(@WebParam(name = "topics") List<String> topics,
			@WebParam(name = "start") Date start,
			@WebParam(name = "end") Date end,
			@WebParam(name = "replayId") String replayId,
			@WebParam(name = "mediaForms") List<String> mediaForms,
			@WebParam(name = "replayRate") int replayRate)
			throws NotAuthorizedException, AuthenicationRequiredException,
			NoSuchTopicException, ExpiredException,
			FeatureNotAvailableException, MediaFormatNotExceptedException,
			InvalidDataException;

	public boolean assertSubscriberPresence(
			@WebParam(name = "subscriberId") String subscriberId,
			@WebParam(name = "presence") PresenceState presence)
			throws NotAuthorizedException, ConflictException,
			AuthenicationRequiredException, ExpiredException;

	public boolean configureSubscriptionOptions(
			@WebParam(name = "topic") String topic,
			@WebParam(name = "subscriberId") String subscriberId,
			@WebParam(name = "subscriptionId") String subscriptionId,
			@WebParam(name = "options") Options options)
			throws NotAuthorizedException, AuthenicationRequiredException,
			NoSuchTopicException, ExpiredException, IncompleteDataException,
			FeatureNotAvailableException, ConflictException,
			NoSuchItemException, InvalidDataException;

	public Options getDefaultSuscriptionOptions(
			@WebParam(name = "topic") String topic)
			throws NotAuthorizedException, AuthenicationRequiredException,
			NoSuchTopicException, FeatureNotAvailableException;

}