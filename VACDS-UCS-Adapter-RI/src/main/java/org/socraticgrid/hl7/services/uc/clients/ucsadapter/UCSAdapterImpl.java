package org.socraticgrid.hl7.services.uc.clients.ucsadapter;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.socraticgrid.hl7.services.uc.interfaces.UCSAdapterIntf;
import org.socraticgrid.hl7.services.uc.model.AdapterMessage;
import org.socraticgrid.hl7.services.uc.model.InteractionTypes;



public class UCSAdapterImpl implements UCSAdapterIntf
{
	private final Logger logger = LoggerFactory.getLogger(UCSAdapterImpl.class);
	public UCSAdapterImpl()
	{
		// TODO Auto-generated constructor stub
	}



	@Override
	public String getServiceId()
	{
		logger.debug("cancelMessage Called");
		return "Test";
	}

	@Override
	public List<InteractionTypes> getInteractionTypes()
	{
		logger.debug("cancelMessage Called");
		ArrayList<InteractionTypes> out = new ArrayList<InteractionTypes>();
		return out;
	}

	@Override
	public boolean cancelMessage(AdapterMessage msg, String serverId) {
		logger.debug("cancelMessage Called");
		return false;
	}

	@Override
	public boolean sendMessage(AdapterMessage msg, String serverId) {
		logger.debug("sendMessage Called");
		return false;
	}

	@Override
	public boolean updateMessage(AdapterMessage newMsg, AdapterMessage oldMsg,
			String serverId) {
		logger.debug("updateMessage Called");
		return false;
	}

}
