/**
 * 
 */
package org.socraticgrid.hl7.services.uc.service.impl;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.socraticgrid.hl7.services.uc.db.UCSDataConnection;
import org.socraticgrid.hl7.services.uc.functional.UCSProperties;
import org.socraticgrid.hl7.services.uc.functional.UCSProperties.PROPS;

/**
 * @author steven
 * @created Jan 22, 2014

 */
public class USCDataConnection_TestIT {

	/**
	 * Test method for {@link org.socraticgrid.hl7.services.uc.db.UCSDataConnection#getSqlSession()}.
	 */
	@Test
	public void testGetSqlSession() {
		//Get the default session
		SqlSession session = UCSDataConnection.getSqlSession();
		assertNotNull(session);
		String envId = session.getConfiguration().getEnvironment().getId();
		String configId = UCSProperties.getProperty(PROPS.ENVIRONMENT);
		assertEquals(configId, envId);
	}

	/**
	 * Test method for {@link org.socraticgrid.hl7.services.uc.db.UCSDataConnection#getSqlSession(org.apache.ibatis.session.ExecutorType)}.
	 */
	@Test
	public void testGetSqlSessionExecutorType() {
		//Get the default session
		SqlSession session = UCSDataConnection.getSqlSession(ExecutorType.BATCH);
		assertNotNull(session);
		String envId = session.getConfiguration().getEnvironment().getId();
		String configId = UCSProperties.getProperty(PROPS.ENVIRONMENT);
		assertEquals(configId, envId);
		
		ExecutorType type = session.getConfiguration().getDefaultExecutorType();
//		assertEquals(ExecutorType.BATCH, type);
	}
	
	@Test
	public void testGetTables() {
		//Get the default session
		SqlSession session = UCSDataConnection.getSqlSession();
		assertNotNull(session);
		try( Connection connection = session.getConnection(); ){
			
			Statement stmt = connection.createStatement();
			ResultSet results = stmt.executeQuery("select * from sys.systables where tabletype = 'T'");
			System.out.println("\n\n*****************\nVACDS SCHEMA");
			int ix = 1;
			while(results.next()) {
				System.err.println("\t 1:"+results.getString("TABLENAME"));
			}
			System.out.println("\n*****************\n\n");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
