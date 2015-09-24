package org.socraticgrid.hl7.services.uc.functional;
import java.util.List;

import org.socraticgrid.hl7.services.uc.interfaces.UCSAdapterIntf;
import org.socraticgrid.hl7.services.uc.model.AdapterMessage;
import org.socraticgrid.hl7.services.uc.model.InteractionTypes;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 16-Dec-2013 3:54:55 PM
 */
public class AdapterImplementation implements UCSAdapterIntf {

	public String Name;

	public AdapterImplementation(){

	}


	/**
	 * 
	 * @param Message
	 */
	public boolean sendMessage(AdapterMessage Message, String serverId){
		return false;
	}

	/**
	 * 
	 * @param Message
	 */
	public boolean updateMessage(AdapterMessage Message, AdapterMessage oldMessage, String serverId){
		return false;
	}

	/**
	 * 
	 * @param Message
	 */
	public boolean cancelMessage(AdapterMessage Message, String serverId){
		return false;
	}

	/**
	 * Used by UCS to map Address to Adapters
	 */
	public String getServiceId(){
		return "";
	}

	public List<InteractionTypes> getInteractionTypes(){
		return null;
	}

}