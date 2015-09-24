package org.socraticgrid.hl7.services.eps.service.impl;

import java.util.Date;
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
import org.socraticgrid.hl7.services.eps.interfaces.SubscriptionIFace;
import org.socraticgrid.hl7.services.eps.internal.interfaces.TopicIFace;
import org.socraticgrid.hl7.services.eps.internal.interfaces.TopicLocatorIFace;
import org.socraticgrid.hl7.services.eps.model.Message;
import org.socraticgrid.hl7.services.eps.model.MessageSummary;
import org.socraticgrid.hl7.services.eps.model.Options;
import org.socraticgrid.hl7.services.eps.model.PresenceState;
import org.socraticgrid.hl7.services.eps.model.PullRange;
import org.socraticgrid.hl7.services.eps.model.Subscription;
import org.socraticgrid.hl7.services.eps.model.SubscriptionType;
import org.socraticgrid.hl7.services.eps.model.User;
import org.springframework.beans.factory.annotation.Autowired;

public class SubscriptionServiceImpl{

	//TODO   Extend this implementation to include User
	
	@Autowired
	TopicLocatorIFace topicLocator;
	
	public SubscriptionServiceImpl() {
	}



	public String subscribe(User user, List<String> topics, SubscriptionType type,
			Options options, String callbackAddress)
			throws NotAuthorizedException, AuthenicationRequiredException,
			ExpiredException, FeatureNotAvailableException,
			InvalidDataException, MediaFormatNotExceptedException,
			NoSuchTopicException, IncompleteDataException {
		//TODO   Implement
		return "12345";
	}

	public boolean unsubscribe(User user, List<String> topics, String subscriberId,
			String subscriptionid) throws NotAuthorizedException,
			AuthenicationRequiredException, NoSuchTopicException,
			NoSuchItemException, InvalidDataException {
		//TODO   Implement
		return false;
	}

	
	public boolean unsubscribeByOptions(User user, List<String> topics,
			String subscriberId, Options options)
			throws NotAuthorizedException, AuthenicationRequiredException,
			NoSuchTopicException, NoSuchItemException, InvalidDataException {
		// TODO Auto-generated method stub
		return false;
	}


	public List<Message> retrieveEvents(User user, String topic, PullRange pullRange,
			Date start, Date end, List<String> mediaForms)
			throws NotAuthorizedException, AuthenicationRequiredException,
			NoSuchTopicException, ExpiredException,
			FeatureNotAvailableException, MediaFormatNotExceptedException,
			NoSuchItemException {

		
		List<Message> out = new LinkedList<Message>();
		
		if (start != null)
		{
			if (end != null)
			{
				if (end.compareTo(start)<0)
				{
					throw new ExpiredException("End date before start date");
				}
			}
		}	
		
		TopicIFace top = topicLocator.locateTopic(topic);
		if (top == null)
		{
			NoSuchTopicException exp = new NoSuchTopicException();
			exp.setTopicId(topic);
			throw exp;
		}
		else
		{
			Subscription curSub = top.findSubscription(user, SubscriptionType.Pull);
			if (curSub != null)
			{
				out = top.retrieveEvents(curSub, pullRange, start, end, mediaForms);
			}
			else
			{
				//TODO  Address autosubscribe issues
				NotAuthorizedException exp = new NotAuthorizedException();
				exp.setMessage("No pull subscription for this user");
				throw exp;
			}
			
		}	
		return out;
	}


	public List<MessageSummary> retrieveEventSummaries(User user,String topic,
			PullRange pullRange, Date start, Date end, List<String> mediaForms)
			throws NotAuthorizedException, AuthenicationRequiredException,
			NoSuchTopicException, ExpiredException,
			FeatureNotAvailableException, MediaFormatNotExceptedException,
			NoSuchItemException {
		
		//TODO  Implement Pull Range Dynamics
		//TODO  Implement MediaForms
		
		List<MessageSummary> out;
		if (start != null)
		{
			if (end != null)
			{
				if (end.compareTo(start)<0)
				{
					throw new ExpiredException("End date before start date");
				}
			}
		}
		TopicIFace top = topicLocator.locateTopic(topic);
		if (top == null)
		{
			NoSuchTopicException exp = new NoSuchTopicException();
			exp.setTopicId(topic);
			throw exp;
		}
		else
		{
		
			out = top.discoverEvents(user, start, end);
			
		}	
		return out;
	}


	public List<Message> retrieveEvents(User user, String topic, List<String> mediaForms)
			throws NotAuthorizedException, AuthenicationRequiredException,
			NoSuchTopicException, ExpiredException,
			FeatureNotAvailableException, MediaFormatNotExceptedException,
			NoSuchItemException {
		
		
		List<Message> out = new LinkedList<Message>();
		

		
		TopicIFace top = topicLocator.locateTopic(topic);
		if (top == null)
		{
			NoSuchTopicException exp = new NoSuchTopicException();
			exp.setTopicId(topic);
			throw exp;
		}
		else
		{
			Subscription curSub = top.findSubscription(user, SubscriptionType.Pull);
			if (curSub != null)
			{	
				out = top.retrieveEvents(curSub, mediaForms);
			}
			else
			{
				//TODO  Address autosubscribe issues
				NotAuthorizedException exp = new NotAuthorizedException();
				exp.setMessage("No pull subscription for this user");
				throw exp;
			}
			
		}	
		
		return out;
	}

	
	public boolean replayEvents(User user, List<String> topics, Date start, Date end,
			String replayId, List<String> mediaForms, int replayRate)
			throws NotAuthorizedException, AuthenicationRequiredException,
			NoSuchTopicException, ExpiredException,
			FeatureNotAvailableException, MediaFormatNotExceptedException,
			InvalidDataException {

		throw new FeatureNotAvailableException();
	
	}


	public boolean assertSubscriberPresence(User user, String subscriberId,
			PresenceState presence) throws NotAuthorizedException,
			ConflictException, AuthenicationRequiredException, ExpiredException {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean configureSubscriptionOptions(User user, String topic,
			String subscriberId, String subscriptionId, Options options)
			throws NotAuthorizedException, AuthenicationRequiredException,
			NoSuchTopicException, ExpiredException, IncompleteDataException,
			FeatureNotAvailableException, ConflictException,
			NoSuchItemException, InvalidDataException {
		// TODO Auto-generated method stub
		return false;
	}

	public Options getDefaultSuscriptionOptions(String topic)
			throws NotAuthorizedException, AuthenicationRequiredException,
			NoSuchTopicException, FeatureNotAvailableException {
		Options out = new Options();
		return out;
	}

}
