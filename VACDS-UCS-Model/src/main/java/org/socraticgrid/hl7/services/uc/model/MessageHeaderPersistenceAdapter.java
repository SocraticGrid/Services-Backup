/**
 * 
 */
package org.socraticgrid.hl7.services.uc.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author steven
 * @created Jan 27, 2014
 * 
 * An adapter to marshall a given MessageHeader type e.g. SimpleMessage, AlertMessage
 * from persistence and instantiate it. 
 */
@SuppressWarnings("unused") // Fields are only used for Persistence retrospection
public class MessageHeaderPersistenceAdapter extends MessageHeader {
	
	private static final long serialVersionUID = 1L;
	private MessageHeader messageHeader;
	public MessageHeader getMessageHeader() {
		return messageHeader;
	}
	
	public MessageHeaderPersistenceAdapter(MessageHeader messageHeader) {
		super(messageHeader.getMessageId());
		super.setCreated(messageHeader.getCreated());
		super.setDeliveryGuarantee(messageHeader.getDeliveryGuarantee());
		super.setDeliveryStatusList(messageHeader.getDeliveryStatusList());
		super.setDynamics(messageHeader.getDynamics());
		super.setLastModified(messageHeader.getLastModified());
		super.setMessageHeaderId(messageHeader.getMessageHeaderId());
		super.setMessageId(messageHeader.getMessageId());
		super.setPriority(messageHeader.getPriority());
		super.setProperties(messageHeader.getProperties());
		super.setReceiptNotification(messageHeader.isReceiptNotification());
		super.setRelatedConversationId(messageHeader.getRelatedConversationId());
		super.setRelatedMessageId(messageHeader.getRelatedMessageId());
		super.setRecipientsList(messageHeader.getRecipientsList());
		super.setRetainFullyInLog(messageHeader.isRetainFullyInLog());
		super.setSender(messageHeader.getSender());
		super.setSubject(messageHeader.getSubject());
		super.setTimeout(messageHeader.getTimeout());
		this.messageHeader = messageHeader;
	}
	
	private MessageHeaderPersistenceAdapter() { super(null); }; //Used only for persistence.

	private MessageType messageType;
	private void setAddressType(MessageType messageType) {
		this.messageType = messageType;
	}
	@Override
	public MessageType getMessageType() {
		if(messageHeader==null){
			return null;
		}
		return messageHeader.getMessageType();
	}

	
	private UserContactInfo replyTo;
	private void setReplyTo(UserContactInfo replyTo) {
		this.replyTo = replyTo;
	}
	private String getReplyToUserContactInfoId(){
		if(messageHeader==null){
			return null;
		}
		switch(messageHeader.getMessageType())
		{
		case SimpleMessage: if(((SimpleMessageHeader)messageHeader).getReplyTo()!=null){
				return ((SimpleMessageHeader)messageHeader).getReplyTo().getUserContactInfoId();
			}
			else{
				return null;
			}
		case Notification: if(((NotificationMessageHeader)messageHeader).getReplyTo()!=null){
				return ((NotificationMessageHeader)messageHeader).getReplyTo().getUserContactInfoId();
			}
			else{
				return null;
			}
		case ConversationRequest: return null;
		case Alert: return null;
	
		default: return null;
		}
	}
	

	
	private Set<Recipient> visibleReceivers = new HashSet<>();
	private void setVisibleReceivers(Set<Recipient> visibleReceivers) {
		this.visibleReceivers = visibleReceivers;
	}
	private Set<Recipient> getVisibleReceivers() {
		if(messageHeader==null){
			return null;
		}
		switch(messageHeader.getMessageType()) {
			case Notification: ((NotificationMessageHeader)messageHeader).getVisableReceivers();
			default: return null;
		}
	}

	
	
