package org.socraticgrid.hl7.services.eps.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 04-Jan-2014 6:15:21 PM
 */
public class TopicMetadata  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	private List<Channel> channels;
	private List<Options> options;
	private User owner;
	private List<User> publishers;
	private String topicId;

	public TopicMetadata(){

	}

	public List<Channel> getChannels(){
		return channels;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setChannels(List<Channel> newVal){
		channels = newVal;
	}

	public List<Options> getOptions(){
		return options;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setOptions(List<Options> newVal){
		options = newVal;
	}

	public User getOwner(){
		return owner;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setOwner(User newVal){
		owner = newVal;
	}

	public List<User> getPublishers(){
		return publishers;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setPublishers(List<User> newVal){
		publishers = newVal;
	}

	public String getTopicId(){
		return topicId;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setTopicId(String newVal){
		topicId = newVal;
	}

}