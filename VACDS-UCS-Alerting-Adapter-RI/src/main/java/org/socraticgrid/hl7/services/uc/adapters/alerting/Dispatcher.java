package org.socraticgrid.hl7.services.uc.adapters.alerting;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.xml.ws.BindingProvider;

import org.apache.commons.validator.routines.UrlValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.socraticgrid.hl7.services.uc.adapters.alerting.interfaces.AddressResolverIntf;
import org.socraticgrid.hl7.services.uc.adapters.alerting.interfaces.DispatcherIntf;
import org.socraticgrid.hl7.services.uc.exceptions.ExceptionType;
import org.socraticgrid.hl7.services.uc.exceptions.InvalidConversationException;
import org.socraticgrid.hl7.services.uc.exceptions.InvalidMessageException;
import org.socraticgrid.hl7.services.uc.exceptions.ProcessingException;
import org.socraticgrid.hl7.services.uc.interfaces.AdapterIntf;
import org.socraticgrid.hl7.services.uc.model.DeliveryAddress;
import org.socraticgrid.hl7.services.uc.model.Message;
import org.socraticgrid.hl7.services.uc.model.MessageModel;
import org.socraticgrid.hl7.services.uc.model.PhysicalAddress;
import org.socraticgrid.hl7.services.ucs.accessclients.ucsalerting.UCSAlertingServiceSE;
import org.socraticgrid.hl7.services.ucs.accessclients.ucsalerting.UCSAlertingServiceSEI;
import org.springframework.beans.factory.annotation.Autowired;
 

public class Dispatcher implements DispatcherIntf {
	
	//TODO Update for a consolidation contract
	//TODO Move validation up another level to deal address resolution at a new level
	
	AdapterIntf adapter;

	AddressResolverIntf addressResolver;

	@Autowired(required=false)
	UCSAlertingServiceSE alertingServiceSE;
	

	Configuration configuration;
	

	private final Logger logger = LoggerFactory.getLogger(Dispatcher.class);
	
	public static Dispatcher createDispatcher()
	{
		Dispatcher out = new Dispatcher();
		out.init();
		return out;
	}

	
	@Override
	public boolean cancelMessage(PhysicalAddress address,
			Message msg, String serverId) {
		boolean out = false;
		ResolvedAddress resAddr = addressResolver.resolveAddress(address);
		if(ValidateaAndRefineAddress(resAddr,address,msg)==true)
		{
			     
			//UCSAlertingServiceSE ss = new UCSAlertingServiceSE();
		     
		    //Get Port
			UCSAlertingServiceSEI port = alertingServiceSE.getUcsAlertingPort();
		    //Change Endpoint
		    ((BindingProvider)port).getRequestContext().put(
		            BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
		            resAddr.getEndPoint());	
		    MessageModel<Message> msgOut = new MessageModel<Message>();
		    msgOut.setMessageType(msg);
		    List<String> remoteIds = new ArrayList<String>(1);
		    remoteIds.add(resAddr.remoteId);
		    out = port.cancelAlertMessage(msgOut,remoteIds,serverId);
		    
		}
		return out;
	}

	/**
	 * @return the adapter
	 */
	public AdapterIntf getAdapter() {
		return adapter;
	}
	
	/**
	 * @return the addressResolver
	 */
	public AddressResolverIntf getAddressResolver() {
		return addressResolver;
	}

	
	/**
	 * @return the alertingServiceSE
	 */
	public UCSAlertingServiceSE getAlertingServiceSE() {
		return alertingServiceSE;
	}

	/**
	 * @return the configuration
	 */
	public Configuration getConfiguration() {
		return configuration;
	}

	@PostConstruct
	public void init()
	{
		if (addressResolver == null)
		{
			logger.warn("Using stock Address Resolver");
			addressResolver = new AddressResolver();
		}
		
		addressResolver.setConfiguration(configuration);
		
		if (alertingServiceSE == null)
		{
			alertingServiceSE = new UCSAlertingServiceSE();
		}

	}

