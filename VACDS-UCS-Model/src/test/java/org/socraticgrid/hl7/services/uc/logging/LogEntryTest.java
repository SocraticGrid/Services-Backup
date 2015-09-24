package org.socraticgrid.hl7.services.uc.logging;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Test;


public class LogEntryTest {

	@Test
	public void testGetWhen() {
		LogEntry instance = new LogEntry();
		Date when = instance.getWhen();
		assertNotNull(when);
	}

	@Test
	public void testSetWhen() {
		Date when = new Date(System.currentTimeMillis());
		LogEntry instance = new LogEntry();
		instance.setWhen(when);
		assertEquals("Log Entry date does not match expected value",when,instance.getWhen());
	}

	@Test
	public void testGetType() {
		LogEntry instance = new LogEntry();
		LogEntryType current = instance.getType();
		assertEquals("Log Entry type is not the expected default of General",LogEntryType.General,current);
	}

	@Test
	public void testSetType() {
		LogEntry instance = new LogEntry();
		instance.setType(LogEntryType.Diagnostic);
		LogEntryType current = instance.getType();
		assertEquals("Log Entry type change did not take effect",LogEntryType.Diagnostic,current);
	}
/*
	@Test
	public void testGetEvent() {
		//fail("Not yet implemented");
	}

	@Test
	public void testSetEvent() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetEventText() {
		//fail("Not yet implemented");
	}

	@Test
	public void testSetEventText() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetContext() {
		//fail("Not yet implemented");
	}

	@Test
	public void testSetContext() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetUser() {
		//fail("Not yet implemented");
	}

	@Test
	public void testSetUser() {
		//fail("Not yet implemented");
	}
*/
}
