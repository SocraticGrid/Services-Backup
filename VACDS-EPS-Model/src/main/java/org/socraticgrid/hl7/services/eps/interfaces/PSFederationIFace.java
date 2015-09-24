package org.socraticgrid.hl7.services.eps.interfaces;

import java.util.Set;

import javax.jws.WebParam;
import javax.jws.WebService;

import org.socraticgrid.hl7.services.eps.model.LinkManagementMessage;
import org.socraticgrid.hl7.services.eps.model.Message;
import org.socraticgrid.hl7.services.eps.model.SynchronizationMessage;
import org.socraticgrid.hl7.services.eps.model.SynchronizationStatus;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 04-Jan-2014 6:15:20 PM
 */
@WebService(name = "psfederation", targetNamespace = "org.socraticgrid.hl7.services.eps")
public interface PSFederationIFace {

	/**
	 * 
	 * @param Events
	 */
	public void receiveEvents(@WebParam(name = "sourceId") String sourceId,
			@WebParam(name = "events") Set<Message> events);

	public Set<Message> pullEvents(@WebParam(name = "syncMsg") String targetId);

	public SynchronizationStatus synchronize(
			@WebParam(name = "syncMsg") SynchronizationMessage syncMsg);

	public boolean linkMananageUpdate(
			@WebParam(name = "msg") LinkManagementMessage msg);

}