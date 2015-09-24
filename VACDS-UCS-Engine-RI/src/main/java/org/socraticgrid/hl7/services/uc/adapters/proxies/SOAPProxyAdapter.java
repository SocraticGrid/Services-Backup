package org.socraticgrid.hl7.services.uc.adapters.proxies;

import java.util.List;

import javax.xml.ws.BindingProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.socraticgrid.hl7.services.uc.interfaces.UCSAdapterIntf;
import org.socraticgrid.hl7.services.uc.model.AdapterMessage;
import org.socraticgrid.hl7.services.uc.model.InteractionTypes;
import org.socraticgrid.hl7.services.ucs.accessclients.ucsadapter.UCSAdapterServiceSE;
import org.socraticgrid.hl7.services.ucs.accessclients.ucsadapter.UCSAdapterServiceSEI;

/**
 * The SOAProxyAdapter provides a bridge between the internal form of the
 * UCSAdapter contract and a SOAP implementation of the interface.
 * 
 * @author Jerry Goodnough
 * 
 */
public class SOAPProxyAdapter implements UCSAdapterIntf
{
	private Logger log = LoggerFactory.getLogger(SOAPProxyAdapter.class);

	private String serviceName;

	public String getServiceName()
	{
		return serviceName;
	}

	public void setServiceName(String serviceName)
	{
		this.serviceName = serviceName;
	}

	private String endPoint;

	public String getEndPoint()
	{
		return endPoint;
	}

	public void setEndPoint(String endPoint)
	{
		this.endPoint = endPoint;
	}

	@Override
	public boolean sendMessage(AdapterMessage message, String serverId)
	{

		UCSAdapterServiceSE ss = new UCSAdapterServiceSE();
		
		UCSAdapterServiceSEI port = ss.getUCSAdapterPort();
		((BindingProvider)port).getRequestContext().put(
        	    BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
        	    endPoint);
		boolean out = false;
		
		log.debug("*********\n\tSending message sent to "+endPoint+"\n*********");
		out = port.sendMessage(message, serverId);
		return out;
	}

	@Override
	public boolean updateMessage(AdapterMessage newMessage, AdapterMessage oldMessage, String serverId)
	{

		UCSAdapterServiceSE ss = new UCSAdapterServiceSE();
		
		UCSAdapterServiceSEI port = ss.getUCSAdapterPort();
		((BindingProvider)port).getRequestContext().put(
        	    BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
        	    endPoint);
		boolean out = false;
		
		out = port.updateMessage(newMessage, oldMessage, serverId);
		return out;
	}

	@Override
	public boolean cancelMessage(AdapterMessage message, String serverId)
	{

		UCSAdapterServiceSE ss = new UCSAdapterServiceSE();
		
		UCSAdapterServiceSEI port = ss.getUCSAdapterPort();
		((BindingProvider)port).getRequestContext().put(
        	    BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
        	    endPoint);
		boolean out = false;
		
		out = port.cancelMessage(message, serverId);
		return out;
	}

	@Override
	public String getServiceId()
	{
		return this.serviceName;
	}

	@Override
	public List<InteractionTypes> getInteractionTypes()
	{

		UCSAdapterServiceSE ss = new UCSAdapterServiceSE();
		
		UCSAdapterServiceSEI port = ss.getUCSAdapterPort();
		((BindingProvider)port).getRequestContext().put(
        	    BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
        	    endPoint);
		List<InteractionTypes> out = null;
		
		out = port.getInteractionTypes();
		return out;
	}

}
