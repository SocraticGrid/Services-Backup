package org.socraticgrid.hl7.services.eps.interfaces;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.socraticgrid.hl7.services.eps.model.Message;
import org.socraticgrid.hl7.services.eps.model.ReviewResult;
/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 04-Jan-2014 6:15:20 PM
 */
@WebService(name = "PSPublicationReview", targetNamespace = "org.socraticgrid.hl7.services.eps.clients")
public interface PSPublicationInterventionIFace {

	/**
	 * 
	 * @param Message
	 */
	public ReviewResult reviewMessage(@WebParam(name = "message")Message message);

}