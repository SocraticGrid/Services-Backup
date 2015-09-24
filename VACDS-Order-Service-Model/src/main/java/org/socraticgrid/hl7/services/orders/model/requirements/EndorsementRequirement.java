package org.socraticgrid.hl7.services.orders.model.requirements;

import java.util.Properties;

public class EndorsementRequirement extends Requirement {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String userId;
	private String seed;
	private String signature;
	private String scheme;
	private Properties properties;
	
	public EndorsementRequirement()
	{
		super();
		this.type=RequirementType.Endorsement;
		
	}
	
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return the seed - This value may be provided by the fulfiller as a value to be hashed to sign. In other words this is a nounce.
	 */
	public String getSeed() {
		return seed;
	}
	/**
	 * @param seed the seed to set 
	 */
	public void setSeed(String seed) {
		this.seed = seed;
	}
	/**
	 * @return the signature
	 */
	public String getSignature() {
		return signature;
	}
	/**
	 * @param signature the signature to set
	 */
	public void setSignature(String signature) {
		this.signature = signature;
	}

	/**
	 * @return the scheme
	 */
	public String getScheme() {
		return scheme;
	}

	/**
	 * @param scheme the scheme to set
	 */
	public void setScheme(String scheme) {
		this.scheme = scheme;
	}

	/**
	 * @return the properties
	 */
	public Properties getProperties() {
		return properties;
	}

	/**
	 * @param properties the properties to set
	 */
	public void setProperties(Properties properties) {
		this.properties = properties;
	}
	
	@Override 
	public void setType(RequirementType newVal){
		//
	}
	

}
