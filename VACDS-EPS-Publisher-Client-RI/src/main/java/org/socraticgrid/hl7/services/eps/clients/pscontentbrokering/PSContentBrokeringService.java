package org.socraticgrid.hl7.services.eps.clients.pscontentbrokering;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import org.socraticgrid.hl7.services.eps.exceptions.AuthenicationRequiredException;
import org.socraticgrid.hl7.services.eps.exceptions.ExpiredException;
import org.socraticgrid.hl7.services.eps.exceptions.NoSuchItemException;
import org.socraticgrid.hl7.services.eps.exceptions.NotAuthorizedException;
import org.socraticgrid.hl7.services.eps.interfaces.PSContentBrokeringIFace;
import org.socraticgrid.hl7.services.eps.model.MessageBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@WebService(name = "PSContentBrokering", targetNamespace = "org.socraticgrid.hl7.services.eps.clients")
public class PSContentBrokeringService implements PSContentBrokeringIFace{

	@Autowired
	@Qualifier("PSContentBrokeringImpl")
	PSContentBrokeringIFace psContentBrokeringImpl;

	public PSContentBrokeringService() {
	}

	public MessageBody getContent(
			@WebParam(name = "messageId") String messageId,
			@WebParam(name = "forms") List<String> forms)
			throws NotAuthorizedException, AuthenicationRequiredException,
			ExpiredException, NoSuchItemException {
		return psContentBrokeringImpl.getContent(messageId, forms);
	}

	public MessageBody getPreferredContent(
			@WebParam(name = "messageId") String messageId)
			throws NotAuthorizedException, AuthenicationRequiredException,
			ExpiredException, NoSuchItemException {
		return psContentBrokeringImpl.getPreferredContent(messageId);
	}

	public boolean supportsContentForm(
			@WebParam(name = "messageId") String messageId,
			@WebParam(name = "form") String form)
			throws NotAuthorizedException, AuthenicationRequiredException,
			ExpiredException, NoSuchItemException {

		return psContentBrokeringImpl.supportsContentForm(messageId, form);
	}
}
