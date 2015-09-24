package org.socraticgrid.hl7.services.uc.model;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class MessageStructIT {

	@Test
	public void testMessageStuctureDefaults() {
		AlertMessage msg = new AlertMessage(System.currentTimeMillis()+"");
		assertNotNull("Alert message header not initialized",msg.getHeader());
	}

}
