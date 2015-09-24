/**
 * 
 */
package org.socraticgrid.hl7.services.uc.db.dto.typehandler;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.socraticgrid.hl7.services.uc.model.PhysicalAddress;
import org.socraticgrid.hl7.services.uc.model.UserContactInfo;
import org.socraticgrid.hl7.services.uc.model.UserContactInfoDelegate;

/**
 * @author steven
 * Feb 4, 2014
 *
 */
public class UserContactInfoDelegateResultHandler implements ResultHandler {

	private UserContactInfo userContactInfo;
	public UserContactInfo getUserContactInfo() {
		return userContactInfo;
	}
	
	@Override
	public void handleResult(ResultContext context) {
		final UserContactInfoDelegate delegate = (UserContactInfoDelegate)context.getResultObject();
		userContactInfo = (UserContactInfo)delegate;
		LinkedList<String> types = delegate.getTypes();
		LinkedList<PhysicalAddress> addresses = delegate.getAddresses();
		Map<String, PhysicalAddress> addressesByType = new LinkedHashMap<>();
		for(int i=0; i<addresses.size(); i++) {
			addressesByType.put(types.get(i),addresses.get(i));
		}
		userContactInfo.setAddressesByType(addressesByType);
	}

}
