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
import java.sql.Date;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
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
public class UCSDataService_ConversationRequestMessageHeader_TestIT {

	static ConversationRequestMessageHeader testMessageHeader;

	static Conversation conversation;
	static ConversationChannel conversationChannel;
	
	//Reply to UserContactInfo
	static UserContactInfo userContactInfo;
	static Properties userContactInfoProps = new Properties();
	static{ 
		userContactInfoProps.setProperty("name","testUser ContactInfo");
		userContactInfoProps.setProperty("endpoint","testUser@some.endpoint.com");
	}
	static PhysicalAddress prefAddress;

	static StringBuffer deliveryAddressToBeDeleted = new StringBuffer();
	static StringBuffer addressesToBeDeleted = new StringBuffer();
	static StringBuffer recipientsToBeDeleted = new StringBuffer();
	static StringBuffer channelsToBeDeleted = new StringBuffer();
	static String subject = "Testing MessageHeader 1 2 3";
	static DeliveryAddress sender;
	static int numRecipients = 5;
	
	static TimeBasedIdGenerator timebasedIdGenerator = new TimeBasedIdGenerator();
	
	@BeforeClass
	public static void init() throws FileNotFoundException, IOException {

		System.out.println("\n\n****************************");
		System.out.println("************* RUNNING - UCSDataService_ConversationRequestMessageHeader_TestIT");
		System.out.println("****************************\n\n");

		conversation = new Conversation();
		conversation.setConversationId(timebasedIdGenerator.getNewId());
		
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
		userContactInfo.setAddressesByType(buildPhysicalAddressByTypeList(5));
		
		conversation.setOwner(userContactInfo);
		
		//Add in some channels
		conversation.setChannels(buildConversationChannelList(5));
		
		//Add in some participants
		conversation.setParticipants(buildParticipantList(3));
		
		////Build the MessageHeader
		testMessageHeader = new ConversationRequestMessageHeader(timebasedIdGenerator.getNewId());
		testMessageHeader.setConversation(conversation);
		testMessageHeader.setRequestExpires(new Date(System.currentTimeMillis()));
		//Set an initial subject
		testMessageHeader.setSubject(subject);		
		//Set an initial sender
		sender = TestUtil.buildDeliveryAddressList(1,deliveryAddressToBeDeleted,
				addressesToBeDeleted).iterator().next();
		testMessageHeader.setSender(sender);

		testMessageHeader.setRequestExpires(new Date(System.currentTimeMillis()));
		//Set some Properties
		URL url = Thread.currentThread().getContextClassLoader().getResource("Test-Properties.properties");
		Properties props = new Properties();
		props.load(new FileReader(url.getFile()));
		testMessageHeader.setProperties(props);
		
		//Get some initial recipients - 5
		testMessageHeader.setRecipientsList(buildRecipientList(numRecipients));
		
	}
	
