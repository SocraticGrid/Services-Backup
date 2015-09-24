package org.socraticgrid.hl7.services.uc.adapters.alerting;

import java.util.List;
import java.util.Map;

import javax.xml.ws.Binding;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.EndpointReference;

import org.socraticgrid.hl7.services.uc.model.Message;
import org.socraticgrid.hl7.services.uc.model.MessageModel;
import org.socraticgrid.hl7.services.ucs.accessclients.ucsalerting.UCSAlertingServiceSEI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class MockSEI implements BindingProvider, UCSAlertingServiceSEI {
	@Autowired
	@Qualifier("SEI")
	UCSAlertingServiceSEI mockDelgate;
	
	private List<String> lastReceiverList;
	
	Map<String,Object> requestContext;
	
	public void setRequestContext(Map<String,Object> requestContext)
	{
		this.requestContext=requestContext;
	}
	
	public UCSAlertingServiceSEI getmockDelgate()
	{
		return mockDelgate;
	}

	@Override
	public Map<String, Object> getRequestContext() {
		return requestContext;
	}

	@Override
	public Map<String, Object> getResponseContext() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Binding getBinding() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EndpointReference getEndpointReference() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends EndpointReference> T getEndpointReference(Class<T> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends Message> boolean receiveAlertMessage(
			MessageModel<T> messageModel, List<String> localReceivers, String serverId) {
		lastReceiverList = localReceivers;
		
		return mockDelgate.receiveAlertMessage(messageModel, localReceivers, serverId);
	}

	@Override
	public <T extends Message> boolean updateAlertMessage(
			MessageModel<T> newMessageModel, MessageModel<T> oldMessageModel,
			List<String> localReceivers, String serverId) {
		lastReceiverList = localReceivers;
		
		return mockDelgate.updateAlertMessage(newMessageModel, oldMessageModel, localReceivers, serverId);
	}

	@Override
	public <T extends Message> boolean cancelAlertMessage(
			MessageModel<T> messageModel, List<String> localReceivers, String serverId) {
		lastReceiverList = localReceivers;
		return mockDelgate.cancelAlertMessage(messageModel, localReceivers, serverId);
	}

	/**
	 * @return the lastReceiverList
	 */
	public List<String> getLastReceiverList() {
		return lastReceiverList;
	}

	/**
	 * @param lastReceiverList the lastReceiverList to set
	 */
	public void setLastReceiverList(List<String> lastReceiverList) {
		this.lastReceiverList = lastReceiverList;
	}

}
