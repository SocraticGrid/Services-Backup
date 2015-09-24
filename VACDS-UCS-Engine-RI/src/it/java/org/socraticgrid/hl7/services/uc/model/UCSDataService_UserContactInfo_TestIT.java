/**
 * 
 */
package org.socraticgrid.hl7.services.uc.model;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Properties;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
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
public class UCSDataService_UserContactInfo_TestIT {


	static UserContactInfo userContactInfo;
	static Properties userContactInfoProps = new Properties();
	static{ 
		userContactInfoProps.setProperty("name","testUser ContactInfo");
		userContactInfoProps.setProperty("endpoint","testUser@some.endpoint.com");
	}
	static PhysicalAddress prefAddress;

	static StringBuffer deliveryAddressToBeDeleted = new StringBuffer();
	static StringBuffer addressesToBeDeleted = new StringBuffer();
	static StringBuffer channelsToBeDeleted = new StringBuffer();
	
	static TimeBasedIdGenerator timebasedIdGenerator = new TimeBasedIdGenerator();
	
	@BeforeClass
	public static void init() throws FileNotFoundException, IOException {				

		System.out.println("\n\n****************************");
		System.out.println("************* RUNNING - UCSDataService_UserContactInfo_TestIT");
		System.out.println("****************************\n\n");

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
		LinkedHashMap<String, PhysicalAddress> addressesByType = buildPhysicalAddressByTypeList(5);
		userContactInfo.setAddressesByType(addressesByType);
	}
	
	@AfterClass
	public static void removeElementsFromDB() {
//		SqlSession sqlSession = null;
//		try{
//			sqlSession = UCSDataConnection.getSqlSession();
//			Connection  con = sqlSession.getConnection();
//			Statement stmt = con.createStatement(); 
//			//Clean up addresses
//			stmt.execute("DELETE FROM user_contact_info_join_address WHERE user_contact_info_id = '"+userContactInfo.getUserContactInfoId()+"'");
//			stmt.execute("DELETE FROM conversation_join_conversation_channel WHERE conversation_id = '"+conversation.getConversationId()+"'");
//			stmt.execute("DELETE FROM conversation WHERE conversation_id = '"+conversation.getConversationId()+"'");
//			stmt.execute("DELETE FROM conversation_channel WHERE conversation_channel_id IN ("+channelsToBeDeleted+")");
//			stmt.execute("DELETE FROM address WHERE address_id IN ("+addressesToBeDeleted+")");
//			stmt.execute("DELETE FROM user_contact_info WHERE user_contact_info_id = '"+userContactInfo.getUserContactInfoId()+"'");
//			stmt.execute("DELETE FROM address WHERE address_id = '"+userContactInfo.getPreferredAddress().getAddressId()+"'");
//
//		}
//		catch(Exception e){
//			e.printStackTrace(System.err);
//		}
//		finally{
//			if(sqlSession!=null){ 
//				sqlSession.close(); 
//			}
//		}
	}
	
	@Test //Insert the Conversation
	public void test1() {
		UCSDto<UserContactInfo> dto = new UCSDto<>();
		dto.getListT().add(userContactInfo);
		UCSDataService.insertUserContactInfo(dto);
		
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
		UCSDto<UserContactInfo> dto = new UCSDto<>();
		dto.getAdHocParams().put("userContactInfoId",userContactInfo.getUserContactInfoId());
		UCSDataService.selectUserContactInfoById(dto);

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
			
			UserContactInfo savedContact = dto.getListT().get(0);
			assertTrue(savedContact.getUserContactInfoId().equals(userContactInfo.getUserContactInfoId()));
			assertTrue(savedContact.getAddressesByType().size() == userContactInfo.getAddressesByType().size());
			
		}
	}

	
	
//	@Test //Update the Conversation
//	public void test3() throws FileNotFoundException, IOException {
//		
//		//Do the update
//		UCSDto<Conversation> dto = new UCSDto<>();
//		dto.getListT().add(conversation);
//		UCSDataService.updateConversation(dto);
//		StringBuffer failures = new StringBuffer();
//		for(DTOMessageType msgType : dto.getMsgs().keySet()){
//			if(msgType.equals(DTOMessageType.ERROR) || msgType.equals(DTOMessageType.WARN)){
//				failures.append(dto.getMsgs().get(msgType));
//			}
//		}
//		if(failures.length()!=0){
//			fail(failures.toString());
//		}
//		
//		//Retrieve the updated Conversation
//		dto = new UCSDto<>();
//		dto.getAdHocParams().put("conversationId",conversation.getConversationId());
//		UCSDataService.selectConversationId(dto);
//		failures = new StringBuffer();
//		for(DTOMessageType msgType : dto.getMsgs().keySet()){
//			if(msgType.equals(DTOMessageType.ERROR) || msgType.equals(DTOMessageType.WARN)){
//				failures.append(dto.getMsgs().get(msgType));
//			}
//		}
//		if(failures.length()!=0){
//			fail(failures.toString());
//		}
//		
//		Conversation savedConversation = dto.getListT().get(0);
//		assertTrue(savedConversation.getConversationId().equals(conversation.getConversationId()));
//		assertTrue(savedConversation.getChannels().size() == conversation.getChannels().size());
//		assertTrue(savedConversation.getParticipants().size() == conversation.getParticipants().size());
//		
//		UserContactInfo savedContact = savedConversation.getOwner();
//		assertTrue(savedContact.getUserContactInfoId().equals(userContactInfo.getUserContactInfoId()));
//		assertTrue(savedContact.getAddresses().size() == userContactInfo.getAddresses().size());
//		
//	}

	
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

}
