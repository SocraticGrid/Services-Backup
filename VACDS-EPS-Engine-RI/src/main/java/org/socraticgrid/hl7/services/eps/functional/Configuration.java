package org.socraticgrid.hl7.services.eps.functional;

public class Configuration {

	/*
	 * Are publications submissions checked prior to handing over to publication intervnetion?
	 */
	private boolean publicationPreCheck;

	/**
	 * @return the publicationPreCheck
	 */
	public boolean isPublicationPreCheck() {
		return publicationPreCheck;
	}

	/**
	 * @param publicationPreCheck the publicationPreCheck to set
	 */
	public void setPublicationPreCheck(boolean publicationPreCheck) {
		this.publicationPreCheck = publicationPreCheck;
	}
	
	
}
