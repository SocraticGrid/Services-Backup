package org.socraticgrid.hl7.services.eps.clients.pspublicationintervention;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;


import org.socraticgrid.hl7.services.eps.model.Message;
import org.socraticgrid.hl7.services.eps.model.ReviewResult;
import org.socraticgrid.hl7.services.eps.interfaces.PSPublicationInterventionIFace;

@WebService(name = "PSPublicationReview", targetNamespace = "org.socraticgrid.hl7.services.eps.clients")
public class PSPublicationInterventionService implements PSPublicationInterventionIFace {

	
	@WebMethod(exclude = true)
	public PSPublicationInterventionIFace getPublicationInterventionImpl() {
		return publicationInterventionImpl;
	}
	@WebMethod(exclude = true)
	public void setPublicationInterventionImpl(
			PSPublicationInterventionIFace publicationInterventionImpl) {
		this.publicationInterventionImpl = publicationInterventionImpl;
	}

	private PSPublicationInterventionIFace publicationInterventionImpl;

	public PSPublicationInterventionService() {
	}

	public ReviewResult reviewMessage(@WebParam(name = "message") Message message) {
	
		return publicationInterventionImpl.reviewMessage(message);
	}


}
