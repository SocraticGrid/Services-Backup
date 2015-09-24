package org.socraticgrid.hl7.services.uc.clients.ucsclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.socraticgrid.hl7.services.uc.exceptions.BadBodyException;
import org.socraticgrid.hl7.services.uc.exceptions.FeatureNotSupportedException;
import org.socraticgrid.hl7.services.uc.exceptions.InvalidContentException;
import org.socraticgrid.hl7.services.uc.exceptions.InvalidMessageException;
import org.socraticgrid.hl7.services.uc.exceptions.MissingBodyTypeException;
import org.socraticgrid.hl7.services.uc.exceptions.ProcessingException;
import org.socraticgrid.hl7.services.uc.exceptions.ServiceAdapterFaultException;
import org.socraticgrid.hl7.services.uc.exceptions.UndeliverableMessageException;
import org.socraticgrid.hl7.services.uc.interfaces.UCSClientIntf;
import org.socraticgrid.hl7.services.uc.model.Conversation;
import org.socraticgrid.hl7.services.uc.model.DeliveryAddress;
import org.socraticgrid.hl7.services.uc.model.Message;
import org.socraticgrid.hl7.services.uc.model.MessageModel;


public class UCSClientImpl implements UCSClientIntf
{
	private final Logger logger = LoggerFactory.getLogger(UCSClientImpl.class);
	
	public UCSClientImpl()
	{
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean callReady(Conversation conversation, String callHandle, String serverId) {
		// TODO Auto-generated method stub
		logger.debug("Class Ready Called");
		return false;
	}



	@Override
	public <T extends Message> boolean handleException(
			MessageModel<T> messageModel, DeliveryAddress sender, DeliveryAddress receiver, ProcessingException exp, String serverId) {
		logger.debug("HandleException Called");
		return false;
	}

	@Override
	public <T extends Message> boolean handleNotification(
			MessageModel<T> messageModel, String serverId) {
		logger.debug("handleNotification Called");
		return false;
	}

	@Override
	public <T extends Message> MessageModel<T> handleResponse(
			MessageModel<T> messageModel, String serverId) throws InvalidMessageException,
			InvalidContentException, MissingBodyTypeException,
			BadBodyException, ServiceAdapterFaultException,
			UndeliverableMessageException, FeatureNotSupportedException {
		logger.debug("handleResponse Called");
		return null;
	}

	@Override
	public <T extends Message> boolean receiveMessage(
			MessageModel<T> messageModel, String serverId) {
		logger.debug("receiveMessage Called");
		return false;
	}

}
