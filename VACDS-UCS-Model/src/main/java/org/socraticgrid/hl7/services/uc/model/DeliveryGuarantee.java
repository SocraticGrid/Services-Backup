package org.socraticgrid.hl7.services.uc.model;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 16-Dec-2013 4:00:12 PM
 */
public enum DeliveryGuarantee {
	/**
	 * The message is by nature durable in that it must survive service interruption
	 * and generate exceptions on delivery failure.
	 */
	BestEffort,
	/**
	 * Delivery is attempted, but delivery failure is of no consequence. Disposable
	 * messages should not generate exceptions when deliverable fails. Disposable
	 * Messages need not be durable.
	 */
	Disposable,
	/**
	 * The Message is a higher priority form of Best effort.
	 * The is might also be modeled as a increased form of priority
	 */
	MaximumEffort
}