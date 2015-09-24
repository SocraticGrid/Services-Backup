package org.socraticgrid.hl7.services.uc.functional;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.socraticgrid.hl7.services.uc.model.Message;
import org.socraticgrid.hl7.services.uc.model.MessageModel;

/**
 * Messages in a directory structure segmented by sender - Supports a nested
 * directory notion. This provides the organization and working storage for
 * message both durable and transient. Is there an Inbox?
 * @author Jerry Goodnough
 * @version 1.0
 * @created 16-Dec-2013 3:54:57 PM
 */
public class MessageManager {
	
	private Logger logger = LoggerFactory.getLogger(MessageManager.class);

//	public UserMessageDirectory m_UserMessageDirectory;
	private ConcurrentHashMap<String, MessageModel<? extends Message>> directory = new ConcurrentHashMap<>();

	public MessageManager(){

	}

	
	public <T extends Message> void saveMessage(MessageModel<T> message){
		directory.put(message.getMessageType().getHeader().getMessageId(), message);
		logger.debug("\n********\n\t MessageManager.saveMessage() for messageId = "+message.getMessageType().getHeader().getMessageId()
				+" from Sender "+message.getMessageType().getHeader().getSender());
	}

	public  MessageModel<? extends Message> getMessageById(String messageId)
	{
		MessageModel<? extends Message> storedMsg = directory.get(messageId);
		if( storedMsg != null ) {
			logger.debug("\n********\n\t MessageManager.getMessageById() for messageId = "+messageId);
		}
		else{
			logger.debug("\n********\n\t MessageManager.getMessageById() for messageId = "+messageId+" is NULL!! \n");
		}
		return storedMsg;
	}
	
	
	public <T extends Message>  void updateMessage(MessageModel<T> message){
		directory.put(message.getMessageType().getHeader().getMessageId(), message);
	}

}