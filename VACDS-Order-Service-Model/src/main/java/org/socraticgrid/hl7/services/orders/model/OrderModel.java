package org.socraticgrid.hl7.services.orders.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;

import org.socraticgrid.hl7.services.orders.model.types.order.LabOrder;
import org.socraticgrid.hl7.services.orders.model.types.order.MedicationOrder;
import org.socraticgrid.hl7.services.orders.model.types.order.NursingOrder;
import org.socraticgrid.hl7.services.orders.model.types.order.NutritionOrder;


@XmlAccessorType(XmlAccessType.FIELD)

public class OrderModel<T extends Order>  implements Serializable {
private static final long serialVersionUID = 1L;
	
	
	@XmlElements(value = { 
            @XmlElement(name="lab", 
                        type=LabOrder.class),
            @XmlElement(name="medication", 
                        type=MedicationOrder.class),
            @XmlElement(name="nursing", 
                        type=NursingOrder.class),
            @XmlElement(name="nutrition",
            	type=NutritionOrder.class)
    })
	private T orderType;
	
	public T getType() {
		return orderType;
	}
	public void setType(T orderType) {
		this.orderType = orderType;
	}
	
	
	public OrderModel()
	{
		
	}
	
	public OrderModel(T orderType)
	{
		this.orderType = orderType;
	}
}
