/**
 * 
 */
package org.socraticgrid.hl7.services.uc.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.socraticgrid.hl7.services.uc.db.dto.DTOMessageType;
import org.socraticgrid.hl7.services.uc.db.dto.UCSDto;
import org.socraticgrid.hl7.services.uc.exceptions.PersistenceException;
import org.socraticgrid.hl7.services.uc.exceptions.ProcessingException;
import org.socraticgrid.hl7.services.uc.model.AddressPersistenceAdapter;
import org.socraticgrid.hl7.services.uc.model.AlertMessage;
import org.socraticgrid.hl7.services.uc.model.AlertMessageHeader;
import org.socraticgrid.hl7.services.uc.model.AlertStatus;
import org.socraticgrid.hl7.services.uc.model.BaseAddress;
import org.socraticgrid.hl7.services.uc.model.Conversation;
import org.socraticgrid.hl7.services.uc.model.ConversationChannel;
import org.socraticgrid.hl7.services.uc.model.ConversationRequestMessage;
import org.socraticgrid.hl7.services.uc.model.ConversationRequestMessageHeader;
import org.socraticgrid.hl7.services.uc.model.DeliveryAddress;
import org.socraticgrid.hl7.services.uc.model.Message;
import org.socraticgrid.hl7.services.uc.model.MessageBody;
import org.socraticgrid.hl7.services.uc.model.MessageBodyExceptionsDelegate;
import org.socraticgrid.hl7.services.uc.model.MessageHeader;
import org.socraticgrid.hl7.services.uc.model.MessageHeaderPersistenceAdapter;
import org.socraticgrid.hl7.services.uc.model.MessageType;
import org.socraticgrid.hl7.services.uc.model.NotificationMessage;
import org.socraticgrid.hl7.services.uc.model.NotificationMessageHeader;
import org.socraticgrid.hl7.services.uc.model.PhysicalAddress;
import org.socraticgrid.hl7.services.uc.model.Recipient;
import org.socraticgrid.hl7.services.uc.model.SimpleMessage;
import org.socraticgrid.hl7.services.uc.model.SimpleMessageHeader;
import org.socraticgrid.hl7.services.uc.model.UserContactInfo;
import org.socraticgrid.hl7.services.uc.model.type.RecipientType;

/**
 * @author steven
 * @created Jan 16, 2014

 */
public class UCSDataService {

	private static final Logger log = LoggerFactory.getLogger(UCSDataService.class);
	
