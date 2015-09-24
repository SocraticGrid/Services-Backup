package org.socraticgrid.hl7.services.eps.engines.publicationintervention;

import org.socraticgrid.hl7.services.eps.interfaces.PSPublicationInterventionIFace;
import org.socraticgrid.hl7.services.eps.model.Message;
import org.socraticgrid.hl7.services.eps.model.ReviewAction;
import org.socraticgrid.hl7.services.eps.model.ReviewResult;


public class RejectAllEngine   implements PSPublicationInterventionIFace {


	public RejectAllEngine() {

	}

	@Override
	public ReviewResult reviewMessage(Message Message) {
		ReviewResult result = new ReviewResult();
		result.setAltered(false);
		result.setAction(ReviewAction.Rejected);
		return result;
	}



}
