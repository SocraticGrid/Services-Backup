package org.socraticgrid.hl7.services.uc;

import static org.junit.Assert.*;

import java.util.UUID;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.Endpoint;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.socraticgrid.hl7.services.uc.model.AlertMessage;
import org.socraticgrid.hl7.services.uc.model.DeliveryAddress;
import org.socraticgrid.hl7.services.uc.model.PhysicalAddress;
import org.socraticgrid.hl7.services.uc.model.Recipient;
import org.socraticgrid.hl7.services.uc.service.impl.ClientServiceImpl;
import org.socraticgrid.hl7.services.ucs.accessclients.client.ClientServiceSE;
import org.socraticgrid.hl7.services.ucs.accessclients.client.ClientServiceSEI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * This test is designed to test UCS by deploying it from the test environment.
 * @author steven
 * Jul 31, 2014
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:Test-ClientServiceImplTest.xml" })
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class ClientServiceTest {

	private static Logger log = LoggerFactory.getLogger(ClientServiceTest.class);
	private static String alertingEndpoint = "http://localhost:8081/KMFUCSAlerting/ucsalerting";

	protected static Endpoint server;
	protected static String address;
	
	@Autowired
	ApplicationContext applicationContext;
	
	@Autowired 
	@InjectMocks
	private ClientServiceImpl clientServiceImpl;
	
	@Autowired 
	@InjectMocks
	private ClientService clientService;

	
	@Before
	public void setUpBeforeClass() throws Exception {
		try {
			address = "http://localHost:8080/UCS/client";
			server = Endpoint.publish(address,clientService);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	@After
	public void tearDownAfterClass() throws Exception {
		try{
			server.stop();
			server = null;
		}
		catch(Throwable t) {
			t.printStackTrace();
		}
	}

	
	
	@Test
	public void applicationContextTest() {
		assertNotNull("Application Context cannot be configured!",applicationContext);
		assertNotNull("ClientService cannot be configured!",clientServiceImpl);
	}
	
	
	
	@Test
	public void test() {
		AlertMessage alertMsg = new AlertMessage(System.currentTimeMillis()+""); // uid
		org.socraticgrid.hl7.services.uc.model.MessageBody body = new org.socraticgrid.hl7.services.uc.model.MessageBody();
		body.setContent("The VA Clinical Decision Support Service received an abnormal Creatinine level of 1.8mg/dl for your patient, BCMA, Eighteen."+
		"  As this patient is currently being treated with Gentamicin 160mg iv q 8h, a dose adjustment is recommended. An unsigned order for Gentamicin 120mg iv q12h has been entered into VistA. If you agree with this recommendation, please sign the suggested order in CPRS."
		+"<br/><br/>The CDS Service also recommends that a Gentamicin peak and trough be obtained around the third administration of the new dosage.");
		
		
		alertMsg.setParts(new org.socraticgrid.hl7.services.uc.model.MessageBody[] {body} );
		DeliveryAddress sender = new DeliveryAddress();
		PhysicalAddress pa = new PhysicalAddress();
		pa.setAddress("CDS_UCS");
		pa.setAddressId("CDS_UCS");
		sender.setAddress(pa);
		alertMsg.getHeader().setSender(sender);
		
		Recipient recipient = new Recipient();
//		recipient.setRecipientId(UUID.randomUUID().toString());
		DeliveryAddress da = new DeliveryAddress();
		pa = new PhysicalAddress();
		pa.setServiceId("Alert");
		pa.setAddress(alertingEndpoint+"|kmf_ucsalert|bcma.eighteen-patient"); //100033
		da.setAddress(pa);
		recipient.setDeliveryAddress(da);
		alertMsg.getHeader().getRecipientsList().add(recipient);
		
		recipient = new Recipient();
//		recipient.setRecipientId(UUID.randomUUID().toString());
		da = new DeliveryAddress();
		pa = new PhysicalAddress();
		pa.setServiceId("TTV");
		pa.setAddress("SIP/6001");
		da.setAddress(pa);
		recipient.setDeliveryAddress(da);
		alertMsg.getHeader().getRecipientsList().add(recipient);
		
		org.socraticgrid.hl7.services.uc.model.MessageModel<AlertMessage> msgModel = new org.socraticgrid.hl7.services.uc.model.MessageModel<>();
		msgModel.setMessageType(alertMsg);
		
		
		ClientServiceSE ss = new ClientServiceSE();
		ClientServiceSEI port = ss.getClientPort();
		((BindingProvider)port).getRequestContext().put(
        	    BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
        	    address);
		String result = null;
		try {
			result = port.sendMessage(msgModel);
		} catch ( Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		log.debug("************** Response from UCS is '"+result+"'");

	}

}
