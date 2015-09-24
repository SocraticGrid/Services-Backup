package org.socraticgrid.hl7.services.orders.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;

import org.socraticgrid.hl7.services.orders.model.workflows.StaticWorkflow;



public class WorkflowModel<T extends Workflow>  implements Serializable {
private static final long serialVersionUID = 1L;
	
	
	@XmlElements(value = { 
            @XmlElement(name="static", 
                        type=StaticWorkflow.class)
        
    })
	private T workflow;
	
	public T getType() {
		return workflow;
	}
	public void setType(T workflow) {
		this.workflow = workflow;
	}
	
	
	public WorkflowModel()
	{
		
	}
	
	public WorkflowModel(T workflow)
	{
		this.workflow = workflow;
	}

}
