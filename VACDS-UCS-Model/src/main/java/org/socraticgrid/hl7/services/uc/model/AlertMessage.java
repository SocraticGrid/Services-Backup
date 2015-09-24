package org.socraticgrid.hl7.services.uc.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 16-Dec-2013 3:54:55 PM
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class AlertMessage extends Message implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@XmlElement(required=true)
	private AlertMessageHeader alertMessageHeader;
	
	public AlertMessage(String messageId) {
		this.alertMessageHeader = new AlertMessageHeader(messageId);
	}
	
	public AlertMessage(AlertMessageHeader header) {
		this.alertMessageHeader = header;
	}
	
	public AlertMessage()
	{
		this.alertMessageHeader = new AlertMessageHeader();
	}

	@Override
	public AlertMessageHeader getHeader()
	{
		return alertMessageHeader;
	}

	@Override
	MessageType getMessageType() {
		return MessageType.Alert;
	}

}