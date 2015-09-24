package org.socraticgrid.hl7.services.eps.interfaces;

import javax.jws.WebParam;
import javax.jws.WebService;

import org.socraticgrid.hl7.services.eps.exceptions.AuthenicationRequiredException;
import org.socraticgrid.hl7.services.eps.exceptions.ConflictException;
import org.socraticgrid.hl7.services.eps.exceptions.ExpiredException;
import org.socraticgrid.hl7.services.eps.exceptions.IncompleteDataException;
import org.socraticgrid.hl7.services.eps.exceptions.InvalidDataException;
import org.socraticgrid.hl7.services.eps.exceptions.MediaFormatNotExceptedException;
import org.socraticgrid.hl7.services.eps.exceptions.NoSuchItemException;
import org.socraticgrid.hl7.services.eps.exceptions.NoSuchTopicException;
import org.socraticgrid.hl7.services.eps.exceptions.NotAuthorizedException;
import org.socraticgrid.hl7.services.eps.model.Message;
import org.socraticgrid.hl7.services.eps.model.PresenceState;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 04-Jan-2014 6:15:20 PM
 */
@WebService(name = "publication", targetNamespace = "org.socraticgrid.hl7.services.eps")
public interface PublicationIFace {

	public String publishEvent(@WebParam(name = "topic") String topic,
			@WebParam(name = "event") Message event)
			throws NotAuthorizedException, AuthenicationRequiredException,
			NoSuchTopicException, IncompleteDataException,
			InvalidDataException, MediaFormatNotExceptedException;

	public void deleteEvent(@WebParam(name = "topic") String topic,
			@WebParam(name = "messageId") String messageId)
			throws NoSuchItemException, NoSuchTopicException, ExpiredException;

	public boolean assertPublisherPresence(
			@WebParam(name = "topic") String topic,
			@WebParam(name = "presence") PresenceState presence)
			throws NotAuthorizedException, ConflictException,
			AuthenicationRequiredException, ExpiredException;

}