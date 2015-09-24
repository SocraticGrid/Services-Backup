package org.socraticgrid.hl7.services.uc.adapters.alerting;

import org.socraticgrid.hl7.services.uc.adapters.alerting.interfaces.AddressResolverIntf;
import org.socraticgrid.hl7.services.uc.model.PhysicalAddress;

/*

 */
public class AddressResolver implements AddressResolverIntf {

	@Override
	public ResolvedAddress resolveAddress(PhysicalAddress addr) {
		
		String base = addr.getAddress();
		int fnd = base.indexOf('|');
		ResolvedAddress resAddr = null;
		if (fnd > 0) {
			resAddr = new ResolvedAddress();
			resAddr.setEndPoint(base.substring(0, fnd));
			resAddr.setRemoteId(base.substring(fnd + 1));
		}
		return resAddr;
	}

	@Override
	public void setConfiguration(Configuration config) {
		// TODO Auto-generated method stub
		
	}

}
