package org.socraticgrid.hl7.services.uc.functional;

import org.socraticgrid.hl7.services.uc.internal.interfaces.IdGeneratorIntf;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 16-Dec-2013 3:54:57 PM
 */
public class MessageIdGenerator {
	
	IdGeneratorIntf generator;


	public String getNewMessageId()
	{
		//Find the IdGeneratorIntf instance
		return generator.getNewId();
	}

	public IdGeneratorIntf getGenerator()
	{
		return generator;
	}

	public void setGenerator(IdGeneratorIntf generator)
	{
		this.generator = generator;
	}
}