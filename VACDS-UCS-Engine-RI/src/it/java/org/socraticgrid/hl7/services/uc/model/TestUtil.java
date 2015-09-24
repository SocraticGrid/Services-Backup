/**
 * 
 */
package org.socraticgrid.hl7.services.uc.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.socraticgrid.hl7.services.uc.exceptions.ExceptionType;
import org.socraticgrid.hl7.services.uc.exceptions.ProcessingException;
import org.socraticgrid.hl7.services.uc.internal.idgenerators.TimeBasedIdGenerator;

/**
 * @author steven
 * Feb 9, 2014
 *
 */
public class TestUtil {
	
	static TimeBasedIdGenerator timebasedIdGenerator = new TimeBasedIdGenerator();

	
	static LinkedHashMap<DeliveryAddress, AlertStatus> buildStatusByReceiverMap(int counter
			, StringBuffer deliveryAddressesToBeDeleted
			, StringBuffer addressesToBeDeleted )
	{
		LinkedHashMap<DeliveryAddress, AlertStatus> addresses = new LinkedHashMap<>();
		AlertStatus[] statuss = {AlertStatus.Acknowledged,AlertStatus.Expired,AlertStatus.New,AlertStatus.Pending,AlertStatus.Retracted};
		for(int i=0; i<counter; i++){
			PhysicalAddress testAddress = new PhysicalAddress();
			testAddress.setAddressId(timebasedIdGenerator.getNewId());
			testAddress.setAddress("http://someserver.com:8080/someservice/~selliott/sendmessge");
			addressesToBeDeleted.append("'").append(testAddress.getAddressId()).append("'")
				.append((i+1==counter?"":","));

			DeliveryAddress testDeliveryAddress = new DeliveryAddress();
			testDeliveryAddress.setDeliveryAddressId(timebasedIdGenerator.getNewId());
			testDeliveryAddress.setAddress(testAddress);
			deliveryAddressesToBeDeleted.append("'").append(testDeliveryAddress.getDeliveryAddressId()).append("'")
				.append((i+1==counter?"":","));
			
			addresses.put(testDeliveryAddress, statuss[i%(statuss.length+1)]);
		}
		return addresses;
	}

	
	static Set<DeliveryAddress> buildDeliveryAddressList(int counter
			, StringBuffer deliveryAddressesToBeDeleted
			, StringBuffer addressesToBeDeleted )
	{
		Set<DeliveryAddress> participants = new HashSet<>();
		for(int i=0; i<counter; i++){
			PhysicalAddress testAddress = new PhysicalAddress();
			testAddress.setAddressId(timebasedIdGenerator.getNewId());
			testAddress.setAddress(10+i+" http://someserver.com:8080/someservice/~selliott/sendmessge");
			testAddress.setServiceId(10+i+"");
			addressesToBeDeleted.append(addressesToBeDeleted.length()==0?"":",")
				.append("'").append(testAddress.getAddressId()).append("'");

			DeliveryAddress testDeliveryAddress = new DeliveryAddress();
			testDeliveryAddress.setDeliveryAddressId(timebasedIdGenerator.getNewId());
			testDeliveryAddress.setAddress(testAddress);
			deliveryAddressesToBeDeleted.append(deliveryAddressesToBeDeleted.length()==0?"":",")
				.append("'").append(testDeliveryAddress.getDeliveryAddressId()).append("'");

			participants.add(testDeliveryAddress);

		}
		return participants;
	}
	
	static Set<PhysicalAddress> buildPhysicalAddressList(int counter
			, StringBuffer addressesToBeDeleted )
	{
		Set<PhysicalAddress> addresses = new HashSet<>();
		for(int i=0; i<counter; i++){
			PhysicalAddress testAddress = new PhysicalAddress();
			testAddress.setAddressId(timebasedIdGenerator.getNewId());
			testAddress.setAddress(i+" http://someserver.com:8080/someservice/~selliott/sendmessge");
			addresses.add(testAddress);
			addressesToBeDeleted.append(addressesToBeDeleted.length()==0?"":",")
				.append("'").append(testAddress.getAddressId()).append("'");
		}
		return addresses;
	}
	
