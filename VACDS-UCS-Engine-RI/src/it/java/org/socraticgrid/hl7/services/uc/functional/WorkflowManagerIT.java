package org.socraticgrid.hl7.services.uc.functional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.*;

import org.socraticgrid.hl7.services.uc.exceptions.ExceptionType;
import org.socraticgrid.hl7.services.uc.exceptions.ProcessingException;
import org.socraticgrid.hl7.services.uc.model.DeliveryAddress;
import org.socraticgrid.hl7.services.uc.model.Message;
import org.socraticgrid.hl7.services.uc.model.MessageModel;
import org.socraticgrid.hl7.services.uc.model.PhysicalAddress;
import org.socraticgrid.hl7.services.uc.model.SimpleMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:Test-ClientServiceImplTest.xml" })
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class WorkflowManagerIT {
	

	@Autowired
	@InjectMocks
	private WorkflowManager service;

	@Mock(name = "msgMgr")
	MessageManager msgMgr;
	@Mock(name = "evtLogger")
	EventLogger evtLogger;
	

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testHandleException() {
		//fail("Not yet implemented");
	}

	@Test
	public void testHandleAdapterException() {
		
		String msgId = "TestMsg01";
		
		//exception,type,messageId,msg
		SimpleMessage msg = new SimpleMessage();
		PhysicalAddress senderAdr = new PhysicalAddress();
		senderAdr.setServiceId("test");
		senderAdr.setAddressId("SomeId");
		senderAdr.setAddress("Address");
		DeliveryAddress senderDa = new DeliveryAddress(senderAdr);
		msg.getHeader().setSender(senderDa);
		msg.getHeader().setMessageId(msgId);
		PhysicalAddress receiverAdr = new PhysicalAddress();
		receiverAdr.setServiceId("testrcpt");
		receiverAdr.setAddressId("SomeReceiverId");
		receiverAdr.setAddress("ReceiverAddress");
		
		DeliveryAddress receiverDa = new DeliveryAddress(receiverAdr);
		
		ProcessingException exp = new ProcessingException();
		exp.setGeneratingMessage(msgId);
		exp.setIssuingService("IntegrationTest");
		exp.setFault("TestCase");
		
		//when(msgMgr.getMessageById(anyString())).thenReturn(msgMdl);
//		when(msgMgr.getMessageById(anyString())).thenReturn(msgMdl);
		doReturn(new MessageModel<SimpleMessage>(msg)).when(msgMgr).getMessageById(anyString());
		
		String adapterIdentity = "ITTestAdapter";

		service.handleAdapterException(exp, ExceptionType.General, msgId, receiverDa, adapterIdentity);
		
		
		
		
	}

}
