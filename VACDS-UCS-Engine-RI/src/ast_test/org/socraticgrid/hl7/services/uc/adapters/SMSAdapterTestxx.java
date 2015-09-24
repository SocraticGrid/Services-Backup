package org.socraticgrid.hl7.services.uc.adapters;

import static org.junit.Assert.*;

import java.util.List;
import java.util.UUID;

import javax.xml.ws.Endpoint;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.socraticgrid.hl7.services.uc.adapters.proxies.SOAPProxyAdapter;
import org.socraticgrid.hl7.services.uc.adapters.service.SMSAdapterServiceImpl;
import org.socraticgrid.hl7.services.uc.functional.ServiceRegistry;
import org.socraticgrid.hl7.services.uc.interfaces.UCSAdapterIntf;
import org.socraticgrid.hl7.services.uc.model.AdapterMessage;
import org.socraticgrid.hl7.services.uc.model.DeliveryAddress;
import org.socraticgrid.hl7.services.uc.model.InteractionTypes;
import org.socraticgrid.hl7.services.uc.model.MessageBody;
import org.socraticgrid.hl7.services.uc.model.PhysicalAddress;
import org.socraticgrid.hl7.services.uc.model.Recipient;
import org.socraticgrid.hl7.services.uc.model.SimpleMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * This test is not meant to be run by Jenkins
 * @author steven
 * Jul 20, 2014
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:Test-IncludedResources.xml" })
public class SMSAdapterTestxx {
	
	private static Logger log = LoggerFactory.getLogger(SMSAdapterTestxx.class);

	protected static Endpoint server;
	protected static String address;
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired 
	ServiceRegistry srvReg;

	
	@BeforeClass
	public static void setUp() throws Exception {
//		try {
//			address = "http://localhost:8080/smsservice";
//			server = Endpoint.publish(address,new SMSAdapterServiceImpl());
//			
//		}
//		catch(Exception e) {
// 			e.printStackTrace();
//		}
	}

	@AfterClass
	public static void tearDown() throws Exception {
//		try{
//			server.stop();
//			server = null;
//		}
//		catch(Throwable t) {
//			t.printStackTrace();
//		}
	}

	
	@Test
	public void testGetEndPoint() {
		UCSAdapterIntf smsAdapter = srvReg.getAdapter("SMS");
		assertNotNull("The SMS Adapter cannot be found in the registry",smsAdapter);
		
		String endpoint = ((SOAPProxyAdapter)smsAdapter).getEndPoint();
		assertNotNull("The SMS Adapter endpoint is NULL!",endpoint);
		assertTrue("The SMS Adapter string should be configured to http://localhost:8080/smsservice according to src/test/resources/UCS_Runtime.xml but is configured for '"+endpoint+"' !",
				"http://localhost:8080/smsservice".equals(endpoint));
	}

	@Test
	public void testSendMessage() {
		
		SimpleMessage msg = new SimpleMessage();
		DeliveryAddress sender = new DeliveryAddress();
		PhysicalAddress physicalAddress = new PhysicalAddress();
		physicalAddress.setAddress("VISTA - Alert");
		sender.setAddress(physicalAddress);
		msg.getHeader().setSender(sender);
		MessageBody body = new MessageBody();
		body.setContent("Please remind the patient that to prepare for this test, they should not eat or drink anything for at least 9 hours before blood is drawn to get a correct reading.");
		msg.setParts(new MessageBody[] {body} );
		
		DeliveryAddress to = new DeliveryAddress();
		Recipient recipient = new Recipient();
		recipient.setRecipientId(System.currentTimeMillis()+"");
		PhysicalAddress pa = new PhysicalAddress();
		pa.setServiceId("Notification");
		pa.setAddress("5204291210@vtext.com");
		to.setAddress(pa);
		recipient.setDeliveryAddress(to);
		msg.getHeader().getRecipientsList().add(recipient);

		
		AdapterMessage adapterMessage = new AdapterMessage();
		adapterMessage.setMessageType(msg);
		
		UCSAdapterIntf adapter = srvReg.getAdapter("SMS");
		
		//To test the Elastix server
		pa.setAddress("5204291210@vtext.com");
		((SOAPProxyAdapter)adapter).setEndPoint("http://172.31.5.72:8080/KMFUCSAdapterService/smsservice");

		
		boolean sent = adapter.sendMessage(adapterMessage, UUID.randomUUID().toString());
		assertTrue(sent);

	}

	@Test
	public void testGetInteractionTypes() {
		UCSAdapterIntf smsAdapter = srvReg.getAdapter("SMS");
		assertNotNull("The SMS Adapter cannot be found in the registry",smsAdapter);
		
		List<InteractionTypes> types = smsAdapter.getInteractionTypes();
		assertTrue("There should be 3 InteractionTypes and there is only "+types.size(), types.size()==3);
	}

}
