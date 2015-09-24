package org.socraticgrid.hl7.services.uc.adapters.alerting.interfaces;

import org.socraticgrid.hl7.services.uc.adapters.alerting.Configuration;
import org.socraticgrid.hl7.services.uc.adapters.alerting.ResolvedAddress;
import org.socraticgrid.hl7.services.uc.model.PhysicalAddress;


public interface AddressResolverIntf {

	public ResolvedAddress resolveAddress(PhysicalAddress addr);
	public void setConfiguration(Configuration config);
}
