package org.socraticgrid.hl7.services.uc.operational;
import java.util.HashMap;
import java.util.List;

import org.socraticgrid.hl7.services.uc.model.PhysicalAddress;
import org.socraticgrid.hl7.services.uc.model.Message;
import org.socraticgrid.hl7.services.uc.model.MessageModel;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 16-Dec-2013 3:54:57 PM
 */
public class MessageWrapper<T extends Message> {
	
	private T message ;
	
	private HashMap<String,List<PhysicalAddress>> AddressMap;

	public MessageWrapper(){

	}

	public MessageWrapper(MessageModel<T> messageModel){
		this.message = messageModel.getMessageType();
	}



	public T getMessage()
	{
		return message;
	}

	public void setMessage(T message)
	{
		this.message = message;
	}

	public HashMap<String, List<PhysicalAddress>> getAddressMap()
	{
		return AddressMap;
	}

	public void setAddressMap(HashMap<String, List<PhysicalAddress>> addressMap)
	{
		AddressMap = addressMap;
	}

}