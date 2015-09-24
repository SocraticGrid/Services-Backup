package org.socraticgrid.hl7.services.uc.model;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 16-Dec-2013 4:00:43 PM
 */
public enum MessageType {
	Notification,
	/**
	 * A request to establish a conversion - The conversation itself may take place
	 * out side the UCS.
	 */
	ConversationRequest,
	/**
	 * A simple message
	 */
	SimpleMessage,
	Alert
}