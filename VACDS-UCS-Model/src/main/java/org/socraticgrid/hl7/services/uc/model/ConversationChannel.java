package org.socraticgrid.hl7.services.uc.model;

import java.io.Serializable;

import javax.jws.WebMethod;

/**
 * @version 1.0
 * @created 16-Dec-2013 3:54:56 PM
 */
public class ConversationChannel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String conversationChannelId;

	public ConversationChannel(){

	}


	@WebMethod(exclude=false)
	public String getConversationChannelId() {
		return conversationChannelId;
	}
	@WebMethod(exclude=false)
	public void setConversationChannelId(String conversationChannelId) {
		this.conversationChannelId = conversationChannelId;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((conversationChannelId == null) ? 0 : conversationChannelId
						.hashCode());
		return result;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ConversationChannel))
			return false;
		ConversationChannel other = (ConversationChannel) obj;
		if (conversationChannelId == null) {
			if (other.conversationChannelId != null)
				return false;
		} else if (!conversationChannelId.equals(other.conversationChannelId))
			return false;
		return true;
	}
	

	/**
	 * ONLY FOR MYBATIS
	 */

}