	/*********** ADDRESS
	 * |: Address
	 */
	public static void selectAddressById(UCSDto<AddressPersistenceAdapter> dto){
		AddressPersistenceAdapter address = null;
		String id = dto.getAdHocParams().get("addressId");
		SqlSession sqlSession = null;
		try{
			sqlSession = UCSDataConnection.getSqlSession();
			address = sqlSession.selectOne("selectAddressById", id);
			if(address == null){
				dto.getMsgs().put(DTOMessageType.WARN, "An Address for ID = "+id+" could not be found.");
			}
			else {
				dto.getListT().add(address);
			}
		}
		catch(Exception e){
			dto.getMsgs().put(DTOMessageType.ERROR, "An error has occurred for Address ID = "+id+". Please check your parameters and resubmit."+
					" If you continue to receive errors please advise your system administrator.");
			log.error("******************* ERROR: ID = "+id+" errored with - "+e.toString(), e);
		}
		finally{
			if(sqlSession!=null){ 
				sqlSession.close(); 
			}
		}
	}
	public static void updateAddress(UCSDto<AddressPersistenceAdapter> dto){
		SqlSession sqlSession = null;
		try{
			sqlSession = UCSDataConnection.getSqlSession();
			for(AddressPersistenceAdapter address : dto.getListT()) {
				int updated = sqlSession.update("updateAddress", address);
				if(updated == 0){
					dto.getMsgs().put(DTOMessageType.WARN, "Address ID = "+address.getAddressId()+" could not be updated.");
				}
			}
			sqlSession.commit();
		}
		catch(Exception e){
			dto.getMsgs().put(DTOMessageType.ERROR, "An error has occurred for while updating addresses. Please check your Address and resubmit."+
					" If you continue to receive errors please advise your system administrator.");
			log.error("******************* ERROR: Address update errored with - "+e.toString(), e);
		}
		finally{
			if(sqlSession!=null){ 
				sqlSession.close(); 
			}
		}
	}
	public static void insertAddress(UCSDto<AddressPersistenceAdapter> dto){
		SqlSession sqlSession = null;
		try{
			sqlSession = UCSDataConnection.getSqlSession();
			for(AddressPersistenceAdapter address : dto.getListT()) {
				int updated = sqlSession.insert("insertAddress", address);
				if(updated == 0){
					dto.getMsgs().put(DTOMessageType.WARN, "Address ID = "+address.getAddressId()+" could not be inserted.");
				}
			}
			sqlSession.commit();
		}
		catch(Exception e){
			dto.getMsgs().put(DTOMessageType.ERROR, "An error has occurred for while inserting addresses. Please check your Address and resubmit."+
					" If you continue to receive errors please advise your system administrator.");
			log.error("******************* ERROR: Address update errored with - "+e.toString(), e);
		}
		finally{
			if(sqlSession!=null){ 
				sqlSession.close(); 
			}
		}
	}

	
	/*********** USER CONTACT INFO
	 * |: UserContactInfo
	 */
	public static void selectUserContactInfoById(UCSDto<UserContactInfo> dto){
		UserContactInfo userContactInfo = null;
		String id = dto.getAdHocParams().get("userContactInfoId");
		SqlSession sqlSession = null;
		try{
			sqlSession = UCSDataConnection.getSqlSession();
			userContactInfo = sqlSession.selectOne("selectUserContactInfoById", id);
			if(userContactInfo == null){
				dto.getMsgs().put(DTOMessageType.WARN, "A UserContactInfo for ID = "+id+" could not be found.");
			}
			else {
				dto.getListT().add(userContactInfo);
			}
		}
		catch(Exception e){
			dto.getMsgs().put(DTOMessageType.ERROR, "An error has occurred for UserContactInfo ID = "+id+". Please check your parameters and resubmit."+
					" If you continue to receive errors please advise your system administrator.");
			log.error("******************* ERROR: UserContactInfo ID = "+id+" errored with - "+e.toString(), e);
		}
		finally{
			if(sqlSession!=null){ 
				sqlSession.close(); 
			}
		}
	}
	public static void updateUserContactInfo(UCSDto<UserContactInfo> dto){
		SqlSession sqlSession = null;
		try{
			sqlSession = UCSDataConnection.getSqlSession();

			upsertUserContactInfo(dto, sqlSession, true);

			//Check to see if an ERROR or WARN was generated
			if(	dto.getMsgs().containsKey(DTOMessageType.ERROR)
				|| dto.getMsgs().containsKey(DTOMessageType.WARN) ) {
				
				dto.getMsgs().putAll(dto.getMsgs());
				//Abort and let the close rollback
				return;
			}

			//If no errors commit
			sqlSession.commit();
		}
		catch(Exception e){
			dto.getMsgs().put(DTOMessageType.ERROR, "An error has occurred for while updating UserContactInfo. Please check your UserContactInfo and resubmit."+
					" If you continue to receive errors please advise your system administrator.");
			log.error("******************* ERROR: UserContactInfo update errored with - "+e.toString(), e);
		}
		finally{
			if(sqlSession!=null){ 
				sqlSession.close(); 
			}
		}
	}
	public static void insertUserContactInfo(UCSDto<UserContactInfo> dto){
		SqlSession sqlSession = null;
		try{
			sqlSession = UCSDataConnection.getSqlSession();

			upsertUserContactInfo(dto, sqlSession, true);

			//Check to see if an ERROR or WARN was generated
			if(	dto.getMsgs().containsKey(DTOMessageType.ERROR)
				|| dto.getMsgs().containsKey(DTOMessageType.WARN) ) {
				
				dto.getMsgs().putAll(dto.getMsgs());
				//Abort and let the close rollback
				return;
			}

			//If no errors commit
			sqlSession.commit();
		}
		catch(Exception e){
			dto.getMsgs().put(DTOMessageType.ERROR, "An error has occurred for while inserting UserContactInfo. Please check your UserContactInfo and resubmit."+
					" If you continue to receive errors please advise your system administrator.");
			log.error("******************* ERROR: UserContactInfo update errored with - "+e.toString(), e);
		}
		finally{
			if(sqlSession!=null){ 
				sqlSession.close(); 
			}
		}
	}
	/**
	 * Will update UserContactInfo and update/insert the preferred Address.
	 * If insert == true then if the UserContactInfo does not exist it will be inserted.
	 * NOTE: IMPORTANT: The SqlSession and Transaction will need to be  controlled by the caller
	 * @param dto
	 */
	public static void upsertUserContactInfo(UCSDto<UserContactInfo> dto, SqlSession sqlSession, boolean insert){
		try{
			for(UserContactInfo userContactInfo : dto.getListT()) {
				//Do the addresses first - they are all PhysicalAddress otherwise construct a new list.
				for(PhysicalAddress address : userContactInfo.getAddressesByType().values()) {
					AddressPersistenceAdapter adapter = new AddressPersistenceAdapter(address);
					int updated = sqlSession.update("updateAddress", adapter);
					if(updated == 0 ){
						updated = sqlSession.insert("insertAddress", adapter);
					}
					if(updated == 0){
						dto.getMsgs().put(DTOMessageType.WARN, "Address ID = "+adapter.getAddressId()+" could not be inserted for UserContactInfo ID "
								+userContactInfo.getUserContactInfoId());
						//Abort and let the close rollback
						return;
					}
				}
				//Do the PreferredAddress
				AddressPersistenceAdapter adapter = new AddressPersistenceAdapter(userContactInfo.getPreferredAddress());
				int updated = sqlSession.update("updateAddress", adapter);
				if(updated == 0 ){
					updated = sqlSession.insert("insertAddress", adapter);
				}
				if(updated == 0){
					dto.getMsgs().put(DTOMessageType.WARN, "Address ID = "+adapter.getAddressId()+" could not be inserted for UserContactInfo ID "
							+userContactInfo.getUserContactInfoId());
					//Abort and let the close rollback
					return;
				}

				
				updated = sqlSession.update("updateUserContactInfo", userContactInfo);
				if(insert && updated == 0 ){
					updated = sqlSession.insert("insertUserContactInfo", userContactInfo);
				}
				if(updated == 0){
					dto.getMsgs().put(DTOMessageType.WARN, "UserContactInfo ID = "+userContactInfo.getUserContactInfoId()+" could not be updated.");
					//Abort and let the close rollback
					return;
				}
				
				//If all succeeded rebuild the user_contact_info_join_address table
				updated = sqlSession.delete("deleteForUserContactInfoId",userContactInfo.getUserContactInfoId());
				
				//Build the Recipient join table
				Map<String,String> params = new HashMap<>();
				Set<String> types = userContactInfo.getAddressesByType().keySet();
				for(String type : types){
					BaseAddress address = userContactInfo.getAddressesByType().get(type);
					params.clear();
					params.put("userContactInfoId",userContactInfo.getUserContactInfoId());
					params.put("addressId",address.getAddressId());
					params.put("type",type);
					updated = sqlSession.insert("insertUserContactInfoJoinAddress", params);
					if(updated == 0){
						dto.getMsgs().put(DTOMessageType.WARN, "UserContactInfo Join Address table could not be created for UserContactInfo Id = '"
								+userContactInfo.getUserContactInfoId()+"'");
						//Abort and let the close rollback
						return;
					}
				}
				
			}
		}
		catch(Exception e){
			dto.getMsgs().put(DTOMessageType.ERROR, "An error has occurred for while updating addresses. Please check your Address and resubmit."+
					" If you continue to receive errors please advise your system administrator.");
			log.error("******************* ERROR: Address update errored with - "+e.toString(), e);
		}
	}

	
	/*********** CONVERSATION
	 * |: Conversation
	 */
	public static void selectConversationId(UCSDto<Conversation> dto){
		Conversation conversation = null;
		String id = dto.getAdHocParams().get("conversationId");
		SqlSession sqlSession = null;
		try{
			sqlSession = UCSDataConnection.getSqlSession();
			conversation = sqlSession.selectOne("selectConversationById", id);
			if(conversation == null){
				dto.getMsgs().put(DTOMessageType.WARN, "A Conversation for ID = "+id+" could not be found.");
			}
			else {
				dto.getListT().add(conversation);
			}
		}
		catch(Exception e){
			dto.getMsgs().put(DTOMessageType.ERROR, "An error has occurred for Conversation ID = "+id+". Please check your parameters and resubmit."+
					" If you continue to receive errors please advise your system administrator.");
			log.error("******************* ERROR: ID = "+id+" errored with - "+e.toString(), e);
		}
		finally{
			if(sqlSession!=null){ 
				sqlSession.close(); 
			}
		}
	}
	public static void updateConversation(UCSDto<Conversation> dto){
		SqlSession sqlSession = null;
		try{
			sqlSession = UCSDataConnection.getSqlSession();

			upsertConversation(dto, sqlSession, true);

			//Check to see if an ERROR or WARN was generated
			if(	dto.getMsgs().containsKey(DTOMessageType.ERROR)
				|| dto.getMsgs().containsKey(DTOMessageType.WARN) ) {
				
				dto.getMsgs().putAll(dto.getMsgs());
				//Abort and let the close rollback
				return;
			}

			//If no errors commit
			sqlSession.commit();
		}
		catch(Exception e){
			dto.getMsgs().put(DTOMessageType.ERROR, "An error has occurred for while updating Conversation. Please check your Conversation and resubmit."+
					" If you continue to receive errors please advise your system administrator.");
			log.error("******************* ERROR: Conversation update errored with - "+e.toString(), e);
		}
		finally{
			if(sqlSession!=null){ 
				sqlSession.close(); 
			}
		}
	}
	public static void insertConversation(UCSDto<Conversation> dto){
		SqlSession sqlSession = null;
		try{
			sqlSession = UCSDataConnection.getSqlSession();

			upsertConversation(dto, sqlSession, true);

			//Check to see if an ERROR or WARN was generated
			if(	dto.getMsgs().containsKey(DTOMessageType.ERROR)
				|| dto.getMsgs().containsKey(DTOMessageType.WARN) ) {
				
				dto.getMsgs().putAll(dto.getMsgs());
				//Abort and let the close rollback
				return;
			}

			//If no errors commit
			sqlSession.commit();
		}
		catch(Exception e){
			dto.getMsgs().put(DTOMessageType.ERROR, "An error has occurred for while inserting Conversation. Please check your Conversation and resubmit."+
					" If you continue to receive errors please advise your system administrator.");
			log.error("******************* ERROR: Conversation update errored with - "+e.toString(), e);
		}
		finally{
			if(sqlSession!=null){ 
				sqlSession.close(); 
			}
		}
	}
	/**
	 * Will update Conversation and update/insert the Owner (UserContactInfo), ConversationChannels, Participants (Address).
	 * If insert == true then if the Conversation does not exist it will be inserted.
	 * NOTE: IMPORTANT: The SqlSession and Transaction will need to be  controlled by the caller
	 * @param dto
	 */
	public static void upsertConversation(UCSDto<Conversation> dto, SqlSession sqlSession, boolean insert){
		try{
			for(Conversation conversation : dto.getListT()) {
				//Do the Owner first
				if(conversation.getOwner()!=null){
					UCSDto<UserContactInfo> ownerDto = new UCSDto<>();
					ownerDto.getListT().add(conversation.getOwner());
					upsertUserContactInfo(ownerDto,sqlSession,true);
					//Check to see if an ERROR or WARN was generated
					if(	ownerDto.getMsgs().containsKey(DTOMessageType.ERROR)
						|| ownerDto.getMsgs().containsKey(DTOMessageType.WARN) ) {
						
						dto.getMsgs().putAll(ownerDto.getMsgs());
						//Abort and let the close rollback
						return;
					}
					else{
						//Get any INFO messages that might be generated
						dto.getMsgs().putAll(ownerDto.getMsgs());
					}
				}
				
				//Do the channels
				for(ConversationChannel channel : conversation.getChannels()) {
					int updated = sqlSession.update("updateConversationChannel", channel);
					if(updated == 0 ){
						updated = sqlSession.insert("insertConversationChannel", channel);
					}
					if(updated == 0){
						dto.getMsgs().put(DTOMessageType.WARN, "CoversationChannel ID = "+channel.getConversationChannelId()+" could not be inserted for Conversation ID "
								+conversation.getConversationId());
						//Abort and let the close rollback
						return;
					}
				}
				
				//Do the Participants
				UCSDto<DeliveryAddress> participantDto = new UCSDto<>();
				participantDto.getListT().addAll(conversation.getParticipants());
				upsertDeliveryAddress(participantDto, sqlSession, true);
				//Check to see if an ERROR or WARN was generated
				if(	participantDto.getMsgs().containsKey(DTOMessageType.ERROR)
					|| participantDto.getMsgs().containsKey(DTOMessageType.WARN) ) {
					
					dto.getMsgs().putAll(participantDto.getMsgs());
					//Abort and let the close rollback
					return;
				}
				else{
					//Get any INFO messages that might be generated
					dto.getMsgs().putAll(participantDto.getMsgs());
				}
				
				//Do the Conversation
				int updated = sqlSession.update("updateConversation", conversation);
				if(updated == 0 ){
					updated = sqlSession.insert("insertConversation", conversation);
				}
				if(updated == 0){
					dto.getMsgs().put(DTOMessageType.WARN, "Coversation ID = "+conversation.getConversationId()+" could not be inserted or updated. ");
					//Abort and let the close rollback
					return;
				}
				
				//Delete existing Conversation ConversationChannel joins
				sqlSession.delete("deleteForConversationIdConversationChannelJoin",conversation.getConversationId());
				
				//Delete existing Conversation Participant joins
				sqlSession.delete("deleteForConversationIdParticipantJoin",conversation.getConversationId());
				
				//Build the Conversation ConversationChannel join table
				Map<String,String> params = new HashMap<>();
				for(ConversationChannel channel : conversation.getChannels()){
					params.clear();
					params.put("conversationId",conversation.getConversationId());
					params.put("conversationChannelId",channel.getConversationChannelId());
					updated = sqlSession.insert("insertConversationJoinConversationChannel", params);
					if(updated == 0){
						dto.getMsgs().put(DTOMessageType.WARN, "Conversation-ConversationChannel Join table could not be created for Conversation Id = '"
								+ conversation.getConversationId()+"' for ConversationChannel Id = '"+channel.getConversationChannelId()+"'.");
						//Abort and let the close rollback
						return;
					}
				}
				
				//Build the Conversation Participants join table
				params = new HashMap<>();
				for(DeliveryAddress participant : conversation.getParticipants()){
					params.clear();
					params.put("conversationId",conversation.getConversationId());
					params.put("deliveryAddressId",participant.getDeliveryAddressId());
					updated = sqlSession.insert("insertConversationJoinParticipant", params);
					if(updated == 0){
						dto.getMsgs().put(DTOMessageType.WARN, "Conversation-Participant Join table could not be created for Conversation Id = '"
								+ conversation.getConversationId()+"' for ConversationChannel Id = '"+participant.getDeliveryAddressId()+"'.");
						//Abort and let the close rollback
						return;
					}
				}
				
			}
		}
		catch(Exception e){
			dto.getMsgs().put(DTOMessageType.ERROR, "An error has occurred for while updating Conversation. Please check your Conversation and resubmit."+
					" If you continue to receive errors please advise your system administrator.");
			log.error("******************* ERROR: Conversation update errored with - "+e.toString(), e);
		}
	}

	
	/*********** DELIVERY ADDRESS
	 * |: DeliveryAddress
	 */
	public static void selectDeliveryAddressById(UCSDto<DeliveryAddress> dto){
		DeliveryAddress address = null;
		String id = dto.getAdHocParams().get("deliveryAddressId");
		SqlSession sqlSession = null;
		try{
			sqlSession = UCSDataConnection.getSqlSession();
			address = sqlSession.selectOne("selectDeliveryAddressById", id);
			if(address == null){
				dto.getMsgs().put(DTOMessageType.WARN, "An DeliveryAddress for ID = "+id+" could not be found.");
			}
			else {
				dto.getListT().add(address);
			}
		}
		catch(Exception e){
			dto.getMsgs().put(DTOMessageType.ERROR, "An error has occurred for DeliveryAddress ID = "+id+". Please check your parameters and resubmit."+
					" If you continue to receive errors please advise your system administrator.");
			log.error("******************* ERROR: DeliveryAddress ID = "+id+" errored with - "+e.toString(), e);
		}
		finally{
			if(sqlSession!=null){ 
				sqlSession.close(); 
			}
		}
	}
	/**
	 * Will update the DeliveryAddress and update/insert Address
	 * in a single Transaction - rollback the entire DeliveryAddress/Address list if fail.
	 * @param dto
	 */
	public static void updateDeliveryAddress(UCSDto<DeliveryAddress> dto){
		SqlSession sqlSession = null;
		try{
			sqlSession = UCSDataConnection.getSqlSession();

			upsertDeliveryAddress(dto, sqlSession, true);

			//Check to see if an ERROR or WARN was generated
			if(	dto.getMsgs().containsKey(DTOMessageType.ERROR)
				|| dto.getMsgs().containsKey(DTOMessageType.WARN) ) {
				
				dto.getMsgs().putAll(dto.getMsgs());
				//Abort and let the close rollback
				return;
			}
			else{
				//Get any INFO messages that might be generated
				dto.getMsgs().putAll(dto.getMsgs());
			}

			//If no errors commit
			sqlSession.commit();
		}
		catch(Exception e){
			dto.getMsgs().put(DTOMessageType.ERROR, "An error has occurred for while updating DeliveryAddresses. Please check your DeliveryAddress and resubmit."+
					" If you continue to receive errors please advise your system administrator.");
			log.error("******************* ERROR: DeliveryAddress update errored with - "+e.toString(), e);
		}
		finally{
			if(sqlSession!=null){ 
				sqlSession.close(); 
			}
		}
	}
	/**
	 * Will update the DeliveryAddress and Address
	 * in a single Transaction - rollback the entire DeliveryAddress/Address list if fail.
	 * @param dto
	 */
	public static void insertDeliveryAddress(UCSDto<DeliveryAddress> dto){
		SqlSession sqlSession = null;
		try{
			sqlSession = UCSDataConnection.getSqlSession();

			upsertDeliveryAddress(dto, sqlSession, true);

			//Check to see if an ERROR or WARN was generated
			if(	dto.getMsgs().containsKey(DTOMessageType.ERROR)
				|| dto.getMsgs().containsKey(DTOMessageType.WARN) ) {
				
				dto.getMsgs().putAll(dto.getMsgs());
				//Abort and let the close rollback
				return;
			}
			else{
				//Get any INFO messages that might be generated
				dto.getMsgs().putAll(dto.getMsgs());
			}

			//If no errors commit
			sqlSession.commit();
		}
		catch(Exception e){
			dto.getMsgs().put(DTOMessageType.ERROR, "An error has occurred for while inserting DeliveryAddress. Please check your DeliveryAddress and resubmit."+
					" If you continue to receive errors please advise your system administrator.");
			log.error("******************* ERROR: DeliveryAddress update errored with - "+e.toString(), e);
		}
		finally{
			if(sqlSession!=null){ 
				sqlSession.close(); 
			}
		}
	}
	/**
	 * Will update DeliveryAddress and update/insert the Address.
	 * If insert == true then if the DeliveryAddress does not exist it will be inserted.
	 * NOTE: IMPORTANT: The SqlSession and Transaction will need to be  controlled by the caller
	 * @param dto
	 */
	public static void upsertDeliveryAddress(UCSDto<DeliveryAddress> dto, SqlSession sqlSession, boolean insert){
		try{
			for(DeliveryAddress address : dto.getListT()) {
				//Do the address first
				AddressPersistenceAdapter adapter = new AddressPersistenceAdapter(address.getAddress());
				int updated = sqlSession.update("updateAddress", adapter);
				if(updated == 0 ){
					updated = sqlSession.insert("insertAddress", adapter);
				}
				if(updated == 0){
					//TODO: Log this somewhere 
					dto.getMsgs().put(DTOMessageType.WARN, "Address ID = "+adapter.getAddressId()+" could not be inserted for DeliveryAddress ID "+address.getDeliveryAddressId());
					//Abort and let the close rollback
					return;
				}
				
				updated = sqlSession.update("updateDeliveryAddress", address);
				if(insert && updated == 0 ){
					updated = sqlSession.insert("insertDeliveryAddress", address);
				}
				if(updated == 0){
					dto.getMsgs().put(DTOMessageType.WARN, "DeliveryAddress ID = "+address.getDeliveryAddressId()+" could not be updated.");
					//Abort and let the close rollback
					return;
				}
			}
		}
		catch(Exception e){
			dto.getMsgs().put(DTOMessageType.ERROR, "An error has occurred for while updating DeliveryAddresses. Please check your DeliveryAddress and resubmit."+
					" If you continue to receive errors please advise your system administrator.");
			log.error("******************* ERROR: DeliveryAddress update errored with - "+e.toString(), e);
		}
	}
	
