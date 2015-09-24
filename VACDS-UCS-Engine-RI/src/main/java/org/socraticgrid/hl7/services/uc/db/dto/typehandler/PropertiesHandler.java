/**
 * 
 */
package org.socraticgrid.hl7.services.uc.db.dto.typehandler;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;



/**
 * @author steven
 * @created Jan 23, 2014

 */
@MappedTypes(Properties.class)
public class PropertiesHandler implements TypeHandler<Properties> {

	@Override
	public void setParameter(PreparedStatement ps, int i, Properties parameter, JdbcType jdbcType) 
			throws SQLException 
	{	
		if(parameter==null){
			ps.setString(i,null);
		}
		else {
			try(StringWriter writer = new StringWriter();) {
				parameter.store(writer,null);
				ps.setString(i,writer.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public Properties getResult(ResultSet rs, String columnName) throws SQLException {
		String properties = rs.getString(columnName);
		if(properties == null){
			return null;
		}
		else {
			Properties prop = new Properties();
			try {
				prop.load(new StringReader(properties));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return prop;
		}
	}

	@Override
	public Properties getResult(ResultSet rs, int columnIndex) throws SQLException {
		String properties = rs.getString(columnIndex);
		if(properties == null){
			return null;
		}
		else {
			Properties prop = new Properties();
			try {
				prop.load(new StringReader(properties));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return prop;
		}
	}

	@Override
	public Properties getResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		String properties = cs.getString(columnIndex);
		if(properties == null){
			return null;
		}
		else {
			Properties props = new Properties();
			try {
				props.load(new StringReader(properties));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return props;
		}
	}


}
