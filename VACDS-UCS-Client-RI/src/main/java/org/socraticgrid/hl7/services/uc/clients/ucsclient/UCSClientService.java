package org.socraticgrid.hl7.services.uc.clients.ucsclient;

import javax.jws.WebParam;
import javax.jws.WebService;

import org.socraticgrid.hl7.services.uc.exceptions.BadBodyException;
import org.socraticgrid.hl7.services.uc.exceptions.FeatureNotSupportedException;
import org.socraticgrid.hl7.services.uc.exceptions.InvalidContentException;
import org.socraticgrid.hl7.services.uc.exceptions.InvalidMessageException;
import org.socraticgrid.hl7.services.uc.exceptions.MissingBodyTypeException;
import org.socraticgrid.hl7.services.uc.exceptions.ProcessingException;
import org.socraticgrid.hl7.services.uc.exceptions.ServiceAdapterFaultException;
import org.socraticgrid.hl7.services.uc.exceptions.UndeliverableMessageException;
import org.socraticgrid.hl7.services.uc.interfaces.UCSClientIntf;
import org.socraticgrid.hl7.services.uc.model.Conversation;
import org.socraticgrid.hl7.services.uc.model.DeliveryAddress;
import org.socraticgrid.hl7.services.uc.model.Message;
import org.socraticgrid.hl7.services.uc.model.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;
@WebService(name = "ucsclient", targetNamespace = "org.socraticgrid.hl7.services.uc.clients")
public class UCSClientService
{
	@Autowired
	UCSClientIntf clientImpl;
	
	public UCSClientService()
	{
		// TODO Auto-generated constructor stub
	}


	public boolean callReady(@WebParam(name = "conversation") Conversation conversation,@WebParam(name = "callHandle")  String callHandle,@WebParam(name = "serverId")  String serverId )
	{
		return clientImpl.callReady(conversation, callHandle, serverId);
	}

	public <T extends Message> boolean handleException(@WebParam(name = "message") 
			MessageModel<T> messageModel,@WebParam(name = "sender")  DeliveryAddress sender, @WebParam(name = "receiver") DeliveryAddress receiver, @WebParam(name = "exception") ProcessingException exp,@WebParam(name = "serverId")  String serverId )
	{
		System.out.println("\n*****************\n\t MessageModel = "+messageModel+"\n**************");
		assert (messageModel != null);
		assert (sender != null);
		assert (receiver != null);
		assert (exp != null);
		assert (serverId != null);
		return true;
//		return clientImpl.handleException(messageModel, sender, receiver, exp, serverId);
	}

	public <T extends Message> boolean handleNotification(
			@WebParam(name = "message") MessageModel<T> messageModel,@WebParam(name = "serverId")  String serverId )
	{
		return clientImpl.handleNotification(messageModel, serverId);
	}

	public <T extends Message> MessageModel<T> handleResponse(@WebParam(name = "message") 
			MessageModel<T> messageModel,@WebParam(name = "serverId")  String serverId ) throws InvalidMessageException,
			InvalidContentException, MissingBodyTypeException,
			BadBodyException, ServiceAdapterFaultException,
			UndeliverableMessageException, FeatureNotSupportedException
	{
		return clientImpl.handleResponse(messageModel, serverId);
	}

	public <T extends Message> boolean receiveMessage(
			@WebParam(name = "message")  MessageModel<T> messageModel,@WebParam(name = "serverId")  String serverId )
	{
		return clientImpl.receiveMessage(messageModel, serverId);
	}

}
