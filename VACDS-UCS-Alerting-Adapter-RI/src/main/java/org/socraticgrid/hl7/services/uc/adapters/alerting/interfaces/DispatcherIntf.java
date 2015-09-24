package org.socraticgrid.hl7.services.uc.adapters.alerting.interfaces;

import org.socraticgrid.hl7.services.uc.adapters.alerting.Configuration;
import org.socraticgrid.hl7.services.uc.interfaces.AdapterIntf;
import org.socraticgrid.hl7.services.uc.model.Message;
import org.socraticgrid.hl7.services.uc.model.PhysicalAddress;

public interface DispatcherIntf {
	//TODO Revise to Support delivery Sets

	public boolean sendMessage(PhysicalAddress address,
			Message msg, String serverId);

	public boolean updateMessage(PhysicalAddress address,
			Message msg, String serverId);

	public boolean cancelMessage(PhysicalAddress address,
			Message msg, String serverId);

	//Initialization contract
	public void setConfiguration(Configuration config);
	public void setAdapter(AdapterIntf adapter);
}
