package org.socraticgrid.hl7.services.uc.internal.interfaces;

import org.socraticgrid.hl7.services.uc.exceptions.UCSException;
import org.socraticgrid.hl7.services.uc.model.Message;
import org.socraticgrid.hl7.services.uc.model.MessageModel;

/**
 * Interface for 
 * @author Jerry Goodnough
 * @version 1.0
 * @created 16-Dec-2013 3:54:58 PM
 */
public interface ValidatorStepIntf {
	public  <T extends Message>  boolean validateMessage(MessageModel<T> msg) throws UCSException;
}