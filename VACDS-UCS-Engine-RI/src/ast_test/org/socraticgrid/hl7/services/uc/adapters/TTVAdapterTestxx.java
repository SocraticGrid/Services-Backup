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
import org.socraticgrid.hl7.services.uc.adapters.service.TextToVoiceAdapterServiceImpl;
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

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:Test-IncludedResources.xml" })
public class TTVAdapterTestxx {
	
	private static Logger log = LoggerFactory.getLogger(TTVAdapterTestxx.class);

	private static final String ASTERISK_IP = "172.31.5.72";
	//This could be a different lhand port (e.g. 9038 or...) but the rhand port needs to be 5038
	private static final int MANAGER_PORT = 5038;
	private static final String SSH_NAME = "selliott";
	private static final String SSH_PWD = "crud8geTU*";
	private static final String MANAGER_PWD = "C0gnitive!";
	private static Session session;

	
	protected static Endpoint server;
	protected static String address;
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired 
	ServiceRegistry srvReg;

	
	@BeforeClass
	public static void setUp() throws Exception {
//		try {
//			address = "http://localhost:8080/ttvservice";
//			server = Endpoint.publish(address,new TextToVoiceAdapterServiceImpl());
//
//			//Setup the tunnel to 5038 using JSch
//			JSch jsch=new JSch();
//			jsch.setKnownHosts(TTVAdapterTestxx.class.getClassLoader().getResourceAsStream("known_hosts"));
//			session = jsch.getSession(SSH_NAME, ASTERISK_IP, 22);
//			session.setPassword(SSH_PWD);
//			session.connect();
//			session.setPortForwardingL(MANAGER_PORT, ASTERISK_IP, MANAGER_PORT);
//			System.out.println("********** SSH PORTFORWARDING INITIATED");
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
//		try{
//			//Teardown the tunnel to 5038
//			session.disconnect();
//			System.out.println("********** SSH PORTFORWARDING DISCONNECTED");
//		}
//		catch(Throwable t) {
//			t.printStackTrace();
//		}
	}

	
	@Test
	public void testGetEndPoint() {
		UCSAdapterIntf ttvAdapter = srvReg.getAdapter("TTV");
		assertNotNull("The SMS Adapter cannot be found in the registry",ttvAdapter);
		
		String endpoint = ((SOAPProxyAdapter)ttvAdapter).getEndPoint();
		assertNotNull("The TTV Adapter endpoint is NULL!",endpoint);
		assertTrue("The TTV Adapter string should be configured to http://localhost:8080/ttvservice according to src/test/resources/UCS_Runtime.xml but is configured for '"+endpoint+"' !",
				"http://localhost:8080/ttvservice".equals(endpoint));
	}

	/**
	 * Test should only be enable when softphone SIP/6001 is connected !!!
	 */
	@Test
	public void testSendMessage() {
		
		SimpleMessage msg = new SimpleMessage();
		DeliveryAddress sender = new DeliveryAddress();
		PhysicalAddress physicalAddress = new PhysicalAddress();
		physicalAddress.setAddress("CDS_UCS");
		physicalAddress.setAddressId("CDS_UCS");
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
		pa.setAddress("SIP/6001");
		to.setAddress(pa);
		recipient.setDeliveryAddress(to);
		msg.getHeader().getRecipientsList().add(recipient);

		
		AdapterMessage adapterMessage = new AdapterMessage();
		adapterMessage.setMessageType(msg);
		
		UCSAdapterIntf adapter = srvReg.getAdapter("TTV");
		
		//To test the Elastix server
//		pa.setAddress("5204291210@vtext.com");
//		((SOAPProxyAdapter)adapter).setEndPoint("http://172.31.5.72:8080/KMFUCSAdapterService/ttvservice");

		
		boolean sent = adapter.sendMessage(adapterMessage, UUID.randomUUID().toString());
		assertTrue(sent);

	}

//	@Test
	public void testGetInteractionTypes() {
		UCSAdapterIntf ttvAdapter = srvReg.getAdapter("TTV");
		assertNotNull("The TTV Adapter cannot be found in the registry",ttvAdapter);
		
		List<InteractionTypes> types = ttvAdapter.getInteractionTypes();
		assertTrue("There should be 2 InteractionTypes and there is only "+types.size(), types.size()==3);
	}

}
