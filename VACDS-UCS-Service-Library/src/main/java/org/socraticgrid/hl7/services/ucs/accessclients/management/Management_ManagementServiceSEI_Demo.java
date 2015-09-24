package org.socraticgrid.hl7.services.ucs.accessclients.management;


import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import org.socraticgrid.hl7.services.uc.model.Status;


public final class Management_ManagementServiceSEI_Demo {

	private Management_ManagementServiceSEI_Demo() {
	}

	public static void main(String args[]) throws java.lang.Exception {
	
		String endpoint = "http://172.31.5.68:8080/UCS/management";
		
		ManagementServiceSE ss = new ManagementServiceSE();
		
		ManagementServiceSEI port = ss.getManagementPort();
		((BindingProvider)port).getRequestContext().put(
        	    BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
        	    endpoint);
		
		//Example Publish Event Message
		{
		
			System.out.println("Invoking getStatus...");
			String capablityType="";
			LinkedList<String> capabilityIds = new LinkedList<String>();
			
			List<Status> result = port.getStatus(capablityType, capabilityIds);
			System.out.println("getStatus.result=" + "result");

		}
	
		System.exit(0);
	}

}
