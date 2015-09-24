package org.socraticgrid.hl7.services.eps.internal.validatationstep.publication;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.socraticgrid.hl7.services.eps.exceptions.IncompleteDataException;
import org.socraticgrid.hl7.services.eps.exceptions.InvalidDataException;
import org.socraticgrid.hl7.services.eps.exceptions.MediaFormatNotExceptedException;
import org.socraticgrid.hl7.services.eps.exceptions.NoSuchTopicException;
import org.socraticgrid.hl7.services.eps.internal.interfaces.PublicationValidationStepIFace;
import org.socraticgrid.hl7.services.eps.model.Message;

public class TopicNaming implements PublicationValidationStepIFace {

	private boolean autoCorrect = true;
	
	@Override
	public boolean valdiateMessage(String topic, Message message)
			throws NoSuchTopicException, IncompleteDataException,
			InvalidDataException, MediaFormatNotExceptedException {

		List<String> topics = message.getTopics();
		LinkedList<String> autoCorrectList = new LinkedList<String>();
		
		Iterator<String> itr = topics.iterator();
		while (itr.hasNext())
		{
			String localTopic = itr.next();
			if (localTopic.startsWith("/")== false)
			{
				if (autoCorrect)
				{
					itr.remove();
					autoCorrectList.add("/"+localTopic);
				}
				else
				{
					InvalidDataException exp = new InvalidDataException("Invalid Topic Name, Missing root prefex (/) for topic "+localTopic);
					throw exp;
				}
			}
		}
		if (autoCorrect && autoCorrectList.isEmpty()==false)
		{
			topics.addAll(autoCorrectList);
		}
		return true;
	}

	/**
	 * @return the autoCorrect
	 */
	public boolean isAutoCorrect() {
		return autoCorrect;
	}

	/**
	 * @param autoCorrect the autoCorrect to set
	 */
	public void setAutoCorrect(boolean autoCorrect) {
		this.autoCorrect = autoCorrect;
	}

}
