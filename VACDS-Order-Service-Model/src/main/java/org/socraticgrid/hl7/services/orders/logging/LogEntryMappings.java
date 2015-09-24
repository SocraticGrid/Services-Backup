package org.socraticgrid.hl7.services.orders.logging;

import java.util.HashMap;

public class LogEntryMappings {

	private static HashMap<LogEntryType,LogEntryGroup> entryGroupMap;
	private static HashMap<LogEntryType,LogEntryLevels> entryLevelMap;
	
	static {
		entryGroupMap = new HashMap<LogEntryType,LogEntryGroup> ();
		
		entryGroupMap.put(LogEntryType.User_AuthenticationSuccess,LogEntryGroup.Authenication);
		entryGroupMap.put(LogEntryType.User_AuthenticationFailure,LogEntryGroup.Authenication);
		entryGroupMap.put(LogEntryType.User_ExceptionUsingService,LogEntryGroup.Exception);
		entryGroupMap.put(LogEntryType.User_CreateOrder,LogEntryGroup.Create);
		entryGroupMap.put(LogEntryType.User_CancelOrder,LogEntryGroup.Update);
		entryGroupMap.put(LogEntryType.User_ChangeOrder,LogEntryGroup.Update);
		entryGroupMap.put(LogEntryType.User_WorflowUpdate,LogEntryGroup.Workflow);
		entryGroupMap.put(LogEntryType.Fullfilment_Accepted,LogEntryGroup.Fullfilment);
		entryGroupMap.put(LogEntryType.Fullfilment_Rejected,LogEntryGroup.Fullfilment);
		entryGroupMap.put(LogEntryType.Fullfilment_Updated,LogEntryGroup.Fullfilment);
		entryGroupMap.put(LogEntryType.System_Startup,LogEntryGroup.Operational);
		entryGroupMap.put(LogEntryType.System_Shutdown,LogEntryGroup.Operational);
		entryGroupMap.put(LogEntryType.System_DirtyStartup,LogEntryGroup.Operational);
		entryGroupMap.put(LogEntryType.System_ServiceDown,LogEntryGroup.Operational);
		entryGroupMap.put(LogEntryType.System_ServiceUp,LogEntryGroup.Operational);
		entryGroupMap.put(LogEntryType.System_InternalError,LogEntryGroup.Exception);
		entryGroupMap.put(LogEntryType.General,LogEntryGroup.Diagnostic);
		entryGroupMap.put(LogEntryType.Diagnostic,LogEntryGroup.Diagnostic);
		entryGroupMap.put(LogEntryType.Trace,LogEntryGroup.Diagnostic);

		
		
		entryLevelMap = new HashMap<LogEntryType,LogEntryLevels> ();
		
		entryLevelMap.put(LogEntryType.User_AuthenticationSuccess,new LogEntryLevels(EventLevel.debug,EventLevel.info));
		entryLevelMap.put(LogEntryType.User_AuthenticationFailure,new LogEntryLevels(EventLevel.debug,EventLevel.warn));
		entryLevelMap.put(LogEntryType.User_ExceptionUsingService,new LogEntryLevels(EventLevel.debug,EventLevel.error));
		entryLevelMap.put(LogEntryType.User_CreateOrder,new LogEntryLevels(EventLevel.debug,EventLevel.error));
		entryLevelMap.put(LogEntryType.User_CancelOrder,new LogEntryLevels(EventLevel.debug,EventLevel.error));
		entryLevelMap.put(LogEntryType.User_ChangeOrder,new LogEntryLevels(EventLevel.debug,EventLevel.error));
		entryLevelMap.put(LogEntryType.User_WorflowUpdate,new LogEntryLevels(EventLevel.debug,EventLevel.error));
		entryLevelMap.put(LogEntryType.Fullfilment_Accepted,new LogEntryLevels(EventLevel.debug,EventLevel.info));
		entryLevelMap.put(LogEntryType.Fullfilment_Rejected,new LogEntryLevels(EventLevel.debug,EventLevel.error));
		entryLevelMap.put(LogEntryType.Fullfilment_Updated,new LogEntryLevels(EventLevel.debug,EventLevel.error));
		entryLevelMap.put(LogEntryType.System_Startup,new LogEntryLevels(EventLevel.debug,EventLevel.error));
		entryLevelMap.put(LogEntryType.System_Shutdown,new LogEntryLevels(EventLevel.debug,EventLevel.info));
		entryLevelMap.put(LogEntryType.System_DirtyStartup,new LogEntryLevels(EventLevel.debug,EventLevel.error));
		entryLevelMap.put(LogEntryType.System_ServiceDown,new LogEntryLevels(EventLevel.debug,EventLevel.error));
		entryLevelMap.put(LogEntryType.System_ServiceUp,new LogEntryLevels(EventLevel.debug,EventLevel.error));
		entryLevelMap.put(LogEntryType.System_InternalError,new LogEntryLevels(EventLevel.debug,EventLevel.error));
		entryLevelMap.put(LogEntryType.General,new LogEntryLevels(EventLevel.debug,EventLevel.error));
		entryLevelMap.put(LogEntryType.Diagnostic,new LogEntryLevels(EventLevel.debug,EventLevel.info));
		entryLevelMap.put(LogEntryType.Trace,new LogEntryLevels(EventLevel.debug,EventLevel.info));
	
		
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
