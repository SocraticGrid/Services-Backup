package org.socraticgrid.hl7.services.uc.service.impl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.socraticgrid.hl7.services.uc.exceptions.BadBodyException;
import org.socraticgrid.hl7.services.uc.exceptions.DeliveryException;
import org.socraticgrid.hl7.services.uc.exceptions.FeatureNotSupportedException;
import org.socraticgrid.hl7.services.uc.exceptions.InvalidAddressException;
import org.socraticgrid.hl7.services.uc.exceptions.InvalidContentException;
import org.socraticgrid.hl7.services.uc.exceptions.InvalidMessageException;
import org.socraticgrid.hl7.services.uc.exceptions.MissingBodyTypeException;
import org.socraticgrid.hl7.services.uc.exceptions.ReadOnlyException;
import org.socraticgrid.hl7.services.uc.exceptions.ServiceOfflineException;
import org.socraticgrid.hl7.services.uc.exceptions.UnknownServiceException;
import org.socraticgrid.hl7.services.uc.exceptions.UpdateException;
import org.socraticgrid.hl7.services.uc.functional.DeliveryManager;
import org.socraticgrid.hl7.services.uc.functional.MessageIdGenerator;
import org.socraticgrid.hl7.services.uc.functional.MessageManager;
import org.socraticgrid.hl7.services.uc.model.AlertMessage;
import org.socraticgrid.hl7.services.uc.model.DeliveryAddress;
import org.socraticgrid.hl7.services.uc.model.Message;
import org.socraticgrid.hl7.services.uc.model.MessageModel;
import org.socraticgrid.hl7.services.uc.model.PhysicalAddress;
import org.socraticgrid.hl7.services.uc.model.Recipient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:Test-ClientServiceImplTest.xml" })
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class ClientServiceImplTestIT {
	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	@InjectMocks
	private ClientServiceImpl service;

	@Mock(name = "dlvMgr")
	DeliveryManager deliveryMgr;
	@Mock(name = "msgMgr")
	MessageManager mockMsgMgr;
	
	@Autowired 
	private MessageIdGenerator idGenerator;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Ignore
	@Test
	public void testSendMessageLogging() {
	
		MessageModel<Message> msg = new MessageModel<>();
		AlertMessage alertTy = new AlertMessage();
		msg.setMessageType(alertTy);
		Recipient recipient = new Recipient();
		DeliveryAddress da = new DeliveryAddress();
		PhysicalAddress pa = new PhysicalAddress();
		pa.setServiceId("Alert");
		pa.setAddress("http://Someendpoint|userId");
		da.setAddress(pa);
		recipient.setDeliveryAddress(da);

		alertTy.getHeader().getRecipientsList().add(recipient);

		try {
			service.sendMessage(msg);
		} 
		catch ( DeliveryException	| FeatureNotSupportedException | ServiceOfflineException
				| UpdateException | ReadOnlyException | MissingBodyTypeException | BadBodyException | InvalidAddressException | UnknownServiceException  exp) {
			
			exp.printStackTrace(System.out);	
			fail(exp.getMessage());
		}
		catch(InvalidContentException exp)
		{
			exp.printStackTrace(System.out);	
			fail("Invalid Content");
		}
		catch(InvalidMessageException exp)
		{
			exp.printStackTrace(System.out);	
			//Logger.getLogger(ClientServiceImplTestIT.class.getName()).error("Invalid Message", exp);
			assertNotNull("Invalid Message Exception has a null message",exp.getMessage());
		
		}
		//We expect the message to be invalid

	}

}
