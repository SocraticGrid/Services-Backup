package org.socraticgrid.hl7.services.uc.exceptions;

public enum ExceptionType {
	BadBody,
	Delivery,
	FeatureNotSupported,
	General,
	InvalidAddress,
	InvalidContext,
	InvalidConversation,
	InvalidInput,
	InvalidMessage,
	InvalidQuery,
	MessageDeliveryFailure,
	MessageDeliveryTimeout,
	MissingBodyType,
	NotConnected,
	ReadOnly,
	ServerAdapterFault,
	ServiceOffline,
	SystemFault,
	UndeliverableMessage,
	UnknownService,
	UnknownUser,
	UpdateError
}
