/**
 * 
 */
package org.socraticgrid.hl7.services.uc.db.dto.typehandler;

import org.apache.ibatis.type.EnumTypeHandler;
import org.apache.ibatis.type.MappedTypes;
import org.socraticgrid.hl7.services.uc.model.MessageDynamics;

/**
 * @author steven
 * @created Jan 23, 2014

 */
@MappedTypes(MessageDynamics.class)
public class MessageDynamicsHandler extends EnumTypeHandler<MessageDynamics> {

	public MessageDynamicsHandler(Class<MessageDynamics> type) {
		super(type);
		// TODO Auto-generated constructor stub
	}

}
