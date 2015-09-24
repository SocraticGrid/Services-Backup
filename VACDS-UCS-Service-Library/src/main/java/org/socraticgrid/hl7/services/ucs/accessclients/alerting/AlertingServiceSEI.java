package org.socraticgrid.hl7.services.ucs.accessclients.alerting;


import javax.jws.WebMethod;
import javax.jws.WebService;

import org.socraticgrid.hl7.services.uc.exceptions.InvalidContentException;
import org.socraticgrid.hl7.services.uc.exceptions.InvalidMessageException;
import org.socraticgrid.hl7.services.uc.exceptions.ReadOnlyException;
import org.socraticgrid.hl7.services.uc.exceptions.ServiceAdapterFaultException;
import org.socraticgrid.hl7.services.uc.exceptions.UnknownServiceException;
import org.socraticgrid.hl7.services.uc.exceptions.UpdateException;
import org.socraticgrid.hl7.services.uc.model.AlertMessage;


@WebService(targetNamespace = "org.socraticgrid.hl7.services.uc", name = "AlertingServiceService")
public interface AlertingServiceSEI  {

	@WebMethod
	public boolean updateAlert(AlertMessage alert)
			throws InvalidMessageException, InvalidContentException,
			UnknownServiceException, ServiceAdapterFaultException,
			UpdateException, ReadOnlyException;
}
