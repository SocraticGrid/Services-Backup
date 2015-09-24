package org.socraticgrid.hl7.services.orders.functional;

import org.socraticgrid.hl7.services.orders.internal.interfaces.IdGeneratorIFace;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 16-Dec-2013 3:54:57 PM
 */
public class MessageIdGenerator {
	
	IdGeneratorIFace generator;
	
	public MessageIdGenerator(){

	}


	public String getNewMessageId()
	{
		//Find the IdGeneratorIFace instance
	
		return generator.getNewId();
	}

	public IdGeneratorIFace getGenerator()
	{
		return generator;
	}

	public void setGenerator(IdGeneratorIFace generator)
	{
		this.generator = generator;
	}
}