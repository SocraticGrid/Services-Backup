package org.socraticgrid.hl7.services.eps.model;

import java.io.Serializable;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 04-Jan-2014 6:15:20 PM
 */
public class Subscription  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	private String id;
	private SubscriptionType type;
	/**
	 * When Empty the Users Default Address should be used
	 */
	private NotificationAddress subscriberNotificationAddress;
	private User subscriber;
	private ContractType contractType;
	private Channel channel;
	private Durability durability;
	private String lastMessage;
	private boolean requireId;

	public Subscription(){

	}

	public String getId(){
		return id;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setId(String newVal){
		id = newVal;
	}

	public SubscriptionType getType(){
		return type;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setType(SubscriptionType newVal){
		type = newVal;
	}

	/**
	 * When Empty the Users Default Address should be used
	 */
	public NotificationAddress getSubscriberNotificationAddress(){
		return subscriberNotificationAddress;
	}

	/**
	 * When Empty the Users Default Address should be used
	 * 
	 * @param newVal
	 */
	public void setSubscriberNotificationAddress(NotificationAddress newVal){
		subscriberNotificationAddress = newVal;
	}

	public User getSubscriber(){
		return subscriber;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setSubscriber(User newVal){
		subscriber = newVal;
	}

	public ContractType getContractType(){
		return contractType;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setContractType(ContractType newVal){
		contractType = newVal;
	}

	public Channel getChannel(){
		return channel;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setChannel(Channel newVal){
		channel = newVal;
	}

	public Durability getDurability(){
		return durability;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setDurability(Durability newVal){
		durability = newVal;
	}

	public String getLastMessage(){
		return lastMessage;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setLastMessage(String newVal){
		lastMessage = newVal;
	}

	public boolean isRequireId(){
		return requireId;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setRequireId(boolean newVal){
		requireId = newVal;
	}

}