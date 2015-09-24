package org.socraticgrid.hl7.services.eps.internal.interfaces;

import org.socraticgrid.hl7.services.eps.exceptions.IncompleteDataException;
import org.socraticgrid.hl7.services.eps.exceptions.InvalidDataException;
import org.socraticgrid.hl7.services.eps.exceptions.MediaFormatNotExceptedException;
import org.socraticgrid.hl7.services.eps.exceptions.NoSuchTopicException;
import org.socraticgrid.hl7.services.eps.model.Message;

public interface PublicationValidationStepIFace {

	public boolean valdiateMessage(String topic, Message message) throws NoSuchTopicException, IncompleteDataException,
	InvalidDataException, MediaFormatNotExceptedException; 
}
