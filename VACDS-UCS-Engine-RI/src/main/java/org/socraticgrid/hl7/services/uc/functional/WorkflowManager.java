package org.socraticgrid.hl7.services.uc.functional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.socraticgrid.hl7.services.uc.exceptions.ExceptionType;
import org.socraticgrid.hl7.services.uc.exceptions.ProcessingException;
import org.socraticgrid.hl7.services.uc.internal.interfaces.ClientRegistryIntf;
import org.socraticgrid.hl7.services.uc.internal.interfaces.ServiceRegistryIntf;
import org.socraticgrid.hl7.services.uc.logging.EventLevel;
import org.socraticgrid.hl7.services.uc.logging.LogEntryType;
import org.socraticgrid.hl7.services.uc.model.DeliveryAddress;
import org.socraticgrid.hl7.services.uc.model.Message;
import org.socraticgrid.hl7.services.uc.model.MessageModel;
import org.socraticgrid.hl7.services.uc.processes.NotifyUCSClientOfException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 16-Dec-2013 3:54:58 PM
 */
public class WorkflowManager {

	@Autowired
	private ClientRegistryIntf clientReg;
	
	@Autowired
	private ServiceRegistryIntf serviceReg;
	
	@Autowired
	MessageManager msgMgr;
	
	@Autowired
	EventLogger evtLogger;

	@Autowired
	AddressResolver resolver;

	private final Logger logger = LoggerFactory.getLogger(WorkflowManager.class);
	
	
	public WorkflowManager(){

	}
 
	
	public boolean handleException(ProcessingException exception, ExceptionType type,
			String messageId)
	{
		DeliveryAddress receiver = null; // General exception not specific to a receievr
		
		//TODO  Deal with caller mismatch in the contract
		

		//Lookup Message
		MessageModel<? extends Message> msgMdl =  msgMgr.getMessageById(messageId);
		if (msgMdl != null)
		{
			Message msg = msgMdl.getMessageType();
			//Add Exception
			msg.getExceptions().add(exception);
			//Save Message
			msgMgr.updateMessage(msgMdl);
			//Log exception
	
			//TODO  Extend logging
			evtLogger.logDetailedEvent(LogEntryType.Adapter_PostedException, getEventLevel(type), "Service", "Service Exception", exception.getFault(), msg);
			//evtLogger.logDetailedEvent(LogEntryType., level, context, event, text, msg);
			//FUTURE: If so Create notification task and submit for processing
			//NOW: Call UCSClient with Exception.
			
			NotifyUCSClientOfException task = new NotifyUCSClientOfException(clientReg,exception,type,messageId,msg,serviceReg.getServerId(),resolver,receiver);
			task.run();
		}
		
		
		return true;
	}
	
	public boolean handleAdapterException(ProcessingException exception, ExceptionType type,
			String messageId, DeliveryAddress receiver, String adapterIdentity)
	{
		//TODO  Deal with caller mismatch in the contract
		
		boolean out = false;
		//Lookup Message
		MessageModel<? extends Message> msgMdl =  msgMgr.getMessageById(messageId);
		if (msgMdl != null)
		{
			Message msg = msgMdl.getMessageType();
			//Add Exception
			msg.getExceptions().add(exception);
			//Save Message
			msgMgr.updateMessage(msgMdl);
			//Log exception
	
			//TODO  Extend logging
			evtLogger.logDetailedEvent(LogEntryType.Adapter_PostedException, getEventLevel(type), "Adapter", "Adapter Exception:"+adapterIdentity, exception.getFault(), msg);
			//FUTURE: If so Create notification task and submit for processing
			//NOW: Call UCSClient with Exception.
			
			NotifyUCSClientOfException task = new NotifyUCSClientOfException(clientReg,exception,type,messageId,msg,serviceReg.getServerId(),resolver,receiver);
			task.run();
			out=true;
		}
		
		
		return out;
	}

	private EventLevel getEventLevel(ExceptionType type)
	{
		EventLevel out;
		out = EventLevel.info;

		switch (type)
		{
		case SystemFault:
		case ServerAdapterFault:
		case UnknownService:
			out = EventLevel.error;
			break;
		default:
			out = EventLevel.warn;
			break;
		}
		return out;
	}
}