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
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.session.SqlSession;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
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
public class UCSDataService_SimpleMessage_TestIT {

	static SimpleMessage simpleMessage;
	static List<Recipient> recipients = new ArrayList<>();
	//Because Recipient references DeliveryAddress
	static List<DeliveryAddress> deliveryAddresses = new ArrayList<>();
	//Because DeliveryAddress references Address
	static List<BaseAddress> addresses = new ArrayList<>();
	
	//Reply to UserContactInfo
	static UserContactInfo userContactInfo;
	static Properties userContactInfoProps = new Properties();
	static{ 
		userContactInfoProps.setProperty("name","testUser ContactInfo");
		userContactInfoProps.setProperty("endpoint","testUser@some.endpoint.com");
	}
	static PhysicalAddress prefAddress;

	static StringBuffer addressesToBeDeleted = new StringBuffer();
	static StringBuffer deliveryAddressesToBeDeleted = new StringBuffer();
	static StringBuffer recipientsToBeDeleted = new StringBuffer();
	static StringBuffer exceptionsToBeDeleted = new StringBuffer();
	static StringBuffer messageBodyToBeDeleted = new StringBuffer();
	static String comma = "";
	static String subject = "Testing MessageHeader 1 2 3";
	static DeliveryAddress sender;
	
	static TimeBasedIdGenerator timebasedIdGenerator = new TimeBasedIdGenerator();
	
	@BeforeClass
	public static void init() throws FileNotFoundException, IOException {		

		System.out.println("\n\n****************************");
		System.out.println("************* RUNNING - UCSDataService_SimpleMessage_TestIT");
		System.out.println("****************************\n\n");

		//We need an actual message impl to set the MessageId and MessageHeaderId
		simpleMessage = new SimpleMessage(timebasedIdGenerator.getNewId());
		
		SimpleMessageHeader testMessageHeader = simpleMessage.getHeader();

		
		//Set an initial subject
		testMessageHeader.setSubject(subject);
		
		//Set an initial sender
		sender = TestUtil.buildDeliveryAddressList(1,deliveryAddressesToBeDeleted,
				addressesToBeDeleted).iterator().next();
		testMessageHeader.setSender(sender);

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
		
		//Initialize the UserContactInfo
		userContactInfo = new UserContactInfo();
		userContactInfo.setUserContactInfoId(timebasedIdGenerator.getNewId());
		userContactInfo.setName(userContactInfoProps.getProperty("name"));
		userContactInfo.setEndpoint(userContactInfoProps.getProperty("endpoint"));
		prefAddress = new PhysicalAddress();
		prefAddress.setAddressId(timebasedIdGenerator.getNewId());
		prefAddress.setAddress("http://someserver.com:8080/someservice/~selliott/sendmessge");
		userContactInfo.setPreferredAddress(prefAddress);
		//Add in the availAddresses
		userContactInfo.setAddressesByType(TestUtil.buildPhysicalAddressByTypeList(5,addressesToBeDeleted));
		
		testMessageHeader.setReplyTo(userContactInfo);
		
		//Add in MessageBody
		simpleMessage.setParts(TestUtil.buildMessageBodys(2,messageBodyToBeDeleted));
		
		//Add in MessageBody
		simpleMessage.setExceptions(TestUtil.buildProcessingExceptionList(3,exceptionsToBeDeleted));
	}
	