	private boolean replyAllowed;
	private void setReplyAllowed(boolean b) {
		this.replyAllowed = b;
	}
	private boolean getReplyAllowed() {
		if(messageHeader==null){
			return false;
		}
		switch(messageHeader.getMessageType())
		{
			case Notification: return ((NotificationMessageHeader)messageHeader).isReplyAllowed();
	
			default: return false;
		}
	}


	
	private Conversation conversation;
	private void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}
	private String getConversationId(){
		if(messageHeader==null){
			return null;
		}
		switch(messageHeader.getMessageType())
		{
		case ConversationRequest: if( ((ConversationRequestMessageHeader)messageHeader).getConversation()!=null ){
				return ((ConversationRequestMessageHeader)messageHeader).getConversation().getConversationId();
			}
			else{
				return null;
			}
	
		default: return null;
		}
	}
	private void setConversationId(String id) {
		//Do nothing
	}
	
	
	private Date requestExpires;
	private void setRequestExpires(Date requestExpires) {
		this.requestExpires = requestExpires;
	}
	private Date getRequestExpires() {
		if(messageHeader==null){
			return null;
		}
		switch(messageHeader.getMessageType())
		{
			case ConversationRequest: return ((ConversationRequestMessageHeader)messageHeader).getRequestExpires();
	
			default: return null;
		}
	}

	
	private AlertStatus alertStatus;
	private void setAlertStatus(AlertStatus alertStatus) {
		this.alertStatus = alertStatus;
	}
	private AlertStatus getAlertStatus(){
		if(messageHeader==null){
			return null;
		}
		switch(messageHeader.getMessageType())
		{
		case Alert: if( ((AlertMessageHeader)messageHeader).getAlertStatus()!=null ){
				return ((AlertMessageHeader)messageHeader).getAlertStatus();
			}
			else{
				return null;
			}
	
		default: return null;
		}
	}
	
	private String serviceResponseAddress;
	private void setServiceResponseAddress(String serviceResponseAddress) {
		this.serviceResponseAddress = serviceResponseAddress;
	}
	private String getServiceResponseAddress(){
		if(messageHeader==null){
			return null;
		}
		switch(messageHeader.getMessageType())
		{
		case Alert: return ((AlertMessageHeader)messageHeader).getServiceResponseAddress();
	
		default: return null;
		}
	}
	
	private Set<StatusByReceiverDelegate> statusByReceiverDelegate = new HashSet<>();
	private void setStatusByReceiverDelegate(Set<StatusByReceiverDelegate> statusByReceiverDelegate) {
		this.statusByReceiverDelegate = statusByReceiverDelegate;
	}
	private Set<StatusByReceiverDelegate> getStatusByReceiverDelegate(){
		return statusByReceiverDelegate;
	}
	

	@SuppressWarnings("unchecked")
	public <T extends MessageHeader> T buildMessageHeader() {
		T built;
		switch(messageType){
		case SimpleMessage: SimpleMessageHeader simpleMsgHdr = new SimpleMessageHeader(super.getMessageId());
			simpleMsgHdr.setReplyTo(replyTo);
			built = (T) simpleMsgHdr;
			break;

		case Notification: NotificationMessageHeader notificationHdr = new NotificationMessageHeader(super.getMessageId());
			notificationHdr.setReplyTo(replyTo);
			notificationHdr.setReplyAllowed(replyAllowed);
			notificationHdr.setVisableReceivers(visibleReceivers);
			built = (T) notificationHdr;
			break;

		case ConversationRequest: ConversationRequestMessageHeader conversationReqHdr = new ConversationRequestMessageHeader(super.getMessageId());
			conversationReqHdr.setConversation(conversation);
			conversationReqHdr.setRequestExpires(requestExpires);
			built = (T) conversationReqHdr;
			break;

		case Alert: AlertMessageHeader alertHdr = new AlertMessageHeader(super.getMessageId());
			alertHdr.setAlertStatus(alertStatus);
			alertHdr.setServiceResponseAddress(serviceResponseAddress);
			for(StatusByReceiverDelegate delegate : statusByReceiverDelegate){
				alertHdr.getStatusByReciever().put(delegate.getDeliveryAddress(),delegate.getAlertStatus());
			}
			built = (T) alertHdr;
			break;


		default:
			return null;
		}
		built.setCreated(super.getCreated());
		built.setDeliveryGuarantee(super.getDeliveryGuarantee());
		built.setDeliveryStatusList(super.getDeliveryStatusList());
		built.setDynamics(super.getDynamics());
		built.setLastModified(super.getLastModified());
		built.setMessageHeaderId(super.getMessageHeaderId());
		built.setPriority(super.getPriority());
		built.setProperties(super.getProperties());
		built.setReceiptNotification(super.isReceiptNotification());
		built.setRelatedConversationId(super.getRelatedConversationId());
		built.setRelatedMessageId(super.getRelatedMessageId());
		built.setRecipientsList(super.getRecipientsList());
		built.setRetainFullyInLog(super.isRetainFullyInLog());
		built.setSender(super.getSender());
		built.setSubject(super.getSubject());
		built.setTimeout(super.getTimeout());
		
		return built;
	}


	
}
