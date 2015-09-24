package org.socraticgrid.hl7.services.uc.model;

import java.util.LinkedList;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 16-Dec-2013 3:54:56 PM
 */
public class DeliveryGroup
{

	private String name;
	private LinkedList<DeliveryAddress> group;
	private UserContactInfo userContactInfo;

	public DeliveryGroup()
	{

	}



	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public LinkedList<DeliveryAddress> getGroup()
	{
		return group;
	}

	public void setGroup(LinkedList<DeliveryAddress> group)
	{
		this.group = group;
	}

	public UserContactInfo getUserContactInfo()
	{
		return userContactInfo;
	}

	public void setUserContactInfo(UserContactInfo userContactInfo)
	{
		this.userContactInfo = userContactInfo;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DeliveryGroup [name=" + name + ", group=" + group
				+ ", userContactInfo=" + userContactInfo + "]";
	}

}