	@AfterClass
	public static void removeElementsFromDB() {
		SqlSession sqlSession = null;
		try{
			sqlSession = UCSDataConnection.getSqlSession();
			Connection  con = sqlSession.getConnection();
			Statement stmt = con.createStatement(); 
			//Clean up addresses
			stmt.execute("DELETE FROM message_header_join_sender WHERE message_header_id = '"+testMessageHeader.getMessageHeaderId()+"'");
			stmt.execute("DELETE FROM user_contact_info_join_address WHERE user_contact_info_id = '"+userContactInfo.getUserContactInfoId()+"'");
			stmt.execute("DELETE FROM conversation_join_conversation_channel WHERE conversation_id = '"+conversation.getConversationId()+"'");
			stmt.execute("DELETE FROM conversation_join_participant WHERE conversation_id = '"+conversation.getConversationId()+"'");
			stmt.execute("DELETE FROM message_header_join_recipient WHERE message_header_id = '"+testMessageHeader.getMessageHeaderId()+"'");
			stmt.execute("DELETE FROM message_header WHERE message_header_id = '"+testMessageHeader.getMessageHeaderId()+"'");
			stmt.execute("DELETE FROM conversation_channel WHERE conversation_channel_id IN ("+channelsToBeDeleted+")");
			stmt.execute("DELETE FROM conversation WHERE conversation_id = '"+conversation.getConversationId()+"'");
			stmt.execute("DELETE FROM user_contact_info WHERE user_contact_info_id = '"+userContactInfo.getUserContactInfoId()+"'");
			stmt.execute("DELETE FROM recipient WHERE recipient_id IN ("+recipientsToBeDeleted+")");
			stmt.execute("DELETE FROM delivery_address WHERE delivery_address_id IN ("+deliveryAddressToBeDeleted+")");
			stmt.execute("DELETE FROM address WHERE address_id = '"+userContactInfo.getPreferredAddress().getAddressId()+"'");
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
	
	@Test //Insert the Conversation
	public void test1() {
		UCSDto<ConversationRequestMessageHeader> dto = new UCSDto<>();
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
		UCSDto<ConversationRequestMessageHeader> dto = new UCSDto<>();
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
			
			ConversationRequestMessageHeader messageHeader = dto.getListT().get(0);
			assertTrue(messageHeader.getMessageType().equals(MessageType.ConversationRequest));
			assertTrue(messageHeader.getSubject().equals(subject));
			assertTrue(messageHeader.getSender().getDeliveryAddressId().equals(sender.getDeliveryAddressId()));
			assertTrue(messageHeader.getProperties().equals(testMessageHeader.getProperties())); //Equality by Map.equals(Object o)
			
			//Check the recipients list
			assertTrue( (messageHeader.getRecipientsList().size()==testMessageHeader.getRecipientsList().size()) );
			assertTrue( (messageHeader.getRequestExpires().getTime() == testMessageHeader.getRequestExpires().getTime()) );

			Conversation savedConversation = messageHeader.getConversation();
			assertTrue(savedConversation.getConversationId().equals(conversation.getConversationId()));
			List<ConversationChannel> channels = new ArrayList<>();
			channels.addAll(savedConversation.getChannels());
			channels.clear();
			channels.addAll(conversation.getChannels());
			
			assertTrue(savedConversation.getChannels().size() == conversation.getChannels().size());
			assertTrue(savedConversation.getParticipants().size() == conversation.getParticipants().size());
			
			UserContactInfo savedContact = savedConversation.getOwner();
			assertTrue(savedContact.getUserContactInfoId().equals(userContactInfo.getUserContactInfoId()));
			assertTrue(savedContact.getAddressesByType().size() == userContactInfo.getAddressesByType().size());
			
			//Check the participants
			assertTrue(savedConversation.getParticipants().size() == conversation.getParticipants().size());
			
		}
	}

	
	@Test //Update the Conversation
	public void test3() throws FileNotFoundException, IOException {
		//new list of participants
		conversation.getParticipants().clear();
		int numParticipants = 2;
		conversation.setParticipants(buildParticipantList(numParticipants));

		//change some elements of the owner
		String newEndpoint = "https://mail.google.com/mail/u/0/?ui=2&shva=1#inbox";
		conversation.getOwner().setEndpoint(newEndpoint);
		String newName = "Iwao AVE!";
		conversation.getOwner().setName(newName);
		
		//Do the update
		UCSDto<ConversationRequestMessageHeader> dto = new UCSDto<>();
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
		
		//Retrieve the updated Conversation
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
		
		ConversationRequestMessageHeader savedMessageHeader = dto.getListT().get(0);

		Conversation savedConversation = savedMessageHeader.getConversation();
		assertTrue(savedConversation.getConversationId().equals(conversation.getConversationId()));
		assertTrue(savedConversation.getChannels().size() == conversation.getChannels().size());
		assertTrue(savedConversation.getParticipants().size() == conversation.getParticipants().size());
		//Test the new list of participants
		assertTrue(savedConversation.getParticipants().size() == numParticipants);
		
		UserContactInfo savedContact = savedConversation.getOwner();
		assertTrue(savedContact.getUserContactInfoId().equals(userContactInfo.getUserContactInfoId()));
		assertTrue(savedContact.getAddressesByType().size() == userContactInfo.getAddressesByType().size());
		//Test the newName , newEndpoint
		assertTrue(newName.equals(savedContact.getName()));
		assertTrue(newEndpoint.equals(savedContact.getEndpoint()));
		
		//Check the recipients list
		assertTrue( (savedMessageHeader.getRecipientsList().size()==testMessageHeader.getRecipientsList().size()) );
		assertTrue( (savedMessageHeader.getRequestExpires().getTime() == testMessageHeader.getRequestExpires().getTime()) );
		
	}

	
	static Set<DeliveryAddress> buildParticipantList(int counter) {
		Set<DeliveryAddress> participants = new HashSet<>();
		for(int i=0; i<counter; i++){
			PhysicalAddress testAddress = new PhysicalAddress();
			testAddress.setAddressId(timebasedIdGenerator.getNewId());
			testAddress.setAddress(10+i+" http://someserver.com:8080/someservice/~selliott/sendmessge");
			testAddress.setServiceId(10+i+"");
			int comma = addressesToBeDeleted.length();
			addressesToBeDeleted.append(comma==0?"":",")
				.append("'").append(testAddress.getAddressId()).append("'");

			DeliveryAddress testDeliveryAddress = new DeliveryAddress();
			testDeliveryAddress.setDeliveryAddressId(timebasedIdGenerator.getNewId());
			testDeliveryAddress.setAddress(testAddress);
			comma = deliveryAddressToBeDeleted.length();
			deliveryAddressToBeDeleted.append(comma==0?"":",")
				.append("'").append(testDeliveryAddress.getDeliveryAddressId()).append("'");

			participants.add(testDeliveryAddress);

		}
		return participants;
	}
	
	static Set<PhysicalAddress> buildPhysicalAddressList(int counter) {
		Set<PhysicalAddress> addresses = new HashSet<>();
		for(int i=0; i<counter; i++){
			PhysicalAddress testAddress = new PhysicalAddress();
			testAddress.setAddressId(timebasedIdGenerator.getNewId());
			testAddress.setAddress(i+" http://someserver.com:8080/someservice/~selliott/sendmessge");
			addresses.add(testAddress);
			int comma = addressesToBeDeleted.length();
			addressesToBeDeleted.append(comma==0?"":",")
				.append("'").append(testAddress.getAddressId()).append("'");
		}
		return addresses;
	}
	
	static Set<ConversationChannel> buildConversationChannelList(int counter) {
		Set<ConversationChannel> channels = new HashSet<>();
		for(int i=0; i<counter; i++){
			ConversationChannel channel = new ConversationChannel();
			channel.setConversationChannelId(timebasedIdGenerator.getNewId());
			channels.add(channel);
			int comma = channelsToBeDeleted.length();
			channelsToBeDeleted.append(comma==0?"":",")
				.append("'").append(channel.getConversationChannelId()).append("'");
		}
		return channels;
	}

	
	
	static LinkedHashMap<String, PhysicalAddress> buildPhysicalAddressByTypeList(int counter) {
		LinkedHashMap<String, PhysicalAddress> addressesByType = new LinkedHashMap<>();
		for(int i=0; i<counter; i++){
			PhysicalAddress testAddress = new PhysicalAddress();
			testAddress.setAddressId(timebasedIdGenerator.getNewId());
			testAddress.setAddress(i+" http://someserver.com:8080/someservice/~selliott/sendmessge");
			testAddress.setServiceId("ServiceID "+i);
			String type = i==2?null:"Type "+i;
			addressesByType.put(type,testAddress);
			int comma = addressesToBeDeleted.length();
			addressesToBeDeleted.append(comma==0?"":",")
				.append("'").append(testAddress.getAddressId()).append("'");
		}
		return addressesByType;
	}

	
	static Set<Recipient> buildRecipientList(int counter) {
		Set<Recipient> recipients = new HashSet<>();
		for(int i=0; i<counter; i++){
			PhysicalAddress testAddress = new PhysicalAddress();
			testAddress.setAddressId(timebasedIdGenerator.getNewId());
			testAddress.setAddress("http://someserver.com:8080/someservice/~selliott/sendmessge");
			int comma = addressesToBeDeleted.length();
			addressesToBeDeleted.append("'").append(comma==0?"":",")
				.append(testAddress.getAddressId()).append("'");

			DeliveryAddress testDeliveryAddress = new DeliveryAddress();
			testDeliveryAddress.setDeliveryAddressId(timebasedIdGenerator.getNewId());
			testDeliveryAddress.setAddress(testAddress);
			comma = deliveryAddressToBeDeleted.length();
			deliveryAddressToBeDeleted.append(comma==0?"":",")
				.append("'").append(testDeliveryAddress.getDeliveryAddressId()).append("'");
		
			Recipient testRecipient = new Recipient();
			testRecipient.setRecipientId(timebasedIdGenerator.getNewId());
			testRecipient.setDeliveryAddress(testDeliveryAddress);
			Role role = new Role();
			role.setName("role name "+i);
			testRecipient.setRole(role); 
			recipients.add(testRecipient);
			comma = recipientsToBeDeleted.length();
			recipientsToBeDeleted.append(comma==0?"":",")
				.append("'").append(testRecipient.getRecipientId()).append("'");

		}
		return recipients;
	}

}