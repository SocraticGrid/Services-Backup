package org.socraticgrid.hl7.services.ucs.accessclients.ucsalerting;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.socraticgrid.hl7.services.uc.model.Message;
import org.socraticgrid.hl7.services.uc.model.MessageModel;

/**

 * 
 */
@WebService(targetNamespace = "org.socraticgrid.hl7.services.uc.clients", name = "ucalerting")

public interface UCSAlertingServiceSEI {
	@WebMethod
	public  <T extends Message> boolean receiveAlertMessage(MessageModel<T> messageModel,List<String> localReceivers, String serverId);
	@WebMethod
	public  <T extends Message> boolean updateAlertMessage(MessageModel<T> newMessageModel, MessageModel<T> oldMessageModel,List<String> localReceiver, String serverId);
	@WebMethod
	public  <T extends Message> boolean cancelAlertMessage(MessageModel<T> messageModel, List<String> localReceivers, String serverId);

}
