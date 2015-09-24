package org.socraticgrid.hl7.services.uc.model;

import java.io.Serializable;
import java.util.List;

public class Status implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String capability;
	private boolean supported;
	private boolean available;
	private List<String> supportedBy;
	private String statusText;
	

	public Status()
	{
		// TODO Auto-generated constructor stub
	}


	public String getCapability()
	{
		return capability;
	}


	public void setCapability(String capability)
	{
		this.capability = capability;
	}


	public boolean isSupported()
	{
		return supported;
	}


	public void setSupported(boolean supported)
	{
		this.supported = supported;
	}


	public boolean isAvailable()
	{
		return available;
	}


	public void setAvailable(boolean available)
	{
		this.available = available;
	}


	public List<String> getSupportedBy()
	{
		return supportedBy;
	}


	public void setSupportedBy(List<String> supportedBy)
	{
		this.supportedBy = supportedBy;
	}


	public String getStatusText()
	{
		return statusText;
	}


	public void setStatusText(String statusText)
	{
		this.statusText = statusText;
	}

}
