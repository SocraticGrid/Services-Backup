package org.socraticgrid.hl7.services.orders.model.primatives;

import java.io.Serializable;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 16-Jan-2014 9:12:40 AM
 */
public class Code implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String code;
	String codeSystem;
	
	String text;
	String label;
	
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the codeSystem
	 */
	public String getCodeSystem() {
		return codeSystem;
	}
	/**
	 * @param codeSystem the codeSystem to set
	 */
	public void setCodeSystem(String codeSystem) {
		this.codeSystem = codeSystem;
	}
	
	
	//TODO Add the reset if the HL7 code model here
}