package org.socraticgrid.hl7.services.uc.model;

import java.io.Serializable;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 17-Dec-2013 10:37:13 AM
 */
public class Role implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Role()
	{

	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Role [name=" + name + "]";
	}



}