	/*********** MESSAGE BODY
	 * |: Message Body
	 */
	public static void selectMessageBodyById(UCSDto<MessageBody> dto){
		MessageBody messageBody = null;
		String id = dto.getAdHocParams().get("messageBodyId");
		SqlSession sqlSession = null;
		try{
			sqlSession = UCSDataConnection.getSqlSession();
			messageBody = sqlSession.selectOne("selectMessageBodyById", id);
			if(messageBody == null){
				dto.getMsgs().put(DTOMessageType.WARN, "A MessageBody for ID = "+id+" could not be found.");
			}
			else {
				dto.getListT().add(messageBody);
			}
		}
		catch(Exception e){
			dto.getMsgs().put(DTOMessageType.ERROR, "An error has occurred for MessageBody ID = "+id+". Please check your parameters and resubmit."+
					" If you continue to receive errors please advise your system administrator.");
			log.error("******************* ERROR: MessageBody ID = "+id+" errored with - "+e.toString(), e);
		}
		finally{
			if(sqlSession!=null){ 
				sqlSession.close(); 
			}
		}
	}
	public static void updateMessageBody(UCSDto<MessageBody> dto){
		SqlSession sqlSession = null;
		try{
			sqlSession = UCSDataConnection.getSqlSession();
				
			upsertMessageBody(dto, sqlSession, true);

			//Check to see if an ERROR or WARN was generated
			if(	dto.getMsgs().containsKey(DTOMessageType.ERROR)
				|| dto.getMsgs().containsKey(DTOMessageType.WARN) ) {
				
				dto.getMsgs().putAll(dto.getMsgs());
				//Abort and let the close rollback
				return;
			}
			else{
				//Get any INFO messages that might be generated
				dto.getMsgs().putAll(dto.getMsgs());
			}
			sqlSession.commit();
		}
		catch(Exception e){
			dto.getMsgs().put(DTOMessageType.ERROR, "An error has occurred for while updating MessageBody. Please check your MessageBody and resubmit."+
					" If you continue to receive errors please advise your system administrator.");
			log.error("******************* ERROR: MessageBody update errored with - "+e.toString(), e);
		}
		finally{
			if(sqlSession!=null){ 
				sqlSession.close(); 
			}
		}
	}
	public static void insertMessageBody(UCSDto<MessageBody> dto){
		SqlSession sqlSession = null;
		try{
			sqlSession = UCSDataConnection.getSqlSession();
				
			upsertMessageBody(dto, sqlSession, true);

			//Check to see if an ERROR or WARN was generated
			if(	dto.getMsgs().containsKey(DTOMessageType.ERROR)
				|| dto.getMsgs().containsKey(DTOMessageType.WARN) ) {
				
				dto.getMsgs().putAll(dto.getMsgs());
				//Abort and let the close rollback
				return;
			}
			else{
				//Get any INFO messages that might be generated
				dto.getMsgs().putAll(dto.getMsgs());
			}
			
			sqlSession.commit();
		}
		catch(Exception e){
			dto.getMsgs().put(DTOMessageType.ERROR, "An error has occurred for while inserting MessageBody. Please check your MessageBody and resubmit."+
					" If you continue to receive errors please advise your system administrator.");
			log.error("******************* ERROR: MessageBody update errored with - "+e.toString(), e);
		}
		finally{
			if(sqlSession!=null){ 
				sqlSession.close(); 
			}
		}
	}
		/**
		 * Will update MessageBody 
		 * If insert == true then if the MessageBody does not exist it will be inserted.
		 * NOTE: IMPORTANT: The SqlSession and Transaction will need to be  controlled by the caller
		 * @param dto
		 */
	public static void upsertMessageBody(UCSDto<MessageBody> dto, SqlSession sqlSession, boolean insert){
		try{
			for(MessageBody messageBody : dto.getListT()) {
				int updated = sqlSession.insert("updateMessageBody", messageBody);
				if(insert && updated == 0 ){
					updated = sqlSession.insert("insertMessageBody", messageBody);
				}
				if(updated == 0){
					dto.getMsgs().put(DTOMessageType.WARN, "MessageBody ID = "+messageBody.getMessageBodyId()+" could not be inserted.");
					//Abort and let the close rollback
					return;
				}
			}
		}
		catch(Exception e){
			dto.getMsgs().put(DTOMessageType.ERROR, "An error has occurred for while inserting MessageBody. Please check your MessageBody and resubmit."+
					" If you continue to receive errors please advise your system administrator.");
			log.error("******************* ERROR: MessageBody update errored with - "+e.toString(), e);
		}
	}
	
