package org.socraticgrid.hl7.services.uc.processes;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.xml.ws.BindingProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.socraticgrid.hl7.services.uc.exceptions.ExceptionType;
import org.socraticgrid.hl7.services.uc.exceptions.ProcessingException;
import org.socraticgrid.hl7.services.uc.functional.AddressResolver;
import org.socraticgrid.hl7.services.uc.internal.interfaces.ClientRegistryIntf;
import org.socraticgrid.hl7.services.uc.model.DeliveryAddress;
import org.socraticgrid.hl7.services.uc.model.Message;
import org.socraticgrid.hl7.services.uc.model.MessageModel;
import org.socraticgrid.hl7.services.uc.model.PhysicalAddress;
import org.socraticgrid.hl7.services.ucs.accessclients.ucsclient.UCSClientServiceSE;
import org.socraticgrid.hl7.services.ucs.accessclients.ucsclient.UCSClientServiceSEI;

public class NotifyUCSClientOfException {

	private Logger log = LoggerFactory.getLogger(NotifyUCSClientOfException.class);

	private ClientRegistryIntf clientReg;
	private ProcessingException exception;
	private ExceptionType type;
	private String messageId;
	private Message msg;
	private String serviceId;
	private AddressResolver resolver;
	private DeliveryAddress receiver;

	public NotifyUCSClientOfException(ClientRegistryIntf clientReg,ProcessingException exception, ExceptionType type,
			String messageId, Message msg, String serviceId,AddressResolver resolver, DeliveryAddress receiver)
	{
		this.clientReg=clientReg;
		this.exception=exception;
		this.type=type;
		this.messageId=messageId;
		this.msg=msg;
		this.serviceId=serviceId;
		this.resolver=resolver;
		this.receiver=receiver;
	}

	public void run()
	{
		//Find the sender
		//Get the sender service name
		String serviceName=null;
		DeliveryAddress sender = msg.getHeader().getSender();
		log.debug("\n*********\n\t NotifyUCSClientOfException for sender "+sender+"\n********\n");
		List<PhysicalAddress> addrs = new LinkedList<PhysicalAddress>();

		resolver.simpleResolveAddress(sender, addrs, null);
		Iterator<PhysicalAddress> itr = addrs.iterator();

		HashSet<String> endpoints = new HashSet<String>();
		//Get the sender service name
		while(itr.hasNext())
		{
			String endpoint;
			PhysicalAddress addr = itr.next();
			serviceName=addr.getServiceId();
			endpoint = clientReg.getClientEndpoint(serviceName);
			if (endpoint != null)
			{
				log.debug("\n*********\n\t NotifyUCSClientOfException serviceName : endpoint "+serviceName+" : "+endpoint+"\n********\n");
				endpoints.add(endpoint);
			}
		}

		Iterator<String> epi = endpoints.iterator();

		while (epi.hasNext())
		{
			String endpoint = epi.next();

			UCSClientServiceSE ss = new UCSClientServiceSE();

			UCSClientServiceSEI port = ss.getUcsclientPort();
			((BindingProvider)port).getRequestContext().put(
					BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
					endpoint);

			MessageModel<Message> msgMdl = new MessageModel<Message>(msg);
			//Call the endpoint

			log.debug("*********\n\t NotifyUCSClientOfException at "+endpoint+"\n*********");
			port.handleException(msgMdl, msg.getHeader().getSender(), receiver, exception, serviceId);
		}


	}
}