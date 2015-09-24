/**
 * 
 */
package org.socraticgrid.hl7.services.uc.db.dto.typehandler;

import org.apache.ibatis.type.EnumTypeHandler;
import org.apache.ibatis.type.MappedTypes;
import org.socraticgrid.hl7.services.uc.model.MessageType;

/**
 * @author steven
 * @created Jan 23, 2014

 */
@MappedTypes(MessageType.class)
public class MessageTypeHandler extends EnumTypeHandler<MessageType> {

	public MessageTypeHandler(Class<MessageType> type) {
		super(type);
		// TODO Auto-generated constructor stub
	}

}
