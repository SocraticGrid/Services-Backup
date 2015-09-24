/**
 * 
 */
package org.socraticgrid.hl7.services.uc.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;


/**
 * @author steven
 * @param <E>
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class MessageModel<T extends Message> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	@XmlElements(value = { 
            @XmlElement(name="notification", 
                        type=NotificationMessage.class),
            @XmlElement(name="alert", 
                        type=AlertMessage.class),
            @XmlElement(name="conversation", 
                        type=ConversationRequestMessage.class), 
            @XmlElement(name="simple",
            	type=SimpleMessage.class)
    })
	private T messageType;
	
	public T getMessageType() {
		return messageType;
	}
	public void setMessageType(T messageType) {
		this.messageType = messageType;
	}
		
	public MessageModel(T msg)
	{
		this.setMessageType(msg);
	}
	
	public MessageModel()
	{
		
	}
}

