package org.socraticgrid.hl7.services.uc.adapters.alerting;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.socraticgrid.hl7.services.uc.adapters.alerting.interfaces.AddressResolverIntf;
import org.socraticgrid.hl7.services.uc.adapters.alerting.interfaces.DispatcherIntf;
import org.socraticgrid.hl7.services.uc.exceptions.ExceptionType;
import org.socraticgrid.hl7.services.uc.exceptions.InvalidConversationException;
import org.socraticgrid.hl7.services.uc.exceptions.InvalidMessageException;
import org.socraticgrid.hl7.services.uc.exceptions.ProcessingException;
import org.socraticgrid.hl7.services.uc.interfaces.AdapterIntf;
import org.socraticgrid.hl7.services.uc.interfaces.UCSAdapterIntf;
import org.socraticgrid.hl7.services.uc.model.AdapterMessage;
import org.socraticgrid.hl7.services.uc.model.DeliveryAddress;
import org.socraticgrid.hl7.services.uc.model.InteractionTypes;
import org.socraticgrid.hl7.services.uc.model.PhysicalAddress;
public class AlertingAdapter implements UCSAdapterIntf
{
	private final Logger logger = LoggerFactory.getLogger(AlertingAdapter.class);

	
	Configuration configuration; 
	
	//Provided to support calling back to the implementing adapter
	AdapterIntf adapter=null;
	
	DispatcherIntf dispatcher=null;
	
	//TODO Extend for aggregate grouping and resolution. 
	//AddressResolverIntf addressResolver;

	
	/**
	 * @return the configuration
	 */
	public Configuration getConfiguration() {
		return configuration;
	}


	/**
	 * @param configuration the configuration to set
	 */
	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}




	/**
	 * @return the dispatcher
	 */
	public DispatcherIntf getDispatcher() {
		return dispatcher;
	}


	/**
	 * @param dispatcher the dispatcher to set
	 */
	public void setDispatcher(DispatcherIntf dispatcher) {
		this.dispatcher = dispatcher;
	}

	
	//TODO Fix for a consolidation contract

	@Override
	public boolean sendMessage(AdapterMessage message, String serverId) {
		logger.debug("In sendMessage");
		List<PhysicalAddress> workList= message.getAddressesList();
		
		
		boolean out = false;
		Iterator<PhysicalAddress> itr = workList.iterator();
		
		while(itr.hasNext())
		{
			PhysicalAddress addr = itr.next();
			boolean dispatchable = dispatcher.sendMessage(addr, message.getMessageType(),serverId);
			if (!dispatchable)
			{
				postDispatchException(addr,message.getMessageType().getHeader().getMessageId());
			}
		}
		return out;
	}


	@Override
	public boolean updateMessage(AdapterMessage newMessage, AdapterMessage oldMessage, String serverId) {
		logger.debug("In updateMessage");
		List<PhysicalAddress> workList= newMessage.getAddressesList();
		boolean out = false;
		Iterator<PhysicalAddress> itr = workList.iterator();
		
		while(itr.hasNext())
		{
			PhysicalAddress addr = itr.next();
			boolean dispatchable = dispatcher.updateMessage(addr, newMessage.getMessageType(),serverId);
			if (!dispatchable)
			{
				postDispatchException(addr,newMessage.getMessageType().getHeader().getMessageId());
			}
		}
		return out;
	}

	@Override
	public boolean cancelMessage(AdapterMessage message, String serverId) {
		logger.debug("In cancelMessage");
		List<PhysicalAddress> workList= message.getAddressesList();
		boolean out = false;
		Iterator<PhysicalAddress> itr = workList.iterator();
		
		while(itr.hasNext())
		{
			PhysicalAddress addr = itr.next();
			boolean dispatchable = dispatcher.cancelMessage(addr, message.getMessageType(),serverId);
			if (!dispatchable)
			{
				postDispatchException(addr,message.getMessageType().getHeader().getMessageId());
			}
		}
		return out;
	}
	
	
	private void postDispatchException(PhysicalAddress addr, String messageId)
	{
		logger.info("Not dispatchable "+addr.toString());
		
		ProcessingException exp = new ProcessingException();
		exp.setIssuingService(configuration.getServiceId());
		exp.setGeneratingMessage(messageId);
		exp.setFault("Not Dispatchable to "+addr.getAddress());
		try {
			adapter.postException(exp,ExceptionType.InvalidAddress,messageId, new DeliveryAddress(addr), configuration.getServiceId());
		} catch (InvalidMessageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidConversationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	@Override
	public String getServiceId() {
		return configuration.getServiceId();
	}

	@Override
	public List<InteractionTypes> getInteractionTypes() {
		List<InteractionTypes> out = new LinkedList<InteractionTypes>();
		out.add(InteractionTypes.alert);
		out.add(InteractionTypes.message);
		out.add(InteractionTypes.notification);
		return out;
	}

	@PostConstruct
	public void init()
	{
		if (dispatcher == null)
		{
			dispatcher = Dispatcher.createDispatcher();
		}
		dispatcher.setConfiguration(configuration);
		dispatcher.setAdapter(adapter);

	}


	/**
	 * @return the adapter
	 */
	public AdapterIntf getAdapter() {
		return adapter;
	}


	/**
	 * @param adapter the adapter to set
	 */
	public void setAdapter(AdapterIntf adapter) {
		this.adapter = adapter;
	}


	
}

