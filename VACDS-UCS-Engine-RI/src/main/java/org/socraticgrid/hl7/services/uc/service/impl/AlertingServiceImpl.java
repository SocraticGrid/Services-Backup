/**
 * 
 */
package org.socraticgrid.hl7.services.uc.service.impl;

import org.socraticgrid.hl7.services.uc.exceptions.InvalidContentException;
import org.socraticgrid.hl7.services.uc.exceptions.InvalidMessageException;
import org.socraticgrid.hl7.services.uc.exceptions.ReadOnlyException;
import org.socraticgrid.hl7.services.uc.exceptions.ServiceAdapterFaultException;
import org.socraticgrid.hl7.services.uc.exceptions.UnknownServiceException;
import org.socraticgrid.hl7.services.uc.exceptions.UpdateException;
import org.socraticgrid.hl7.services.uc.functional.MessageManager;
import org.socraticgrid.hl7.services.uc.interfaces.AlertingIntf;
import org.socraticgrid.hl7.services.uc.model.AlertMessage;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Jerry Goodnough
 *
 */
public class AlertingServiceImpl implements AlertingIntf
{

	@Autowired
	MessageManager msgMgr;
	/**
	 * 
	 */
	public AlertingServiceImpl()
	{
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.socraticgrid.hl7.services.uc.interfaces.AlertingIntf#updateAlert(org.socraticgrid.hl7.services.uc.type.AlertType)
	 */
	@Override
	public boolean updateAlert(AlertMessage alert) throws InvalidMessageException,
			InvalidContentException, UnknownServiceException,
			ServiceAdapterFaultException, UpdateException, ReadOnlyException
	{
		// TODO -- Lookup the message
		
		// If found start the alert update process

		// Save alert changes (Limited scope)

		// Find if there is a UCS Client that need to be notified

		return false;

	}

}
