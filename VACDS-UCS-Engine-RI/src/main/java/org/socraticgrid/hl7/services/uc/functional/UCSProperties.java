/**
 * 
 */
package org.socraticgrid.hl7.services.uc.functional;

import java.io.InputStream;
import java.util.Properties;

import org.apache.ibatis.io.Resources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author steven
 * @created Jan 22, 2014

 */
public class UCSProperties {
	/**
	 * A way to enumerate properties... provides entry for a default value
	 * 
	 * NOTE: Keep it current
	 * 
	 */
	public enum PROPS {
		UCSCONFIG_FILE("ucs.properties","ucs.properties")
		, JUNIT("junit","default-property")
		, ENVIRONMENT("environment","test") //corresponds to environment in IBatis.xml
		;

		private String prop;
		public String getProp(){ return prop; }
		private String defaultValue;
		public String getDefaultValue(){ return defaultValue; }

		PROPS(String prop, String defaultValue){
			this.prop = prop;
			this.defaultValue = defaultValue;
		}
	}

	private static Logger log = LoggerFactory.getLogger(UCSProperties.class);

	private UCSProperties() {};


	private static final class PropertiesSingleton {
		private static volatile Properties properties = new Properties();
		
		static {
			if(UCSProperties.class.getClassLoader().getResource(PROPS.UCSCONFIG_FILE.getProp()) != null) {
				try( InputStream inputStream = Resources.getResourceAsStream(PROPS.UCSCONFIG_FILE.getProp()) ) {
					if(inputStream != null){
						properties.load(inputStream);
					}
				} catch (Exception e) {
					log.error("************************** Problem configuring UCOMM !!!",e);
				}
				log.error("************************** Problem configuring UCOMM !!!"+
						"\n Configuration file cannot be found.");
			}
		}
	}


	public static String getProperty(PROPS props){
		return PropertiesSingleton.properties.getProperty(props.getProp(),props.getDefaultValue());
	}

	public static void setProperty(PROPS props, String value){
		PropertiesSingleton.properties.setProperty(props.getProp(),value);
	}

}
