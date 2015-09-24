package org.socraticgrid.hl7.services.ucs.accessclients.client;


import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import org.socraticgrid.hl7.services.uc.model.AlertMessage;
import org.socraticgrid.hl7.services.uc.model.DeliveryAddress;
import org.socraticgrid.hl7.services.uc.model.Message;
import org.socraticgrid.hl7.services.uc.model.MessageModel;
import org.socraticgrid.hl7.services.uc.model.PhysicalAddress;
import org.socraticgrid.hl7.services.uc.model.Recipient;


public final class Client_ClientServiceSEI_Demo {

	private static final QName SERVICE_NAME = new QName(
			"org.socraticgrid.hl7.services.uc",
			"ClientServiceService");

	private Client_ClientServiceSEI_Demo() {
	}

	public static void main(String args[]) throws java.lang.Exception {
		String endpoint = "http://172.31.5.68:8080/UCS/client";
		
		ClientServiceSE ss = new ClientServiceSE();
		
		ClientServiceSEI port = ss.getClientPort();
		((BindingProvider)port).getRequestContext().put(
        	    BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
        	    endpoint);

		//Example Send Message
		{
		
			System.out.println("Invoking send message...");
			
			MessageModel<Message> msg = new MessageModel<>();
			AlertMessage alertTy = new AlertMessage();
			msg.setMessageType(alertTy);
			Recipient recipient = new Recipient();
			DeliveryAddress da = new DeliveryAddress();
			PhysicalAddress pa = new PhysicalAddress();
			pa.setServiceId("Alert");
			pa.setAddress("http://172.31.5.68:8080/UCSAlerting/ucsalerting|userId");
			da.setAddress(pa);
			recipient.setDeliveryAddress(da);
	
			alertTy.getHeader().getRecipientsList().add(recipient);
			

			String result = port.sendMessage(msg);
			
			System.out.println("send Message.result=" + result.toString());

		}
		
		
		System.exit(0);
	}

}
