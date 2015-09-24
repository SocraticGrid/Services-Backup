/**
 * 
 */
package org.socraticgrid.hl7.services.uc.db;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.socraticgrid.hl7.services.uc.functional.UCSProperties;
import org.socraticgrid.hl7.services.uc.functional.UCSProperties.PROPS;

/**
 * @author steven
 * @created Jan 22, 2014

 */
public class UCSDataConnection {

	private static final String CONFIG = "IBatis.xml";
	
	private static Logger log = LoggerFactory.getLogger(UCSDataConnection.class);

	private UCSDataConnection() {};
	
	private static final class ConnectionSingleton {
		private static volatile SqlSessionFactory sqlSessionFactory;
		static {
			try {
				String resource = CONFIG;
				InputStream inputStream = Resources.getResourceAsStream(resource);
				SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
				sqlSessionFactory = builder.build(inputStream,UCSProperties.getProperty(PROPS.ENVIRONMENT));
				inputStream.close();
			} catch (Exception e) {
				log.error("************************** Problem configuring MyBatis !!!",e);
			}
		}
		
		private static SqlSession getSqlSession() {
			return sqlSessionFactory.openSession();
		}
		
		private static SqlSession getSqlSession(ExecutorType type) {
			return sqlSessionFactory.openSession(type);
		}
	}
	

	public static final SqlSession getSqlSession() {
		return ConnectionSingleton.getSqlSession();
	}
	

	/**
	 * Types are BATCH, REUSE, SIMPLE
	 * @param type
	 * @return
	 */
	public static final SqlSession getSqlSession(ExecutorType type) {
		return ConnectionSingleton.getSqlSession(type);
	}
	
	public static void refreshEnvironment(){
		try {
			InputStream inputStream = Resources.getResourceAsStream(CONFIG);
			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
			ConnectionSingleton.sqlSessionFactory = builder.build(inputStream,UCSProperties.getProperty(PROPS.ENVIRONMENT));
			inputStream.close();
			log.info("*******************");
			log.info("\t\tUCOMM Connection changed to - "+UCSProperties.getProperty(PROPS.ENVIRONMENT));
			log.info("*******************");
		} catch (Exception e) {
			log.error("*******************");
			log.error("\tERROR : UCOMM Connection COULD NOT BE changed !!!!!");
			log.error("*******************");
			log.error(e.getLocalizedMessage(),e);
		}
	}

	
	
	/**
	 * For Testing
	 */
	public static void main(String... agrs){
		
		SqlSession sqlSession = getSqlSession();
		System.out.println("sqlSession isNull ? "+(sqlSession == null));
		
	}

}