	protected void postDispatchException(PhysicalAddress address, Message msg, ExceptionType exceptionType,  String fault, String typeSpecificContext)
	{
		logger.info("Dispatch Exception for "+address.toString());
		String messageId = msg.getHeader().getMessageId();
		ProcessingException exp = new ProcessingException(exceptionType, configuration.getServiceId(), messageId, fault,  typeSpecificContext);
		
		logger.warn("Dispatch Exception: "+exp.toString());
		
		try {
			adapter.postException(exp, exceptionType,messageId, new DeliveryAddress(address),  configuration.getServiceId());
		} catch (InvalidMessageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidConversationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	@Override
	public boolean sendMessage(PhysicalAddress address,
			Message msg, String serverId) {
		boolean out = false;
		if (addressResolver == null)
		{
			logger.warn("Automatic initialization is failing");
			System.out.println("Automatic initialization is failing");
			this.init();
		}
		ResolvedAddress resAddr = addressResolver.resolveAddress(address);
		if(ValidateaAndRefineAddress(resAddr,address,msg)==true)
		{
			        
		    //Get Port
			UCSAlertingServiceSEI port = alertingServiceSE.getUcsAlertingPort();
		    //Change Endpoint
		    ((BindingProvider)port).getRequestContext().put(
		            BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
		            resAddr.getEndPoint());	
		    MessageModel<Message> msgOut = new MessageModel<Message>();
		    msgOut.setMessageType(msg);
		    List<String> remoteIds = new ArrayList<String>(1);
		    remoteIds.add(resAddr.remoteId);
		    out = port.receiveAlertMessage(msgOut,remoteIds, serverId);
		    
		}
		return out;
	}

	/**
	 * @param adapter the adapter to set
	 */
	public void setAdapter(AdapterIntf adapter) {
		this.adapter = adapter;
	}

	/**
	 * @param addressResolver the addressResolver to set
	 */
	public void setAddressResolver(AddressResolverIntf addressResolver) {
		this.addressResolver = addressResolver;
	}

	/**
	 * @param alertingServiceSE the alertingServiceSE to set
	 */
	public void setAlertingServiceSE(UCSAlertingServiceSE alertingServiceSE) {
		this.alertingServiceSE = alertingServiceSE;
	}
	/**
	 * @param configuration the configuration to set
	 */
	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}
	
	
	@Override
	public boolean updateMessage(PhysicalAddress address,
			Message msg, String serverId) {
		boolean out = false;
		ResolvedAddress resAddr = addressResolver.resolveAddress(address);
		if(ValidateaAndRefineAddress(resAddr,address,msg)==true)
		{
			     
			//UCSAlertingServiceSE ss = new UCSAlertingServiceSE();
		     
		    //Get Port
			UCSAlertingServiceSEI port = alertingServiceSE.getUcsAlertingPort();
		    //Change Endpoint
		    ((BindingProvider)port).getRequestContext().put(
		            BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
		            resAddr.getEndPoint());	
		    MessageModel<Message> msgOut = new MessageModel<Message>();
		    msgOut.setMessageType(msg);
		    List<String> remoteIds = new ArrayList<String>(1);
		    remoteIds.add(resAddr.remoteId);

		    out = port.updateAlertMessage(msgOut, msgOut,remoteIds, serverId);
		    
		}
		return out;
	}

	protected boolean ValidateaAndRefineAddress(ResolvedAddress resAddr, PhysicalAddress address,
			Message msg)
	{
		Map<String,String> aliasMap = configuration.getEndpointMap();
		
		if (configuration.isRequireAliasUse())
		{
			String key = resAddr.getEndPoint();
			if (aliasMap.containsKey(key))
			{
				resAddr.setEndPoint(aliasMap.get(key));
				return true;
			}
			else
			{
				//Exception - Message Alias Use required
				postDispatchException(address, msg, ExceptionType.InvalidAddress, "Not a defined endpoint alias and use is required",key);
				return false;
			}
		}
		else if (configuration.isRequireSecureEndpoint())
		{
			String endpoint = resAddr.getEndPoint();
			if (aliasMap.containsKey(endpoint))
			{
				endpoint = aliasMap.get(endpoint);
				resAddr.setEndPoint(endpoint);
			}
			String check=endpoint.substring(0, 5);
			if (check.compareToIgnoreCase("HTTPS") != 0  )
			{
				//Address is not HTTPS
				postDispatchException(address, msg, ExceptionType.InvalidAddress, "https endpoint is requrired",endpoint);
				return false;
			}
		}
		else
		{
			String key = resAddr.getEndPoint();
			if (aliasMap.containsKey(key))
			{
				resAddr.setEndPoint(aliasMap.get(key));
			}
		}
		
		//Validate the endpoint is a Valid URL for good measure
		

	    String[] schemes = {"http","https"};
	    UrlValidator urlValidator = new UrlValidator(schemes);
	    if (urlValidator.isValid(resAddr.getEndPoint())==false)
	    {
	    	postDispatchException(address, msg, ExceptionType.InvalidAddress, "Endpoint is Malformed", resAddr.getEndPoint());
	    }
		return true;
	}
	
}
