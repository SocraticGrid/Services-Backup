package org.socraticgrid.hl7.services.uc;

import javax.xml.ws.BindingProvider;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.socraticgrid.hl7.services.uc.model.AlertMessage;
import org.socraticgrid.hl7.services.uc.model.DeliveryAddress;
import org.socraticgrid.hl7.services.uc.model.PhysicalAddress;
import org.socraticgrid.hl7.services.uc.model.Recipient;
import org.socraticgrid.hl7.services.ucs.accessclients.client.ClientServiceSE;
import org.socraticgrid.hl7.services.ucs.accessclients.client.ClientServiceSEI;


/**
 * This test is designed to test UCS running on a local deployment.
 * @author steven
 * Jul 31, 2014
 *
 */
public class KMF_CDS_AlertingServiceTest {

	private static Logger log = LoggerFactory.getLogger(KMF_CDS_AlertingServiceTest.class);
	private static String alertingEndpoint = "http://localhost:8081/KMFUCSAlerting/ucsalerting";
	private static String endpoint = "http://localhost:8081/UCS/client";
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
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
		pa.setAddress("testCDS_UCS");
		pa.setServiceId("testCDS_UCS");
		sender.setAddress(pa);
		alertMsg.getHeader().setSender(sender);
		
		Recipient recipient = new Recipient();
		recipient.setRecipientId(System.currentTimeMillis()+"");
		DeliveryAddress da = new DeliveryAddress();
		pa = new PhysicalAddress();
		pa.setServiceId("Alert");
		pa.setAddress(alertingEndpoint+"|kmf_ucsalert|bcma.eighteen-patient"); //100033
		da.setAddress(pa);
		recipient.setDeliveryAddress(da);
		alertMsg.getHeader().getRecipientsList().add(recipient);
		
		recipient = new Recipient();
		recipient.setRecipientId(System.currentTimeMillis()+1+"");
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
        	    endpoint);
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
