package org.socraticgrid.hl7.services.uc.interfaces;
import java.util.List;

import org.socraticgrid.hl7.services.uc.model.AdapterMessage;
import org.socraticgrid.hl7.services.uc.model.InteractionTypes;

/**
 * Is a separate interface required for incoming messages?
 * @author Jerry Goodnough
 * @version 1.0
 * @updated 17-Dec-2013 1:48:20 PM
 */
public interface UCSAdapterIntf {


	/**
	 * 
	 * @param message    Message
	 */
	public boolean sendMessage(AdapterMessage message,String serverId);

	/**
	 * 
	 * @param newMessage    Message
	 * @param oldMessage TODO
	 */
	public boolean updateMessage(AdapterMessage newMessage, AdapterMessage oldMessage, String serverId);

	/**
	 * 
	 * @param message    Message
	 */
	public boolean cancelMessage(AdapterMessage message, String serverId);

	/**
	 * Used by UCS to map Address to Adapters
	 */
	public String getServiceId();

	public List<InteractionTypes> getInteractionTypes();

}