package org.socraticgrid.hl7.services.eps.internal.model;

import java.util.List;

import org.socraticgrid.hl7.services.eps.internal.interfaces.ChannelIFace;
import org.socraticgrid.hl7.services.eps.model.Channel;
import org.socraticgrid.hl7.services.eps.model.Message;
import org.socraticgrid.hl7.services.eps.model.Subscription;

public abstract class CommonChannel implements ChannelIFace {

	private Channel channelInfo;
	private List<Subscription> subscriptions;
	
	
	@Override
	public void publishEvent(Message event) {
		
		channelInfo.getMessageQueueList().add(event);
		processMessageQueue();
	}

	@Override
	public void deleteEvent(Message event) {
		boolean resume = this.pauseMessageQueue();
		channelInfo.getMessageQueueList().remove(event);
		if (resume)
		{
			this.resumeMessageQueue();
		}
	}

	abstract protected void processMessageQueue();
	abstract protected boolean pauseMessageQueue();
	abstract protected boolean resumeMessageQueue();
	
	/**
	 * @return the channelInfo
	 */
	public Channel getChannelInfo() {
		return channelInfo;
	}

	/**
	 * @param channelInfo the channelInfo to set
	 */
	public void setChannelInfo(Channel channelInfo) {
		this.channelInfo = channelInfo;
	}

	/**
	 * @return the subsciptions
	 */
	public List<Subscription> getSubscriptions() {
		return subscriptions;
	}

	/**
	 * @param subsciptions the subsciptions to set
	 */
	public void setSubscriptions(List<Subscription> subsciptions) {
		this.subscriptions = subsciptions;
	}

	public void addSubscription(Subscription subscripton)
	{
		subscriptions.add(subscripton);
	}
	
	public void removeSubscription(Subscription subscription)
	{
		subscriptions.remove(subscription);
	}
	

}
