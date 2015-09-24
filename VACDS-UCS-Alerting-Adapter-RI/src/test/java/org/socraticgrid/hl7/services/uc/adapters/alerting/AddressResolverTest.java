package org.socraticgrid.hl7.services.uc.adapters.alerting;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.socraticgrid.hl7.services.uc.adapters.alerting.interfaces.AddressResolverIntf;
import org.socraticgrid.hl7.services.uc.exceptions.InvalidMessageException;
import org.socraticgrid.hl7.services.uc.model.Message;
import org.socraticgrid.hl7.services.uc.model.PhysicalAddress;
import org.socraticgrid.hl7.services.uc.model.SimpleMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:Test-DispatcherTest.xml"})
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class AddressResolverTest {
	
	@Autowired 
	@InjectMocks
	private AddressResolverIntf service;
	
	@Test
	public void testResolveAddress() {
		PhysicalAddress addr = new PhysicalAddress();
		addr.setServiceId("Test");
		String url ="http://someserver";
		String user = "remoteuser|and|context";
		addr.setAddress(url+"|"+user);
		
		ResolvedAddress resAddr = service.resolveAddress(addr);
		
		assertNotNull(resAddr);
		assertTrue("EndPoint Does not match",resAddr.getEndPoint().compareTo(url)==0);
		assertTrue("Remote user does not match",resAddr.getRemoteId().compareTo(user)==0);
		
		}

}
