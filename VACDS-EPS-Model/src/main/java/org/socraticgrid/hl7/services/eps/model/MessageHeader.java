package org.socraticgrid.hl7.services.eps.model;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 04-Jan-2014 6:15:20 PM
 */
public class MessageHeader implements Serializable {

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
	
	/**
	 * Empty id not a replay event.
	 */
	private String replayId;

	public MessageHeader(){

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

	public String getMessageId(){
		return messageId;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setMessageId(String newVal){
		messageId = newVal;
	}

	public User getPublisher(){
		return publisher;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setPublisher(User newVal){
		publisher = newVal;
	}

	public Authorship getAuthor(){
		return author;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setAuthor(Authorship newVal){
		author = newVal;
	}

	public int getPriority(){
		return priority;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setPriority(int newVal){
		priority = newVal;
	}

	public Date getMessagePublicationTime(){
		return messagePublicationTime;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setMessagePublicationTime(Date newVal){
		messagePublicationTime = newVal;
	}

	public Date getMessageCreatedTime(){
		return messageCreatedTime;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setMessageCreatedTime(Date newVal){
		messageCreatedTime = newVal;
	}

	public Date getMessageExpirationTime(){
		return messageExpirationTime;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setMessageExpirationTime(Date newVal){
		messageExpirationTime = newVal;
	}

	/**
	 * Empty id not a replay event.
	 */
	public String getReplayId(){
		return replayId;
	}

	/**
	 * Empty id not a replay event.
	 * 
	 * @param newVal
	 */
	public void setReplayId(String newVal){
		replayId = newVal;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MessageHeader [topicId=" + topicId + ", messageId=" + messageId
				+ ", publisher=" + publisher + ", author=" + author
				+ ", priority=" + priority + ", messagePublicationTime="
				+ messagePublicationTime + ", messageCreatedTime="
				+ messageCreatedTime + ", messageExpirationTime="
				+ messageExpirationTime + ", subject=" + subject
				+ ", replayId=" + replayId + "]";
	}

}