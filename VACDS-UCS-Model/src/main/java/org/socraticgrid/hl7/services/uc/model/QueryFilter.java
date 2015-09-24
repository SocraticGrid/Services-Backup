package org.socraticgrid.hl7.services.uc.model;

import java.io.Serializable;

public class QueryFilter implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String element;
	private String expression;
	/**
	 * @param element
	 * @param expression
	 */
	public QueryFilter(String element, String expression)
	{
		super();
		this.element = element;
		this.expression = expression;
	}
	public QueryFilter()
	{
		
	}
	public String getElement()
	{
		return element;
	}
	public void setElement(String element)
	{
		this.element = element;
	}
	public String getExpression()
	{
		return expression;
	}
	public void setExpression(String expression)
	{
		this.expression = expression;
	}
}
