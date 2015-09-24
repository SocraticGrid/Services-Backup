/**
 * 
 */
package org.socraticgrid.hl7.services.eps.service.impl;

import org.socraticgrid.hl7.services.eps.exceptions.FeatureNotAvailableException;
import org.socraticgrid.hl7.services.eps.exceptions.NoSuchItemException;
import org.socraticgrid.hl7.services.eps.exceptions.NoSuchTopicException;
import org.socraticgrid.hl7.services.eps.exceptions.NotAuthorizedException;
import org.socraticgrid.hl7.services.eps.interfaces.FederationIFace;
import org.socraticgrid.hl7.services.eps.model.FederationMap;
import org.socraticgrid.hl7.services.eps.model.FederationSource;
import org.socraticgrid.hl7.services.eps.model.FederationTarget;

/**
 * @author Jerry Goodnough
 *
 */
public class FederationServiceImpl implements FederationIFace {

	/**
	 * 
	 */
	public FederationServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.socraticgrid.hl7.services.eps.interfaces.FederationIFace#GetMasterFederationMap()
	 */
	@Override
	public FederationMap GetMasterFederationMap()
			throws NotAuthorizedException, 
			FeatureNotAvailableException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.socraticgrid.hl7.services.eps.interfaces.FederationIFace#UpdateSource(java.lang.String, org.socraticgrid.hl7.services.eps.model.FederationSource)
	 */
	@Override
	public boolean UpdateSource(String sourceId, FederationSource sourceInfo)
			throws NotAuthorizedException, 
			FeatureNotAvailableException, NoSuchItemException {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.socraticgrid.hl7.services.eps.interfaces.FederationIFace#GetSourceMap(java.lang.String)
	 */
	@Override
	public FederationMap GetSourceMap(String sourceId)
			throws NotAuthorizedException, 
			FeatureNotAvailableException, NoSuchItemException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.socraticgrid.hl7.services.eps.interfaces.FederationIFace#UpdateSourceMap(java.lang.String, org.socraticgrid.hl7.services.eps.model.FederationMap)
	 */
	@Override
	public boolean UpdateSourceMap(String sourceId, FederationMap sourceMap)
			throws NotAuthorizedException, 
			FeatureNotAvailableException, NoSuchItemException,
			NoSuchTopicException {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.socraticgrid.hl7.services.eps.interfaces.FederationIFace#UpdateTarget(java.lang.String, org.socraticgrid.hl7.services.eps.model.FederationTarget)
	 */
	@Override
	public boolean UpdateTarget(String targetId, FederationTarget targetInfo)
			throws NotAuthorizedException, 
			FeatureNotAvailableException, NoSuchItemException {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.socraticgrid.hl7.services.eps.interfaces.FederationIFace#GetTargetMap(java.lang.String)
	 */
	@Override
	public FederationMap GetTargetMap(String targetId)
			throws NotAuthorizedException, 
			FeatureNotAvailableException, NoSuchItemException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.socraticgrid.hl7.services.eps.interfaces.FederationIFace#UpdateTargetMap(java.lang.String, org.socraticgrid.hl7.services.eps.model.FederationMap)
	 */
	@Override
	public boolean UpdateTargetMap(String targetId, FederationMap targetMap)
			throws NotAuthorizedException, 
			FeatureNotAvailableException, NoSuchItemException,
			NoSuchTopicException {
		// TODO Auto-generated method stub
		return false;
	}

}
