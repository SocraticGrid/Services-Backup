package org.socraticgrid.hl7.services.uc.clients.ucsadapter;

import java.util.List;

import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.socraticgrid.hl7.services.uc.interfaces.UCSAdapterIntf;
import org.socraticgrid.hl7.services.uc.model.AdapterMessage;
import org.socraticgrid.hl7.services.uc.model.AlertMessage;
import org.socraticgrid.hl7.services.uc.model.ConversationRequestMessage;
import org.socraticgrid.hl7.services.uc.model.InteractionTypes;
import org.socraticgrid.hl7.services.uc.model.NotificationMessage;
import org.socraticgrid.hl7.services.uc.model.SimpleMessage;
import org.springframework.beans.factory.annotation.Autowired;

@WebService(name = "ucsadapter", targetNamespace = "org.socraticgrid.hl7.services.uc.clients")
@XmlSeeAlso({SimpleMessage.class,AlertMessage.class,NotificationMessage.class,ConversationRequestMessage.class}) 
public class UCSAdapterService
{
	@Autowired
	UCSAdapterIntf adapterSrv;
	
	public UCSAdapterService()
	{
		// TODO Auto-generated constructor stub
	}

	public boolean sendMessage(AdapterMessage message, String serverId)
	{
		return adapterSrv.sendMessage(message, serverId);
	}


	public boolean updateMessage(AdapterMessage message, AdapterMessage oldMessage,  String serverId)
	{
		return adapterSrv.updateMessage(message, oldMessage, serverId);
	}


	public boolean cancelMessage(AdapterMessage message, String serverId)
	{
		return adapterSrv.cancelMessage(message,serverId);
	}


	public String getServiceId()
	{
		return adapterSrv.getServiceId();
	}


	public List<InteractionTypes> getInteractionTypes()
	{
		return adapterSrv.getInteractionTypes();
	}

}
