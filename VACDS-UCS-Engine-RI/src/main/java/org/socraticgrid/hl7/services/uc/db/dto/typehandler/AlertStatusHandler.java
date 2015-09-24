/**
 * 
 */
package org.socraticgrid.hl7.services.uc.db.dto.typehandler;

import org.apache.ibatis.type.EnumTypeHandler;
import org.apache.ibatis.type.MappedTypes;
import org.socraticgrid.hl7.services.uc.model.AlertStatus;

/**
 * @author steven
 * @created Jan 23, 2014

 */
@MappedTypes(AlertStatus.class)
public class AlertStatusHandler extends EnumTypeHandler<AlertStatus> {

	public AlertStatusHandler(Class<AlertStatus> type) {
		super(type);
		// TODO Auto-generated constructor stub
	}

}
