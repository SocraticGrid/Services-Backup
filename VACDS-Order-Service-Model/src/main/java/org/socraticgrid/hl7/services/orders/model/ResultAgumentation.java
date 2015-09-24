package org.socraticgrid.hl7.services.orders.model;

import java.io.Serializable;

import org.socraticgrid.hl7.services.orders.model.primatives.Code;

public class ResultAgumentation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String agumeentationWSource;
	private Code augmentationType;
	private String summaryText;
	private String detailtext;
	/**
	 * @return the agumeentationWSource
	 */
	public String getAgumeentationWSource() {
		return agumeentationWSource;
	}
	/**
	 * @param agumeentationWSource the agumeentationWSource to set
	 */
	public void setAgumeentationWSource(String agumeentationWSource) {
		this.agumeentationWSource = agumeentationWSource;
	}
	/**
	 * @return the augmentationType
	 */
	public Code getAugmentationType() {
		return augmentationType;
	}
	/**
	 * @param augmentationType the augmentationType to set
	 */
	public void setAugmentationType(Code augmentationType) {
		this.augmentationType = augmentationType;
	}
	/**
	 * @return the summaryText
	 */
	public String getSummaryText() {
		return summaryText;
	}
	/**
	 * @param summaryText the summaryText to set
	 */
	public void setSummaryText(String summaryText) {
		this.summaryText = summaryText;
	}
	/**
	 * @return the detailtext
	 */
	public String getDetailtext() {
		return detailtext;
	}
	/**
	 * @param detailtext the detailtext to set
	 */
	public void setDetailtext(String detailtext) {
		this.detailtext = detailtext;
	}
	
	
}
