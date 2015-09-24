package org.socraticgrid.hl7.services.uc.functional;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.socraticgrid.hl7.services.uc.interfaces.UCSAdapterIntf;
import org.socraticgrid.hl7.services.uc.internal.interfaces.ServiceRegistryIntf;
import org.socraticgrid.hl7.services.uc.model.AdapterMessage;
import org.socraticgrid.hl7.services.uc.model.Message;
import org.socraticgrid.hl7.services.uc.model.PhysicalAddress;
import org.socraticgrid.hl7.services.uc.operational.MessageWrapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Messages that are Active in the UCS
 * 
 * @author Jerry Goodnough
 * @version 1.0
 * @created 16-Dec-2013 3:54:56 PM
 */


// TODO - Work out queuing, recovery, priority etc.  Add PriorityQueue and Hand Off
// TODO - Delivery should have a maximum number of worker threads and be configured to pull from the queue.
// 
public class DeliveryManager {

	@Autowired
	ServiceRegistryIntf services;
	
	private final Logger logger = LoggerFactory.getLogger(DeliveryManager.class);
	
	public DeliveryManager(){

	}


	public <T extends Message> void deliverMessage(MessageWrapper<T> msgWrap)
	{
	
		// Add to delivery queue.
		
		
		// TODO - Handle Sync/ASync execution here - See Spring (http://docs.spring.io/spring/docs/3.0.x/reference/scheduling.html)
		
		// If the semantics of the message request require sync behavior then do so
		// Normal operational semantics is for Async behavior
		//
		// If the message semantic require it add to durable queue to insure recovery occurs
		// post to operational queue
		
		//For quick testing we just process the message
		this.processMessage(msgWrap);
	}
	
	/**
	 * Process the message that has been pulled to the delivery queue
	 * 
	 * @param msgWrap
	 */
	public <T extends Message> void processMessage(MessageWrapper<T> msgWrap)
	{
		
		
		String serverId = services.getServerId();
		// Loop each adapter
		
		HashMap<String,List<PhysicalAddress>> addMap = msgWrap.getAddressMap();
		
		Set<String> adapters = addMap.keySet();
		Iterator<String> itr = adapters.iterator();
		while(itr.hasNext())
		{
			String adapterName = itr.next();
			UCSAdapterIntf adapter = services.getAdapter(adapterName);
			if (adapter != null)
			{
		// TODO:  To what degree do we want possible parallel behavior on the adapter hand off - Seem like an potential bind point.
		
		// TODO:  Should adapters have a operational priority?
			AdapterMessage adptrMsg = new AdapterMessage(msgWrap.getMessage(), addMap.get(adapterName));
			
		// TODO:  TRACK The hand off on each message to the adapter
			try
			{
				adapter.sendMessage(adptrMsg,serverId);
			}
			catch(Throwable exp)
			{
				logger.error("Failure to send message",exp);
			}
		// TODO:  Deal with any exceptions from the hand off
		// TODO:  After hand off mark as "Sent"
			}
			else
			{
				//Somewhere along the line this adapter has disappeared
				//TODO Handle thw exception.
			}
		// TODO:  Update Tracking Status (Durable)
		}
		// 
	}
}