package org.socraticgrid.hl7.services.uc.logging;

import java.util.HashMap;

public class LogEntryMappings {

	private static HashMap<LogEntryType,LogEntryGroup> entryGroupMap;
	private static HashMap<LogEntryType,LogEntryLevels> entryLevelMap;
	
	static {
		entryGroupMap = new HashMap<LogEntryType,LogEntryGroup> ();
		
		entryGroupMap.put(LogEntryType.User_AuthenticationSuccess,LogEntryGroup.Authenication);
		entryGroupMap.put(LogEntryType.User_AuthenticationFailure,LogEntryGroup.Authenication);
		entryGroupMap.put(LogEntryType.User_ExceptionUsingService,LogEntryGroup.Exception);
		entryGroupMap.put(LogEntryType.User_AcceptanceForDelivery,LogEntryGroup.Delivery);
		entryGroupMap.put(LogEntryType.User_AdapterHandOff,LogEntryGroup.Delivery);
		entryGroupMap.put(LogEntryType.User_FailedDelivery,LogEntryGroup.Delivery);
		entryGroupMap.put(LogEntryType.User_SuccessfulDelivery,LogEntryGroup.Delivery);
		entryGroupMap.put(LogEntryType.User_Reply,LogEntryGroup.Reply);
		entryGroupMap.put(LogEntryType.User_Response,LogEntryGroup.Reply);
		entryGroupMap.put(LogEntryType.User_ConversationReady,LogEntryGroup.Conversation);
		entryGroupMap.put(LogEntryType.User_ConversationConnect,LogEntryGroup.Conversation);
		entryGroupMap.put(LogEntryType.User_ConversationEnded,LogEntryGroup.Conversation);
		entryGroupMap.put(LogEntryType.User_ConversationUpdate,LogEntryGroup.Conversation);
		entryGroupMap.put(LogEntryType.User_Modification,LogEntryGroup.Update);
		entryGroupMap.put(LogEntryType.User_QueryMessages,LogEntryGroup.View);
		entryGroupMap.put(LogEntryType.User_RetrieveMessage,LogEntryGroup.View);
		entryGroupMap.put(LogEntryType.User_PreferenceUpdate,LogEntryGroup.Update);
		entryGroupMap.put(LogEntryType.User_CancelMessage,LogEntryGroup.Update);
		entryGroupMap.put(LogEntryType.System_Startup,LogEntryGroup.Operational);
		entryGroupMap.put(LogEntryType.System_Shutdown,LogEntryGroup.Operational);
		entryGroupMap.put(LogEntryType.System_DirtyStartup,LogEntryGroup.Operational);
		entryGroupMap.put(LogEntryType.System_AdapterUp,LogEntryGroup.Operational);
		entryGroupMap.put(LogEntryType.System_AdapterDown,LogEntryGroup.Operational);
		entryGroupMap.put(LogEntryType.System_SuspendChannel,LogEntryGroup.Operational);
		entryGroupMap.put(LogEntryType.System_ResumeChannel,LogEntryGroup.Operational);
		entryGroupMap.put(LogEntryType.System_MessageDeliveryTimeout,LogEntryGroup.Delivery);
		entryGroupMap.put(LogEntryType.System_MessageDeliveryRescheduled,LogEntryGroup.Delivery);
		entryGroupMap.put(LogEntryType.System_MessageDeliverySuspended,LogEntryGroup.Delivery);
		entryGroupMap.put(LogEntryType.System_InternalError,LogEntryGroup.Exception);
		entryGroupMap.put(LogEntryType.General,LogEntryGroup.Diagnostic);
		entryGroupMap.put(LogEntryType.Diagnostic,LogEntryGroup.Diagnostic);
		entryGroupMap.put(LogEntryType.Trace,LogEntryGroup.Diagnostic);
		entryGroupMap.put(LogEntryType.User_SendMessage,LogEntryGroup.Send);
		entryGroupMap.put(LogEntryType.User_Presence,LogEntryGroup.Presence);
		entryGroupMap.put(LogEntryType.Adapter_PostedException,LogEntryGroup.Adapter);
		entryGroupMap.put(LogEntryType.Adapetr_Fault,LogEntryGroup.Adapter);
	
		
		
		entryLevelMap = new HashMap<LogEntryType,LogEntryLevels> ();
		
		entryLevelMap.put(LogEntryType.User_AuthenticationSuccess,new LogEntryLevels(EventLevel.debug,EventLevel.info));
		entryLevelMap.put(LogEntryType.User_AuthenticationFailure,new LogEntryLevels(EventLevel.debug,EventLevel.warn));
		entryLevelMap.put(LogEntryType.User_ExceptionUsingService,new LogEntryLevels(EventLevel.debug,EventLevel.error));
		entryLevelMap.put(LogEntryType.User_AcceptanceForDelivery,new LogEntryLevels(EventLevel.debug,EventLevel.info));
		entryLevelMap.put(LogEntryType.User_AdapterHandOff,new LogEntryLevels(EventLevel.debug,EventLevel.info));
		entryLevelMap.put(LogEntryType.User_FailedDelivery,new LogEntryLevels(EventLevel.debug,EventLevel.error));
		entryLevelMap.put(LogEntryType.User_SuccessfulDelivery,new LogEntryLevels(EventLevel.debug,EventLevel.info));
		entryLevelMap.put(LogEntryType.User_Reply,new LogEntryLevels(EventLevel.debug,EventLevel.info));
		entryLevelMap.put(LogEntryType.User_Response,new LogEntryLevels(EventLevel.debug,EventLevel.info));
		entryLevelMap.put(LogEntryType.User_ConversationReady,new LogEntryLevels(EventLevel.debug,EventLevel.info));
		entryLevelMap.put(LogEntryType.User_ConversationConnect,new LogEntryLevels(EventLevel.debug,EventLevel.info));
		entryLevelMap.put(LogEntryType.User_ConversationEnded,new LogEntryLevels(EventLevel.debug,EventLevel.error));
		entryLevelMap.put(LogEntryType.User_ConversationUpdate,new LogEntryLevels(EventLevel.debug,EventLevel.warn));
		entryLevelMap.put(LogEntryType.User_Modification,new LogEntryLevels(EventLevel.debug,EventLevel.warn));
		entryLevelMap.put(LogEntryType.User_QueryMessages,new LogEntryLevels(EventLevel.debug,EventLevel.info));
		entryLevelMap.put(LogEntryType.User_RetrieveMessage,new LogEntryLevels(EventLevel.debug,EventLevel.info));
		entryLevelMap.put(LogEntryType.User_PreferenceUpdate,new LogEntryLevels(EventLevel.debug,EventLevel.warn));
		entryLevelMap.put(LogEntryType.User_CancelMessage,new LogEntryLevels(EventLevel.debug,EventLevel.warn));
		entryLevelMap.put(LogEntryType.System_Startup,new LogEntryLevels(EventLevel.debug,EventLevel.info));
		entryLevelMap.put(LogEntryType.System_Shutdown,new LogEntryLevels(EventLevel.debug,EventLevel.info));
		entryLevelMap.put(LogEntryType.System_DirtyStartup,new LogEntryLevels(EventLevel.debug,EventLevel.error));
		entryLevelMap.put(LogEntryType.System_AdapterUp,new LogEntryLevels(EventLevel.debug,EventLevel.warn));
		entryLevelMap.put(LogEntryType.System_AdapterDown,new LogEntryLevels(EventLevel.debug,EventLevel.error));
		entryLevelMap.put(LogEntryType.System_SuspendChannel,new LogEntryLevels(EventLevel.debug,EventLevel.warn));
		entryLevelMap.put(LogEntryType.System_ResumeChannel,new LogEntryLevels(EventLevel.debug,EventLevel.warn));
		entryLevelMap.put(LogEntryType.System_MessageDeliveryTimeout,new LogEntryLevels(EventLevel.debug,EventLevel.error));
		entryLevelMap.put(LogEntryType.System_MessageDeliveryRescheduled,new LogEntryLevels(EventLevel.debug,EventLevel.warn));
		entryLevelMap.put(LogEntryType.System_MessageDeliverySuspended,new LogEntryLevels(EventLevel.debug,EventLevel.warn));
		entryLevelMap.put(LogEntryType.System_InternalError,new LogEntryLevels(EventLevel.debug,EventLevel.error));
		entryLevelMap.put(LogEntryType.General,new LogEntryLevels(EventLevel.debug,EventLevel.error));
		entryLevelMap.put(LogEntryType.Diagnostic,new LogEntryLevels(EventLevel.debug,EventLevel.info));
		entryLevelMap.put(LogEntryType.Trace,new LogEntryLevels(EventLevel.debug,EventLevel.info));
		entryLevelMap.put(LogEntryType.User_SendMessage,new LogEntryLevels(EventLevel.debug,EventLevel.error));
		entryLevelMap.put(LogEntryType.User_Presence,new LogEntryLevels(EventLevel.debug,EventLevel.error));
		entryLevelMap.put(LogEntryType.Adapter_PostedException,new LogEntryLevels(EventLevel.debug,EventLevel.error));
		entryLevelMap.put(LogEntryType.Adapetr_Fault,new LogEntryLevels(EventLevel.debug,EventLevel.error));	
		
	}
	
	static public LogEntryGroup getLogEntryGroup(LogEntryType entry)
	{
		return entryGroupMap.get(entry);
	}

	static public LogEntryLevels getLogEntryLevels(LogEntryType entry)
	{
		return entryLevelMap.get(entry);
	}
	
}
