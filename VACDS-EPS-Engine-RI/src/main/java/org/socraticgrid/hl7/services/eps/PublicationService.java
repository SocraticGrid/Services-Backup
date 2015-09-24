package org.socraticgrid.hl7.services.eps;

import javax.jws.WebParam;
import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.socraticgrid.hl7.services.eps.exceptions.AuthenicationRequiredException;
import org.socraticgrid.hl7.services.eps.exceptions.ConflictException;
import org.socraticgrid.hl7.services.eps.exceptions.ExpiredException;
import org.socraticgrid.hl7.services.eps.exceptions.IncompleteDataException;
import org.socraticgrid.hl7.services.eps.exceptions.InvalidDataException;
import org.socraticgrid.hl7.services.eps.exceptions.MediaFormatNotExceptedException;
import org.socraticgrid.hl7.services.eps.exceptions.NoSuchItemException;
import org.socraticgrid.hl7.services.eps.exceptions.NoSuchTopicException;
import org.socraticgrid.hl7.services.eps.exceptions.NotAuthorizedException;
import org.socraticgrid.hl7.services.eps.exceptions.PubSubException;
import org.socraticgrid.hl7.services.eps.interfaces.PublicationIFace;
import org.socraticgrid.hl7.services.eps.model.Message;
import org.socraticgrid.hl7.services.eps.model.PresenceState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@WebService(name = "publication", targetNamespace = "org.socraticgrid.hl7.services.eps")
public class PublicationService implements PublicationIFace {

	@Autowired
	@Qualifier("PublicationServiceImpl")
	PublicationIFace publicationImpl;

	private final Logger logger = LoggerFactory
			.getLogger(PublicationService.class);

	@Override
	public String publishEvent(@WebParam(name = "topic") String topic,
			@WebParam(name = "event") Message event)
			throws NotAuthorizedException, AuthenicationRequiredException,
			NoSuchTopicException, IncompleteDataException,
			InvalidDataException, MediaFormatNotExceptedException {

		String out="";
		try
		{
			logger.debug("Publishing to topic "+topic);
			out = publicationImpl.publishEvent(topic, event);
		}
		catch(PubSubException exp)
		{
			logger.warn("Expected possible Exception publishing event",exp);
			throw exp;
		}
		catch(Throwable exp)
		{
			logger.error("Unexcepted Exception publishing event",exp);
		}

		return out;
	}

	@Override
	public void deleteEvent(@WebParam(name = "topic") String topic,
			@WebParam(name = "messageId") String messageId)
			throws NoSuchItemException, NoSuchTopicException, ExpiredException {
		try
		{
			logger.debug("Deleting Event on Topic "+topic+" Id ="+messageId);
			publicationImpl.deleteEvent(topic, messageId);
		}
		catch(PubSubException exp)
		{
			logger.warn("Expected possible Exception deleting message",exp);
			throw exp;
		}
		catch(Throwable exp)
		{
			logger.error("Unexcepted Exception deleting message",exp);
		}
	}

	@Override
	public boolean assertPublisherPresence(
			@WebParam(name = "topic") String publisherId,
			@WebParam(name = "presence") PresenceState presence)
			throws NotAuthorizedException, ConflictException,
			AuthenicationRequiredException, ExpiredException {
		return publicationImpl.assertPublisherPresence(publisherId, presence);
	}
}
