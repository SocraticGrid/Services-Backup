/**
 * 
 */
package org.socraticgrid.hl7.services.eps.service.impl;

import java.util.Set;

import org.socraticgrid.hl7.services.eps.interfaces.PSFederationIFace;
import org.socraticgrid.hl7.services.eps.model.LinkManagementMessage;
import org.socraticgrid.hl7.services.eps.model.Message;
import org.socraticgrid.hl7.services.eps.model.SynchronizationMessage;
import org.socraticgrid.hl7.services.eps.model.SynchronizationStatus;

/**
 * @author Jerry Goodnough
 *
 */
public class PSFederationServiceImpl implements PSFederationIFace {

	/**
	 * 
	 */
	public PSFederationServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.socraticgrid.hl7.services.eps.interfaces.PSFederationIFace#receiveEvents(java.lang.String, java.util.Set)
	 */
	@Override
	public void receiveEvents(String sourceId, Set<Message> events) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.socraticgrid.hl7.services.eps.interfaces.PSFederationIFace#pullEvents(java.lang.String)
	 */
	@Override
	public Set<Message> pullEvents(String targetId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.socraticgrid.hl7.services.eps.interfaces.PSFederationIFace#synchronize(org.socraticgrid.hl7.services.eps.model.SynchronizationMessage)
	 */
	@Override
	public SynchronizationStatus synchronize(SynchronizationMessage syncMsg) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.socraticgrid.hl7.services.eps.interfaces.PSFederationIFace#linkMananageUpdate(org.socraticgrid.hl7.services.eps.model.LinkManagementMessage)
	 */
	@Override
	public boolean linkMananageUpdate(LinkManagementMessage msg) {
		// TODO Auto-generated method stub
		return false;
	}

}
