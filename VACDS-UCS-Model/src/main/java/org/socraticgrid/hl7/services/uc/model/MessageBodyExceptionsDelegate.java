package org.socraticgrid.hl7.services.uc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.socraticgrid.hl7.services.uc.exceptions.ProcessingException;

/**
 * 
 * @author steven
 *
 */
public class MessageBodyExceptionsDelegate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Set<MessageBody> parts = new HashSet<>();
	private Set<ProcessingException> exceptions = new HashSet<>();
	
	
	public Set<MessageBody> getParts() {
		return parts;
	}
	public void setParts(Set<MessageBody> parts) {
		this.parts = parts;
	}
	public MessageBody[] getPartsArray(){
		return parts.toArray(new MessageBody[parts.size()]);
	}
	public Set<ProcessingException> getExceptions() {
		return exceptions;
	}
	public void setExceptions(Set<ProcessingException> exceptions) {
		this.exceptions = exceptions;
	}
	public List<ProcessingException> getExceptionsList() {
		List<ProcessingException> list = new ArrayList<>();
		list.addAll(exceptions);
		return list;
	}

}