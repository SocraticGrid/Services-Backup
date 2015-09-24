package org.socraticgrid.hl7.services.uc.internal.configuration;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.socraticgrid.hl7.services.uc.adapters.proxies.SOAPProxyAdapter;
import org.socraticgrid.hl7.services.uc.functional.ServiceRegistry;
import org.socraticgrid.hl7.services.uc.interfaces.UCSAdapterIntf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:Test-IncludedResources.xml" })
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class ConfiguratorTest {
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired 
	ServiceRegistry srvReg;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testSMSConfiguration() {
	
		UCSAdapterIntf smsAdapter = srvReg.getAdapter("testSMS");
		assertNotNull("The Test SMS Adapter cannot be found in the registry",smsAdapter);
		
		String endpoint = ((SOAPProxyAdapter)smsAdapter).getEndPoint();
		assertNotNull("The Test MS Adapter endpoint is NULL!",endpoint);
		assertTrue("The Test SMS Adapter string should be configured to http://localhost:8080/smsservice according to src/test/resources/UCS_Runtime.xml",
				"http://localhost:8080/smsservice".equals(endpoint));

	}

}
