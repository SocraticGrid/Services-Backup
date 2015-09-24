/**
 * 
 */
package org.socraticgrid.hl7.services.uc.db.dto.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

/**
 * @author steven
 * @created Jan 23, 2014

 */
@MappedTypes(Boolean.class)
public class DerbyBooleanTypeHandler implements TypeHandler<Boolean> {

	@Override
	public void setParameter(PreparedStatement ps, int i, Boolean parameter,
			JdbcType jdbcType) throws SQLException {
		ps.setInt(i, parameter?1:0 );		
	}

	@Override
	public Boolean getResult(ResultSet rs, String columnName)
			throws SQLException {
		
		return rs.getInt(columnName)==1?true:false;
	}

	@Override
	public Boolean getResult(ResultSet rs, int columnIndex) throws SQLException {
		// TODO Auto-generated method stub

		return rs.getInt(columnIndex)==1?true:false;
	}

	@Override
	public Boolean getResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		// TODO Auto-generated method stub

		return cs.getInt(columnIndex)==1?true:false;
	}


}
