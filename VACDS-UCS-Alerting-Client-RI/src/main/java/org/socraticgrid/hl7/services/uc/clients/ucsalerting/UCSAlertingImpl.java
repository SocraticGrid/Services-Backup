package org.socraticgrid.hl7.services.uc.clients.ucsalerting;

import java.util.List;

import javax.jws.WebParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.socraticgrid.hl7.services.uc.interfaces.UCSAlertingIntf;
import org.socraticgrid.hl7.services.uc.model.Message;
import org.socraticgrid.hl7.services.uc.model.MessageModel;

public class UCSAlertingImpl implements UCSAlertingIntf
{
	private final Logger logger = LoggerFactory.getLogger(UCSAlertingImpl.class);
	public UCSAlertingImpl()
	{
		// TODO Auto-generated constructor stub
	}

	@Override
	public <T extends Message> boolean receiveAlertMessage(
			MessageModel<T> messageModel,List<String> localReceivers,  String serverId)
	{
		logger.debug("receiveAlertMessage called");
		return true;
	}

	@Override
	public <T extends Message> boolean updateAlertMessage(
			MessageModel<T> newMessageModel, MessageModel<T> oldMessageModel,  List<String> localReceivers, String serverId)
	{
		logger.debug("updateAlertMessage called");
		return true;
	}

	@Override
	public <T extends Message> boolean cancelAlertMessage(
			 MessageModel<T> messageModel,List<String> localReceivers,String serverId)
	{
		logger.debug("cancelAlertMessage called");
		return true;
	}

}
