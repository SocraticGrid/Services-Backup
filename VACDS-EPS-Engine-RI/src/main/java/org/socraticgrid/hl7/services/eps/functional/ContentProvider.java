package org.socraticgrid.hl7.services.eps.functional;

import java.util.List;

import org.socraticgrid.hl7.services.eps.exceptions.AuthenicationRequiredException;
import org.socraticgrid.hl7.services.eps.exceptions.ExpiredException;
import org.socraticgrid.hl7.services.eps.exceptions.NoSuchItemException;
import org.socraticgrid.hl7.services.eps.exceptions.NotAuthorizedException;
import org.socraticgrid.hl7.services.eps.interfaces.PSContentBrokeringIFace;
import org.socraticgrid.hl7.services.eps.model.MessageBody;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 04-Jan-2014 6:15:19 PM
 */
public class ContentProvider implements PSContentBrokeringIFace {

	public ContentProvider(){

	}

	@Override
	public MessageBody getContent(String messageId, List<String> forms)
			throws NotAuthorizedException, AuthenicationRequiredException,
			ExpiredException, NoSuchItemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MessageBody getPreferredContent(String messageId)
			throws NotAuthorizedException, AuthenicationRequiredException,
			ExpiredException, NoSuchItemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean supportsContentForm(String messageId, String form)
			throws NotAuthorizedException, AuthenicationRequiredException,
			ExpiredException, NoSuchItemException {
		// TODO Auto-generated method stub
		return false;
	}


}