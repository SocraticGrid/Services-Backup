/**
 * 
 */
package org.socraticgrid.hl7.services.uc.db.dto.typehandler;

import org.apache.ibatis.type.EnumTypeHandler;
import org.apache.ibatis.type.MappedTypes;
import org.socraticgrid.hl7.services.uc.model.AddressType;

/**
 * @author steven
 * @created Jan 23, 2014

 */
@MappedTypes(AddressType.class)
public class AddressTypeHandler extends EnumTypeHandler<AddressType> {

	public AddressTypeHandler(Class<AddressType> type) {
		super(type);
		// TODO Auto-generated constructor stub
	}

}
