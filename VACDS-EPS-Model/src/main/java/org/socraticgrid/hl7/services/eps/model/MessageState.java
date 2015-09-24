package org.socraticgrid.hl7.services.eps.model;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 04-Jan-2014 7:28:44 PM
 */
public enum MessageState {
	Delivered,
	Expired,
	ReviewPending,
	Rejected,
	Initial,
	Approved,
	Deleted,
	Pending
}