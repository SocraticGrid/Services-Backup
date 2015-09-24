/**
 * 
 */
package org.socraticgrid.hl7.services.uc.db.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author steven
 * @created Jan 16, 2014

 */
public class UCSDto<T> {
	List<T> listT = new ArrayList<T>(); // can be used to store objects both ways up/down
	
	Map<DTOMessageType,String> msgs = new HashMap<DTOMessageType,String>();

	Map<String,String> adHocParams = new HashMap<String,String>();
	
	public List<T> getListT() {
		return listT;
	}
	public void setListT(List<T> listT) {
		this.listT = listT;
	}
	public Map<DTOMessageType, String> getMsgs() {
		return msgs;
	}
	public void setMsgs(Map<DTOMessageType, String> msgs) {
		this.msgs = msgs;
	}
	public Map<String, String> getAdHocParams() {
		return adHocParams;
	}
	public void setAdHocParams(Map<String, String> adHocParams) {
		this.adHocParams = adHocParams;
	}

}
