package org.socraticgrid.hl7.services.uc.model;
import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.socraticgrid.hl7.services.uc.exceptions.ProcessingException;

/**
 * @version 1.0
 * @created 16-Dec-2013 3:54:56 PM
 */

/*
 * 
 * We use the @XmlSeeAlso to allow JAXB unmarshalling to get the correct subclasses
 * Don't Really like a downward dependence.. But clearly JAXB is not very smart for inheritence
 * The @XmlTransient might be required but may cause and issue in other contexts
 * 
 */

@XmlAccessorType(XmlAccessType.FIELD)
//@XmlTransient
@XmlSeeAlso({SimpleMessage.class,NotificationMessage.class,AlertMessage.class,ConversationRequestMessage.class})
public abstract class Message implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@XmlElement(required=true)	
	private MessageBody[] parts;

	public MessageBody[] getParts()
	{
		return parts;
	}

	public void setParts(MessageBody[] parts)
	{
		this.parts = parts;
	}


	@XmlElement(required=false)
	private List<ProcessingException> exceptions;

	public List<ProcessingException> getExceptions()
	{
		return exceptions;
	}
	public void setExceptions(List<ProcessingException> exceptions)
	{
		this.exceptions = exceptions;
	}

	public Message(){
		exceptions = new LinkedList<ProcessingException>();
	}


	abstract public MessageHeader getHeader();

	abstract MessageType getMessageType();

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Message [header: "+getHeader().toString()+", parts=" + Arrays.toString(parts) + ", exceptions="
				+ exceptions + "]";
	}

}