	static Set<ConversationChannel> buildConversationChannelList(int counter
			, StringBuffer channelsToBeDeleted )
	{
		Set<ConversationChannel> channels = new HashSet<>();
		for(int i=0; i<counter; i++){
			ConversationChannel channel = new ConversationChannel();
			channel.setConversationChannelId(timebasedIdGenerator.getNewId());
			channels.add(channel);
			channelsToBeDeleted.append(channelsToBeDeleted.length()==0?"":",")
				.append("'").append(channel.getConversationChannelId()).append("'");
		}
		return channels;
	}

	
	
	static LinkedHashMap<String, PhysicalAddress> buildPhysicalAddressByTypeList(int counter
			, StringBuffer addressesToBeDeleted )
	{
		LinkedHashMap<String, PhysicalAddress> addressesByType = new LinkedHashMap<>();
		for(int i=0; i<counter; i++){
			PhysicalAddress testAddress = new PhysicalAddress();
			testAddress.setAddressId(timebasedIdGenerator.getNewId());
			testAddress.setAddress(i+" http://someserver.com:8080/someservice/~selliott/sendmessge");
			testAddress.setServiceId("ServiceID "+i);
			String type = i==2?null:"Type "+i;
			addressesByType.put(type,testAddress);
			addressesToBeDeleted.append(addressesToBeDeleted.length()==0?"":",")
				.append("'").append(testAddress.getAddressId()).append("'");
		}
		return addressesByType;
	}

	
	static Set<Recipient> buildRecipientList(int counter
				, StringBuffer recipientsToBeDeleted
				, StringBuffer deliveryAddressToBeDeleted
				, StringBuffer addressesToBeDeleted )
				
	{
		Set<Recipient> recipients = new HashSet<>();
		for(int i=0; i<counter; i++){
			PhysicalAddress testAddress = new PhysicalAddress();
			testAddress.setAddressId(timebasedIdGenerator.getNewId());
			testAddress.setAddress("http://someserver.com:8080/someservice/~selliott/sendmessge");
			addressesToBeDeleted.append("'").append(addressesToBeDeleted.length()==0?"":",")
				.append(testAddress.getAddressId()).append("'");

			DeliveryAddress testDeliveryAddress = new DeliveryAddress();
			testDeliveryAddress.setDeliveryAddressId(timebasedIdGenerator.getNewId());
			testDeliveryAddress.setAddress(testAddress);
			deliveryAddressToBeDeleted.append(deliveryAddressToBeDeleted.length()==0?"":",")
				.append("'").append(testDeliveryAddress.getDeliveryAddressId()).append("'");
		
			Recipient testRecipient = new Recipient();
			testRecipient.setRecipientId(timebasedIdGenerator.getNewId());
			testRecipient.setDeliveryAddress(testDeliveryAddress);
			Role role = new Role();
			role.setName("role name "+i);
			testRecipient.setRole(role); 
			recipients.add(testRecipient);
			recipientsToBeDeleted.append(recipientsToBeDeleted.length()==0?"":",")
				.append("'").append(testRecipient.getRecipientId()).append("'");

		}
		return recipients;
	}
	
	static MessageBody[] buildMessageBodys(int num, StringBuffer messageBodyToBeDeleted){
		MessageBody[] bodys = new MessageBody[num];
		for(int i=0; i<num; i++){
			MessageBody body = new MessageBody();
			body.setMessageBodyId(timebasedIdGenerator.getNewId());
			body.setContent("some small amount of content because large content can be tested in MessageBody test");
			body.setTag("Tag - "+i);
			body.setType("text/plain");
						
			bodys[i]=body;
			messageBodyToBeDeleted.append(messageBodyToBeDeleted.length()==0?"":",")
			.append("'").append(body.getMessageBodyId()).append("'");
		}
		return bodys;
	}
	
	static List<ProcessingException> buildProcessingExceptionList(int num, StringBuffer exceptionsToBeDeleted){
		List<ProcessingException> exceptions = new ArrayList<>();
		for(int i=0; i<num; i++){
			ProcessingException exception = new ProcessingException();
			exception.setProcessingExceptionId(timebasedIdGenerator.getNewId());
			exception.setExceptionType(ExceptionType.BadBody);
			exception.setFault("some fault - "+i);
			exception.setGeneratingMessageId(timebasedIdGenerator.getNewId());
			exception.setIssuingService("some service - "+i);
			exception.setTypeSpecificContext("type specific context - "+i);
			
			exceptions.add(exception);
			exceptionsToBeDeleted.append(exceptionsToBeDeleted.length()==0?"":",")
			.append("'").append(exception.getProcessingExceptionId()).append("'");
		}
		return exceptions;
	}

}
