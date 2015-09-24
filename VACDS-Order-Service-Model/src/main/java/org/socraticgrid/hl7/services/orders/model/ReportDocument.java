package org.socraticgrid.hl7.services.orders.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import org.socraticgrid.hl7.services.orders.model.primatives.Code;
import org.socraticgrid.hl7.services.orders.model.primatives.Identifier;

/**
 * For some results, encapsulation in a document (which can be authenticated) is
 * the requirement.  This class is for the lab result as formatted to fit in a
 * electronic document.
 * @author Jerry Goodnough
 * @version 1.0
 * @created 16-Jan-2014 9:12:41 AM
 */
public class ReportDocument extends OrderResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Unique identifier for an instantiation of a lab report, lab results which are
	 * formatted in the form of a report or electronic document.
	 */
	protected Identifier reportIdentity;
	/**
	 * Unique identifier for this instantiation of the documentation of diagnostic lab
	 * results.
	 */
	private Identifier resultIdentity;
	/**
	 * Unique identifier for this instantiation of a fulfillment status
	 */
	private Identifier fulfillmentIdentity;
	/**
	 * Unique identifier for this instantiation of a request for performance of
	 * order(s)
	 */
	private Identifier orderIdentity;
	/**
	 * Values for the status attribute are taken from the concept domain DocumentStatus
	 */
	private Code status;
	/**
	 * Reference to the subject of the result along with relevant details.
	 */
	private Subject subjectDetails;
	/**
	 * The codes for the testing being requested
	 */
	private List<Test> orderedTests=new LinkedList<Test>();
	/**
	 * For diagnostics like Laboratory, this class represents the 'answer' to the
	 * quantative or qualitative request.
	 */
	private List<Result> results = new LinkedList<Result>();
	/**
	 * Information relative to the verification or authentication of a report by a
	 * clinician.
	 */
	private String authenticatioIinfo;
	/**
	 * Represents the content of the report.
	 */
	private String reportContent;
	/**
	 * @return the reportIdentity
	 */
	public Identifier getReportIdentity() {
		return reportIdentity;
	}
	/**
	 * @param reportIdentity the reportIdentity to set
	 */
	public void setReportIdentity(Identifier reportIdentity) {
		this.reportIdentity = reportIdentity;
	}
	/**
	 * @return the resultIdentity
	 */
	public Identifier getResultIdentity() {
		return resultIdentity;
	}
	/**
	 * @param resultIdentity the resultIdentity to set
	 */
	public void setResultIdentity(Identifier resultIdentity) {
		this.resultIdentity = resultIdentity;
	}
	/**
	 * @return the fulfillmentIdentity
	 */
	public Identifier getFulfillmentIdentity() {
		return fulfillmentIdentity;
	}
	/**
	 * @param fulfillmentIdentity the fulfillmentIdentity to set
	 */
	public void setFulfillmentIdentity(Identifier fulfillmentIdentity) {
		this.fulfillmentIdentity = fulfillmentIdentity;
	}
	/**
	 * @return the orderIdentity
	 */
	public Identifier getOrderIdentity() {
		return orderIdentity;
	}
	/**
	 * @param orderIdentity the orderIdentity to set
	 */
	public void setOrderIdentity(Identifier orderIdentity) {
		this.orderIdentity = orderIdentity;
	}
	/**
	 * @return the status
	 */
	public Code getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(Code status) {
		this.status = status;
	}
	/**
	 * @return the subjectDetails
	 */
	public Subject getSubjectDetails() {
		return subjectDetails;
	}
	/**
	 * @param subjectDetails the subjectDetails to set
	 */
	public void setSubjectDetails(Subject subjectDetails) {
		this.subjectDetails = subjectDetails;
	}
	/**
	 * @return the orderedTests
	 */
	public List<Test> getOrderedTests() {
		return orderedTests;
	}
	/**
	 * @param orderedTests the orderedTests to set
	 */
	public void setOrderedTests(List<Test> orderedTests) {
		this.orderedTests = orderedTests;
	}
	/**
	 * @return the results
	 */
	public List<Result> getResults() {
		return results;
	}
	/**
	 * @param results the results to set
	 */
	public void setResults(List<Result> results) {
		this.results = results;
	}
	/**
	 * @return the authenticatioIinfo
	 */
	public String getAuthenticatioIinfo() {
		return authenticatioIinfo;
	}
	/**
	 * @param authenticatioIinfo the authenticatioIinfo to set
	 */
	public void setAuthenticatioIinfo(String authenticatioIinfo) {
		this.authenticatioIinfo = authenticatioIinfo;
	}
	/**
	 * @return the reportContent
	 */
	public String getReportContent() {
		return reportContent;
	}
	/**
	 * @param reportContent the reportContent to set
	 */
	public void setReportContent(String reportContent) {
		this.reportContent = reportContent;
	}



}