package org.socraticgrid.hl7.services.eps.internal.validatationstep.publication;

import java.util.Iterator;
import java.util.List;

import org.socraticgrid.hl7.services.eps.exceptions.IncompleteDataException;
import org.socraticgrid.hl7.services.eps.exceptions.InvalidDataException;
import org.socraticgrid.hl7.services.eps.exceptions.MediaFormatNotExceptedException;
import org.socraticgrid.hl7.services.eps.exceptions.NoSuchTopicException;
import org.socraticgrid.hl7.services.eps.internal.interfaces.PublicationValidationStepIFace;
import org.socraticgrid.hl7.services.eps.internal.interfaces.TopicIFace;
import org.socraticgrid.hl7.services.eps.internal.interfaces.TopicLocatorIFace;
import org.socraticgrid.hl7.services.eps.model.Message;
import org.springframework.beans.factory.annotation.Autowired;

public class TopicExistence implements PublicationValidationStepIFace {

	@Autowired
	TopicLocatorIFace topicLocator;
	
	@Override
	public boolean valdiateMessage(String topic, Message message)
			throws NoSuchTopicException, IncompleteDataException,
			InvalidDataException, MediaFormatNotExceptedException {
		
		
		List<String> topics = message.getTopics();
		Iterator<String> itr = topics.iterator();
		while (itr.hasNext())
		{
			String localTopic = itr.next();
			TopicIFace tp = topicLocator.locateTopic(localTopic);
			if (tp == null)
			{
				NoSuchTopicException exp = new NoSuchTopicException("Topic does not exist");
				exp.setTopicId(localTopic);
				throw exp;
			}
		}
		return true;

	}

}