	/*********** MESSAGE HEADER
	 * |: Message Header
	 */
	public static <T extends MessageHeader> void selectMessageHeaderById(UCSDto<T> dto){
		selectMessageHeader(dto, MessageHeaderSelectType.BYID);
	}
	@SuppressWarnings("unchecked")
	public static <T extends MessageHeader> void selectMessageHeader(UCSDto<T> dto, MessageHeaderSelectType type){
		MessageHeaderPersistenceAdapter messageAdapter = null;
		String id = dto.getAdHocParams().get(type.getQueryId());

		SqlSession sqlSession = null;
		try{
			sqlSession = UCSDataConnection.getSqlSession();
			messageAdapter = sqlSession.selectOne(type.getQueryId(), id);
			if(messageAdapter == null){
				if(type.equals(MessageHeaderSelectType.BYID)){
					dto.getMsgs().put(DTOMessageType.WARN, "A MessageHeader for ID = "+id+" could not be found.");
				}
				else {
					dto.getMsgs().put(DTOMessageType.WARN, "A Message for ID = "+id+" could not be found.");
				}
			}
			else {
				dto.getListT().add((T)messageAdapter.buildMessageHeader());
			}
		}
		catch(Exception e){
			if(type.equals(MessageHeaderSelectType.BYID)){
				dto.getMsgs().put(DTOMessageType.ERROR, "An error has occurred for MessageHeader ID = "+id+". Please check your parameters and resubmit."+
					" If you continue to receive errors please advise your system administrator.");
			}
			else{
				dto.getMsgs().put(DTOMessageType.ERROR, "An error has occurred for Message ID = "+id+". Please check your parameters and resubmit."+
						" If you continue to receive errors please advise your system administrator.");
				}

			log.error("******************* ERROR: ID = "+id+" errored with - "+e.toString(), e);
		}
		finally{
			if(sqlSession!=null){ 
				sqlSession.close(); 
			}
		}
	}
	/**
	 * Will update MessageHeader and update/insert Recipient, DeliveryAddress and Address 
	 * in a single Transaction - rollback all MessageHeaders if fail.
	 * This has the potential of locking more than a couple of tables.
	 * @param dto
	 */
	public static void updateMessageHeader(UCSDto<? extends MessageHeader> dto){
		SqlSession sqlSession = null;
		try{
			sqlSession = UCSDataConnection.getSqlSession();

			upsertMessageHeader(dto, sqlSession, true);
			
			//Check to see if we should commit
			if(	! (dto.getMsgs().containsKey(DTOMessageType.ERROR)
				|| dto.getMsgs().containsKey(DTOMessageType.WARN) )) {
				
				sqlSession.commit();
			}
		}
		catch(Exception e){
			dto.getMsgs().put(DTOMessageType.ERROR, "An error has occurred for while inserting MessageHeader. Please check your MessageHeader and resubmit."+
					" If you continue to receive errors please advise your system administrator.");
			log.error("******************* ERROR: MessageHeader update errored with - "+e.toString(), e);
		}
		finally{
			if(sqlSession!=null){ 
				sqlSession.close(); 
			}
		}
	}
	public static void insertMessageHeader(UCSDto<? extends MessageHeader> dto){
		SqlSession sqlSession = null;
		try{
			sqlSession = UCSDataConnection.getSqlSession();
			upsertMessageHeader(dto, sqlSession, true);
			
			//Check to see if we should commit
			if(	! (dto.getMsgs().containsKey(DTOMessageType.ERROR)
				|| dto.getMsgs().containsKey(DTOMessageType.WARN) )) {
				
				sqlSession.commit();
			}
		}
		catch(Exception e){
			dto.getMsgs().put(DTOMessageType.ERROR, "An error has occurred for while inserting MessageHeader. Please check your MessageHeader and resubmit."+
					" If you continue to receive errors please advise your system administrator.");
			log.error("******************* ERROR: MessageHeader update errored with - "+e.toString(), e);
		}
		finally{
			if(sqlSession!=null){ 
				sqlSession.close(); 
			}
		}
	}
	/**
	 * This method is used by MessageHeader insert,update
	 * as well as Message insert,update
	 */
	static void upsertMessageHeader(UCSDto<? extends MessageHeader> dto, SqlSession sqlSession, boolean deleteJoins){
		
		for(MessageHeader messageHeader : dto.getListT()) {
			MessageHeaderPersistenceAdapter messageAdapter = new MessageHeaderPersistenceAdapter(messageHeader);
			int updated = 0;
			
			/**
			 * NotificationMessageHeader set a list of recipients that are
			 * VisibleReceivers.  This list is used to make the join in
			 * MessageHeaderJoinRecipient with a RecipientType = RecipientType:VISIBLE_RECEIVER
			 */
			List<String> visibleReceiverIds = new ArrayList<>();
			
			/**
			 * AlertMessageHeader set a list of DeliveryAddress that are
			 * matched to an AlertStatus.  This list is used to make the join in
			 * MessageHeaderAlertStatusJoinDeliveryAddress
			 */
			Map<DeliveryAddress, AlertStatus> statusByReceiver = new LinkedHashMap<>();

			/**
			 * Recipient and MessageHeader have a many-to-one relationship which is kept in the
			 * message_header_join_recipient table.  The reason for the join table vis-a-vis a
			 * message_header_id column in recipient is that it is easier to manage. For instance to "update"
			 * recipients:
			 * delete the association from the join table.
			 * update the recipients and or insert new recipients and their data
			 * remake the join table using the ids in the list
			 * 
			 * In this way the association doesn't touch the actual recipient (e.g. using deleted)
			 * otherwise it becomes problematic to find which recipients have been dropped from the 
			 * previous association and update their deleted to 1.
			 */
			UCSDto<Recipient> recipientDto = new UCSDto<>();
			List<Recipient> recipientList = new ArrayList<>();
			recipientList.addAll(messageAdapter.getRecipientsList());
			recipientDto.setListT(recipientList);
			upsertRecipient(recipientDto, sqlSession, true);
			
			//Check to see if an ERROR or WARN was generated
			if(	recipientDto.getMsgs().containsKey(DTOMessageType.ERROR)
				|| recipientDto.getMsgs().containsKey(DTOMessageType.WARN) ) {
				
				dto.getMsgs().putAll(recipientDto.getMsgs());
				//Abort and let the close rollback
				return;
			}
			else{
				//Get any INFO messages that might be generated
				dto.getMsgs().putAll(recipientDto.getMsgs());
			}
			
			/**
			 * Do the Sender
			 */
			UCSDto<DeliveryAddress> senderDto = new UCSDto<>();
			senderDto.getListT().add(messageAdapter.getSender());
			upsertDeliveryAddress(senderDto, sqlSession, true);
			
			//Check to see if an ERROR or WARN was generated
			if(	senderDto.getMsgs().containsKey(DTOMessageType.ERROR)
				|| senderDto.getMsgs().containsKey(DTOMessageType.WARN) ) {
				
				dto.getMsgs().putAll(senderDto.getMsgs());
				//Abort and let the close rollback
				return;
			}
			else{
				//Get any INFO messages that might be generated
				dto.getMsgs().putAll(senderDto.getMsgs());
			}

			/*****************************
			 * Do Implementation specific persistence
			 */
			if( messageHeader instanceof SimpleMessageHeader )
			{
				//Do UserContactInfo
				UserContactInfo userContactInfo = ((SimpleMessageHeader)messageHeader).getReplyTo();
				UCSDto<UserContactInfo> userContactDto = new UCSDto<>();
				userContactDto.getListT().add(userContactInfo);
				upsertUserContactInfo(userContactDto, sqlSession, true);
				//Check to see if an ERROR or WARN was generated
				if(	userContactDto.getMsgs().containsKey(DTOMessageType.ERROR)
					|| userContactDto.getMsgs().containsKey(DTOMessageType.WARN) ) {
					
					dto.getMsgs().putAll(userContactDto.getMsgs());
					//Abort and let the close rollback
					return;
				}else{
					//Get any INFO messages that might be generated
					dto.getMsgs().putAll(recipientDto.getMsgs());
				}
			}
			if( messageHeader instanceof NotificationMessageHeader )
			{
				//Do UserContactInfo
				UserContactInfo userContactInfo = ((NotificationMessageHeader)messageHeader).getReplyTo();
				UCSDto<UserContactInfo> userContactDto = new UCSDto<>();
				userContactDto.getListT().add(userContactInfo);
				upsertUserContactInfo(userContactDto, sqlSession, true);
				//Check to see if an ERROR or WARN was generated
				if(	userContactDto.getMsgs().containsKey(DTOMessageType.ERROR)
					|| userContactDto.getMsgs().containsKey(DTOMessageType.WARN) ) {
					
					dto.getMsgs().putAll(userContactDto.getMsgs());
					//Abort and let the close rollback
					return;
				}else{
					//Get any INFO messages that might be generated
					dto.getMsgs().putAll(recipientDto.getMsgs());
				}
				
				//Do VisibleReceivers
				List<Recipient> visibleReceivers = new ArrayList<>();
				visibleReceivers.addAll(((NotificationMessageHeader)messageHeader).getVisableReceivers());
				UCSDto<Recipient> dtoRecipient = new UCSDto<>();
				dtoRecipient.getListT().addAll(visibleReceivers);
				upsertRecipient(dtoRecipient, sqlSession, true);
				//Check to see if an ERROR or WARN was generated
				if(	dtoRecipient.getMsgs().containsKey(DTOMessageType.ERROR)
					|| dtoRecipient.getMsgs().containsKey(DTOMessageType.WARN) ) {
					
					dto.getMsgs().putAll(dtoRecipient.getMsgs());
					//Abort and let the close rollback
					return;
				}else{
					//Get any INFO messages that might be generated
					dto.getMsgs().putAll(dtoRecipient.getMsgs());
					//Build the Visible Receivers ID list
					for(Recipient visibleReceiver : visibleReceivers){
						visibleReceiverIds.add(visibleReceiver.getRecipientId());
					}
				}
			}
			if( messageHeader instanceof ConversationRequestMessageHeader )
			{
				Conversation conversation = ((ConversationRequestMessageHeader)messageHeader).getConversation();
				UCSDto<Conversation> conversationDto = new UCSDto<>();
				conversationDto.getListT().add(conversation);
				upsertConversation(conversationDto, sqlSession, true);
				//Check to see if an ERROR or WARN was generated
				if(	conversationDto.getMsgs().containsKey(DTOMessageType.ERROR)
					|| conversationDto.getMsgs().containsKey(DTOMessageType.WARN) ) {
					
					dto.getMsgs().putAll(conversationDto.getMsgs());
					//Abort and let the close rollback
					return;
				}else{
					//Get any INFO messages that might be generated
					dto.getMsgs().putAll(conversationDto.getMsgs());
				}
			}
			if( messageHeader instanceof AlertMessageHeader )
			{
				statusByReceiver = ((AlertMessageHeader)messageHeader).getStatusByReciever();
				UCSDto<DeliveryAddress> daDto;
				for(DeliveryAddress da : statusByReceiver.keySet()){
					daDto = new UCSDto<>();
					daDto.getListT().add(da);
					upsertDeliveryAddress(daDto, sqlSession, true);
					//Check to see if an ERROR or WARN was generated
					if(	daDto.getMsgs().containsKey(DTOMessageType.ERROR)
						|| daDto.getMsgs().containsKey(DTOMessageType.WARN) ) {
						
						dto.getMsgs().putAll(daDto.getMsgs());
						//Abort and let the close rollback
						return;
					}else{
						//Get any INFO messages that might be generated
						dto.getMsgs().putAll(daDto.getMsgs());
					}
				}
			}
				
			/*****************************
			 * EndOf Implementation specific persistence
			 */

			//Do the MessageHeader
			updated = sqlSession.insert("updateMessageHeader", messageAdapter);
			if(updated == 0){
				//Try inserting
				updated = sqlSession.insert("insertMessageHeader", messageAdapter);
			}
			if(updated == 0){
				dto.getMsgs().put(DTOMessageType.WARN, "MessageHeader ID = "+messageAdapter.getMessageHeaderId()+" could not be inserted.");
				//Abort and let the close rollback
				return;
			}
			
			//IMPORTANT: Delete existing joins for ALL Types in message_header_join_recipient
			if(deleteJoins) {
				//This will delete both Recipients and
				updated = sqlSession.delete("deleteMessageHeaderJoinRecipientAndReceiverByMessageHeaderId",messageAdapter.getMessageHeaderId());
				updated = sqlSession.delete("deleteMessageHeaderAlertStatusByMessageHeaderId",messageAdapter.getMessageHeaderId());
				updated = sqlSession.delete("deleteMessageHeaderJoinSenderByMessageHeaderId",messageAdapter.getMessageHeaderId());
			}
			
			//Build the Recipient join table
			Set<Recipient> recipients = messageAdapter.getRecipientsList();
			Map<String,String> params = new HashMap<>();
			for(Recipient recipient : recipients){
				params.clear();
				params.put("messageHeaderId",messageAdapter.getMessageHeaderId());
				params.put("recipientId",recipient.getRecipientId());
				params.put("recipientType",RecipientType.RECIPIENT.name());
				updated = sqlSession.insert("insertMessageHeaderJoinRecipient", params);
				if(updated == 0){
					dto.getMsgs().put(DTOMessageType.WARN, "MessageHeader-Recipient Join table could not be created for MessageHeader Id = '"
							+messageAdapter.getMessageHeaderId()+"'.");
					//Abort and let the close rollback
					return;
				}
			}
			
			//If they exist build the Visible Receivers into Recipient join table
			params = new HashMap<>();
			for(String visibleReceiverId : visibleReceiverIds){
				params.clear();
				params.put("messageHeaderId",messageAdapter.getMessageHeaderId());
				params.put("recipientId",visibleReceiverId);
				params.put("recipientType",RecipientType.VISIBLE_RECEIVER.name());
				updated = sqlSession.insert("insertMessageHeaderJoinRecipient", params);
				if(updated == 0){
					dto.getMsgs().put(DTOMessageType.WARN, "MessageHeader-Recipient Join table could not be created for VisibleReceiver Id '"
							+visibleReceiverId+"' for MessageHeader Id = '"+messageAdapter.getMessageHeaderId()+"'.");
					//Abort and let the close rollback
					return;
				}
			}
			
			//If they exist build the AlertStatus into Receivers join table
			params = new HashMap<>();
			for(DeliveryAddress da : statusByReceiver.keySet()){
				params.clear();
				params.put("messageHeaderId",messageAdapter.getMessageHeaderId());
				params.put("deliveryAddressId",da.getDeliveryAddressId());
				params.put("alertStatus",statusByReceiver.get(da).name());
				updated = sqlSession.insert("insertMessageHeaderAlertStatusJoin", params);
				if(updated == 0){
					dto.getMsgs().put(DTOMessageType.WARN, "MessageHeader-AlertStatus Join table could not be created for DeliveryAddress Id '"
							+da.getDeliveryAddressId()+"' for MessageHeader Id = '"+messageAdapter.getMessageHeaderId()+"'.");
					//Abort and let the close rollback
					return;
				}
			}
			
			//Join the sender
			params = new HashMap<>();
			params.put("messageHeaderId",messageAdapter.getMessageHeaderId());
			params.put("deliveryAddressId",messageAdapter.getSender().getDeliveryAddressId());
			updated = sqlSession.insert("insertMessageHeaderJoinSender", params);
			if(updated == 0){
				dto.getMsgs().put(DTOMessageType.WARN, "MessageHeader-Sender Join table could not be created for DeliveryAddress Id '"
						+messageAdapter.getSender().getDeliveryAddressId()+"' for MessageHeader Id = '"+messageAdapter.getMessageHeaderId()+"'.");
				//Abort and let the close rollback
				return;
			}

		}
		
		//Finally - all succeeds - commit
		sqlSession.commit();

	}
		
		
	/*********** RECIPIENT
	 * |: Recipient
	 */
	public static void selectRecipientById(UCSDto<Recipient> dto){
		Recipient recipient = null;
		String id = dto.getAdHocParams().get("recipientId");
		SqlSession sqlSession = null;
		try{
			sqlSession = UCSDataConnection.getSqlSession();
			recipient = sqlSession.selectOne("selectRecipientById", id);
			if(recipient == null){
				dto.getMsgs().put(DTOMessageType.WARN, "A Recipient for ID = "+id+" could not be found.");
			}
			else {
				dto.getListT().add(recipient);
			}
		}
		catch(Exception e){
			dto.getMsgs().put(DTOMessageType.ERROR, "An error has occurred for Recipient ID = "+id+". Please check your parameters and resubmit."+
					" If you continue to receive errors please advise your system administrator.");
			log.error("******************* ERROR: Recipient ID = "+id+" errored with - "+e.toString(), e);
		}
		finally{
			if(sqlSession!=null){ 
				sqlSession.close(); 
			}
		}
	}
	public static void updateRecipient(UCSDto<Recipient> dto){
		SqlSession sqlSession = null;
		try {
			sqlSession = UCSDataConnection.getSqlSession();
			upsertRecipient(dto, sqlSession, false);
			
			//Check to see if we should commit
			if(	! (dto.getMsgs().containsKey(DTOMessageType.ERROR)
				|| dto.getMsgs().containsKey(DTOMessageType.WARN) )) {
				
				sqlSession.commit();
			}
		}
		catch(Exception e){
			dto.getMsgs().put(DTOMessageType.ERROR, "An error has occurred for while updating Recipient. Please check your Recipient and resubmit."+
					" If you continue to receive errors please advise your system administrator.");
			log.error("******************* ERROR: Recipient update errored with - "+e.toString(), e);
		}
		finally{
			if(sqlSession!=null){ 
				sqlSession.close(); 
			}
		}

	}
	/**
	 * Will update Recipient and update/insert DeliveryAddress and Address.
	 * If insert == true then if the Recipient does not exist it will be inserted.
	 * NOTE: IMPORTANT: The SqlSession and Transaction will need to be  controlled by the caller
	 * @param dto
	 */
	public static void upsertRecipient(UCSDto<Recipient> dto, SqlSession sqlSession, boolean insert){
		for(Recipient recipient : dto.getListT()) {
			//Do the address first
			AddressPersistenceAdapter adapter = new AddressPersistenceAdapter(recipient.getDeliveryAddress().getAddress());
			int updated = sqlSession.insert("updateAddress", adapter);
			if(updated == 0 ){
				updated = sqlSession.insert("insertAddress", adapter);
			}
			if(updated == 0){
				//TODO: Log this somewhere 
				dto.getMsgs().put(DTOMessageType.WARN, "Address ID = "+adapter.getAddressId()+" could not be inserted for Recipient ID "+recipient.getRecipientId());
				//Abort and let the close rollback
				return;
			}
			
			//Do the DeliveryAddress
			DeliveryAddress deliveryAddress = recipient.getDeliveryAddress();
			updated = sqlSession.update("updateDeliveryAddress", deliveryAddress);
			if(updated == 0){
				//Try inserting
				updated = sqlSession.insert("insertDeliveryAddress", deliveryAddress);
			}
			if(updated == 0){
				dto.getMsgs().put(DTOMessageType.WARN, "DeliveryAddress ID = "+deliveryAddress.getDeliveryAddressId()+" could not be updated for Recipient ID "+recipient.getRecipientId());
				//Abort and let the close rollback
				return;
			}

			updated = sqlSession.update("updateRecipient", recipient);
			if(insert && updated == 0){
				//Try inserting only if insert is true
				updated = sqlSession.insert("insertRecipient", recipient);
			}
			if(updated == 0){
				dto.getMsgs().put(DTOMessageType.WARN, "Recipient ID = "+recipient.getRecipientId()+" could not be updated.");
				//Abort and let the close rollback
				return;
			}
		}
	}
	/**
	 * Will insert the Recipient , DeliveryAddress and Address
	 * in a single Transaction - rollback all if fail.
	 * @param dto
	 */
	public static void insertRecipient(UCSDto<Recipient> dto){
		SqlSession sqlSession = null;
		try {
			sqlSession = UCSDataConnection.getSqlSession();
			upsertRecipient(dto, sqlSession, true);
			
			//Check to see if we should commit
			if(	! (dto.getMsgs().containsKey(DTOMessageType.ERROR)
				|| dto.getMsgs().containsKey(DTOMessageType.WARN) )) {
				
				sqlSession.commit();
			}
		}
		catch(Exception e){
			dto.getMsgs().put(DTOMessageType.ERROR, "An error has occurred for while updating Recipient. Please check your Recipient and resubmit."+
					" If you continue to receive errors please advise your system administrator.");
			log.error("******************* ERROR: Recipient update errored with - "+e.toString(), e);
		}
		finally{
			if(sqlSession!=null){ 
				sqlSession.close(); 
			}
		}
	}


	
	/*********** MESSAGE
	 * |: Message
	 */
	public static void insertMessage(UCSDto<? extends Message> dto)
	{
		for(Message baseMessage : dto.getListT()){
			MessageHeader messaageHeader = baseMessage.getHeader();
			UCSDto<MessageHeader> messageHeaderDto = new UCSDto<>();
			messageHeaderDto.getListT().add(messaageHeader);

			insertMessageHeader(messageHeaderDto);
			dto.getMsgs().putAll(messageHeaderDto.getMsgs());
			
			//Check to see if we should do MessageBody and ProcessingExceptions
			if(	! (dto.getMsgs().containsKey(DTOMessageType.ERROR)
					|| dto.getMsgs().containsKey(DTOMessageType.WARN) )) 
			{
				Map<String,String> joinParams = new HashMap<>();
				SqlSession sqlSession = null;
				
				//Delete existing joins
				try{
					sqlSession = UCSDataConnection.getSqlSession();
					sqlSession.update("deleteMessageJoinProcessingExceptionForMessageId", messaageHeader.getMessageId());
					sqlSession.update("deleteMessageJoinMessageBodyForMessageId", messaageHeader.getMessageId());
				}
				catch(Exception e){
					log.error("******************* ERROR: MessageBody/Processing Exception Join delete errored with - "+e.toString(), e);
				}
				finally{
					if(sqlSession!=null){ 
						sqlSession.close(); 
					}
				}

				//Do the MessageBody(s)
				MessageBody[] parts = baseMessage.getParts();
				if(parts != null && parts.length>0){
					try{
						sqlSession = UCSDataConnection.getSqlSession();
						UCSDto<MessageBody> messageBodyDto;
						for(MessageBody part : parts){
							messageBodyDto = new UCSDto<>();
							messageBodyDto.getListT().add(part);
							upsertMessageBody(messageBodyDto, sqlSession, true);
							//Check to see if we should commit
							if(	(messageBodyDto.getMsgs().containsKey(DTOMessageType.ERROR)
									|| messageBodyDto.getMsgs().containsKey(DTOMessageType.WARN) )) {

								dto.getMsgs().putAll(messageHeaderDto.getMsgs());
								throw new PersistenceException("MessageBody '"+part.getMessageBodyId()+"' for Message '"+part.getMessageId()
										+"' could not be persisted!");
							}

							//Do the join
							joinParams.clear();
							joinParams.put("messageId",baseMessage.getHeader().getMessageId());
							joinParams.put("messageBodyId",part.getMessageBodyId());					
							if(1 == sqlSession.update("insertMessageJoinMessageBody", joinParams)) {
								//Finally
								sqlSession.commit();
							}
						}
					}
					catch(Exception e){
						log.error("******************* ERROR: MessageBody update errored with - "+e.toString(), e);
					}
					finally{
						if(sqlSession!=null){ 
							sqlSession.close(); 
						}
					}
				}

				List<ProcessingException> exceptions = baseMessage.getExceptions();
				sqlSession = null;
				try{
					sqlSession = UCSDataConnection.getSqlSession();
					UCSDto<ProcessingException> exceptionDto;
					for(ProcessingException exception : exceptions) {
						exceptionDto = new UCSDto<>();
						exceptionDto.getListT().add(exception);
						upsertProcessingException(exceptionDto, sqlSession, true);
						dto.getMsgs().putAll(exceptionDto.getMsgs());
						//Check to see if we should commit
						if(	(exceptionDto.getMsgs().containsKey(DTOMessageType.ERROR)
								|| exceptionDto.getMsgs().containsKey(DTOMessageType.WARN) )) {

							dto.getMsgs().putAll(messageHeaderDto.getMsgs());
							throw new PersistenceException("ProcessingException '"+exception.getProcessingExceptionId()+"' for Message '"
									+baseMessage.getHeader().getMessageId()+"' could not be persisted!");
						}
						//Do the join
						joinParams.clear();
						joinParams.put("messageId",baseMessage.getHeader().getMessageId());
						joinParams.put("processingExceptionId",exception.getProcessingExceptionId());
						if(1 == sqlSession.update("insertMessageJoinProcessingException", joinParams)) {
							//Finally
							sqlSession.commit();
						}

					}
				}
				catch(Exception e){
					log.error("******************* ERROR: ProcessingException update errored with - "+e.toString(), e);
				}
				finally{
					if(sqlSession!=null){ 
						sqlSession.close(); 
					}
				}
			}
		}
	}
	public static void updateMessage(UCSDto<? extends Message> dto)
	{
		for(Message baseMessage : dto.getListT()){
			MessageHeader messaageHeader = baseMessage.getHeader();
			UCSDto<MessageHeader> messageHeaderDto = new UCSDto<>();
			messageHeaderDto.getListT().add(messaageHeader);

			updateMessageHeader(messageHeaderDto);
			dto.getMsgs().putAll(messageHeaderDto.getMsgs());
		}
	}
	@SuppressWarnings("unchecked")
	public static <T extends Message> void selectMessage(UCSDto<T> dto)
	{
			UCSDto<MessageHeader> simpleDto = new UCSDto<>();
			simpleDto.getAdHocParams().putAll(dto.getAdHocParams());

			selectMessageHeader(simpleDto, MessageHeaderSelectType.BYMSGID);
			dto.getMsgs().putAll(simpleDto.getMsgs());
						

			if(simpleDto.getListT().size()>0) {
				
				// Get the MessageBody and ProcessingExceptions
				String messageHeaderId = simpleDto.getListT().get(0).getMessageHeaderId();
				UCSDto<MessageBodyExceptionsDelegate> msgExDelegate = new UCSDto<>();
				msgExDelegate.getAdHocParams().put("messageId",messageHeaderId);
				selectMessageBodyExceptionsDelegateByMessageId(msgExDelegate);
				MessageBodyExceptionsDelegate delegate = null;
				if(msgExDelegate.getListT().size()>0){
					delegate = msgExDelegate.getListT().get(0); //should only be one
				}

				MessageType messageType = simpleDto.getListT().get(0).getMessageType();
				switch(messageType)
				{
					case SimpleMessage:
						for(MessageHeader header : simpleDto.getListT()) {
							SimpleMessageHeader simpleHeader = (SimpleMessageHeader)header;
							SimpleMessage message = new SimpleMessage(simpleHeader);
							if(delegate != null){
								message.setExceptions(delegate.getExceptionsList());
								message.setParts(delegate.getPartsArray());
							}
							dto.getListT().add((T)message);
						}
						break;
						
					case Notification:
						for(MessageHeader header : simpleDto.getListT()) {
							NotificationMessageHeader notificationHeader = (NotificationMessageHeader)header;
							NotificationMessage message = new NotificationMessage(notificationHeader);
							if(delegate != null){
								message.setExceptions(delegate.getExceptionsList());
								message.setParts(delegate.getPartsArray());
							}
							dto.getListT().add((T)message);
						}
						break;
						
					case ConversationRequest:
						for(MessageHeader header : simpleDto.getListT()) {
							ConversationRequestMessageHeader conversationHeader = (ConversationRequestMessageHeader)header;
							ConversationRequestMessage message = new ConversationRequestMessage(conversationHeader);
							if(delegate != null){
								message.setExceptions(delegate.getExceptionsList());
								message.setParts(delegate.getPartsArray());
							}
							dto.getListT().add((T)message);
						}
						break;
						
					case Alert:
						for(MessageHeader header : simpleDto.getListT()) {
							AlertMessageHeader alertHeader = (AlertMessageHeader)header;
							AlertMessage message = new AlertMessage(alertHeader);
							if(delegate != null){
								message.setExceptions(delegate.getExceptionsList());
								message.setParts(delegate.getPartsArray());
							}
							dto.getListT().add((T)message);
						}
						break;
						
					default:break;
				}
			}
	}
	
	
	
