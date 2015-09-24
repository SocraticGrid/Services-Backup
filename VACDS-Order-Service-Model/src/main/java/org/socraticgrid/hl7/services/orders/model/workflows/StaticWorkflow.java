package org.socraticgrid.hl7.services.orders.model.workflows;

import java.io.Serializable;

import org.socraticgrid.hl7.services.orders.model.Workflow;

public class StaticWorkflow extends Workflow implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String workFlowId;

	/**
	 * @return the workFlowId
	 */
	public String getWorkFlowId() {
		return workFlowId;
	}

	/**
	 * @param workFlowId the workFlowId to set
	 */
	public void setWorkFlowId(String workFlowId) {
		this.workFlowId = workFlowId;
	}
	
	
}
