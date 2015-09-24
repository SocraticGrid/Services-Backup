package org.socraticgrid.hl7.services.ucs.accessclients.management;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.socraticgrid.hl7.services.uc.exceptions.FeatureNotSupportedException;
import org.socraticgrid.hl7.services.uc.exceptions.InvalidContentException;
import org.socraticgrid.hl7.services.uc.exceptions.InvalidConversationException;
import org.socraticgrid.hl7.services.uc.exceptions.InvalidMessageException;
import org.socraticgrid.hl7.services.uc.exceptions.InvalidQueryException;
import org.socraticgrid.hl7.services.uc.exceptions.ServiceAdapterFaultException;
import org.socraticgrid.hl7.services.uc.exceptions.UnknownServiceException;
import org.socraticgrid.hl7.services.uc.logging.LogEntry;
import org.socraticgrid.hl7.services.uc.model.Conversation;
import org.socraticgrid.hl7.services.uc.model.EventMetadata;
import org.socraticgrid.hl7.services.uc.model.EventType;
import org.socraticgrid.hl7.services.uc.model.Message;
import org.socraticgrid.hl7.services.uc.model.MessageModel;
import org.socraticgrid.hl7.services.uc.model.ServiceInfo;
import org.socraticgrid.hl7.services.uc.model.Status;

@WebService(targetNamespace = "org.socraticgrid.hl7.services.us", name = "ManagementServiceService")
public interface ManagementServiceSEI {

	@WebMethod
	public List<ServiceInfo> discoverChannels()
			throws FeatureNotSupportedException;

	@WebMethod
	public List<EventMetadata> getEventMetaData(EventType eventType,
			List<String> eventIds) throws InvalidMessageException,
			InvalidConversationException, FeatureNotSupportedException;

	@WebMethod
	public List<EventMetadata> queryEventMetaData(String query)
			throws InvalidQueryException, FeatureNotSupportedException;

	@WebMethod
	public List<Status> getStatus(String capablityType,
			List<String> capabilityIds) throws InvalidMessageException,
			InvalidContentException, InvalidConversationException,
			UnknownServiceException, ServiceAdapterFaultException,
			FeatureNotSupportedException;

	@WebMethod
	public List<Conversation> getConversations(List<String> conversationIds)
			throws InvalidContentException, FeatureNotSupportedException;

	@WebMethod
	public <T extends Message> List<MessageModel<T>> getMessages(
			List<String> messageIds) throws InvalidMessageException;

	@WebMethod
	public List<LogEntry> getEvents(List<String> messageIds)
			throws InvalidMessageException, FeatureNotSupportedException;

	@WebMethod
	public boolean resumeChannel(List<String> channelIds)
			throws UnknownServiceException, ServiceAdapterFaultException,
			FeatureNotSupportedException;

	@WebMethod
	public boolean suspendChannel(List<String> channelIds)
			throws UnknownServiceException, ServiceAdapterFaultException,
			FeatureNotSupportedException;

	// @WebMethod

}
