package org.socraticgrid.hl7.services.uc.adapters.test;
import java.util.List;

import org.socraticgrid.hl7.services.uc.interfaces.UCSAdapterIntf;
import org.socraticgrid.hl7.services.uc.model.AdapterMessage;
import org.socraticgrid.hl7.services.uc.model.InteractionTypes;

import java.util.logging.Logger;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 16-Dec-2013 3:54:57 PM
 */
public class TestAdapter implements UCSAdapterIntf {

	private static Logger getLogger()
	{
		return Logger.getLogger(TestAdapter.class.getName());
		
	}
	public TestAdapter(){

	}


	public boolean sendMessage(){
		return false;
	}

	public boolean updateMessage(){
		return false;
	}

	public boolean cancelMessage(){
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

	@Override
	public boolean sendMessage(AdapterMessage message, String serverId)
	{
		getLogger().info("Test Adapter Received message to Send: "+message.toString());
		return false;
	}

	@Override
	public boolean updateMessage(AdapterMessage newMessage, AdapterMessage oldMessage, String serverId)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean cancelMessage(AdapterMessage message, String serverId)
	{
		// TODO Auto-generated method stub
		return false;
	}

}