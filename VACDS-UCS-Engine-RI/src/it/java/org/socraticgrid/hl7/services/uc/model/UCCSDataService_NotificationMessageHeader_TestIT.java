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
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

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
public class UCCSDataService_NotificationMessageHeader_TestIT {

	static NotificationMessageHeader testMessageHeader;
	static Set<Recipient> recipients = new HashSet<>();
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
	static String subject = "Testing MessageHeader 1 2 3";
	static DeliveryAddress sender;
	
	static TimeBasedIdGenerator timebasedIdGenerator = new TimeBasedIdGenerator();
	
	@BeforeClass
	public static void init() throws FileNotFoundException, IOException {		

		System.out.println("\n\n****************************");
		System.out.println("************* RUNNING - UCCSDataService_NotificationMessageHeader_TestIT");
		System.out.println("****************************\n\n");

		//We need an actual message impl to set the MessageId and MessageHeaderId
		NotificationMessage notificationMessage = new NotificationMessage(timebasedIdGenerator.getNewId());
		
		testMessageHeader = notificationMessage.getHeader();
		testMessageHeader.setMessageId(timebasedIdGenerator.getNewId());

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
		recipients = TestUtil.buildRecipientList(5,recipientsToBeDeleted,deliveryAddressesToBeDeleted,addressesToBeDeleted);
		testMessageHeader.setRecipientsList(recipients);
		
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
		
		//Setup some VisibleReceivers
		testMessageHeader.setVisableReceivers(TestUtil.buildRecipientList(3,recipientsToBeDeleted,deliveryAddressesToBeDeleted,addressesToBeDeleted));
		
	}
	
	@AfterClass
	public static void removeElementsFromDB() {
		SqlSession sqlSession = null;
		try{
			sqlSession = UCSDataConnection.getSqlSession();
			Connection  con = sqlSession.getConnection();
			Statement stmt = con.createStatement(); 
			stmt.execute("DELETE FROM message_header_join_sender WHERE message_header_id = '"+testMessageHeader.getMessageHeaderId()+"'");
			stmt.execute("DELETE FROM message_header_join_recipient WHERE message_header_id = '"+testMessageHeader.getMessageHeaderId()+"'");
			stmt.execute("DELETE FROM message_header WHERE message_header_id = '"+testMessageHeader.getMessageHeaderId()+"'");
			stmt.execute("DELETE FROM recipient WHERE recipient_id IN ("+recipientsToBeDeleted+")");
			stmt.execute("DELETE FROM delivery_address WHERE delivery_address_id IN ("+deliveryAddressesToBeDeleted+")");
			//Clean up addresses
			stmt.execute("DELETE FROM user_contact_info_join_address WHERE user_contact_info_id = '"+userContactInfo.getUserContactInfoId()+"'");
			stmt.execute("DELETE FROM address WHERE address_id IN ("+addressesToBeDeleted+")");
			stmt.execute("DELETE FROM user_contact_info WHERE user_contact_info_id = '"+userContactInfo.getUserContactInfoId()+"'");
			stmt.execute("DELETE FROM address WHERE address_id = '"+userContactInfo.getPreferredAddress().getAddressId()+"'");
			//Clean up UserContactInfo

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
		UCSDto<NotificationMessageHeader> dto = new UCSDto<>();
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
	
	@Ignore("Issues with recipients - And general issues with Ids")
	@Test //Select the MessageHeader
	public void test2() {
		UCSDto<NotificationMessageHeader> dto = new UCSDto<>();
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
			
			NotificationMessageHeader messageHeader = dto.getListT().get(0);
			assertTrue(messageHeader.getMessageType().equals(MessageType.Notification));
			assertTrue(messageHeader.getSubject().equals(subject));
			assertTrue(messageHeader.getSender().getDeliveryAddressId().equals(sender.getDeliveryAddressId()));
			assertTrue(messageHeader.getProperties().equals(testMessageHeader.getProperties())); //Equality by Map.equals(Object o)
			
			//Check the recipients list
			assertTrue( (messageHeader.getRecipientsList().size()==recipients.size()) );

			//Check the UserContactInfo
			UserContactInfo uci =  messageHeader.getReplyTo();
			assertTrue( uci.getName().equals(userContactInfo.getName()) );
			assertTrue( uci.getEndpoint().equals(userContactInfo.getEndpoint()) );
			PhysicalAddress userContactPreferredAddress = uci.getPreferredAddress();
			assertTrue( userContactPreferredAddress.getAddressId().equals(prefAddress.getAddressId()) );
			assertTrue( userContactPreferredAddress.getAddress().equals(prefAddress.getAddress()) );
			assertTrue( userContactPreferredAddress.getAddressType().equals(prefAddress.getAddressType()) );

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
		
		//Get a new list of visibleReceivers - 1
		int numVisibleReceivers = 1;
		testMessageHeader.setVisableReceivers(TestUtil.buildRecipientList(numVisibleReceivers
					,recipientsToBeDeleted
					,deliveryAddressesToBeDeleted
					,addressesToBeDeleted));
		
		//Update the properties
		URL url = Thread.currentThread().getContextClassLoader().getResource("Test-Properties2.properties");
		Properties props = new Properties();
		props.load(new FileReader(url.getFile()));
		testMessageHeader.setProperties(props);
		
		//Do the update
		UCSDto<NotificationMessageHeader> dto = new UCSDto<>();
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
		
		NotificationMessageHeader messageHeader = dto.getListT().get(0);
		assertTrue(messageHeader.getMessageType().equals(MessageType.Notification));
		assertTrue(messageHeader.getSubject().equals(nuSubject));
		assertTrue(messageHeader.getSender().getDeliveryAddressId().equals(nuSender.getDeliveryAddressId()));
		assertTrue(messageHeader.getProperties().equals(props)); //Equality by Map.equals(Object o)
		
		//Check the recipients list
		assertTrue( (messageHeader.getRecipientsList().size()==numRecipients) );
		
		//Check the VisibleReceivers list
		assertTrue( (messageHeader.getVisableReceivers().size()==numVisibleReceivers) );

		//Check the UserContactInfo
		UserContactInfo uci =  messageHeader.getReplyTo();
		assertTrue( uci.getName().equals(userContactInfo.getName()) );
		assertTrue( uci.getEndpoint().equals(userContactInfo.getEndpoint()) );
		PhysicalAddress userContactPreferredAddress = uci.getPreferredAddress();
		assertTrue( userContactPreferredAddress.getAddressId().equals(prefAddress.getAddressId()) );
		assertTrue( userContactPreferredAddress.getAddress().equals(prefAddress.getAddress()) );
		assertTrue( userContactPreferredAddress.getAddressType().equals(prefAddress.getAddressType()) );
	}

	
}