	@AfterClass
	public static void removeElementsFromDB() {
		SqlSession sqlSession = null;
		try{
			SimpleMessageHeader testMessageHeader = simpleMessage.getHeader();
			sqlSession = UCSDataConnection.getSqlSession();
			Connection  con = sqlSession.getConnection();
			Statement stmt = con.createStatement(); 
			stmt.execute("DELETE FROM message_header_join_sender WHERE message_header_id = '"+testMessageHeader.getMessageHeaderId()+"'");
			stmt.execute("DELETE FROM message_join_message_body WHERE message_id = '"+testMessageHeader.getMessageId()+"'");
			stmt.execute("DELETE FROM message_join_processing_exception WHERE message_id = '"+testMessageHeader.getMessageId()+"'");
			stmt.execute("DELETE FROM message_header_join_recipient WHERE message_header_id = '"+testMessageHeader.getMessageHeaderId()+"'");
			stmt.execute("DELETE FROM message_header WHERE message_header_id = '"+testMessageHeader.getMessageHeaderId()+"'");
			stmt.execute("DELETE FROM recipient WHERE recipient_id IN ( "+recipientsToBeDeleted+" )");
			stmt.execute("DELETE FROM delivery_address WHERE delivery_address_id IN ("+deliveryAddressesToBeDeleted+")");
			//Clean up addresses
			stmt.execute("DELETE FROM user_contact_info_join_address WHERE user_contact_info_id = '"+userContactInfo.getUserContactInfoId()+"'");
			stmt.execute("DELETE FROM address WHERE address_id IN ("+addressesToBeDeleted+")");
			stmt.execute("DELETE FROM user_contact_info WHERE user_contact_info_id = '"+userContactInfo.getUserContactInfoId()+"'");
			stmt.execute("DELETE FROM address WHERE address_id = '"+userContactInfo.getPreferredAddress().getAddressId()+"'");
			stmt.execute("DELETE FROM message_body WHERE message_body_id IN ("+messageBodyToBeDeleted+")");
			stmt.execute("DELETE FROM processing_exception WHERE processing_exception_id IN ("+exceptionsToBeDeleted+")");

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
		UCSDto<SimpleMessage> dto = new UCSDto<>();
		dto.getListT().add(simpleMessage);
		UCSDataService.insertMessage(dto);
		
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
	
	@Ignore("Issues with recipients - And general issues with Ids")
	@Test //Select the MessageHeader
	public void test2() {
		UCSDto<SimpleMessage> dto = new UCSDto<>();
		dto.getAdHocParams().put(MessageHeaderSelectType.BYMSGID.getQueryId(),simpleMessage.getHeader().getMessageHeaderId());
		UCSDataService.selectMessage(dto);

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
			
			SimpleMessage savedMessage = dto.getListT().get(0);
			SimpleMessageHeader messageHeader = savedMessage.getHeader();
			assertTrue(messageHeader.getMessageType().equals(MessageType.SimpleMessage));
			assertTrue(messageHeader.getSubject().equals(simpleMessage.getHeader().getSubject()));
			assertTrue(messageHeader.getSender().equals(simpleMessage.getHeader().getSender()));
			assertTrue(messageHeader.getProperties().equals(simpleMessage.getHeader().getProperties())); //Equality by Map.equals(Object o)
			
			//Check the recipients list
			assertTrue( (messageHeader.getRecipientsList().size()==simpleMessage.getHeader().getRecipientsList().size()) );

			//Check the UserContactInfo
			UserContactInfo uci =  messageHeader.getReplyTo();
			assertTrue( uci.getName().equals(userContactInfo.getName()) );
			assertTrue( uci.getEndpoint().equals(userContactInfo.getEndpoint()) );
			PhysicalAddress userContactPreferredAddress = uci.getPreferredAddress();
			assertTrue( userContactPreferredAddress.getAddressId().equals(prefAddress.getAddressId()) );
			assertTrue( userContactPreferredAddress.getAddress().equals(prefAddress.getAddress()) );
			assertTrue( userContactPreferredAddress.getAddressType().equals(prefAddress.getAddressType()) );

			//Check the MessageBodys TODO: do more in depth MessageBody testing
			assertTrue( savedMessage.getParts().length == simpleMessage.getParts().length );
			
			//Check the ProcessingExceptions TODO: do more in depth ProcessingException testing
			assertTrue( savedMessage.getExceptions().size() == simpleMessage.getExceptions().size());
		}
	}

	
	@Ignore("Issues with recipients - And general issues with Ids")
	@Test //Update the MessageHeader
	public void test3() throws FileNotFoundException, IOException {
		//Get a new subject
		String nuSubject = "Surprise - this is not the original subject";
		simpleMessage.getHeader().setSubject(nuSubject);
		
		//Update the sender
		DeliveryAddress nuSender = TestUtil.buildDeliveryAddressList(1,deliveryAddressesToBeDeleted,
				addressesToBeDeleted).iterator().next();
		simpleMessage.getHeader().setSender(nuSender);
		
		//Get a new list of recipients - 2
		int numRecipients = 2;
		simpleMessage.getHeader().setRecipientsList(TestUtil.buildRecipientList(numRecipients
					,recipientsToBeDeleted
					,deliveryAddressesToBeDeleted
					,addressesToBeDeleted));
		
		//Update the properties
		URL url = Thread.currentThread().getContextClassLoader().getResource("Test-Properties2.properties");
		Properties props = new Properties();
		props.load(new FileReader(url.getFile()));
		simpleMessage.getHeader().setProperties(props);
		
		//Do the update
		UCSDto<SimpleMessage> dto = new UCSDto<>();
		dto.getListT().add(simpleMessage);
		UCSDataService.updateMessage(dto);
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
		dto.getAdHocParams().put(MessageHeaderSelectType.BYMSGID.getQueryId(),simpleMessage.getHeader().getMessageHeaderId());
		UCSDataService.selectMessage(dto);
		failures = new StringBuffer();
		for(DTOMessageType msgType : dto.getMsgs().keySet()){
			if(msgType.equals(DTOMessageType.ERROR) || msgType.equals(DTOMessageType.WARN)){
				failures.append(dto.getMsgs().get(msgType));
			}
		}
		if(failures.length()!=0){
			fail(failures.toString());
		}
		
		SimpleMessage savedMessage = dto.getListT().get(0);
		SimpleMessageHeader messageHeader = savedMessage.getHeader();
		assertTrue(messageHeader.getMessageType().equals(MessageType.SimpleMessage));
		assertTrue(messageHeader.getSubject().equals(nuSubject));
		assertTrue(messageHeader.getSender().getDeliveryAddressId().equals(nuSender.getDeliveryAddressId()));
		assertTrue(messageHeader.getProperties().equals(props)); //Equality by Map.equals(Object o)
		
		//Check the recipients list
		assertTrue( (messageHeader.getRecipientsList().size()==numRecipients) );

		//Check the UserContactInfo
		UserContactInfo uci =  messageHeader.getReplyTo();
		assertTrue( uci.getName().equals(userContactInfo.getName()) );
		assertTrue( uci.getEndpoint().equals(userContactInfo.getEndpoint()) );
		PhysicalAddress userContactPreferredAddress = uci.getPreferredAddress();
		assertTrue( userContactPreferredAddress.getAddressId().equals(prefAddress.getAddressId()) );
		assertTrue( userContactPreferredAddress.getAddress().equals(prefAddress.getAddress()) );
		assertTrue( userContactPreferredAddress.getAddressType().equals(prefAddress.getAddressType()) );

		//Check the MessageBodys TODO: do more in depth MessageBody testing
		assertTrue( savedMessage.getParts().length == simpleMessage.getParts().length );
		
		//Check the ProcessingExceptions TODO: do more in depth ProcessingException testing
		assertTrue( savedMessage.getExceptions().size() == simpleMessage.getExceptions().size());
	}

}
