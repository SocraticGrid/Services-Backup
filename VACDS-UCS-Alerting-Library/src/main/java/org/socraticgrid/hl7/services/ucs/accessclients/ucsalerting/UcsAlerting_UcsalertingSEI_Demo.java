package org.socraticgrid.hl7.services.ucs.accessclients.ucsalerting;


import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import org.socraticgrid.hl7.services.uc.model.AlertMessage;
import org.socraticgrid.hl7.services.uc.model.MessageModel;


public final class UcsAlerting_UcsalertingSEI_Demo {

	private static final QName SERVICE_NAME = new QName(
			"org.socraticgrid.hl7.services.uc.clients",
			"UCSAlertingServiceService");

	private UcsAlerting_UcsalertingSEI_Demo() {
	}

	public static void main(String args[]) throws java.lang.Exception {
		URL wsdlURL = UCSAlertingServiceSE.WSDL_LOCATION;
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

		String endpoint = "http://172.31.5.68:8080/UCSAlerting/ucsalerting";
		
		UCSAlertingServiceSE ss = new UCSAlertingServiceSE(wsdlURL,
				SERVICE_NAME);
		
		UCSAlertingServiceSEI port = ss.getUcsAlertingPort();
		((BindingProvider)port).getRequestContext().put(
        	    BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
        	    endpoint);

		//Example Alert Receive Message
		{
			System.out.println("Invoking receiveAlertMessage...");
			MessageModel<AlertMessage> alert = new MessageModel<AlertMessage>();
			LinkedList<String> localRecievers = new LinkedList<String>();
			localRecievers.add("AnId");
			String serverId="TestServer";	
			boolean out = port.receiveAlertMessage(alert,localRecievers,serverId);
			System.out.println("receiveAlertMessage.result=" + out);

		}
		//Example Update Alert Message
		{
			System.out.println("Invoking updateAlertMessage...");
			MessageModel<AlertMessage> msg = new MessageModel<AlertMessage>();
			MessageModel<AlertMessage> newMsg = new MessageModel<AlertMessage>();
			LinkedList<String> localRecievers = new LinkedList<String>();
			localRecievers.add("AnId");
			String serverId="TestServer";
			boolean out = port
					.updateAlertMessage(newMsg,msg,localRecievers,serverId);
			System.out.println("updateAlertMessage.result="
					+ out);

		}
		//Example Cancel an Alert Messagte
		{
			
			LinkedList<String> localRecievers = new LinkedList<String>();
			localRecievers.add("AnId");
			String serverId="TestServer";
			System.out.println("Invoking cancelAlertMessage...");
			MessageModel<AlertMessage> msg = new MessageModel<AlertMessage>();
			String userId = "User";
			boolean out = port.cancelAlertMessage(msg,localRecievers,serverId);
			System.out.println("cancelAlertMessage.result="
					+ out);

		}
	

		System.exit(0);
	}

}
