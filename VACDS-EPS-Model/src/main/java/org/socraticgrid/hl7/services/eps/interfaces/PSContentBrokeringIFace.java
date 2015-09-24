package org.socraticgrid.hl7.services.eps.interfaces;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import org.socraticgrid.hl7.services.eps.exceptions.AuthenicationRequiredException;
import org.socraticgrid.hl7.services.eps.exceptions.ExpiredException;
import org.socraticgrid.hl7.services.eps.exceptions.NoSuchItemException;
import org.socraticgrid.hl7.services.eps.exceptions.NotAuthorizedException;
import org.socraticgrid.hl7.services.eps.model.MessageBody;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 04-Jan-2014 6:15:20 PM
 */
@WebService(name = "pscontentbroker", targetNamespace = "org.socraticgrid.hl7.services.eps")
public interface PSContentBrokeringIFace {

	/**
	 * 
	 * @param MessageId
	 * @param Forms
	 */
	public MessageBody getContent(
			@WebParam(name = "messageId") String messageId,
			@WebParam(name = "forms") List<String> forms)
			throws NotAuthorizedException, AuthenicationRequiredException,
			ExpiredException, NoSuchItemException;

	/**
	 * 
	 * @param Messageid
	 */
	public MessageBody getPreferredContent(
			@WebParam(name = "messageId") String messageId)
			throws NotAuthorizedException, AuthenicationRequiredException,
			ExpiredException, NoSuchItemException;

	/**
	 * 
	 * @param MessageId
	 * @param Form
	 */
	public boolean supportsContentForm(
			@WebParam(name = "messageId") String messageId,
			@WebParam(name = "form") String form)
			throws NotAuthorizedException, AuthenicationRequiredException,
			ExpiredException, NoSuchItemException;

}