package org.socraticgrid.hl7.services.eps.internal.validatationstep.publication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.socraticgrid.hl7.services.eps.exceptions.IncompleteDataException;
import org.socraticgrid.hl7.services.eps.exceptions.InvalidDataException;
import org.socraticgrid.hl7.services.eps.exceptions.MediaFormatNotExceptedException;
import org.socraticgrid.hl7.services.eps.exceptions.NoSuchTopicException;
import org.socraticgrid.hl7.services.eps.internal.interfaces.PublicationValidationStepIFace;
import org.socraticgrid.hl7.services.eps.model.Message;

public class TopicInEvent implements PublicationValidationStepIFace {
	private final Logger logger = LoggerFactory.getLogger(TopicInEvent.class);

	boolean autoCorrect = false;

	@Override
	public boolean valdiateMessage(String topic, Message message)
			throws NoSuchTopicException, IncompleteDataException,
			InvalidDataException, MediaFormatNotExceptedException {

		if (message.getTopics().contains(topic) == false) {
			if (autoCorrect) {
				logger.info("Topic being published to was not included in the list of topics in the message, automatically corrected");
				message.getTopics().add(topic);
			} else {
				IncompleteDataException exp = new IncompleteDataException("Message does not include the topic as one it is intended for");
				exp.setMissingElementName(topic);
				throw exp;
			}
			if (message.getHeader().getTopicId() == null)
			{
				if (autoCorrect)
				{
					message.getHeader().setTopicId(topic);
				}
				else
				{
					IncompleteDataException exp = new IncompleteDataException("Message header is missing the topic");
					exp.setMissingElementName(topic);
					throw exp;
				}
			}
			else if(message.getHeader().getTopicId().compareTo(topic)!=0)
			{
				if (autoCorrect)
				{
					message.getHeader().setTopicId(topic);
				}
				else
				{
					IncompleteDataException exp = new IncompleteDataException("Message header topic does not match the pushed topic");
					exp.setMissingElementName(topic);
					throw exp;					
				}
			}
		}
		return true;
	}

	/**
	 * This error can be automatically corrected. The validation step will
	 * correct the error if the property is true.
	 * 
	 * @return the autoCorrect
	 */
	public boolean isAutoCorrect() {
		return autoCorrect;
	}

	/**
	 * Change the auto correct setting
	 * 
	 * @param autoCorrect
	 *            the autoCorrect to set
	 */
	public void setAutoCorrect(boolean correct) {
		this.autoCorrect = correct;
	}

}
