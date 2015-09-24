package org.socraticgrid.hl7.services.eps.accessclients.pssubscriber;


import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import org.socraticgrid.hl7.services.eps.interfaces.PSSubscriptionClientIFace;
import org.socraticgrid.hl7.services.eps.model.ManagementEvent;
import org.socraticgrid.hl7.services.eps.model.Message;
import org.socraticgrid.hl7.services.eps.model.MessageBody;


public final class PushSubscription_PSSubscriptionClientServiceSEI_Demo {

	private static final QName SERVICE_NAME = new QName(
			"org.socraticgrid.hl7.services.eps.clients",
			"PSSubscriptionClientServiceService");

	private PushSubscription_PSSubscriptionClientServiceSEI_Demo() {
	}

	public static void main(String args[]) throws java.lang.Exception {
		URL wsdlURL = PSSubscriptionClientServiceSE.WSDL_LOCATION;
		if (args.length > 0 && args[0] != null && !"".equals(args[0])) {
			File wsdlFile = new File(args[0]);
			try {
				if (wsdlFile.exists()) {
					wsdlURL = wsdlFile.toURI().toURL();
				} else {
					wsdlURL = new URL(args[0]);
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}

		String endpoint = "http://172.31.5.68:8080/EPSSubscriber/pssubscriptionclient";
		
		PSSubscriptionClientServiceSE ss = new PSSubscriptionClientServiceSE(wsdlURL,
				SERVICE_NAME);
		
		PSSubscriptionClientIFace port = ss.getPSSubscriptionClientPort();
		((BindingProvider)port).getRequestContext().put(
        	    BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
        	    endpoint);
		
		//Example topic Event Message
		{
		
			System.out.println("Invoking topicEvent...");
			String topic = "test Topic";
			Message event = new Message();
			event.getHeader().setMessageId(Long.toString(System.currentTimeMillis()));
			event.getTopics().add(topic);
			MessageBody body = new MessageBody();
			body.setType("txt/test");
			body.setBody("A Test Body");
			event.getMessageBodies().add(body);
			boolean bOk = port.topicEvent(topic, event);
			
			System.out.println("topicEvent.result=" + Boolean.toString(bOk));

		}
		//Example topic Event Message
		{
		
			System.out.println("Invoking topicManagementEvent...");
			String topic = "test Topic";
			ManagementEvent event = new ManagementEvent();
			event.setTitle("Title");
			event.setEventType(666);
			event.getTopics().add(topic);
			boolean bOk = port.topicManagementEvent(topic, event);
			
			System.out.println("topicManagementEvent.result=" + Boolean.toString(bOk));

		}
		/*
		//Example retrieveResultByResultId Message
		{
			System.out.println("Invoking retrieveResultByResultId...");
			Identifier resultId = new Identifier();
			resultId.setValue("MyResult");
			
			Result out = port.retrieveResultByResultId(resultId);
			
			System.out.println("retrieveResultByResultId.result="
					+ out);
		}
		//Example retrieveResultByOrderId Message
		{
			System.out.println("Invoking retrieveResultByResultId...");
			Identifier resultId = new Identifier();
			resultId.setValue("MyResult");
			
			Result out = port.retrieveResultByResultId(resultId);
			
			System.out.println("retrieveResultByResultId.result="
					+ out);
		}
		//Example retrieveResultByOrderId Message
		{
			System.out.println("Invoking retrieveResultByResultIdretrieveResultByResultId...");
			Identifier resultId = new Identifier();
			resultId.setValue("MyResult");
			
			List<Result> out = port.retrieveResultByOrderId(resultId);
			
			System.out.println("retrieveResultByOrderId.result="
					+ out);
		}
*/
		
		//public CancellationStatus cancelOrder(Identifier orderId);

		//public <T extends Order> OrderModel<T> getProposedOrderReplacement(
		//		Identifier orderId);

		//public void getResultAgumentatons(Identifier resultId) ;
		
		System.exit(0);
	}

}
