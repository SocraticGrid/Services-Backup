package org.socraticgrid.hl7.services.eps.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 04-Jan-2014 6:15:20 PM
 */
public class Topic  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	private String name;
	private String topicId;
	private String description;
	private Options optionsList;
	private Channel channelsList;
	private String publishersList;
	private List<Subscription> subscriptionsList;
	private List<Affiliation> affiliationsList;
	private Map<String,Topic> subtopics;
	private List<String> publicationReviewersList;
	private List<String>deliveryReviewersList;


	public Topic(){

	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return the topicId
	 */
	public String getTopicId() {
		return topicId;
	}


	/**
	 * @param topicId the topicId to set
	 */
	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}


	/**
	 * @return the optionsList
	 */
	public Options getOptionsList() {
		return optionsList;
	}


	/**
	 * @param optionsList the optionsList to set
	 */
	public void setOptionsList(Options optionsList) {
		this.optionsList = optionsList;
	}


	/**
	 * @return the channelsList
	 */
	public Channel getChannelsList() {
		return channelsList;
	}


	/**
	 * @param channelsList the channelsList to set
	 */
	public void setChannelsList(Channel channelsList) {
		this.channelsList = channelsList;
	}


	/**
	 * @return the publishersList
	 */
	public String getPublishersList() {
		return publishersList;
	}


	/**
	 * @param publishersList the publishersList to set
	 */
	public void setPublishersList(String publishersList) {
		this.publishersList = publishersList;
	}


	/**
	 * @return the subscriptionsList
	 */
	public List<Subscription> getSubscriptionsList() {
		return subscriptionsList;
	}


	/**
	 * @param subscriptionsList the subscriptionsList to set
	 */
	public void setSubscriptionsList(List<Subscription> subscriptionsList) {
		this.subscriptionsList = subscriptionsList;
	}


	/**
	 * @return the affiliationsList
	 */
	public List<Affiliation> getAffiliationsList() {
		return affiliationsList;
	}


	/**
	 * @param affiliationsList the affiliationsList to set
	 */
	public void setAffiliationsList(List<Affiliation> affiliationsList) {
		this.affiliationsList = affiliationsList;
	}


	/**
	 * @return the subtopics
	 */
	public Map<String, Topic> getSubtopics() {
		return subtopics;
	}


	/**
	 * @param subtopics the subtopics to set
	 */
	public void setSubtopics(Map<String, Topic> subtopics) {
		this.subtopics = subtopics;
	}


	/**
	 * @return the publicationReviewersList
	 */
	public List<String> getPublicationReviewersList() {
		return publicationReviewersList;
	}


	/**
	 * @param publicationReviewersList the publicationReviewersList to set
	 */
	public void setPublicationReviewersList(List<String> publicationReviewersList) {
		this.publicationReviewersList = publicationReviewersList;
	}


	/**
	 * @return the deliveryReviewersList
	 */
	public List<String> getDeliveryReviewersList() {
		return deliveryReviewersList;
	}


	/**
	 * @param deliveryReviewersList the deliveryReviewersList to set
	 */
	public void setDeliveryReviewersList(List<String> deliveryReviewersList) {
		this.deliveryReviewersList = deliveryReviewersList;
	}


	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}


	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}


}