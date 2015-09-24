/**
 * 
 */
package org.socraticgrid.hl7.services.eps.service.impl;

import java.util.List;

import org.socraticgrid.hl7.services.eps.exceptions.AuthenicationRequiredException;
import org.socraticgrid.hl7.services.eps.exceptions.ConflictException;
import org.socraticgrid.hl7.services.eps.exceptions.FeatureNotAvailableException;
import org.socraticgrid.hl7.services.eps.exceptions.InvalidDataException;
import org.socraticgrid.hl7.services.eps.exceptions.NoSuchItemException;
import org.socraticgrid.hl7.services.eps.exceptions.NoSuchTopicException;
import org.socraticgrid.hl7.services.eps.exceptions.NotAuthorizedException;
import org.socraticgrid.hl7.services.eps.interfaces.TopicManagementIFace;
import org.socraticgrid.hl7.services.eps.model.AccessRequest;
import org.socraticgrid.hl7.services.eps.model.Affiliation;
import org.socraticgrid.hl7.services.eps.model.AffiliationMapping;
import org.socraticgrid.hl7.services.eps.model.AffiliationRole;

/**
 * @author Jerry Goodnough
 *
 */
public class TopicManagementServiceImpl implements TopicManagementIFace {

	/**
	 * 
	 */
	public TopicManagementServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.socraticgrid.hl7.services.eps.interfaces.TopicManagementIFace#createAffiliation(java.lang.String, java.lang.String, org.socraticgrid.hl7.services.eps.model.AffiliationRole)
	 */
	@Override
	public boolean createAffiliation(String topic, String userId,
			AffiliationRole role) throws NotAuthorizedException,
			AuthenicationRequiredException, NoSuchTopicException,
			NoSuchItemException, InvalidDataException {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.socraticgrid.hl7.services.eps.interfaces.TopicManagementIFace#getAffiliationsForTopic(java.lang.String)
	 */
	@Override
	public List<Affiliation> getAffiliationsForTopic(String topicId)
			throws NotAuthorizedException, AuthenicationRequiredException,
			NoSuchTopicException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.socraticgrid.hl7.services.eps.interfaces.TopicManagementIFace#getAffiliationsForUser(java.lang.String)
	 */
	@Override
	public List<AffiliationMapping> getAffiliationsForUser(String userId)
			throws NotAuthorizedException, AuthenicationRequiredException,
			NoSuchItemException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.socraticgrid.hl7.services.eps.interfaces.TopicManagementIFace#updateAffiliation(java.lang.String, java.lang.String, org.socraticgrid.hl7.services.eps.model.AffiliationRole)
	 */
	@Override
	public boolean updateAffiliation(String topic, String userId,
			AffiliationRole role) throws NotAuthorizedException,
			AuthenicationRequiredException, NoSuchTopicException,
			ConflictException {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.socraticgrid.hl7.services.eps.interfaces.TopicManagementIFace#deleteAffiliation(java.lang.String, java.lang.String, org.socraticgrid.hl7.services.eps.model.AffiliationRole)
	 */
	@Override
	public boolean deleteAffiliation(String topic, String userId,
			AffiliationRole role) throws NotAuthorizedException,
			AuthenicationRequiredException, NoSuchTopicException {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.socraticgrid.hl7.services.eps.interfaces.TopicManagementIFace#getPendingAccessRequests(java.lang.String)
	 */
	@Override
	public List<AccessRequest> getPendingAccessRequests(String topic)
			throws NotAuthorizedException, AuthenicationRequiredException,
			NoSuchTopicException, FeatureNotAvailableException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.socraticgrid.hl7.services.eps.interfaces.TopicManagementIFace#grantAccessRequest(org.socraticgrid.hl7.services.eps.model.AccessRequest)
	 */
	@Override
	public boolean grantAccessRequest(AccessRequest request)
			throws NotAuthorizedException, AuthenicationRequiredException,
			ConflictException, FeatureNotAvailableException {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.socraticgrid.hl7.services.eps.interfaces.TopicManagementIFace#rejectAccessRequest(org.socraticgrid.hl7.services.eps.model.AccessRequest)
	 */
	@Override
	public boolean rejectAccessRequest(AccessRequest request)
			throws NotAuthorizedException, AuthenicationRequiredException,
			ConflictException, FeatureNotAvailableException {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.socraticgrid.hl7.services.eps.interfaces.TopicManagementIFace#processPendingAccessRequests(java.lang.String)
	 */
	@Override
	public boolean processPendingAccessRequests(String topic)
			throws NotAuthorizedException, AuthenicationRequiredException,
			NoSuchTopicException, FeatureNotAvailableException {
		// TODO Auto-generated method stub
		return false;
	}

}
