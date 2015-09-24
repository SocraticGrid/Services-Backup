package org.socraticgrid.hl7.services.ucs.accessclients.ucsadapter;


import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import org.socraticgrid.hl7.services.uc.model.AdapterMessage;
import org.socraticgrid.hl7.services.uc.model.AlertMessage;
import org.socraticgrid.hl7.services.uc.model.DeliveryAddress;
import org.socraticgrid.hl7.services.uc.model.MessageBody;
import org.socraticgrid.hl7.services.uc.model.MessageModel;
import org.socraticgrid.hl7.services.uc.model.PhysicalAddress;
import org.socraticgrid.hl7.services.uc.model.Recipient;
import org.socraticgrid.hl7.services.uc.model.SimpleMessage;


public final class UcsAdapter_UcsAdapterSEI_Demo {


	private UcsAdapter_UcsAdapterSEI_Demo() {
	}

	public static void main(String args[]) throws java.lang.Exception {
		

		String endpoint = "http://172.31.5.68:8080/UCSAdapter/ucsadapter";
		
		UCSAdapterServiceSE ss = new UCSAdapterServiceSE();
		
		UCSAdapterServiceSEI port = ss.getUCSAdapterPort();
		((BindingProvider)port).getRequestContext().put(
        	    BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
        	    endpoint);

		//Example getServiceId Message
		{
			System.out.println("Invoking getServiceId...");
			
	
			String out = port.getServiceId();
			System.out.println("getServiceId.result=" + out);

		}
		//Example sendMessage Message
		{
			System.out.println("Invoking sendMessage...");
			
			List<PhysicalAddress> addressList = new ArrayList<PhysicalAddress>();
			PhysicalAddress addr = new PhysicalAddress();
			addr.setServiceId("Test");
			addr.setAddress("someaphysicaladdress");
			addr.setAddressId("A Label");
			addressList.add(addr);
			
			Recipient rcp = new Recipient();
			DeliveryAddress da = new DeliveryAddress();
			da.setAddress(addr);
			rcp.setDeliveryAddress(da);
			
			SimpleMessage msg = new SimpleMessage();
			msg.getHeader().setMessageId("MsgId");
			msg.getHeader().getRecipientsList().add(rcp);
			msg.getHeader().setSubject("A Subject message");
			msg.getHeader().setSender(da);
			
			
			MessageBody bdy = new MessageBody();
			bdy.setType("text");
			bdy.setContent("Content");
			
			MessageBody[] parts = new MessageBody[1];
			parts[0] = bdy;
			
			msg.setParts(parts);
			AdapterMessage adaptMsg = new AdapterMessage(msg,addressList);
			
	
			boolean out = port.sendMessage(adaptMsg, "TestClientId");
					
			System.out.println("sendMessage.result="
					+ out);

		}

	

		System.exit(0);
	}

}
