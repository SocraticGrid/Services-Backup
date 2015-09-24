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
import java.util.List;
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
public class UCSDataService_ConversationRequestMessage_TestIT {

	static ConversationRequestMessage testMessage;

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
		System.out.println("************* RUNNING - UCSDataService_ConversationRequestMessage_TestIT");
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
		userContactInfo.setAddressesByType(TestUtil.buildPhysicalAddressByTypeList(5,addressesToBeDeleted));
		
		conversation.setOwner(userContactInfo);
		
		//Add in some channels
		conversation.setChannels(TestUtil.buildConversationChannelList(5,channelsToBeDeleted));
		
		//Add in some participants
		conversation.setParticipants(TestUtil.buildDeliveryAddressList(3,deliveryAddressToBeDeleted,addressesToBeDeleted));
		
		testMessage = new ConversationRequestMessage(timebasedIdGenerator.getNewId());
		
		////Build the MessageHeader
		ConversationRequestMessageHeader testMessageHeader = testMessage.getHeader();
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
		testMessageHeader.setRecipientsList(TestUtil.buildRecipientList(numRecipients
				,recipientsToBeDeleted
				,deliveryAddressToBeDeleted
				,addressesToBeDeleted));
		
	}
	
	@AfterClass
	public static void removeElementsFromDB() {
		SqlSession sqlSession = null;
		try{
			sqlSession = UCSDataConnection.getSqlSession();
			Connection  con = sqlSession.getConnection();
			Statement stmt = con.createStatement(); 
			//Clean up addresses
			ConversationRequestMessageHeader testMessageHeader = testMessage.getHeader();
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
		UCSDto<ConversationRequestMessage> dto = new UCSDto<>();
		dto.getListT().add(testMessage);
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
	
	
	@Test //Select the MessageHeader
	public void test2() {
		UCSDto<ConversationRequestMessage> dto = new UCSDto<>();
		dto.getAdHocParams().put(MessageHeaderSelectType.BYMSGID.getQueryId(),testMessage.getHeader().getMessageHeaderId());
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
			
			ConversationRequestMessage savedMessage = dto.getListT().get(0);

			ConversationRequestMessageHeader messageHeader = savedMessage.getHeader();
			assertTrue(messageHeader.getMessageType().equals(MessageType.ConversationRequest));
			assertTrue(messageHeader.getSubject().equals(subject));
			assertTrue(messageHeader.getSender().getDeliveryAddressId().equals(sender.getDeliveryAddressId()));
			assertTrue(messageHeader.getProperties().equals(savedMessage.getHeader().getProperties())); //Equality by Map.equals(Object o)
			
			//Check the recipients list
			assertTrue( (messageHeader.getRecipientsList().size()==savedMessage.getHeader().getRecipientsList().size()) );
			assertTrue( (messageHeader.getRequestExpires().getTime() == savedMessage.getHeader().getRequestExpires().getTime()) );

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
		conversation.setParticipants(TestUtil.buildDeliveryAddressList(numParticipants,deliveryAddressToBeDeleted,addressesToBeDeleted));

		//change some elements of the owner
		String newEndpoint = "https://mail.google.com/mail/u/0/?ui=2&shva=1#inbox";
		conversation.getOwner().setEndpoint(newEndpoint);
		String newName = "Iwao AVE!";
		conversation.getOwner().setName(newName);
		
		//Do the update
		UCSDto<ConversationRequestMessage> dto = new UCSDto<>();
		dto.getListT().add(testMessage);
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
		
		//Retrieve the updated Conversation
		dto = new UCSDto<>();
		dto.getAdHocParams().put(MessageHeaderSelectType.BYMSGID.getQueryId(),testMessage.getHeader().getMessageHeaderId());
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

		ConversationRequestMessage savedMessage = dto.getListT().get(0);

		ConversationRequestMessageHeader savedMessageHeader = savedMessage.getHeader();

		Conversation savedConversation = savedMessageHeader.getConversation();
		assertTrue(savedConversation.getConversationId().equals(conversation.getConversationId()));
		assertTrue(savedConversation.getChannels().size() == conversation.getChannels().size());
		assertTrue(savedConversation.getParticipants().size() == conversation.getParticipants().size());
		
		UserContactInfo savedContact = savedConversation.getOwner();
		assertTrue(savedContact.getUserContactInfoId().equals(userContactInfo.getUserContactInfoId()));
		assertTrue(savedContact.getAddressesByType().size() == userContactInfo.getAddressesByType().size());
		//Test the newName , newEndpoint
		assertTrue(newName.equals(savedContact.getName()));
		assertTrue(newEndpoint.equals(savedContact.getEndpoint()));
		
		//Test the new list of participants
		assertTrue(savedConversation.getParticipants().size() == numParticipants);
	}

}
