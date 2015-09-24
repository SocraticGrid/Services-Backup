package org.socraticgrid.hl7.services.uc.adapters.alerting;



import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.socraticgrid.hl7.services.uc.adapters.alerting.interfaces.DispatcherIntf;
import org.socraticgrid.hl7.services.uc.exceptions.ExceptionType;
import org.socraticgrid.hl7.services.uc.exceptions.InvalidConversationException;
import org.socraticgrid.hl7.services.uc.exceptions.InvalidMessageException;
import org.socraticgrid.hl7.services.uc.exceptions.ProcessingException;
import org.socraticgrid.hl7.services.uc.interfaces.AdapterIntf;
import org.socraticgrid.hl7.services.uc.model.DeliveryAddress;
import org.socraticgrid.hl7.services.uc.model.MessageModel;
import org.socraticgrid.hl7.services.uc.model.PhysicalAddress;
import org.socraticgrid.hl7.services.uc.model.SimpleMessage;
import org.socraticgrid.hl7.services.ucs.accessclients.ucsalerting.UCSAlertingServiceSE;
import org.socraticgrid.hl7.services.ucs.accessclients.ucsalerting.UCSAlertingServiceSEI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:Test-DispatcherTest.xml"})
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class DispatcherTest {
	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired 
	@InjectMocks
	private DispatcherIntf service;
	
	@Autowired 
	UCSAlertingServiceSE serviceEndpoint;
	
	
	@Autowired 
	private Configuration config;
		
	@Autowired 
	@Qualifier("MockSEI")
	UCSAlertingServiceSEI serviceEI;
	

	@Mock(name = "requestMap") Map<String,Object> requestMap;
	@Mock(name = "adapter") AdapterIntf adapter;

	@Before public void setUp()
	{
		MockitoAnnotations.initMocks(this);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testRequireAlias_AliasPassed() throws InvalidMessageException, InvalidConversationException {

		
		SimpleMessage smsgTy = new SimpleMessage();
	
		PhysicalAddress addr = new PhysicalAddress();
		addr.setAddress("MyAlias|userId");
		
		config.setRequireAliasUse(true);
		UCSAlertingServiceSEI delMock= ((MockSEI)serviceEI).getmockDelgate();
		Mockito.when(delMock.receiveAlertMessage((MessageModel<SimpleMessage>) anyObject(), (List<String>) anyObject(), anyString())).thenReturn(true);
		//Setup the SEI
		Mockito.when(serviceEndpoint.getUcsAlertingPort()).thenReturn(serviceEI);
		//Hack in the request Map
		((MockSEI)serviceEI).setRequestContext(requestMap);
		
		boolean ok = service.sendMessage(addr,smsgTy,"serverId");
		assertTrue("SendMessage was not true",ok);
		
		
		verify(requestMap).put(eq("javax.xml.ws.service.endpoint.address"), eq("http://www.somewhere.com/ucsalerting"));
		verify(delMock).receiveAlertMessage((MessageModel<SimpleMessage>) anyObject(),(List<String>) anyObject(),eq("serverId"));
		assertTrue("User Id not passed to UCS Alert client (Alias)",((MockSEI)serviceEI).getLastReceiverList().contains("userId"));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testRetieveCallsMsgMgr() {

		
		SimpleMessage smsgTy = new SimpleMessage();
	
		PhysicalAddress addr = new PhysicalAddress();
		addr.setAddress("http://Someendpoint|userId");
		
		//Setup the SEI
		Mockito.when(serviceEndpoint.getUcsAlertingPort()).thenReturn(serviceEI);
		
		
		//Setup the SEI
		Mockito.when(serviceEI.receiveAlertMessage((MessageModel<SimpleMessage>) anyObject(), (List<String>) anyObject(), anyString())).thenReturn(true);
		//Hack in the request Map
		((MockSEI)serviceEI).setRequestContext(requestMap);
		UCSAlertingServiceSEI delMock= ((MockSEI)serviceEI).getmockDelgate();
		Mockito.when(delMock.receiveAlertMessage((MessageModel<SimpleMessage>) anyObject(), (List<String>) anyObject(), anyString())).thenReturn(true);
		
		boolean ok = service.sendMessage(addr,smsgTy,"serverId");
		assertTrue("SendMessage was not true",ok);
		//VerifY Mock SEI was called as expected
		verify(((MockSEI)serviceEI).getmockDelgate()).receiveAlertMessage((MessageModel<SimpleMessage>)anyObject(), (List<String>) anyObject(),eq("serverId"));
		verify(requestMap).put(eq("javax.xml.ws.service.endpoint.address"), eq("http://Someendpoint"));
		assertTrue("User Id not passed to UCS Alert client (URL)",((MockSEI)serviceEI).getLastReceiverList().contains("userId"));
	
	}
	
	
	@Test
	public void testRequireAlias_AliasNotPassed() throws InvalidMessageException, InvalidConversationException {

		
		SimpleMessage smsgTy = new SimpleMessage();
	
		PhysicalAddress addr = new PhysicalAddress();
		addr.setAddress("http://Someendpoint|userId");
		
		config.setRequireAliasUse(true);
		boolean ok = service.sendMessage(addr,smsgTy,"serverId");
		assertFalse(ok);
		verify(adapter).postException(isA(ProcessingException.class), eq(ExceptionType.InvalidAddress), anyString(),isA(DeliveryAddress.class), anyString());
		

	}



	@Test
	public void testRequireAlias_SecureAddressRequired_NotPassed() throws InvalidMessageException, InvalidConversationException {

		
		SimpleMessage smsgTy = new SimpleMessage();
	
		PhysicalAddress addr = new PhysicalAddress();
		addr.setAddress("http://Someendpoint|userId");
		
		config.setRequireSecureEndpoint(true);
		boolean ok = service.sendMessage(addr,smsgTy,"serverId");
		assertFalse(ok);
		verify(adapter).postException(isA(ProcessingException.class), eq(ExceptionType.InvalidAddress), anyString(),isA(DeliveryAddress.class), anyString());
		

	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testRequireAlias_SecureAddressRequired_Passed() throws InvalidMessageException, InvalidConversationException {

		
		SimpleMessage smsgTy = new SimpleMessage();
	
	
		PhysicalAddress addr = new PhysicalAddress();
		addr.setAddress("HttpS://Someendpoint|userId");
		
		config.setRequireSecureEndpoint(true);
		UCSAlertingServiceSEI delMock= ((MockSEI)serviceEI).getmockDelgate();
		Mockito.when(delMock.receiveAlertMessage((MessageModel<SimpleMessage>) anyObject(),(List<String>) anyObject(), anyString())).thenReturn(true);
		//Setup the SEI
		Mockito.when(serviceEndpoint.getUcsAlertingPort()).thenReturn(serviceEI);
		//Hack in the request Map
		((MockSEI)serviceEI).setRequestContext(requestMap);
		
		boolean ok = service.sendMessage(addr,smsgTy,"serverId");
		assertTrue("SendMessage was not true",ok);
		
		
		verify(requestMap).put(eq("javax.xml.ws.service.endpoint.address"), eq("HttpS://Someendpoint"));
		verify(delMock).receiveAlertMessage((MessageModel<SimpleMessage>) anyObject(),(List<String>) anyObject(),eq("serverId"));
	}

	@Test
	public void testRequireAlias_MalformedURL() throws InvalidMessageException, InvalidConversationException {

		
		SimpleMessage smsgTy = new SimpleMessage();
	
		PhysicalAddress addr = new PhysicalAddress();
		addr.setAddress("ftp://Someendpoint|userId");
		
		config.setRequireSecureEndpoint(true);
		boolean ok = service.sendMessage(addr,smsgTy,"serverId");
		assertFalse(ok);
		verify(adapter).postException(isA(ProcessingException.class), eq(ExceptionType.InvalidAddress), anyString(),isA(DeliveryAddress.class), anyString());
		

	}
}
