package org.socraticgrid.hl7.services.uc.service.impl;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.socraticgrid.hl7.services.uc.exceptions.FeatureNotSupportedException;
import org.socraticgrid.hl7.services.uc.exceptions.InvalidContentException;
import org.socraticgrid.hl7.services.uc.exceptions.InvalidConversationException;
import org.socraticgrid.hl7.services.uc.exceptions.InvalidMessageException;
import org.socraticgrid.hl7.services.uc.exceptions.InvalidQueryException;
import org.socraticgrid.hl7.services.uc.exceptions.ServiceAdapterFaultException;
import org.socraticgrid.hl7.services.uc.exceptions.UnknownServiceException;
import org.socraticgrid.hl7.services.uc.interfaces.ManagementIntf;
import org.socraticgrid.hl7.services.uc.logging.LogEntry;
import org.socraticgrid.hl7.services.uc.model.Conversation;
import org.socraticgrid.hl7.services.uc.model.EventMetadata;
import org.socraticgrid.hl7.services.uc.model.EventType;
import org.socraticgrid.hl7.services.uc.model.Message;
import org.socraticgrid.hl7.services.uc.model.MessageModel;
import org.socraticgrid.hl7.services.uc.model.ServiceInfo;
import org.socraticgrid.hl7.services.uc.model.Status;

public class ManagementServiceImpl implements ManagementIntf
{

	public ManagementServiceImpl()
	{
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<ServiceInfo> discoverChannels()
			throws FeatureNotSupportedException
	{
		throw new FeatureNotSupportedException();		
	}

	@Override
	public List<EventMetadata> getEventMetaData(EventType eventType,
			List<String> eventIds) throws InvalidMessageException,
			InvalidConversationException, FeatureNotSupportedException
	{
		throw new FeatureNotSupportedException();		
	}

	@Override
	public List<EventMetadata> queryEventMetaData(String query)
			throws InvalidQueryException, FeatureNotSupportedException
	{
		throw new FeatureNotSupportedException();		
	}

	@Override
	public List<Status> getStatus(String capablityType, List<String> capabilityIds)
			throws InvalidMessageException, InvalidContentException,
			InvalidConversationException, UnknownServiceException,
			ServiceAdapterFaultException, FeatureNotSupportedException
	{
		
		List<Status> out = new LinkedList<Status>();
		// Loop the capabilities 
		Iterator<String> itr = capabilityIds.iterator();
		while(itr.hasNext())
		{
			String capability = itr.next();
			Status stat = new Status();
			stat.setCapability(capability);
			stat.setSupported(false);
			stat.setAvailable(false);
			stat.setStatusText("Unknown state");
		}
		//return out;
		
		throw new FeatureNotSupportedException();		
	}

	@Override
	public List<Conversation> getConversations(List<String> conversationIds)
			throws InvalidContentException, FeatureNotSupportedException
	{
		throw new FeatureNotSupportedException();		
	}

	@Override
	public <T extends Message> List<MessageModel<T>> getMessages(
			List<String> messageIds) throws InvalidMessageException
	{
		List<MessageModel<T>> out = new LinkedList<MessageModel<T>>();
		return out;
	}

	@Override
	public List<LogEntry> getEvents(List<String> messageIds)
			throws InvalidMessageException, FeatureNotSupportedException
	{
		throw new FeatureNotSupportedException();		
	}

	@Override
	public boolean resumeChannel(List<String> channelIds)
			throws UnknownServiceException, ServiceAdapterFaultException,
			FeatureNotSupportedException
	{
		throw new FeatureNotSupportedException();		
	}

	@Override
	public boolean suspendChannel(List<String> channelIds)
			throws UnknownServiceException, ServiceAdapterFaultException,
			FeatureNotSupportedException
	{
		throw new FeatureNotSupportedException();		
	}

}
