package org.socraticgrid.hl7.services.uc.processes;

import static org.junit.Assert.*;

import org.junit.Test;

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
import org.socraticgrid.hl7.services.uc.exceptions.ExceptionType;
import org.socraticgrid.hl7.services.uc.exceptions.FeatureNotSupportedException;
import org.socraticgrid.hl7.services.uc.exceptions.InvalidAddressException;
import org.socraticgrid.hl7.services.uc.exceptions.InvalidContentException;
import org.socraticgrid.hl7.services.uc.exceptions.InvalidMessageException;
import org.socraticgrid.hl7.services.uc.exceptions.MissingBodyTypeException;
import org.socraticgrid.hl7.services.uc.exceptions.ProcessingException;
import org.socraticgrid.hl7.services.uc.exceptions.ReadOnlyException;
import org.socraticgrid.hl7.services.uc.exceptions.ServiceOfflineException;
import org.socraticgrid.hl7.services.uc.exceptions.UnknownServiceException;
import org.socraticgrid.hl7.services.uc.exceptions.UpdateException;
import org.socraticgrid.hl7.services.uc.functional.AddressResolver;
import org.socraticgrid.hl7.services.uc.functional.DeliveryManager;
import org.socraticgrid.hl7.services.uc.functional.MessageIdGenerator;
import org.socraticgrid.hl7.services.uc.functional.MessageManager;
import org.socraticgrid.hl7.services.uc.internal.interfaces.ClientRegistryIntf;
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
@ContextConfiguration(locations = { "classpath:Test-ClientServiceImplTest.xml" })
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class NotifiyUCSClientOfExceptionIT {

	@Autowired
	ClientRegistryIntf cleintReg;
	
	@Autowired
	AddressResolver resolver;
	
	@Test
	public void testRun() {
		String msgId = "TestMsgId01";
		
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

		String serverId = "ITTest";
		NotifyUCSClientOfException task = new NotifyUCSClientOfException(cleintReg, exp, ExceptionType.InvalidMessage, msgId, msg, serverId, resolver, receiverDa);
		task.run();
	}

}
