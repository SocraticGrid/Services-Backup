package org.socraticgrid.hl7.services.eps.internal.model;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

public class PushStatusTest {
	PushStatus subject  = new PushStatus();

	
	@Test
	public void testDeliveryTime() {
		assertNull("Initial Delivery time is not null",subject.getDeliveryTime());
		Date deliveryTime = new Date(System.currentTimeMillis());
		subject.setDeliveryTime(deliveryTime);
		Date fndDate = subject.getDeliveryTime();
		assertTrue("Delivery Time does not match what is set",deliveryTime.equals(fndDate));
		
	}

	@Test
	public void testIsDelivered() {
		assertFalse("Initial Delivery Status in not false",subject.isDelivered());
	}

	@Test
	public void testSetDelivered() {
		subject.setDelivered(true);
		assertTrue("Delivery Status should be true",subject.isDelivered());
		subject.setDelivered(false);
		assertFalse("DeliveryStatus should be false",subject.isDelivered());
	}


	@Test
	public void testMessageId() {
		assertNull("Initial Message Id is not null",subject.getMessageId());
		String id= "TestId";
		subject.setMessageId(id);
		String fndId = subject.getMessageId();
		assertTrue("Message id does not match what was set",id.compareTo(fndId)==0);
	}

}
