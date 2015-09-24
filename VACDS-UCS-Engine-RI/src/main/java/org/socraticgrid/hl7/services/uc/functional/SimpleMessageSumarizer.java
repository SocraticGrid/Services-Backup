package org.socraticgrid.hl7.services.uc.functional;


import org.socraticgrid.hl7.services.uc.model.Message;

public class SimpleMessageSumarizer
{
	public String getMesageSummary(Message msg){
		StringBuffer buf = new StringBuffer();
		buf.append(msg.getHeader().getMessageType().name());
		buf.append(" message");
		return buf.toString();
	}
}
