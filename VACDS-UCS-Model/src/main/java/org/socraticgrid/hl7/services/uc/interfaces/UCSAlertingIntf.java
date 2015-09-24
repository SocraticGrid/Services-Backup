package org.socraticgrid.hl7.services.uc.interfaces;

import java.util.List;

import javax.jws.WebService;

import org.socraticgrid.hl7.services.uc.model.Message;
import org.socraticgrid.hl7.services.uc.model.MessageModel;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 16-Dec-2013 3:54:57 PM
 */
@WebService(name = "ucsalerting", targetNamespace = "org.socraticgrid.hl7.services.uc.clients")
public interface UCSAlertingIntf {

	public  <T extends Message> boolean receiveAlertMessage(MessageModel<T> messageModel, List<String> localReceivers, String serverId);

	public  <T extends Message> boolean updateAlertMessage(MessageModel<T> newMessageModel, MessageModel<T> oldMessageModel, List<String> localReceivers, String serverId);

	public  <T extends Message> boolean cancelAlertMessage(MessageModel<T> messageModel, List<String> localReceivers, String serverId);

}