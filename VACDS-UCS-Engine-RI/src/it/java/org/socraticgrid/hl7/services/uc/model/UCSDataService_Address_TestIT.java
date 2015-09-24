/**
 * 
 */
package org.socraticgrid.hl7.services.uc.model;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.Statement;

import org.apache.ibatis.session.SqlSession;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.socraticgrid.hl7.services.uc.db.UCSDataConnection;
import org.socraticgrid.hl7.services.uc.db.UCSDataService;
import org.socraticgrid.hl7.services.uc.db.dto.DTOMessageType;
import org.socraticgrid.hl7.services.uc.db.dto.UCSDto;
import org.socraticgrid.hl7.services.uc.internal.idgenerators.TimeBasedIdGenerator;

/**
 * @author steven
 * @created Jan 23, 2014
 * 
 */

// So the Address can be inserted then selected then updated...ISUD w/o the D
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UCSDataService_Address_TestIT {

	static PhysicalAddress testPhysicalAddress = new PhysicalAddress();
	static GroupAddress testGroupAddress = new GroupAddress();
	
	@BeforeClass
	public static void initAddress() {
		testPhysicalAddress.setAddressId(new TimeBasedIdGenerator().getNewId());
		testPhysicalAddress.setAddress("http://someserver.com:8080/someservice/~selliott/sendmessge");
		
		testGroupAddress.setAddressId(new TimeBasedIdGenerator().getNewId());
		testGroupAddress.setName("Group Address");

		System.out.println("\n\n****************************");
		System.out.println("************* RUNNING - UCSDataService_Address_TestIT");
		System.out.println("****************************\n\n");
	}
	
	@AfterClass
	public static void removeAddressFromDB() {
		SqlSession sqlSession = null;
		try{
			sqlSession = UCSDataConnection.getSqlSession();
			Connection  con = sqlSession.getConnection();
			Statement stmt = con.createStatement(); 
			stmt.execute("DELETE FROM address WHERE address_id = '"+testPhysicalAddress.getAddressId()+"'");
			stmt.execute("DELETE FROM address WHERE address_id = '"+testGroupAddress.getAddressId()+"'");
		}
		catch(Exception e){
			e.printStackTrace(System.err);
		}
		finally{
			if(sqlSession!=null){ 
				sqlSession.close(); 
			}
		}
	}
	
	@Test //Insert the Address
	public void test1() {
		AddressPersistenceAdapter physicalAdapter = new AddressPersistenceAdapter(testPhysicalAddress);
		AddressPersistenceAdapter groupAdapter = new AddressPersistenceAdapter(testGroupAddress);
		
		UCSDto<AddressPersistenceAdapter> dto = new UCSDto<>();
		dto.getListT().add(physicalAdapter);
		dto.getListT().add(groupAdapter);
		UCSDataService.insertAddress(dto);
		
		StringBuffer failures = new StringBuffer();
		for(DTOMessageType msgType : dto.getMsgs().keySet()){
			if(msgType.equals(DTOMessageType.ERROR) || msgType.equals(DTOMessageType.WARN)){
				failures.append(dto.getMsgs().get(msgType));
			}
		}
		if(failures.length()!=0){
			fail(failures.toString());
		}
	}
	
	
	@Test //Select the PhysicalAddress
	public void test2() {
		UCSDto<AddressPersistenceAdapter> dto = new UCSDto<>();
		dto.getAdHocParams().put("addressId",testPhysicalAddress.getAddressId());
		UCSDataService.selectAddressById(dto);

		StringBuffer failures = new StringBuffer();
		for(DTOMessageType msgType : dto.getMsgs().keySet()){
			if(msgType.equals(DTOMessageType.ERROR) || msgType.equals(DTOMessageType.WARN)){
				failures.append(dto.getMsgs().get(msgType));
			}
		}
		if(failures.length()!=0){
			fail(failures.toString());
		}
		else{
			assertNotNull(dto.getListT().get(0));
			
			//How to get a specific address type from the adapter
			AddressPersistenceAdapter adapter = dto.getListT().get(0);
			PhysicalAddress physicalAddr = (PhysicalAddress)adapter.getMaskedAddress();

			assertTrue(physicalAddr.getAddress().equals(testPhysicalAddress.getAddress()));
			assertTrue(physicalAddr.getAddressId().equals(testPhysicalAddress.getAddressId()));
		}
	}
	
	
	@Test //Select the GroupAddress
	public void test3() {
		UCSDto<AddressPersistenceAdapter> dto = new UCSDto<>();
		dto.getAdHocParams().put("addressId",testGroupAddress.getAddressId());
		UCSDataService.selectAddressById(dto);

		StringBuffer failures = new StringBuffer();
		for(DTOMessageType msgType : dto.getMsgs().keySet()){
			if(msgType.equals(DTOMessageType.ERROR) || msgType.equals(DTOMessageType.WARN)){
				failures.append(dto.getMsgs().get(msgType));
			}
		}
		if(failures.length()!=0){
			fail(failures.toString());
		}
		else{
			assertNotNull(dto.getListT().get(0));
			
			//How to get a specific address type from the adapter
			AddressPersistenceAdapter adapter = dto.getListT().get(0);
			GroupAddress groupAddr = (GroupAddress)adapter.getMaskedAddress();

			assertTrue(groupAddr.getName().equals(testGroupAddress.getName()));
			assertTrue(groupAddr.getAddressId().equals(testGroupAddress.getAddressId()));
		}
	}

	
	
	@Test //Update the PhysicalAddress
	public void test4() {
		
		String serviceId = "123456789-"; //Only valid for PhysicalAddress types
		testPhysicalAddress.setServiceId(serviceId);
		
		AddressPersistenceAdapter adapter = new AddressPersistenceAdapter(testPhysicalAddress);

		
		UCSDto<AddressPersistenceAdapter> dto = new UCSDto<>();
		dto.getListT().add(adapter);
		//Update the serviceId
		UCSDataService.updateAddress(dto);
		
		dto = new UCSDto<>();
		dto.getAdHocParams().put("addressId",testPhysicalAddress.getAddressId());
		UCSDataService.selectAddressById(dto);
		
		PhysicalAddress address = (PhysicalAddress)dto.getListT().get(0).getMaskedAddress();
		assertTrue(serviceId.equals(address.getServiceId()));

	}

	
	
	@Test //Update the GroupAddress
	public void test5() {
		
		String newName = "A completely different name"; //Only valid for PhysicalAddress types
		testGroupAddress.setName(newName);
		
		AddressPersistenceAdapter adapter = new AddressPersistenceAdapter(testGroupAddress);

		
		UCSDto<AddressPersistenceAdapter> dto = new UCSDto<>();
		dto.getListT().add(adapter);
		//Update the serviceId
		UCSDataService.updateAddress(dto);
		
		dto = new UCSDto<>();
		dto.getAdHocParams().put("addressId",testGroupAddress.getAddressId());
		UCSDataService.selectAddressById(dto);
		
		GroupAddress address = (GroupAddress)dto.getListT().get(0).getMaskedAddress();
		assertTrue(newName.equals(address.getName()));

	}

}
