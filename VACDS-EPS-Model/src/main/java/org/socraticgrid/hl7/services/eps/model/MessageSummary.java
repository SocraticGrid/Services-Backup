package org.socraticgrid.hl7.services.eps.model;

import java.io.Serializable;
import java.util.Date;

public class MessageSummary  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	
	private String topicId;
	private String messageId;
	private User publisher;
	private Authorship author;
	private int priority;
	private Date messagePublicationTime;
	private Date messageCreatedTime;
	private Date messageExpirationTime;
	private String subject;
	
	
	public MessageSummary() {
		super();
		// TODO Auto-generated constructor stub
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
	 * @return the messageId
	 */
	public String getMessageId() {
		return messageId;
	}


	/**
	 * @param messageId the messageId to set
	 */
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}


	/**
	 * @return the publisher
	 */
	public User getPublisher() {
		return publisher;
	}


	/**
	 * @param publisher the publisher to set
	 */
	public void setPublisher(User publisher) {
		this.publisher = publisher;
	}


	/**
	 * @return the author
	 */
	public Authorship getAuthor() {
		return author;
	}


	/**
	 * @param author the author to set
	 */
	public void setAuthor(Authorship author) {
		this.author = author;
	}


	/**
	 * @return the priority
	 */
	public int getPriority() {
		return priority;
	}


	/**
	 * @param priority the priority to set
	 */
	public void setPriority(int priority) {
		this.priority = priority;
	}


	/**
	 * @return the messagePublicationTime
	 */
	public Date getMessagePublicationTime() {
		return messagePublicationTime;
	}


	/**
	 * @param messagePublicationTime the messagePublicationTime to set
	 */
	public void setMessagePublicationTime(Date messagePublicationTime) {
		this.messagePublicationTime = messagePublicationTime;
	}


	/**
	 * @return the messageCreatedTime
	 */
	public Date getMessageCreatedTime() {
		return messageCreatedTime;
	}


	/**
	 * @param messageCreatedTime the messageCreatedTime to set
	 */
	public void setMessageCreatedTime(Date messageCreatedTime) {
		this.messageCreatedTime = messageCreatedTime;
	}


	/**
	 * @return the messageExpirationTime
	 */
	public Date getMessageExpirationTime() {
		return messageExpirationTime;
	}


	/**
	 * @param messageExpirationTime the messageExpirationTime to set
	 */
	public void setMessageExpirationTime(Date messageExpirationTime) {
		this.messageExpirationTime = messageExpirationTime;
	}


	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}


	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	
}
