/**
 * 
 */
package org.socraticgrid.hl7.services.uc;

import javax.jws.WebParam;
import javax.jws.WebService;

import org.socraticgrid.hl7.services.uc.exceptions.FeatureNotSupportedException;
import org.socraticgrid.hl7.services.uc.exceptions.InvalidContentException;
import org.socraticgrid.hl7.services.uc.exceptions.InvalidMessageException;
import org.socraticgrid.hl7.services.uc.exceptions.ReadOnlyException;
import org.socraticgrid.hl7.services.uc.exceptions.ServiceAdapterFaultException;
import org.socraticgrid.hl7.services.uc.exceptions.UnknownServiceException;
import org.socraticgrid.hl7.services.uc.exceptions.UpdateException;
import org.socraticgrid.hl7.services.uc.interfaces.AlertingIntf;
import org.socraticgrid.hl7.services.uc.model.AlertMessage;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Jerry Goodnough
 * 
 */
//TODO - Move Implementation Logic to and Implementation class that we delegate
//to to allow other frontends to be used for this service
@WebService(name = "alerting", targetNamespace = "org.socraticgrid.hl7.services.uc")
public class AlertingService 
{


	@Autowired
	AlertingIntf alertingService;

	public  boolean updateAlert(
			@WebParam(name = "alert") AlertMessage alert)
			throws InvalidMessageException, InvalidContentException,
			UnknownServiceException, ServiceAdapterFaultException,
			FeatureNotSupportedException, UpdateException, ReadOnlyException
	{
		return alertingService.updateAlert(alert);

	}

}