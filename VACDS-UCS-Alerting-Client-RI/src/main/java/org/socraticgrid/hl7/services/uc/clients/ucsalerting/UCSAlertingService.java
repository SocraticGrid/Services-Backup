package org.socraticgrid.hl7.services.uc.clients.ucsalerting;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import org.socraticgrid.hl7.services.uc.interfaces.UCSAlertingIntf;
import org.socraticgrid.hl7.services.uc.model.Message;
import org.socraticgrid.hl7.services.uc.model.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;
@WebService(name = "ucsalerting", targetNamespace = "org.socraticgrid.hl7.services.uc.clients")

public class UCSAlertingService
{	
	@Autowired
	UCSAlertingIntf alertingSrv;
	public UCSAlertingService()
	{
		
	}


	public <T extends Message> boolean receiveAlertMessage(
			@WebParam(name = "messageModel") MessageModel<T> messageModel, @WebParam(name = "localReceivers") List<String> localReceivers, @WebParam(name = "serverId")String serverId)
	{
	
		return alertingSrv.receiveAlertMessage(messageModel, localReceivers,serverId);
	}


	public <T extends Message> boolean updateAlertMessage(
			@WebParam(name = "messageModel") MessageModel<T> newMessageModel,@WebParam(name = "oldMessageModel")  MessageModel<T> oldMessageModel,@WebParam(name = "localReceivers") List<String> localReceivers,@WebParam(name = "serverId") String serverId)
	{
		return alertingSrv.updateAlertMessage(newMessageModel, oldMessageModel, localReceivers, serverId);	}

	
	public <T extends Message> boolean cancelAlertMessage(
			@WebParam(name = "messageModel") MessageModel<T> messageModel,@WebParam(name = "localReceivers") List<String> localReceivers, @WebParam(name = "serverId")String serverId)
	{
		return alertingSrv.cancelAlertMessage(messageModel, localReceivers,serverId);
	}

}

