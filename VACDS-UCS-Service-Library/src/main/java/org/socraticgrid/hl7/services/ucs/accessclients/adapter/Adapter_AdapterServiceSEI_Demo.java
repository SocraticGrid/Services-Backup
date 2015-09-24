package org.socraticgrid.hl7.services.ucs.accessclients.adapter;


import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import org.socraticgrid.hl7.services.uc.exceptions.ExceptionType;
import org.socraticgrid.hl7.services.uc.exceptions.ProcessingException;
import org.socraticgrid.hl7.services.uc.model.AlertMessage;
import org.socraticgrid.hl7.services.uc.model.AlertMessageHeader;
import org.socraticgrid.hl7.services.uc.model.AlertStatus;
import org.socraticgrid.hl7.services.uc.model.DeliveryAddress;
import org.socraticgrid.hl7.services.uc.model.MessageBody;
import org.socraticgrid.hl7.services.uc.model.PhysicalAddress;


public final class Adapter_AdapterServiceSEI_Demo {

	private static final QName SERVICE_NAME = new QName(
			"org.socraticgrid.hl7.services.eps",
			"PublicationServiceService");

	private Adapter_AdapterServiceSEI_Demo() {
	}

	public static void main(String args[]) throws java.lang.Exception {
	

		String endpoint = "http://172.31.5.68:8080/UCS/adapter";
		
		AdapterServiceSE ss = new AdapterServiceSE();
		
		AdapterServiceSEI port = ss.getAdapterPort();
		((BindingProvider)port).getRequestContext().put(
        	    BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
        	    endpoint);
		String msgId;
		//Example Publish Event Message
		{
		
			System.out.println("Invoking postException...");
			String messageId = "FakeMessageId";
			String adapterIdentity="Example";
			ProcessingException exception = new ProcessingException();
			DeliveryAddress receiver = new DeliveryAddress();
			receiver.setAddress(new PhysicalAddress("SomeService","SomeAddress"));
			
			boolean result = port.postException(exception, ExceptionType.UndeliverableMessage, messageId, receiver, adapterIdentity);
			System.out.println("postException.result="+result);

		}
		
		System.exit(0);
	}
	
	private static AlertMessage getBaseAlertMessage()
	{
		AlertMessage alert = new AlertMessage();
		AlertMessageHeader hdr = alert.getHeader();
		hdr.setAlertStatus(AlertStatus.New);
		hdr.setMessageId("A message id");
		MessageBody[] parts = new MessageBody[1];
		
		MessageBody body = new MessageBody();
		body.setContent("text");
		body.setContent("Test Data");
	
		parts[0] = body;
		
		alert.setParts(parts);
		return alert;
	}
}
