package org.socraticgrid.hl7.services.orders.logging;

public enum LogEntryType {
	User_AuthenticationSuccess,
	User_AuthenticationFailure,
	User_ExceptionUsingService,
	User_CreateOrder,
	User_CancelOrder,
	User_ChangeOrder,
	User_WorflowUpdate,
	Fullfilment_Accepted,
	Fullfilment_Rejected,
	Fullfilment_Updated,
	System_Startup,
	System_Shutdown,
	System_DirtyStartup,
	System_ServiceDown,
	System_ServiceUp,
	System_InternalError,
	General,
	Diagnostic,
	Trace
}
