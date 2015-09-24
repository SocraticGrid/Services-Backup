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
import org.socraticgrid.hl7.services.uc.model.Role;



/**
 * @author steven
 * @created Jan 23, 2014

 */
@MappedTypes(Role.class)
public class RoleHandler implements TypeHandler<Role> {

	@Override
	public void setParameter(PreparedStatement ps, int i, Role parameter, JdbcType jdbcType) 
			throws SQLException 
	{	
		if(parameter==null){
			ps.setString(i,null);
		}
		else {
			ps.setString(i,parameter.getName());
		}
	}

	@Override
	public Role getResult(ResultSet rs, String columnName) throws SQLException {
		String roleName = rs.getString(columnName);
		if(roleName == null){
			return null;
		}
		else {
			Role role = new Role();
			role.setName(roleName);
			return role;
		}
	}

	@Override
	public Role getResult(ResultSet rs, int columnIndex) throws SQLException {
		String roleName = rs.getString(columnIndex);
		if(roleName == null){
			return null;
		}
		else {
			Role role = new Role();
			role.setName(roleName);
			return role;
		}
	}

	@Override
	public Role getResult(CallableStatement cs, int columnIndex) throws SQLException {
		String roleName = cs.getString(columnIndex);
		if(roleName == null){
			return null;
		}
		else {
			Role role = new Role();
			role.setName(roleName);
			return role;
		}
	}


}
