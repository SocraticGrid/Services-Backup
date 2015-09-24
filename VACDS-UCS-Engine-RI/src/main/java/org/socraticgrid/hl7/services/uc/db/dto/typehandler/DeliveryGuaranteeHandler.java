/**
 * 
 */
package org.socraticgrid.hl7.services.uc.db.dto.typehandler;

import org.apache.ibatis.type.EnumTypeHandler;
import org.apache.ibatis.type.MappedTypes;
import org.socraticgrid.hl7.services.uc.model.DeliveryGuarantee;

/**
 * @author steven
 * @created Jan 23, 2014

 */
@MappedTypes(DeliveryGuarantee.class)
public class DeliveryGuaranteeHandler extends EnumTypeHandler<DeliveryGuarantee> {

	public DeliveryGuaranteeHandler(Class<DeliveryGuarantee> type) {
		super(type);
		// TODO Auto-generated constructor stub
	}

}
