package org.socraticgrid.hl7.services.uc.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.TreeMap;


/**
 * @author Jerry Goodnough
 * @version 1.0
 * @created 16-Dec-2013 3:54:58 PM
 */
public class UserMessageDirectory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private UserContactInfo owner;
	private LinkedList<Message> messages;
	private TreeMap<String,UserMessageDirectory> subdirectories;

	public UserMessageDirectory(){

	}


	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public UserContactInfo getOwner()
	{
		return owner;
	}

	public void setOwner(UserContactInfo owner)
	{
		this.owner = owner;
	}

	public LinkedList<Message> getMessages()
	{
		return messages;
	}

	public void setMessages(LinkedList<Message> messages)
	{
		this.messages = messages;
	}

	public TreeMap<String, UserMessageDirectory> getSubdirectories()
	{
		return subdirectories;
	}

	public void setSubdirectories(
			TreeMap<String, UserMessageDirectory> subdirectories)
	{
		this.subdirectories = subdirectories;
	}

}