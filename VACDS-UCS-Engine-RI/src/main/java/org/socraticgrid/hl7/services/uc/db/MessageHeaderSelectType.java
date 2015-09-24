package org.socraticgrid.hl7.services.uc.db;

public enum MessageHeaderSelectType {
	BYID("selectMessageHeaderById")
	,BYMSGID("selectMessageHeaderByMsgId")
	;
	
	private String queryId;
	public String getQueryId(){
		return queryId;
	}
	
	private MessageHeaderSelectType(String queryId){
		this.queryId = queryId;
	}
}
