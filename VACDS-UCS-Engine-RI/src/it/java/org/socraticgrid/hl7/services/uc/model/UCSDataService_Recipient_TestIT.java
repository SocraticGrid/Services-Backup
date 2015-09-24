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

// So the Recipient can be inserted then selected then updated...ISUD w/o the D
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UCSDataService_Recipient_TestIT {

	static Recipient testRecipient = new Recipient();
	//Because Recipient references DeliveryAddress
	static DeliveryAddress testDeliveryAddress = new DeliveryAddress();
	//Because DeliveryAddress references Address
	static PhysicalAddress testAddress = new PhysicalAddress();
	
	static TimeBasedIdGenerator timebasedIdGenerator = new TimeBasedIdGenerator();
	
	@BeforeClass
	public static void init() {

		System.out.println("\n\n****************************");
		System.out.println("************* RUNNING - UCSDataService_Recipient_TestIT");
		System.out.println("****************************\n\n");

		testAddress.setAddressId(timebasedIdGenerator.getNewId());
		testAddress.setAddress("http://someserver.com:8080/someservice/~selliott/sendmessge");
		
		testDeliveryAddress.setDeliveryAddressId(timebasedIdGenerator.getNewId());
		testDeliveryAddress.setAddress(testAddress);
		
		testRecipient.setRecipientId(timebasedIdGenerator.getNewId());
		testRecipient.setDeliveryAddress(testDeliveryAddress);
		Role role = new Role();
		role.setName("role name");
		testRecipient.setRole(role); 
	}
	
	
	
	@AfterClass
	public static void removeAddressFromDB() {
		SqlSession sqlSession = null;
		try{
			sqlSession = UCSDataConnection.getSqlSession();
			Connection  con = sqlSession.getConnection();
			Statement stmt = con.createStatement(); 
			stmt.execute("DELETE FROM recipient WHERE recipient_id = '"+testRecipient.getRecipientId()+"'");
			stmt.execute("DELETE FROM delivery_address WHERE delivery_address_id = '"+testDeliveryAddress.getDeliveryAddressId()+"'");
			stmt.execute("DELETE FROM address WHERE address_id = '"+testAddress.getAddressId()+"'");
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
	
	@Test //Insert the Recipient, DeliveryAddress, Address
	public void test3() {
		
		
		UCSDto<Recipient> dto = new UCSDto<>();
		dto.getListT().add(testRecipient);
		UCSDataService.insertRecipient(dto);
		
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
	
	
	@Test //Select the Recipient
	public void test4() {
		UCSDto<Recipient> dto = new UCSDto<>();
		dto.getAdHocParams().put("recipientId",testRecipient.getRecipientId());
		UCSDataService.selectRecipientById(dto);

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
		}
	}

	
	
	@Test //Update the Recipient
	public void test5() {
		boolean deliveryReceipt = true;
		boolean readReceipt = true;
		Role role = new Role();
		role.setName("new Role");
		
		testRecipient.setDeliveryReceipt(deliveryReceipt);
		testRecipient.setReadReceipt(readReceipt);
		testRecipient.setRole(role);
		
		UCSDto<Recipient> dto = new UCSDto<>();
		dto.getListT().add(testRecipient);
		UCSDataService.updateRecipient(dto);
		
		dto = new UCSDto<>();
		dto.getAdHocParams().put("recipientId",testRecipient.getRecipientId());
		UCSDataService.selectRecipientById(dto);
		
		Recipient recipient = dto.getListT().get(0);
		assertTrue(deliveryReceipt == recipient.isDeliveryReceipt() );
		assertTrue(readReceipt == recipient.isReadReceipt() );
		assertTrue(role.getName().equals(recipient.getRole().getName()));

	}

}
