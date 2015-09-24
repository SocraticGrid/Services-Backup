package org.socraticgrid.hl7.services.uc.interfaces;

import org.socraticgrid.hl7.services.uc.exceptions.InvalidContentException;
import org.socraticgrid.hl7.services.uc.exceptions.InvalidMessageException;
import org.socraticgrid.hl7.services.uc.exceptions.ReadOnlyException;
import org.socraticgrid.hl7.services.uc.exceptions.ServiceAdapterFaultException;
import org.socraticgrid.hl7.services.uc.exceptions.UnknownServiceException;
import org.socraticgrid.hl7.services.uc.exceptions.UpdateException;
import org.socraticgrid.hl7.services.uc.model.AlertMessage;


/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 16-Dec-2013 3:54:55 PM
 */
public interface AlertingIntf {

	public boolean updateAlert(AlertMessage alert) 
			throws InvalidMessageException, 
					InvalidContentException, 
					UnknownServiceException, 
					ServiceAdapterFaultException, 
					UpdateException, 
					ReadOnlyException;

}