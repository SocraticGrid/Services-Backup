package org.socraticgrid.hl7.services.uc;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

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
import org.springframework.beans.factory.annotation.Autowired;

@WebService(name = "management", targetNamespace = "org.socraticgrid.hl7.services.uc")
public class ManagementService
{
	@Autowired
	private ManagementIntf mgmtSrv;

	/**
	 * 
	 */

	public List<ServiceInfo> discoverChannels()
			throws FeatureNotSupportedException
	{
		return mgmtSrv.discoverChannels();
	}

	public List<EventMetadata> getEventMetaData(
			@WebParam(name = "eventType") EventType eventType,
			@WebParam(name = "eventIds") List<String> eventIds)
			throws InvalidMessageException, InvalidConversationException,
			FeatureNotSupportedException
	{
		return mgmtSrv.getEventMetaData(eventType, eventIds);
	}

	public List<EventMetadata> queryEventMetaData(
			@WebParam(name = "query") String query)
			throws InvalidQueryException, FeatureNotSupportedException
	{
		return mgmtSrv.queryEventMetaData(query);
	}

	public List<Status> getStatus(
			@WebParam(name = "capablityType") String capablityType,
			@WebParam(name = "capablityType") List<String> capabilityIds)
			throws InvalidMessageException, InvalidContentException,
			InvalidConversationException, UnknownServiceException,
			ServiceAdapterFaultException, FeatureNotSupportedException
	{
		return mgmtSrv.getStatus(capablityType, capabilityIds);

	}

	public List<Conversation> getConversations(
			@WebParam(name = "conversationIds") List<String> conversationIds)
			throws InvalidContentException, FeatureNotSupportedException
	{
		return mgmtSrv.getConversations(conversationIds);
	}

	public <T extends Message> List<MessageModel<T>> getMessages(
			@WebParam(name = "messageIds") List<String> messageIds)
			throws InvalidMessageException
	{
		return mgmtSrv.getMessages(messageIds);
	}

	public List<LogEntry> getEvents(
			@WebParam(name = "messageIds") List<String> messageIds)
			throws InvalidMessageException, FeatureNotSupportedException
	{
		return mgmtSrv.getEvents(messageIds);
	}

	public boolean resumeChannel(
			@WebParam(name = "channelIds") List<String> channelIds)
			throws UnknownServiceException, ServiceAdapterFaultException,
			FeatureNotSupportedException
	{
		return mgmtSrv.resumeChannel(channelIds);
	}

	public boolean suspendChannel(
			@WebParam(name = "channelIds") List<String> channelIds)
			throws UnknownServiceException, ServiceAdapterFaultException,
			FeatureNotSupportedException
	{
		return mgmtSrv.suspendChannel(channelIds);
	}

}
