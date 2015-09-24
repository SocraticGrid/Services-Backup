package org.socraticgrid.hl7.services.uc.model;
import java.io.Serializable;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 16-Dec-2013 3:54:57 PM
 */
public class SimpleMessageHeader extends MessageHeader implements Serializable {

	
	
	SimpleMessageHeader(String id) {
		super(id);
	}
	
	public SimpleMessageHeader()
	{
		
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UserContactInfo getReplyTo()
	{
		return replyTo;
	}

	public void setReplyTo(UserContactInfo replyTo)
	{
		this.replyTo = replyTo;
	}

	private UserContactInfo replyTo; //User Id

	
	@Override
	public MessageType getMessageType() {
		return MessageType.SimpleMessage;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SimpleMessageHeader [replyTo=" + replyTo + ", toString()="
				+ super.toString() + "]";
	}


}