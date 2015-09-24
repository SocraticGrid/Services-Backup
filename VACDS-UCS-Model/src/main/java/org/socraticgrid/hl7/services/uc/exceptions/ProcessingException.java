package org.socraticgrid.hl7.services.uc.exceptions;

import java.io.Serializable;

import javax.jws.WebMethod;

public class ProcessingException implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String processingExceptionId;
	private String fault;
	private String generatingMessageId;
	private String issuingService;
	private ExceptionType exceptionType = ExceptionType.General;
	private String typeSpecificContext;
	
	public ProcessingException()
	{
		
	}
	
	public ProcessingException(ExceptionType exceptionType, String issuingService, String messageId, String fault)
	{
		this.exceptionType=exceptionType;
		this.issuingService=issuingService;
		this.generatingMessageId=messageId;
		this.fault=fault;
	}
	public ProcessingException(ExceptionType exceptionType, String issuingService, String messageId, String fault, String typeSpecificContext)
	{
		this.exceptionType=exceptionType;
		this.issuingService=issuingService;
		this.generatingMessageId=messageId;
		this.fault=fault;
		this.typeSpecificContext=typeSpecificContext;
	}
	
	public String getFault()
	{
		return fault;
	}

	public String getGeneratingMessageId()
	{
		return generatingMessageId;
	}

	public String getIssuingService()
	{
		return issuingService;
	}

	public void setFault(String fault)
	{
		this.fault = fault;
	}

	public void setGeneratingMessage(String generatingMessageId)
	{
		this.generatingMessageId = generatingMessageId;
	}

	public void setIssuingService(String issuingService)
	{
		this.issuingService = issuingService;
	}

	/**
	 * @return the exceptionType
	 */
	public ExceptionType getExceptionType() {
		return exceptionType;
	}

	/**
	 * @param exceptionType the exceptionType to set
	 */
	public void setExceptionType(ExceptionType exceptionType) {
		this.exceptionType = exceptionType;
	}

	/**
	 * @return the typeSpecificContext
	 */
	public String getTypeSpecificContext() {
		return typeSpecificContext;
	}

	/**
	 * @param typeSpecificContext the typeSpecificContext to set
	 */
	public void setTypeSpecificContext(String typeSpecificContext) {
		this.typeSpecificContext = typeSpecificContext;
	}

	/**
	 * @param generatingMessageId the generatingMessageId to set
	 */
	public void setGeneratingMessageId(String generatingMessageId) {
		this.generatingMessageId = generatingMessageId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ProcessingException [exceptionType=" + exceptionType
				+ ", issuingService=" + issuingService
				+ ", generatingMessageId=" + generatingMessageId + ", fault="
				+ fault + ", typeSpecificContext=" + typeSpecificContext + "]";
	}

	@WebMethod(exclude=true)
	public String getProcessingExceptionId() {
		return processingExceptionId;
	}
	@WebMethod(exclude=true)
	public void setProcessingExceptionId(String processingExceptionId) {
		this.processingExceptionId = processingExceptionId;
	}
	@Override
	public boolean equals(Object o){
		if(o==null){
			return false;
		}
		if(o instanceof ProcessingException){
			return getProcessingExceptionId().equals(((ProcessingException)o).getProcessingExceptionId());
		}
		return false;
	}
	@Override
	public int hashCode() {
		return getProcessingExceptionId().hashCode();
	}

	
}