	/*********** MESSAGE BODY EXCEPTION DELEGATE
	 * |: MessageBodyExceptionDelegate
	 */
	public static void selectMessageBodyExceptionsDelegateByMessageId(UCSDto<MessageBodyExceptionsDelegate> dto){
		MessageBodyExceptionsDelegate delegate = null;
		String id = dto.getAdHocParams().get("messageId");
		SqlSession sqlSession = null;
		try{
			sqlSession = UCSDataConnection.getSqlSession();
			delegate = sqlSession.selectOne("selectMessageBodyAndExceptionsByMessageId", id);
			if(delegate == null){
//				dto.getMsgs().put(DTOMessageType.WARN, "An MessageBodyExceptionsDelegate for MessageHeader ID = "+id+" could not be found.");
			}
			else {
				dto.getListT().add(delegate);
			}
		}
		catch(Exception e){
			dto.getMsgs().put(DTOMessageType.ERROR, "An error has occurred for MessageBody / Exceptions delegate ID = "+id+". Please check your parameters and resubmit."+
					" If you continue to receive errors please advise your system administrator.");
			log.error("******************* ERROR: ID = "+id+" errored with - "+e.toString(), e);
		}
		finally{
			if(sqlSession!=null){ 
				sqlSession.close(); 
			}
		}
	}
	
	
	/*********** PROCESSING EXCEPTION
	 * |: ProcessingException
	 */
	public static void selectProcessingExceptionById(UCSDto<ProcessingException> dto){
		ProcessingException processingException = null;
		String id = dto.getAdHocParams().get("processingExceptionId");
		SqlSession sqlSession = null;
		try{
			sqlSession = UCSDataConnection.getSqlSession();
			processingException = sqlSession.selectOne("selectProcessingExceptionById", id);
			if(processingException == null){
				dto.getMsgs().put(DTOMessageType.WARN, "A ProcessingException for ID = "+id+" could not be found.");
			}
			else {
				dto.getListT().add(processingException);
			}
		}
		catch(Exception e){
			dto.getMsgs().put(DTOMessageType.ERROR, "An error has occurred for ProcessingException ID = "+id+". Please check your parameters and resubmit."+
					" If you continue to receive errors please advise your system administrator.");
			log.error("******************* ERROR: ProcessingException ID = "+id+" errored with - "+e.toString(), e);
		}
		finally{
			if(sqlSession!=null){ 
				sqlSession.close(); 
			}
		}
	}
	public static void updateProcessingException(UCSDto<ProcessingException> dto){
		SqlSession sqlSession = null;
		try{
			sqlSession = UCSDataConnection.getSqlSession();
				
			upsertProcessingException(dto, sqlSession, true);

			//Check to see if an ERROR or WARN was generated
			if(	dto.getMsgs().containsKey(DTOMessageType.ERROR)
				|| dto.getMsgs().containsKey(DTOMessageType.WARN) ) {
				
				dto.getMsgs().putAll(dto.getMsgs());
				//Abort and let the close rollback
				return;
			}
			else{
				//Get any INFO messages that might be generated
				dto.getMsgs().putAll(dto.getMsgs());
			}
			sqlSession.commit();
		}
		catch(Exception e){
			dto.getMsgs().put(DTOMessageType.ERROR, "An error has occurred for while updating ProcessingException. Please check your ProcessingException and resubmit."+
					" If you continue to receive errors please advise your system administrator.");
			log.error("******************* ERROR: MessageBody update errored with - "+e.toString(), e);
		}
		finally{
			if(sqlSession!=null){ 
				sqlSession.close(); 
			}
		}
	}
	public static void insertProcessingException(UCSDto<ProcessingException> dto){
		SqlSession sqlSession = null;
		try{
			sqlSession = UCSDataConnection.getSqlSession();
				
			upsertProcessingException(dto, sqlSession, true);

			//Check to see if an ERROR or WARN was generated
			if(	dto.getMsgs().containsKey(DTOMessageType.ERROR)
				|| dto.getMsgs().containsKey(DTOMessageType.WARN) ) {
				
				dto.getMsgs().putAll(dto.getMsgs());
				//Abort and let the close rollback
				return;
			}
			else{
				//Get any INFO messages that might be generated
				dto.getMsgs().putAll(dto.getMsgs());
			}
			
			sqlSession.commit();
		}
		catch(Exception e){
			dto.getMsgs().put(DTOMessageType.ERROR, "An error has occurred for while inserting ProcessingException. Please check your ProcessingException and resubmit."+
					" If you continue to receive errors please advise your system administrator.");
			log.error("******************* ERROR: ProcessingException update errored with - "+e.toString(), e);
		}
		finally{
			if(sqlSession!=null){ 
				sqlSession.close(); 
			}
		}
	}
	/**
	 * Will update ProcessingException 
	 * If insert == true then if the ProcessingException does not exist it will be inserted.
	 * NOTE: IMPORTANT: The SqlSession and Transaction will need to be  controlled by the caller
	 * @param dto
	 */
	public static void upsertProcessingException(UCSDto<ProcessingException> dto, SqlSession sqlSession, boolean insert){
		try{
			for(ProcessingException processingException : dto.getListT()) {
				int updated = sqlSession.insert("updateProcessingException", processingException);
				if(insert && updated == 0 ){
					updated = sqlSession.insert("insertProcessingException", processingException);
				}
				if(updated == 0){
					dto.getMsgs().put(DTOMessageType.WARN, "ProcessingException ID = "+processingException.getProcessingExceptionId()+" could not be inserted.");
					//Abort and let the close rollback
					return;
				}
			}
		}
		catch(Exception e){
			dto.getMsgs().put(DTOMessageType.ERROR, "An error has occurred for while inserting MessageBody. Please check your MessageBody and resubmit."+
					" If you continue to receive errors please advise your system administrator.");
			log.error("******************* ERROR: MessageBody update errored with - "+e.toString(), e);
		}
	}

}
