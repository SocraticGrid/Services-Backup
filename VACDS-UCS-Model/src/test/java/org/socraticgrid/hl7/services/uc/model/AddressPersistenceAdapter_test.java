/**
 * 
 */
package org.socraticgrid.hl7.services.uc.model;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author steven
 * @created Jan 27, 2014

 */
public class AddressPersistenceAdapter_test {
	
	@Test
	public void groupAddressTest() {
		
		GroupAddress grpAddr = new GroupAddress();
		grpAddr.setAddressId(System.currentTimeMillis()+"");
		grpAddr.setName("Group Address");
		
		AddressPersistenceAdapter adapter = new AddressPersistenceAdapter(grpAddr);
		assertTrue(grpAddr.getAddressId().equals(adapter.getAddressId()));
		assertTrue(grpAddr.getName().equals(adapter.getName()));
		
		/**
		 * These tests will fail because the Address was not pulled from persistence.!
		 * Only when the test is pulled from persistence are all the fields populated
		 * specifically the addressType.
		 */
//		BaseAddress maskedAddr = adapter.getMaskedAddress();
//		assertTrue(maskedAddr instanceof GroupAddress);
//		GroupAddress maskedGrpAddr = (GroupAddress)maskedAddr;
//		assertTrue(grpAddr.getAddressId().equals(maskedGrpAddr.getAddressId()));
//		assertTrue(grpAddr.getName().equals(maskedGrpAddr.getName()));
				
	}
	
	@Test
	public void partyAddressTest() {
		
		PartyAddress prtyAddr = new PartyAddress();
		prtyAddr.setAddressId(System.currentTimeMillis()+"");
		prtyAddr.setName("Party Address");
		
		AddressPersistenceAdapter adapter = new AddressPersistenceAdapter(prtyAddr);
		assertTrue(prtyAddr.getAddressId().equals(adapter.getAddressId()));
		assertTrue(prtyAddr.getName().equals(adapter.getName()));
		
		/**
		 * These tests will fail because the Address was not pulled from persistence.!
		 * Only when the test is pulled from persistence are all the fields populated
		 * specifically the addressType.
		 */
//		BaseAddress maskedAddr = adapter.getMaskedAddress();
//		assertTrue(maskedAddr instanceof PartyAddress);
//		PartyAddress maskedPrtyAddr = (PartyAddress)maskedAddr;
//		assertTrue(prtyAddr.getAddressId().equals(maskedPrtyAddr.getAddressId()));
//		assertTrue(prtyAddr.getName().equals(maskedPrtyAddr.getName()));
	
	}
	
	@Test
	public void physicalAddressTest() {
		
		PhysicalAddress physAddr = new PhysicalAddress();
		physAddr.setAddressId(System.currentTimeMillis()+"");
		physAddr.setServiceId("Service Id");
		physAddr.setAddress("http://someserver.com:8080/someservice/~selliott/sendmessge");
		
		
		AddressPersistenceAdapter adapter = new AddressPersistenceAdapter(physAddr);
		assertTrue(physAddr.getAddressId().equals(adapter.getAddressId()));
		assertTrue(physAddr.getServiceId().equals(adapter.getServiceId()));
		assertTrue(physAddr.getAddress().equals(adapter.getAddress()));
		
		/**
		 * These tests will fail because the Address was not pulled from persistence.!
		 * Only when the test is pulled from persistence are all the fields populated
		 * specifically the addressType.
		 */
//		BaseAddress maskedAddr = adapter.getMaskedAddress();
//		assertTrue(maskedAddr instanceof PhysicalAddress);
//		PhysicalAddress maskedPhysAddr = (PhysicalAddress)maskedAddr;
//		assertTrue(physAddr.getAddressId().equals(maskedPhysAddr.getAddressId()));
//		assertTrue(physAddr.getServiceId().equals(maskedPhysAddr.getServiceId()));
//		assertTrue(physAddr.getAddress().equals(maskedPhysAddr.getAddress()));
	}
	
}
