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

// So the DeliveryAddress can be inserted then selected then updated...ISUD w/o the D
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UCSDataService_DeliveryAddress_TestIT {

	static DeliveryAddress testDeliveryAddress = new DeliveryAddress();
	//Because DeliveryAddress will test reference to Address
	static PhysicalAddress physicalAddress = new PhysicalAddress();
	//Because DeliveryAddress will update reference to Address
	static GroupAddress groupAddress = new GroupAddress();

	
	static TimeBasedIdGenerator timebasedIdGenerator = new TimeBasedIdGenerator();
	
	@BeforeClass
	public static void init() {

		System.out.println("\n\n****************************");
		System.out.println("************* RUNNING - UCSDataService_DeliveryAddress_TestIT");
		System.out.println("****************************\n\n");

		physicalAddress.setAddressId(timebasedIdGenerator.getNewId());
		physicalAddress.setAddress("http://someserver.com:8080/someservice/~selliott/sendmessge");
		groupAddress.setAddressId(timebasedIdGenerator.getNewId());
		groupAddress.setName("group name");
		
		
		testDeliveryAddress.setDeliveryAddressId(timebasedIdGenerator.getNewId());
		testDeliveryAddress.setAddress(physicalAddress);
	}
	
	
	@AfterClass
	public static void removeAddressFromDB() {
		SqlSession sqlSession = null;
		try{
			sqlSession = UCSDataConnection.getSqlSession();
			Connection  con = sqlSession.getConnection();
			Statement stmt = con.createStatement(); 
			stmt.execute("DELETE FROM delivery_address WHERE delivery_address_id = '"+testDeliveryAddress.getDeliveryAddressId()+"'");
			stmt.execute("DELETE FROM address WHERE address_id = '"+physicalAddress.getAddressId()+"'");
			stmt.execute("DELETE FROM address WHERE address_id = '"+groupAddress.getAddressId()+"'");
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
	
	@Test //Insert the DeliveryAddress
	public void test1() {
		UCSDto<DeliveryAddress> dto = new UCSDto<>();
		dto.getListT().add(testDeliveryAddress);
		UCSDataService.insertDeliveryAddress(dto);
		
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
	
	
	@Test //Select the Address
	public void test2() {
		UCSDto<DeliveryAddress> dto = new UCSDto<>();
		dto.getAdHocParams().put("deliveryAddressId",testDeliveryAddress.getDeliveryAddressId());
		UCSDataService.selectDeliveryAddressById(dto);

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
			DeliveryAddress retrievedAddr = dto.getListT().get(0);
			assertNotNull(retrievedAddr);
			assertNotNull(retrievedAddr.getAddress());
			assertNotNull(retrievedAddr.getPhysicalAddress());
		}
	}

	
	
	@Test //Update the Address
	public void test3() {
		//First test the current associated address
		UCSDto<DeliveryAddress> dto = new UCSDto<>();
		dto.getAdHocParams().put("deliveryAddressId",testDeliveryAddress.getDeliveryAddressId());
		UCSDataService.selectDeliveryAddressById(dto);
		//Check for errors
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
			DeliveryAddress retrievedAddr = dto.getListT().get(0);
			assertNotNull(retrievedAddr);
			assertNotNull(retrievedAddr.getAddress());
			assertNotNull(retrievedAddr.getPhysicalAddress());
			assertTrue(retrievedAddr.getPhysicalAddress().getAddressId().equals(physicalAddress.getAddressId()));
		}
		
		//Now update the DeliveryAddress to use the GroupAddress
		testDeliveryAddress.setAddress(groupAddress);
		dto = new UCSDto<>();
		dto.getListT().add(testDeliveryAddress);
		UCSDataService.updateDeliveryAddress(dto);
		//Check for errors
		failures = new StringBuffer();
		for(DTOMessageType msgType : dto.getMsgs().keySet()){
			if(msgType.equals(DTOMessageType.ERROR) || msgType.equals(DTOMessageType.WARN)){
				failures.append(dto.getMsgs().get(msgType));
			}
		}
		if(failures.length()!=0){
			fail(failures.toString());
		}
		
		//Now retieve the DeliveryAddress and check if the Address has been updated.
		dto = new UCSDto<>();
		dto.getAdHocParams().put("deliveryAddressId",testDeliveryAddress.getDeliveryAddressId());
		UCSDataService.selectDeliveryAddressById(dto);
		//Check for errors
		failures = new StringBuffer();
		for(DTOMessageType msgType : dto.getMsgs().keySet()){
			if(msgType.equals(DTOMessageType.ERROR) || msgType.equals(DTOMessageType.WARN)){
				failures.append(dto.getMsgs().get(msgType));
			}
		}
		if(failures.length()!=0){
			fail(failures.toString());
		}
		else{
			DeliveryAddress retrievedAddr = dto.getListT().get(0);
			assertNotNull(retrievedAddr);
			assertNotNull(retrievedAddr.getAddress());
			assertNotNull(retrievedAddr.getGroupAddress());
			assertTrue(retrievedAddr.getGroupAddress().getAddressId().equals(groupAddress.getAddressId()));
		}

	}

}
