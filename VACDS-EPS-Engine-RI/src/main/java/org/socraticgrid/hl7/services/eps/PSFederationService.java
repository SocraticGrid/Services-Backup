package org.socraticgrid.hl7.services.eps;

import java.util.Set;

import javax.jws.WebParam;
import javax.jws.WebService;

import org.socraticgrid.hl7.services.eps.interfaces.PSFederationIFace;
import org.socraticgrid.hl7.services.eps.model.LinkManagementMessage;
import org.socraticgrid.hl7.services.eps.model.Message;
import org.socraticgrid.hl7.services.eps.model.SynchronizationMessage;
import org.socraticgrid.hl7.services.eps.model.SynchronizationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@WebService(name = "psfederation", targetNamespace = "org.socraticgrid.hl7.services.eps")
public class PSFederationService implements PSFederationIFace {

	@Autowired
	@Qualifier("PSFederationServiceImpl")
	PSFederationIFace psfederationImpl;

	@Override
	public Set<Message> pullEvents(@WebParam(name = "targetId") String targetId) {

		return psfederationImpl.pullEvents(targetId);
	}

	@Override
	public SynchronizationStatus synchronize(
			@WebParam(name = "syncMsg") SynchronizationMessage syncMsg) {

		return psfederationImpl.synchronize(syncMsg);
	}

	@Override
	public boolean linkMananageUpdate(
			@WebParam(name = "msg") LinkManagementMessage msg) {

		return psfederationImpl.linkMananageUpdate(msg);
	}

	@Override
	public void receiveEvents(@WebParam(name = "sourceId") String sourceId,
			@WebParam(name = "events") Set<Message> events) {
		psfederationImpl.receiveEvents(sourceId, events);

	}

}
