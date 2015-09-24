package org.socraticgrid.hl7.services.uc.functional;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.socraticgrid.hl7.services.uc.exceptions.InvalidAddressException;
import org.socraticgrid.hl7.services.uc.internal.interfaces.ServiceRegistryIntf;
import org.socraticgrid.hl7.services.uc.logging.EventLevel;
import org.socraticgrid.hl7.services.uc.logging.LogEntryType;
import org.socraticgrid.hl7.services.uc.model.AddressType;
import org.socraticgrid.hl7.services.uc.model.DeliveryAddress;
import org.socraticgrid.hl7.services.uc.model.DeliveryGroup;
import org.socraticgrid.hl7.services.uc.model.Message;
import org.socraticgrid.hl7.services.uc.model.PhysicalAddress;
import org.socraticgrid.hl7.services.uc.model.Recipient;
import org.socraticgrid.hl7.services.uc.model.UserContactInfo;
import org.socraticgrid.hl7.services.uc.operational.MessageWrapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 16-Dec-2013 3:54:55 PM
 */
public class AddressResolver
{
	@Autowired
	UserManager userMgr;

	@Autowired
	ServiceRegistryIntf services;

	@Autowired
	GroupManager groupMgr;
	
	@Autowired
	EventLogger eventLogger;


	public <T extends Message> void resolveAddresses(MessageWrapper<T> msg)
			throws InvalidAddressException
	{
		// Ok Here we go

		List<DeliveryAddress> error = validateMessageRecipients(msg);
		if (!error.isEmpty())
		{
			throw new InvalidAddressException(error);
		}
		HashMap<String, List<PhysicalAddress>> addressMap = new HashMap<String, List<PhysicalAddress>>();

		Set<Recipient> sendTo = msg.getMessage().getHeader()
				.getRecipientsList();
		Iterator<Recipient> itr = sendTo.iterator();
		
		
		// Exclusion is limited to a recipient
		boolean excludeOnRecipient = true;
		//TODO Detail what changes this behavior;
		Set<DeliveryAddress> exclude = new HashSet<DeliveryAddress>();
		
		//TODO - Add Traceback  to Recipient

		while (itr.hasNext())
		{		

			Recipient r = itr.next();
			DeliveryAddress adr = r.getDeliveryAddress();
			resolveAddress(adr, addressMap, exclude, msg, r);
			if (excludeOnRecipient  && itr.hasNext())
			{
				//Reset the exclusion list for the next recipient
				exclude = new HashSet<DeliveryAddress>();
			}
		}
		// We have passed validation and deep resolution
		msg.setAddressMap(addressMap);

	}

	/**
	 * @param adr
	 * @param addressMap
	 * @param exclude - Set of Delivery addresses already seen to avoid loops
	 * @param msg - The Message wrapper for context dependent addressing 
	 */
	public <T extends Message> void resolveAddress(DeliveryAddress adr,
			HashMap<String, List<PhysicalAddress>> addressMap,
			Set<DeliveryAddress> exclude, MessageWrapper<T> msg, Recipient r)
	{
		if (!exclude.contains(adr))
		{
			exclude.add(adr);
			
			AddressType type = adr.getAddressType();
			
			if (type== null)
			{
					eventLogger.logSummaryEvent(LogEntryType.User_SendMessage, EventLevel.warn, "resolveAddress", "RecipientAddress Type Unknown", "Recipient passed with unknown/type");
					return;
			}
			
			
			switch (adr.getAddressType())
			{
			case Physical:
			{
				PhysicalAddress a = adr.getPhysicalAddress();
				List<PhysicalAddress> list = addressMap.get(a.getServiceId());
				if (list == null)
				{
					list = new LinkedList<PhysicalAddress>();
					addressMap.put(a.getServiceId(), list);
				}
				list.add(a);
				

				break;
			}
			case Group:
			{
				DeliveryGroup grp = groupMgr.findGroup(adr.getGroupAddress().getName());
				if (grp != null)
				{
					// Loop the group
					LinkedList<DeliveryAddress> grpList = grp.getGroup();
					Iterator<DeliveryAddress> itr = grpList.iterator();
					while(itr.hasNext())
					{
						resolveAddress(itr.next(),addressMap,exclude,msg,r);
					}
				}
				break;
			}
			case Party:
			{
			
				UserContactInfo userInfo = userMgr.findUser(adr.getPartyAddress().getName());
				if (userInfo != null)
				{
					
					// TODO Extend for context and better address selection - ay need more context for the operation.
					// TODO Extend for presence
					
					// Resolve the user preferred address
					PhysicalAddress a = userInfo.getPreferredAddress();
					
					List<PhysicalAddress> list = addressMap.get(a.getServiceId());
					if (list == null)
					{
						list = new LinkedList<PhysicalAddress>();
						addressMap.put(a.getServiceId(), list);
					}
					list.add(a);
									
				}
				break;
			}

			}
	
		}

	}

