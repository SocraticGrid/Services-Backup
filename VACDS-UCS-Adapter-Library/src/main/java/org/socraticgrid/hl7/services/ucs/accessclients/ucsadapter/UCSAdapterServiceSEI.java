package org.socraticgrid.hl7.services.ucs.accessclients.ucsadapter;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.socraticgrid.hl7.services.uc.model.AdapterMessage;
import org.socraticgrid.hl7.services.uc.model.InteractionTypes;

/**

 * 
 */
@WebService(targetNamespace = "org.socraticgrid.hl7.services.uc.clients", name = "ucsadapter")

public interface UCSAdapterServiceSEI  {

	@WebMethod
	public boolean cancelMessage(AdapterMessage arg0, String arg1);

	@WebMethod
	public List<InteractionTypes> getInteractionTypes();

	@WebMethod
	public String getServiceId() ;

	@WebMethod
	public boolean sendMessage(AdapterMessage arg0, String arg1) ;

	@WebMethod
	public boolean updateMessage(AdapterMessage arg0, AdapterMessage arg1,
			String arg2) ;

}
