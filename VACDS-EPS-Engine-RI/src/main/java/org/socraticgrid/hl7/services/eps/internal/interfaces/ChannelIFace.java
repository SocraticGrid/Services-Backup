package org.socraticgrid.hl7.services.eps.internal.interfaces;

import org.socraticgrid.hl7.services.eps.model.Message;

public interface ChannelIFace {

	public void publishEvent(Message event);
	public void deleteEvent(Message event);
}
