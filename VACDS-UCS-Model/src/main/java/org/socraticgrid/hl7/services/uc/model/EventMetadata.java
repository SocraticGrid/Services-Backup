/**
 * 
 */
package org.socraticgrid.hl7.services.uc.model;

import java.io.Serializable;

/**
 * @author Jerry Goodnough
 *
 */
public class EventMetadata implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public EventMetadata()
	{
		// TODO Auto-generated constructor stub
	}

	private EventType type;

	public EventType getType()
	{
		return type;
	}

	public void setType(EventType type)
	{
		this.type = type;
	}
}
