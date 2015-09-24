package org.socraticgrid.hl7.services.eps.internal;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:Test-SerialSubscriptionIdGenerator.xml" })
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)

public class SerialSubscriptionIdGeneratorTest {

	@Autowired
	SerialSubscriptionIdGenerator generator;
	
	@Test
	public void testGenerateSubscriptionId() {
	
		String id = generator.generateSubscriptionId(null, null, null);
		assertNotNull("Generated a null Id", id);
		String id2 = generator.generateSubscriptionId(null, null, null);
		assertFalse("New Id matches old",id.compareToIgnoreCase(id2)==0);
	}

	
	@Test
	public void testGenerateMultipleUniqueSubscriptionId() {
	
		int limit=1000;
		Set<String> seen = new HashSet<String>();
		for (int i=0;i<limit;i++)
		{
			String id =generator.generateSubscriptionId(null, null, null);
			assertTrue("Non Unique Id seen",seen.add(id));
		}
	}

}
