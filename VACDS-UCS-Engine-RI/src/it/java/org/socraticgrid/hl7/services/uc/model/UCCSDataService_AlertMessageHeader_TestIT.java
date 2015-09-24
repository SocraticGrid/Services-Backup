/**
 * 
 */
package org.socraticgrid.hl7.services.uc.model;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Properties;

import org.apache.ibatis.session.SqlSession;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.socraticgrid.hl7.services.uc.db.MessageHeaderSelectType;
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

// So the MessageHeader can be inserted then selected then updated...ISUD w/o the D
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UCCSDataService_AlertMessageHeader_TestIT {

	static AlertMessageHeader testMessageHeader;

	static AlertStatus alertStatus = AlertStatus.New;
	static String serviceResponseAddress = "testUser@some.endpoint.com";
	static String subject = "Testing MessageHeader 1 2 3";
	static DeliveryAddress sender;

	static StringBuffer addressesToBeDeleted = new StringBuffer();
	static StringBuffer deliveryAddressesToBeDeleted = new StringBuffer();
	static StringBuffer recipientsToBeDeleted = new StringBuffer();
	
	static TimeBasedIdGenerator timebasedIdGenerator = new TimeBasedIdGenerator();
	
	@BeforeClass
	public static void init() throws FileNotFoundException, IOException {		

		System.out.println("\n\n****************************");
		System.out.println("************* RUNNING - UCCSDataService_AlertMessageHeader_TestIT");
		System.out.println("****************************\n\n");

		//We need an actual message impl to set the MessageId and MessageHeaderId
		AlertMessage alertMessage = new AlertMessage(timebasedIdGenerator.getNewId());
		
		testMessageHeader = alertMessage.getHeader();
		testMessageHeader.setMessageId(timebasedIdGenerator.getNewId());

		//Set an initial service response address
		testMessageHeader.setServiceResponseAddress(serviceResponseAddress);

		//Set some Properties
		URL url = Thread.currentThread().getContextClassLoader().getResource("Test-Properties.properties");
		Properties props = new Properties();
		props.load(new FileReader(url.getFile()));
		testMessageHeader.setProperties(props);
		
		//Get some initial recipients - 5
		testMessageHeader.setRecipientsList(TestUtil.buildRecipientList(5
				,recipientsToBeDeleted
				,deliveryAddressesToBeDeleted
				,addressesToBeDeleted));
				
		testMessageHeader.setAlertStatus(alertStatus);

		//Set an initial subject
		testMessageHeader.setSubject(subject);
		
		//Set an initial sender
		sender = TestUtil.buildDeliveryAddressList(1,deliveryAddressesToBeDeleted,
				addressesToBeDeleted).iterator().next();
		testMessageHeader.setSender(sender);
		
		//Setup some StatusByReceiver
		testMessageHeader.setStatusByReciever(TestUtil.buildStatusByReceiverMap(3,deliveryAddressesToBeDeleted,addressesToBeDeleted));
		
	}
	
	@AfterClass
	public static void removeElementsFromDB() {
		SqlSession sqlSession = null;
		try{
			sqlSession = UCSDataConnection.getSqlSession();
			Connection  con = sqlSession.getConnection();
			Statement stmt = con.createStatement(); 
			stmt.execute("DELETE FROM message_header_join_recipient WHERE message_header_id = '"+testMessageHeader.getMessageHeaderId()+"'");
			stmt.execute("DELETE FROM message_header_join_sender WHERE message_header_id = '"+testMessageHeader.getMessageHeaderId()+"'");
			stmt.execute("DELETE FROM message_header_join_sender WHERE message_header_id = '"+testMessageHeader.getMessageHeaderId()+"'");
			stmt.execute("DELETE FROM message_header_alert_status_join_delivery_address WHERE message_header_id = '"+testMessageHeader.getMessageHeaderId()+"'");
			stmt.execute("DELETE FROM message_header WHERE message_header_id = '"+testMessageHeader.getMessageHeaderId()+"'");
			stmt.execute("DELETE FROM recipient WHERE recipient_id IN ("+recipientsToBeDeleted+")");
			stmt.execute("DELETE FROM delivery_address WHERE delivery_address_id IN ("+deliveryAddressesToBeDeleted+")");
			stmt.execute("DELETE FROM address WHERE address_id IN ("+addressesToBeDeleted+")");

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
	
	@Test //Insert the MessageHeader
	public void test1() {
		UCSDto<AlertMessageHeader> dto = new UCSDto<>();
		dto.getListT().add(testMessageHeader);
		UCSDataService.insertMessageHeader(dto);
		
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
	
	
	@Test //Select the MessageHeader
	public void test2() {
		UCSDto<AlertMessageHeader> dto = new UCSDto<>();
		dto.getAdHocParams().put(MessageHeaderSelectType.BYID.getQueryId(),testMessageHeader.getMessageHeaderId());
		UCSDataService.selectMessageHeaderById(dto);

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
			
			AlertMessageHeader messageHeader = dto.getListT().get(0);
			assertTrue(messageHeader.getMessageType().equals(MessageType.Alert));
			assertTrue(messageHeader.getSender().getDeliveryAddressId().equals(sender.getDeliveryAddressId()));
			
			//Check the recipients list
			assertTrue( (messageHeader.getRecipientsList().size()==testMessageHeader.getRecipientsList().size()) );
			
			assertTrue(messageHeader.getServiceResponseAddress().equals(testMessageHeader.getServiceResponseAddress()));
			assertTrue(messageHeader.getAlertStatus()==testMessageHeader.getAlertStatus());
			assertTrue(messageHeader.getStatusByReciever().equals(testMessageHeader.getStatusByReciever()));

		}
	}

	
	
	@Test //Update the MessageHeader
	public void test3() throws FileNotFoundException, IOException {
		//Get a new subject
		String nuSubject = "Surprise - this is not the original subject";
		testMessageHeader.setSubject(nuSubject);
		
		//Update the sender
		DeliveryAddress nuSender = TestUtil.buildDeliveryAddressList(1,deliveryAddressesToBeDeleted,
				addressesToBeDeleted).iterator().next();
		testMessageHeader.setSender(nuSender);
		
		//Get a new list of recipients - 2
		int numRecipients = 2;
		testMessageHeader.setRecipientsList(TestUtil.buildRecipientList(numRecipients
				,recipientsToBeDeleted
				,deliveryAddressesToBeDeleted
				,addressesToBeDeleted));
		
		//Update the properties
		URL url = Thread.currentThread().getContextClassLoader().getResource("Test-Properties2.properties");
		Properties props = new Properties();
		props.load(new FileReader(url.getFile()));
		testMessageHeader.setProperties(props);
		
		//Do the update
		UCSDto<AlertMessageHeader> dto = new UCSDto<>();
		dto.getListT().add(testMessageHeader);
		UCSDataService.updateMessageHeader(dto);
		StringBuffer failures = new StringBuffer();
		for(DTOMessageType msgType : dto.getMsgs().keySet()){
			if(msgType.equals(DTOMessageType.ERROR) || msgType.equals(DTOMessageType.WARN)){
				failures.append(dto.getMsgs().get(msgType));
			}
		}
		if(failures.length()!=0){
			fail(failures.toString());
		}
		
		//Retrieve the updated MessageHeader
		dto = new UCSDto<>();
		dto.getAdHocParams().put(MessageHeaderSelectType.BYID.getQueryId(),testMessageHeader.getMessageHeaderId());
		UCSDataService.selectMessageHeaderById(dto);
		failures = new StringBuffer();
		for(DTOMessageType msgType : dto.getMsgs().keySet()){
			if(msgType.equals(DTOMessageType.ERROR) || msgType.equals(DTOMessageType.WARN)){
				failures.append(dto.getMsgs().get(msgType));
			}
		}
		if(failures.length()!=0){
			fail(failures.toString());
		}
		
		AlertMessageHeader messageHeader = dto.getListT().get(0);
		assertTrue(messageHeader.getMessageType().equals(MessageType.Alert));
		assertTrue(messageHeader.getSubject().equals(nuSubject));
		assertTrue(messageHeader.getSender().getDeliveryAddressId().equals(nuSender.getDeliveryAddressId()));
		assertTrue(messageHeader.getProperties().equals(props)); //Equality by Map.equals(Object o)
		
		//Check the recipients list
		assertTrue( (messageHeader.getRecipientsList().size()==numRecipients) );
		
		assertTrue(messageHeader.getServiceResponseAddress().equals(testMessageHeader.getServiceResponseAddress()));
		assertTrue(messageHeader.getAlertStatus()==testMessageHeader.getAlertStatus());
		assertTrue(messageHeader.getStatusByReciever().equals(testMessageHeader.getStatusByReciever()));
	}

}