	public <T extends Message> List<DeliveryAddress> validateMessageRecipients(MessageWrapper<T> msg)
			throws InvalidAddressException
	{
		LinkedList<DeliveryAddress> outList = new LinkedList<DeliveryAddress>();

		// Ok Here we go
		Set<String> validServices = services.getServiceNames();

		Set<Recipient> sendTo = msg.getMessage().getHeader()
				.getRecipientsList();
		Iterator<Recipient> itr = sendTo.iterator();
		while (itr.hasNext())
		{
			Recipient r = itr.next();
			DeliveryAddress adr = r.getDeliveryAddress();
			
			AddressType type = adr.getAddressType();
			
			if (type== null)
			{
	
					InvalidAddressException exp = new InvalidAddressException("Recipient passed with unknown/type");
					eventLogger.logUserExceptionEvent("validateMessageRecipients", exp);
					throw exp;
			}
			switch (type)
			{
			case Physical:
			{
				PhysicalAddress a = adr.getPhysicalAddress();
				if (!validServices.contains(a.getServiceId()))
				{
					outList.add(adr);
				}
				break;
			}
			case Group:
			{
				// TODO: Should we do a deep validation? - For not no - We
				// assume Group integrity

				if (groupMgr.findGroup(adr.getGroupAddress().getName()) == null)
				{
					outList.add(adr);
				}
				break;
			}
			case Party:
			{
				// TODO: Should we do a deep Validation? - For not no - We
				// assume user integrity;
				if (userMgr.findUser(adr.getPartyAddress().getName()) == null)
				{
					outList.add(adr);
				}
				break;
			}
			default:
			{
				outList.add(adr);
			}

			}

		}

		// Loop Address - Switch by type
		return outList;
	}

	public List<DeliveryAddress> validateAddress(DeliveryAddress adr)
			throws InvalidAddressException
	{
		LinkedList<DeliveryAddress> outList = new LinkedList<DeliveryAddress>();

		switch (adr.getAddressType())
		{
		case Physical:
		{
			PhysicalAddress a = adr.getPhysicalAddress();
			if (!services.getServiceNames().contains(a.getServiceId()))
			{
				outList.add(adr);
			}
			break;
		}
		case Group:
		{
			// TODO: Should we do a deep validation? - For now no - We assume
			// Group integrity

			if (groupMgr.findGroup(adr.getGroupAddress().getName()) == null)
			{
				outList.add(adr);
			}
			break;
		}
		case Party:
		{
			// TODO: Should we do a deep Validation? - For now no - We assume
			// user integrity;
			if (userMgr.findUser(adr.getPartyAddress().getName()) == null)
			{
				outList.add(adr);
			}
			break;
		}
		default:
		{
			outList.add(adr);
		}

		}

		// Loop Address - Switch by type
		return outList;
	}
	
	/**
	 * @param adr
	 * @param addressMap
	 * @param exclude - Set of Delivery addresses already seen to avoid loops
	 * @param msg - The Message wrapper for context dependent addressing 
	 */
	public void simpleResolveAddress(DeliveryAddress adr,
			List<PhysicalAddress> addressList,
			Set<DeliveryAddress> exclude)
	{
		if (exclude == null)
		{
			exclude = new HashSet<DeliveryAddress>();
		}
		
		if (!exclude.contains(adr))
		{
			exclude.add(adr);
			
			AddressType type = adr.getAddressType();
				
			
			switch (type)
			{
			case Physical:
			{
				PhysicalAddress a = adr.getPhysicalAddress();
				addressList.add(a);
				break;
			}
			case Group:
			{
				DeliveryGroup grp = groupMgr.findGroup(adr.getGroupAddress().getName());
				if (grp != null)
				{
					// Loop the group
					LinkedList<DeliveryAddress> grpList = grp.getGroup();
					Iterator<DeliveryAddress> itr = grpList.iterator();
					while(itr.hasNext())
					{
						simpleResolveAddress(itr.next(),addressList,exclude);
					}
				}
				break;
			}
			case Party:
			{
			
				UserContactInfo userInfo = userMgr.findUser(adr.getPartyAddress().getName());
				if (userInfo != null)
				{
					
					// TODO Extend for context and better address selection - ay need more context for the operation.
					// TODO Extend for presence
					
					// Resolve the user preferred address
					PhysicalAddress a = userInfo.getPreferredAddress();
					addressList.add(a);
									
				}
				break;
			}

			}
	
		}

	}

}