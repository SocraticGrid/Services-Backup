package org.socraticgrid.hl7.services.eps.accessclients.broker;


import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import org.socraticgrid.hl7.services.eps.interfaces.BrokerIFace;
import org.socraticgrid.hl7.services.eps.model.Capabilities;


public final class Broker_BrokerServiceSEI_Demo {

	private static final QName SERVICE_NAME = new QName(
			"org.socraticgrid.hl7.services.eps",
			"BrokerServiceService");

	private Broker_BrokerServiceSEI_Demo() {
	}

	public static void main(String args[]) throws java.lang.Exception {
		URL wsdlURL = BrokerServiceSE.WSDL_LOCATION;
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

		String endpoint = "http://172.31.5.68:8080/EPSService/broker";
		
		BrokerServiceSE ss = new BrokerServiceSE(wsdlURL,
				SERVICE_NAME);
		
		BrokerIFace port = ss.getBrokerPort();
		((BindingProvider)port).getRequestContext().put(
        	    BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
        	    endpoint);

		//Example Publish Event Message
		{
		
			System.out.println("Invoking discoverCapabilities...");

			Capabilities result  = port.discoverCapabilities();
			
			System.out.println("discoverCapabilities.result=" + result.toString());

		}
		
		
		System.exit(0);
	}

}
