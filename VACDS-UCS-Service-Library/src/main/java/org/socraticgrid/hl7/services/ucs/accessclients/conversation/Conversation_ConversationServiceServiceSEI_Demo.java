package org.socraticgrid.hl7.services.ucs.accessclients.conversation;


import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import org.socraticgrid.hl7.services.uc.model.Conversation;
import org.socraticgrid.hl7.services.uc.model.PhysicalAddress;
import org.socraticgrid.hl7.services.uc.model.UserContactInfo;


//TODO  Add more demos....

public final class Conversation_ConversationServiceServiceSEI_Demo {


	private Conversation_ConversationServiceServiceSEI_Demo() {
	}

	public static void main(String args[]) throws java.lang.Exception {
		
		String endpoint = "http://172.31.5.68:8080/UCS/conversation";
		
		ConversationServiceSE ss = new ConversationServiceSE();
		
		ConversationServiceSEI port = ss.getConversationPort();
		((BindingProvider)port).getRequestContext().put(
        	    BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
        	    endpoint);

		//Example createAffiliation Message
		{
		
			System.out.println("Invoking createConversation...");
			Conversation conversation= new Conversation();
			UserContactInfo owner = new UserContactInfo();
			owner.setName("Test User");
			PhysicalAddress pa = new PhysicalAddress();
			pa.setAddress("joe@gmail.com");
			pa.setServiceId("mail");
			owner.setPreferredAddress(pa);
			conversation.setOwner(owner);
			String result = port.createConversation(conversation);
			System.out.println("createConversation.result="+result);

		}

		
		System.exit(0);
	}

}
