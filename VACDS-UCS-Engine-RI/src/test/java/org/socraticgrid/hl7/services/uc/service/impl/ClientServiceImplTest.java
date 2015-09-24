package org.socraticgrid.hl7.services.uc.service.impl;

import static org.junit.Assert.fail;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.socraticgrid.hl7.services.uc.exceptions.InvalidMessageException;
import org.socraticgrid.hl7.services.uc.functional.DeliveryManager;
import org.socraticgrid.hl7.services.uc.functional.MessageIdGenerator;
import org.socraticgrid.hl7.services.uc.functional.MessageManager;
import org.socraticgrid.hl7.services.uc.model.AlertMessage;
import org.socraticgrid.hl7.services.uc.model.DeliveryAddress;
import org.socraticgrid.hl7.services.uc.model.Message;
import org.socraticgrid.hl7.services.uc.model.MessageModel;
import org.socraticgrid.hl7.services.uc.model.PhysicalAddress;
import org.socraticgrid.hl7.services.uc.model.Recipient;
import org.socraticgrid.hl7.services.uc.model.SimpleMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:Test-ClientServiceImplTest.xml"})
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class ClientServiceImplTest {
	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired 
	@InjectMocks
	private ClientServiceImpl service;
	
	@Autowired 
	private MessageIdGenerator idGenerator;

	
	@Mock(name = "dlvMgr") DeliveryManager deliveryMgr;
	@Mock(name = "msgMgr") MessageManager mockMsgMgr;

	@Before public void setUp()
	{
		MockitoAnnotations.initMocks(this);
	}

	
	@Test
	public void testRetieveCallsMsgMgr() {
		String messageId ="testmessageId";
		SimpleMessage smsgTy = new SimpleMessage(idGenerator.getGenerator().getNewId());
		MessageModel<SimpleMessage> msg = new MessageModel<>(smsgTy);
		
		// getMessageById(...) can only return a MessagModel<Message>
//		Mockito.when(mockMsgMgr.getMessageById(messageId)).thenReturn(msg);
		doReturn(msg).when(mockMsgMgr).getMessageById(messageId);
		try {
			service.retrieveMessage(messageId);
		} catch (InvalidMessageException e) {
			// TODO Auto-generated catch block
			fail(e.getMessage());
		}
	
		verify(mockMsgMgr).getMessageById(messageId);
		
		verify(mockMsgMgr,never()).saveMessage(msg);
	}

	@Ignore
	@Test
	public void testMessageCreation()
	{

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
		

		
	}
}	
