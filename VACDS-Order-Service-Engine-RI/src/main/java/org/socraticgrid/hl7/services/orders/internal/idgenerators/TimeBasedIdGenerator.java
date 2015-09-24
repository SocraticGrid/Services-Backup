package org.socraticgrid.hl7.services.orders.internal.idgenerators;

import org.socraticgrid.hl7.services.orders.internal.interfaces.IdGeneratorIFace;

/**
 * Simple Millisecond Id generator - Suitable only for single threaded testing and Proof of concept work.
 * Should nver be used in a production environment
 * @author Jerry Goodnough
 *
 */
public class TimeBasedIdGenerator implements IdGeneratorIFace
{

	@Override
	public String getNewId()
	{
		return Long.toString(System.currentTimeMillis());
	}

}
