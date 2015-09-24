package org.socraticgrid.hl7.services.eps.accessclients.topicmanagement;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.socraticgrid.hl7.services.eps.exceptions.AuthenicationRequiredException;
import org.socraticgrid.hl7.services.eps.exceptions.ConflictException;
import org.socraticgrid.hl7.services.eps.exceptions.FeatureNotAvailableException;
import org.socraticgrid.hl7.services.eps.exceptions.InvalidDataException;
import org.socraticgrid.hl7.services.eps.exceptions.NoSuchItemException;
import org.socraticgrid.hl7.services.eps.exceptions.NoSuchTopicException;
import org.socraticgrid.hl7.services.eps.exceptions.NotAuthorizedException;
import org.socraticgrid.hl7.services.eps.model.AccessRequest;
import org.socraticgrid.hl7.services.eps.model.Affiliation;
import org.socraticgrid.hl7.services.eps.model.AffiliationMapping;
import org.socraticgrid.hl7.services.eps.model.AffiliationRole;

@WebService(targetNamespace = "org.socraticgrid.hl7.services.eps.clients", name = "TopicManagementServiceService")
public interface TopicManagementServiceSEI {

	@WebMethod
	public boolean createAffiliation(String topic, String userId,
			AffiliationRole role) throws NotAuthorizedException,
			AuthenicationRequiredException, NoSuchTopicException,
			NoSuchItemException, InvalidDataException;

	@WebMethod
	public List<Affiliation> getAffiliationsForTopic(String topicId)
			throws NotAuthorizedException, AuthenicationRequiredException,
			NoSuchTopicException;

	@WebMethod
	public List<AffiliationMapping> getAffiliationsForUser(String userId)
			throws NotAuthorizedException, AuthenicationRequiredException,
			NoSuchItemException;

	@WebMethod
	public boolean updateAffiliation(String topic, String userId,
			AffiliationRole role) throws NotAuthorizedException,
			AuthenicationRequiredException, NoSuchTopicException,
			ConflictException;

	@WebMethod
	public boolean deleteAffiliation(String topic, String userId,
			AffiliationRole role) throws NotAuthorizedException,
			AuthenicationRequiredException, NoSuchTopicException;

	@WebMethod
	public List<AccessRequest> getPendingAccessRequests(String topic)
			throws NotAuthorizedException, AuthenicationRequiredException,
			NoSuchTopicException, FeatureNotAvailableException;

	@WebMethod
	public boolean grantAccessRequest(AccessRequest request)
			throws NotAuthorizedException, AuthenicationRequiredException,
			ConflictException, FeatureNotAvailableException;

	@WebMethod
	public boolean rejectAccessRequest(AccessRequest request)
			throws NotAuthorizedException, AuthenicationRequiredException,
			ConflictException, FeatureNotAvailableException;

	@WebMethod
	public boolean processPendingAccessRequests(String topic)
			throws NotAuthorizedException, AuthenicationRequiredException,
			NoSuchTopicException, FeatureNotAvailableException;

}
