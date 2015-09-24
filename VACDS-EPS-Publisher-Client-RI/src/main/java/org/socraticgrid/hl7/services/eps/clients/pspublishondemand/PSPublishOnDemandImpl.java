package org.socraticgrid.hl7.services.eps.clients.pspublishondemand;

import java.util.Date;
import java.util.List;

import javax.jws.WebParam;

import org.socraticgrid.hl7.services.eps.exceptions.AuthenicationRequiredException;
import org.socraticgrid.hl7.services.eps.exceptions.ExpiredException;
import org.socraticgrid.hl7.services.eps.exceptions.FeatureNotAvailableException;
import org.socraticgrid.hl7.services.eps.exceptions.MediaFormatNotExceptedException;
import org.socraticgrid.hl7.services.eps.exceptions.NoSuchItemException;
import org.socraticgrid.hl7.services.eps.exceptions.NoSuchTopicException;
import org.socraticgrid.hl7.services.eps.exceptions.NotAuthorizedException;
import org.socraticgrid.hl7.services.eps.interfaces.PSPublishOnDemandIFace;
import org.socraticgrid.hl7.services.eps.model.Message;
import org.socraticgrid.hl7.services.eps.model.MessageSummary;
import org.socraticgrid.hl7.services.eps.model.PullRange;
import org.socraticgrid.hl7.services.eps.model.TopicSummary;

public class PSPublishOnDemandImpl implements PSPublishOnDemandIFace {

	public PSPublishOnDemandImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<TopicSummary> discoverTopics(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean endPublishingOnTopic(String Topic) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<TopicSummary> getSubTopics(String topic) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean replayEvents(List<String> topics, Date start, Date end,
			String replayId, List<String> mediaForms, int replayRate)
			throws NotAuthorizedException, AuthenicationRequiredException,
			NoSuchTopicException, ExpiredException,
			FeatureNotAvailableException, MediaFormatNotExceptedException,
			NoSuchItemException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Message> retrieveEvents(String topic,
			@WebParam(name = "start") Date start,
			@WebParam(name = "end") Date end, List<String> mediaForms)
			throws NotAuthorizedException, AuthenicationRequiredException,
			NoSuchTopicException, ExpiredException,
			FeatureNotAvailableException, MediaFormatNotExceptedException,
			NoSuchItemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Message> retrieveEvents(String topic, PullRange pullRange,
			Date start, Date end, List<String> mediaForms)
			throws NotAuthorizedException, AuthenicationRequiredException,
			NoSuchTopicException, ExpiredException,
			FeatureNotAvailableException, MediaFormatNotExceptedException,
			NoSuchItemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MessageSummary> retrieveEventSumariess(String topic,
			PullRange pullRange, Date start, Date end, List<String> mediaForms)
			throws NotAuthorizedException, AuthenicationRequiredException,
			NoSuchTopicException, ExpiredException,
			FeatureNotAvailableException, MediaFormatNotExceptedException,
			NoSuchItemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean startPublishingOnTopic(Date StartTime, String Topic) {
		// TODO Auto-generated method stub
		return false;
	}

}
