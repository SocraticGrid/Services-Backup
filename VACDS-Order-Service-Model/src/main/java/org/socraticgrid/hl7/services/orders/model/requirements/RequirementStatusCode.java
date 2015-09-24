package org.socraticgrid.hl7.services.orders.model.requirements;

public enum RequirementStatusCode {

	Pending(1), Complete(2), Removed(3);
	private int intVal;
	
	
	private RequirementStatusCode(int val)
	{
		this.intVal=val;
		
	}
	
	public int getIntVal()
	{
		return intVal;
	}
}
