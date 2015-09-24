package org.socraticgrid.hl7.services.orders.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;


/**
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class SubjectModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	@XmlElements(value = { 
            @XmlElement(name="patient", 
                        type=Patient.class) })
	private Subject subjectType;
	
	public Subject getSubject() {
		return subjectType;
	}
	public void setSubject(Subject subjectType) {
		this.subjectType = subjectType;
	}
		
	public SubjectModel(Subject subjectType)
	{
		this.setSubject(subjectType);
	}
	
	public SubjectModel()
	{
		
	}
}
